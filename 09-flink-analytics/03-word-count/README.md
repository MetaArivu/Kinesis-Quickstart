
# Wordcount Example Using FLINK API

- Create a jar 
- Run jar using below command
```
./bin/flink run ~/workspace_learning/kinesis-quickstart/09-flink-analytics/03-word-count/wordcount.jar --input ~/workspace_learning/kinesis-quickstart/09-flink-analytics/03-word-count/src/main/resources/wc.txt --output  ~/workspace_learning/kinesis-quickstart/09-flink-analytics/03-word-count/src/main/resources/wcout.txt 
```
- This will take input from one file and count number of line which is starting with "N" and stream the output to another file