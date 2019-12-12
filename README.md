# TextfileAnalyzer

Repository for development of an Java Project that served as a coding example.
The program provides a minimal, modular, responsive UI for analysis tasks processed for provided text files.
More detailed (but German) information is provided further on.

*** Szenario ***
Für einen Kunden wird ein kleines, einfaches Utility benötigt. Das Programm mit
Benutzeroberfläche soll eine angegebene Textdatei einlesen, die einzelnen
Wörter herausfiltern und für jedes Wort die Anzahl der Vorkommnisse zählen. Das
Ergebnis ist in Form einer einfachen Tabelle mit zwei Spalten auszugegeben.
Die erste Spalte enthält die gefundenen Wörter, die zweite Spalte die Anzahl der
Vorkommnisse. Eine Sortierung der Ergebnisse nach Anzahl Vorkommnisse ist gewünscht.

Die File-Parse Logik, die die Unterteilung in Wörter vornimmt, sollte 
modular entwickelt werden, um sie später für andere Projekte wieder verwenden zu 
können. Hohe Performance ist wünschenswert. Die Benutzeroberfläche sollte während 
der Bearbeitung "responsive" bleiben, damit der Kunde nicht das Gefühl bekommt, die
Anwendung würde nicht richtig funktionieren/nichts tun. 

*** Nähere Funktionsbeschreibung ***
Das Programm liest ANSI-Textdateien ein. Die Datei soll vom User angegeben
werden können. Die Trennung in Wörter erfolgt einzig aufgrund von Whitespaces (Space,
LF, CR, ...), auf die Behandlung von Satzzeichen muss nicht eingegangen werden.
Es werden vom Kunden größere Dateien (~50MB) verarbeitet, daher
sollte die Benutzeroberfläche einen Progress-Balken anzeigen. Eine Option zum
Abbruch ist ebenfalls erforderlich.

Die folgende Datei...

1:1 Adam Seth Enos
1:2 Cainan Adam Seth Iared

sollte folgende Tabelle als Ergebnis ausgeben:

Wort		Anzahl
1:1		  1
Adam		2
Seth		2
Enos		1
1:2		  1
Cainan	1
Iared		1
