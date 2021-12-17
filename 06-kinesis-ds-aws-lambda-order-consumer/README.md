
# Delivery Stream With AWS Lambda

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

- Deploy Project. This command will guid to deploy AWS lambda on cloud
    ```
    $ sam deploy --guided
    ```