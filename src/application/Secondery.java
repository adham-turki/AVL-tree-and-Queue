package application;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class Secondery extends BorderPane {

    static File file;
    static YearsAVL avl ;

    public Secondery() {
        // Create a menu bar
        final MenuBar menuBar = new MenuBar();
        Menu managementMenu;
        Menu statisticsMenu;
        Menu saveMenu;
        Menu importMenu;  // Changed from "exportMenu" to "importMenu"
        MenuItem insertItem;
        MenuItem showItem;
        MenuItem statisticsItem;
        MenuItem saveItem;
        MenuItem importItem; 

        setBackground(getBackground1());
        // Create menus
        managementMenu = new Menu("Management");
        statisticsMenu = new Menu("Statistics");
        saveMenu = new Menu("Save");
        importMenu = new Menu("Import");  

        // Create menu items
        insertItem = new MenuItem("Insert 🌍");
        showItem = new MenuItem("Show and Update 🌍");
        // Add menu items to the menu
        managementMenu.getItems().addAll(insertItem, showItem);

        // Create menu items
        statisticsItem = new MenuItem("Statistics 📊");
        // Add menu items to the menu
        statisticsMenu.getItems().addAll(statisticsItem);

        // Create menu items
        saveItem = new MenuItem("Save 🔒");
        // Add menu items to the menu
        saveMenu.getItems().addAll(saveItem);

        importItem = new MenuItem("Import 💾");  
        importMenu.getItems().addAll(importItem);

        //action on items
        insertItem.setOnAction(e -> setCenter(new InsertScreen()));
        showItem.setOnAction(e -> setCenter(new Mangement()));
        statisticsItem.setOnAction(e -> setCenter(new Statistics()));
        saveItem.setOnAction(e -> setCenter(new SaveScreen()));
        importItem.setOnAction(e -> setCenter(new ImportScreen()));  

        // Add the menu to the menu bar
        menuBar.getMenus().addAll(managementMenu, statisticsMenu, saveMenu, importMenu);  // Changed from "exportMenu" to "importMenu"

        // Set the menu bar to the top of the BorderPane
        setTop(menuBar);

        // Create a vertical box to hold the main content
        VBox contentBox = new VBox(20);
        contentBox.setAlignment(Pos.CENTER);
        
        Label messageLabel = new Label();
		messageLabel.setFont(Font.font("Arial", 28));

        // Add a title text
        Text titleText = new Text("Import a File");  
        titleText.setFont(Font.font("Arial", 48));
        titleText.setFill(Color.DARKBLUE);

        // Add buttons with symbols
        Button importButton = createStyledButton("Import", "💾");  

        importButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            file = fileChooser.showOpenDialog(Main.stage);
            try {
            	messageLabel.setText("The file has been successfully imported");
				messageLabel.setTextFill(Color.GREEN);
                avl = new YearsAVL(file);
            } catch (Exception ex) {
            	messageLabel.setText("Please choose file");
				messageLabel.setTextFill(Color.RED);
                
            }
        });

        // Add the buttons to the content box
        contentBox.getChildren().addAll(titleText, importButton,messageLabel);  

        // Set the content box to the center of the BorderPane
        setCenter(contentBox);
    }

    protected Button createStyledButton(String text, String symbol) {
        Button button = new Button(text);
        button.setStyle(
                "-fx-background-color: #009688; -fx-text-fill: white; -fx-font-family: 'Arial Black'; -fx-font-size: 18px;");
        Text symbolText = new Text(symbol);
        symbolText.setFill(Color.web("#FFD700")); // Set the symbol color
        symbolText.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 24px;");
        button.setGraphic(symbolText);

        // Hover effect to change button color to black
        button.setOnMouseEntered(e -> {
            button.setStyle(
                    "-fx-background-color: black; -fx-text-fill: white; -fx-font-family: 'Arial Black'; -fx-font-size: 18px;");
            symbolText.setFill(Color.WHITE);
        });
        button.setOnMouseExited(e -> {
            button.setStyle(
                    "-fx-background-color: #009688; -fx-text-fill: white; -fx-font-family: 'Arial Black'; -fx-font-size: 18px;");
            symbolText.setFill(Color.web("#FFD700")); // Restore the symbol color
        });

        return button;
    }

    // Method to create the background with a color
    public Background getBackground1() {
        BackgroundFill backgroundFill = new BackgroundFill(Color.DARKSLATEBLUE, CornerRadii.EMPTY, Insets.EMPTY);
        return new Background(backgroundFill);
    }
}
