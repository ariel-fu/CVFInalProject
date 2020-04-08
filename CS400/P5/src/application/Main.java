package application;

import java.util.List;

import javax.swing.JComboBox;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application {

  private List<String> args; // arguments

  private static final int WINDOW_WIDTH = 300; // height
  private static final int WINDOW_HEIGHT = 300; // width
  private static final String APP_TITLE = "HELLO CRUEL WORLD"; // title

  /**
   * Helper method that creates and returns a ComboBox
   * 
   * @return a ComboBox
   */
  private ComboBox<String> getComboBox() {

    String[] gradesForAriel = { "A", "A", "A", "C, oh my...", "D, please no :(",
        "A" };
    ComboBox<String> combo = new ComboBox<String>();
    combo.setItems(FXCollections.observableArrayList(gradesForAriel));

    combo.setPromptText(
        "What grade shall the TA give Ariel on this assignment?");
    return combo;
  }

  /**
   * Helper method that creates and returns an ImageView
   * 
   * @return an ImageView
   */
  private ImageView getImage() {
    Image image = new Image("Ariel.jpg");
    ImageView view = new ImageView(image);
    return view;
  }

  /**
   * Helper method that creates and returns a Button
   * 
   * @return a button
   */
  private Button getButton() {
    Button button = new Button();
    button.setText("Exit ?");
    return button;
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // save args example
    args = this.getParameters().getRaw();
    
    Canvas canvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGHT);
    // Create a new BorderPane
    BorderPane template = new BorderPane();

    // Set the main Scene to the template
    Scene main = new Scene(template);
    
    // add a Label in the top panel, that says "CS400 My First JavaFX Program"
    Label title = new Label("         CS400 My First JavaFX Program" + "\r\n");
    template.setTop(title);

    // add a Combo box with three or more option in the left panel

    template.setLeft(this.getComboBox());

    // add an image view of an image in the center panel
    template.setCenter(this.getImage());
//
//    // add a button in the bottom panel with the label "Done"
//    template.setBottom(this.getButton());
    
    // add UI control element in the right panel

    // add and set and display
    primaryStage.setTitle(APP_TITLE);
    primaryStage.setScene(main);
    primaryStage.show();
  }

  /**
   * Runs my launch to start the GUI
   * 
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

}
