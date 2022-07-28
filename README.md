# ScalaWiremock

This simple example is based on using a standalone Wiremock instance (docker container), rather than creating our own
embedded WiremockServer (e.g. [here](https://wiremock.org/docs/java-usage/)), and has been put together purely to
demonstrate how to populate stubs in Wiremock, either by volume-mount, or programmatically.

### Volume-Mounting Mappings

This is the most simple method to create mappings, you write json files that represent the request and response you want
to stub for, then you mount these to a particular directory within the Wiremock docker container.

This is more of a manual, hard-coded (static) approach. Everything is set up ahead of the app/tests running, and you won't be able to
change/clear any mappings dynamically (without adding code to do so - see next section).

In order for this approach to meet all of your needs you'll need to explicitly define every stub your app/tests need
ahead of time.

### Provisioning Mappings in Code

This approach is a bit more involved and can take a bit more time to setup initially, however, this approach does let
you write your mappings as code alongside your tests (no more manual json wrangling!).

When provisioning your mappings via functions you can easily integrate the setup and cleardown of stub mappings
per-test/suite (e.g. using [Before | After][Each | All] steps). This means that you can specific only the stubs you need for a
given test (or suite of test), run the test(s), then clear down the stub mappings before repeating on the next test/suite of tests.

I personally prefer this approach, as long as I write my stubbing code well, it is much easier to manage my stubs than
working with an ever-expanding set of json files which can easily become confusing and lead to mistakes.

### How to Run This Example

This example uses two Wiremock [docker containers](docker-compose.yml);

* One which is volume-mounted to [some mappings](wiremock_config/mappings) and is available on
  port [8080](http://localhost:8080/__admin/mappings)
* The other which does not have volume-mounted mappings is available on
  port [8081](http://localhost:8081/__admin/mappings).

To start these containers, from the root of this project run `docker-compose up -d`, to shut them down
run `docker-compose down`.

Once these containers are running, you can either view the stubs at the links below or run the tests
in [MySpec.scala](src/test/scala/MySpec.scala) to exercise and experiment with the stubbing and removal of stubs on port
8081 via code.

### Wiremock Endpoints

* [View all mappings (port 8080)](http://localhost:8080/__admin/mappings)
* [View all mappings (port 8081)](http://localhost:8081/__admin/mappings)
* [Example stub - checkstock/abc (port 8080)](http://localhost:8080/checkstock/abc)
* [Example stub - checkstock/abc (port 8081)](http://localhost:8081/checkstock/abc)
* [Example stub - checkstock/123 (port 8080)](http://localhost:8080/checkstock/123)
* [Example stub - checkstock/123 (port 8081)](http://localhost:8081/checkstock/123)

### Sources

* [Official Wiremock Website](https://wiremock.org/)
    * [Documentation on Stubbing via API or HTTP](https://wiremock.org/docs/stubbing/)
* [Wiremock Library (Github)](https://github.com/wiremock/wiremock) 
