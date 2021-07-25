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

public class ClientsWindowController implements Initializable {
    @FXML
    private TextArea textPlace;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField criterionField;

    private ClientsManager clientsManager;
    private Stage stage;

    private Scene scene;

    private Parent root;


    public void setClientsManager(ClientsManager clientsManager) {
        this.clientsManager = clientsManager;
    }

    public void showAllClients(ActionEvent e){
        Alert alert = AlertsDialogs.createAlert("Czy na pewno chcesz wyświetlić wszystkich klientów?");
        if(alert.getResult() == ButtonType.OK) {
            textPlace.setText(Client.getHeader() + clientsManager.showAllClients());
        }
    }

    public void showSelectedClients(ActionEvent event){

        try{
            Alert alert = AlertsDialogs.createAlert("Czy na pewno chcesz wyświetlić klientów spełniających kryteria?");
            if(alert.getResult() == ButtonType.OK) {
                String criterion = criterionField.getText();
                String foundedCliendList;
                if (criterion.isEmpty()) {
                    textPlace.setText("Pole z kryterium nie może być puste");
                } else {

                    foundedCliendList = switch (choiceBox.getValue()) {
                        case "Nr konta" -> clientsManager.returnClientsById(Integer.parseInt(criterion)).getClient();
                        case "Imię" -> clientsManager.showClientsByFirstName(criterion);
                        case "Nazwisko" -> clientsManager.showClientsBySecondName(criterion);
                        case "PESEL" -> clientsManager.showClientsByPesel(criterion);
                        case "Miasto" -> clientsManager.showClientsByCity(criterion);
                        default -> "Niepoprawny wybór";
                    };

                    textPlace.setText(Client.getHeader() + foundedCliendList);
                }
            }

        }catch (NumberFormatException e){
            textPlace.setText("Wprowadzone kryterium jest nieprawidlowe");
        }catch (ClientNotFoundException e){
            textPlace.setText("Nie znaleziono klienta");
        }

    }


    public void goMainWindow(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));

        root = loader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().addAll("Nr konta", "Imię", "Nazwisko", "PESEL", "Miasto");
        choiceBox.setValue("Nr konta");
    }
}
