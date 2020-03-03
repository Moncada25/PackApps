RESUMEN DE CODIFICACI�N
C�digo destinado a la validaci�n de las integraciones encontradas en el aplicativo a manera de E2E, conclu�dos en varios casos de prueba
en los que se incluyen: correcta respuesta segun requiera y la ausencia de esta.

FRAMEWORK -> Serenity

COMPILADOR -> Gradle 4.9

PATR�N DE DESARROLLO -> Cucumber con Screenplay

PR�CTICAS DE AUTOMATIZACI�N -> Principios SOLID y POO

HERRAMIENTAS DE AUTOMATIZACI�N -> Serenity Reporter y Cucumber

ESTRATEGIA DE AUTOMATIZACI�N
Se ejecuta el script de pruebas automatizadas luego del paso al ambiente de certificaci�n para validar la correcta integraci�n entre la 
aplicaci�n Bizagi y los servicios web, rutas cr�ticas y escenarios concurrentes.

APLICACI�N EN PRUEBA -> Bizagi 11 - ECU Colombia

NAVEGADOR -> Google Chrome Version 71.0.3578.98 (Official Build) (64-bit)

DRIVER -> Chromedriver 2.45

COMANDOS PARA EJECUCI�N -> gradle clean test aggregate

INFORMACI�N ADICIONAL
El proyecto esta estructurado en el patr�n screenplay con cada uno de sus paquetes implementados adem�s el de integraci�n, el cual permite la
b�squeda y lectura de las trazas arrojadas por Bizagi para su posterior uso en las comparaciones con el front end.
Cada Feature tiene su propio Runner.
Existe una clase general de StepDefinitions donde contiene la configuraci�n y los pasos que se repiten en los dem�s StepDefinitions y por ende, hereda.
