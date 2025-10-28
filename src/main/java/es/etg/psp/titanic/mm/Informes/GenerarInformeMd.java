package es.etg.psp.titanic.mm.Informes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenerarInformeMd {

    public String exportar(Informes informe) {
        StringBuilder sb = new StringBuilder();

        // Cabecera fija
        sb.append("# SERVICIO DE EMERGENCIAS\n\n");

        // Fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' HH:mm:ss");
        sb.append("Ejecución realizada el día ").append(ahora.format(formato)).append("\n\n");

        // Secciones del informe
        for (Seccion s : informe.getSeccions()) {
            sb.append("## ").append(s.getTiitulo()).append("\n\n");
            sb.append(s.getContenido()).append("\n\n");
        }

        return sb.toString();
    }
}