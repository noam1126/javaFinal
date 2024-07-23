package com.example.clientapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BookDetailsController {

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
    private Stage stage; // Store the stage reference

    @FXML
    private void initialize() {
        // Bind the books list to the ListView
        bookListView.setItems(books);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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

    private void clearFields() {
        nameField.clear();
        descriptionField.clear();
        authorField.clear();
        yearField.clear();
    }
}
