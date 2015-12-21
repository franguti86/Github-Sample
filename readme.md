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

I decided to apply [The Clean Architecture] from Robert Martin (Uncle Bob) using MVP for the UI layer. The reason to use this kind of architecture is to have an architecture independent to the UI, independent of the framework, and very important, testable. The domain and model layer can be easily changed, they are independent to the framework. To improve the architecture and to make easier to test the app, I used dependency injection (Dagger from Square Open Source) in every single layer. To fetch the data from the network and perform it on background I used Retrofit. The app is responsive for all sizes supporting screen densities from MDPI to XXXHDPI (assets ready for those resolutions, for the rest it will be scaled) although there is no tablet version. To make a tablet version we can have the RepositoryListFragment as a frame on the left hand side for instance.

The project was configured to create a build process with different environments; Dev, Stage and Prod. For the development, I integrated the following libraries:

  - **Dependency injection:** Dagger from Square Open Source.
  - **View injection:** ButterKnife from Square Open Source.
  - **Retrofit with OkHttp:** Network communication from Square Open Source.
  - **Android Support design, v7 AppCompact and CardView:** Libraries for Material Design and support.
  - **Parceler:** To pass data from activities using Parcelable objects.
  - **Picasso:** Download and cache image system.
  - **CircleImageView:** To round images.
  - **MockWebServer:** To mock Retrofit network layer.
  - **Junit and Mockito:** For unit test.

[The Clean Architecture]:http://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html