# Tttwordscount

This project was generated with [angular-cli](https://github.com/angular/angular-cli) version 1.0.0-beta.28.3.
Front end developed using Angular 2 that accepts a number to get top n number of most frequently occurring 
words from specified file at back end service.


## Development server
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Getting Started

For running on local machine:
Make sure backend service ‘wordsplay’ is up and running.
Import ‘wordsplay’ project in any IDE of your choice and deploy it on port 8080.
To test whether service is up, simply hit below URL from any REST Client (e.g. Use POSTMAN client extension for Chrome)

http://localhost:8080/wordsplay/counter/test

It should return 200 Ok Status code with response message as ‘test succeed’.

To run UI follow below:
Run command from terminal (you must have installed latest node)
>>npm install
>>npm start


## Prerequisites

Install node - v8.9.4 or latest version
After that Run npm install, to install dependencies for the application

## Components
AppComponent is the main component for the application that accepts number from user and request AppService to get requested number 
of words.
AppService is the service that request wordsplay REST API to get top n most frequently occurring words from the file specified by
the service itself.

