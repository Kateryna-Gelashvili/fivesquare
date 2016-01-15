package org.k.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.k.dao.PlaceDAOImpl;
import org.k.domain.Place;

import javax.servlet.annotation.WebServlet;

@Title("Fivesquare")
@Theme("reindeer")
public class MainUI extends UI {


    @Override
    protected void init(VaadinRequest vaadinRequest) {
        new Navigator(this,this);
        getNavigator().addView(LoginView.NAME,LoginView.class);
        getNavigator().addView(MainView.NAME,MainView.class);
        getNavigator().navigateTo(LoginView.NAME);
    }


    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainUIServlet extends VaadinServlet{

    }
}
