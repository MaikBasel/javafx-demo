package org.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;

public class ParentController {

    @FXML
    private Accordion accordion;

    public void onAddAction() {
        var itemControl = new ItemControl();
        EventHandler<ActionEvent> removeEventHandler = event -> {
            accordion.getPanes().remove(itemControl);
        };
        itemControl.setOnRemoveProperty(removeEventHandler);
        accordion.getPanes().add(itemControl);
    }
}
