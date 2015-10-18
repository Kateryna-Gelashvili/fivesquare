package org.k.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.k.dao.PlaceDAO;
import org.k.dao.PlaceDAOImpl;
import org.k.domain.Place;

public class NewPlaceWindow extends Window {
    private MainUI mainUI;
    private final TextField nameField;

    public NewPlaceWindow(MainUI mainUI) {
        super("Add new place");
        this.mainUI = mainUI;
        setWidth("30%");
        VerticalLayout windowContent = new VerticalLayout();
        windowContent.setMargin(true);
        setContent(windowContent);

        nameField = new TextField("Name");
        nameField.setWidth("100%");
        Button saveButton = new Button("Save");
        saveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                savePlace();
            }
        });

        windowContent.addComponent(nameField);
        windowContent.addComponent(saveButton);
        center();
    }

    private void savePlace() {
        if (!nameField.isEmpty()){
            PlaceDAO placeDAO = PlaceDAOImpl.getInstance();
            Place place = new Place();
            place.setName(nameField.getValue());
            placeDAO.addPlace(place);
        }
        mainUI.refreshPlaces();
        close();
    }
}
