package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AddClientsWindowController {

    @FXML
    public TextField firstName;
    @FXML
    public TextField secondName;
    @FXML
    public TextField pesel;
    @FXML
    public TextField city;
    @FXML
    public TextField zipCode;
    @FXML
    public TextField street;
    @FXML
    public TextField houseNumber;
    @FXML
    public TextArea informLabel;

    private ClientsManager clientsManager;
    private Stage stage;

    private Scene scene;

    private Parent root;


    public void setClientsManager(ClientsManager clientsManager) {
        this.clientsManager = clientsManager;
    }

    public void addClients(ActionEvent event) {
        try {
            Alert alert = AlertsDialogs.createAlert("Czy na pewno chcesz wyświetlić wszystkich klientów?");
            if (alert.getResult() == ButtonType.OK) {
                clientsManager.addClient(firstName.getText(), secondName.getText(), pesel.getText(), city.getText(), zipCode.getText(), street.getText(), houseNumber.getText());
                clearForm();
                informLabel.setText("Dodano klienta poprawnie");
            }
        } catch (IOException e) {
            informLabel.setText("Wyjatek w add client linia 52");
        } catch (WrongDataFormatException e) {
            informLabel.setText(e.getMessage());
        }
    }

    public void clearForm() {
        firstName.clear();
        secondName.clear();
        pesel.clear();
        city.clear();
        zipCode.clear();
        street.clear();
        houseNumber.clear();
    }

    public void goMainWindow(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));

        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

}
