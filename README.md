# Java Task Set Operations

A Java collections exercise comparing task assignments across people and projects.

## What is implemented

Task records/classes, priority and status modelling, comparators, and set operations for finding overlaps or missing assignments.

## Build and run

Use JDK 23 or newer and Maven from the repository root. The Maven file is the source of truth for any lower compiler target.

~~~sh
mvn -B compile
java -cp target/classes Main
~~~

## Scope and limitations

Lombok annotation processing is configured in Maven. This is a console analysis exercise rather than a deployed project-management application. Input validation and persistent storage are possible extensions.

## Verification

See [verification notes](docs/verification.md) for the exact checks performed. A compile result is not an end-to-end test result.

Local configuration, credentials, browser drivers, generated reports, IDE metadata, and personal data are excluded from new source commits. Existing source notices are retained.