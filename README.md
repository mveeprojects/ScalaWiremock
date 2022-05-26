# ScalaWiremock

Simple project demonstrating how to create stubs using Wiremock.

This can be done by either of the below two methods.

### Volume-Mounting Mappings

This is the most simple means to create mappings, you write json files that represent the request and response you want to stub for, then you mount these to a particular directory within the Wiremock docker container.

This is more of a manual, hard-coded approach. Everything is set up ahead of the tests running, and you won't be able to change/clear any mappings dynamically as the tests run (without adding code to do so - see next section). 

In order to use this approach to meet all of your needs you'll need to explicitly define every stub your app/tests need ahead of time.

### Provisioning Mappings in Code

This approach is a bit more involved and can take a bit more time to setup initially. However, this approach does let you write your mappings as code next to your tests and therefore allows you to use the same language as used in your tests (e.g. Scala) to manage not only your tests but also your stub mappings (no more manual json wrangling!).

When provisioning your mappings via functions you can easily integrate the setup and cleardown of stub mappings per-test (e.g. using [Before | After][Each | All] steps). This means that you can specific only the stubs you need for a given test (or set of test), run the test(s), then clear down the stub mappings before repeating on the next test(s).    

I prefer this approach as it means as long as I write my stub code well, it is much easier to maintain and extend than working with an ever-expanding set of json files which can easily become confusing and lead to mistakes.

### How to Run This Example

This example uses two Wiremock [docker containers](docker-compose.yml);
* One which is volume-mounted to [some mappings](wiremock_config/mappings) and is available on port [8080](http://localhost:8080/__admin/mappings)
* The other which does not have volume-mounted mappings is available on port [8081](http://localhost:8081/__admin/mappings).

To run these containers simply run `docker-compose up -d`, to shut them down just run `docker-compose down`.

### Wiremock Endpoints

* [View all mappings](http://localhost:8080/__admin/mappings)
* [Example 200 response - checkstock/abc](http://localhost:8080/checkstock/abc)
* [Example 404 response - checkstock/123](http://localhost:8080/checkstock/123)

### Sources

* [Official Wiremock Website](https://wiremock.org/)
* [Wiremock Library (Github)](https://github.com/wiremock/wiremock) 
