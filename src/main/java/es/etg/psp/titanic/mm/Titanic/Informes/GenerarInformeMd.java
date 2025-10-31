package es.etg.psp.titanic.mm.Titanic.Informes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenerarInformeMd {

    // Constantes de formato y texto
    private static final String CABECERA = "# SERVICIO DE EMERGENCIAS\n\n";
    private static final String FORMATO_FECHA = "dd/MM/yyyy 'a las' HH:mm:ss";
    private static final String TEXTO_EJECUCION = "Ejecución realizada el día %s\n\n";
    private static final String FORMATO_SECCION = "## %s\n\n%s\n\n";

    public String exportar(Informes informe) {
        StringBuilder sb = new StringBuilder();

        // Cabecera fija
        sb.append(CABECERA);

        // Fecha y hora actual
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        sb.append(String.format(TEXTO_EJECUCION, ahora.format(formato)));

        // Secciones del informe
        for (Seccion s : informe.getSeccions()) {
            sb.append(String.format(FORMATO_SECCION, s.getTiitulo(), s.getContenido()));
        }

        return sb.toString();
    }
}