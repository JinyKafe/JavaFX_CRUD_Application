package com.codetreatise.view.login;


import com.codetreatise.view.FxController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@FxmlView
public class LoginPane implements Initializable, FxController
{
    @Autowired
    private LoginPaneManager loginManager;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label lblLogin;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        EventHandler<ActionEvent> loginEventHandler = event -> loginManager.login(this);

        password.setOnAction(loginEventHandler);
        btnLogin.setOnAction(loginEventHandler);
    }

    public String getPassword()
    {
        return password.getText();
    }

    public String getUsername()
    {
        return username.getText();
    }

    public Label getLblLogin()
    {
        return lblLogin;
    }
}
