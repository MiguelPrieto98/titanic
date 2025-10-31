package es.etg.psp.titanic.mm.Titanic.Informes;

public class Seccion {
    private final String titulo;
    private final String contenido;

    public Seccion(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public String getTiitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }
}
