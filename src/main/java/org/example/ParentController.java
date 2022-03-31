package org.example;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;

public class ParentController {

    @FXML
    private Accordion accordion;

    private final ListProperty<ItemControl> itemsControlsProperty = new SimpleListProperty<>(
            FXCollections.observableArrayList());

    @FXML
    public void initialize() {
        accordion.getPanes().addAll(itemsControlsProperty.get());

        itemsControlsProperty.addListener((ListChangeListener<ItemControl>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    var added = change.getAddedSubList().stream()
                            .findFirst()
                            .orElseThrow();
                    accordion.getPanes().add(added);
                } else if (change.wasRemoved()) {
                    var removed = change.getRemoved().stream()
                            .findFirst()
                            .orElseThrow();
                    accordion.getPanes().remove(removed);
                }
            }
        });
    }

    public void onAddAction() {
        var itemControl = new ItemControl();
        EventHandler<ActionEvent> removeEventHandler = event -> {
            Node source = (Node) event.getSource();
            if ("removeButton".equals(source.getId())) {
                itemsControlsProperty.remove(itemControl);
            }
        };
        itemControl.setOnRemoveProperty(removeEventHandler);
        itemsControlsProperty.add(itemControl);
    }
}
