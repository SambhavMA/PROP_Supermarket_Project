# SOBRE EL PROYECTO

Proyecto desarrollado en java para la asignatura de Proyectos de Programacion (PROP).

Durante mi formación en la UPC, participé en un proyecto de optimización de distribución de productos en supermercados, en el que trabajé en un equipo de cuatro personas. Mi principal contribución se centró en el diseño e implementación de algoritmos de ordenación para la disposición óptima de los productos, asegurando eficiencia y escalabilidad en las soluciones. Además, documenté detalladamente estos algoritmos, garantizando su claridad y mantenibilidad. A lo largo del proyecto, adquirí experiencia en desarrollo con Java, pruebas automatizadas con JUnit y el uso de IntelliJ IDEA. También fortalecí mis habilidades en arquitectura de software, trabajo en equipo y resolución de problemas complejos. Esta experiencia me permitió desarrollar un enfoque estructurado para la optimización de sistemas y consolidar mi capacidad para enfrentar desafíos técnicos en entornos colaborativos.

# DOCUMENTACIÓN 
- [Integrantes del equipo](DOCS/equip.txt)
- [Normativa de la asignatura](DOCS/normativa-1q2425.pdf)
- [Enunciado del proyecto](DOCS/enunciatt2425.pdf)
- [Ejecutable del proyecto](EXE/supermarket.main.jar)

# COMO EJECUTAR EL PROYECTO

## REQUISITOS:
- Hay que estar en mac o linux (Window Subsystem for Linux/WSL tambien es valido)
- Hay que tener instalado java 22

## FUNCIONALIDADES:
### Gradle
- ./gradlew build: compila el proyecto
- ./gradlew test: corre los tests
### Desde InteliJ IDE
- Run 'Main' para correr el programa e introducir los datos por consola
- Run 'Test' para correr los tests
### Desde la terminal con JAR
Se puede ejectuar el proyecto desde la terminal con los siguientes comandos:
```
# java -jar ./EXE/supermarket.jar  
# java -jar ./EXE/supermarket.jar  < ./EXE/juegosDePrueba/"nombreDelJuegoDePrueba".inp 
```
