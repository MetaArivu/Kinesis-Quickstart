# Datastream Producer

This demo focus on consuming data on Kinesis Data Stream. 

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
- java -jar target/kinesis-consumer-1.0.jar
- You will be able to see data getting pulle from twitter-in-data-stream
- <img width="1668" alt="Screen Shot 2021-12-17 at 8 16 18 PM" src="https://user-images.githubusercontent.com/23295769/146561881-2f3e9d34-2ade-4756-8de6-28ee73ce96ec.png">


