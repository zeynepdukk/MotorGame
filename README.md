# Motorcycle Game

Welcome to the Motorcycle Game! This is an Android game where players control a motorcycle using the device's accelerometer. The objective is to avoid other cars and score as high as possible. The game offers different difficulty levels, which affect the speed of the motorcycle and the challenge.

## Features

- **Accelerometer Control**: Tilt your device to steer the motorcycle left and right.
- **Difficulty Levels**: Choose between Novice, Intermediate, and Advanced difficulty levels.
- **Score Tracking**: Your score increases as you avoid more cars. The game ends if a collision occurs.
- **Touch Controls**: Tap the screen to accelerate and release to decelerate.

## Installation

1. **Clone the repository**:

    ```bash
    git clone https://github.com/zeynepdukk/MotorGame.git
    ```

2. **Open the project** in Android Studio.

3. **Build and run** the project on an Android device or emulator.

## Usage

1. **Start the Game**: Open the app and select a difficulty level by tapping the corresponding button (Novice, Intermediate, or Advanced).

2. **Control the Motorcycle**: Tilt your device to steer left or right. Tap the screen to accelerate and release to decelerate.

3. **End of Game**: The game ends if the motorcycle collides with another car. Your final score will be displayed.
## ScreenShots
![Ekran görüntüsü 2024-08-05 182503](https://github.com/user-attachments/assets/2cb14e6c-23b0-409d-9854-76404ad7c5a4)
![image](https://github.com/user-attachments/assets/3182feb1-6bf8-4558-abec-7ddf70b4cadd)



## Project Structure

- **`GameView.java`**: The main game logic, including rendering and game mechanics.
- **`MainActivity.java`**: The entry point of the app, handling the game view and difficulty selection.
- **`res/drawable`**: Contains drawable resources like images for the motorcycle and cars.
- **`res/layout/activity_main.xml`**: Layout file for the main activity, including buttons for difficulty selection.

## Dependencies

- Android SDK
- SensorManager for accelerometer data

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contributing

Feel free to fork the repository and submit pull requests. For major changes, please open an issue to discuss before making changes.

## Contact

For any questions or feedback, please contact [your-email@example.com](mailto:your-email@example.com).

---

Enjoy playing the Motorcycle Game!
