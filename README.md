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

### Wiremock Endpoints

View all mappings -> http://localhost/__admin/mappings
