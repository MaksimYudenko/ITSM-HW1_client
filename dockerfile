FROM openjdk:11
COPY ./src /usr/src/itsm_hw1_client
WORKDIR /usr/src/itsm_hw1_client
RUN javac Client.java
ENTRYPOINT ["java", "Client"]
