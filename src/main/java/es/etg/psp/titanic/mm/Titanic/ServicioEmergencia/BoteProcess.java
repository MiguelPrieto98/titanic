package es.etg.psp.titanic.mm.Titanic.ServicioEmergencia;

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

    public static Map<String, Integer> convertirLineaAHashMap(String linea) {
    if (linea == null || linea.trim().isEmpty()) {
        throw new IllegalArgumentException("La línea no puede ser nula ni vacía");
    }

    Map<String, Integer> pasajeros = new HashMap<>();

    for (String kv : linea.split(",")) {
        String[] partes = kv.split("=");

        if (partes.length != 2) {
            throw new IllegalArgumentException(
                "Formato inválido en el fragmento: '" + kv + "'. Debe ser clave=valor");
        }

        try {
            pasajeros.put(partes[0], Integer.parseInt(partes[1]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Valor no numérico para la clave '" + partes[0] + "': " + partes[1], e);
        }
    }

    return pasajeros;
}
}
