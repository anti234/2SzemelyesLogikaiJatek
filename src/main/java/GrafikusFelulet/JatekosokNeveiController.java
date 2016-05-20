package GrafikusFelulet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anti
 */
public class JatekosokNeveiController implements Initializable {

    @FXML
    private TextField kekJatekosNeve;

    @FXML
    private TextField sargaJatekosNeve;

    @FXML
    private Button jatekButton;

    @FXML
    private void JatekButtonAction(ActionEvent event) {
        GrafikusVezerlo.setKekJatekosNeve(kekJatekosNeve.getText());
        GrafikusVezerlo.setSargaJatekosNeve(sargaJatekosNeve.getText());
        Stage stage = (Stage) jatekButton.getScene().getWindow();
        stage.close();
        GrafikusVezerlo.setLephet(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (GrafikusVezerlo.getKekJatekosNeve() != null) {
            kekJatekosNeve.setText(GrafikusVezerlo.getKekJatekosNeve());
            sargaJatekosNeve.setText(GrafikusVezerlo.getSargaJatekosNeve());
        }
    }

}
