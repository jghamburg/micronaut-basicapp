# Micronaut Framework Evaluation  

This project is the result of an evaluation of the [micronaut framework](https://micronaut.io).  
It was created by processing the provided guildes from the framework homepage.  

## GraalVM Image Creation  

Here is the first approach to create a native image using the graalVM (Version 20.0.0, jdk 11).  

```bash
java --version
# openjdk 11.0.6 2020-01-14
# OpenJDK Runtime Environment GraalVM CE 20.0.0 (build 11.0.6+9-jvmci-20.0-b02)
# OpenJDK 64-Bit Server VM GraalVM CE 20.0.0 (build 11.0.6+9-jvmci-20.0-b02, mixed mode, sharing)
```  

Install native image support  
```bash
gu install native-image
```

Generate a FAT JAR for native image.
```bash
./gradlew clean assemble
```

Generate native image itself.  
```bash
native-image --no-server -cp build/libs/complete-0.1-all.jar exampe.micronaut.Application build/nativeApp
```

We need to provide a reflection configuration file for Velocity (templating engine) for the application propperly to run.

```bash
 mkdir config-files
```

TODO: Missing details - [check guide](https://guides.micronaut.io/micronaut-error-handling/guide/index.html).  
  
Processing times on a MacBook Pro 2015  

```bash
native-image --no-server -cp build/libs/complete-0.1-all.jar example.micronaut.Application build/nativeApp
# [build/nativeApp:7591]    classlist:   5,505.44 ms,  1.63 GB
# [build/nativeApp:7591]        (cap):   1,307.46 ms,  1.63 GB
# [build/nativeApp:7591]        setup:   3,056.23 ms,  1.63 GB
# [build/nativeApp:7591]   (typeflow):  60,596.46 ms, 12.54 GB
# [build/nativeApp:7591]    (objects):  52,217.96 ms, 12.54 GB
# [build/nativeApp:7591]   (features):   5,667.06 ms, 12.54 GB
# [build/nativeApp:7591]     analysis: 125,080.95 ms, 12.54 GB
# [build/nativeApp:7591]     (clinit):   1,806.13 ms, 12.54 GB
# [build/nativeApp:7591]     universe:   4,707.67 ms, 12.54 GB
# [build/nativeApp:7591]      (parse):  10,196.21 ms, 12.62 GB
# [build/nativeApp:7591]     (inline):  10,744.95 ms, 12.70 GB
# [build/nativeApp:7591]    (compile):  44,134.40 ms, 12.72 GB
# [build/nativeApp:7591]      compile:  69,535.83 ms, 12.72 GB
# [build/nativeApp:7591]        image:   8,064.55 ms, 12.72 GB
# [build/nativeApp:7591]        write:   2,173.89 ms, 12.72 GB
# [build/nativeApp:7591]      [total]: 218,548.77 ms, 12.72 GB
```
