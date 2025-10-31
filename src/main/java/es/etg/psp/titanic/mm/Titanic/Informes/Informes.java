package es.etg.psp.titanic.mm.Titanic.Informes;
import java.util.ArrayList;
import java.util.List;


public class Informes {
    
        private final List<Seccion> secciones = new ArrayList<>();

        public void agregarSeccion(Seccion seccion){
            secciones.add(seccion);
        }
        public List<Seccion> getSeccions(){
            return  secciones;
        }
}
