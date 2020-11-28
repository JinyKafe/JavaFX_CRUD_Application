package com.hexenwerk.tutorial.javafx.crm.client.mvvc.main.left;

import com.hexenwerk.tutorial.javafx.crm.client.AbstractModelView;
import com.hexenwerk.tutorial.javafx.crm.domain.GenderType;
import com.hexenwerk.tutorial.javafx.crm.domain.Person;
import com.hexenwerk.tutorial.javafx.crm.domain.RoleType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;

import java.time.LocalDate;

public class PersonDetailMV extends AbstractModelView<Person, PersonDetailVC> {

    public final StringProperty id = new SimpleStringProperty();

    private final StringProperty firstName = new SimpleStringProperty();

    private final StringProperty lastName = new SimpleStringProperty();

    private final ObjectProperty<LocalDate> birthDay = new SimpleObjectProperty<>();


    private final ObjectProperty<GenderType> gender = new SimpleObjectProperty<>();

    private final ObjectProperty<RoleType> role = new SimpleObjectProperty<>();

    private final StringProperty email = new SimpleStringProperty();

    private final StringProperty password = new SimpleStringProperty();

    private ChangeListener<Toggle> genderChangeListener;
    private ChangeListener<RoleType> roleTypeChangeListener;


    public StringProperty idProperty() {
        return id;
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public ObjectProperty<LocalDate> birthDayProperty() {
        return birthDay;
    }

    public ObjectProperty<GenderType> genderProperty() {
        return gender;
    }

    public ObjectProperty<RoleType> roleProperty() {
        return role;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty passwordProperty() {
        return password;
    }


    @Override
    public void merge() {
        Person.builder()
                .id(id.get())
                .firstName(firstName.get())
                .lastName(lastName.get())
                .birthDay(birthDay.get())
                .gender(gender.get())
                .role(role.get())
                .email(email.get())
                .password(password.get())
                .update(super.model);
    }

    @Override
    public void load() {
        Person person = super.model;

        this.id.setValue(person.getId());
        this.firstName.setValue(person.getFirstName());
        this.lastName.setValue(person.getFirstName());
        this.birthDay.setValue(person.getBirthDay());
        this.gender.setValue(person.getGender());
        this.role.setValue(person.getRole());
        this.email.setValue(person.getEmail());
        this.password.setValue(person.getPassword());
    }

    @Override
    public void bind(PersonDetailVC view) {

        this.genderChangeListener = (ov, oldToggle, newToggle)
                -> gender.setValue(((RadioButton) newToggle).getId().equals(view.maleRadioButton.getId())
                ? GenderType.MALE
                : GenderType.FEMALE);

        this.roleTypeChangeListener = (observable, oldValue, newValue)
                -> role.setValue(newValue);

        Bindings.bindBidirectional(view.firstNameField.textProperty(), firstName);
        Bindings.bindBidirectional(view.lastNameField.textProperty(), lastName);
        Bindings.bindBidirectional(view.emailTextField.textProperty(), email);
        Bindings.bindBidirectional(view.passwordField.textProperty(), password);
        Bindings.bindBidirectional(view.birthDayPicker.valueProperty(), birthDay);

        view.genderToggleGroup.selectToggle(GenderType.MALE == gender.getValue()
                ? view.maleRadioButton
                : view.femaleRadioButton);

        view.genderToggleGroup.selectedToggleProperty().addListener(genderChangeListener);

        view.userRoleComboBox.getSelectionModel().select(role.getValue());

        view.userRoleComboBox.getSelectionModel().selectedItemProperty().addListener(roleTypeChangeListener);
    }

    @Override
    public void unbind(PersonDetailVC view) {
        Bindings.unbindBidirectional(view.userIdLabel.textProperty(), id);
        Bindings.unbindBidirectional(view.firstNameField.textProperty(), firstName);
        Bindings.unbindBidirectional(view.lastNameField.textProperty(), lastName);
        Bindings.unbindBidirectional(view.emailTextField.textProperty(), email);
        Bindings.unbindBidirectional(view.passwordField.textProperty(), password);
        Bindings.unbindBidirectional(view.birthDayPicker.valueProperty(), birthDay);
        view.genderToggleGroup.selectedToggleProperty().removeListener(genderChangeListener);
        view.userRoleComboBox.getSelectionModel().selectedItemProperty().removeListener(roleTypeChangeListener);
        view.clear();
    }
}
