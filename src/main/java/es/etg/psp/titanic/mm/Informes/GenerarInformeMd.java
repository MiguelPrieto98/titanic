package es.etg.psp.titanic.mm.Informes;

import java.util.List;

public class GenerarInformeMd implements  Exportador{

    @Override
    public String exportar(Informes informe) {
        StringBuilder salida = new StringBuilder();
        List<Seccion> secciones = informe.getSeccions();  // Asegura tipo correcto

        for (Seccion seccion : secciones) {
            salida.append("## ").append(seccion.getTiitulo()).append("\n\n");
            salida.append(seccion.getContenido()).append("\n\n");
        }

        return salida.toString();
    }
    
}
