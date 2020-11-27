package com.hexenwerk.tutorial.javafx.crm.client.mvvc.main;

import com.hexenwerk.tutorial.javafx.crm.client.StageManager;
import com.hexenwerk.tutorial.javafx.crm.client.ViewController;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.center.PersonListVC;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.left.PersonDetailVC;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.top.MenuBarVC;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("MainPaneView.fxml")
public class MainPaneVC implements ViewController {

    private final StageManager stageManager;

    public MainPaneVC(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @FXML
    public BorderPane borderPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // set layout
        borderPane.setTop(stageManager.loadView(MenuBarVC.class));
        borderPane.setLeft(stageManager.loadView(PersonDetailVC.class));
        borderPane.setCenter(stageManager.loadView(PersonListVC.class));
    }

    @Override
    public void clear() {

    }
}
