package com.example.parcial2progra1.Controladores;

import com.example.parcial2progra1.Launcher;
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
            // Usar ruta relativa desde la clase
            FXMLLoader loader = new FXMLLoader(MenuController.class.getResource("/com/example/parcial2progra1/Dashboard.fxml"));
            Parent dashboard = loader.load();

            DashboardController dashboardController = loader.getController();
            dashboardController.setMenuController(this);
            dashboardController.cargarInmuebles();

            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(dashboard);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar el Dashboard: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (Exception e) {
            mostrarAlerta("Error", "Error inesperado: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void onCrearProducto(ActionEvent event) {
        try {
            // Ruta correcta:
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/com/example/parcial2progra1/Formulario.fxml"));
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

    public void restaurarMenu() {
        try {
            // Ruta correcta:
            FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/com/example/parcial2progra1/Menu.fxml"));
            Parent menu = loader.load();

            panePrincipal.getChildren().clear();
            panePrincipal.getChildren().add(menu);

        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo restaurar el menú principal", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    public Pane getPanePrincipal() {
        return panePrincipal;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
