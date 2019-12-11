
Zeigen Sie uns mit Ihrer Implementierung dieses kleinen Beispiels, wie aus Ihrer Sicht qualitativ hochwertiger Code aussehen sollte! 

*** Szenario ***

F�r einen Kunden wird ein kleines, einfaches Utility ben�tigt. Das Programm mit
Benutzeroberfl�che soll eine angegebene Textdatei einlesen, die einzelnen
W�rter herausfiltern und f�r jedes Wort die Anzahl der Vorkommnisse z�hlen. Das
Ergebnis ist in Form einer einfachen Tabelle mit zwei Spalten auszugegeben.
Die erste Spalte enth�lt die gefundenen W�rter, die zweite Spalte die Anzahl der
Vorkommnisse. Eine Sortierung der Ergebnisse nach Anzahl Vorkommnisse ist gew�nscht.

Die File-Parse Logik, die die Unterteilung in W�rter vornimmt, sollte 
modular entwickelt werden, um sie sp�ter f�r andere Projekte wieder verwenden zu 
k�nnen. Hohe Performance ist w�nschenswert. Die Benutzeroberfl�che sollte w�hrend 
der Bearbeitung "responsive" bleiben, damit der Kunde nicht das Gef�hl bekommt, die
Anwendung w�rde nicht richtig funktionieren/nichts tun. 

*** N�here Funktionsbeschreibung ***

Das Programm liest ANSI-Textdateien ein. Die Datei soll vom User angegeben
werden k�nnen. Die Trennung in W�rter erfolgt einzig aufgrund von Whitespaces (Space,
LF, CR, ...), auf die Behandlung von Satzzeichen muss nicht eingegangen werden.
Es werden vom Kunden gr��ere Dateien (~50MB) verarbeitet, daher
sollte die Benutzeroberfl�che einen Progress-Balken anzeigen. Eine Option zum
Abbruch ist ebenfalls erforderlich.

Siehe Datei Sample.txt f�r eine Beispieldatei.

Die folgende Datei...

1:1 Adam Seth Enos
1:2 Cainan Adam Seth Iared

sollte folgende Tabelle als Ergebnis ausgeben:

Wort		Anzahl
1:1		1
Adam		2
Seth		2
Enos		1
1:2		1
Cainan		1
Iared		1


Bitte beim Zusenden der L�sung das "bin" und "obj" Verzeichniss der Solution l�schen, das sonst unsere
Firewall die Zustellung Ihres Mails unterbindet.


