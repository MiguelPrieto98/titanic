package es.etg.psp.titanic.mm.Botes;

import java.util.Map;

public class EjecutarBote {
    public static void main(String[] args) throws InterruptedException {
        String id = args[0];
        Bote bote = new Bote(id);
        Map<String,Integer> pasajeros = bote.generarPasajeros();

        StringBuilder sb = new StringBuilder();
        pasajeros.forEach((k,v) -> sb.append(k).append("=").append(v).append(","));
        System.out.println(sb.toString().replaceAll(",$",""));
    }
}
