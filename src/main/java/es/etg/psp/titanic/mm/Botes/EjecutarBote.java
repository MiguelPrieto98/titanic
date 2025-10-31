package es.etg.psp.titanic.mm.Botes;

import java.util.Map;

public class EjecutarBote {

    private static final String ERROR_FALTA_ID = "Se requiere un identificador de bote como argumento.";
    private static final String SEPARADOR_CLAVE_VALOR = "=";
    private static final String SEPARADOR_ENTRADA = ",";

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            System.err.println(ERROR_FALTA_ID);
            System.exit(1);
        }

        String id = args[0];
        Bote bote = new Bote(id);

        Map<String, Integer> pasajeros = bote.generarPasajeros();

        StringBuilder sb = new StringBuilder();
        pasajeros.forEach((k, v) -> sb.append(k).append(SEPARADOR_CLAVE_VALOR).append(v).append(SEPARADOR_ENTRADA));

        System.out.println(sb.toString().replaceAll(SEPARADOR_ENTRADA + "$", ""));
    }
}