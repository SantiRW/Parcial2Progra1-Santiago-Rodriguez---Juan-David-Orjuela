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

    exports com.example.parcial2progra1;
    exports com.example.parcial2progra1.Controladores;
    exports com.example.parcial2progra1.Modelo;
    exports com.example.parcial2progra1.Repositorio;

    opens com.example.parcial2progra1 to javafx.fxml;
    opens com.example.parcial2progra1.Controladores to javafx.fxml;
    opens com.example.parcial2progra1.Modelo to javafx.base;
}