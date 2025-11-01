# Titanic
## Miguel Ángel Prieto, Manuel Pavon 
## Índice

### 1. Análisis del problema

### 2. Diseño de la solución

### 3. Manual de usuario

### 4. Elementos destacables del desarrollo

### 5. Problemas encontrados

### 6. Conclusiones individuales

### 7. Desarrollo

### 8. Ejecución de las pruebas


## Analisis del Problema 
- Hay que implementar una aplicacion  que gestione los botes de emergencia teniendo en cuenta que los botes son 20, el tiempo de despliege de botes es finito y el conteo se realiza una vez el bote ya ha sido soltado, los botes tienes un minimo de 1 pasajero y un maximo de 100 divividos en hombres mujeres y niños.
- Para calcular el numero de pasajeros de cada bote se genera un numero aleatorio de 1 a 100 y se reparte entre mujeres hombre y niños y esto se manda al informe.
- el servicio de emergencia generar un informe apartir de la informacion recibida de cada bote y generando un informe individual de cada bote con el conteo total y el desglose de hombre mujeres y niños y al final un total general y un desglose del total por hombre mujeres y niños.
## Diseño de la solucion

## Manual de usuario

## Elemantos destacables del desarrollo

## Botes

El paquete Botes está estructurado con una interfaz IBote y dos clases principales Bote y EjecutarBote.

- La interfaz **IBote** define el contrato que deben seguir todas las clases que implementen un bote, asegurando consistencia y modularidad. Tiene los siguientes métodos:

```java
    String getId();
    Map<String, Integer> generarPasajeros() throws InterruptedException;
    void simularTiempoConteo() throws InterruptedException;
```
Esto permite que se puedan crear diferentes tipos de botes sin modificar el resto del sistema.

- **Bote** es la clase que representa un bote individual y se encarga de generar los pasajeros y simular el tiempo de conteo.

    - **Atributos principales**:

        - **id**: identificador del bote.

        - **MAX_PASAJEROS**: número máximo de pasajeros posibles.

        - **TIEMPO_MINIMO y TIEMPO_MAXIMO_AMPLIABLE**: para simular retrasos en el conteo.

    - **Método principal**:
        ```java
            generarPasajeros(): Map<String, Integer>
        ```
        Genera un número aleatorio de pasajeros, distribuidos en mujeres, varones y niños, asegurando consistencia.
    - **Método auxiliar**:

        ```java
        simularTiempoConteo(): void
        ```
        Introduce un retraso aleatorio para simular el tiempo real de conteo de pasajeros.

- **EjecutarBote** es la clase que permite ejecutar un bote de manera individual desde línea de comandos.

    - Recibe como argumento el ID del bote.

    - Genera la salida de pasajeros en formato clave=valor, lo que facilita su posterior procesamiento.
    - Salida típica:
      ```java
      codigo=1,mujeres=20,varones=30,ninos=10
      ```
    Esta estructura permite un código modular y extensible, donde se pueden añadir nuevos tipos de botes o modificar la generación de pasajeros sin afectar el resto del sistema.
    
### Informes 
El paquete informes esta escructurado con una interfaz **Exportador**, dos objetos **Informe y Seccion** y dos metodos **Generador informeRescate y ExportadorMarkown**.
- La Intefaz **Exportador** define el contrato que deben seguir todas la clases que quiran exportar un informe especifico a un formato especifico, esto permite implementa multiples formatos de exportacion sin modificar las clases principales del sistema. Tiene un metodo:
``` java
        exportar(informe informe):String
```

- **Seccion** es una clase objeto que representa el contenido del informe y sus diferentes seciones.
    - Atributos:
    - titulo: String
    - contenido: String (puede estar en Markdown, HTML, etc.)

    Devuelve el contenido del informe en el formato deseado.
- **Informe** es una  clases objeto representa un informe completo sin darle formatos ni fuentes de datos externos. Tienen sus atributos y 2 metodos:
``` java
    agregarSeccion(Seccion seccion)
    getSecciones():List<Seccion>
```
Añaden una sección al informe y devuelve todas las seciones agregada.

- **GeneradorInformeRescate** se encarga de crear un objeto **Infrome** a partir de datos externos tiene un metodo principal que Recibe datos y devuelve un informe estructurado.
```` java 
- generar(List datos): Informe
````
- **ExportadorMarkdown** una vez que se ha generado el informe, esta clase la trasforma en markdown e implemente la interfaz exportador y posee un metodo:
```java
exportar(Informe informe):String
```
Esta estructura permite un facil mantenimiento y la posiblidad de añadir nuevos metodos que generer nuevos tipos de infromes como pfd word etc.

## Problemas encontrados
Durante la creación de la aplicación de simulación de rescate del Titanic, se identificaron varios problemas y desafíos técnicos. A continuación se describen con sus causas, soluciones adoptadas y lecciones aprendidas.

### 1. Generación aleatoria de pasajeros

**Problema**:

- Se necesitaba generar un número aleatorio de pasajeros para cada bote, distribuidos en mujeres, varones y niños, garantizando que la suma coincidiera con el total.

**Solución adoptada**:

- Se generó primero el número total de pasajeros, luego el número de mujeres, varones y finalmente los niños se calcularon como total - mujeres - varones.

- Esto asegura coherencia en todos los registros.

### 2. Comunicación con procesos externos

**Problema**:

Cada bote se ejecuta como un proceso independiente de Java (Process) desde BoteProcess.

**Desafío**:

- Capturar correctamente la salida del proceso y manejar errores de ejecución.

- Evitar que un error en un solo bote detuviera la simulación completa.

**Solución adoptada**:

- Se implementó lectura de InputStream y ErrorStream.

- Se generaron mensajes de error claros y excepciones manejadas para cada fallo en la ejecución de un bote.

## 3. Generación de informes

**Problema**:

- Los datos generados por los botes debían consolidarse en un informe legible, en Markdown, con secciones por bote y resumen total.

**Desafío**:

- Asegurar que la información fuera clara, coherente y correctamente formateada.

- Permitir ampliaciones futuras (otros formatos de informe).

**Solución adoptada**:

- Se creó la clase Informes que contiene secciones (Seccion).

- Se implementó GeneradorInformeRescate para consolidar los datos y GenerarInformeMd para exportar en Markdown.

- Se definió un formato consistente y profesional, con título, fecha de ejecución y detalle por bote.



## Desarrollo

## Ejecucion de pruebas ..