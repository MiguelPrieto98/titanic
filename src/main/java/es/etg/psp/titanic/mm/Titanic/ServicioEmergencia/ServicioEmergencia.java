package es.etg.psp.titanic.mm.Titanic.ServicioEmergencia;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import es.etg.psp.titanic.mm.Titanic.Informes.GeneradorInformeRescate;
import es.etg.psp.titanic.mm.Titanic.Informes.GenerarInformeMd;
import es.etg.psp.titanic.mm.Titanic.Informes.Informes;

public class ServicioEmergencia implements IServicioEmergencia {

    private static final int NUM_BOTES = 20;
    private static final String FORMATO_ID = "B%02d";
    private static final String FORMATO_FECHA = "dd-MM-yyyy_HH:mm";
    private static final String TITULO_INFORME = "InformeRescate";
    private static final String EXTENSION_INFORME = ".md";

    @Override
    public void iniciarSimulacion() throws Exception {
        Map<String, Map<String, Integer>> resultados = ejecutarBotes();
        resultados = procesarResultados(resultados);
        exportarInforme(new ArrayList<>(resultados.values()));
    }

    @Override
    public Map<String, Map<String, Integer>> ejecutarBotes() throws Exception {
        Map<String, Map<String, Integer>> resultados = new HashMap<>();

        for (int i = 0; i < NUM_BOTES; i++) {
            String id = String.format(FORMATO_ID, i);
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

        String nombreArchivo = generarNombreArchivo();
        Files.write(Paths.get(nombreArchivo), markdown.getBytes());
    }

    private String generarNombreArchivo() {
        LocalDateTime fecha = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return TITULO_INFORME + fecha.format(formato) + EXTENSION_INFORME;
    }

    public static void main(String[] args) throws Exception {
        new ServicioEmergencia().iniciarSimulacion();
    }
}
