# Restful Webservice with Spring-Server / Jersey Client / H2 / Hibernate / Swagger 

Aufgabe von Verteilte Verarbeitung 2 abgabe 12.06.2018

Alle Aufgaben wurden erfüllt

1. Erstellung eines Restful WEbservice
2. Verwaltung von Datenstruktur durch Rest
3. Test Client
4. Anfragen Proxy 
5. Verhindern von Lost Update
6. Client Authentisierung
7. https Zugriff
8. Docker build


## CONFIGS und Startup
	HTTP server Address localhost:8080
    
    HTTPS server Address localhost:8081
    
    Swagger address
    http://localhost:8080/swagger-ui.html#/
    
    H2 Console
    http://localhost:8080/console
    
    **BASIC AUTH**
	Benutzername: user
    Passwort: qwer
    
    
## Eigenheiten des Projektes
###### Swagger
Swagger für eine JAX RS Springboot implemtierung ist veraltet und funktioniert nicht#
Mit Springfox - /den Swagger Framework von Spring/ konnte nur mit MVN gemappte Api-Endpoints dokumentiert werden.

**Deswegen existiert das PACKAGE package vv.restwebservice.Controller**

###### Lost-Update
Laut Internet und SpringBoot-Doku kann man Transaction mit der Annotation @Transactional optimitisch sperren.

Der genaue Sperrungablauf Verwaltung ist direkt von Spring-verwaltet, und somit schlecht einsehbar.

Laut der Datenbank Vorlesung und dem Internet kann das Lost Update von eine optimitischen Sperren behoben werden.



###### JUNIT TEST
Der JUNIT Test JerseyTestClientTest funktioniert nur wenn die Datenbank neu initalisiert ist
###### Zurücksetz der GeneratedValue ID in Spring funktioniert nicht 

## DOCKER
Der Docker build funktioniert erst wenn alle Test AUSKOMMENTIERT sind 

Bei Fehler den Befehl 
	gradle build clean 
verwenden
DOCKER

Docker starten
systemctl start docker

Docker build
sudo ./gradlelw build buildDocker
Docker Server run
docker images
docker run -p 8080:8080 -t "/image/"

### Revision

Die Entscheidung  Jersey / JAX-RS / JAX-RS Annotaions in Spring Server zu verwenden war auch fatal. Da diese Tools einfach schlecht in Spring integiert sind. Z.B. Swaggers Springfox nimmt trotz unzählicher verschiedener Konfigurations- Einstellungen nicht die Jersey Endpoints. 
Atomarität von JUNIT Test des Proxys wurden durch Datenbankzugriff und Serverzugriff erschwert.
Die Verwendung von Swagger dirket zu konfiguration beginn als am Anfang des Projektes. Hätte die Konfiguration und Testung von den Endpoints verleichert. Habe es leider erst am Schluss zu Dokumentation eingbunden.










