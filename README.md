<<<<<<< HEAD
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
=======
# KotlinViikkoteht-v-1 week4

**Navigointi Jetpack Composessa**

Navigointi on sovelluksen sisäistä liikkumista näkymästä toiseen ilman activityn vaihtoa.

NavController: Ohjaa liikennettä

NavHost: Määrittää reitit (esim. home ja calendar)

Rakenne: Sovellus vaihtaa HomeScreenin ja CalendarScreenin välillä reiteillä


**MVVM ja tilan hallinta**

Yhteinen ViewModel: Sekä koti- että kalenterinäyttö käyttävät samaa TaskViewModel

Tilan jako: Koska ViewModel on yhteinen, tehtävälistan tila synkronoituu automaattisesti


**CalendarScreen toteutus**

Päivämäärävalitsin: Näytön yläreunassa on horisontaalinen LazyRow, joka näyttää kaikki uniikit päivät.

Ryhmittely: Tehtävät suodatetaan kun käyttäjä valitsee tietyn päivän, LazyColumn näyttää vain ne tehtävät, joiden dueDate täsmää valintaan.

Käyttöliittymä: Selkeä listanäkymä, joka keskittyy kerrallaan vain valitun päivän sisältöön.

**Tehtävien hallinta (AlertDialog)**

addTask: Avautuu "+" -napista. Sisältää lomakkeen uuden tehtävän luomiseksi suoraan päänäkymän päälle.

editTask: Toimii samallatavalla kuin week3 mallissa eli avataan dialogi kun painetaan korttia.



**APK:n asentaminen ja ajaminen**

Lataa APK projekti-kansiosta (app/build/outputs/apk/debug/app-debug.apk)

Salli asennus tuntemattomista lähteistä Android-laitteessa:

Asetukset → Turvallisuus → Salli tuntemattomat sovellukset

Siirrä APK laitteeseesi USB:n, pilven tai sähköpostin avulla

Asenna APK laitteella klikkaamalla tiedostoa

Aja sovellus:

Klikkaa sovellusikonia

Näet mock-tehtävälistan

Voit testata

>>>>>>> 86301355a626a10ea21f718a7c02b2ac42fd42c2
