package com.codetreatise.view.login;

import com.codetreatise.view.main.MainPane;
import com.codetreatise.view.StageManager;
import com.codetreatise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class LoginPaneManager
{
    @Autowired
    private UserService userService;

    @Lazy
    @Autowired
    private StageManager stageManager;

    public void login(LoginPane loginPane)
    {
        String username = loginPane.getUsername();
        String password = loginPane.getPassword();

        if (userService.authenticate(username, password))
            stageManager.rebuildStage(MainPane.class);
        else
            loginPane.getLblLogin().setText("Login Failed.");
    }
}
