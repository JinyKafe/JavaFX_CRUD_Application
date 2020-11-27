package com.hexenwerk.tutorial.javafx.crm.client;

import com.hexenwerk.tutorial.javafx.crm.ResourceBundleUtil;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class StageManager {
    private static final Logger LOG = getLogger(StageManager.class);

    private final FxWeaver fxWeaver;
    private Stage primaryStage;

    private final List<String> styleSheets;

    public StageManager(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
        this.styleSheets = new ArrayList<>();
        this.styleSheets.add("/styles/Styles.css");
    }

    public void displayScene(Class<? extends Initializable> fxControllerClass, String title) {
        Objects.requireNonNull(primaryStage, "primaryStage is not set!");

        Scene scene = new Scene(fxWeaver.loadView(fxControllerClass));
        scene.getStylesheets().addAll(getStyleSheets());

        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
//        primaryStage.centerOnScreen();

        try {
            primaryStage.show();
        } catch (Exception exception) {
            logAndExit("Unable to show scene with title " + title, exception);
        }
    }

    private void logAndExit(String errorMsg, Exception exception) {
        LOG.error(errorMsg, exception, exception.getCause());
        Platform.exit();
    }

    public <C, V extends Node> V loadView(Class<C> controllerClass) {
        return fxWeaver.loadView(controllerClass, ResourceBundleUtil.getResourceBundle());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public List<String> getStyleSheets() {
        return styleSheets;
    }
}
