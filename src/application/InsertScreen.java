package application;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class InsertScreen extends Mangement {
	public InsertScreen() {
		// Clear the existing content
		getChildren().clear();
		// Create a grid pane for the input fields
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20));
		Label IsraelLabel = new Label("Israeli Lines MWs");
		IsraelLabel.setFont(Font.font("Arial", 24));
		IsraelLabel.setTextFill(Color.DARKBLUE);

		// Create Fields and labels
		TextField IsraelField = new TextField();
		IsraelField.setPrefHeight(40);

		// Create Fields and labels
		Label GazaLabel = new Label("Gaza_Power Plant MWs");
		GazaLabel.setFont(Font.font("Arial", 24));
		GazaLabel.setTextFill(Color.DARKBLUE);

		// Create Fields and labels
		TextField GazaField = new TextField();
		GazaField.setPrefHeight(40);

		// Create Fields and labels
		Label EgyptianLabel = new Label("Egyptian Lines MWs");
		EgyptianLabel.setFont(Font.font("Arial", 24));
		EgyptianLabel.setTextFill(Color.DARKBLUE);

		// Create Fields and labels
		TextField EgyptianField = new TextField();
		EgyptianField.setPrefHeight(40);

		// Create Fields and labels
		Label Total_daily_SupplyLabel = new Label("Total daily Supply available in MWs");
		Total_daily_SupplyLabel.setFont(Font.font("Arial", 24));
		Total_daily_SupplyLabel.setTextFill(Color.DARKBLUE);

		// Create Fields and labels
		TextField Total_daily_SupplyField = new TextField();
		Total_daily_SupplyField.setPrefHeight(40);

		// Create Fields and labels
		Label Overall_demandLabel = new Label("Overall demand in MWs");
		Overall_demandLabel.setFont(Font.font("Arial", 24));
		Overall_demandLabel.setTextFill(Color.DARKBLUE);

		// Create Fields and labels
		TextField Overall_demandField = new TextField();
		Overall_demandField.setPrefHeight(40);

		// Create Fields and labels
		Label Power_CutsLabel = new Label("Power Cuts hours day 400mg");
		Power_CutsLabel.setFont(Font.font("Arial", 24));
		Power_CutsLabel.setTextFill(Color.DARKBLUE);

		// Create Fields and labels
		TextField Power_CutsField = new TextField();
		Power_CutsField.setPrefHeight(40);

		// Create Fields and labels
		Label TempLabel = new Label("Temp");
		TempLabel.setFont(Font.font("Arial", 24));
		TempLabel.setTextFill(Color.DARKBLUE);

		// Create Fields and labels
		TextField TempField = new TextField();
		TempField.setPrefHeight(40);

		// Create Fields and labels
		Label dateLabel = new Label("date");
		dateLabel.setFont(Font.font("Arial", 24));
		dateLabel.setTextFill(Color.DARKBLUE);

		// Create Fields and labels
		DatePicker datePicker = new DatePicker();
		datePicker.setPrefHeight(40);

		// Create Fields and labels
		Label messageLabel = new Label();
		messageLabel.setFont(Font.font("Arial", 28));

		// Add the labels and text fields to the grid pane
		gridPane.add(dateLabel, 0, 0);
		gridPane.add(datePicker, 1, 0);
		gridPane.add(IsraelLabel, 0, 1);
		gridPane.add(IsraelField, 1, 1);
		gridPane.add(GazaLabel, 0, 2);
		gridPane.add(GazaField, 1, 2);
		gridPane.add(EgyptianLabel, 0, 3);
		gridPane.add(EgyptianField, 1, 3);
		gridPane.add(Total_daily_SupplyLabel, 0, 4);
		gridPane.add(Total_daily_SupplyField, 1, 4);
		gridPane.add(Overall_demandLabel, 0, 5);
		gridPane.add(Overall_demandField, 1, 5);
		gridPane.add(Power_CutsLabel, 0, 6);
		gridPane.add(Power_CutsField, 1, 6);
		gridPane.add(TempLabel, 0, 7);
		gridPane.add(TempField, 1, 7);

		// Create a back button
		Button backButton = createStyledButton("Back");
		backButton.setOnAction(e -> {
			setCenter(new Mangement());
		});

		Button InsertButton = createStyledButton("Insert");
		InsertButton.setOnAction(e -> {
			try {
				// extract the date
				Date currentDate = new Date();
				Date userDate = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				// check the cases
				if (currentDate.compareTo(userDate) > 0) {
					try {

						if (datePicker.getValue() == null || IsraelField.getText().trim() == ""
								|| GazaField.getText().trim() == "" || EgyptianField.getText().trim() == ""
								|| Total_daily_SupplyField.getText().trim() == ""
								|| Overall_demandField.getText().trim() == "" || Power_CutsField.getText().trim() == ""
								|| TempField.getText().trim() == "") {
							messageLabel.setText("Please fill all the fields");
							messageLabel.setTextFill(Color.RED);

						}
						// check the cases
						else if (Double.parseDouble(IsraelField.getText()) < 0
								|| Double.parseDouble(GazaField.getText()) < 0
								|| Double.parseDouble(EgyptianField.getText()) < 0
								|| Double.parseDouble(Total_daily_SupplyField.getText()) < 0
								|| Double.parseDouble(Overall_demandField.getText()) < 0
								|| Double.parseDouble(Power_CutsField.getText()) < 0
								|| Double.parseDouble(TempField.getText()) < 0) {
							messageLabel.setText("Unvalid number in the fields");
							messageLabel.setTextFill(Color.RED);

						}
						// check the cases
						else {
							//ectract the day and the year and the month
							LocalDate date = datePicker.getValue();
							Double day = (double) date.getDayOfMonth();
							int month = date.getMonthValue();
							int year = date.getYear();
							Date normalDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
							//make a day object to check
							Days dayObject = new Days(day, Double.parseDouble(IsraelField.getText()),
									Double.parseDouble(GazaField.getText()),
									Double.parseDouble(EgyptianField.getText()),
									Double.parseDouble(Total_daily_SupplyField.getText()),
									Double.parseDouble(Overall_demandField.getText()),
									Double.parseDouble(Power_CutsField.getText()),
									Double.parseDouble(TempField.getText()), normalDate);
							boolean validDate = true;
							//check if the object is already exist
							if (avl.contains(new Years(year))) {
								if (avl.find(new Years(year)).element.getMonthsAVL().contains(new Months(month))) {
									if (avl.find(new Years(year)).element.getMonthsAVL().find(new Months(month)).element
											.getDaysAVL().contains(dayObject)) {
										messageLabel.setText("this date is already exist");
										messageLabel.setTextFill(Color.RED);
										validDate = false;
									}
								}
							}
							//if not exist it will add it to the tree
							if (validDate) {
								avl.adding(dayObject, year, month, year);
								messageLabel.setText("The record has been added successfuly");
								messageLabel.setTextFill(Color.GREEN);
							}

						}
					} catch (NumberFormatException ex) {
						// Handle the case where one or more fields are not valid doubles
						messageLabel.setText("Invalid number format in one or more fields");
						messageLabel.setTextFill(Color.RED);
					}

				} else {
					messageLabel.setText("this date is not valid");
					messageLabel.setTextFill(Color.RED);
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		});

		// Create an HBox to hold the buttons
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(InsertButton, backButton);

		// Create a title text
		Text titleText = new Text("Insert new Record");
		titleText.setFont(Font.font("Arial", 38));
		titleText.setFill(Color.DARKBLUE);

		// Create a vertical box to hold the title, grid pane, and buttons
		VBox contentBox = new VBox(20);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().addAll(titleText, gridPane, buttonBox, messageLabel);

		setCenter(contentBox);

	}
}
