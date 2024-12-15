FROM maven
COPY target/calculator-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","calculator-0.0.1-SNAPSHOT.jar"]
