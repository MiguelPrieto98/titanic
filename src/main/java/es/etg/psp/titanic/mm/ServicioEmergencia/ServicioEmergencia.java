package es.etg.psp.titanic.mm.ServicioEmergencia;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.etg.psp.titanic.mm.Informes.GeneradorInformeRescate;
import es.etg.psp.titanic.mm.Informes.GenerarInformeMd;
import es.etg.psp.titanic.mm.Informes.Informes;

public class ServicioEmergencia {
    public void iniciarSimulacion() throws Exception {
  Map<String, Map<String, Integer>> resultados = ejecutarBotes();

        List<Map<String, Integer>> listaResultados = new ArrayList<>(resultados.values());

        Informes informe = GeneradorInformeRescate.generar(listaResultados);

        // 4️⃣ Exportar a Markdown
        GenerarInformeMd exportador = new GenerarInformeMd();
        String markdown = exportador.exportar(informe);
        Files.write(Paths.get("InformeRescate.md"), markdown.getBytes());
    }

    private Map<String, Map<String, Integer>> ejecutarBotes() throws Exception {
        Map<String, Map<String, Integer>> resultados = new HashMap<>();

        final int num_botes = 20;
        for (int i = 0; i < num_botes; i++) {
            String id = String.format("B%02d", i);

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

            Map<String, Integer> pasajeros = new HashMap<>();
            for (String kv : linea.split(",")) {
                String[] partes = kv.split("=");
                pasajeros.put(partes[0], Integer.parseInt(partes[1]));
            }

            resultados.put(id, pasajeros);
        }

        return resultados;
    }

    public static void main(String[] args) throws Exception {
        ServicioEmergencia servicio = new ServicioEmergencia();
        servicio.iniciarSimulacion();
    }
}
