# Initial commit 

Useful commands:


./gradlew test: will run your unit tests.
./gradlew run : will run your application in the environment. This is useful to test your application in the development environment.
./gradlew jar: will create the jar inside the directory <project root>/build/libs with only the project's code. Not dependencies.
./gradlew assembleDist: will create a .tar and a .zip (both contain the same) in the directory <project root>/build/distributions that contain
the whole directory structure that will allow to install your project along with its dependencies in a machine without IDE (only with java 11 installed) and run it.
./gradlew clean: will clean the compilation files and the created artifacts


More info


Gradle application plugin
https://docs.gradle.org/current/userguide/application_plugin.html

--Commit
git add .
git commit -m "Info commit"
git push origin X-nom-issue

--Actualizar si tenemos commit pedientes
git checkout main
git pull
git checkout X-nom-issue
git merge main
