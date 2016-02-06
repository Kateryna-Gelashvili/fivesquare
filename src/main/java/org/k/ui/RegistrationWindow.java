package org.k.ui;

import com.vaadin.ui.*;
import org.k.dao.UserDAO;
import org.k.dao.UserDAOImpl;
import org.k.domain.User;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationWindow extends Window {
    private TextField name;
    private TextField fullName;
    private PasswordField password;
    private PasswordField checkPassword;
    private Button registerButton;
    private Label message;
    private static final Logger logger = LoggerFactory.getLogger(RegistrationWindow.class);

    public RegistrationWindow() {
        super("Registration information");
        setWidth("30%");
        center();
        name = new TextField("Name");
        name.setWidth("100%");
        name.focus();
        fullName = new TextField("Full name");
        fullName.setWidth("100%");
        password = new PasswordField("Password");
        password.setWidth("100%");
        checkPassword = new PasswordField("Repeat password");
        checkPassword.setWidth("100%");
        message = new Label();
        message.setWidth("100%");

        registerButton = new Button("Register");
        registerButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                logger.debug("Registration requested: {}",name.getValue());
                saveUser();
            }
        });


        VerticalLayout layout = new VerticalLayout(name,fullName,password,checkPassword,registerButton,message);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
    }

    private void saveUser(){
        UserDAO userDAO = UserDAOImpl.getInstance();
        User userDB = userDAO.getUserByName(name.getValue());
        if (userDB != null){
            message.setValue("Sorry this name is not free");
            logger.debug("Registration declined, name {} is not free", name.getValue());
        }else {
            User newUser = new User();
            newUser.setName(name.getValue());
            newUser.setPassword(BCrypt.hashpw(password.getValue(),BCrypt.gensalt()));
            newUser.setFullName(fullName.getValue());
            try {
                User addedUser = userDAO.addUser(newUser);
                message.setValue("User was registered successfully");
                logger.debug("Successfully saved user: {}", name.getValue());
            }catch (Exception e){
                logger.error("Registration failed",e);
                message.setValue("Something went wrong. Try again");
            }
        }
    }
}
