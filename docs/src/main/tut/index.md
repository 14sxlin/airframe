---
layout: home
title:  "Home"
section: "home"
technologies:
 - first: ["Scala", "Airframe is completely written in Scala"]
 - second: ["SBT", "Airframe uses SBT and other sbt plugins to generate microsites easily"]
---
# Airframe

Airframe is a collection of lightweight libraries useful for building full-fledged applications in Scala.
As its core Airframe provides [dependency injection (DI) library](docs) tailored to [Scala](https://www.scala-lang.org/). 
Dependency injection ([Wikipedia](https://en.wikipedia.org/wiki/Dependency_injection)) is a design pattern for simplifying object instantiation; 
Instead of enumerating necessary objects (dependencies) within constructor arguments, 
DI framework builds objects on your behalf. 
While Google's [Guice](https://github.com/google/guice) is designed for injecting Java objects (e.g., using class constructors or providers), Airframe redesigned it for Scala so that we can enjoy the flexibilities of Scala traits and DI at the same time.

Airframe also provides [lightweight utilities](docs/utils.html) that embody our best practices 
in developing complex services in Scala, including [application configuration](docs/airframe-config.html), [runtime monitoring](docs/airframe-jmx.html), [source code logging](docs/airframe-log.html), [command-line option parser](docs/airframe-opts.html) etc.


## Resources
- [Documentation](docs)
- [Use Cases](docs/use-cases.html)
   - Configuring applications, managing resources, service mix-in, etc.
- [DI Framework Comparison](docs/comparison.html)
   - Comparing Airframe with Google Guice, Macwire, Dagger2, etc. 
- [Airframe Utilities](docs/utils.html)
   - A collection of useful Scala libraries that can be used with Airframe.
- [Release Notes](docs/release-notes.html)
- [Source Code (GitHub)](https://github.com/wvlet/airframe)

## Getting Started
 [![Latest version](https://index.scala-lang.org/wvlet/airframe/airframe/latest.svg?color=orange)](https://index.scala-lang.org/wvlet/airframe)

Airframe is available for Scala 2.13, 2.12, 2.11, and [Scala.js](https://www.scala-js.org/):

**build.sbt**
```
libraryDependencies += "org.wvlet.airframe" %% "airframe" % "(version)"
```

### Usage

With Airframe, your Scala programming can be greatly simplified:

First, **bind** objects to your code with `bind[X]`:
```scala
import wvlet.airframe._

trait App {
  val x = bind[X]
  val y = bind[Y]
  val z = bind[Z]
  // Do something with x, y, and z
}
```

Next, **design** the object bindings:
```scala
val design: Design =
  newDesign
    .bind[X].toInstance(new X)  // Bind type X to a concrete instance
    .bind[Y].toSingleton        // Bind type Y to a singleton object
    .bind[Z].to[ZImpl]          // Bind type Z to ZImpl instance
```

Then **build** an instance and use it:
```scala
val session = design.newSession
val app: App = session.build[App]
```

Airframe builds an instance of `App` based on the binding rules specified in the *design* object. That means when writing applications, you only need to care about how to use objects (*bind*), rather than how to build them, because design objects already knows how to provide necessary objects to build your classes.

This separation of object bindings and their design (assembly) is useful for reducing code duplications between production and test codes. For example, compare writing `new App(new X, new Y(...), new Z(...), ...)` in both of your main and test codes, and just calling `session.build[App]`.
Airframe can integrate the flexibility of Scala traits and dependency injection (DI). Mixing traits is far easier than calling object constructors. This is because traits can be combined in an arbitrary order. So you no longer need to remember the order of the constructor arguments.

## Features

Major features of Airframe are as follows:

- Simple usage
  - Just `import wvlet.airframe._` and do the above three steps to start DI in Scala.
- Airframe can create objects from Scala traits.
- Design objects are immutable. You can create a new design safely based on an existing design.
- Supports all possible binding types: constructor, instance, provider, singleton bindings.
- Built-in life cycle management of objects (init, shutdown, etc.) through sessions.
- Scala macro based binding generation, which helps binding objects to your code.
- Scala 2.11, 2.12, and [Scala.js](https://www.scala-js.org/) support.

## What's Next?

See [Documentation](docs) for further details.
