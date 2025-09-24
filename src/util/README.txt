XML Utilities - Elección de DOM

¿Por qué DOM sobre SAX?

PROS de DOM:
- Fácil modificación in-memory: Permite leer, modificar y escribir XML de forma sencilla
- Sencillo para archivos pequeños: Ideal para archivos de configuración y datos pequeños
- Curva de aprendizaje baja: API más intuitiva para desarrolladores principiantes
- Acceso aleatorio: Permite navegar por cualquier parte del documento en cualquier momento
- Manipulación completa: Permite crear, modificar y eliminar elementos fácilmente
- Validación integrada: Soporte nativo para validación de esquemas XML

CONTRAS de DOM:
- Carga el XML completo en memoria: No es streaming, consume más memoria
- Menos eficiente para archivos grandes: Puede ser lento con archivos de varios MB
- No es event-driven: No es ideal para procesamiento secuencial de grandes volúmenes

¿Por qué DOM para este proyecto?

1. Archivos pequeños: Los archivos XML del proyecto (clientes.xml, servicios.xml, mecanicos.xml) 
   son relativamente pequeños y no justifican el overhead de SAX.

2. Modificación frecuente: El proyecto requiere leer, modificar y escribir archivos XML,
   lo cual es más natural con DOM.

3. Relaciones entre entidades: El mapeo de servicios a mecánicos requiere navegación
   bidireccional que es más fácil con DOM.

4. Desarrollo educativo: DOM es más didáctico para entender la estructura XML y
   las operaciones de manipulación.

5. Simplicidad de código: Menos líneas de código y lógica más clara para mantener.

Clases implementadas:

- CargadorXML: Utilidad única con métodos estáticos para cargar/guardar todas las entidades
  - Cargar(String archivo) → ArrayList<Cliente>
  - CargarServicios(String archivo) → ArrayList<Servicio>
  - CargarMecanicos(String archivo, ArrayList<Servicio>) → ArrayList<Mecanico>
  - Métodos Guardar* para persistir cambios
  - Helper getValue() para extraer texto de elementos
  - Resolución automática de servicios en mecánicos

Patrón seguido: DocumentBuilderFactory → DocumentBuilder → Document → NodeList → Element
