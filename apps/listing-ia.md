# Listing generation
Copy-paste the following template message to [chatgpt](https://chatgpt.com/) in order to generate
a new app:

```
I have a JSON structure, and I want you to generate Kotlin data classes and a Ktor suspend function within a class. Here's what I need:

Package Name:
package com.your.package.name (Replace with your desired package name)

JSON Structure:
(Paste the JSON here)

Details for the Function and Class:

Base API URL: (Provide the URL)
Query Parameters: (List the query parameters and their mapping to function parameters, e.g., page, query)
Expected Class Name: (Provide the name of the class, e.g., ImageCollectionDataSource)
Dependency Injection: The class should accept an HttpClient as a constructor parameter, annotated with @Inject and a qualifier like @YourHttpClientQualifier.

Expected Data Classes:
Generate data classes with fields and nested objects matching the JSON structure.
Use @SerialName annotations to map JSON snake_case fields to camelCase Kotlin properties.

Expected Function:
Define a suspend function inside the class that fetches data from the provided API URL.
The function should accept parameters for all query strings.
Return the root data class representing the API response.
```