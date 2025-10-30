package es.etg.psp.titanic.mm.ServicioEmergencia;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import es.etg.psp.titanic.mm.Informes.GeneradorInformeRescate;
import es.etg.psp.titanic.mm.Informes.GenerarInformeMd;
import es.etg.psp.titanic.mm.Informes.Informes;

public class ServicioEmergencia implements IServicioEmergencia {

    @Override
    public void iniciarSimulacion() throws Exception {
        Map<String, Map<String, Integer>> resultados = ejecutarBotes();
        resultados = procesarResultados(resultados);
        exportarInforme(new ArrayList<>(resultados.values()));
    }

    @Override
    public Map<String, Map<String, Integer>> ejecutarBotes() throws Exception {
        Map<String, Map<String, Integer>> resultados = new HashMap<>();

        final int NUM_BOTES = 20;
        for (int i = 0; i < NUM_BOTES; i++) {
            String id = String.format("B%02d", i);
            resultados.put(id, BoteProcess.obtenerPasajeros(id));
        }
        return resultados;
    }

    @Override
    public Map<String, Map<String, Integer>> procesarResultados(Map<String, Map<String, Integer>> resultados) {
        return new TreeMap<>(resultados);
    }

    @Override
    public void exportarInforme(List<Map<String, Integer>> datos) throws Exception {
        Informes informe = GeneradorInformeRescate.generar(datos);
        String markdown = new GenerarInformeMd().exportar(informe);

        LocalDateTime fecha = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm");
        String fechaFormateada = fecha.format(formato);

        String nombreArchivo = "InformeRescate " + fechaFormateada + ".md";

        Files.write(Paths.get(nombreArchivo), markdown.getBytes());
    }

    public static void main(String[] args) throws Exception {
        new ServicioEmergencia().iniciarSimulacion();
    }
}
