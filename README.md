# Foundations of Distributed Systems Exam 03 - Filip Müller

## How to start the system

### Use class `Start`

Execute method `main` in class `Start`. This will start the embedded Tomcat server and deploy the application. The application
is available at `http://localhost:8080/demo/api`.

## How to execute the test cases

Right-click on **"src/test/java"** and select **"Run 'All Tests' "** to execute all test cases.

### Use Docker

### For integration testing

Call `mvn verify` to start the integration tests. This will create a Docker image and start a container for the demo application.  
Then the integration tests will be executed. Finally, the container will be stopped and removed.

