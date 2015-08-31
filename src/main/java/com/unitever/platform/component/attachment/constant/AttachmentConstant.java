package com.unitever.platform.component.attachment.constant;

import java.util.HashMap;
import java.util.Map;

public class AttachmentConstant {

	/**
	 * 附件配置，子目录类型，日期
	 */
	public static final String ATTACHMENTCONFIG_SUBDIRTYPE_BY_DATE = "0";

	/**
	 * 附件配置，子目录类型，业务
	 */
	public static final String ATTACHMENTCONFIG_SUBDIRTYPE_BY_BIZ = "1";

	/**
	 * 附件配置，层级取值类型，按年
	 */
	public static final Integer ATTACHMENTCONFIG_DIRLAYER_YEAR = 1;

	/**
	 * 附件配置，层级取值类型，按月
	 */
	public static final Integer ATTACHMENTCONFIG_DIRLAYER_MONTH = 2;

	/**
	 * 附件配置，层级取值类型，按日
	 */
	public static final Integer ATTACHMENTCONFIG_DIRLAYER_DAY = 3;

	/**
	 * 临时文件
	 */
	public static final String ATTACHMENT_STATUS_TEMP = "0";

	/**
	 * 使用中的文件
	 */
	public static final String ATTACHMENT_STATUS_USE = "1";

	/**
	 * 已删除的文件
	 */
	public static final String ATTACHMENT_STATUS_DELETE = "2";

	/**
	 * 附件上传(显示)标签中，默认modelName的值
	 */
	public static final String ATTACHMENTTAG_DEFAULT_MODELNAME = "model";

	/**
	 * 附件上传(显示)标签中，默认fieldName的值
	 */
	public static final String ATTACHMENTTAG_DEFAULT_FIELDNAME = "id";

	/**
	 * 附件上传(显示)标签中，默认period的值
	 */
	public static final String ATTACHMENTTAG_DEFAULT_PERIOD = "year";

	/**
	 * 附件显示标签中，默认showAll的值
	 */
	public static final Boolean ATTACHMENTTAG_DEFAULT_SHOWALL = true;

	/**
	 * 附件下载时间有效期，永久
	 */
	public static final String ATTACHMENT_DOWNLOAD_PERIOD_FOREVER = "forever";

	/**
	 * 附件下载时间有效期，年
	 */
	public static final String ATTACHMENT_DOWNLOAD_PERIOD_YEAR = "year";

	/**
	 * 附件下载时间有效期，月
	 */
	public static final String ATTACHMENT_DOWNLOAD_PERIOD_MONTH = "month";

	/**
	 * 附件下载时间有效期，日
	 */
	public static final String ATTACHMENT_DOWNLOAD_PERIOD_DAY = "day";

	/**
	 * 附件下载时间有效期，小时
	 */
	public static final String ATTACHMENT_DOWNLOAD_PERIOD_HOUR = "hour";

	/**
	 * 下载的相对路径
	 */
	public static final String ATTACHMENT_DOWNLOAD_RELATIVE_URL = "/sys/attachment/download.do";

	/**
	 * 下载多个owner的文件附件(打包)
	 */
	public static final String ATTACHMENT_DOWNLOAD_MULITOWNER_RELATIVE_URL = "/sys/attachment/downloadMulitOwner.do";

	/**
	 * 图片的相对路径
	 */
	public static final String ATTACHMENT_PIC_RELATIVE_URL = "/sys/attachment/showPic.do";

	/**
	 * 视频的相对路径
	 */
	public static final String ATTACHMENT_AUDIO_RELATIVE_URL = "/sys/attachment/showAudio.do";

	/**
	 * 显示的相对路径，根据文件后缀自动判断
	 */
	public static final String ATTACHMENT_SHOW_RELATIVE_URL = "/sys/attachment/show.do";

	/**
	 * token key
	 */
	public static final String ATTACHMENT_TOKEN_KEY = "unitever";

	/**
	 * 默认的配置编码
	 */
	public static final String ATTACHMENT_DEFAULTCONFIG_CODE = "default";

	/**
	 * UEditor的配置编码
	 */
	public static final String ATTACHMENT_UEDITOR_CODE = "sys_ueditor";

	/**
	 * UEditor附件的ownerId
	 */
	public static final String ATTACHMENT_UEDITOR_OWNER_ID = "20130613111248163324992470514346";

	/**
	 * 附件压缩zip包buffer
	 */
	public static final int ATTACHMENT_ZIP_BUFFER_SIZE = 16 * 1024;

	/**
	 * 应用程序输出类型
	 */
	public static final String CONTENTTYPE_APPLICATION = "application/octet-stream";

	/**
	 * 图片输出类型
	 */
	public static final String CONTENTTYPE_IMAGE = "image/jpeg";

	/**
	 * 视频输出类型
	 */
	public static final String CONTENTTYPE_AUDIO = "audio/mpeg";

	public static final Map<String, String> contentTypeMap = new HashMap<String, String>();
	static {
		contentTypeMap.put("abs", "audio/x-mpeg");
		contentTypeMap.put("ai", "application/postscript");
		contentTypeMap.put("aif", "audio/x-aiff");
		contentTypeMap.put("aifc", "audio/x-aiff");
		contentTypeMap.put("aiff", "audio/x-aiff");
		contentTypeMap.put("aim", "application/x-aim");
		contentTypeMap.put("art", "image/x-jg");
		contentTypeMap.put("asf", "video/x-ms-asf");
		contentTypeMap.put("asx", "video/x-ms-asf");
		contentTypeMap.put("au", "audio/basic");
		contentTypeMap.put("avi", "video/x-msvideo");
		contentTypeMap.put("avx", "video/x-rad-screenplay");
		contentTypeMap.put("bcpio", "application/x-bcpio");
		contentTypeMap.put("bin", "application/octet-stream");
		contentTypeMap.put("bmp", "image/bmp");
		contentTypeMap.put("body", "text/html");
		contentTypeMap.put("cdf", "application/x-cdf");
		contentTypeMap.put("cer", "application/x-x509-ca-cert");
		contentTypeMap.put("class", "application/java");
		contentTypeMap.put("cpio", "application/x-cpio");
		contentTypeMap.put("csh", "application/x-csh");
		contentTypeMap.put("css", "text/css");
		contentTypeMap.put("dib", "image/bmp");
		contentTypeMap.put("doc", "application/msword");
		contentTypeMap.put("dtd", "application/xml-dtd");
		contentTypeMap.put("dv", "video/x-dv");
		contentTypeMap.put("dvi", "application/x-dvi");
		contentTypeMap.put("eps", "application/postscript");
		contentTypeMap.put("etx", "text/x-setext");
		contentTypeMap.put("exe", "application/octet-stream");
		contentTypeMap.put("gif", "image/gif");
		contentTypeMap.put("gtar", "application/x-gtar");
		contentTypeMap.put("gz", "application/x-gzip");
		contentTypeMap.put("hdf", "application/x-hdf");
		contentTypeMap.put("hqx", "application/mac-binhex40");
		contentTypeMap.put("htc", "text/x-component");
		contentTypeMap.put("htm", "text/html");
		contentTypeMap.put("html", "text/html");
		contentTypeMap.put("ief", "image/ief");
		contentTypeMap.put("jad", "text/vnd.sun.j2me.app-descriptor");
		contentTypeMap.put("jar", "application/java-archive");
		contentTypeMap.put("java", "text/plain");
		contentTypeMap.put("jnlp", "application/x-java-jnlp-file");
		contentTypeMap.put("jpe", "image/jpeg");
		contentTypeMap.put("jpeg", "image/jpeg");
		contentTypeMap.put("jpg", "image/jpeg");
		contentTypeMap.put("js", "text/javascript");
		contentTypeMap.put("jsf", "text/plain");
		contentTypeMap.put("jspf", "text/plain");
		contentTypeMap.put("kar", "audio/x-midi");
		contentTypeMap.put("latex", "application/x-latex");
		contentTypeMap.put("m3u", "audio/x-mpegurl");
		contentTypeMap.put("mac", "image/x-macpaint");
		contentTypeMap.put("man", "application/x-troff-man");
		contentTypeMap.put("mathml", "application/mathml+xml");
		contentTypeMap.put("me", "application/x-troff-me");
		contentTypeMap.put("mid", "audio/x-midi");
		contentTypeMap.put("midi", "audio/x-midi");
		contentTypeMap.put("mif", "application/x-mif");
		contentTypeMap.put("mov", "video/quicktime");
		contentTypeMap.put("movie", "video/x-sgi-movie");
		contentTypeMap.put("mp1", "audio/x-mpeg");
		contentTypeMap.put("mp2", "audio/x-mpeg");
		contentTypeMap.put("mp3", "audio/x-mpeg");
		contentTypeMap.put("mp4", "video/mp4");
		contentTypeMap.put("mpa", "audio/x-mpeg");
		contentTypeMap.put("mpe", "video/mpeg");
		contentTypeMap.put("mpeg", "video/mpeg");
		contentTypeMap.put("mpega", "audio/x-mpeg");
		contentTypeMap.put("mpg", "video/mpeg");
		contentTypeMap.put("mpv2", "video/mpeg2");
		contentTypeMap.put("ms", "application/x-wais-source");
		contentTypeMap.put("nc", "application/x-netcdf");
		contentTypeMap.put("oda", "application/oda");
		contentTypeMap.put("odb", "application/vnd.oasis.opendocument.database");
		contentTypeMap.put("odc", "application/vnd.oasis.opendocument.chart");
		contentTypeMap.put("odf", "application/vnd.oasis.opendocument.formula");
		contentTypeMap.put("odg", "application/vnd.oasis.opendocument.graphics");
		contentTypeMap.put("odi", "application/vnd.oasis.opendocument.image");
		contentTypeMap.put("odm", "application/vnd.oasis.opendocument.text-master");
		contentTypeMap.put("odp", "application/vnd.oasis.opendocument.presentation");
		contentTypeMap.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
		contentTypeMap.put("odt", "application/vnd.oasis.opendocument.text");
		contentTypeMap.put("ogg", "application/ogg");
		contentTypeMap.put("otg", "application/vnd.oasis.opendocument.graphics-template");
		contentTypeMap.put("oth", "application/vnd.oasis.opendocument.text-web");
		contentTypeMap.put("otp", "application/vnd.oasis.opendocument.presentation-template");
		contentTypeMap.put("ots", "application/vnd.oasis.opendocument.spreadsheet-template");
		contentTypeMap.put("ott", "application/vnd.oasis.opendocument.text-template");
		contentTypeMap.put("pbm", "image/x-portable-bitmap");
		contentTypeMap.put("pct", "image/pict");
		contentTypeMap.put("pdf", "application/pdf");
		contentTypeMap.put("pgm", "image/x-portable-graymap");
		contentTypeMap.put("pic", "image/pict");
		contentTypeMap.put("pict", "image/pict");
		contentTypeMap.put("pls", "audio/x-scpls");
		contentTypeMap.put("png", "image/png");
		contentTypeMap.put("pnm", "image/x-portable-anymap");
		contentTypeMap.put("pnt", "image/x-macpaint");
		contentTypeMap.put("ppm", "image/x-portable-pixmap");
		contentTypeMap.put("ppt", "application/vnd.ms-powerpoint");
		contentTypeMap.put("pps", "application/vnd.ms-powerpoint");
		contentTypeMap.put("ps", "application/postscript");
		contentTypeMap.put("psd", "image/x-photoshop");
		contentTypeMap.put("qt", "video/quicktime");
		contentTypeMap.put("qti", "image/x-quicktime");
		contentTypeMap.put("qtif", "image/x-quicktime");
		contentTypeMap.put("ras", "image/x-cmu-raster");
		contentTypeMap.put("rdf", "application/rdf+xml");
		contentTypeMap.put("rgb", "image/x-rgb");
		contentTypeMap.put("rm", "application/vnd.rn-realmedia");
		contentTypeMap.put("roff", "application/x-troff");
		contentTypeMap.put("rtf", "application/rtf");
		contentTypeMap.put("rtx", "text/richtext");
		contentTypeMap.put("sh", "application/x-sh");
		contentTypeMap.put("shar", "application/x-shar");
		contentTypeMap.put("smf", "audio/x-midi");
		contentTypeMap.put("sit", "application/x-stuffit");
		contentTypeMap.put("snd", "audio/basic");
		contentTypeMap.put("src", "application/x-wais-source");
		contentTypeMap.put("sv4cpio", "application/x-sv4cpio");
		contentTypeMap.put("sv4crc", "application/x-sv4crc");
		contentTypeMap.put("svg", "image/svg+xml");
		contentTypeMap.put("svgz", "image/svg+xml");
		contentTypeMap.put("swf", "application/x-shockwave-flash");
		contentTypeMap.put("t", "application/x-troff");
		contentTypeMap.put("tar", "application/x-tar");
		contentTypeMap.put("tcl", "application/x-tcl");
		contentTypeMap.put("tex", "application/x-tex");
		contentTypeMap.put("texi", "application/x-texinfo");
		contentTypeMap.put("texinfo", "application/x-texinfo");
		contentTypeMap.put("tif", "image/tiff");
		contentTypeMap.put("tiff", "image/tiff");
		contentTypeMap.put("tr", "application/x-troff");
		contentTypeMap.put("tsv", "text/tab-separated-values");
		contentTypeMap.put("txt", "text/plain");
		contentTypeMap.put("ulw", "audio/basic");
		contentTypeMap.put("ustar", "application/x-ustar");
		contentTypeMap.put("vxml", "application/voicexml+xml");
		contentTypeMap.put("xbm", "image/x-xbitmap");
		contentTypeMap.put("xht", "application/xhtml+xml");
		contentTypeMap.put("xhtml", "application/xhtml+xml");
		contentTypeMap.put("xls", "application/vnd.ms-excel");
		contentTypeMap.put("xml", "application/xml");
		contentTypeMap.put("xpm", "image/x-xpixmap");
		contentTypeMap.put("xsl", "application/xml");
		contentTypeMap.put("xslt", "application/xslt+xml");
		contentTypeMap.put("xul", "application/vnd.mozilla.xul+xml");
		contentTypeMap.put("xwd", "image/x-xwindowdump");
		contentTypeMap.put("vsd", "application/x-visio");
		contentTypeMap.put("wav", "audio/x-wav");
		contentTypeMap.put("wbmp", "image/vnd.wap.wbmp");
		contentTypeMap.put("wml", "text/vnd.wap.wml");
		contentTypeMap.put("wmlc", "application/vnd.wap.wmlc");
		contentTypeMap.put("wmls", "text/vnd.wap.wmlscript");
		contentTypeMap.put("wmlscriptc", "application/vnd.wap.wmlscriptc");
		contentTypeMap.put("wmv", "video/x-ms-wmv");
		contentTypeMap.put("wrl", "x-world/x-vrml");
		contentTypeMap.put("wspolicy", "application/wspolicy+xml");
		contentTypeMap.put("Z", "application/x-compress");
		contentTypeMap.put("zip", "application/zip");
	}
}
