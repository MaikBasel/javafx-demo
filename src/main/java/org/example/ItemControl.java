package org.example;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class ItemControl extends TitledPane {

    private final UUID id = UUID.randomUUID();

    private final ObjectProperty<EventHandler<ActionEvent>> onRemoveProperty = new SimpleObjectProperty<>();

    @FXML
    private Button removeButton;

    public ItemControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(ItemControl.class.getResource("itemControl.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        removeButton.onActionProperty().bind(onRemoveProperty);
    }

    public void setOnRemoveProperty(EventHandler<ActionEvent> onRemoveProperty) {
        this.onRemoveProperty.set(onRemoveProperty);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemControl that = (ItemControl) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

