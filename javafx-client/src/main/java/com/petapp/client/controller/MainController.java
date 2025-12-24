package com.petapp.client.controller;

import com.petapp.client.model.Pet;
import com.petapp.client.service.PetApiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class MainController {

    @FXML private TableView<Pet> petTable;
    @FXML private TableColumn<Pet, Long> idColumn;
    @FXML private TableColumn<Pet, String> petNameColumn;
    @FXML private TableColumn<Pet, String> speciesColumn;
    @FXML private TableColumn<Pet, String> ownerNameColumn;

    @FXML private TextField petNameField;
    @FXML private TextField speciesField;
    @FXML private TextField ownerNameField;

    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private Button refreshButton;

    private final PetApiService apiService = new PetApiService();
    private final ObservableList<Pet> petList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Настройка колонок таблицы
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        petNameColumn.setCellValueFactory(new PropertyValueFactory<>("petName"));
        speciesColumn.setCellValueFactory(new PropertyValueFactory<>("species"));
        ownerNameColumn.setCellValueFactory(new PropertyValueFactory<>("ownerName"));

        petTable.setItems(petList);

        // Обработчик выбора строки в таблице
        petTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {
                if (newValue != null) {
                    fillFields(newValue);
                }
            }
        );

        loadPets();
    }

    @FXML
    private void handleAdd() {
        if (petNameField.getText().isEmpty() || speciesField.getText().isEmpty() || ownerNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Предупреждение", "Заполните все поля!");
            return;
        }
        
        try {
            Pet pet = new Pet(
                petNameField.getText(),
                speciesField.getText(),
                ownerNameField.getText()
            );

            Pet createdPet = apiService.createPet(pet);
            petList.add(createdPet);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Успех", "Питомец добавлен!");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Не удалось добавить питомца: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        Pet selectedPet = petTable.getSelectionModel().getSelectedItem();
        if (selectedPet == null) {
            showAlert(Alert.AlertType.WARNING, "Предупреждение", "Выберите питомца для обновления!");
            return;
        }

        if (petNameField.getText().isEmpty() || speciesField.getText().isEmpty() || ownerNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Предупреждение", "Заполните все поля!");
            return;
        }

        try {
            Pet updatedPet = new Pet(
                petNameField.getText(),
                speciesField.getText(),
                ownerNameField.getText()
            );

            apiService.updatePet(selectedPet.getId(), updatedPet);
            loadPets();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Успех", "Питомец обновлен!");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Не удалось обновить питомца: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        Pet selectedPet = petTable.getSelectionModel().getSelectedItem();
        if (selectedPet == null) {
            showAlert(Alert.AlertType.WARNING, "Предупреждение", "Выберите питомца для удаления!");
            return;
        }

        try {
            apiService.deletePet(selectedPet.getId());
            petList.remove(selectedPet);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Успех", "Питомец удален!");
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Не удалось удалить питомца: " + e.getMessage());
        }
    }

    @FXML
    private void handleRefresh() {
        loadPets();
    }

    private void loadPets() {
        try {
            petList.clear();
            petList.addAll(apiService.getAllPets());
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Не удалось загрузить список питомцев: " + e.getMessage());
        }
    }

    private void fillFields(Pet pet) {
        petNameField.setText(pet.getPetName());
        speciesField.setText(pet.getSpecies());
        ownerNameField.setText(pet.getOwnerName());
    }

    private void clearFields() {
        petNameField.clear();
        speciesField.clear();
        ownerNameField.clear();
        petTable.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}