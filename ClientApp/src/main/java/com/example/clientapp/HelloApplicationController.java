package com.example.clientapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloApplicationController {

    @FXML
    private ListView<String> bookListView;

    @FXML
    private TextField nameField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField yearField;

    private final ObservableList<String> books = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        bookListView.setItems(books);
    }

    @FXML
    private void onSaveButtonClick() {
        String name = nameField.getText();
        String description = descriptionField.getText();
        String author = authorField.getText();
        String year = yearField.getText();

        String bookEntry = name + " | " + description + " | " + author + " | " + year;

        books.add(bookEntry);

        clearFields();
    }

    @FXML
    private void onUpdateButtonClick() {
        int selectedIndex = bookListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String updatedBook = nameField.getText() + " | " +
                    descriptionField.getText() + " | " +
                    authorField.getText() + " | " +
                    yearField.getText();
            books.set(selectedIndex, updatedBook);

            clearFields();
        }
    }

    @FXML
    private void onRemoveButtonClick() {
        int selectedIndex = bookListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            books.remove(selectedIndex);

            clearFields();
        }
    }

    private void clearFields() {
        nameField.clear();
        descriptionField.clear();
        authorField.clear();
        yearField.clear();
    }
}
