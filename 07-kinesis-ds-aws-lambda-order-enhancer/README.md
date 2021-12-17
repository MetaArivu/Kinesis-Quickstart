# Delivery Stream ->  AWS Lambda -> S3

In this demo we will consume data stream data using AWS Lambda and stream data to another stream

### Note: We have used SAM command as previous and deploy AWS Lambda.

### Create Delivery Stream

- <img width="835" alt="Screen Shot 2021-12-17 at 10 20 58 PM" src="https://user-images.githubusercontent.com/23295769/146579946-60da518d-0c94-493f-992e-7477ae4d0273.png">

- <img width="842" alt="Screen Shot 2021-12-17 at 10 21 39 PM" src="https://user-images.githubusercontent.com/23295769/146579978-1b576769-c877-4914-89cd-b7bea7a81fd1.png">

- <img width="836" alt="Screen Shot 2021-12-17 at 10 22 28 PM" src="https://user-images.githubusercontent.com/23295769/146579995-b283439d-176c-4941-b72f-04d058aaedcb.png">

- <img width="795" alt="Screen Shot 2021-12-17 at 10 23 10 PM" src="https://user-images.githubusercontent.com/23295769/146580031-88d8907f-618e-408a-b8b4-d62f5edc687c.png">


## Producer Order Data
 - Use Order Producer [Application](https://github.com/MetaArivu/Kinesis-Quickstart/tree/main/03-kinesis-order-producer) to produce order data

## Check AWS Lambda Log on AWS Cloud Watch
 - <img width="1385" alt="Screen Shot 2021-12-17 at 10 31 13 PM" src="https://user-images.githubusercontent.com/23295769/146580914-5090b8da-1dcc-4e1a-87c3-22901ed0a891.png">

## Check S3, you will be able to see data which is produced on order-data-stream
- <img width="1366" alt="Screen Shot 2021-12-17 at 10 36 13 PM" src="https://user-images.githubusercontent.com/23295769/146581567-9ee25114-a9f1-4269-b70f-a33d14e7b580.png">
