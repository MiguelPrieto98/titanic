package es.etg.psp.titanic.mm.Botes;

import java.util.Map;

public interface IBote {
    String getId();

    Map<String, Integer> generarPasajeros() throws InterruptedException;
}
