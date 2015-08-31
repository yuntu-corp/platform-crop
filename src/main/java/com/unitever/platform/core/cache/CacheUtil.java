package com.unitever.platform.core.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.unitever.platform.util.PropertyUtil;

public class CacheUtil {

	private static final Log LOG = LogFactory.getLog(CacheUtil.class);

	private static DBCollection dbCollection = null;

	// private static long lastConnectTime = System.currentTimeMillis();

	static {
		connection();
	}

	private static synchronized boolean checkConnection() {
		// 禁止自动连接数据库，防止出现脏缓存。
		// if (dbCollection == null && (System.currentTimeMillis() - lastConnectTime) > 1000 * 60 * 10) {// 如果数据库断开，10分钟连接一次
		// connection();
		// }
		if (dbCollection == null) {
			LOG.warn("Mongo连接失败，缓存不可用！");
			return false;
		}
		return true;
	}

	private static synchronized void connection() {
		try {
			String url = PropertyUtil.getProperty("conf/mongo.properties", "mongo.url");
			int port = Integer.valueOf(PropertyUtil.getProperty("conf/mongo.properties", "mongo.port"));
			Mongo mongo = new Mongo(url, port);
			String userName = PropertyUtil.getProperty("conf/mongo.properties", "mongo.user");
			String password = PropertyUtil.getProperty("conf/mongo.properties", "mongo.password");
			String dbName = PropertyUtil.getProperty("conf/mongo.properties", "mongo.dbName");
			boolean flag = true;
			if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)) {
				DB adminDb = mongo.getDB("admin");
				flag = adminDb.authenticate(userName, password.toCharArray());
			}
			if (flag) {
				dbCollection = mongo.getDB(dbName).getCollection("cache");
			} else {
				LOG.warn("Mongo连接失败，缓存不可用！");
				dbCollection = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.warn("Mongo连接失败，缓存不可用！", e);
			dbCollection = null;
		}
		// lastConnectTime = System.currentTimeMillis();
	}

	public static final void storeCache(String expiredKey, String businessKey, Object value) {
		storeCache(new String[] { expiredKey }, businessKey, value);
	}

	public static final void storeCache(List<String> expiredKey, String businessKey, Object value) {
		storeCache(expiredKey.toArray(new String[] {}), businessKey, value);
	}

	public static final void storeCache(String[] expiredKeys, String businessKey, Object val) {
		if (!checkConnection()) {
			return;
		}
		BasicDBObject data = new BasicDBObject();
		data.append("expiredKey", expiredKeys);
		data.append("businessKey", businessKey);
		data.append("className", val.getClass().getName());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = null;
		boolean flag = true;
		try {
			oos = new ObjectOutputStream(baos);
			oos.writeObject(val);
			oos.flush();
			oos.close();
			data.append("value", baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			LOG.warn("序列化失败，保存缓存失败！");
			flag = false;
		}
		if (flag) {
			try {
				dbCollection.insert(data);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.warn("保存缓存失败！");
				dbCollection = null;
			}
		}
	}

	public static final Object getCache(String businessKey) {
		if (!checkConnection()) {
			return null;
		}
		BasicDBObject data = new BasicDBObject();
		data.append("businessKey", businessKey);
		DBObject obj = null;
		try {
			obj = dbCollection.findOne(data);
		} catch (MongoException e) {
			e.printStackTrace();
			LOG.warn("读取缓存失败！", e);
			dbCollection = null;
			obj = null;
		}
		if (obj == null || obj.get("value") == null) {
			return null;
		}
		Object result = null;
		try {
			ObjectInputStream bis = new ObjectInputStream(new ByteArrayInputStream((byte[]) obj.get("value")));
			result = bis.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			LOG.warn("反序列化失败，读取缓存失败！" + obj.get("_id"));
		}
		return result;
	}

	public static final void deleteCache(String expiredKey) {
		if (!checkConnection()) {
			return;
		}
		BasicDBObject data = new BasicDBObject();
		data.append("expiredKey", expiredKey);

		try {
			DBCursor cursor = dbCollection.find(data);
			List<DBObject> result = new ArrayList<DBObject>();
			while (cursor.hasNext()) {
				result.add(cursor.next());
			}

			for (DBObject obj : result) {
				dbCollection.remove(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOG.warn("删除缓存失败，禁用缓存，缓存可能已经不同步，请手动清除缓存！", e);
			dbCollection = null;
		}
	}

	public static final void deleteAll() {
		if (!checkConnection()) {
			return;
		}
		dbCollection.drop();
	}
}
