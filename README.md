[![License](https://img.shields.io/github/license/Torchmind/Observables.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.txt)
[![Maven Central](https://img.shields.io/maven-central/v/com.torchmind/observables.svg?style=flat-square)](https://search.maven.org/#search%7Cga%7C1%7Cg%3Acom.torchmind%20a%3Aobservables)
[![GitHub Release](https://img.shields.io/github/release/Torchmind/Observables.svg?style=flat-square)](https://github.com/Torchmind/Observables/releases)
[![CircleCI](https://img.shields.io/circleci/project/github/Torchmind/Observables.svg?style=flat-square)](https://circleci.com/gh/Torchmind/Observables)

Observable Properties for Java
==============================

Provides Observable Properties which can be listened to or even bound to one and another to Java
environments.

Installation
------------

This library is deployed on the Sonatype Open Source Software maven repository and is thus available
via maven central as follows:

### Maven
```xml
<!-- ... -->
<dependencies>
  <dependency>
    <groupId>com.torchmind</groupId>
    <artifactId>observables</artifactId>
    <version>1.0</version>
  </dependency>
</dependencies>
<!-- ... -->
```

or for snapshot (e.g. unstable) releases

```xml
<!-- ... -->
<repositories>
    <repository>
      <id>ossrh</id>
      <url>https://oss.sonatype.org/content/repositories/snapshots</url>
      
      <snapshots>
        <enabled>true</enabled>
      </snapshots>
    </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.torchmind</groupId>
    <artifactId>observables</artifactId>
    <version>1.0-SNAPSHOT</version>
  </dependency>
</dependencies>
<!-- ... -->
```

### Gradle
```groovy
// ...
repositories {
  mavenCentral()
}

dependencies {
  compile 'com.torchmind:observables:1.0'
}
// ...
```

Usage
-----

#### Basic Observable Property
```java
import com.torchmind.observable.Observable;
import com.torchmind.observable.SimpleObservable;

public class Example {
  private final Observable<String> username = new SimpleObservable<>("John");
  
  public String getUsername() {
    return this.username.get();
  }
  
  public void setUsername(String value) {
    this.username.set(value);
  }
  
  public Observable<String> username() {
    return this.username;
  }
}

// ...

public class OtherType {
  
  public void otherMethod() {
    Example example;

    // ...
    
    example.username().registerListener((property, oldValue, newValue) -> System.out.println("Username changed from " + oldValue + " to " + newValue));
    
    // ...
    
    example.setUsername("Peter"); // Prints "Username changed from John to Peter"
  }
}
```

### Bindings
```java
import com.torchmind.observable.Observable;
import com.torchmind.observable.SimpleObservable;

public class OtherType {
  private final Observable<String> displayName = new SimpleObservable<>();
  
  public void otherMethod() {
    Example example;
    
    // ...
    
    this.displayName.bindTo(this.displayName);
    
    // ...
    example.setUsername("Jane");
    System.out.println(this.displayName.get()); // Prints "Jane"
  }
}
```


### Bi-Directional Bindings
```java
import com.torchmind.observable.Observable;
import com.torchmind.observable.SimpleObservable;

public class OtherType {
  private final Observable<String> displayName = new SimpleObservable<>();
  
  public void otherMethod() {
    Example example;
    
    // ...
    
    this.displayName.bindBidirectionallyTo(this.displayName);
    
    // ...
    example.setUsername("Jane");
    System.out.println(this.displayName.get()); // Prints "Jane"
    
    this.displayName.set("Peter");
    System.out.println(example.getUsername()); // Prints "Peter"
  }
}
```

Reporting Bugs / Submitting Suggestions
---------------------------------------

**Important:** This project is free software. You will not be compensated for suggestions nor will
authors of suggestions be credited (beyond appearing in the submission on GitHub for as long as said
issue is publicly visible)

1. Search for previously submitted reports (either closed or open)
1. Search for pull requests which might solve your issue (in case of feature additions you may also indicate your approval)
1. Accurately describe the expected outcome and actual outcome (Bugs Only)
1. Provide steps to reproduce the issue and name the correct version (Bugs Only)
1. Accurately describe the feature and name use cases (Suggestions Only)

**Note:** When providing logs or code, submit them as a Gist. File attachments or dubious pastebin
services will be ignored.

Contributing
------------

**Important:** This project is subject to the Apache License, Version 2.0 (as shipped within this
repository). All contributions, without exception, must abide by these terms and be released with
the license notice outlined at the end of this file.

1. Fork this repository to your user or organization
1. Create a feature branch using git flow (`git flow feature start <myfeaturename>`)
1. Add your changes
1. Document every part of your API changes using JavaDoc
1. Write unit tests for new functionality (unless changes are simple enough to omit tests)
1. Verify that all unit tests are working properly (alter them if needed and note so in your comment)
1. Add yourself to the `<contributors>` element within the `pom.xml` (Optional; Please only add yourself once)
1. Push your feature branch
1. Submit your pull request through GitHub's website (see below for more information)

When submitting your pull request, we also ask that:

* You provide a short explanation of your changes
* You provide a short use-case explanation (or even better: a code sample)
* You note any referenced issues (if the feature has previously been discussed)

We also recommend that you discuss bigger changes by opening an issue on GitHub to avoid issues with
merging your request before they arise.

*Note:* Even when changes or additions have been previously discussed there is no guarantee that
your change will be merged into the repository. Changes which benefit only a small part of the user
base or affect the library in a way that jeopardizes API compatibility always run at high risk of
rejection.

History
-------

06/06/2017 - First designs
06/07/2017 - Publication on GitHub

Credits
-------

* The JavaFX Observable Property implementation (provided by the `javafx.beans` package) which acted as an inspiration for this project.

License
-------

This project is released under the terms of the
[Apache License](https://www.apache.org/licenses/LICENSE-2.0.txt), Version 2.0.

The following note shall be replicated by all contributors within their respective newly created
files (variables are to be replaced; E-Mail address or URL are optional):

```
Copyright <year> <first name> <surname <[email address/url]>
and other copyright owners as documented in the project's IP log.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
