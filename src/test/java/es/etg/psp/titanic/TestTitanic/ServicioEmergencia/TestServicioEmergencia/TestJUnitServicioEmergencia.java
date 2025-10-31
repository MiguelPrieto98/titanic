package es.etg.psp.titanic.TestTitanic.ServicioEmergencia.TestServicioEmergencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.etg.psp.titanic.mm.Titanic.ServicioEmergencia.ServicioEmergencia;

public class TestJUnitServicioEmergencia {

    private static final String BOTE_1 = "B01";
    private static final String BOTE_2 = "B02";
    private static final String BOTE_10 = "B10";
    private static final String CARPETA_INFORMES = "informes";
    private static final String PREFIJO_INFORME = "InformeRescate";
    private static final String MENSAJE_ARCHIVO_NO_VACIO = "El archivo generado no debe estar vacío";
    private ServicioEmergencia servicio;
    
    @BeforeEach
    void setUp() {
        servicio = new ServicioEmergencia();
    }

    @Test
    void procesarResultadosOrdenaClaves() {
        Map<String, Map<String, Integer>> entrada = new HashMap<>();
        entrada.put(BOTE_10, Map.of());
        entrada.put(BOTE_2, Map.of());
        entrada.put(BOTE_1, Map.of());

        Map<String, Map<String, Integer>> salida = servicio.procesarResultados(entrada);

        List<String> clavesEsperadas = List.of(BOTE_1, BOTE_2, BOTE_10);
        assertEquals(clavesEsperadas, new ArrayList<>(salida.keySet()));
    }

    @Test
    void exportarInformeListaVaciaArchivoNoVacio() throws Exception {

        Files.createDirectories(Paths.get(CARPETA_INFORMES));

        servicio.exportarInforme(new ArrayList<>());

        var archivo = Files.list(Paths.get(CARPETA_INFORMES))
                .filter(p -> p.getFileName().toString().startsWith(PREFIJO_INFORME))
                .findFirst()
                .orElseThrow(() -> new AssertionError("No se generó el informe"));

        assertTrue(Files.size(archivo) > 0, MENSAJE_ARCHIVO_NO_VACIO);
        Files.deleteIfExists(archivo);
    }

    @Test
    void exportarInformeConNullLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> servicio.exportarInforme(null));
    }
}
