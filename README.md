# KotlinViikkotehtät week 3
## MVVM ja Compose

Sovellus käyttää MVVM-arkkitehtuuria.

- Model: Task
- View: Jetpack Compose UI
- ViewModel: TaskViewModel

ViewModel hallitsee kaiken tilan ja logiikan.
UI ei sisällä liiketoimintalogiikkaa.

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
Voit testata
