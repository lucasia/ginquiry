# ginquiry

Ginquiry is the one stop shop for gin lovers!

Find new gins and rate the gins you love.

The _ginquiry_ repository is the server-side code to view, create and update 
the Gins and Brands database over HTTP (RPC over http).

Build and run from maven:
``` 
mvn spring-boot:run
``` 

Build and run from Docker:
``` 
docker build -t ginquiry .
docker run -p 8081:8081 ginquiry
```