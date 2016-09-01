# In Memory Key-Value Store Service

This project provides a service that once packaged as WAR can be loaded 
by a distribution of ProActive Workflows and Scheduling to deploy an 
in-memory key-value store. This service exposes by default some 
resources to help debugging.

## Disclaimer

The code is not ready yet for use in production and should not be integrated for
now in a release of [ProActive Workflows and Scheduling](https://github.com/ow2-proactive/scheduling). The API was designed
based on the requirements for Orange CLIF. It could most probably be
generalized, naming enhanced, etc. Besides, lot of tests are missing.

# Prerequisite

Using this service requires that [inmemory-keyvalue-store-addons](https://github.com/ow2-proactive/inmemory-keyvalue-store-addons) has been 
packaged and installed in your ProActive distribution.

# Installation

Run the following Gradle command to generate JAR file:

``` gradle -Plocal clean war ```

It will generate a WAR file in
`build/libs/inmemory-keyvalue-store-service-X.Y.Z.war`

Copy the WAR file in the `dist/war` folder of your ProActive installation:

```
$ cp build/libs/inmemory-keyvalue-store-service-X.Y.Z.war $PROACTIVE_HOME/dist/war/inmemory-keyvalue-store-service-X.Y.Z.war
```

Then, when the ProActive Scheduler is launched, the service is automatically discovered and started. If you have followed the procedure, a REST Web service is available at [http://localhost:8080/inmemory-keyvalue-store-service/](http://localhost:8080/inmemory-keyvalue-store-service/).
