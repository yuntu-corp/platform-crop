package com.unitever.platform.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	/**
	 * 读取Excel内容，只读取第一个sheet
	 * 
	 * @param fileName
	 *            文件名称（包含文件路径）
	 * @return 读取结果，可能包含null值
	 */
	public static List<String[]> readExcel(String fileName) {
		return readExcel(new File(fileName));
	}

	/**
	 * 读取Excel内容，只读取第一个sheet
	 * 
	 * @param file
	 *            要读取的文件
	 * @return 读取结果，可能包含null值
	 */
	public static List<String[]> readExcel(File file) {
		List<String[]> result = new ArrayList<String[]>();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(fileInputStream);// 通用
			Sheet sheet = wb.getSheetAt(0);
			for (Row row : sheet) {
				String[] rowStrs = new String[row.getLastCellNum()];
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_BLANK:
						rowStrs[cell.getColumnIndex()] = "";
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						rowStrs[cell.getColumnIndex()] = String.valueOf(cell.getBooleanCellValue());
						break;
					case Cell.CELL_TYPE_ERROR:
						rowStrs[cell.getColumnIndex()] = String.valueOf(cell.getErrorCellValue());
						break;
					case Cell.CELL_TYPE_NUMERIC:
						rowStrs[cell.getColumnIndex()] = String.valueOf(cell.getNumericCellValue());
						break;
					case Cell.CELL_TYPE_STRING:
						rowStrs[cell.getColumnIndex()] = cell.getStringCellValue().replaceAll("\t|\r|\n", "").trim();
						break;
					case Cell.CELL_TYPE_FORMULA:
						rowStrs[cell.getColumnIndex()] = String.valueOf(cell.getNumericCellValue());
						break;
					default:
						rowStrs[cell.getColumnIndex()] = "";
						break;
					}
				}
				result.add(rowStrs);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	/**
	 * 以文本格式读取Excel内容，只读取第一个sheet
	 * 
	 * @param file
	 *            要读取的文件
	 * @return 读取结果，可能包含null值
	 */
	public static List<String[]> readExcelWithText(File file) {
		List<String[]> result = new ArrayList<String[]>();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			Sheet sheet = WorkbookFactory.create(fileInputStream).getSheetAt(0);
			for (Row row : sheet) {
				String[] rowStrs = new String[row.getLastCellNum()];
				for (Cell cell : row) {
					cell.setCellType(Cell.CELL_TYPE_STRING);
					rowStrs[cell.getColumnIndex()] = cell.getStringCellValue();
				}
				result.add(rowStrs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	public static List<String> readExcelSheetNames(File file) {
		List<String> result = new ArrayList<String>();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(fileInputStream);// 通用
			int num = wb.getNumberOfSheets();
			for (int i = 0; i < num; i++) {
				String sheetName = wb.getSheetName(i);
				result.add(sheetName);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	/**
	 * 读取excel，指定sheet页内容
	 * 
	 * @param file
	 * @param sheetNum
	 *            从0开始
	 * @return
	 */
	public static List<String[]> readExcel(File file, int sheetNum) {
		List<String[]> result = new ArrayList<String[]>();
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			Workbook wb = WorkbookFactory.create(fileInputStream);// 通用
			int sheetCount = wb.getNumberOfSheets();
			if (sheetNum < sheetCount) {
				Sheet sheet = wb.getSheetAt(sheetNum);
				for (Row row : sheet) {
					String[] rowStrs = new String[row.getLastCellNum()];
					for (Cell cell : row) {
						switch (cell.getCellType()) {
						case Cell.CELL_TYPE_BLANK:
							rowStrs[cell.getColumnIndex()] = "";
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							rowStrs[cell.getColumnIndex()] = String.valueOf(cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_ERROR:
							rowStrs[cell.getColumnIndex()] = String.valueOf(cell.getErrorCellValue());
							break;
						case Cell.CELL_TYPE_NUMERIC:
							rowStrs[cell.getColumnIndex()] = String.valueOf(cell.getNumericCellValue());
							break;
						case Cell.CELL_TYPE_STRING:
							rowStrs[cell.getColumnIndex()] = cell.getStringCellValue().replaceAll("\t|\r|\n", "").trim();
							break;
						case Cell.CELL_TYPE_FORMULA:
							rowStrs[cell.getColumnIndex()] = String.valueOf(cell.getNumericCellValue());
							break;
						default:
							rowStrs[cell.getColumnIndex()] = "";
							break;
						}
					}
					result.add(rowStrs);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	public static void exportExcel(List<ExcelSheetVO> excelSheetVOs, OutputStream out) throws IOException {
		Workbook wb = new HSSFWorkbook();
		CellStyle cs = wb.createCellStyle();
		cs.setDataFormat(wb.createDataFormat().getFormat("@"));
		for (ExcelSheetVO vo : excelSheetVOs) {
			Sheet sheet = wb.createSheet(vo.getName());
			int rownum = 0;
			for (List<String> datas : vo.getDatas()) {
				Row row = sheet.createRow(rownum);
				rownum++;
				int cellNum = 0;
				for (String str : datas) {
					Cell cell = row.createCell(cellNum);
					cell.setCellStyle(cs);
					cell.setCellValue(str);
					cellNum++;
				}
			}
		}
		wb.write(out);
	}

}
