package es.etg.psp.titanic.TestBote;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import es.etg.psp.titanic.mm.Botes.Bote;

public class TestJUnitBotes {
    @Test
    void testConstructorConVariasEntradas() {
        Bote boteValido = new Bote("B1");
        Bote boteNulo = new Bote(null);
        Bote boteVacio = new Bote("   ");

        assertAll("Validaciones de constructor",
                () -> assertEquals("B1", boteValido.getId(), "ID válido debe mantenerse"),
                () -> assertNull(boteNulo.getId(), "ID nulo debe mantenerse como null"),
                () -> assertEquals("   ", boteVacio.getId(), "ID vacío debe mantenerse tal cual"));
    }

@Test
void generarPasajerosDebeGenerarDistribucionValida() throws InterruptedException {
    Bote bote = new Bote("B1");

    Map<String, Integer> pasajeros = bote.generarPasajeros();

    int mujeres = pasajeros.get("mujeres");
    int varones = pasajeros.get("varones");
    int ninos   = pasajeros.get("ninos");
    int total   = mujeres + varones + ninos;

    pasajeros.put("Total", total); 

    assertTrue(total >= 1 && total <= 100, "Total debe estar entre 1 y 100");
    assertTrue(mujeres >= 0 && mujeres <= total, "Mujeres debe estar entre 0 y total");
    assertTrue(varones >= 0 && varones <= total - mujeres, "Varones debe estar entre 0 y total - mujeres");
    assertTrue(ninos >= 0, "Niños no puede ser negativo");

    assertEquals(total, mujeres + varones + ninos, "La suma de pasajeros debe coincidir con el total");
}

}
