package es.etg.psp.titanic.mm.Informes;

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
            int total = mujeres + varones + ninos;

            totalMujeres += mujeres;
            totalVarones += varones;
            totalNinos += ninos;

            String contenido = "- Total Salvados: " + total + "\n"
                             + "  - Mujeres: " + mujeres + "\n"
                             + "  - Varones: " + varones + "\n"
                             + "  - Niños: " + ninos;

            informe.agregarSeccion(new Seccion("Bote " + codigo, contenido));
        }

        int totalSalvados = totalMujeres + totalVarones + totalNinos;

        String contenidoResumen = "- Total Salvados: " + totalSalvados + "\n"
                                + "  - Mujeres: " + totalMujeres + "\n"
                                + "  - Varones: " + totalVarones + "\n"
                                + "  - Niños: " + totalNinos;

        Seccion resumen = new Seccion("Total", contenidoResumen);
        informe.agregarSeccion(resumen);

        return informe;
    }
}