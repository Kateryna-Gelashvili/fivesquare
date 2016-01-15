package org.k.ui;

import com.vaadin.ui.*;

public class RegistrationWindow extends Window {
    private TextField name;
    private TextField fullName;
    private PasswordField password;
    private PasswordField checkPassword;
    private Button registerButton;

    public RegistrationWindow() {
        super("Registration information");
        setWidth("30%");
        center();
        name = new TextField("Name");
        name.setWidth("100%");
        fullName = new TextField("Full name");
        fullName.setWidth("100%");
        password = new PasswordField("Password");
        password.setWidth("100%");
        checkPassword = new PasswordField("Repeat password");
        checkPassword.setWidth("100%");
        registerButton = new Button("Register");
        VerticalLayout layout = new VerticalLayout(name,fullName,password,checkPassword,registerButton);
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);
    }
}
