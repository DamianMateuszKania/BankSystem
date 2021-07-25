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

public class PaymentWindowController {

    @FXML
    public TextArea textArea;
    @FXML
    public TextField amountTextField;
    @FXML
    public TextField idSourceTextField;
    @FXML
    public TextField idDestinationTextField;
    @FXML
    public ToggleGroup paymentButtons;

    private ClientsManager clientsManager;

    private Stage stage;

    private Scene scene;

    private Parent root;

    public void setClientsManager(ClientsManager clientsManager) {
        this.clientsManager = clientsManager;
    }

    public void payment(ActionEvent action) {
        try {
            Alert alert = AlertsDialogs.createAlert("Czy na pewno chcesz wykonać tę operację?");

            if (alert.getResult() == ButtonType.OK) {
                RadioButton selectedRadioButton = (RadioButton) paymentButtons.getSelectedToggle();
                Client client;

                switch (selectedRadioButton.getText()) {
                    case "Wpłata":
                        client = clientsManager.payIn(Integer.parseInt(idSourceTextField.getText()), Double.parseDouble(amountTextField.getText()));
                        textArea.setText("Dokonano wplaty. Obecny stan konta: \n" + client.getClient());
                        break;
                    case "Wypłata":
                        client = clientsManager.payOut(Integer.parseInt(idSourceTextField.getText()), Double.parseDouble(amountTextField.getText()));
                        textArea.setText("Dokonano wypłaty. Obecny stan konta: \n" + client.getClient());
                        break;
                    case "Przelew":
                        clientsManager.transferMoney(Integer.parseInt(idSourceTextField.getText()), Integer.parseInt(idDestinationTextField.getText()), Double.parseDouble(amountTextField.getText()));
                        textArea.setText("Dokonano Przlewu. Obecny stan konta: \n" + clientsManager.returnClientsById(Integer.parseInt(idSourceTextField.getText())).getClient() + "\n"
                                + clientsManager.returnClientsById(Integer.parseInt(idDestinationTextField.getText())).getClient());
                }
            }

        } catch (NumberFormatException e) {
            textArea.setText("Wprowadzone dane maja niepoprawny format");
        } catch (ClientNotFoundException e) {
            textArea.setText("Nie znaleziono takiego klienta");
        } catch (NotEnoughMoneyException e) {
            textArea.setText("Nie ma wystarczającej kwoty na koncie aby dokonać wypłaty");
        } catch (IOException e) {
            System.out.println("Problem z zapisaniem zmian");
        } catch (WrongDataFormatException e){
            textArea.setText("Wprowadzona wartosc jest nieprawidlowa");
        }
    }

    public void payWindowView(ActionEvent action) {
        idSourceTextField.setPromptText("Podaj nr klienta");
        idDestinationTextField.setVisible(false);
    }

    public void transferWindowView(ActionEvent action) {
        idSourceTextField.setPromptText("Podaj nr nadawcy");
        idDestinationTextField.setVisible(true);
    }

    public void goMainWindow(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));

        root = loader.load();

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
    }
}
