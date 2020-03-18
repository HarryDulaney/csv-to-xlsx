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

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author HGDULANEYIV
 */

public class CSVfilter {

	/** The csv file path. */
	private File csvFilePath;

	private File outFile;

	/**
	 * Instantiates a new CSvfilter.
	 */
	public CSVfilter() {
		super();

	}

	/**
	 * Extract to XLSX.
	 *
	 * @param headers the headers
	 * @throws IOException            Signals that an I/O exception has occurred.
	 * @throws InvalidFormatException the invalid format exception
	 */
	public void extractToXLSX() throws IOException, InvalidFormatException {

		Workbook workBook = null;
		CSVReader reader;
		SXSSFSheet sheet;

		try {

			String[] lineVals;
			reader = new CSVReader(new FileReader(csvFilePath));

			workBook = new SXSSFWorkbook();
			sheet = (SXSSFSheet) workBook.createSheet("NewSheet1");

			int rowNumber = 0;
			while ((lineVals = reader.readNext()) != null) {
				Row currentRow = sheet.createRow(rowNumber++);
				for (int i = 0; i < lineVals.length; i++) {
					currentRow.createCell(i).setCellValue(lineVals[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (outFile != null) {

			try (FileOutputStream fOs = new FileOutputStream(new File(outFile.getAbsolutePath()))) {
				workBook.write(fOs);
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setContentText("Success! Your .csv file has been converted to .xlsx Excel format");
				alert.showAndWait();
				
				Platform.exit();
				
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
	public void setUserFile(File csvFilePath) {

		this.csvFilePath = csvFilePath;

	}

	public void setOutputFile(File outFile) {

		this.outFile = outFile;

	}

}
