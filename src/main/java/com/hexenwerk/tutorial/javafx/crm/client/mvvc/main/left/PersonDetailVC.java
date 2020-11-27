package com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.left;

import com.hexenwerk.tutorial.javafx.crm.client.ViewController;
import com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.center.PersonListMV;
import com.hexenwerk.tutorial.javafx.crm.domain.RoleType;
import com.hexenwerk.tutorial.javafx.crm.service.PersonService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("PersonDetailView.fxml")
public class PersonDetailVC implements ViewController {

    private final PersonListMV personListMV;
    private final PersonService personService;

    private final ObservableList<RoleType> roles = FXCollections.observableArrayList(RoleType.ADMIN, RoleType.USER);

    @FXML
    public
    Label userIdLabel;

    @FXML
    public
    TextField firstNameField;

    @FXML
    public
    TextField lastNameField;

    @FXML
    public
    DatePicker birthDayPicker;

    @FXML
    public
    RadioButton maleRadioButton;

    @FXML
    public
    RadioButton femaleRadioButton;

    @FXML
    ToggleGroup genderToggleGroup;

    @FXML
    public
    ComboBox<RoleType> userRoleComboBox;

    @FXML
    TextField emailTextField;

    @FXML
    PasswordField passwordField;

    @FXML
    private Button resetButton;

    @FXML
    private Button saveButton;

    public PersonDetailVC(PersonListMV personListMV, PersonService personService) {
        this.personListMV = personListMV;
        this.personService = personService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        personListMV.currentPersonProperty().addListener(
                (ObservableValue<? extends PersonDetailMV> obs, PersonDetailMV oldUser, PersonDetailMV newUser) -> {
                    if (oldUser != null) oldUser.unbind(this);
                    if (newUser != null) newUser.bind(this);
                });

        userRoleComboBox.setItems(roles);
//        resetButton.setOnAction(event -> userDetailPaneManager.clearFields());
        saveButton.setOnAction(event -> personService.save(personListMV.getCurrentPersonModelView().getModel()));
    }

    @Override
    public void clear() {
        this.firstNameField.setText("");
        this.lastNameField.setText("");
        this.userIdLabel.setText("");
        this.firstNameField.setText("");
        this.lastNameField.setText("");
        this.maleRadioButton.setSelected(true);
        this.femaleRadioButton.setSelected(false);
        this.userRoleComboBox.getSelectionModel().clearSelection();
        this.emailTextField.clear();
        this.passwordField.clear();
    }
}
