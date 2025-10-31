package es.etg.psp.titanic.TestBote;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import es.etg.psp.titanic.mm.Botes.Bote;

public class TestJUnitBotes {
    private static final String MUJERES = "mujeres";
    private static final String VARONES = "varones";
    private static final String NINOS = "ninos";
    private static final String MSG_TOTAL = "Total debe estar entre 1 y 100";
    private static final String MSG_MUJERES = "Mujeres debe estar entre 0 y total";
    private static final String MSG_VARONES = "Varones debe estar entre 0 y total - mujeres";
    private static final String MSG_NINOS = "Niños no puede ser negativo";
    private static final String MSG_SUMATORIA = "La suma de pasajeros debe coincidir con el total";

    @Test
    void generarPasajerosDebeGenerarDistribucionValida() throws InterruptedException {
        Bote bote = new Bote("B1");

        Map<String, Integer> pasajeros = bote.generarPasajeros();

        int mujeres = pasajeros.get(MUJERES);
        int varones = pasajeros.get(VARONES);
        int ninos = pasajeros.get(NINOS);
        int total = mujeres + varones + ninos;

        assertAll(
                () -> assertTrue(total >= 1 && total <= 100, MSG_TOTAL),
                () -> assertTrue(mujeres >= 0 && mujeres <= total, MSG_MUJERES),
                () -> assertTrue(varones >= 0 && varones <= total - mujeres, MSG_VARONES),
                () -> assertTrue(ninos >= 0, MSG_NINOS),
                () -> assertEquals(total, mujeres + varones + ninos, MSG_SUMATORIA));
    }
}
