package com.unitever.platform.util.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * Tree的工具类
 * 
 * @author 沈涛
 * 
 */
public class TreeUtil {

	/**
	 * 构建多结点树,根据父结点构建层次关系
	 * 
	 * @param nodes
	 * @return
	 */
	public static String buildTreeByParent(List<TreeNode> nodes) {
		Map<String, TreeNode> nodeMap = new LinkedHashMap<String, TreeNode>(0);
		for (TreeNode node : nodes) {
			nodeMap.put(node.getId(), node);
		}
		
//		final List<String> nullFilter = Arrays.asList(new String[]{"icon"});
		final List<String> falseFilter = Arrays.asList(new String[]{"isParent","open","nocheck"});
		final List<String> excludesFilter = Arrays.asList(new String[]{"num","target","parentId"});
		
		PropertyFilter propertyFilter = new PropertyFilter() {
			public boolean apply(Object source/* 属性的拥有者 */, String name /* 属性名字 */, Object value/* 属性值 */) {
				if(falseFilter.contains(name)&&(Byte)value==0){
					return false;
				}
				// return true to skip name 
				if (excludesFilter.contains(name)) {
					return false;
				}
				return true;
			}
		};
		
		return JSONArray.toJSONString(getNodeList(nodeMap), propertyFilter);
	}

	/**
	 * 构建多结点树,根据子结点构建层次关系
	 * 
	 * @param nodes
	 * @return
	 */
	public static String buildTree(List<TreeNode> nodes) {
		return JSONArray.toJSONString(nodes);
	}

	/**
	 * 构建单结点树,根据子结点构建层次关系
	 * @param root
	 * @return
	 */
	public static String buildTree(TreeNode root) {
		String str = JSONObject.toJSONString(root);
		return "[" + str + "]";
	}

	/**
	 * 构建单结点树,根据父结点构建层次关系
	 * @param root
	 * @return
	 */
	public static String buildTreeByParent(TreeNode root) {
		String str = JSONObject.toJSONString(root);
		return "[" + str + "]";
	}

	/**
	 * 给结点排序
	 * @param nodes
	 * @return
	 */
	private static List<TreeNode> getNodeList(Map<String, TreeNode> nodes) {
		List<TreeNode> roots = new ArrayList<TreeNode>();
		for (TreeNode node : nodes.values()) {
			if (StringUtils.isNotEmpty(node.getParentId())) {
				TreeNode parentnode = nodes.get(node.getParentId());
				if (null != parentnode) {
					parentnode.setIsParent(true);
					parentnode.getNodes().add(node);
				}
			} else {
				roots.add(node);
			}
		}
		Collections.sort(roots, new Comparator<TreeNode>() {
			public int compare(TreeNode o1, TreeNode o2) {
				if (o1 == null || o2 == null || o1.getNum() == null || o2.getNum() == null)
					return -1;
				return Integer.parseInt(o1.getNum()) - Integer.parseInt(o2.getNum());
			}
		});
		return roots;
	}

	public static String getTestNodes() {
		return "[{id:1, pId:0, name:'北京',isParent:true},{id:2, pId:0, name:'天津'},{id:4, pId:0,name:'河北省',nodes:[{id:41, pId:4, name:'石家庄'},{id:42, pId:4, name:'张家口',nodes:[{id:421,pId:42,name:'涿鹿',nodes:[{id:4211,pId:421,name:'西关村'}]}]}]}]";
	}

	public static String getMenuTree() {
		return "[{id:1, pId:0, name:'百度',url_:'http://www.baidu.com'},{id:2, pId:0, name:'谷歌',url_:'http://www.baidu.com'},{id:4, pId:0, name:'购物网站', open:true,nodes:[{id:41, pId:4, name:'淘宝',url_:'http://www.taobao.com'},{id:42, pId:4, name:'京东',url_:'http://www.360buy.com'}]}]";
	}
	
	public static String getDeptNodes() {
		return "[{id:1,pId:0,name:'朝阳师范学校附属小学',open:true,nodes:[{id:11,pId:1,name:'校长室'},{id:12,pId:1,name:'办公室'},{id:13,pId:1,name:'本部',nodes:[{id:31,pId:13,name:'教研组'},{id:32,pId:13,name:'备课组'}]},{id:14,pId:0,name:'和平街校区',open:true,nodes:[{id:41,pId:14,name:'德育处'},{id:42,pId:14,name:'教务处'}]}]}]";
	}
	
	public static String getEclassNodes() {
		return "[{id:1, pId:0, name:'小学部',isParent:true},{id:2, pId:0, name:'初中部'},{id:4, pId:0,name:'高中部',nodes:[{id:41, pId:4, name:'1年级'},{id:42, pId:4, name:'2年级',nodes:[{id:421,pId:42,name:'1班'}]}]}]";
	}

}
