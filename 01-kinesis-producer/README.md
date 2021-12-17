# Datastream Producer

This demo focus on producing data on Kinesis Data Stream. In this demo we will pull data periodically from Twitter and push to kinesis data stream.

## Configuration Changes
- Create Stream called 'twitter-in-data-stream' 
- In Application YAML update following configuration
    - Datastream name
    - Twitter Token
    - AWS
        - Access Key
        - Secret Key
        - Region

## Build and Run
- mvn clean install
- java -jar target/kinesis-producer-0.0.1-SNAPSHOT.jar
- You will be able to see data getting pushed to twitter-in-data-stream
- <img width="1676" alt="Screen Shot 2021-12-17 at 7 58 44 PM" src="https://user-images.githubusercontent.com/23295769/146559410-33771749-e96f-4209-9e3d-f6f077798007.png">


