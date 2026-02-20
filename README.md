# Week6 - Room-tietokannan toteutus

Tämä projekti on tehtävälista-sovelluksen jatkoa, tietojen tallenus pysyvä Room-tietokannalla. Sovellus hyödyntää MVVM-arkitehtuuria.

## Arkkitehtuuri ja rakenne

Sovellus on jaettu selkeisiin kerroksiin, mikä tekee koodista helpommin hallittavaa ja testattavaa:

*   **Entity: TaskEntity**: Määrittelee tietokantaan tallennettavan datan rakenteen
*   **DAO: TaskDao**: Rajapinta, joka sisältää metodit tietokannan lukemiseen ja muokkaamiseen
*   **Database: AppDatabase**: Hallitsee tietokantayhteyttä ja toimii pääpisteenä datan tallennukselle
*   **Repository: TaskRepository**: Toimii välikätenä tietokannan ja sovelluslogiikan välillä, tarjoten datan eteenpäin siistissä muodossa
*   **ViewModel: TaskViewModel**: Hallitsee sovelluksen tilaa ja logiikkaa. Se käsittelee datan (esim. järjestely ja suodatus) ennen sen näyttämistä käyttäjälle.
*   **UI (Jetpack Compose)**: Käyttöliittymä, joka päivittyy automaattisesti ViewModelin mukaan

## Datavirta
Room päivittää tiedon, ja reaktiivinen Flow päivittää näkymän automaattisesti ilman erillistä päivityskäskyä.

## Ominaisuudet

*   Tehtävien lisäys, haku, päivitys ja poisto.
*   Pysyvä tallennus: tiedot säilyvät, vaikka sovellus suljetaan.
*   Tehtävien järjestely nimen tai päivämäärän mukaan.
*   Kalenterinäkymä tehtävien tarkasteluun päiväkohtaisesti.

## Demovideo

[Linkki demovideoon] https://www.youtube.com/watch?v=ZPmuLcHprAw

## APK
APK sijainti on suoraa githubin juuressa sekä myös palautus tekstissä onedrive linkkinä
