#Find Full Days Between Dates
A simple application to find full days between two given dates. The given dates themselves are excluded from the calculation as they are considered partial days.

#Java and Maven versions
This application has been built using JDK 11 and Maven 3.6.3

#Running the application
To run this application, execute the following command in terminal
```
mvn compile exec:java -Dexec.mainClass="com.myapps.datecalculator.FindFullDaysBetweenDates"
```

#Example
User Input
```
Enter Date 1: 1983-06-02
Enter Date 2: 1983-06-22
```

Output
```
There are '19' full days between 1983-06-02 and 1983-06-22
```