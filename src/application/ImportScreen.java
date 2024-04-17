package application;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class ImportScreen extends Secondery {
	public ImportScreen() {
		getChildren().clear();

		// Create a vertical box to hold the main content
		VBox contentBox = new VBox(20);
		contentBox.setAlignment(Pos.CENTER);

		// Add a title text
		Text titleText = new Text("Import a File");
		titleText.setFont(Font.font("Arial", 48));
		titleText.setFill(Color.DARKBLUE);

		// Add buttons with symbols
		Button exportButton = createStyledButton("Import", "💾");

		exportButton.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			file = fileChooser.showOpenDialog(Main.stage);
            avl = new YearsAVL(file);

		});

		// Add the buttons to the content box
		contentBox.getChildren().addAll(titleText, exportButton);

		// Set the content box to the center of the BorderPane
		setCenter(contentBox);
	}

}
