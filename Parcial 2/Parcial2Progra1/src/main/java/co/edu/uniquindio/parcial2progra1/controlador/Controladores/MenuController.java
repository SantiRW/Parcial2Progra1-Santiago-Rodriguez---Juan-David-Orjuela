package co.edu.uniquindio.parcial2progra1.controlador.Controladores;

import co.edu.uniquindio.parcial2progra1.controlador.Launcher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {
    @FXML
    private Pane panePrincipal;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnCrearProducto;

    @FXML
    private Label lblMenu;

    @FXML
    public void onInfo(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/co/edu/uniquindio/parcial2progra1/vista/Dashboard.fxml"));
            Parent dashboard = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.setMenuController(this);
            dashboardController.cargarInmuebles();

            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(dashboard);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar el Dashboard: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void onCrearProducto(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/co/edu/uniquindio/parcial2progra1/vista/Formulario.fxml"));
            Parent formulario = loader.load();


            FormularioController formularioController = loader.getController();
            formularioController.setMenuController(this);

            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(formulario);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar el Formulario: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    public Pane getPanePrincipal() {
        return panePrincipal;
    }

    public void restaurarMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/co/edu/uniquindio/parcial2progra1/vista/Menu.fxml"));
            Parent menu = loader.load();

            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(menu);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo restaurar el menú principal", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
