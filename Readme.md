# Setup of basic Gradle using cli:

The below command is to setup a Gradle Project through Commandline without any IDE:
```agsl
gradle init --use-defaults --type java-application
```
The above command will setup a basic Gradle project, if Gradle is installed in your system.

Building project through IDE, it will give options for Gradle DSL(Domain Specific Language), in every gradle project we have a file called build.gradle which we can choose to write in different languages from the option.

When we run the project, a build folder is created which contain all the byte code.

```agsl
./gradlew tasks
```
It will print in the commandline, what all different tasks gradlew can do.

```agsl
./gradlew clean
```
This is used to delete your build folder.

## The 2 things building and running is done in the IDE just by clicking the run, but in cmd:
### Building a build folder:-
```agsl
./gradlew build
```
This will make a build folder from the command line itself...means it will create a byte code from the commandline.

### Running the code:-
To get the project actually running, we can build a jar file
```agsl
./gradlew jar
```
This will build a jar file

```agsl
 java -jar build/libs/(filename).jar
```
This will execute the jar file

#### Initially it will not execute and will give "no main manifest attribute", manifest property actually defines where the execution of your whole project actually starts from.

### To configure this manifest
In build.gradle, we add:
For groovy-
```agsl
jar {
    manifest {
        attributes (
            'Main-Class': 'org.example.Main'
        )
    }
}
```

For kotlin-
```agsl
tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to "org.example.Main"
            )
        )
    }
}
```

After this build the project again and execute the jar file.

***

## If I want to add external library and run this from terminal:

we create the jar, after that when we run java -jar....  command, it will give an error

```agsl
 java.lang.NoClassDefFoundError: okhttp3/OkHttpClient
```

It means that when jar is created "Gradle builds a plain JAR, so external libraries like OkHttp are not bundled, causing the class to be missing at runtime".

So we include this in build.gradle, inside jar:

```java
from {
        configurations.runtimeClasspath.collect { it.isDirectory() ? it : zipTree(it) }
    }
```

Then this error will be resolved but a new error comes:

```agsl
 duplicate but no duplicate handling strategy has been set
```

When Gradle creates a JAR, it copies files from dependencies.
If the same file appears more than once, Gradle throws this error unless you tell it what to do.

So we need to explicitely tell Gradle that

```agsl
duplicatesStrategy = DuplicatesStrategy.INCLUDE
````
Keeps all duplicate files, because you intentionally want every version to be packaged.
OR
```agsl
duplicatesStrategy = DuplicatesStrategy.INCLUDE
```
Keeps only one copy and ignores duplicate files, because duplicates are unnecessary and can cause conflicts.

