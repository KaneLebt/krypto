# krypto

Projekt bekommen:

# git clone https://github.com/KaneLebt/krypto.git


Datei ausführen:
# javac Main.java
# java Main

Commiten:
# git add  Main.java
# git commit -m "Commit Nachricht"
# git push

Akutellen Stand holen:
# git pull

Aufgaben:

Aufgabe 9: Streufunktion mit ARC4
Mit Alleged RC4 (ARC4) soll folgendermaßen eine Streufunktion implementiert werden:

1. Der aus einer Datei eingelesene Klartext wird wie in SHA-256 aufgefüllt.

2. Der aufgefüllte Klartext wird in Blöcke zu je 128 Bit zerlegt.

3. Das 256-Bit Textregister wird initialisiert wie in SHA-256.
4. Für jeden Klartextblock B wiederhole:

5. Die niederwertigen 128 Bits des Textregisters werden bitweise EXOR-verknüpft mit B. 
Das liefert die neuen 128 Bits des Textregisters.

6. Mit dem Textregister wird ARC4 initialisiert.

7. Man läßt die nächsten 256 Bytes des ARC4 verfallen.
8. Die nächsten ausgegebenen 256 Bit des ARC4 liefern den neuen Inhalt des Textregisters.

9. Ergebnis: Der Inhalt des Textregisters.

Die Streuwerte von einigen Beispielen sollen damit bestimmt werden. 
