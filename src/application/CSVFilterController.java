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
	private static File csvStarterFile;
//	private Main m;
	private static List<String> textFields = new ArrayList<>();
	public static int numFields;

	@FXML
	protected TextField textfield1;

	@FXML
	protected TextField textfield2;

	@FXML
	protected TextField textfield3;

	@FXML
	protected TextField textfield4;

	@FXML
	protected TextField textfield5;

	@FXML
	public void handleSubmitCSVfilter(ActionEvent event) {

		if (textfield1.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Something went wrong");
			alert.setContentText("The only field you cannot leave blank is header one.");
			alert.show();

		} else {
			if (!textfield1.getText().isEmpty()) {

				textFields.add(textfield1.getText());
			}
			if (!textfield2.getText().isEmpty()) {
				textFields.add(textfield2.getText());
			}
			if (!textfield3.getText().isEmpty()) {
				textFields.add(textfield3.getText());
			}
			if (!textfield4.getText().isEmpty()) {
				textFields.add(textfield4.getText());
			}
			if (!textfield5.getText().isEmpty()) {
				textFields.add(textfield5.getText());
			}

			try {

				csVfilter.extractToXLSX(textFields);

			} catch (Exception e) {
				System.out.print("line 73" + e.getMessage());
			}
		}
	}

	public CSVFilterController() {

	}

	public void start() throws Exception {

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Select the CSV file you want to work with");
		chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(".csv", "*.csv"));
		csvStarterFile = chooser.showOpenDialog(new Stage(StageStyle.UTILITY));

		if (csvStarterFile != null) {

			CSVfilter csvfilter = new CSVfilter(csvStarterFile);
			this.csVfilter = csvfilter;

		}

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
