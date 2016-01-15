package org.k.ui;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.k.dao.PlaceDAOImpl;
import org.k.domain.Place;

public class MainView extends CustomComponent implements View {
    public static final String NAME = "main";

    private Button newPlace = new Button("New place");
    private TextField filter = new TextField();
    private Grid placeList = new Grid();
    private PlaceDAOImpl placeDAO = PlaceDAOImpl.getInstance();
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
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
                Window newPlaceWindow = new NewPlaceWindow();
               getUI().addWindow(newPlaceWindow);
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
        content.setComponentAlignment(placeList, Alignment.BOTTOM_CENTER);
        placeList.setWidth("100%");
        placeList.setHeight("100%");
        content.setExpandRatio(placeList,1);

        setCompositionRoot(content);
    }

    public void refreshPlaces(){
        refreshPlaces(filter.getValue());
    }

    private void refreshPlaces(String stringFilter){
        placeList.setContainerDataSource(new BeanItemContainer<>(Place.class,placeDAO.getPlacesByName(stringFilter)));
    }

}
