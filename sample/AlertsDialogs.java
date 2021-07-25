package sample;

import javafx.scene.control.Alert;

public class AlertsDialogs {

    public static Alert createAlert(String message){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Spójrz, na informację poniżej");
        alert.setContentText(message);
        alert.showAndWait();
        return alert;
    }

}
