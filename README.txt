Hi!

Included in this package are two executable JAR files, as well as the source code for accessibility. 

weather-alert.jar runs the application with the given endpoint hardcoded into the program. To run this, navigate to the directory where the JAR is located via your command line, and enter in the command:

java -jar weather-alert.jar

The second JAR, weather-alert-paramater.jar, takes in a Yahoo weather endpoint URL and will generate the alert output based on the payload of the given endpoint. To run this application, navigate to the directory where the JAR is located via the command line, and enter in the command:

java -jar weather-alert-paramater.jar <ENDPOINT AS STRING IN QUOTES>

Future enhancements: more readable error handling that does not just spit out the stack trace, allow user to enter in city and state as parameters, map Yahoo weather condition codes to generate alerts vs string matching on text description of condition, (potential) abstraction of RESTful call using Spring RestTemplate library

This program was written in Java, using JetBrain IntelliJ. Dependencies for this project include Java 1.8 and the org.JSON external library. 