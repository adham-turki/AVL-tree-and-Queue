package application;

import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

public class SaveScreen extends Statistics {
	public SaveScreen() {
			getChildren().clear();
			Label messageLabel = new Label();
			messageLabel.setFont(Font.font("Arial", 30));
			Button saveButton = createStyledButton("Save");
			saveButton.setOnAction(e -> {
				messageLabel.setText("Successfuly saved");
				messageLabel.setTextFill(Color.GREEN);
				FileChooser fileChooser = new FileChooser();
	            File fileChosen = fileChooser.showOpenDialog(Main.stage);
	            avl.save(fileChosen);
			});
			
			VBox contentBox = new VBox(10);
			contentBox.setAlignment(Pos.CENTER);
			contentBox.getChildren().addAll(saveButton, messageLabel);
			setCenter(contentBox);
	
		}
	
}
