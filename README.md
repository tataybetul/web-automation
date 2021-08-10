# Trendyol Api Automation


## Get the code

Git:

    git clone ....
    cd web-automation

### Download chrome driver

https://chromedriver.storage.googleapis.com/index.html?path=91.0.4472.101/

## Use Maven

Open a command window and run:

    ./mvnw test -Demail="email" -Dpassword="password" -DdriverPath="fullPath" -Dbrowser="chrome"

This runs Cucumber features using Cucumber's JUnit runner. The `@RunWith(Cucumber.class)` annotation on the 
`RunCucumberTest` class tells JUnit to kick off Cucumber.
