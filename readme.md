# Github Sample App

## Requirements

### Functional:

  - User should be able to enter a programming language
  - App should request list of popular repositories for this language from Github API and present this list to the user.
  - User should be able to tap on a repository to see repository data like name, description, 3 newest issues and 3 top contributors (No Web view. Extra data should be loaded via Github API).

### Non-functional:

  - The app should be displayed equally well for phones of two different sizes (two is not a limit, just a suggestion).
  - Data for the app should be fetched from Github API
  - Tests if applicable

## Design decision

I decided to apply [The Clean Architecture] from Robert Martin (Uncle Bob) using MVP for the UI layer. The reason to use this kind of architecture is to have an architecture independent to the UI, independent of the framework, and very important, testable. The domain and model layer can be easily changed, they are independent to the framework. To improve the architecture and to make easier to test the app, I used dependency injection (Dagger from Square Open Source) in every single layer. A event bus system is used to notify the result of the execution of a use case because of the lifecycle of the view on Android; Activities, Fragments, etc.

The project was configured to create a build process with different environments; Dev, Stage and Prod. Mostly I used the most important libraries for Android, completely necessary for a robust and reliable architecture.

  - **Dependency injection:** Dagger from Square Open Source.
  - **View injection:** ButterKnife from Square Open Source.
  - **Otto bus event:** Bus event from Square Open Source.
  - **Retrofit with OkHttp:** Network communication from Square Open Source.
  - **Android Support libraries v4 and AppCompact** from the framework.

[The Clean Architecture]:http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html