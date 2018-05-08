# MealyMachine

Dies ist eine funktionierende MealyMachine. 

### Aufgaben
- [x] Zustände
- [x] Eingabealphabet identisch mit Ausgabealphabet
- [x] Zustandsübergabefunktion und Ausgabefunktion mit Tabellen
- [x] Laden der Maschine aus Xml
- [x] Ordner Überwachung
- [x] Threadskommunikation

    die Aufgaben wurden vollständig erfüllt. In den Test Klassen findet man einen Abnahmetest.

Es ist möglich die Default Konfiguration zu ändern.
Diese findet man in xml Verzeichnis mit den Titel Machine.xml.

**Damit können die Tabellen der Zustandsübergangsfunktion und Ausgabefunktion geändert**

Übergangstabelle:
Alles was zwischen \<transitonTable\> * \</transitonTable\> sowie zischen Item Tags \<item\> \</item\>  kann mit einen anderen Enum State Values geändert werden.

Ausgabetabelle:
Alles was zwischen \<alphabet\> * \</alphabet\> sowie zischen Item Tags \<item\> \</item\>  kann mit Anderen Enum Alphabet Values geändert werden.


In den \<transitionSymbols\>*\</transitionSymbols\> kann die Wertigkeit der Inputsymbole verändert werden  

Um eine neue default Config zu erstellen muss in der MealyMachine diese Zeilen

    transitonTable = Table.transitonTable;
    alphabetOut = Table.alphabetOut;
    
auskommentiert werden. Danach muss entweder MachineSerilization.main oder MachineSerilization.marshaller ausgeführt werden
### Ändern des Alphabets
Das Ändern des Alphabets ist durch XJC möglich.
durch hinzu fügen von \<xsd:enumeration value="ALPHABET_VALUE"/\> Tag-Element kann das Alphabet erweitert werden.

ALPHABET_VALUE = Platzhalter für neuen Enum Eintrag  

Dafür muss dieser Befehl verwenden werden

    "PFADzurJavaSDK1.8"/bin/xjc -d "PFADzumREPO"/src/main/java/enums "PFADzumREPO"/xml/xjc-Schemata
    
.

### Warum wurden enums verwendet
Vorteile
* Typsicherheit
* keine Reflextion während der Laufzeit
* Klar definierte Zustände
* Klar definierte Input/ Ouputsymbole

Nachteile
* umständliche Xml Serilization
* schwer erweiterbar 

### Weitere Informationen zum Repo 

* Der Ordner deprecated ist nicht genutzter und veralteter Code.
* Der Ordner xmlGenerator ist eine nicht vollständige Implemtierung eines XJC SchemasGenerators
