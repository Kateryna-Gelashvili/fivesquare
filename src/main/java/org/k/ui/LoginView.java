package org.k.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.k.dao.UserDAOImpl;
import org.k.domain.User;
import org.mindrot.jbcrypt.BCrypt;

public class LoginView extends CustomComponent implements View {
    public static final String NAME = "login";
    private final TextField user;
    private final PasswordField password;
    private final Button loginButton;
    private final Button registerButton;
    private UserDAOImpl userDAO = UserDAOImpl.getInstance();

    public LoginView() {
        setSizeFull();
        user = new TextField("User:");
        user.setWidth("300px");
        user.setRequired(true);

        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");

        loginButton = new Button("Login");
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String username = user.getValue();
                String pwd = password.getValue();

                User userdb = userDAO.getUserByName(username);

                if (BCrypt.checkpw(pwd, userdb.getPassword())){
                    getSession().setAttribute("user",username);
                    getUI().getNavigator().navigateTo(MainView.NAME);
                }else {
                    password.setValue("");
                    password.focus();
                }
            }
        });
        registerButton = new Button("Register");
        registerButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window registrationWindow = new RegistrationWindow();
                getUI().addWindow(registrationWindow);
            }
        });

        HorizontalLayout buttons = new HorizontalLayout(loginButton,registerButton);
        buttons.setSpacing(true);

        VerticalLayout fields = new VerticalLayout(user,password,buttons);
        fields.setCaption("Please login to access the application.");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true,true,true,false));
        fields.setSizeUndefined();

        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields,Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);

        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        user.focus();
    }

}
