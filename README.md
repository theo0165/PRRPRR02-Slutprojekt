# Programmering 2 Slutprojekt

### Idé
* Quiz spel
* Flera olika quizar
* Importera med json/xml
* Engelska/Svenska, fungerar som androids språkgrej, xml/json med strängar i de olika språken, sedan en class som hämtar de olika strängarna i korrekt språk
* Poäng, leaderboard för bästa på en viss quiz


* Sidor
    * Huvudmeny
        * Spela
        * Topplista
        * Importera
        * Avsluta
    * Spelet
        * TOP RIGHT: vilken fråga/antal frågor
        * TOP LEFT: avsluta
        * CENTER
            * TOP: Frågan
            * UNDER: Svar/alternativ
            
#### Import layout
JSON file format.

```
{
    "id": String (auto generate),
    "name": String,
    "dificulty": int(1-5),
    "questions": [
        "q": String,
        "correct": String
        "invalid": [
            Strings (4)
        ]
    ]
}
```