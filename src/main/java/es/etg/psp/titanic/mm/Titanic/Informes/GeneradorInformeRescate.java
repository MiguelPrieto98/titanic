package es.etg.psp.titanic.mm.Titanic.Informes;

import java.util.List;
import java.util.Map;

public class GeneradorInformeRescate {

    public static Informes generar(List<Map<String, Integer>> datos) {
        Informes informe = new Informes();

        int totalMujeres = 0;
        int totalVarones = 0;
        int totalNinos = 0;

        for (Map<String, Integer> registro : datos) {
            Integer codigo = registro.get("codigo");
            int mujeres = registro.get("mujeres");
            int varones = registro.get("varones");
            int ninos = registro.get("ninos");

            totalMujeres += mujeres;
            totalVarones += varones;
            totalNinos += ninos;

            String contenido = generarContenido(mujeres, varones, ninos);
            informe.agregarSeccion(new Seccion("Bote " + codigo, contenido));
        }

        String contenidoResumen = generarContenido(totalMujeres, totalVarones, totalNinos);
        informe.agregarSeccion(new Seccion("Total", contenidoResumen));

        return informe;
    }

    private static String generarContenido(int mujeres, int varones, int ninos) {
        int total = mujeres + varones + ninos;
        return String.format(
            "- Total Salvados: %d\n  - Mujeres: %d\n  - Varones: %d\n  - Niños: %d",
            total, mujeres, varones, ninos
        );
    }
}
