# Bank Fraud Detection

In this demo we will tag a Alarm to creditcard transaction based on Following Rules

- Check if customer is already Alarmed previously
- Check if card used  by customer is reported as LOST card
- If customer is making more then 10 transaction in 1 minute
- If Customer is making transaction more then 2 different cities in 1 minute 

## Steps
- Start transaction producer "BankServer", this will keep producing data from "bank_data" file after 0.5 secs on 9996 socket
- 
```
./bin/flink run /Users/ketangote/workspace_learning/kinesis-quickstart/09-flink-analytics/09-bank-fraud-detection/target/09-bank-fraud-detection-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```
- Above will consume data from "alarmed_cust & lost_card" file & will connect on socket 9996 & will triggger flaged transaction.
- <img width="1240" alt="Screen Shot 2021-12-21 at 12 51 06 PM" src="https://user-images.githubusercontent.com/23295769/146896705-d7b45cc6-5ca3-4dcf-9db8-88d0cc21bd4a.png">

