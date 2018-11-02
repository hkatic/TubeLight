# TubeLight #

## Introduction ##

TubeLight is an application that I primarily wrote for my personal needs as a replacement for native Youtube Android app. As an active Youtuber, I don't wanna be annoyed by ads that usually pop-up before video starts, or even worse, during video playback. I also wanted a cleaner UI that will be easier to navigate with TalkBack screen reader that I'm using for my daily Android tasks as a blind user and developer.

Currently you can search for videos and play them. I will implement more features from time to time.

## Running and compiling ##

Note, TubeLight is written in Java8, and I was using MVVM architecture as well as data binding.

To run and compile the application, you need the following:

* A physical Android device, running Android 4.3 Jelly Bean (API17) or later. A minimum Android version may change in the future if required.
* Android Studio version 3.2 or later with latest Android SDK and Gradle build system.
* Build tools V28.0.3 required
* Java JDK, version 8.


## Other dependences ##

There are some additional 3rd-party libraries used to make TubeLight development and debugging easier and faster. They are already defined in project's build.gradle file, or included in project's 'libs' directory, so there's no need for getting them separately.

* [GSON](https://github.com/google/gson) - Used for handling JSON objects.
* [Retrofit](https://square.github.io/retrofit/) - Used for accessing Youtube data API.
* [Android Youtube Player](https://github.com/PierfrancescoSoffritti/android-youtube-player) - Used for Youtube playback. All thanks goes to the user who created this awesome Youtube player library!


## API keys ##

For Youtube data API access, an API key is required. For security reasons, API key must be secret and therefore should not be visible inside any source or configuration files from a remote repository. Before you can compile/run the application, first you need to generate your unique API key in Google's API console, and then store it inside gradle.properties file on your local machine.

Please open or create gradle.properties file in .gradle subdirectory, which is located under your home directory. For example, on Windows: C:\users\<yourusername>\.gradle\gradle.properties

Then add the following entry:

tubelight_YoutubeApiKey="MYKEY"

Replace MYKEY with your generated API key.

Then save the file, and you're done.

## Contributions ##

If you like this app and you'd like to contribute code, you can do it via pull requests. Please note that this is a hoby project and I can't actively work on it due to my other life priorities, so you are more than welcome to give your contribution to keep the project going.

Have fun!
