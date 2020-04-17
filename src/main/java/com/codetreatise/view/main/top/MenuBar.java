package com.codetreatise.view.main.top;

import com.codetreatise.MainApp;
import com.codetreatise.view.ResourceBundleUtil;
import com.codetreatise.view.main.MainPane;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

@Component
@FxmlView
public class MenuBar implements Initializable
{
    public Menu languageMenu;

    @FXML
    private void exit(ActionEvent event)
    {
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        for (Locale locale : ResourceBundleUtil.getSupportedLanguages())
        {
            MenuItem menuItem = new MenuItem(locale.getDisplayLanguage());
            menuItem.setOnAction(event ->
            {
                MainApp.setLOCALE(locale);
                MainApp.getStageManager().rebuildStage(MainPane.class);
            });
            languageMenu.getItems().addAll(menuItem);
        }
    }
}
