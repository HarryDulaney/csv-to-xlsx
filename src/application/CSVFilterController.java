package application;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ooxml.extractor.POIXMLPropertiesTextExtractor;

import application.CSVfilter;
import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TextField;

/**
 * @author HGDULANEYIV
 */
public class CSVFilterController {

	private CSVfilter csVfilter;
	private File csvStarterFile;
	public static int numFields;

	@FXML
	protected TextField textfieldInput;

	@FXML
	protected TextField textfieldOutput;

	@FXML
	protected void handleChooseSaveFolder(ActionEvent event) {
		
		FileChooser fChooser = new FileChooser();
		fChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fChooser.setTitle("Please choose a location to save your completed Excel file");
		fChooser.setInitialFileName("Excel-File.xlsx");
		File toWrite = fChooser.showSaveDialog(new Stage());
		
		if (toWrite != null) {
			textfieldOutput.setText(toWrite.getAbsolutePath());
			csVfilter.setOutputFile(toWrite);
		}

	}

	@FXML
	protected void handlePickSourceFile(ActionEvent event) {

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select the CSV file you want to work with");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".csv", "*.csv"));
		csvStarterFile = chooser.showOpenDialog(new Stage(StageStyle.UTILITY));

		if (csvStarterFile != null) {
			textfieldInput.setText(csvStarterFile.getAbsolutePath());
			csVfilter.setUserFile(csvStarterFile);

		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Something went wrong");
			alert.setContentText("Could not load the csv filepath");
			alert.show();

		}
	}

	@FXML
	protected void handleSubmitAndRun(ActionEvent event) {

		try {
			csVfilter.extractToXLSX();

		} catch (Exception e) {
//			System.out.print("line 73" + e.getMessage());
		}
	}

	public CSVFilterController() {

	}

	public void start() throws Exception {
		CSVfilter csvfilter = new CSVfilter();
		this.csVfilter = csvfilter;

	}

	@FXML
	public void initialize() {

		try {
			start();
		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText("Something went wrong, we could not load the selected file or you didn't select a "
					+ "			file ");
			a.showAndWait();
			e.printStackTrace();

		}

	}

}
