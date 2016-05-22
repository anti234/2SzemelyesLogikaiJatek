// CHECKSTYLE:OFF
package grafikusFelulet;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JatekosokNeveiController implements Initializable {

    @FXML
    private TextField kekJatekosNeve;

    @FXML
    private TextField sargaJatekosNeve;

    @FXML
    private Button jatekButton;

    private GrafikusVezerlo grafikusVezerlo;

    @FXML
    private void jatekButtonAction(ActionEvent event) {
        grafikusVezerlo.setKekJatekosNeve(kekJatekosNeve.getText());
        grafikusVezerlo.setSargaJatekosNeve(sargaJatekosNeve.getText());
        Stage stage = (Stage) jatekButton.getScene().getWindow();
        stage.close();
        grafikusVezerlo.setLephet(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initData(GrafikusVezerlo grafikusVezerlo) {
        this.grafikusVezerlo = grafikusVezerlo;
        if (grafikusVezerlo.getKekJatekosNeve() != null) {
            kekJatekosNeve.setText(grafikusVezerlo.getKekJatekosNeve());
            sargaJatekosNeve.setText(grafikusVezerlo.getSargaJatekosNeve());
        }
    }

}
