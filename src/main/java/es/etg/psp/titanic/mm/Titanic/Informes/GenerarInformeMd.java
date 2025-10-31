package es.etg.psp.titanic.mm.Titanic.Informes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GenerarInformeMd {

    private static final String CABECERA = "# SERVICIO DE EMERGENCIAS\n\n";
    private static final String FORMATO_FECHA = "dd/MM/yyyy 'a las' HH:mm:ss";
    private static final String TEXTO_EJECUCION = "Ejecución realizada el día %s\n\n";
    private static final String FORMATO_SECCION = "## %s\n\n%s\n\n";

    public String exportar(Informes informe) {
        StringBuilder sb = new StringBuilder();

        sb.append(CABECERA);

        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        sb.append(String.format(TEXTO_EJECUCION, ahora.format(formato)));

        for (Seccion s : informe.getSeccions()) {
            sb.append(String.format(FORMATO_SECCION, s.getTiitulo(), s.getContenido()));
        }

        return sb.toString();
    }
}