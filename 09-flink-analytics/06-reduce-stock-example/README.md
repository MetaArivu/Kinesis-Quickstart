
# Stock Reduce Example Using FLINK API

In this demo we will focus on Reduce functioon

- Start socket using below command
```
nc -lk 9999
```
- Execute Jar using below command
```
./bin/flink run ~/workspace_learning/kinesis-quickstart/09-flink-analytics/06-reduce-stock-example/stock-reducer-example.jar
```
- Enter Some Data as below on Socket which we started in step1
```
1,HDFC,BANKING,1419
2,TATAMOTORS,AUTO,447
3,ICICI,BANKING,254
4,RIL,PETROLEUM,1350
5,GAIL,PETROLEUM,130
```
- Enter Above Same Stock Data with different priice as below on Socket which we started in step1
```
1,HDFC,BANKING,1420
2,TATAMOTORS,AUTO,487
3,ICICI,BANKING,252
4,RIL,PETROLEUM,1310
5,GAIL,PETROLEUM,140
```
- Check Flink Dashboard Logs
- <img width="1679" alt="Screen Shot 2021-12-20 at 2 34 50 PM" src="https://user-images.githubusercontent.com/23295769/146741219-eb7f9e28-7e06-4af5-ab50-41c8e0506ce9.png">
