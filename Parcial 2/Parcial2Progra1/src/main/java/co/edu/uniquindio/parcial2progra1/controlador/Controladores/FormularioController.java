package co.edu.uniquindio.parcial2progra1.controlador.Controladores;

import co.edu.uniquindio.parcial2progra1.controlador.Launcher;
import co.edu.uniquindio.parcial2progra1.controlador.Modelo.Inmueble;
import co.edu.uniquindio.parcial2progra1.controlador.Modelo.Tipo;
import co.edu.uniquindio.parcial2progra1.controlador.Repositorio.InmuebleRepositorio;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FormularioController {
    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtCiudad;

    @FXML
    private TextField txtNumHabitaciones;

    @FXML
    private TextField txtNumPisos;

    @FXML
    private TextField txtPrecio;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    private InmuebleRepositorio inmuebleRepositorio;
    private MenuController menuController;

    @FXML
    public void initialize() {
        inmuebleRepositorio = InmuebleRepositorio.getInstancia();
    }

    public void setMenuprincipalController(MenuController menuPrincipalController) {
        this.menuController = menuController;
    }

    @FXML
    private void onGuardarInmueble() {
        if (!validarCampos()) {
            return;
        }

        try {
            Tipo tipo =Tipo.valueOf( txtTipo.getText().trim());
            String ciudad = txtCiudad.getText().trim();
            String numHabitaciones = txtNumHabitaciones.getText().trim();
            String numPisos = txtNumPisos.getText().trim();
            Double precio = Double.parseDouble(txtPrecio.getText().trim());


            if (inmuebleRepositorio.buscarPorCiudad(ciudad) != null) {
                mostrarAlerta("Error", "Ya existe un producto con ese código", Alert.AlertType.ERROR);
                return;
            }

            // Crear y guardar el producto
            Inmueble nuevoInmueble = new Inmueble(tipo, ciudad, numHabitaciones, numPisos, precio);
            inmuebleRepositorio.agregarInmueble(nuevoInmueble);

            mostrarAlerta("Éxito", "Producto creado correctamente", Alert.AlertType.INFORMATION);

            // Volver al Menu Principal
            volverAlMenuPrincipal();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio y stock deben ser valores numéricos válidos", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onCancelar() {
        volverAlMenuPrincipal();
    }

    private void volverAlMenuPrincipal() {
        if (menuController != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Launcher.class.getResource("/co/edu/uniquindio/fx10/vista/MenuPrincipal.fxml"));
                Parent menu = loader.load();

                menuController.getPanePrincipal().getChildren().clear();
                menuController.getPanePrincipal().getChildren().add(menu);

            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo volver al menú principal", Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }
    }


    private boolean validarCampos() {
        if (txtTipo.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El Tipo es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        if (txtCiudad.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "La Ciudad es obligatoria", Alert.AlertType.WARNING);
            return false;
        }
        if (txtNumHabitaciones.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El Numero de Habtiaciones es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        if (txtNumPisos.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El Numero de Pisos es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        if (txtPrecio.getText().trim().isEmpty()) {
            mostrarAlerta("Error de validación", "El Precio es obligatorio", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
