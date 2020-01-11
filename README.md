[![Build Status](https://travis-ci.org/TheDanishWonder/CA-3-Backend.svg?branch=master)](https://travis-ci.org/TheDanishWonder/CA-3-Backend)

*This is the quick start project which will be used as startcode for the semester project*

*Projects which are expected to use this qucik-start-code are projects that require all, or most of the following technologies:*
 - *JPA and REST*
- *Testing, including database test*
- *Testing, including tests of REST-API's*
- *CI and CONTINUOUS DELIVERY*

### Preconditions
*In order to use this code, you should have a local developer setup + a "matching" droplet on Digital Ocean as described in the 3. semester guidelines*

# Getting Started

This project contains two major documentation files: 
 - [First time users - getting started](README_proof_of_concept.md)
 - [How to use for future projects](README_how_to_use.md)

First clone the project into a folder with the following git command
 - $ git clone https://github.com/TheDanishWonder/CA-3-Backend.git
 - Open the project in your preferred editor.
 - Change the URL to their server in [this file](https://github.com/TheDanishWonder/CA-3-Backend/blob/master/src/main/resources/config.properties).
 - Set up travis.ci (Change test database in [this file](https://github.com/TheDanishWonder/CA-3-Backend/blob/master/.travis.yml)).
 - Clean and build project.
 - Test endpoint via postman.
 - If endpoints work, deploy to droplet via travis.
 
