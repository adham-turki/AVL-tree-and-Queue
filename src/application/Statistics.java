package application;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Statistics extends Mangement {
    // Declare variables for statistics calculations
	double min = 1000;
	double max = 0;
	double sum = 0;
	double avg = 0;
	double count = 0;

	// Constructor for the Statistics class
	public Statistics() {
		// Clear the page
		getChildren().clear();

		// ComboBox for selecting the time option (Day, Month, Year, All data)
		ComboBox<String> optioncomboBox = new ComboBox<>();
		// Set options and styles for the ComboBox
		optioncomboBox.setItems(FXCollections.observableArrayList());
		optioncomboBox.setPromptText("Option");
		optioncomboBox.setStyle(
				"-fx-font-family: Arial; -fx-font-size: 14px; -fx-background-color: #D2E4F6; -fx-border-color: #A6A6A6; -fx-border-radius: 5; -fx-padding: 5px;"
						+ "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.3), 2, 0.0, 0, 1);");

		// Add options to the ComboBox
		optioncomboBox.getItems().add("Day");
		optioncomboBox.getItems().add("Month");
		optioncomboBox.getItems().add("Year");
		optioncomboBox.getItems().add("All data");

		// ComboBox for selecting the time option (Day, Month, Year, All data)
		ComboBox<String> ordercomboBox = new ComboBox<>();
		// Set options and styles for the ComboBox
		ordercomboBox.setItems(FXCollections.observableArrayList());
		ordercomboBox.setPromptText("Order");
		ordercomboBox.setStyle(
				"-fx-font-family: Arial; -fx-font-size: 14px; -fx-background-color: #D2E4F6; -fx-border-color: #A6A6A6; -fx-border-radius: 5; -fx-padding: 5px;"
						+ "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.3), 2, 0.0, 0, 1);");

		// Add options to the ComboBox
		ordercomboBox.getItems().add("PreOrder");
		ordercomboBox.getItems().add("InOrder");
		ordercomboBox.getItems().add("PostOrder");
		ordercomboBox.getItems().add("LevelByLevel");

		ComboBox<String> elecComboBox = new ComboBox<>();
		elecComboBox.setItems(FXCollections.observableArrayList());
		elecComboBox.setPromptText("Electricity");
		elecComboBox.setStyle(
				"-fx-font-family: Arial; -fx-font-size: 14px; -fx-background-color: #D2E4F6; -fx-border-color: #A6A6A6; -fx-border-radius: 5; -fx-padding: 5px;"
						+ "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.3), 2, 0.0, 0, 1);");

		// Add options to the ComboBox
		elecComboBox.getItems().add("Israeli_Lines_MWs");
		elecComboBox.getItems().add("Gaza_Power_Plant_MWs");
		elecComboBox.getItems().add("Egyptian_Lines_MWs");
		elecComboBox.getItems().add("Total_daily_Supply_available_in_MWs data");
		elecComboBox.getItems().add("Overall_demand_in_MWs");
		elecComboBox.getItems().add("Power_Cuts_hours_day_400mg");
		elecComboBox.getItems().add("Temp");

		// Create fields and labels
		TextField textField = new TextField();
		Button button = createStyledButton("Show");
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setMinHeight(400);
		Label messageLabel = new Label();
		messageLabel.setFont(Font.font("Arial", 28));

		// Create fields and labels
		TextField minField = new TextField();
		minField.setPrefHeight(40);
		minField.setEditable(false);

		// Create fields and labels
		Label minLabel = new Label("Minimum");
		minLabel.setFont(Font.font("Arial", 24));
		minLabel.setTextFill(Color.DARKBLUE);

		// Create fields and labels
		TextField maxField = new TextField();
		maxField.setPrefHeight(40);
		maxField.setEditable(false);

		// Create fields and labels
		Label maxLabel = new Label("Maximum");
		maxLabel.setFont(Font.font("Arial", 24));
		maxLabel.setTextFill(Color.DARKBLUE);
		// Create fields and labels
		TextField sumField = new TextField();
		sumField.setPrefHeight(40);
		sumField.setEditable(false);
		// Create fields and labels
		Label sumLabel = new Label("Sum");
		sumLabel.setFont(Font.font("Arial", 24));
		sumLabel.setTextFill(Color.DARKBLUE);
		// Create fields and labels
		TextField avgField = new TextField();
		avgField.setPrefHeight(40);
		avgField.setEditable(false);
		// Create fields and labels
		Label avgLabel = new Label("Average");
		avgLabel.setFont(Font.font("Arial", 24));
		avgLabel.setTextFill(Color.DARKBLUE);
		// Create the grid pane
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20));
		//adding to the grid pane
		gridPane.add(elecComboBox, 0, 0);
		gridPane.add(optioncomboBox, 1, 0);
		gridPane.add(ordercomboBox, 2, 0);
		gridPane.add(textField, 3, 0);
		gridPane.add(button, 4, 0);
		//create another grid pane
		GridPane gridPane2 = new GridPane();
		gridPane2.setAlignment(Pos.CENTER);
		gridPane2.setHgap(10);
		gridPane2.setVgap(10);
		gridPane2.setPadding(new Insets(20));
		//adding to the second grid pane
		gridPane2.add(minLabel, 0, 0);
		gridPane2.add(minField, 1, 0);
		gridPane2.add(maxLabel, 0, 1);
		gridPane2.add(maxField, 1, 1);
		gridPane2.add(sumLabel, 0, 2);
		gridPane2.add(sumField, 1, 2);
		gridPane2.add(avgLabel, 0, 3);
		gridPane2.add(avgField, 1, 3);
		//create an hbox 
		HBox hbox = new HBox(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(gridPane2, textArea);
		//creating a title text
		Text titleText = new Text("Statistics");
		titleText.setFont(Font.font("Arial", 38));
		titleText.setFill(Color.DARKBLUE);
		//creating a vbox
		VBox contentBox = new VBox(20);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().addAll(titleText, gridPane, messageLabel, hbox);
		//make the action on the show button
		button.setOnAction(e -> {
			try {
                // Clear previous results
				textArea.clear();
				minField.clear();
				maxField.clear();
				sumField.clear();
				avgField.clear();
                // Perform statistics calculations based on the user input
				if (optioncomboBox.getValue().equals("Day")) {
					min = 1000;
					max = 0;
					sum = 0;
					avg = 0;
					count = 0;
                    // Day-specific calculations
					double day = Double.parseDouble(textField.getText());
					Days dayObj = new Days();
					ArrayList<Days> list = new ArrayList<>();
					//print the tree inorder
					if (ordercomboBox.getValue().equals("InOrder")) {
						avl.inOrderAppendsDaysData(textArea, new Days(day), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);
						}
						//print the tree in preorder
					} else if (ordercomboBox.getValue().equals("PreOrder")) {

						avl.preOrderAppendsDaysData(textArea, new Days(day), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);
						}
					}
					//print the tree in postorder
					else if (ordercomboBox.getValue().equals("PostOrder")) {

						avl.postOrderAppendsDaysData(textArea, new Days(day), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);
						}
						//print the tree in levelorder
					} else {
						avl.levelOrderAppendsDaysData(textArea, new Days(day), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);
						}
					}
                    // Month-specific calculations
				} else if (optioncomboBox.getValue().equals("Month")) {
					min = 1000;
					max = 0;
					sum = 0;
					avg = 0;
					count = 0;
					//extract the data based on the month
					int month = Integer.parseInt(textField.getText());
					ArrayList<Days> list = new ArrayList<>();
					if( avl.find(new Years(2019)).element.getMonthsAVL().find(new Months(month))!=null)
					textArea.appendText("The Height of the chosen month : "
							+ avl.find(new Years(2019)).element.getMonthsAVL().find(new Months(month)).element
									.getDaysAVL().getRoot().height
							+ "\n");
					//print inorder
					if (ordercomboBox.getValue().equals("InOrder")) {
						avl.inOrderAppendsMonthsData(textArea, new Months(month), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}
						//print preorder
					} else if (ordercomboBox.getValue().equals("PreOrder")) {
						avl.preOrderAppendsMonthsData(textArea, new Months(month), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}
						//print postorder
					} else if (ordercomboBox.getValue().equals("PostOrder")) {
						avl.postOrderAppendsMonthsData(textArea, new Months(month), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}
						//print levelorder
					} else {
						avl.levelOrderAppendsMonthsData(textArea, new Months(month), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}

					}
                    // Year-specific calculations
				} else if (optioncomboBox.getValue().equals("Year")) {
					min = 1000;
					max = 0;
					sum = 0;
					avg = 0;
					count = 0;
					//extract the data from aspecific year
					int year = Integer.parseInt(textField.getText());
					ArrayList<Days> list = new ArrayList<>();
					if(avl.find(new Years(year))!=null)
						//append the height
					textArea.appendText("The Height of the chosen year : "
							+ avl.find(new Years(year)).element.getMonthsAVL().getRoot().height + "\n");
					//print inorder
					if (ordercomboBox.getValue().equals("InOrder")) {
						avl.inOrderAppendsYearsData(textArea, new Years(year), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}
						//print preorder
					} else if (ordercomboBox.getValue().equals("PreOrder")) {
						avl.preOrderAppendsYearsData(textArea, new Years(year), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}
						//print postorder
					} else if (ordercomboBox.getValue().equals("PostOrder")) {
						avl.postOrderAppendsYearsData(textArea, new Years(year), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}
						//print levelorder
					} else {
						avl.levelOrderAppendsYearsData(textArea, new Years(year), list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}

					}

				} else {
					min = 1000;
					max = 0;
					sum = 0;
					avg = 0;
					count = 0;
					//extract all the data
					ArrayList<Days> list = new ArrayList<>();
					//print the height
					textArea.appendText("The Height of the chosen year : " + avl.getRoot().height + "\n");
					//print the tree in order
					if (ordercomboBox.getValue().equals("InOrder")) {
						avl.inOrderAppendAllData(textArea, list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}
					}
					//print the tree in preorder
					if (ordercomboBox.getValue().equals("PreOrder")) {
						avl.preOrderAppendsAllData(textArea, list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}
					}
					//print the tree in postorder
					if (ordercomboBox.getValue().equals("PostOrder")) {
						avl.postOrderAppendsAllData(textArea, list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}
						//print the tree in levelorder
					} else {
						avl.levelOrderAppendsAllData(textArea, list);

						String selectedValue = elecComboBox.getValue();
						messageLabel.setText(selectedValue);
						if (selectedValue != null) {
							updateStatistics(list, selectedValue);

						}

					}

				}
                // Update statistics fields with calculated values
				avg = sum / count;
				String formattedAvg = String.format("%.2f", avg);
				minField.setText(min + "");
				maxField.setText(max + "");
				sumField.setText(sum + "");
				avgField.setText(formattedAvg);
			} catch (Exception e2) {
				// TODO: handle exception
			}

		});

		setCenter(contentBox);
	}
    // Method to update statistics based on selected electricity type
	public void updateStatistics(ArrayList<Days> list, String selectedValue) {
        // Iterate through the list and update statistics accordingly
		for (Days dayObj : list) {
			switch (selectedValue) {
			case "Israeli_Lines_MWs":
                // Update statistics for Israeli_Lines_MWs
				if (dayObj.getIsraeli_Lines_MWs() < min)
					min = dayObj.getIsraeli_Lines_MWs();
				if (dayObj.getIsraeli_Lines_MWs() > max)
					max = dayObj.getIsraeli_Lines_MWs();
				sum += dayObj.getIsraeli_Lines_MWs();
				count++;

				break;
			case "Gaza_Power_Plant_MWs":
                // Update statistics for Gaza_Power_Plant_MWs
				if (dayObj.getGaza_Power_Plant_MWs() < min)
					min = dayObj.getGaza_Power_Plant_MWs();
				if (dayObj.getGaza_Power_Plant_MWs() > max)
					max = dayObj.getGaza_Power_Plant_MWs();
				sum += dayObj.getGaza_Power_Plant_MWs();
				count++;
				break;
			case "Egyptian_Lines_MWs":
                // Update statistics for Egyptian_Lines_MWs
				if (dayObj.getEgyptian_Lines_MWs() < min)
					min = dayObj.getEgyptian_Lines_MWs();
				if (dayObj.getEgyptian_Lines_MWs() > max)
					max = dayObj.getEgyptian_Lines_MWs();
				sum += dayObj.getEgyptian_Lines_MWs();
				count++;
				break;
			case "Total_daily_Supply_available_in_MWs data":
                // Update statistics for Total_daily_Supply_available_in_MWs
				if (dayObj.getTotal_daily_Supply_available_in_MWs() < min)
					min = dayObj.getTotal_daily_Supply_available_in_MWs();
				if (dayObj.getTotal_daily_Supply_available_in_MWs() > max)
					max = dayObj.getTotal_daily_Supply_available_in_MWs();
				sum += dayObj.getTotal_daily_Supply_available_in_MWs();
				count++;
				break;
			case "Overall_demand_in_MWs":
                // Update statistics for Overall_demand_in_MWs
				if (dayObj.getOverall_demand_in_MWs() < min)
					min = dayObj.getOverall_demand_in_MWs();
				if (dayObj.getOverall_demand_in_MWs() > max)
					max = dayObj.getOverall_demand_in_MWs();
				sum += dayObj.getOverall_demand_in_MWs();
				count++;
				break;
			case "Power_Cuts_hours_day_400mg":
                // Update statistics for Power_Cuts_hours_day_400mg
				if (dayObj.getPower_Cuts_hours_day_400mg() < min)
					min = dayObj.getPower_Cuts_hours_day_400mg();
				if (dayObj.getPower_Cuts_hours_day_400mg() > max)
					max = dayObj.getPower_Cuts_hours_day_400mg();
				sum += dayObj.getPower_Cuts_hours_day_400mg();
				count++;
				break;
			case "Temp":
                // Update statistics for Temp
				if (dayObj.getTemp() < min)
					min = dayObj.getTemp();
				if (dayObj.getTemp() > max)
					max = dayObj.getTemp();
				sum += dayObj.getTemp();
				count++;
				break;
			default:
				break;
			}
		}
	}

}
