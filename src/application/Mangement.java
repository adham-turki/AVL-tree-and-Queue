package application;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Mangement extends Secondery {

	public Mangement() {
		// Clear the existing content
		getChildren().clear();
		// Create a grid pane for the input fields
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setPadding(new Insets(20));

		Button searchButton = createStyledButton("Search");
		DatePicker datePicker = new DatePicker();
		datePicker.setPrefHeight(40);

		// Create a TableView and define columns
		TableView<Days> tableView = new TableView<>();
		tableView.getChildrenUnmodifiable().clear();
        // Define columns for the TableView
		TableColumn<Days, Date> dateColumn = new TableColumn<>("Date");
		TableColumn<Days, Double> Israeli_Lines_MWsColumn = new TableColumn<>("Israeli_Lines_MWs");
		TableColumn<Days, Double> Gaza_Power_Plant_MWsColumn = new TableColumn<>("Gaza_Power_Plant_MWs");
		TableColumn<Days, Double> Egyptian_Lines_MWsColumn = new TableColumn<>("Egyptian_Lines_MWs");
		TableColumn<Days, Double> Total_daily_Supply_available_in_MWsColumn = new TableColumn<>(
				"Total_daily_Supply_available_in_MWs");
		TableColumn<Days, Double> Overall_demand_in_MWsColumn = new TableColumn<>("Date");
		TableColumn<Days, Double> Power_Cuts_hours_day_400mgColumn = new TableColumn<>("Power_Cuts_hours_day_400mg");
		TableColumn<Days, Double> TempColumn = new TableColumn<>("Temp");

		// Set cell value factories
		Israeli_Lines_MWsColumn.setCellValueFactory(new PropertyValueFactory<>("Israeli_Lines_MWs"));
		Gaza_Power_Plant_MWsColumn.setCellValueFactory(new PropertyValueFactory<>("Gaza_Power_Plant_MWs"));
		Egyptian_Lines_MWsColumn.setCellValueFactory(new PropertyValueFactory<>("Egyptian_Lines_MWs"));
		Total_daily_Supply_available_in_MWsColumn
				.setCellValueFactory(new PropertyValueFactory<>("Total_daily_Supply_available_in_MWs"));
		Overall_demand_in_MWsColumn.setCellValueFactory(new PropertyValueFactory<>("Overall_demand_in_MWs"));
		Power_Cuts_hours_day_400mgColumn.setCellValueFactory(new PropertyValueFactory<>("Power_Cuts_hours_day_400mg"));
		TempColumn.setCellValueFactory(new PropertyValueFactory<>("Temp"));

        // Set preferred widths for the columns
		dateColumn.setPrefWidth(260);
		Israeli_Lines_MWsColumn.setPrefWidth(120);
		Gaza_Power_Plant_MWsColumn.setPrefWidth(120);
		Egyptian_Lines_MWsColumn.setPrefWidth(120);
		Total_daily_Supply_available_in_MWsColumn.setPrefWidth(120);
		Overall_demand_in_MWsColumn.setPrefWidth(120);
		Power_Cuts_hours_day_400mgColumn.setPrefWidth(120);
		TempColumn.setPrefWidth(120);

        // Add columns to the TableView
		tableView.getColumns().addAll(dateColumn, Israeli_Lines_MWsColumn, Gaza_Power_Plant_MWsColumn,
				Egyptian_Lines_MWsColumn, Total_daily_Supply_available_in_MWsColumn, Overall_demand_in_MWsColumn,
				Power_Cuts_hours_day_400mgColumn, TempColumn);

		// Add the text field and button to the grid pane
		gridPane.add(datePicker, 0, 0);
		gridPane.add(searchButton, 1, 0);

		ObservableList<Days> daysData = FXCollections.observableArrayList();
		try {
			// add the data from tree into the list
			avl.inOrderTraversalDaysData(daysData);

			// Set the cell value factory for the date column
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

			// Set the cell factory to format the date
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			dateColumn.setCellFactory(column -> new TableCell<Days, Date>() {
				@Override
				protected void updateItem(Date item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
					} else {
						setText(dateFormat.format(item));
					}
				}
			});
			
            // Add a listener to handle item selection in the TableView
			tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
				if (newSelection != null) {
                    // Get the selected Day and open the UpdateScreen
					Days selectedday = tableView.getSelectionModel().getSelectedItem();
					setCenter(new UpdateScreen(selectedday));
				}
			});
            // Add event handler for the search button
			searchButton.setOnAction(e -> {
				daysData.clear();
				LocalDate date = datePicker.getValue();
				Double day = (double) date.getDayOfMonth();
				int month = date.getMonthValue();
				int year = date.getYear();
				Date normalDate = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
                // extract the data for the avl trees
				if (avl.contains(new Years(year))) {
					if (avl.find(new Years(year)).element.getMonthsAVL().contains(new Months(month))) {
						if (avl.find(new Years(year)).element.getMonthsAVL().find(new Months(month)).element
								.getDaysAVL().contains(new Days(day))) {
							Days resultDay = avl.find(new Years(year)).element.getMonthsAVL()
									.find(new Months(month)).element.getDaysAVL().find(new Days(day)).element;
							daysData.add(resultDay);
						}
					}
				}

			});
		} catch (Exception ex) {
			
		}

		// Set the items for the TableView
		tableView.setItems(daysData);

		// Create a title text
		Text titleText = new Text("Search for country");
		titleText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		titleText.setFill(Color.DARKBLUE);

		// Create a vertical box to hold the title, grid pane, and buttons
		VBox contentBox = new VBox(20);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.getChildren().addAll(titleText, gridPane, tableView);

		setCenter(contentBox);
	}

	protected Button createStyledButton(String text) {
		Button button = new Button(text);
		button.setStyle(
				"-fx-background-color: #009688; -fx-text-fill: white; -fx-font-family: 'Arial Black'; -fx-font-size: 18px;");

		// Hover effect to change button color to black
		button.setOnMouseEntered(e -> {
			button.setStyle(
					"-fx-background-color: black; -fx-text-fill: white; -fx-font-family: 'Arial Black'; -fx-font-size: 18px;");
		});

		button.setOnMouseExited(e -> {
			button.setStyle(
					"-fx-background-color: #009688; -fx-text-fill: white; -fx-font-family: 'Arial Black'; -fx-font-size: 18px;");
		});

		return button;
	}
}