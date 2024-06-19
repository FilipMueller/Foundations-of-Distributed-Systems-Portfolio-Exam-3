# Foundations of Distributed Systems Exam 03 - Filip Müller

## How to start the system

### Use class `Start`

Execute method `main` in class `Start`. This will start the embedded Tomcat server and deploy the application. The application
is available at `http://localhost:8080/exam/api`.

## How to execute the test cases

Right-click on **"src/test/java"** and select **"Run 'All Tests' "** to execute all test cases.


### Use Docker

### For manual testing 

Use file `src/main/docker/Dockerfile` as an example how to create a Docker image for the demo application. You first need to build 
the WAR file of the demo application using `mvn package`. Then execute `$> docker build .` (don't forget the point after `build`) to 
create an image. Finally, execute `$> docker run -p 8080:8080 --rm <IMAGE_ID>` to start the container.

### For integration testing

Call `mvn verify` to start the integration tests. This will create a Docker image and start a container for the demo application.  
Then the integration tests will be executed. Finally, the container will be stopped and removed. All integration tests must
be located in the `src/test/java` directory and must end with `IT`. See class `TestDemoAppIT` for an example.

