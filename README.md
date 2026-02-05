<<<<<<< HEAD
# KotlinViikkoteht-v-1
week1

A) Ominaisuudet
data class, useampi funktio ja napit (add/toggle/filter/sort) + UI näyttää listan/mockin
B) Rakenn
selkeä domain/UI-erottelu, nimet järkeviä
C) UI/UX
perus Compose-näkymä (Column/Row) 
D) Dokumentointi & demo
https://www.youtube.com/watch?v=kqJcmOc7qHs
=======
# KotlinViikkotehtät week 3
## MVVM ja Compose

Sovellus käyttää MVVM-arkkitehtuuria.

- Model: Task
- View: Jetpack Compose UI
- ViewModel: TaskViewModel

ViewModel hallitsee kaiken tilan ja logiikan.
UI ei sisällä liiketoimintalogiikkaa.
>>>>>>> 7834bba45f1d64bc3cc8c2f11e6e1a3a50ce99e3

## StateFlow
TaskViewModel käyttää StateFlowta tilan hallintaan.
Compose kuuntelee tilaa collectAsState()-funktiolla.
Kun tila muuttuu, UI päivittyy automaattisesti.

## Hyödyt
- Selkeä rakenne
- Reaktiivinen UI
- Tila säilyy rotaatiossa
- Helppo testata ja laajentaa

  
APK:n asentaminen ja ajaminen
Lataa APK projekti-kansiosta (app/build/outputs/apk/debug/app-debug.apk)
Salli asennus tuntemattomista lähteistä Android-laitteessa:
Asetukset → Turvallisuus → Salli tuntemattomat sovellukset
Siirrä APK laitteeseesi USB:n, pilven tai sähköpostin avulla
Asenna APK laitteella klikkaamalla tiedostoa
Aja sovellus:
Klikkaa sovellusikonia
Näet mock-tehtävälistan
<<<<<<< HEAD

Voit testata Add Task, Toggle Done, Show Done / Show All, Sort-napit
=======
Voit testata
>>>>>>> 7834bba45f1d64bc3cc8c2f11e6e1a3a50ce99e3
