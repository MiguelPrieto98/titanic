package es.etg.psp.titanic.mm.Titanic.ServicioEmergencia;

import java.util.List;
import java.util.Map;

public interface IServicioEmergencia {
    void iniciarProceso() throws Exception;
    Map<String, Map<String, Integer>> ejecutarBotes() throws Exception;
    Map<String, Map<String, Integer>> procesarResultados(Map<String, Map<String, Integer>> resultados);
    void exportarInforme(List<Map<String, Integer>> datos) throws Exception;
}