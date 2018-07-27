### Order Management Micro Service ###
High Level Uses cases:
1)	Order Submission
2)	Order Retrieval
3)	Payment Submission
4)	Review Order Summary Status

GitHub: git@github.com:anandavignes/order-management.git

Postman Collection: https://www.getpostman.com/collections/fd27c682f92188f81193

	To run locally -> Right click, then run as Spring Boot App

	To navigate the code, Controller (Order and Payment) -> Service -> Repository -> Database.

	This application uses H2 in-memory database for persistence (#scope - runtime). 

	Unit and Integration Test cases exists under test folder.

Note: Spring security configuration is commented for local development testing scenarios

Next steps:
1)	Enable Authentication and Authorization
2)	API Gateway integration for API management
3)	Deploy this Micro service in a Docker OR Cloud Foundry - Waden container
4)	Develop a RAML or Swagger API specification
5)	Automated Functional Testing
6)	Performance and Capacity Testing
7)	Develop monitoring plan - APM, Synthetic and Host level monitoring enablement
8)	Chaos Monkey exercise for better reliability readiness
9)	Production - Blue – Deploy
10)	Production - Green - Live launch
