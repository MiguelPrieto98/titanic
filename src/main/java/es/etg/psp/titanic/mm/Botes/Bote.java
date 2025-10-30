package es.etg.psp.titanic.mm.Botes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bote implements IBote {
    private String id;
    private Random random = new Random();

    public Bote(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Map<String, Integer> generarPasajeros() throws InterruptedException {
        int total = random.nextInt(100) + 1;
        int mujeres = random.nextInt(total + 1);
        int varones = random.nextInt(total - mujeres + 1);
        int ninos = total - mujeres - varones;

        Map<String, Integer> pasajeros = new HashMap<>();
        pasajeros.put("Total", total);
        pasajeros.put("Mujeres", mujeres);
        pasajeros.put("Varones", varones);
        pasajeros.put("Niños", ninos);

        simularTiempoConteo();

        return pasajeros;
    }

    @Override
    public void simularTiempoConteo() throws InterruptedException {
        int delay = 2000 + random.nextInt(4000); // 2 a 6 segundos
        Thread.sleep(delay);
    }

}
