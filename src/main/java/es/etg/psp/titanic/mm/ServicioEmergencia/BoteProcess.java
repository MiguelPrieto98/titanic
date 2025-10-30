package es.etg.psp.titanic.mm.ServicioEmergencia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BoteProcess {

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

    private static Map<String, Integer> convertirLineaAHashMap(String linea) {
        Map<String, Integer> pasajeros = new HashMap<>();
        for (String kv : linea.split(",")) {
            String[] partes = kv.split("=");
            pasajeros.put(partes[0], Integer.parseInt(partes[1]));
        }
        return pasajeros;
    }
}
