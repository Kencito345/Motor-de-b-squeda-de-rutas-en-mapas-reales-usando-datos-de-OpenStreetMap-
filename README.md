# Motor-de-b-squeda-de-rutas-en-mapas-reales-usando-datos-de-OpenStreetMap-
estudiantes kenderth Muñoz 31782889 TPIII 
# Enrutador Avanzado - OpenStreetMap

Aplicación de escritorio en Java que calcula y visualiza la ruta óptima entre dos puntos geográficos, utilizando el algoritmo de Dijkstra y renderizando mapas interactivos en tiempo real.

## 📋 Requisitos Previos

Para compilar y ejecutar este proyecto, necesitarás tener instalado:
* **Java Development Kit (JDK):** Versión 8 o superior.
* **Entorno de Desarrollo (IDE):** IntelliJ IDEA, Eclipse, NetBeans o VS Code.
* **Librería Externa:** `JMapViewer` (estrictamente necesaria para renderizar el panel del mapa).

## 🛠️ Instalación y Configuración

El proyecto requiere la configuración de la librería de OpenStreetMap. Puedes hacerlo de dos formas según la estructura de tu proyecto:

### Opción 1: Configuración Manual (Archivos .jar)
1. Clona o descarga el código fuente y ábrelo en tu IDE.
2. Descarga el archivo `JMapViewer.jar` desde su repositorio oficial.
3. Añade el archivo `.jar` al *Build Path* (Ruta de construcción) de tu proyecto:
    * **En Eclipse:** Clic derecho en el proyecto -> `Build Path` -> `Configure Build Path` -> `Libraries` -> `Add External JARs`.
    * **En IntelliJ IDEA:** `File` -> `Project Structure` -> `Modules` -> `Dependencies` -> `+` -> `JARs or directories`.

### Opción 2: Configuración con Maven
Si configuraste tu proyecto utilizando Maven, no necesitas descargar el `.jar` manualmente. Simplemente agrega la siguiente dependencia en tu archivo `pom.xml`:

```xml
<dependency>
    <groupId>org.openstreetmap.jmapviewer</groupId>
    <artifactId>jmapviewer</artifactId>
    <version>2.19</version> </dependency>
-Cómo Ejecutar el Proyecto
En tu explorador de archivos del IDE, navega hasta el paquete main.
Localiza el archivo MainApp.java.
Haz clic derecho sobre el archivo y selecciona Run 'MainApp.main()' (o la opción equivalente en tu IDE).
Se abrirá la interfaz gráfica (VentanaPrincipal) mostrando el mapa.

-Instrucciones de Uso
Seleccionar Puntos:
Por Texto: Escribe lugares válidos (ej. "Capitolio", "La Hoyada") en los campos de Origen y Destino.
Por Mapa: Deja los textos en blanco. Haz un primer clic en el mapa para fijar el Origen y un segundo clic para el Destino.
Preferencias: Selecciona el medio de transporte (Coche, A pie, Bicicleta) y marca "Evitar Tráfico" si lo deseas.
Calcular: Presiona "Buscar Ruta" para trazar la línea en el mapa y generar las direcciones de texto.
Limpiar: Usa el botón "Limpiar Mapa" para borrar los marcadores y reiniciar la búsqueda.
Nota:Estos son los únicos nombres que el sistema reconocerá si utilizas los campos de texto para buscar el origen y destino:
"Capitolio"
"La Hoyada"
"Bellas Artes"
"Plaza Venezuela"
"Sabana Grande"
¿Cuál es el límite real del proyecto?
Técnicamente, el límite de lugares que puedes agregar es gigantesco; está dictado únicamente por la memoria RAM de tu computadora.
