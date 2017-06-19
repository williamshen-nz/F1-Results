# F1 Results
This project is a proof of concept aiming to provide quick and efficient access to F1 season data including race session
data (FP1, FP2, FP3, Qualifying, Race) and driver data (name, number, team, nationality, points) in JSON form using Java. 

A basic model of an F1 season has been implemented and broken down in the following manner:<br>
Season → Race → Sessions → Result

___

### Project Structure
* `data/` contains scraped data from 2006 to 2017 from the official F1 results website.
* `scraper/` contains the Python code used to scrape the data (except for drivers, teams and race metadata)
* `src/main/java` contains the main Java code used for representing an F1 season, and several classes to show
potential capabilities.
___

### How to Run
The `Application` class within the `Demo` package contains the main method required to start the web server to handle
API requests. Some sample mapping have been provided in `F1Controller.java`. 

To start the program, you can either initiate a Gradle build and run using `gradlew bootRun` or generate an executable
JAR using `gradlew build`.

The `LoadSeason` class within the `Demo` package gives an example of how session data can be loaded into
the program to generate the corresponding JSON output. All the provided scraped data follow the shown format.

It should be noted that loading season data using `LoadSeason` is much quicker than deserializing a resulting JSON string 
using `JSONtoF1`.

Complete sample data for the 2017 season as of the Canada Grand Prix has been provided (i.e. including race metadata, 
drivers, teams, etc.). Sample output has not been provided - to get the resulting JSON data simply call `toString()` on 
the relevant `Formula1.Model` object.

___

### API Documentation
To get complete season data, you can submit a `GET` request to `/f1/{year}` where year represents the year of the season you want to get results for (e.g. 2017). The API also supports a year value of 'current' which will get the current F1 season.

Specific race data can be requested using `/f1/{year}/{round}?sessions=...` - the 'sessions' query parameter is optional. All races are indexed by their round number which can be used to get the relevant Race JSON. For example, `/f1/current/3` will get the 3rd race of the current season (in the case of 2017, the Bahrain Grand Prix). 

The `round` parameter also includes string matching. You can enter the location or name of the Grand Prix and the program will _attempt_ to find a matching race based on the Race object's name and location (this is case insensitive). For example, `/f1/2017/monaco` will return the Monaco Grand Prix JSON data.

Finally, each Race supports the optional query parameter `sessions`. By default, if this is not specified, only the race session results will be returned. However, the following values are supported: `fp1, fp2, fp3, qualifying, race`. Additionally, there is the `all` option which will get all of the sessions. You can specify multiple sessions by separating them using commas. 

For example, `/f1/2017/canada?sessions=fp1,fp2,fp3` will return the practice sessions of the 2017 Canadian Grand Prix. Please note that the options are parsed sequentially. 

___
#### Dependencies
[Spring Boot](https://github.com/spring-projects/spring-boot) is used for creating the RESTful API used to `GET` data
from the system. 

The [Jackson Project](https://github.com/FasterXML/jackson) is used for streaming JSON into their
corresponding objects and for converting Java objects into JSON.
