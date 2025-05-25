# MonadSplitParseDivide

## Java
Just a try after viewed YT video [No Nonsense Monad & Functor - Les fondements de la programmation fonctionnelle par César Tron-Lozai](https://www.youtube.com/watch?v=e6tWJD5q8uw&t=3s&pp=ugMICgJmchABGAHKBRBObyBub3NlbnNlIE1vbmFk)


## Maven
[Apache Maven Survival Guide “Bring It On! -Mode”](https://www.youtube.com/watch?v=90O2ghOtaBo&t=580s)
[github/rfichtner/maven-survival-guide](https://github.com/rfichtner/maven-survival-guide/blob/JavaOne25/pizza-backend)

### Commands
```
mvn verify
mvn help::effective-pom
mvn versions:display-plugin-updates
mvn org.apache.maven.plugins:maven-dependency-plugin:3.8.1:analyze
mvn dependency:tree
mvn org.owasp:dependency-check-maven:check
mvn buildplan:list-phase
mvn verify -X
mvn project-info-reports:dependencies
mvn license:third-party-report
```

## OWASP: dependency-check-maven

see [dependency-check-maven](https://jeremylong.github.io/DependencyCheck/dependency-check-maven/index.html)
Request API key to [NIST](https://nvd.nist.gov/developers/request-an-api-key)

**Maven *settings.xml* **

```
    <server>
      <id>owasp-nvd-api</id>
      <username></username>
      <password>your_api-key</password>
    </server>
````

**Maven *pom.xml* **

```
      <plugin>
        <groupId>org.owasp</groupId>
        <artifactId>dependency-check-maven</artifactId>
        <version>12.1.0</version>
        <configuration>
          <nvdApiServerId>owasp-nvd-api</nvdApiServerId>
        </configuration>
      </plugin>
```
##  Maven Build Time Profiler Summary

Add extentins.xml into .mvn directory

## Detecting Stale source detected

mvn verify -X and search