// CHECKSTYLE:OFF
package grafikusFelulet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JatekTablaController implements Initializable {

    private final Logger logger = LoggerFactory.getLogger(JatekTablaController.class);

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
    private void ujJatekMenuItemAction(ActionEvent event) {
        grafikusVezerlo = new GrafikusVezerlo(gridPane);
        grafikusVezerlo.setLephet(true);
    }

    @FXML
    private void kilepesMenuItemAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void eredmenyekMenuItemAction(ActionEvent event) {
        ObservableList data = FXCollections.observableList(grafikusVezerlo.getEredmenyLista());
        Scene scene = new Scene(new Group());
        Stage stage = new Stage();
        stage.setTitle("Eredmények");
        stage.setWidth(600);
        stage.setHeight(800);

        final Label label = new Label("EredményTábla");
        label.setFont(new Font("Arial", 20));

        TableView table = new TableView();
        table.setMaxSize(500, 600);
        table.setEditable(true);
        table.setItems(data);

        TableColumn kek = new TableColumn("kek");
        kek.setCellValueFactory(new PropertyValueFactory("kekJatekosNeve"));
        TableColumn sarga = new TableColumn("sarga");
        sarga.setCellValueFactory(new PropertyValueFactory("sargaJatekosNeve"));
        TableColumn nyert = new TableColumn("nyert");
        nyert.setCellValueFactory(new PropertyValueFactory("nyeroJatekosNeve"));
        TableColumn lepesszam = new TableColumn("lepesszam");
        lepesszam.setCellValueFactory(new PropertyValueFactory("lepesszam"));

        table.getColumns().addAll(kek, sarga, nyert, lepesszam);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void atnevezesMenuItemAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/JatekosokNevei.fxml"));
            Parent root = loader.load();
            loader.<JatekosokNeveiController>getController().initData(grafikusVezerlo);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Átnevezés");
            stage.show();
            grafikusVezerlo.setLephet(false);
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @FXML
    private void segítsegMenuItemAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/Segitseg.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Segítség");
            stage.show();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @FXML
    public void gridPaneMouseAction(MouseEvent event) {
        if (grafikusVezerlo.isLephet()) {
            for (Node node : gridPane.getChildren()) {
                if (node instanceof Circle) {
                    if (node.getBoundsInParent().contains(event.getX(), event.getY())) {
                        uzenetek.setText(grafikusVezerlo.esemeny((Circle) node));
                        logger.debug("Hiba üzenet: " + uzenetek.getText());
                        break;
                    }
                }
            }

            if (grafikusVezerlo.isNyert()) {
                nyert();
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
        grafikusVezerlo.setLephet(true);
    }

    private void nyert() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Nyert.fxml"));
            Parent root = loader.load();
            loader.<NyertController>getController().initData(grafikusVezerlo.getAktivJatekos() + " nyert!");
            Scene scene = new Scene(root);
            Stage stage = (Stage) uzenetek.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(grafikusVezerlo.getAktivJatekos() + " nyert!");
            stage.show();
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }
}
