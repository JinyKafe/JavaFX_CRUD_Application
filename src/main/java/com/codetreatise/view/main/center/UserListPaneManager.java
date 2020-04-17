package com.codetreatise.view.main.center;

import com.codetreatise.model.User;
import com.codetreatise.service.UserServiceImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserListPaneManager
{
    @Autowired
    UserListPane userListPane;

    @Autowired
    private UserServiceImpl userService;

    public void deleteUsers()
    {
        List<User> users = userListPane.userTable.getSelectionModel().getSelectedItems();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete selected?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) userService.deleteInBatch(users);

        loadUserDetails();
    }

    /*
     *  Add All users to observable list and update table
     */
    public void loadUserDetails()
    {
        userListPane.userList.clear();
        userListPane.userList.addAll(userService.findAll());

        userListPane.userTable.setItems(userListPane.userList);
    }
}
