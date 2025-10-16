package co.edu.uniquindio.parcial2progra1.controlador.Controladores;

import co.edu.uniquindio.parcial2progra1.controlador.Launcher;
import co.edu.uniquindio.parcial2progra1.controlador.Modelo.Inmueble;
import co.edu.uniquindio.parcial2progra1.controlador.Modelo.Tipo;
import co.edu.uniquindio.parcial2progra1.controlador.Repositorio.InmuebleRepositorio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DashboardController {
    @FXML
    private VBox contenedorPrincipal;

    @FXML
    private Label lblTitulo;

    @FXML
    private TableView<Inmueble> tablaInmuebles;

    @FXML
    private TableColumn<Inmueble, Tipo> colTipo;

    @FXML
    private TableColumn<Inmueble, String> colCiudad;

    @FXML
    private TableColumn<Inmueble, String> colNumHabitaciones;

    @FXML
    private TableColumn<Inmueble, String> colNumPisos;

    @FXML
    private TableColumn<Inmueble, Double> colPrecio;

    @FXML
    private Button btnCrearInmueble;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRegresar;

    private InmuebleRepositorio inmuebleRepositorio;
    private ObservableList<Inmueble> listaInmuebles;
    private MenuController menuController;

    @FXML
    public void initialize() {
        inmuebleRepositorio = InmuebleRepositorio.getInstancia();

        // Configurar las columnas de la tabla
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        colNumHabitaciones.setCellValueFactory(new PropertyValueFactory<>("numero de habitaciones"));
        colNumPisos.setCellValueFactory(new PropertyValueFactory<>("numeros de pisos"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Formatear la columna de precio
        colPrecio.setCellFactory(column -> new TableCell<Inmueble, Double>() {
            @Override
            protected void updateItem(Double precio, boolean empty) {
                super.updateItem(precio, empty);
                if (empty || precio == null) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", precio));
                }
            }
        });

        // Cargar los productos
        cargarInmuebles();
    }

    public void setMenuPrincipalController(MenuController menuPrincipalController) {
        this.menuController = menuPrincipalController;
    }

    public void cargarInmuebles() {
        listaInmuebles = FXCollections.observableArrayList(inmuebleRepositorio.getInmuebles());
        tablaInmuebles.setItems(listaInmuebles);
    }

    @FXML
    private void onEliminarInmueble() {
        Inmueble inmuebleSeleccionado = tablaInmuebles.getSelectionModel().getSelectedItem();

        if (inmuebleSeleccionado == null) {
            mostrarAlerta("Advertencia", "Por favor seleccione un producto para eliminar", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar el inmueble?");
        confirmacion.setContentText("Inmueble: " + inmuebleSeleccionado.getCiudad());

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                inmuebleRepositorio.eliminarInmueble(inmuebleSeleccionado);
                cargarInmuebles();
                mostrarAlerta("Éxito", "Producto eliminado correctamente", Alert.AlertType.INFORMATION);
            }
        });
    }

    @FXML
    private void onRegresar() {
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

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
