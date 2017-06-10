# F1 Results
This project is a work in progress to provide quick and efficient access to F1 season data including race session
data (FP1, FP2, FP3, Qualifying, Race) and driver data (name, number, team, nationality, points) in JSON form. 

A basic model of an F1 season has been implemented and broken down in the following manner:<br>
Season → Race → Sessions → Result

___

### Things to Implement

- Calculating more useful statistics
- Support for more data fields
- API for getting and selecting relevant race data
- Front-end based web-service to provide simplified access to the API
- Web Scraper for the Formula 1 results website
___

### Demo
The `Demo2017` class within the `Demo` package gives an example of how session data can be loaded into
the program to generate the corresponding JSON output.

Sample data for the 2017 season as of the Canada Grand Prix has been provided. Sample output has also been provided.

___

#### Dependencies

The [Jackson Project](https://github.com/FasterXML/jackson) is used for streaming JSON into the corresponding objects
and for converting Java objects into JSON.