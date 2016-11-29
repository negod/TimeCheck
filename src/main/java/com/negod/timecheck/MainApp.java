package com.negod.timecheck;

import com.negod.timecheck.event.EventRegistry;
import com.negod.timecheck.event.events.GenericObjectEvents;
import com.negod.timecheck.event.events.TimerEvent;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainApp extends Application {

    private final String ROOT_SCENE = "/fxml/Scene.fxml";
    private final String ROOT_CSS = "/styles/Styles.css";
    private final String TITLE = "TimeCheck";

    @Override
    public void start(Stage stage) throws Exception {

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });

        EventRegistry.getInstance().register(TimerEvent.values());
        EventRegistry.getInstance().register(GenericObjectEvents.values());

        Parent root = FXMLLoader.load(getClass().getResource(ROOT_SCENE));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(ROOT_CSS);

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
