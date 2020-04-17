package com.codetreatise.view.main.left;

import com.codetreatise.view.FxController;
import com.codetreatise.view.main.center.UserListPaneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Controller
@FxmlView
public class UserDetailPane implements Initializable, FxController
{
    @Autowired
    private UserDetailPaneManager userDetailPaneManager;

    @Autowired
    private UserListPaneManager userListPaneManager;

    private ObservableList<String> roles = FXCollections.observableArrayList("Admin", "User");

    @FXML
    public
    Label userId;

    @FXML
    public
    TextField firstName;

    @FXML
    public
    TextField lastName;

    @FXML
    public
    DatePicker dob;

    @FXML
    public
    RadioButton rbMale;

    @FXML
    ToggleGroup gender;

    @FXML
    public
    RadioButton rbFemale;

    @FXML
    public
    ComboBox<String> cbRole;

    @FXML
    TextField email;

    @FXML
    PasswordField password;

    @FXML
    private Button reset;

    @FXML
    private Button saveUser;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        cbRole.setItems(roles);

        reset.setOnAction(event -> userDetailPaneManager.clearFields());
        saveUser.setOnAction(event ->
        {
            userDetailPaneManager.saveUser();
            userListPaneManager.loadUserDetails();
        });
    }

    public String getGenderTitle(String gender)
    {
        return (gender.equals("Male")) ? "his" : "her";
    }

    public String getFirstName()
    {
        return firstName.getText();
    }

    public String getLastName()
    {
        return lastName.getText();
    }

    public LocalDate getDob()
    {
        return dob.getValue();
    }

    public String getGender()
    {
        return rbMale.isSelected() ? "Male" : "Female";
    }

    public String getRole()
    {
        return cbRole.getSelectionModel().getSelectedItem();
    }

    public String getEmail()
    {
        return email.getText();
    }

    public String getPassword()
    {
        return password.getText();
    }
}
