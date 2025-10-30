package es.etg.psp.titanic.TestTitanic.ServicioEmergencia;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.etg.psp.titanic.mm.ServicioEmergencia.BoteProcess;

public class TestJUnitServicioEmergencia {

    private String lineaValida;

    @BeforeEach
    void setUp() {
        lineaValida = "mujeres=5,varones=10,ninos=2";
    }

    @Test
    void convertirLineaAHashMapSalidaValida() {
        Map<String, Integer> pasajeros = BoteProcess.convertirLineaAHashMap(lineaValida);
        assertEquals(5, pasajeros.get("mujeres"));
        assertEquals(10, pasajeros.get("varones"));
        assertEquals(2, pasajeros.get("ninos"));
    }

    @Test
    void convertirLineaAHashMapExcepciones() {
        assertAll("Excepciones de convertirLineaAHashMap",
                () -> assertThrows(IllegalArgumentException.class,
                        () -> BoteProcess.convertirLineaAHashMap(""),
                        "Debe lanzar excepción para línea vacía"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> BoteProcess.convertirLineaAHashMap(null),
                        "Debe lanzar excepción para línea nula"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> BoteProcess.convertirLineaAHashMap("mujeres5,varones=10"),
                        "Debe lanzar excepción para fragmento sin '='"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> BoteProcess.convertirLineaAHashMap("mujeres=abc,varones=10"),
                        "Debe lanzar excepción para valor no numérico"));
    }

    @Test
    void totalPasajerosCoincide() {
        Map<String, Integer> pasajeros = BoteProcess.convertirLineaAHashMap("mujeres=5,varones=10,ninos=2");
        int total = pasajeros.get("mujeres") + pasajeros.get("varones") + pasajeros.get("ninos");
        assertEquals(17, total, "La suma de pasajeros debe coincidir con el total esperado");
    }

}
