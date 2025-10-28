package es.etg.psp.titanic.mm.Informes;


import java.util.List;
import java.util.Map;

public class GeneradorInformeRescate {

    public static Informes generar(List<Map<String, Integer>> datos) {
        Informes informe = new Informes();

        for (Map<String, Integer> registro : datos) {
            Integer codigo = registro.get("codigo");
            int mujeres = registro.get("mujeres");
            int varones = registro.get("varones");
            int ninos = registro.get("ninos");
            int total = mujeres + varones + ninos;

            String contenido = "- Total Salvados: " + total + "\n"
                             + "  - Mujeres: " + mujeres + "\n"
                             + "  - Varones: " + varones + "\n"
                             + "  - Niños: " + ninos;

            informe.agregarSeccion(new Seccion("Bote " + codigo, contenido));
        }

        
        
        return informe;
    }
}