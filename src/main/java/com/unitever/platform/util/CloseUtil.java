package com.unitever.platform.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * 关闭辅助类
 * 
 * @author Sheny on 2011-10-19
 */
public class CloseUtil {

	/**
	 * 关闭给定的输入流. <BR>
	 * 
	 * @param inputStream
	 */
	public static void close(InputStream inputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭给定的输出流. <BR>
	 * 
	 * @param outputStream
	 */
	public static void close(OutputStream outputStream) {
		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭给定的输出流. <BR>
	 * 
	 * @param writer
	 */
	public static void close(Writer writer) {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭给定的输入流. <BR>
	 * 
	 * @param reader
	 */
	public static void close(Reader reader) {
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				throw ExceptionUtil.convertExceptionToUnchecked(e);
			}
		}
	}

}
