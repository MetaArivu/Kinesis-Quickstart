# Kinesis Data Analytics for legacy SQL applications for running SQL queries

In this demo we will use SQL Application legacy to get stream data and send new data to another stream. For demo we will be processing Stock Price data and using SQL & Windowing we will determine trend of stock i.e is it Increased or decreased.

## Steps
- Create two data streams
    - stock-input-stream
    - stock-output-stream

- [Start Stock Producer](https://github.com/MetaArivu/Kinesis-Quickstart/tree/main/08-analytics-sql-application/07-kinesis-stock-producer)
    - This will keep Producing Stock with Ramdom Price and push data to stock-input-stream
    - <img width="1680" alt="Screen Shot 2021-12-18 at 10 40 06 AM" src="https://user-images.githubusercontent.com/23295769/146629968-f206596d-4173-41f1-88eb-66288796a4f4.png">

- Create SQL Legacy Application
    - <img width="825" alt="Screen Shot 2021-12-18 at 10 19 24 AM" src="https://user-images.githubusercontent.com/23295769/146629388-c143489f-0307-4a23-81ee-15730f1ed23b.png">
    - Configure Source Stream i.e from which datastream data should be pickeup
        - <img width="1337" alt="Screen Shot 2021-12-18 at 10 21 20 AM" src="https://user-images.githubusercontent.com/23295769/146629424-41471835-3730-415e-aaef-44b6e6bb90a0.png">
        - Click on Discover Schema (Before this make sure some data is already produced on stock-input-stream using stock-producer)
        - <img width="1283" alt="Screen Shot 2021-12-18 at 10 27 05 AM" src="https://user-images.githubusercontent.com/23295769/146629604-4de989f1-da43-457e-b545-47032ba5d070.png">
        - SAVE 
    - Configure SQL which will work on input stream and produce data for out stream
        - Click on Configure SQL
        - <img width="1339" alt="Screen Shot 2021-12-18 at 10 28 31 AM" src="https://user-images.githubusercontent.com/23295769/146629643-40c28272-b68f-4cd7-9ccb-0402ace36ac5.png">
        - Copy SQL from [sql-legacy.sql]() provided in repo
        - Click on Save & Run Application (This will take a miniute)
        - Once it is saved you will be able to see Output Stream and Input Stream which are defined in SQL as below. This will show which data is consumed and produced by SQL.
        - <img width="1313" alt="Screen Shot 2021-12-18 at 10 32 52 AM" src="https://user-images.githubusercontent.com/23295769/146629759-dc68cfdf-cc8e-46f4-bef7-3f6ab209b0d6.png">
        - <img width="1311" alt="Screen Shot 2021-12-18 at 10 33 08 AM" src="https://user-images.githubusercontent.com/23295769/146629762-5d9c68ea-d3c8-4b5b-a1c4-f159b3491e8c.png">
    - Configure Destination
        - Click on Add Destination
        - <img width="1341" alt="Screen Shot 2021-12-18 at 10 35 35 AM" src="https://user-images.githubusercontent.com/23295769/146629825-8975bd5c-112b-45e8-a71c-512a68d654ea.png">
        - Connect Output Datastream
        <img width="860" alt="Screen Shot 2021-12-18 at 10 36 48 AM" src="https://user-images.githubusercontent.com/23295769/146629869-77c773aa-2a7f-47c4-8057-30f9fd5794c2.png">
        - Select in Application Stream 
        - <img width="828" alt="Screen Shot 2021-12-18 at 10 37 22 AM" src="https://user-images.githubusercontent.com/23295769/146629875-0b09ca77-0d75-4359-80bd-3da1d3fcf232.png">
        - Click on Save

- [Start Stock Consumer](https://github.com/MetaArivu/Kinesis-Quickstart/tree/main/08-analytics-sql-application/07-kinesis-stock-consumer)
    - This will consum data from stock-output-stream, data on this is produced by above SQL application
    - <img width="1675" alt="Screen Shot 2021-12-18 at 10 44 47 AM" src="https://user-images.githubusercontent.com/23295769/146630064-9fedbf59-385d-457e-b9fd-7f625a9fafed.png">
    - It will show stock price has trend as INCREASING or DECREASING
    






    


