# Template Android
A sample project using composable components. The projects contains a `app-playground` app, in which
the applications can be loaded.

There are also several projects under the `apps` folder. You can replace them in the container
to test them. The following steps are needed:

1. Add the dependency into the `app-playground` module

```kotlin
dependencies {
    implementation(projects.apps.showcaseApp) // Replace with the right dependency name
    
    // Other dependencies
}
```

2. Replace on the `MainActivity` the name of the composable function to lunch, that acts as single
entry point of the app.

```kotlin
@Composable
fun AppContent() = DemoApp() // Replace DemoApp with the name of the right function 
```

## Development
In order to use the modules `apps:tmdb-app` and `apps:rijks-app` remember to save the API keys:

```shell
open ~/.gradle/gradle.properties
```

```properties 
# ~/.gradle/gradle.properties file

# To obtain a key -> https://data.rijksmuseum.nl/docs/api/
API_KEY_RIJKS=<your-api-key>

# To obtain a key -> https://developer.themoviedb.org/reference/intro/getting-started
API_KEY_TMDB=<your-api-key>
```