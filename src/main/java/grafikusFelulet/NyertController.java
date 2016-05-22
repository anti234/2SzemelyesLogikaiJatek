// CHECKSTYLE:OFF
package grafikusFelulet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NyertController implements Initializable {

    private final Logger logger = LoggerFactory.getLogger(JatekTablaController.class);

    @FXML
    private Button ujJatekButton;

    @FXML
    private Label nyertLabel;

    @FXML
    private void ujJatekButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/JatekTabla.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Játék Tábla");
            stage.show();
            stage = (Stage) ujJatekButton.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initData(String string) {
        nyertLabel.setText(string);
    }

}
