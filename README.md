# spring-request-logging
Configurable, multi-mode library for logging request traffic in applications using the Spring Framework.  The **spring-request-logging** package automatically extracts metadata from every request and provides you with a simple interface to handle those results using one of two modes: **single**, or **batched**.  The following two sections detail how to use each of these modes.  The code for the library can be found in the `library/` directory, and a sample Spring Boot application using the library can be found in the `sample-application/` directory.

## Single Mode

A request logger that runs in single mode will execute once for each request, after the response has been flushed to the client.  To create a SingleRequestLogger, implement the [SingleRequestLogger](https://github.com/AaronCoplan/spring-request-logging/blob/master/library/src/main/java/com/aaroncoplan/springrequestlogging/SingleRequestLogger.java) interface and then [add it to your interceptor registry](https://github.com/AaronCoplan/spring-request-logging/blob/master/sample-application/src/main/java/com/aaroncoplan/Application.java#L28).  This requires you to implement the following function:

```java
void processSingleRequest(RequestData requestData)
```

Optionally, you may override the following function to extract the request pattern from the handler:

```java
String extractRequestPattern(Object handler)
```
For more information about what data is contained within a RequestData object, see the implementation [here](https://github.com/AaronCoplan/spring-request-logging/blob/master/library/src/main/java/com/aaroncoplan/springrequestlogging/RequestData.java).

## Batched Mode

## Performance Benchmarks

* Ran on Google Compute Engine in the us-central1-c region
* 1 client benchmark server using Apache Benchmark, n1-standard-4 (4 vCPUs, 15 GB memory), Ubuntu 16.04 LTS
* 1 small server running the example request loggers, n1-standard-1 (1 vCPU, 3.75 GB memory), Ubuntu 16.04 LTS
* 1 larger server running the example request loggers, n1-standard-4 (4 vCPUs, 15 GB memory), Ubuntu 16.04 LTS
* Used internal Google Cloud IPs
* Single endpoint serving a constant string
* Process for each benchmark:
    1. Start the server
    2. Warm the server up with 10 requests
    3. Run the Apache Benchmark command on the benchmark server, making requests to the application
    4. Kill the server
* Command for benchmarking with no concurrent connections --> ab -n 100000 -c 1 http://<ip>:<port>/
* Command for medium concurrency --> ab -n 100000 -c 50 http://<ip>:<port>/
* Command for high concurrency --> ab -n 100000 -c 250 http://<ip>:<port>/
* Results are in mean ms/request

| Smaller Instance | 1 Concurrent Request | 50 Concurrent Requests | 250 Concurrent Requests |
| ---------------- | -------------------- | ---------------------- | ----------------------- |
| No Logger        | 0.632                | 0.469                  | 0.525                   |
| Single Logger    | 0.620                | 0.479                  | 0.507                   |
| Batch Logger 10  | 0.637                | 0.486                  | 0.514                   |
| Batch Logger 250 | 0.644                | 0.475                  | 0.557                   |

| Bigger Instance  | 1 Concurrent Request | 50 Concurrent Requests | 250 Concurrent Requests |
| ---------------- | -------------------- | ---------------------- | ----------------------- |
| No Logger        | 0.711                | 0.176                  | 0.165                   |
| Single Logger    | 0.719                | 0.173                  | 0.156                   |
| Batch Logger 10  | 0.736                | 0.169                  | 0.161                   |
| Batch Logger 250 | 0.725                | 0.174                  | 0.164                   |
