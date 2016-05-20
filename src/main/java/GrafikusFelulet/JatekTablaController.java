package GrafikusFelulet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Anti
 */
public class JatekTablaController implements Initializable {

    @FXML
    private GridPane gridPane;

    @FXML
    private Label kovetkezoJatekos;

    @FXML
    private Label uzenetek;

    @FXML
    private MenuItem ujJatek;

    private GrafikusVezerlo grafikusVezerlo;

    @FXML
    private void UjJatekMenuItemAction(ActionEvent event) {
        grafikusVezerlo = new GrafikusVezerlo(gridPane);
        GrafikusVezerlo.setLephet(true);
    }

    @FXML
    private void KilepesMenuItemAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void AtnevezesMenuItemAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/JatekosokNevei.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            GrafikusVezerlo.setLephet(false);
        } catch (IOException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void BetoltesMenuItem(ActionEvent event) {
    }

    @FXML
    public void GridPaneMouseAction(MouseEvent event) {
        if (GrafikusVezerlo.isLephet()) {
            for (Node node : gridPane.getChildren()) {
                if (node instanceof Circle) {
                    if (node.getBoundsInParent().contains(event.getX(), event.getY())) {
                        uzenetek.setText(grafikusVezerlo.Esemeny((Circle) node));
                        break;
                    }
                }
            }

            if (grafikusVezerlo.isNyert()) {
                Nyert();
            } else {
                kovetkezoJatekos.setText(grafikusVezerlo.getAktivJatekos() + " következik!");
                kovetkezoJatekos.setTextFill(grafikusVezerlo.getAktivColor());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Circle kor = new Circle(25);
                GridPane.setHalignment(kor, HPos.CENTER);
                GridPane.setValignment(kor, VPos.CENTER);
                GridPane.setRowIndex(kor, i);
                GridPane.setColumnIndex(kor, j);
                gridPane.getChildren().add(kor);
            }
        }

        grafikusVezerlo = new GrafikusVezerlo(gridPane);
        kovetkezoJatekos.setText(grafikusVezerlo.getAktivJatekos() + " következik!");
        kovetkezoJatekos.setTextFill(grafikusVezerlo.getAktivColor());
        GrafikusVezerlo.setLephet(true);
    }

    private void Nyert() {
        try {
            Stage stage;
            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Nyert.fxml"));
            root = loader.load();
            loader.<NyertController>getController().initData(grafikusVezerlo.getAktivJatekos() + " nyert!");
            Scene scene = new Scene(root);
            stage = (Stage) uzenetek.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(JatekTablaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
