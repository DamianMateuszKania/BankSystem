package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    public Button showClientsWindow;

    @FXML
    public TextArea textArea;

    private Stage stage;

    private Scene scene;

    private Parent root;

    public void goToClientsWindow(ActionEvent event) {

        try {
            ClientsManager clientsManager = new ClientsManager();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientsWindow.fxml"));

            root = loader.load();

            ClientsWindowController sceneShowClients = loader.getController();

            sceneShowClients.setClientsManager(clientsManager);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void goToAddClientsWindow(ActionEvent event) {
        try {
            ClientsManager clientsManager = new ClientsManager();
            clientsManager.loadData("clientsDatabase");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddClientsWindows.fxml"));

            root = loader.load();

            AddClientsWindowController sceneAddClients = loader.getController();

            sceneAddClients.setClientsManager(clientsManager);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }


    }

    public void goToPaymentWindow(ActionEvent event) {
        try {
            ClientsManager clientsManager = new ClientsManager();
            clientsManager.loadData("clientsDatabase");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentWindow.fxml"));

            root = loader.load();

            PaymentWindowController scenePayment = loader.getController();

            scenePayment.setClientsManager(clientsManager);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void goToDeleteClientWindow(ActionEvent event) {
        try {
            ClientsManager clientsManager = new ClientsManager();
            clientsManager.loadData("clientsDatabase");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeleteClientWindow.fxml"));

            root = loader.load();

            DeleteClientWindowController sceneDelete = loader.getController();

            sceneDelete.setClientsManager(clientsManager);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void createTestDatabase(ActionEvent event) {
        try {
            Alert alert = AlertsDialogs.createAlert("Czy na pewno chcesz stworzyć bazę testową?");
            if (alert.getResult() == ButtonType.OK) {
                ClientsManager clientsManager = new ClientsManager();
                clientsManager.testClients();
                textArea.setText("Stworzona nowa baze");
            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setText("Miłego dnia :)");
    }


}
