# Billogram Technical Evaluation of Per-Erik Björkstad

This is a solution to the programming task in the technical evaluation from Billogram given
to Per-Erik Björkstad.

## System Overview
I envision the surrounding system is deployed and hosted in AWS. The system is
primarily built using serverless cloud services like AWS Lambda. Container services
like AWS ECS are discouraged and are only used for exceptional cases where a serverless
approach does not fit. The primary data store is DynamoDB, Rest API:s are exposed using
API Gateway and Kinesis is used to notify other systems and services, when e.g. a discount
code has been consumed by a user.

## Discount code manager Overview

The discount code manager in this repository consists of an AWS Lambda function that exposes
one Rest API endpoint /discount. Supported operations are Post and Get. Post is used by brands
to create discount codes while Get is used by users to retrieve discount codes.

An overview diagram of the solution can be found [here](https://drive.google.com/file/d/1zpTIJVYaNdUyAUGVwWk_PpxIjzIFDIe_/) 

## Not covered by this implementation

* Authentication
  - Login using Oauth2 and AWS Cognito as userpool and token provider
* Input data validation
  - Validate that a brand exists
  - Validate that a user exists
* Async assignment of discount codes
  - Use DynamoDB transactions
* Generate Rest documentation
  - Use Spring REST Docs or Swagger
