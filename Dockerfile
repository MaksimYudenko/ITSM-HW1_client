FROM openjdk:11
COPY ./src /usr/src/itsm_hw1_client
WORKDIR /usr/src/itsm_hw1_client
RUN javac by.itsm.training.Client.java
ENTRYPOINT ["java", "by.itsm.training.Client"]
