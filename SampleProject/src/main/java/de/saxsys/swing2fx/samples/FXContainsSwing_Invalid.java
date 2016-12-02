package de.saxsys.swing2fx.samples;

import javax.swing.JButton;
import javax.swing.SwingUtilities;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FXContainsSwing_Invalid extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button label = new Button("JavaFX");
		SwingNode swing = new SwingNode();
		
		SwingUtilities.invokeLater(() -> {
			JButton jLabel = new JButton("Swing");
			Platform.runLater(() -> {
				swing.setContent(jLabel);
				VBox vBox = new VBox(label, swing);
				Scene scene = new Scene(vBox);
				primaryStage.setScene(scene);
				primaryStage.show();
			});
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}