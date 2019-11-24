# Todos MongoUI

A sample frontend app wrapped in Spring Boot

* [Spring Boot](https://spring.io/projects/spring-boot) for app bits
* [MongoDB](https://mongodb.com) for persistence
* [Vue.js](https://vuejs.org/) for frontend, inspired by [TodoMVC Vue App](http://todomvc.com/examples/vue/), difference is this one is vendored as a Spring Boot app and calls a backing endpoint (``/todos``)

## Run on PCF

1. Consider forking [this project](https://github.com/corbtastik/todos-mongoui) then clone to dev machine
1. cd into project
1. mvnw clean package
1. modify ``manifest.yml`` for your cloudfoundry tastes (custom route perhaps?)
1. login to PCF (or [PWS](https://run.pivotal.io/))
1. cf push (awwwweee yeah)

## Local

You can clone, build, run then access ``localhost:8080`` or change the port.

```bash
java -jar ./target/todos-webui-1.0.0.SNAP.jar \
  --server.port=whatever
``` 

**MongoUI running**

<p align="center">
    <img src="https://github.com/corbtastik/todos-mongoui/raw/master/src/main/resources/static/mongoui.png" width="640">
</p>
