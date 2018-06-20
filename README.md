# Dritte Aufgaben ist noch unter dem Development Branch.


# VV Praktikums Aufgaben
1. [MEALYMACHINE] (#mealymachine)
2. [Restwebservice] (#restful-webservice-with-spring-server-/-jersey-client-/-h2-/-hibernate-/-swagger-)

# MealyMachine

Dies ist eine funktionierende MealyMachine. 

### Aufgaben
- [x] Zust�nde
- [x] Eingabealphabet identisch mit Ausgabealphabet
- [x] Zustands�bergabefunktion und Ausgabefunktion mit Tabellen
- [x] Laden der Maschine aus Xml
- [x] Ordner �berwachung
- [x] Threadskommunikation

die Aufgaben wurden vollst�ndig erf�llt. In den Test Klassen findet man einen Abnahmetest (MainTest.java), welcher eine einfache Mealy Machine erzeugt und mit Dummy Daten f�ttert.

Es ist m�glich die Default Konfiguration zu �ndern.
Diese findet man in xml Verzeichnis mit den Titel Machine.xml.

**Damit k�nnen die Tabellen der Zustands�bergangsfunktion und Ausgabefunktion ge�ndert werden.**

�bergangstabelle:
Alles was zwischen \<transitonTable\> * \</transitonTable\> sowie zwischen Item Tags \<item\> \</item\>  kann mit einen anderen Enum State Values ge�ndert werden.

Ausgabetabelle:
Alles was zwischen \<alphabet\> * \</alphabet\> sowie zwischen Item Tags \<item\> \</item\>  kann mit Anderen Enum Alphabet Values ge�ndert werden.


In den \<transitionSymbols\>*\</transitionSymbols\> kann die Wertigkeit der Inputsymbole ver�ndert werden  

Um eine neue default Config zu erstellen muss in der MealyMachine diese Zeilen

    transitonTable = Table.transitonTable;
    alphabetOut = Table.alphabetOut;
    
auskommentiert werden. Danach muss entweder MachineSerilization.main oder MachineSerilization.marshaller ausgef�hrt werden
### �ndern des Alphabets
Das �ndern des Alphabets ist durch XJC m�glich.
Durch hinzuf�gen von \<xsd:enumeration value="ALPHABET_VALUE"/\> Tag-Element in der Datei /xml/xjc-Schemata/Alphabet.xjc kann das Alphabet erweitert werden.

ALPHABET_VALUE = Platzhalter f�r neuen Enum Eintrag  

Um die �nderungen zu �bernehmen muss dieser Befehl verwenden werden

    "PFADzurJavaSDK1.8"/bin/xjc -d "PFADzumREPO"/src/main/java/enums "PFADzumREPO"/xml/xjc-Schemata/Alphabet.xjc
    
    
.

### Warum wurden enums verwendet
Vorteile
* Typsicherheit
* keine Reflextion w�hrend der Laufzeit
* Klar definierte Zust�nde
* Klar definierte Input/ Ouputsymbole

Nachteile
* umst�ndliche Xml Serilization
* schwer erweiterbar 

### Weitere Informationen zum Repo 

* Der Ordner deprecated ist nicht genutzter und veralteter Code.
* Der Ordner xmlGenerator ist eine nicht vollst�ndige Implemtierung eines XJC SchemasGenerators


# Restful Webservice with Spring-Server / Jersey Client / H2 / Hibernate / Swagger 

Aufgabe von Verteilte Verarbeitung 2 abgabe 12.06.2018

Alle Aufgaben wurden erf�llt

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
Swagger f�r eine JAX RS Springboot implemtierung ist veraltet und funktioniert nicht#
Mit Springfox - /den Swagger Framework von Spring/ konnte nur mit MVN gemappte Api-Endpoints dokumentiert werden.

**Deswegen existiert das PACKAGE package vv.restwebservice.Controller**

###### Lost-Update
Laut Internet und SpringBoot-Doku kann man Transaction mit der Annotation @Transactional optimitisch sperren.

Der genaue Sperrungablauf Verwaltung ist direkt von Spring-verwaltet, und somit schlecht einsehbar.

Laut der Datenbank Vorlesung und dem Internet kann das Lost Update von eine optimitischen Sperren behoben werden.



###### JUNIT TEST
Der JUNIT Test JerseyTestClientTest funktioniert nur wenn die Datenbank neu initalisiert ist
###### Zur�cksetz der GeneratedValue ID in Spring funktioniert nicht 

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

Die Entscheidung  Jersey / JAX-RS / JAX-RS Annotaions in Spring Server zu verwenden war auch fatal. Da diese Tools einfach schlecht in Spring integiert sind. Z.B. Swaggers Springfox nimmt trotz unz�hlicher verschiedener Konfigurations- Einstellungen nicht die Jersey Endpoints. 
Atomarit�t von JUNIT Test des Proxys wurden durch Datenbankzugriff und Serverzugriff erschwert.
Die Verwendung von Swagger dirket zu konfiguration beginn als am Anfang des Projektes. H�tte die Konfiguration und Testung von den Endpoints verleichert. Habe es leider erst am Schluss zu Dokumentation eingbunden.











