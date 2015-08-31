package com.unitever.platform.util.lucene.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import com.unitever.platform.core.dataSource.helper.DataSourceHolder;
import com.unitever.platform.util.PropertyUtil;
import com.unitever.platform.util.lucene.ILuceneService;
import com.unitever.platform.util.lucene.LuceneDoc;
import com.unitever.platform.util.lucene.LuceneKeyWord;
import com.unitever.platform.util.lucene.LuceneLongRange;
import com.unitever.platform.util.lucene.LuceneSortField;

public class LocalLuceneService implements ILuceneService {

	private static File luceneDir = null;

	static {
		luceneDir = new File(PropertyUtil.getProperty("/conf/lucene.properties", "lucene.localDir"));
	}

	@Override
	public void addIndex(String code, List<LuceneDoc> docs) {
		try {
			File luceneTrueDir = getLuceneTrueDir(code);
			IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_47, new StandardAnalyzer(Version.LUCENE_47));
			IndexWriter writer = new IndexWriter(new SimpleFSDirectory(luceneTrueDir), conf);
			for (LuceneDoc doc : docs) {
				Document document = new Document();
				document.add(new TextField("businessId", doc.getBusinessId(), Store.YES));
				for (String key : doc.getSearchFieldMap().keySet()) {
					document.add(new TextField(key, doc.getSearchFieldMap().get(key), Store.YES));
				}
				for (String key : doc.getRangeFieldMap().keySet()) {
					document.add(new LongField(key, doc.getRangeFieldMap().get(key), Store.YES));
				}
				writer.addDocument(document);
			}
			writer.commit();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private File getLuceneTrueDir(String code) {
		Object dsName = DataSourceHolder.getCurDataSourceKey();
		String dsNameStr = "";
		if (dsName != null && StringUtils.isNotBlank(dsName.toString())) {
			dsName.toString();
		}
		return new File(luceneDir.getAbsolutePath() + "/" + dsNameStr + "/" + code);
	}

	@Override
	public void updateIndex(String code, LuceneDoc doc) {
		try {
			File luceneTrueDir = getLuceneTrueDir(code);
			IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_47, new StandardAnalyzer(Version.LUCENE_47));
			IndexWriter writer = new IndexWriter(new SimpleFSDirectory(luceneTrueDir), conf);
			Document document = new Document();
			document.add(new TextField("businessId", doc.getBusinessId(), Store.YES));
			for (String key : doc.getSearchFieldMap().keySet()) {
				document.add(new TextField(key, doc.getSearchFieldMap().get(key), Store.YES));
			}
			for (String key : doc.getRangeFieldMap().keySet()) {
				document.add(new LongField(key, doc.getRangeFieldMap().get(key), Store.YES));
			}
			writer.updateDocument(new Term("businessId", doc.getBusinessId()), document);
			writer.commit();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void deleteIndex(String code, String businessId) {
		try {
			IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_47, new StandardAnalyzer(Version.LUCENE_47));
			IndexWriter writer = new IndexWriter(new SimpleFSDirectory(getLuceneTrueDir(code)), conf);
			writer.deleteDocuments(new Term("businessId", businessId));
			writer.forceMergeDeletes();
			writer.commit();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public List<String> searchIndex(String code, int count, List<LuceneKeyWord> keyWordList, List<LuceneLongRange> searchRangeList, List<LuceneSortField> sortFieldList) {
		try {
			File luceneTrueDir = getLuceneTrueDir(code);
			if (luceneTrueDir.list().length == 0) {// 未创建索引时
				return new ArrayList<String>();
			}
			DirectoryReader reader = DirectoryReader.open(FSDirectory.open(luceneTrueDir));
			IndexSearcher searcher = new IndexSearcher(reader);
			BooleanQuery query = new BooleanQuery();

			if (keyWordList.size() > 0) {
				BooleanQuery query2 = new BooleanQuery();
				for (LuceneKeyWord keyWord : keyWordList) {
					MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_47, new String[] { keyWord.getKey() }, new StandardAnalyzer(Version.LUCENE_47));
					Query q1 = parser.parse(keyWord.getValue());
					query2.add(q1, keyWord.isAnd() ? Occur.MUST : Occur.SHOULD);
				}
				query.add(query2, Occur.MUST);
			}

			for (LuceneLongRange range : searchRangeList) {
				NumericRangeQuery<Long> numRange = NumericRangeQuery.newLongRange(range.getKey(), range.getMin(), range.getMax(), range.isMinInclusive(), range.isMaxInclusive());
				query.add(numRange, Occur.MUST);
			}
			Sort sort = null;
			List<SortField> sortFields = new ArrayList<SortField>();
			for (LuceneSortField sortField : sortFieldList) {
				SortField field = new SortField(sortField.getKey(), Type.STRING_VAL, sortField.isReverse());
				sortFields.add(field);
			}
			if (sortFieldList.size() > 0) {
				sort = new Sort(sortFields.toArray(new SortField[] {}));
			}
			ScoreDoc[] hits = null;
			if (sort != null) {
				hits = searcher.search(query, null, count, sort).scoreDocs;
			} else {
				hits = searcher.search(query, null, count).scoreDocs;
			}
			List<String> result = new ArrayList<String>();
			for (ScoreDoc scoreDoc : hits) {
				Document doc = searcher.doc(scoreDoc.doc);
				result.add(doc.getField("businessId").stringValue());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}
