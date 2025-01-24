# EuroZahl: Lotto Results Viewer

**EuroZahl** is an Android application that consumes the **Lotto24.de API** to display the latest lotto results. Built using **Kotlin**, **Jetpack Compose**, and modern Android development practices, this app also features a **home screen widget** to provide quick lotto results at a glance.

---

## Features

- 🎯 **Fetch Lotto Results**: Retrieves the latest lotto results using the Lotto24.de API.
- 🖌️ **Modern UI**: Developed with Jetpack Compose.
- 📱 **Home Screen Widget**: Displays lotto results directly on the home screen.
- 🏗️ **Dependency Injection**: Uses Hilt for DI management.
- 🌐 **Network Requests**: Built with Retrofit and Gson for network interaction.
- 🧪 **Unit Testing**: Includes tests with JUnit and MockK.

## Limitations

- 🎯 **Widget**: Doesn't automatically get updated
- 🖌️ **Offline support**: Has no offline functionality

---

## Project Structure

``` This project adheres to **MVVM** Architecture. Here's a high-level overview of the structure:
com.freddominant.eurozahl
├── data          // API services, data models, and repositories
├── ui            // Jetpack Compose UI components
├── viewmodel     // ViewModels for state management
├── widget        // Glance-based home screen widget
└── di            // Hilt dependency injection modules
```

---

## Tech Stack

### Core Libraries
- **Jetpack Compose**: Declarative UI toolkit.
- **Retrofit**: REST client for API calls.
- **Gson**: JSON parser and converter.
- **Hilt**: Dependency injection framework.

### Testing
- **JUnit**: Unit testing framework.
- **MockK**: Mocking library.
- **Compose UI Test**: Test Compose-based UI components.
- **Espresso**: UI testing library.

---

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/freddominant/eurozahl.git
   cd eurozahl
2.	Open the project in Android Studio.
3.	Sync Gradle:
•	Ensure that the libs.versions.toml file is properly configured for the libraries used.
•	Update dependencies as needed.
4.	Build and run the app on an emulator or a physical device.

## Contribution
#### Contributions are welcome! Feel free to fork the repository and submit pull requests.
1.	Fork the repository.
2.	Create a feature branch: git checkout -b feature/new-feature.
3.	Commit your changes: git commit -m "Add new feature".
4.	Push to the branch: git push origin feature/new-feature.
5.	Submit a pull request.
