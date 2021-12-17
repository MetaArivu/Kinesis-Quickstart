
# Delivery Stream With AWS Lambda

In this demo we will consume data stream data using AWS Lambda.

We will using [SAM](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/what-is-sam.html) to create AWS Lambda for Deleivery Stream 

- SAM Installation, Follow this [link](
https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install-mac.html) to install SAM Locally

- Create project using below command. Note as per your requirement select respective options e.g language (node, java etc), aws region, aws policy.
    ```
    sam init
    ```
- Once project is created update [template.yml](https://github.com/MetaArivu/Kinesis-Quickstart/blob/main/06-kinesis-ds-aws-lambda-order-consumer/template.yaml). More info on template can be found [here](https://docs.aws.amazon.com/lambda/latest/dg/with-kinesis-example-use-app-spec.html).

- Build Project
    ```
    sam build
    ```
    <img width="1675" alt="Screen Shot 2021-12-17 at 9 46 40 PM" src="https://user-images.githubusercontent.com/23295769/146575027-1baa4ff1-57a5-4c9c-bdd1-377f51f5379f.png">


- Deploy Project. This command will guid to deploy AWS lambda on cloud
    ```
    $ sam deploy --guided
    ```
    <img width="1479" alt="Screen Shot 2021-12-17 at 9 54 48 PM" src="https://user-images.githubusercontent.com/23295769/146576191-87eb0300-9955-4f13-9af7-304aecf5cd92.png">
    <img width="1662" alt="Screen Shot 2021-12-17 at 9 55 00 PM" src="https://user-images.githubusercontent.com/23295769/146576204-39747854-0e58-4175-bb3a-2a40c052bafe.png">

- This will create stack on Cloud Formation
    <img width="1388" alt="Screen Shot 2021-12-17 at 9 56 36 PM" src="https://user-images.githubusercontent.com/23295769/146576449-be799718-b20a-47e3-afca-d6451de69473.png">

- You can check resource section on Cloud Formation
    <img width="1416" alt="Screen Shot 2021-12-17 at 9 58 08 PM" src="https://user-images.githubusercontent.com/23295769/146576600-2cb155a9-e2b5-4ef4-8372-bf395b3ed0fc.png">

- Check AWS Lambda by clicking on "OrdersConsumerFunction" in above resource section. You will be able to see all AWS Lambda configuration here.
<img width="1585" alt="Screen Shot 2021-12-17 at 9 59 41 PM" src="https://user-images.githubusercontent.com/23295769/146576864-a3dec763-c85e-4985-b134-d42fc7eca617.png">

- Publish some order data on order-in-data-stream using [order-producer](https://github.com/MetaArivu/Kinesis-Quickstart/tree/main/03-kinesis-order-producer)

- Check the AWS Lamda Logs on Cloud watch 
    - Click on cloudwatch button 
    <img width="1575" alt="Screen Shot 2021-12-17 at 10 02 26 PM" src="https://user-images.githubusercontent.com/23295769/146577167-d133b333-bf43-4d92-96c7-7afaef3cde26.png">
    - Check log in cloudwatch
    <img width="1402" alt="Screen Shot 2021-12-17 at 10 03 32 PM" src="https://user-images.githubusercontent.com/23295769/146577353-0f311c55-4cc3-4795-bb20-add8cbb7d2ee.png">



