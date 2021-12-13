# Discount code manager
The discount code manager consists of an AWS Lambda function that exposes one
Rest API endpoint /discount. Supported operations are Post and Get. Post is used by
brands to create discount codes while Get is used by users to retrieve discount codes.

The project folder includes a `template.yml` file. You can use this [SAM](https://github.com/awslabs/serverless-application-model) file to deploy the project to AWS Lambda and Amazon API Gateway or test in local with the [SAM CLI](https://github.com/awslabs/aws-sam-cli). 

## Pre-requisites
* [AWS CLI](https://aws.amazon.com/cli/)
* [SAM CLI](https://github.com/awslabs/aws-sam-cli)
* [Gradle](https://gradle.org/)

## Building the project
You can use the SAM CLI to quickly build the project
```bash
$ cd discount-code-manager
$ sam build
Building resource 'DiscountCodeManagerFunction'
Running JavaGradleWorkflow:GradleBuild
Running JavaGradleWorkflow:CopyArtifacts

Build Succeeded

Built Artifacts  : .aws-sam/build
Built Template   : .aws-sam/build/template.yaml

Commands you can use next
=========================
[*] Invoke Function: sam local invoke
[*] Test Function in the Cloud: sam sync --stack-name {stack-name} --watch
[*] Deploy: sam deploy --guided
```

## Testing locally with the SAM CLI

From the project root folder - where the `template.yml` file is located - start the API with the SAM CLI.

```bash
$ sam local start-api

...
Mounting DiscountCodeManagerFunction at http://127.0.0.1:3000/{proxy+} [DELETE, GET, HEAD, OPTIONS, PATCH, POST, PUT]
You can now browse to the above endpoints to invoke your functions. You do not need to restart/reload SAM CLI while working on your functions, changes will be reflected instantly/automatically. You only need to restart SAM CLI if you update your AWS SAM template
2021-12-13 13:47:13  * Running on http://127.0.0.1:3000/ (Press CTRL+C to quit)
...
```

Using a new shell, you can send a test request:

```bash
$ curl --request POST \
   --url http://127.0.0.1:3000/discount \
   --header 'Content-Type: application/json' \
   --data \
    '{
      "brandId": "95AAC5FF-12F1-49A0-86F6-BA41D3EC7DF6",
      "nrOfCodes": 1
    }'
    
 {
    "codes": [
        "PFVDXC"
    ]
}
```

## Deploying to AWS
To deploy the application in your AWS account, you can use the SAM CLI's guided deployment process and follow the instructions on the screen

```
$ sam deploy --guided
```

Once the deployment is completed, the SAM CLI will print out the stack's outputs, including the new application URL. You can use `curl` or a web browser to make a call to the URL

```
...
-------------------------------------------------------------------------------------------------------------
OutputKey-Description                        OutputValue
-------------------------------------------------------------------------------------------------------------
DiscountCodeManagerApi - URL for application            https://xxxxxxxxxx.execute-api.us-west-2.amazonaws.com/Prod/pets
-------------------------------------------------------------------------------------------------------------
```

Copy the `OutputValue` into a browser or use curl to test your first request:

```bash
$ curl --request POST \
  --url https://pq6zqu8cc1.execute-api.eu-north-1.amazonaws.com/Prod/discount/ \
  --header 'Content-Type: application/json' \
  --data '{
	"brandId": "95AAC5FF-12F1-49A0-86F6-BA41D3EC7DF6",
	"nrOfCodes": 1
}' | python -m json.tool

{
    "codes": [
        "UPLMWG"
    ]
}
```
