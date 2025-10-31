package es.etg.psp.titanic.mm.Botes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bote implements IBote {

    private static final int MAX_PASAJEROS = 100;
    private static final int TIEMPO_MINIMO = 2000;
    private static final int TIEMPO_MAXIMO_AMPLIABLE = 4000;
    private static final String CODIGO = "codigo";
    private static final String MUJERES = "mujeres";
    private static final String VARONES = "varones";
    private static final String NINOS = "ninos";

    private final String id;
    private final Random random = new Random();

    public Bote(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Map<String, Integer> generarPasajeros() throws InterruptedException {
        int total = random.nextInt(MAX_PASAJEROS) + 1;
        int mujeres = random.nextInt(total + 1);
        int varones = random.nextInt(total - mujeres + 1);
        int ninos = total - mujeres - varones;

        Map<String, Integer> pasajeros = new HashMap<>();
        pasajeros.put(CODIGO, Integer.parseInt(id.replaceAll("\\D", "")));
        pasajeros.put(MUJERES, mujeres);
        pasajeros.put(VARONES, varones);
        pasajeros.put(NINOS, ninos);

        simularTiempoConteo();

        return pasajeros;
    }

    @Override
    public void simularTiempoConteo() throws InterruptedException {
        int delay = TIEMPO_MINIMO + random.nextInt(TIEMPO_MAXIMO_AMPLIABLE);
        Thread.sleep(delay);
    }
}
