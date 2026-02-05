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

