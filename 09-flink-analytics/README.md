# Kinesis Data Analytics using Stream Application (Flink)

In this demo we will create flink application which will consume data from one stream  and do data transformation and then stream to another stream.

## Steps
- Create two data streams
    - stock-input-stream
    - stock-output-stream

- [Start Stock Producer](https://github.com/MetaArivu/Kinesis-Quickstart/tree/main/08-analytics-sql-application/07-kinesis-stock-producer)
    - This will keep Producing Stock with Ramdom Price and push data to stock-input-stream
    - <img width="1680" alt="Screen Shot 2021-12-18 at 10 40 06 AM" src="https://user-images.githubusercontent.com/23295769/146629968-f206596d-4173-41f1-88eb-66288796a4f4.png">

- Create Stream Application
    - <img width="1335" alt="Screen Shot 2021-12-18 at 11 03 33 AM" src="https://user-images.githubusercontent.com/23295769/146630565-9f58e8da-74b3-46fc-8688-6c11613e13c8.png">
    - <img width="799" alt="Screen Shot 2021-12-18 at 11 04 01 AM" src="https://user-images.githubusercontent.com/23295769/146630567-ba07a4b0-0567-4176-9ca0-17cd77d7a08c.png">
    - <img width="831" alt="Screen Shot 2021-12-18 at 11 04 11 AM" src="https://user-images.githubusercontent.com/23295769/146630573-026a552f-aaeb-45f2-a2c9-6c34b135d434.png">

- Build [Flink Application](https://github.com/MetaArivu/Kinesis-Quickstart/tree/main/09-flink-analytics/01-kinesis-simple-flink-example) and upload jar(kinesis-simple-flink-example-1.0.jar) on S3 

- Configure Application
    - <img width="1330" alt="Screen Shot 2021-12-18 at 11 07 30 AM" src="https://user-images.githubusercontent.com/23295769/146630734-51307b36-fc92-4c62-a465-34f834f87098.png">

    - Select Flink Application JAR from S3
    - <img width="852" alt="Screen Shot 2021-12-18 at 11 09 44 AM" src="https://user-images.githubusercontent.com/23295769/146630737-3d0c7f2c-ec9a-4aae-a302-580b1df90193.png">
    - Save the changes (This will take few minutes)
    - Start the Application
    - <img width="1317" alt="Screen Shot 2021-12-18 at 11 14 33 AM" src="https://user-images.githubusercontent.com/23295769/146630799-b1bae5ca-b14c-40ae-ae29-7bbc8afcb34f.png">
    - Starting application will take few minutes
    - Check Cloud Watch, There you will be able to see Flink Application Logs as below 
    - <img width="1400" alt="Screen Shot 2021-12-18 at 11 22 55 AM" src="https://user-images.githubusercontent.com/23295769/146630986-e859745a-4609-4e8e-807e-e776b75cb4f2.png">
    - Check Flink Dashboard, you will be able to see running jobs
    - <img width="1676" alt="Screen Shot 2021-12-18 at 11 23 18 AM" src="https://user-images.githubusercontent.com/23295769/146631013-ea723e8f-b667-4522-8905-390e890be302.png">



- [Start Stock Consumer](https://github.com/MetaArivu/Kinesis-Quickstart/tree/main/08-analytics-sql-application/07-kinesis-stock-consumer)
    - This will consume data from stock-output-stream, data on this is produced by above FLINK application
    - <img width="1451" alt="Screen Shot 2021-12-18 at 11 24 56 AM" src="https://user-images.githubusercontent.com/23295769/146631038-d610b970-532a-487c-bfc9-af396e3705d2.png">

