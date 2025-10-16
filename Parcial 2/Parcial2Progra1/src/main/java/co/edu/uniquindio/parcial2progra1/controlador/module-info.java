module com.example.parcial2progra1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires javafx.graphics;
    requires javafx.base;

    opens com.example.parcial2progra1 to javafx.fxml;
    exports com.example.parcial2progra1;
    exports com.example.parcial2progra1.Controlador;
    opens com.example.parcial2progra1.Controlador to javafx.fxml;
    exports co.edu.uniquindio.parcial2progra1.controlador;
    opens co.edu.uniquindio.parcial2progra1.controlador to javafx.fxml;
    exports co.edu.uniquindio.parcial2progra1.controlador.Controladores;
    opens co.edu.uniquindio.parcial2progra1.controlador.Controladores to javafx.fxml;
}