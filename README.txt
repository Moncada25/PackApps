RESUMEN DE CODIFICACIÓN
Código destinado a la validación de las integraciones encontradas en el aplicativo a manera de E2E, concluídos en varios casos de prueba
en los que se incluyen: correcta respuesta segun requiera y la ausencia de esta.

FRAMEWORK -> Serenity

COMPILADOR -> Gradle 4.9

PATRÓN DE DESARROLLO -> Cucumber con Screenplay

PRÁCTICAS DE AUTOMATIZACIÓN -> Principios SOLID y POO

HERRAMIENTAS DE AUTOMATIZACIÓN -> Serenity Reporter y Cucumber

ESTRATEGIA DE AUTOMATIZACIÓN
Se ejecuta el script de pruebas automatizadas luego del paso al ambiente de certificación para validar la correcta integración entre la 
aplicación Bizagi y los servicios web, rutas críticas y escenarios concurrentes.

APLICACIÓN EN PRUEBA -> Bizagi 11 - ECU Colombia

NAVEGADOR -> Google Chrome Version 71.0.3578.98 (Official Build) (64-bit)

DRIVER -> Chromedriver 2.45

COMANDOS PARA EJECUCIÓN -> gradle clean test aggregate

INFORMACIÓN ADICIONAL
El proyecto esta estructurado en el patrón screenplay con cada uno de sus paquetes implementados además el de integración, el cual permite la
búsqueda y lectura de las trazas arrojadas por Bizagi para su posterior uso en las comparaciones con el front end.
Cada Feature tiene su propio Runner.
Existe una clase general de StepDefinitions donde contiene la configuración y los pasos que se repiten en los demás StepDefinitions y por ende, hereda.
