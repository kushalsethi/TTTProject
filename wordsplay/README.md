### wordsplay ###

A backend service developed in Java using Spring MVC REST architecture for tttwordscount angular application that gives REST end points for top words count from file.

### Getting Started -How do I get set up? ###

This is maven project. So you need to have maven installed in your IDE.
Once maven is installed, run command 
>> mvn clean install

This will install all required dependencies for the application like spring-web.
Then deploy the generated war on any web container server like tomcat or jboss on port 8080.

Spring MVC framework is used to design RESTful webservice for client. This reads the file specified in property file
and then parses it get the top n requested most occurring words from it. File is parsed fetched and parsed only once per user request.
It means if same user calls the service the get next number of top words count then it will directly look in collection (HashMap) rather than
fetching file again. File url can be configured through property file if in case different file is needed to test. 

### Prerequisites ###
You must have JDK or JRE 1.8 installed.
Tomcat or JBoss or any web container to deploy service on port 8080.

### Components ###

EntryController is the rest controller for handling client requests.
ConuterService is the service that fetches the file specified in properties file. Then parses it and stores it in Map collection.
Any request to get top n most frquently occurring words is processed here and those words with their count is returned as json response.

@CrossOrigin annotation provided from spring 4.2 onwards is used to set header with Access-Control-Allow-Origin.
As front-end angular application is running on 4200 port whereas wordsplay service is running on 8080.
This resolves any request to wrodsplay REST service.


### Test Scenarios ###
To test use any REST Client like Postman for Chrome and hit below urls:
1. To test whether service is running
[GET] http://localhost:8080/wordsplay/counter/test 
This is will return 200 OK and response message as 'test succeed'

2. To get top number of most frequently occuring words 
[GET] http://localhost:8080/wordsplay/counter/getwords?number=3

Here number is the query parameter that takes the number of words as input.
This will return top 3 most frequently occurring words as JSON response:
Ex.
{
    "I": 28,
    "a": 25,
    "to": 22
}

3. In case the number is more than total number of words from the file, then error is returned as below.

[GET] http://localhost:8080/wordsplay/counter/getwords?number=3000
Ex.
{
    "httpStatus": "Bad Request",
    "errorMessage": "Requested number of words can't be returned, number exceeds total number of words"
}






