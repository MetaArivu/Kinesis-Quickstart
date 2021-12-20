
# SOCKET Example Using FLINK API

In this demo we will take input from Stocket and Work On It.

- Start socket using below command
```
nc -lk 9999
```
- Execute Jar using below command
```
./bin/flink run ~/workspace_learning/kinesis-quickstart/09-flink-analytics/05-socket-example/socket-example.jar
```
- Enter Some Data as below on Socket which we started in step1
```
John
Jane
Jack
John
```
- Check Flink Dashboard Logs
- <img width="1677" alt="Screen Shot 2021-12-20 at 12 00 01 PM" src="https://user-images.githubusercontent.com/23295769/146722032-a268104c-b378-4ac2-8dd8-7243533d20da.png">
