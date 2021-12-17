# Datastream Producer

This demo focus on producing data on Kinesis Data Stream using Core Kinesis API. 

## Configuration Changes
- Create Stream called 'order-in-data-stream' 
- In Application YAML update following configuration
    - Datastream name
    - Twitter Token
    - AWS
        - Access Key
        - Secret Key
        - Region

## Build and Run
- mvn clean install
- java -jar target/kinesis-order-producer-0.0.1-SNAPSHOT.jar
- Execute Below Curl Command

``` 
curl --location --request POST 'http://localhost:8080/api/v1/' \
--header 'Content-Type: application/json' \
--data-raw '{"id":"order-1","customerId":"cust-1","date":1635399776069,"lineItems":[{"itemId":"ID-111","item":"IPhone 12","qty":1,"price":50000},{"itemId":"ID-222","item":"IPhone 13","qty":1,"price":130000}]}' 
```
- Check Application Console
<img width="1674" alt="Screen Shot 2021-12-17 at 8 29 04 PM" src="https://user-images.githubusercontent.com/23295769/146564010-bd4ab05a-45bd-4ea7-8168-7ae54e459097.png">



