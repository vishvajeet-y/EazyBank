#Start With a base image  containing JAVA RUNTIME
FROM openjdk:26-ea-22-oracle

#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="wish.com"

# Add the application's jar to the image
#We are telling Docker server that there is jar in my target folder and you need to copy it.
COPY target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar

# execute the application-->Same command which we used to execute our Application
ENTRYPOINT ["java", "-jar", "account-0.0.1-SNAPSHOT.jar"]