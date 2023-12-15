# Template Android
A sample project using composable components. The projects contains a `app-container` app, in which
the applications can be loaded.

There are also several projects under the `apps` folder. You can replace them in the container
to test them. The following steps are needed:

1. Add the dependency into the `app-container` module

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
