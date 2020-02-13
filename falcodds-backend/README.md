# falcodds-backend

Odds service for the falcodds project.

## Run

To build and run the odds service, use:
```
./buildAndRun.sh
```

You can also directly type the following command:
```
./mvnw clean package && java -jar target/falcodds-backend-1.0-SNAPSHOT-runner.jar
```

## Ping

To check that the server is running, use: `curl -X GET http://localhost:8080/ping`.

This should display `falcodds` as a result.