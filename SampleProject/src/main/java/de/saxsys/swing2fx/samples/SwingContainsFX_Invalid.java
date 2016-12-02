package de.saxsys.swing2fx.samples;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class SwingContainsFX_Invalid extends JFrame {
	
	public SwingContainsFX_Invalid() {
		super("Swing Frame");
		setLayout(new GridLayout(0, 1));
		JButton swingButton = new JButton("Swing Button");
		
		// Create JavaFX Container
		JFXPanel jfxPanel = new JFXPanel();
		
		// Switch to JavaFX Thread to create JavaFX content
		Platform.runLater(() -> {
			Button btJavaFX = new Button("JavaFX Button");
			Scene newScene = new Scene(btJavaFX);
			jfxPanel.setScene(newScene); // INVALID
			pack(); // INVALID
			setVisible(true); // INVALID
		});
		
		getContentPane().add(swingButton);
		getContentPane().add(jfxPanel);
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new SwingContainsFX_Invalid());
	}
}