package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.opencsv.CSVReader;

import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author HGDULANEYIV
 */

public class CSVfilter {

	/** The csv file path. */
	private File csvFilePath;

	/**
	 * Instantiates a new CSvfilter.
	 */
	public CSVfilter() {
		super();

	}

	/**
	 * Instantiates a new CSvfilter.
	 *
	 * @param file the file
	 */
	public CSVfilter(File file) {

		this.csvFilePath = file;

	}

	/**
	 * Extract to XLSX.
	 *
	 * @param headers the headers
	 * @throws IOException            Signals that an I/O exception has occurred.
	 * @throws InvalidFormatException the invalid format exception
	 */
	public void extractToXLSX(List<String> headers) throws IOException, InvalidFormatException {

		TextInputDialog inDialog = new TextInputDialog();
		inDialog.setHeaderText("Information Requested");
		inDialog.setContentText(
				"Input a name for your new spreadsheet. " + "Don't forget to select a .csv or .xlsx output format");
		inDialog.setHeight(150);
		inDialog.setWidth(235);
		inDialog.showAndWait();

		if (inDialog.getResult().isEmpty()) {
			inDialog.setContentText("Sheet name can not be blank. Please input a name for your spreadsheet.");
			inDialog.showAndWait();

		}

		String sheetName = inDialog.getResult();

		Workbook workBook = null;
		CSVReader reader;
		SXSSFSheet sheet;

		try {

			String[] lineVals;
			reader = new CSVReader(new FileReader(csvFilePath));

			workBook = new SXSSFWorkbook();
			sheet = (SXSSFSheet) workBook.createSheet(sheetName);

			int rowNumber = 0;
			System.out.print("Creating New .Xls File From The Already Generated .Csv File");
			while ((lineVals = reader.readNext()) != null) {
				Row currentRow = sheet.createRow(rowNumber++);
				for (int i = 0; i < lineVals.length; i++) {
					currentRow.createCell(i).setCellValue(lineVals[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		FileChooser fChooser = new FileChooser();
		fChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fChooser.setTitle("Please choose a location to save your completed Excel file");
		fChooser.setInitialFileName("Excel-File.xlsx");
		File toWrite = fChooser.showSaveDialog(new Stage());

		if (toWrite != null) {

			try (FileOutputStream fOs = new FileOutputStream(new File(toWrite.getAbsolutePath()))) {
				workBook.write(fOs);
			} catch (Exception e) {
				e.printStackTrace();
			}
			workBook.close();

		}

	}

	/**
	 * Sets the user file.
	 *
	 * @return the file
	 */
	public File setUserFile() {

		return csvFilePath;

	}

}
