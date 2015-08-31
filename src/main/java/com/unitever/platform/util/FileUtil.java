package com.unitever.platform.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.unitever.platform.core.spring.SpringMVCUtil;
import com.unitever.platform.util.web.WebUtil;

/**
 * 封装了文件操作相关的一些方法。
 */
public class FileUtil {

	public final static String LINE_FEED = System.getProperty("line.separator");
	public final static String FILE_SEPARATOR = System.getProperty("file.separator");
	public final static String EXTENTION_PDF = "pdf";
	public final static String EXTENTION_DOC = "doc";
	public final static String EXTENTION_SWF = "swf";

	/**
	 * 获取临时目录
	 * 
	 * @return
	 */
	public static String getTempDir() {
		String tempPath = System.getProperty("java.io.tmpdir");
		File tempFile = new File(tempPath);
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		return tempFile.getAbsolutePath() + "\\";
	}

	/**
	 * 判断是否是pdf文件
	 * 
	 * @param fileName
	 *            文件名（可以包含路径）
	 * @return
	 */
	public static boolean isPdfFile(String fileName) {
		return getFileExtention(new File(fileName).getName()).toLowerCase().equals(EXTENTION_PDF);
	}

	/**
	 * 判断是否是doc文件
	 * 
	 * @param fileName
	 *            文件名（可以包含路径）
	 * @return
	 */
	public static boolean isDocFile(String fileName) {
		return getFileExtention(new File(fileName).getName()).toLowerCase().equals(EXTENTION_DOC);
	}

	/**
	 * 判断是否是swf文件
	 * 
	 * @param fileName
	 *            文件名（可以包含路径）
	 * @return
	 */
	public static boolean isSwfFile(String fileName) {
		return getFileExtention(new File(fileName).getName()).toLowerCase().equals(EXTENTION_SWF);
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtention(String fileName) {
		if (fileName == null) {
			return "";
		}
		int pos = fileName.lastIndexOf('.');
		if (pos >= 0) {
			return fileName.substring(pos + 1);
		}
		return "";
	}

	/**
	 * 获取文件后缀
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileExtention(File file) {
		if (file != null) {
			return getFileExtention(file.getName());
		}
		return "";
	}

	/**
	 * 读取inputStream，将读取的内容按照byte[]返回。自动关闭inputStream。
	 */
	public static byte[] readBytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream bos = null;
		try {
			bos = new ByteArrayOutputStream();
			byte buff[] = new byte[4096];
			int length;
			while ((length = inputStream.read(buff, 0, 4096)) != -1) {
				if (length > 0) {
					bos.write(buff, 0, length);
				}
			}
			return bos.toByteArray();
		} finally {
			CloseUtil.close(bos);
			CloseUtil.close(inputStream);
		}
	}

	public static boolean isExist(String file) {
		ClassLoader cl = PropertyUtil.class.getClassLoader();
		try {
			URL fileUrl = cl.getResource(file);
			if (fileUrl == null) {
				return false;
			}
			String path = URLDecoder.decode(fileUrl.getFile(), "utf-8");
			return new File(path).exists();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static File getFile(String file) {
		ClassLoader cl = PropertyUtil.class.getClassLoader();
		try {
			URL fileUrl = cl.getResource(file);
			if (fileUrl == null) {
				return null;
			}
			String path = URLDecoder.decode(fileUrl.getFile(), "utf-8");
			return new File(path);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将文件输出到客户端，一般用于预览
	 * 
	 * @param file
	 * @param contentType
	 */
	public static void renderMediaToClient(File file, String contentType) {
		OutputStream toClient = null;
		InputStream fis = null;
		byte[] buffer = null;
		HttpServletResponse response = SpringMVCUtil.getResponse();
		try {
			fis = new BufferedInputStream(new FileInputStream(file));
			buffer = new byte[1024];
			response.reset();
			response.setContentLength(Long.valueOf(file.length()).intValue());
			response.setContentType(contentType);
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			toClient = new BufferedOutputStream(response.getOutputStream());
			int bytesRead;
			while (-1 != (bytesRead = fis.read(buffer, 0, buffer.length))) {
				toClient.write(buffer, 0, bytesRead);
				toClient.flush();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (toClient != null) {
				try {
					toClient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void download(File file, String sourceName) {
		OutputStream toClient = null;
		RandomAccessFile raf = null;
		byte[] buffer = null;
		HttpServletResponse response = SpringMVCUtil.getResponse();
		HttpServletRequest request = SpringMVCUtil.getRequest();
		String charsetName = "utf-8";
		if (WebUtil.isIE(request)) {
			charsetName = "gb2312";
		}
		try {
			raf = new RandomAccessFile(file, "r");
			buffer = new byte[1024];
			response.reset();
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(sourceName.getBytes(charsetName), "iso8859-1"));
			// tell the client to allow accept-ranges
			response.addHeader("Accept-Ranges", "bytes");

			Long start = 0L;// 包含
			Long fileLength = file.length();
			Long end = fileLength;// 不包含
			// client requests a file block download start byte
			if (request.getHeader("Range") != null) {
				response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
				String str = request.getHeader("Range").replaceAll("bytes=", "");
				if (str.indexOf("-") != -1) {
					start = Long.parseLong(str.split("-")[0].trim());
					if (str.split("-").length > 1 && StringUtils.isNotBlank(str.split("-")[1])) {
						end = Long.parseLong(str.split("-")[1].trim()) + 1;
					}
				}
			} else {
				response.setStatus(HttpServletResponse.SC_OK);
			}
			if (start > fileLength) {
				start = fileLength;
			}
			if (end > fileLength) {
				end = fileLength;
			}
			// support multi-threaded download
			// respone format:
			// Content-Length:[file size] - [client request start bytes from file block]
			response.setHeader("Content-Length", new Long(end - start).toString());

			if (start != 0) {
				// 断点开始
				// 响应的格式是:
				// Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
				String contentRange = "bytes " + new Long(start).toString() + "-" + new Long(end - 1).toString() + "/" + new Long(fileLength).toString();
				response.setHeader("Content-Range", contentRange);
				raf.seek(start);
			}

			toClient = new BufferedOutputStream(response.getOutputStream());
			int bytesRead;
			if (end - start > buffer.length) {
				int hasRead = 0;
				int needRead = buffer.length;
				while ((bytesRead = raf.read(buffer, 0, needRead)) != -1) {
					toClient.write(buffer, 0, bytesRead);
					hasRead += bytesRead;
					if (end - start == hasRead) {
						break;
					}
					if (hasRead + needRead > end - start) {
						needRead = Long.valueOf(end - start).intValue() - hasRead;
					}
				}
			} else {
				bytesRead = raf.read(buffer, 0, Long.valueOf((end - start)).intValue());
				toClient.write(buffer, 0, bytesRead);
			}
			toClient.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (toClient != null) {
				try {
					toClient.close();
				} catch (IOException e) {
				}
			}
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
				}
			}
		}
	}

}
