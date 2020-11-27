package com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.top;

import com.hexenwerk.tutorial.javafx.crm.JavaFxRunner;
import com.hexenwerk.tutorial.javafx.crm.ResourceBundleUtil;
import com.hexenwerk.tutorial.javafx.crm.client.StageManager;
import com.hexenwerk.tutorial.javafx.crm.client.ViewController;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.MainPaneVC;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
@FxmlView("MenuBarView.fxml")
public class MenuBarVC implements ViewController {

    private final StageManager stageManager;

    public Menu languageMenu;

    public MenuBarVC(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (Locale locale : ResourceBundleUtil.getSupportedLanguages()) {
            MenuItem menuItem = new MenuItem(locale.getDisplayLanguage());
            menuItem.setOnAction(event ->
            {
                JavaFxRunner.setLOCALE(locale);
                stageManager.displayScene(MainPaneVC.class, ResourceBundleUtil.getValue("mainPane.title"));
            });
            languageMenu.getItems().addAll(menuItem);
        }
    }

    @Override
    public void clear() {
        // not applicable
    }
}
