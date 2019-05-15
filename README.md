# Recycling examples

The example that describes the behaviour of a system should be 
valid however you use a system. The behaviour is not expected 
to be different if you its web user interface or through its rest api.

This leads us to want to use the same example and verify that 
the system works through different seams and with different clients 
or devices.

## Specifying the seam, client, or device

Recycling examples means that the seam, client, or device must be specified 
when the system is built.

This is a Java example. Specifying runtime properties for the JVM is done 
using `-Dproperpty-name=property-value`.

This can be used to specify a combination of the seam, the client, and the device 
that should be used for a specific execution.

## Building

The example uses Gradle, build it with:

    ./gradlew cucumber

This will build the system and verify that the core works as expected. 
That is, that the model works as expected.

