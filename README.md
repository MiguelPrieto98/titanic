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

## Conclusiones individuales

## Desarrollo

## Ejecucion de pruebas ..