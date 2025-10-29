package es.etg.psp.titanic.mm;

import es.etg.psp.titanic.mm.ServicioEmergencia.ServicioEmergencia;

public class Main {
    public static void main(String[] args) throws Exception {
        ServicioEmergencia servicio = new ServicioEmergencia();
        servicio.iniciarSimulacion();
    }
}