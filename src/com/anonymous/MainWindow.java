package com.anonymous;

import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Created by Adam on 10.04.2018.
 */
public class MainWindow extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Qulios!");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 5, 5, 5));

        MenuBar menuBar = new MenuBar();

        Menu menuFile = new Menu("File");
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        menuFile.getItems().addAll(exit);

        Menu menuSettings = new Menu("Settings");
        MenuItem configureEmail = new MenuItem("Configure email");
        configureEmail.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                System.exit(0);
            }
        });
        menuSettings.getItems().addAll(configureEmail);
        Menu menuOther = new Menu("Other");
        menuBar.getMenus().addAll(menuFile, menuSettings, menuOther);

        Label dirConf = new Label("Directory configuration:");
        dirConf.setFont(Font.font("default", FontWeight.BOLD, 12));
        grid.add(dirConf, 0, 0, 2,1);

        Label pathLabel = new Label("Path:");
        grid.add(pathLabel, 0, 1);

        TextField pathTextField = new TextField();
        grid.add(pathTextField, 1, 1);

        Button browseBtn = new Button("browse");
        browseBtn.setMinSize(60, 20);
        browseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                Label browseText = new Label("Pay $5 to unlock this functionality!\n Contact to sdcapros@gmail.com");

                StackPane browseLayout = new StackPane();
                browseLayout.getChildren().add(browseText);

                Scene browseScene = new Scene(browseLayout, 200, 100);

                Stage browseWindow = new Stage();
                browseWindow.setTitle("My New Stage Title");
                browseWindow.setScene(browseScene);
                browseWindow.setX(primaryStage.getX() + 150);
                browseWindow.setY(primaryStage.getY() + 100);
                browseWindow.initModality(Modality.WINDOW_MODAL);
                browseWindow.initOwner(primaryStage);
                browseWindow.show();
            }
        });
        grid.add(browseBtn, 2, 1);

        Label emailConf = new Label("Gmail configuration:");
        emailConf.setFont(Font.font("default", FontWeight.BOLD, 12));
        grid.add(emailConf, 0, 3, 2,1);

        Label emailAddrLabel = new Label("Gmail:");
        grid.add(emailAddrLabel, 0, 4);

        TextField emailAddrTextField = new TextField();
        grid.add(emailAddrTextField, 1, 4);

        Label emailPassLabel = new Label("Password:");
        grid.add(emailPassLabel, 0, 5);

        PasswordField emailPassTextField = new PasswordField();
        grid.add(emailPassTextField, 1, 5);

        Button testEmailBtn = new Button("test");
        testEmailBtn.setMinSize(60, 20);
        grid.add(testEmailBtn, 2, 5);

        Button runBtn = new Button("Run");
        runBtn.setMinSize(100, 20);
        runBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                /*MailSender mailSender = new MailSender();
                mailSender.init(emailAddrTextField.getText(), emailPassTextField.getText());
                mailSender.sendMessage("harnas15@poczta.onet.pl", "Hello Adam", "blablabla\nblablabla");*/

                DirWatcher dirWatcher = new DirWatcher();
                dirWatcher.start(pathTextField.getText());
            }
        });

        grid.add(runBtn, 10, 10);

        //Scene scene = new Scene(new VBox(), 500, 400);
        //((VBox) scene.getRoot()).getChildren().addAll(menuBar);
        Scene scene = new Scene(grid, 500, 300);
        //((GridPane) scene.getRoot()).getChildren().addAll(menuBar);
        primaryStage.setScene(scene);
        primaryStage.show();

        //primaryStage.setOnCloseRequest(event -> Platform.exit());

        /*StackPane root = new StackPane();
        root.getChildren().add(btn);
        root.getChildren().add(menuBar);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();*/
    }
}
