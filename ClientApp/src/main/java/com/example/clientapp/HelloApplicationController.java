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
        // Bind the books list to the ListView
        bookListView.setItems(books);
    }

    @FXML
    private void onSaveButtonClick() {
        // Get book details from text fields
        String name = nameField.getText();
        String description = descriptionField.getText();
        String author = authorField.getText();
        String year = yearField.getText();

        // Create a book entry
        String bookEntry = name + " | " + description + " | " + author + " | " + year;

        // Add book entry to the list
        books.add(bookEntry);

        // Clear input fields after adding book
        clearFields();
    }

    @FXML
    private void onUpdateButtonClick() {
        // Get selected book from list
        int selectedIndex = bookListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Update book entry in the list
            String updatedBook = nameField.getText() + " | " +
                    descriptionField.getText() + " | " +
                    authorField.getText() + " | " +
                    yearField.getText();
            books.set(selectedIndex, updatedBook);

            // Clear input fields after updating book
            clearFields();
        }
    }

    @FXML
    private void onRemoveButtonClick() {
        // Get selected book from list
        int selectedIndex = bookListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            // Remove book entry from the list
            books.remove(selectedIndex);

            // Clear input fields after removing book
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
