# Github Sample App

### Design decision

I decided to apply [The Clean Architecture] from Robert Martin (Uncle Bob) using MVP for the UI layer. The reason to use this kind of architecture is to have an architecture independent to the UI, independent of the framework, and very important, testable. The domain and model layer can be easily changed, they are independent to the framework. To improve the architecture and to make easier to test the app, I used dependency injection (Dagger from Square Open Source) in every single layer. A event bus system is used to notify the result of the execution of a use case because of the lifecycle of the view on Android; Activities, Fragments, etc.

The project was configured to create a build process with different environments; Dev, Stage and Prod. Mostly I used the most important libraries for Android, completely necessary for a robust and reliable architecture.

  - **Dependency injection:** Dagger from Square Open Source.
  - **View injection:** ButterKnife from Square Open Source.
  - **Otto bus event:** Bus event from Square Open Source.
  - **Retrofit with OkHttp:** Network communication from Square Open Source.
  - **Android Support libraries v4 and AppCompact** from the framework.
