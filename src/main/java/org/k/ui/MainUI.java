package org.k.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.SelectionEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import org.k.dao.PlaceDAOImpl;
import org.k.domain.Place;

import javax.servlet.annotation.WebServlet;

@Title("Fivesquare")
@Theme("reindeer")
public class MainUI extends UI {
    private Button newPlace = new Button("New place");
    private TextField filter = new TextField();
    private Grid placeList = new Grid();
    private PlaceDAOImpl placeDAO = PlaceDAOImpl.getInstance();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        configureComponents();
        buildLayOut();
    }

    private void configureComponents(){
        filter.setInputPrompt("Filter places ... ");
        filter.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                refreshPlaces(event.getText());
            }
        });
        newPlace.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window newPlaceWindow = new NewPlaceWindow(MainUI.this);
                addWindow(newPlaceWindow);
            }
        });
        placeList.setContainerDataSource(new BeanItemContainer<>(Place.class));
        placeList.setColumnOrder("name");
        placeList.getColumn("id").setHidden(true);
        placeList.removeColumn("comments");
        placeList.removeColumn("tags");
        placeList.removeColumn("checkings");
        placeList.setSelectionMode(Grid.SelectionMode.SINGLE);
        placeList.addSelectionListener(new SelectionEvent.SelectionListener() {
            @Override
            public void select(SelectionEvent event) {

            }
        });
        refreshPlaces();
    }

    private void buildLayOut(){
        HorizontalLayout actions = new HorizontalLayout(filter,newPlace);
        actions.setWidth("100%");
        filter.setWidth("100%");
        actions.setExpandRatio(filter,1);

        VerticalLayout content = new VerticalLayout(actions,placeList);
        content.setSizeFull();
        placeList.setWidth("100%");
        content.setExpandRatio(placeList,1);

        setContent(content);
    }

    public void refreshPlaces(){
        refreshPlaces(filter.getValue());
    }

    private void refreshPlaces(String stringFilter){
        placeList.setContainerDataSource(new BeanItemContainer<>(Place.class,placeDAO.getPlacesByName(stringFilter)));
    }

    @WebServlet(urlPatterns = "/*")
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainUIServlet extends VaadinServlet{

    }
}
