import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

/**
 * Created by Klaas on 28-2-2016.
 */
public class FileTester extends Application {

    final CheckBox checkDisplayBinary = new CheckBox("Display Binary");
    @Override
    public void start(final Stage primaryStage) {

        TextArea log = new TextArea();


        final CheckBox checkDisplayBinary = new CheckBox("Display Binary");
        final Label labelSelectedDirectory = new Label();
        final FileChooser fileChooser = new FileChooser();
        final DirectoryChooser directoryChooser = new DirectoryChooser();

        final Button btnOpenDirectoryChooser = new Button("Open a Directory...");
        final Button btnOpenFileChooser = new Button("Open a File...");
        final Button btnSave = new Button("Save...");
        // dir chooser


        btnOpenDirectoryChooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                File selectedDirectory =
                        directoryChooser.showDialog(primaryStage);

                if(selectedDirectory == null){
                    labelSelectedDirectory.setText("No Directory selected");
                }else{
                    labelSelectedDirectory.setText(selectedDirectory.getAbsolutePath());
                }
            }
        });

        btnOpenFileChooser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                boolean displayBinary = false;
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file == null) {
                    labelSelectedDirectory.setText("No File selected");

                } else
                {
                    if(checkDisplayBinary.isSelected())
                        displayBinary = true;

                    labelSelectedDirectory.setText(file.getAbsolutePath());
                    if(!displayBinary) {
                        log.setText(log.getText() + printFile(file));
                    } else {
                        //TODO: Need to fix the binary display
                        log.setText("TODO: we currently cannot display binary files");
                    }

                    // Here we have a file to play with
                    // lets make it into a copy program

                }
            }
        });


        btnSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO: Implement this method!!
                labelSelectedDirectory.setText("TODO!!!");
            }
        });

        final Pane root = new VBox(12);

        final GridPane inputGridPane = new GridPane();
        GridPane.setConstraints(labelSelectedDirectory,0,0);
        GridPane.setColumnSpan(labelSelectedDirectory,2);
        GridPane.setConstraints(btnOpenFileChooser, 0, 1);
        GridPane.setConstraints(btnOpenDirectoryChooser, 1, 1);
        GridPane.setConstraints(checkDisplayBinary,0,2);
        GridPane.setHalignment(checkDisplayBinary, HPos.LEFT);
        inputGridPane.setHgap(6);
        inputGridPane.setVgap(6);
        inputGridPane.getChildren().addAll(btnOpenFileChooser, btnOpenDirectoryChooser,labelSelectedDirectory,checkDisplayBinary);

        //root.getChildren().add(vBox);
        root.getChildren().addAll(inputGridPane,log);
        root.setPadding(new Insets(12,12,12,12));
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("java.io.File Testing");
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

    public static String printFile(File file) {
        String returnString = "";
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null ) {
                returnString += line + "\n";


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return returnString;

    }
}
