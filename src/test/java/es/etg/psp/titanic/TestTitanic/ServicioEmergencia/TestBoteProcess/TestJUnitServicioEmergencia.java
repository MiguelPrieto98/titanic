package es.etg.psp.titanic.TestTitanic.ServicioEmergencia.TestBoteProcess;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;

import org.junit.jupiter.api.Test;

import es.etg.psp.titanic.mm.Titanic.ServicioEmergencia.BoteProcess;

public class TestJUnitServicioEmergencia {

    private static final String LINEA_VALIDA = "mujeres=5,varones=10,ninos=2";
    private static final String LINEA_VACIA = "";
    private static final String LINEA_NULA = null;
    private static final String LINEA_FRAGMENTO_INVALIDO = "mujeres5,varones=10";
    private static final String LINEA_VALOR_NO_NUM = "mujeres=abc,varones=10";

    private static final String MUJERES = "mujeres";
    private static final String VARONES = "varones";
    private static final String NINOS = "ninos";

    @Test
    void convertirLineaAHashMapSalidaValida() {
        Map<String, Integer> pasajeros = BoteProcess.convertirLineaAHashMap(LINEA_VALIDA);
        assertEquals(5, pasajeros.get(MUJERES));
        assertEquals(10, pasajeros.get(VARONES));
        assertEquals(2, pasajeros.get(NINOS));
    }

    @Test
    void convertirLineaAHashMapExcepciones() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> BoteProcess.convertirLineaAHashMap(LINEA_VACIA)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> BoteProcess.convertirLineaAHashMap(LINEA_NULA)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> BoteProcess.convertirLineaAHashMap(LINEA_FRAGMENTO_INVALIDO)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> BoteProcess.convertirLineaAHashMap(LINEA_VALOR_NO_NUM)));
    }

    @Test
    void totalPasajerosCoincide() {
        Map<String, Integer> pasajeros = BoteProcess.convertirLineaAHashMap(LINEA_VALIDA);
        int total = pasajeros.get(MUJERES) + pasajeros.get(VARONES) + pasajeros.get(NINOS);
        assertEquals(17, total, "La suma de pasajeros debe coincidir con el total esperado");
    }
}
