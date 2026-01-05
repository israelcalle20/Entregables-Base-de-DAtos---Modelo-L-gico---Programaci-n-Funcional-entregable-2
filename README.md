# Entregables-Base-de-DAtos---Modelo-L-gico---Programaci-n-Funcional-entregable-2
# Procesamiento de JSON con Circe en Scala

Este proyecto demuestra el uso de la librería **Circe** para la ingesta y limpieza de datos cinematográficos.

## Proceso Realizado
1. **Parsing**: Conversión de Strings JSON a tipos de datos seguros (Case Classes).
2. **Manejo de Optionals**: Uso de `Option[String]` para manejar `backdrop_path` nulos sin romper la ejecución.
3. **Procesamiento de Crew**: 
   - Se transformó la columna `crew` (originalmente una cadena de texto JSON) en una lista de objetos `CrewMember`.
   - Se aplicaron filtros para extraer roles específicos (Directores/Productores).
4. **Limpieza**:
   - Eliminación de espacios en blanco en nombres.
   - Validación de rutas de posters.
   - Manejo de errores mediante `Either` para evitar excepciones en tiempo de ejecución.
