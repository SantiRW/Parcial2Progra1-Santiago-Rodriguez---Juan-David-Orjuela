package co.edu.uniquindio.parcial2progra1.controlador.Repositorio;

import co.edu.uniquindio.parcial2progra1.controlador.Modelo.Inmueble;
import co.edu.uniquindio.parcial2progra1.controlador.Modelo.Tipo;

import java.util.ArrayList;

public class InmuebleRepositorio {

        private static InmuebleRepositorio instancia;
        private ArrayList<Inmueble> inmuebles;

        private InmuebleRepositorio() {
            inmuebles = new ArrayList<>();
            cargarDatosEjemplo();
        }

        public static InmuebleRepositorio getInstancia() {
            if (instancia == null) {
                instancia = new InmuebleRepositorio();
            }
            return instancia;
        }


        private void cargarDatosEjemplo() {
            inmuebles.add(new Inmueble(Tipo.CASA, "Armenia", "15", "3", 500000000));
        }

        public ArrayList<Inmueble> getInmuebles() {
            return inmuebles;
        }

        public void agregarInmueble(Inmueble inmueble) {
            inmuebles.add(inmueble);
        }

        public boolean eliminarInmueble(Inmueble inmueble) {
            return inmuebles.remove(inmueble);
        }

        public Inmueble buscarPorCiudad(String ciudad) {
            return inmuebles.stream()
                    .filter(p -> p.getCiudad().equals(ciudad))
                    .findFirst()
                    .orElse(null);
        }

        public int getCantidadInmuebles() {
            return inmuebles.size();
        }
    }