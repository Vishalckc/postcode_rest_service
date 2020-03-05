# postcode_rest_service
Spring-boot REST API demo integration with a 3rd party API

Method: ​ PUT
Endpoint URL: ​ /api/v1/people/{id}
Request ​"Content-Type" ​: ​"application/json"
Request Payload body:
{
​"name" ​: ​"Jack" ​,
​"addresses" ​: [ { ​"postcode" ​: ​"EC2 3CD" ​}, { ​"postcode" ​: ​"EC1 1QR" ​} ]
} R
esponse:
{ ​"id" ​: ​{id} ​}

Method: ​ GET
Endpoint URL: ​ /api/v1/people/{id}
Request ​"Content-Type" ​: ​"application/json"
Request Payload body: ​Empty/None
Response:
{
​"id" ​: ​{id},
"name" ​: ​"Jack" ​,
​"addresses" ​: [
{ ​"type" ​: ​"HOME", ​"postcode" ​: ​"EC2 3CD" ​},
{ ​"type" ​: ​"OFFICE", ​"postcode" ​: ​"EC1 1QR" ​}
]
}

Above API gets the address details from a 3rd party API/service i.e.
GET ​: ​/api/v1/address/postcodes/{postcode} ​ , which returns
{ ​"type" ​: ​"HOME", ​"postcode" ​: ​"EC2 3CD" ​} ​ in the response for that ​{postcode} ​.

This app provides a brief overview on the use of Put and Get methods along with the integration of 3rd party API (here we have used another microservice for this)
The postcode_rest_service consumes data through another microservice address data service

The two apps can be downloaded and run locally (ports 8080 and 8081) and then the master service uses API call from addressDataService through a Rest template
to generate the required data by marshalling into a JSON data with the use of a wrapper POJO.

Unit tests and an integration test (using wiremock) covers the positive test cases and can be run using a standalone wiremock server(configuration is already done)

The integration test covers both the scenarios 
	a)If the 3rd party api is up and running and 
	b)The fail safe case in case if the 3rd party API is down

The integration test case has to be just tweaked to use the "dummyServer" instead of "apiHostedServer for wiremock server to take charge and fire the proxy service

For any issues please create a nw request or send a mail to vishalvermackc@gmail.com

Note: No need to setup any sql db. We have used collections for static data (create/update/retrieve)