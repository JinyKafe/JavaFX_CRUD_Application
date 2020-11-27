package com.hexenwerk.tutorial.javafx.crm.client.mvvc.login;


import com.hexenwerk.tutorial.javafx.crm.ResourceBundleUtil;
import com.hexenwerk.tutorial.javafx.crm.client.StageManager;
import com.hexenwerk.tutorial.javafx.crm.client.ViewController;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.MainPaneVC;
import com.hexenwerk.tutorial.javafx.crm.service.PersonService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("LoginPane.fxml")
public class LoginPaneVC implements ViewController {

    private final StageManager stageManager;
    private final PersonService userService;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label lblLogin;

    public LoginPaneVC(StageManager stageManager, PersonService userService) {
        this.stageManager = stageManager;
        this.userService = userService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        EventHandler<ActionEvent> loginEventHandler = event -> {
            String username1 = getUsername();
            String password1 = getPassword();

            if (userService.authenticate(username1, password1))
                stageManager.displayScene(MainPaneVC.class, ResourceBundleUtil.getValue("mainPane.title"));
            else
                getLblLogin().setText("Login Failed.");
        };

        password.setOnAction(loginEventHandler);
        btnLogin.setOnAction(loginEventHandler);
    }

    public String getPassword() {
        return password.getText();
    }

    public String getUsername() {
        return username.getText();
    }

    public Label getLblLogin() {
        return lblLogin;
    }

    @Override
    public void clear() {
        password.textProperty().setValue("");
        username.textProperty().setValue("");
    }
}
