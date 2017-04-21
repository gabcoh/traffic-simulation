
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.geometry.*;
import javafx.stage.*;
import java.io.File;

public class OSMSim extends Application {

    @Override
    public void start(Stage primaryStage) {
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Scene scene = new Scene(tabPane, 300, 275);
        primaryStage.setScene(scene);

        Tab optionsTab = new Tab();
        optionsTab.setText("Options");
        BorderPane optionsPane = new BorderPane();
        GridPane optionsInputPane = new GridPane();
        optionsInputPane.setPadding(new Insets(4, 4, 4, 4));
        optionsInputPane.setHgap(10);
        optionsInputPane.setVgap(10);

        Text optionsText = new Text("Configuration Options");
        //optionsText.setFill(Color.rgb(2, 118, 255));
        BorderPane.setAlignment(optionsText, Pos.CENTER);
        BorderPane.setMargin(optionsText, new Insets(8,0,8,8));
        optionsText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        optionsPane.setTop(optionsText);

        FileChooser osmFileChooser = new FileChooser();
        osmFileChooser.setTitle("OSM XML File");
        osmFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("OSM XML Files", "*.osm"));
        Text osmFileStatus = new Text("Map file unspecified");
        osmFileStatus.setFont(Font.font("Tahoma"));
        optionsInputPane.add(osmFileStatus, 0, 0);
        Button osmFileDialogButton = new Button("Choose File");
        osmFileDialogButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                File selectedFile = osmFileChooser.showOpenDialog(
                        ((Node)e.getTarget()).getScene().getWindow());
                if (selectedFile != null) {
                    osmFileStatus.setText("File Selected");
                }
            }
        });

        HBox osmFileDialogButtonHBox = new HBox(10);
        osmFileDialogButtonHBox.setAlignment(Pos.CENTER_RIGHT);
        osmFileDialogButtonHBox.getChildren().add(osmFileDialogButton);
        optionsInputPane.add(osmFileDialogButtonHBox, 1, 0);

        optionsInputPane.setGridLinesVisible(true);
        optionsPane.setCenter(optionsInputPane);
        optionsTab.setContent(optionsPane);

        Tab mapTab = new Tab();
        mapTab.setText("Map");

        tabPane.getTabs().addAll(optionsTab, mapTab);

        primaryStage.setTitle("OSM Traffic Simulator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
