# Manual de Usuario – Simulación de Rescate Titanic

## Indice

- [Descripción general](#descripción-general)
- [Requisitos del sistema](#requisitos-del-sistema)
- [Instalación](#instalación)
- [Uso de la aplicación](#uso-de-la-aplicación)
  - [Ejecutar aplicación](#ejecutar-aplicacion)
  - [Salida esperada](#salida-esperada)
- [Uso individual de un bote](#uso-individual-de-un-bote)
- [Descripción de los informes](#descripción-de-los-informes)
- [Consideraciones](#consideraciones)

## Descripción general

La aplicación simula el rescate de pasajeros del Titanic usando botes. Cada bote transporta un número aleatorio de pasajeros, clasificados como mujeres, varones y niños. La aplicación genera un informe en formato Markdown ``(.md)`` con los datos de cada bote y un resumen total.

## Requisitos del sistema

- Java 11 o superior instalado.

- Sistema operativo con soporte para ejecución de procesos Java.

- Acceso de escritura en la carpeta informes/ para generar el informe Markdown.

## Instalación

1. Clonar o descargar el proyecto.

2. Compilar con Maven o tu IDE:

    ```java
    mvn clean install
    ```

3. La clase principal que inicia la simulación es:

    ```text
    es.etg.psp.titanic.mm.Titanic.ServicioEmergencia.ServicioEmergencia
    ```

## Uso de la aplicación

### Ejecutar aplicacion

Desde la terminal o IDE, ejecutar:

```java
    java -cp target/classes es.etg.psp.titanic.mm.Titanic.ServicioEmergencia.ServicioEmergencia
```

- La aplicación ejecutará 20 botes (ID B00 a B19).

- Cada bote generará pasajeros aleatorios.

- Se espera un pequeño retraso simulado por bote para representar el conteo de pasajeros.

### Salida esperada

- Se genera un archivo Markdown en la carpeta informes/ con el nombre:

    ```java
    InformeRescate<fecha>.md
    ```

    Ejemplo:

    ```java
        informes/InformeRescate01-11-2025_15-30.md
    ```

- Contiene:

    - Número de pasajeros por categoría en cada bote.

    - Resumen total de mujeres, varones y niños rescatados.

## Uso individual de un bote

Si deseas ejecutar un solo bote:

1. Ejecutar:

    ```java
    java -cp target/classes es.etg.psp.titanic.mm.Botes.EjecutarBote <ID_BOTE>
    ```

    Ejemplo:

    ```java
    java -cp target/classes es.etg.psp.titanic.mm.Botes.EjecutarBote B01
    ```

2. Salida en consola:

    ``codigo=1,mujeres=20,varones=30,ninos=10``

## Descripción de los informes

- El informe está en formato Markdown, legible por cualquier editor de texto o visor Markdown.

- Contiene:

   - Encabezado: # SERVICIO DE EMERGENCIAS

   - Fecha y hora de ejecución

   - Secciones por bote:

        ```markdown
        ## Bote 01
        - Total Salvados: 60
            - Mujeres: 20
            - Varones: 30
            - Niños: 10
        ```

    - Resumen total de todos los botes.

## Consideraciones

- La aplicación es una simulación: los números de pasajeros son generados aleatoriamente.

- Cada ejecución puede producir resultados distintos.

- La aplicación está diseñada para ser ampliable: se pueden aumentar el número de botes cambiando NUM_BOTES en ServicioEmergencia.