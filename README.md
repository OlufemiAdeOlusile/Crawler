# com.web.crawler
## A simple selenium/java com.web.crawler using Selenium, Java and Gradle

### TO DO
Crawler to crawl  all pages on any url checking for broken links.

 Limit the depth of the traversal via some config
 Check for assets returning non 200 statuses (JS, CSS, images, etc...)
 Crawl links rendered by client side JS, such as React
 Execute JS in the browser, detect the presence of
React, and listen for DOM mutation events to know when it’s done rendering.

### Dependencies:
All dependencies will be handled by Gradle. Chromedriver executable has been included into the project.
### Prerequisites:
Latest version of Chrome needs to be installed on the machine (75 at the time the project was compiled)
Latest Java version is needed (12 used)

### How to configure:

I have provided a ```config.properties``` file to include any configurations you want.

- ```url```: base url for the crawler. "Default = https://www.google.com/"
- ```statusCode```: Status code to search for the good unbroken links. "Default = 200”
- ```asset```: This is the type of links you want the page to search for. Either JS, IMAGES, CSS, or PAGES. “Default = PAGES”
- ```maxLinksPerPage```: maximum good links each parent page should retrieve. “Default = 2”
- ```maxDepth```: maximum depth of the traversal. “Default = 3”

### How to run the project:
To run from terminal simply do ```gradle run```

### How to build and run a JAR:
To build an executable JAR simply run ```gradle clean``` then run fatJar gradle task ```gradle fatJar``` this will
create an executable Jar under ```build/libs/crawler-1.0-SNAPSHOT.jar```
To run it go to terminal, cd to project directory and run ```java -jar build/libs/crawler-1.0-SNAPSHOT.jar```
(run from the project root and do not move the jar as it will break the resources)

### Output
The output of the program is displayed on the console using slf4j/log4J library