# Deliivery Stream Application

## Setup Of Delivery Stream
Data Stream -> KDF -> S3

- Choose Source & Desitination
<img width="838" alt="Screen Shot 2021-12-15 at 1 43 23 PM" src="https://user-images.githubusercontent.com/23295769/146148641-a46e245b-15f3-49ca-ab28-9dec486040fd.png">

- Select Data Stream 
<img width="844" alt="Screen Shot 2021-12-15 at 1 44 57 PM" src="https://user-images.githubusercontent.com/23295769/146148818-5f1677ec-6e64-4075-8955-00b21c1d4d50.png">

- Delivery Stream Name
<img width="837" alt="Screen Shot 2021-12-15 at 1 46 11 PM" src="https://user-images.githubusercontent.com/23295769/146149007-f89d84b0-a98f-439a-b995-cdfde58c063b.png">

- Select Destination Setting For S3
<img width="830" alt="Screen Shot 2021-12-15 at 1 48 15 PM" src="https://user-images.githubusercontent.com/23295769/146149331-2b187221-23d9-4c85-b4f2-3083c851a4e2.png">

- Change Buffer Setting (Only For Testing Purpose Follow Below)
<img width="799" alt="Screen Shot 2021-12-15 at 1 49 11 PM" src="https://user-images.githubusercontent.com/23295769/146149544-13b88876-ddf4-4f0b-b615-a388db728f46.png">

- Finallu Click on "Create Delivery Stream"


## Produce Data On Stream
As we are using order stream, produce some data on "order-in-data-stream" using [order producing application](https://github.com/MetaArivu/Kinesis-Quickstart/tree/main/03-kinesis-order-producer)

## Check Data In S3
- Go To Data Stream Application which we created
<img width="1325" alt="Screen Shot 2021-12-15 at 2 24 10 PM" src="https://user-images.githubusercontent.com/23295769/146154678-52c7a4bb-bda9-43e6-919f-e646dfbf7aa2.png">
- Select S3 Bucket
<img width="1311" alt="Screen Shot 2021-12-15 at 2 25 58 PM" src="https://user-images.githubusercontent.com/23295769/146154872-6b48ec05-f833-4af1-b2a2-cdda210ec3da.png">
- You Will be able see different files which will have order data
<img width="1366" alt="Screen Shot 2021-12-15 at 2 27 21 PM" src="https://user-images.githubusercontent.com/23295769/146155081-0387f5ff-0908-4c52-99ff-729b4fa62617.png">

