# Spring boot tutorial

https://spring.io/guides/gs/spring-boot/

# Spring boot project structure

https://medium.com/the-resonant-web/spring-boot-2-0-project-structure-and-best-practices-part-2-7137bdcba7d3

# Spring boot advangatge

1. Existing spring benefit

   1. dependencies
      1. only need to add dependencies in pom.xml then mvn will do the job during `mvn install` or `mvn exec`
   2. Built-in server like tomcat

2. Easy setup
   1. Spring initiator and auto configuration
3. `Actuator` make monitoring easiler
4. [Auto config](#auto_config) 
5. [Dependency injection](#dpi)


# Spring boot disadvantages

1. Skip a lot details, we may not know the low level things
   1. need to setup spring/springmvn from xml in order to understand the auto-config place in spring boot

# @Getter and @Setter annotation

https://projectlombok.org/features/GetterSetter

# Java bean

https://www.youtube.com/watch?v=xlWwMSu5I70&list=PLGibysfsUS7NAbefiaj1V4LbX0glTftDI&index=4&t=0s
https://www.youtube.com/watch?v=7c6ZTF6cF88&list=PLGibysfsUS7NAbefiaj1V4LbX0glTftDI&index=4

# Dependency injection <a name="dpi"></a>
Reference:
https://www.youtube.com/watch?v=K5bkniAjkZA

https://stackoverflow.com/questions/3058/what-is-inversion-of-control

## @Autowired

- Put the component dependency inside the constructor instead of using setting
- don't need to create class inside constructor
- or even don't need the constructor

## @Qualifier

- When there are two component/java bean, use qualifier to select which dependency to be wired

## Other reference

https://www.youtube.com/watch?v=suiEGbKf21g&list=PLGibysfsUS7NAbefiaj1V4LbX0glTftDI&index=10

http://blog.appx.tw/2017/08/21/spring-%E8%A8%BB%E8%A7%A3-%E4%B9%8B-autowired/

# Tomcat

Is a web server and servlet container
Receive request then decide to pass to service inside servlets

# Servlet

A servlet is simply a class which responsds to a particular type of network request - most commonly an HTTP request.

# Auto configuration  <a name="auto_config"></a>

When we use springMVC we need to use xml file to define a lot of stuff include bean to tell the bean factory to create object for us.

With spring boot, if the `jar` dependancy is on the class path, we can use the existing config for beans

We can also use spring initiator to skip those details

for example, spring boot can skip the setting of `DispatcherServlet`

https://juejin.im/post/6844903970972909581

We can initialize `DispatcherServlet` by using spring-web.jar

https://blog.csdn.net/qq_28411869/article/details/101053098

## debug autoconfiguration

### turn on debug logging

add the following line in `.src/main/resources/application.properties`

```
logging.level.org.springframework.web=DEBUG
```

### use actuator

find positiveMatches and negativeMatches (found no beans)

## Reference

https://www.youtube.com/watch?v=J_kTukE7hr8

https://www.springboottutorial.com/spring-boot-auto-configuration

# Thymeleaf

template engine for serving static resources

# XML config
100% javaConfig, hybrid config vs xml config (https://www.baeldung.com/spring-xml-vs-java-config)

# pom.xml

Usage
1. Project info like <groupID>, <artifactId>, <packaging> type
2. <dependencies>
3. <build> let we define compile, build and execution option
4. different <profile> can define different <build> tag
   
Reference: https://www.youtube.com/watch?v=IYRYbPR5Gek

# Dockerize Springboot

https://spring.io/guides/gs/spring-boot-docker

https://www.baeldung.com/dockerizing-spring-boot-application
