package es.etg.psp.titanic.TestTitanic.ServicioEmergencia.TestServicioEmergencia;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import es.etg.psp.titanic.mm.ServicioEmergencia.ServicioEmergencia;

public class TestJUnitServicioEmergencia {
    
@Test
void procesarResultadosOrdenaClaves() {
    ServicioEmergencia servicio = new ServicioEmergencia();
    Map<String, Map<String,Integer>> entrada = new HashMap<>();
    entrada.put("B10", Map.of());
    entrada.put("B02", Map.of());
    entrada.put("B01", Map.of());

    Map<String, Map<String,Integer>> salida = servicio.procesarResultados(entrada);
    assertEquals(List.of("B01","B02","B10"), new ArrayList<>(salida.keySet()));
}

@Test
void exportarInformeListaVaciaCreaArchivo() throws Exception {
    ServicioEmergencia servicio = new ServicioEmergencia();
    servicio.exportarInforme(new ArrayList<>());

    boolean existe = Files.list(Paths.get("."))
        .anyMatch(p -> p.getFileName().toString().startsWith("InformeRescate "));
    
    assertTrue(existe);
}

}
