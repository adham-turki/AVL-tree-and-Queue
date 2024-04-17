package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UpdateScreen extends InsertScreen {
	public UpdateScreen(Days day) {
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

		TextField IsraelField = new TextField();
		IsraelField.setPrefHeight(40);

		Label GazaLabel = new Label("Gaza_Power Plant MWs");
		GazaLabel.setFont(Font.font("Arial", 24));
		GazaLabel.setTextFill(Color.DARKBLUE);

		TextField GazaField = new TextField();
		GazaField.setPrefHeight(40);

		Label EgyptianLabel = new Label("Egyptian Lines MWs");
		EgyptianLabel.setFont(Font.font("Arial", 24));
		EgyptianLabel.setTextFill(Color.DARKBLUE);

		TextField EgyptianField = new TextField();
		EgyptianField.setPrefHeight(40);

		Label Total_daily_SupplyLabel = new Label("Total daily Supply available in MWs");
		Total_daily_SupplyLabel.setFont(Font.font("Arial", 24));
		Total_daily_SupplyLabel.setTextFill(Color.DARKBLUE);

		TextField Total_daily_SupplyField = new TextField();
		Total_daily_SupplyField.setPrefHeight(40);

		Label Overall_demandLabel = new Label("Overall demand in MWs");
		Overall_demandLabel.setFont(Font.font("Arial", 24));
		Overall_demandLabel.setTextFill(Color.DARKBLUE);

		TextField Overall_demandField = new TextField();
		Overall_demandField.setPrefHeight(40);

		Label Power_CutsLabel = new Label("Power Cuts hours day 400mg");
		Power_CutsLabel.setFont(Font.font("Arial", 24));
		Power_CutsLabel.setTextFill(Color.DARKBLUE);

		TextField Power_CutsField = new TextField();
		Power_CutsField.setPrefHeight(40);

		Label TempLabel = new Label("Temp");
		TempLabel.setFont(Font.font("Arial", 24));
		TempLabel.setTextFill(Color.DARKBLUE);

		TextField TempField = new TextField();
		TempField.setPrefHeight(40);

		Label dateLabel = new Label("date");
		dateLabel.setFont(Font.font("Arial", 24));
		dateLabel.setTextFill(Color.DARKBLUE);

		DatePicker datePicker = new DatePicker();
		datePicker.setPrefHeight(40);

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

		Date date = day.getDate();
		// Convert the Date to LocalDate
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		datePicker.setValue(localDate);
		datePicker.setEditable(false);
		IsraelField.setText(day.getIsraeli_Lines_MWs() + "");
		GazaField.setText(day.getGaza_Power_Plant_MWs() + "");
		EgyptianField.setText(day.getEgyptian_Lines_MWs() + "");
		Total_daily_SupplyField.setText(day.getTotal_daily_Supply_available_in_MWs() + "");
		Overall_demandField.setText(day.getOverall_demand_in_MWs() + "");
		Power_CutsField.setText(day.getPower_Cuts_hours_day_400mg() + "");
		TempField.setText(day.getTemp() + "");

		// Create a back button
		Button backButton = createStyledButton("Back");
		backButton.setOnAction(e -> {
			setCenter(new Mangement());
		});

		Button updateButton = createStyledButton("Update");
		updateButton.setOnAction(e -> {
			try {
				// check the cases
				if (datePicker.getValue() == null || IsraelField.getText().trim() == ""
						|| GazaField.getText().trim() == "" || EgyptianField.getText().trim() == ""
						|| Total_daily_SupplyField.getText().trim() == "" || Overall_demandField.getText().trim() == ""
						|| Power_CutsField.getText().trim() == "" || TempField.getText().trim() == "") {
					messageLabel.setText("Please fill all the fields");
					messageLabel.setTextFill(Color.RED);

				}
				// check the cases
				else if (Double.parseDouble(IsraelField.getText()) < 0 || Double.parseDouble(GazaField.getText()) < 0
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
					// extract the day and month and year
					SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
					SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
					SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
					int dayNumber = Integer.parseInt(dayFormat.format(date));
					int monthNumber = Integer.parseInt(monthFormat.format(date));
					int yearNumber = Integer.parseInt(yearFormat.format(date));
					// make the day object to check if it is not exist
					Days dayObject = new Days(dayNumber, Double.parseDouble(IsraelField.getText()),
							Double.parseDouble(GazaField.getText()), Double.parseDouble(EgyptianField.getText()),
							Double.parseDouble(Total_daily_SupplyField.getText()),
							Double.parseDouble(Overall_demandField.getText()),
							Double.parseDouble(Power_CutsField.getText()), Double.parseDouble(TempField.getText()),
							date);
					// check if the day object is not exist
					if (avl.contains(new Years(yearNumber))) {
						if (avl.find(new Years(yearNumber)).element.getMonthsAVL().contains(new Months(monthNumber))) {
							if (avl.find(new Years(yearNumber)).element.getMonthsAVL()
									.find(new Months(monthNumber)).element.getDaysAVL().contains(dayObject)) {
								// update the object after find it
								Days updateDay = avl.find(new Years(yearNumber)).element.getMonthsAVL()
										.find(new Months(monthNumber)).element.getDaysAVL().find(dayObject).element;
								updateDay.setEgyptian_Lines_MWs(Double.parseDouble(EgyptianField.getText()));
								updateDay.setGaza_Power_Plant_MWs(Double.parseDouble(GazaField.getText()));
								updateDay.setIsraeli_Lines_MWs(Double.parseDouble(IsraelField.getText()));
								updateDay.setOverall_demand_in_MWs(Double.parseDouble(Overall_demandField.getText()));
								updateDay.setPower_Cuts_hours_day_400mg(Double.parseDouble(Power_CutsField.getText()));
								updateDay.setTotal_daily_Supply_available_in_MWs(
										Double.parseDouble(Total_daily_SupplyField.getText()));
								updateDay.setTemp(Double.parseDouble(TempField.getText()));
								messageLabel.setText("this record has been updated");
								messageLabel.setTextFill(Color.GREEN);
							}
						}
					}

				}
			} catch (NumberFormatException ex) {
				// Handle the case where one or more fields are not valid doubles
				messageLabel.setText("Invalid number format in one or more fields");
				messageLabel.setTextFill(Color.RED);
			}

		});

		Button deleteButton = createStyledButton("Delete");
		deleteButton.setOnAction(e -> {
			// extract the day and month and year
			SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
			SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
			SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
			int dayNumber = Integer.parseInt(dayFormat.format(date));
			int monthNumber = Integer.parseInt(monthFormat.format(date));
			int yearNumber = Integer.parseInt(yearFormat.format(date));
			// make the day object to check if it is not exist
			Days dayObject = new Days(dayNumber, Double.parseDouble(IsraelField.getText()),
					Double.parseDouble(GazaField.getText()), Double.parseDouble(EgyptianField.getText()),
					Double.parseDouble(Total_daily_SupplyField.getText()),
					Double.parseDouble(Overall_demandField.getText()), Double.parseDouble(Power_CutsField.getText()),
					Double.parseDouble(TempField.getText()), date);
			// find the object in the tree and delete it
			if (avl.contains(new Years(yearNumber))) {
				if (avl.find(new Years(yearNumber)).element.getMonthsAVL().contains(new Months(monthNumber))) {
					if (avl.find(new Years(yearNumber)).element.getMonthsAVL().find(new Months(monthNumber)).element
							.getDaysAVL().contains(dayObject)) {
						avl.find(new Years(yearNumber)).element.getMonthsAVL().find(new Months(monthNumber)).element
								.getDaysAVL().remove(dayObject);
						messageLabel.setText("this record has been updated");
						messageLabel.setTextFill(Color.GREEN);
					}
				}
			}

		});

		// Create an HBox to hold the buttons
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(updateButton, deleteButton, backButton);

		// Create a title text
		Text titleText = new Text("Update Record");
		titleText.setFont(Font.font("Arial", 38));
		titleText.setFill(Color.DARKBLUE);

		// Create a vertical box to hold the title, grid pane, and buttons
		VBox contentBox = new VBox(20);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().addAll(titleText, gridPane, buttonBox, messageLabel);

		setCenter(contentBox);
	}

}
