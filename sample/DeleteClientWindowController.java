package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class DeleteClientWindowController {

    @FXML
    public TextField idToDeletedTextField;

    @FXML
    public TextArea label;

    private ClientsManager clientsManager;
    private Stage stage;

    private Scene scene;

    private Parent root;

    public void setClientsManager(ClientsManager clientsManager) {
        this.clientsManager = clientsManager;
    }

    public void deleteUser(ActionEvent event) {
        try {
            Alert alert = AlertsDialogs.createAlert("Czy na pewno chcesz skasować wybranego klienta?");
            if (alert.getResult() == ButtonType.OK) {
                clientsManager.deleteClient(Integer.parseInt(idToDeletedTextField.getText()));
                label.setText("Użytkownik poprawnie skasowany");
            }
        } catch (ClientNotFoundException e) {
            label.setText("Nie znaleziono takiego użytkownika");
        } catch (IOException e) {
            label.setText("Problem z zapisaniem zmian");
        } catch (NumberFormatException e) {
            label.setText("Wprowadzone dane są nieprawidłowe");
        }
    }


    public void goMainWindow(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));

        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }


}

