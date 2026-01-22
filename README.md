# KotlinViikkotehtät
week2

State & ViewModel
Compose käyttää reaktiivista tilaa: UI päivittyy automaattisesti, kun state muuttuu.
remember säilyttää tilan vain Composable-funktion ajan, kun taas ViewModel
säilyttää tilan kokoajan, joten tiedot eivät katoa.


A) Ominaisuudet
4p: ViewModel + kaikki toiminnot (add/toggle/remove/filter/sort) toimii
B) Rakenne
domain, ViewModel ja UI erillään
C) UI/UX
selkeä, luettava, marginaalit kunnossa, omaa "koristelua" on tehty


APK:n asentaminen ja ajaminen

Lataa APK projekti-kansiosta (app/build/outputs/apk/debug/app-debug.apk)

Salli asennus tuntemattomista lähteistä Android-laitteessa:

Asetukset → Turvallisuus → Salli tuntemattomat sovellukset

Siirrä APK laitteeseesi USB:n, pilven tai sähköpostin avulla

Asenna APK laitteella klikkaamalla tiedostoa

Aja sovellus:

Klikkaa sovellusikonia

Näet mock-tehtävälistan

Voit testata Add Task, Toggle Done, Show Done / Show All, Sort-napit
