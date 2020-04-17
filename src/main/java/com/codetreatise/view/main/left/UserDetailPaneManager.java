package com.codetreatise.view.main.left;

import com.codetreatise.model.User;
import com.codetreatise.service.UserServiceImpl;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserDetailPaneManager
{
    @Autowired
    UserDetailPane userDetailPane;

    @Autowired
    private UserServiceImpl userService;

    public void clearFields()
    {
        userDetailPane.userId.setText(null);
        userDetailPane.firstName.clear();
        userDetailPane.lastName.clear();
        userDetailPane.dob.getEditor().clear();
        userDetailPane.rbMale.setSelected(true);
        userDetailPane.rbFemale.setSelected(false);
        userDetailPane.cbRole.getSelectionModel().clearSelection();
        userDetailPane.email.clear();
        userDetailPane.password.clear();
    }

    public void saveUser()
    {
        if (validate("First Name", userDetailPane.firstName.getText(), "[a-zA-Z]+") &&
                validate("Last Name", userDetailPane.lastName.getText(), "[a-zA-Z]+") &&
                emptyValidation("DOB", userDetailPane.dob.getEditor().getText().isEmpty()) &&
                emptyValidation("Role", userDetailPane.getRole() == null))
        {

            if (userDetailPane.userId.getText() == null || userDetailPane.userId.getText() == "")
            {
                if (validate("Email", userDetailPane.getEmail(), "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+") &&
                        emptyValidation("Password", userDetailPane.getPassword().isEmpty()))
                {

                    User user = new User();
                    user.setFirstName(userDetailPane.getFirstName());
                    user.setLastName(userDetailPane.getLastName());
                    user.setDob(userDetailPane.getDob());
                    user.setGender(userDetailPane.getGender());
                    user.setRole(userDetailPane.getRole());
                    user.setEmail(userDetailPane.getEmail());
                    user.setPassword(userDetailPane.getPassword());

                    User newUser = userService.save(user);

                    saveAlert(newUser);
                }

            } else
            {
                User user = userService.findById(Long.parseLong(userDetailPane.userId.getText()));
                user.setFirstName(userDetailPane.getFirstName());
                user.setLastName(userDetailPane.getLastName());
                user.setDob(userDetailPane.getDob());
                user.setGender(userDetailPane.getGender());
                user.setRole(userDetailPane.getRole());
                User updatedUser = userService.update(user);
                updateAlert(updatedUser);
            }

            clearFields();
        }
    }

    public void refreshUserPane(User user)
    {
        userDetailPane.userId.setText(Long.toString(user.getId()));
        userDetailPane.firstName.setText(user.getFirstName());
        userDetailPane.lastName.setText(user.getLastName());
        userDetailPane.dob.setValue(user.getDob());
        userDetailPane.cbRole.getSelectionModel().select(user.getRole());

        if (user.getGender().equals("Male"))
            userDetailPane.rbMale.setSelected(true);
        else
            userDetailPane.rbFemale.setSelected(true);
    }

    /*
     * Validations
     */
    boolean validate(String field, String value, String pattern)
    {
        if (!value.isEmpty())
        {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.find() && m.group().equals(value))
            {
                return true;
            } else
            {
                validationAlert(field, false);
                return false;
            }
        } else
        {
            validationAlert(field, true);
            return false;
        }
    }

    private boolean emptyValidation(String field, boolean empty)
    {
        if (!empty)
        {
            return true;
        } else
        {
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (field.equals("Role")) alert.setContentText("Please Select " + field);
        else
        {
            if (empty) alert.setContentText("Please Enter " + field);
            else alert.setContentText("Please Enter Valid " + field);
        }
        alert.showAndWait();
    }

    private void saveAlert(User user)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User saved successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user " + user.getFirstName() + " " + user.getLastName() + " has been created and \n" + userDetailPane.getGenderTitle(user.getGender()) + " id is " + user.getId() + ".");
        alert.showAndWait();
    }

    private void updateAlert(User user)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User updated successfully.");
        alert.setHeaderText(null);
        alert.setContentText("The user " + user.getFirstName() + " " + user.getLastName() + " has been updated.");
        alert.showAndWait();
    }
}
