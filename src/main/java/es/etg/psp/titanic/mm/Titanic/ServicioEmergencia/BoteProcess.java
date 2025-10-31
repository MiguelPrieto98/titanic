package es.etg.psp.titanic.mm.Titanic.ServicioEmergencia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BoteProcess {

    private static final String LINEA_NULA_O_VACIA = "La línea no puede ser nula ni vacía";
    private static final String FORMATO_INVALIDO = "Formato inválido en el fragmento: '%s'. Debe ser clave=valor";
    private static final String VALOR_NO_NUMERICO = "Valor no numérico para la clave '%s': %s";
    private static final String SEPARADOR_CLAVE_VALOR = "=";
    private static final String SEPARADOR_ENTRADA = ",";

    public static Map<String, Integer> obtenerPasajeros(String id) throws Exception {
        ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-cp",
                "target/classes",
                "es.etg.psp.titanic.mm.Botes.EjecutarBote",
                id);

        Process process = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String linea = reader.readLine();
        process.waitFor();

        return convertirLineaAHashMap(linea);
    }

    public static Map<String, Integer> convertirLineaAHashMap(String linea) {
        if (linea == null || linea.trim().isEmpty()) {
            throw new IllegalArgumentException(LINEA_NULA_O_VACIA);
        }

        Map<String, Integer> pasajeros = new HashMap<>();
        for (String kv : linea.split(SEPARADOR_ENTRADA)) { 
            String[] partes = kv.split(SEPARADOR_CLAVE_VALOR); 

            if (partes.length != 2) {
                throw new IllegalArgumentException(String.format(FORMATO_INVALIDO, kv));
            }

            try {
                pasajeros.put(partes[0], Integer.parseInt(partes[1]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(String.format(VALOR_NO_NUMERICO, partes[0], partes[1]), e);
            }
        }

        return pasajeros;
    }
}
