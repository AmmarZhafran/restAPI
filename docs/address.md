# Address API Spec

## Create Address

Endpoint : POST /api/contacts/{idContact}/addresses

Request Header : 

- X-API-TOKEN : Token (Mandatory)

Request Body
```json
{
  "street" : "jalan apa",
  "city" : " kota",
  "proincy" : "proinsi mana",
  "country": "negara mana",
  "alamat code" : "12345"
}
```
Response Body (Success) :
```json
{
  "data" : {
    "id" : "string random",
    "street" : "jalan apa",
    "city" : " kota",
    "proincy" : "proinsi mana",
    "country": "negara mana",
    "alamat code" : "12345"
  }
}
```
Response Body (Failed) :
```json
{
  "errors" : "Contact is  not found"
}
```
## Update Address

Endpoint : PUT /api/contacts/{idContact}/addresses/{idAddress}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Request Body
```json
{
  "street" : "jalan apa",
  "city" : " kota",
  "proincy" : "proinsi mana",
  "country": "negara mana",
  "alamat code" : "12345"
}
```
Response Body (Success) :
```json
{
  "data" : {
    "id" : "string random",
    "street" : "jalan apa",
    "city" : " kota",
    "proincy" : "proinsi mana",
    "country": "negara mana",
    "alamat code" : "12345"
  }
}
```
Response Body (Failed) :
```json
{
  "errors" : "Address is  not found"
}
```
## Get Address

Endpoint : GET /api/contacts/{idContact}/addresses/{idAddress}

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : {
    "id" : "string random",
    "street" : "jalan apa",
    "city" : " kota",
    "proincy" : "proinsi mana",
    "country": "negara mana",
    "alamat code" : "12345"
  }
}
```
Response Body (Failed) :
```json
{
  "errors" : "Address is  not found"
}
```

## Remove Address

Endpoint : DELETE /api/contacts/{idContact}/addresses

Request Header :

- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : "OK"
}
```

Response Body (Failed) :
```json
{
  "errors" : "Address is  not found"
}
```
## list Address

Endpoint : GET /api/contacts/{idContact}/addresses/list

Request Header :

- X-API-TOKEN : Token (Mandatory)


Response Body (Success) :
```json
{
  "data" :[
  {
    "id" : "string random",
    "street" : "jalan apa",
    "city" : " kota",
    "proincy" : "proinsi mana",
    "country": "negara mana",
    "alamat code" : "12345"
  }
    ]
}
```
Response Body (Failed) :
```json
{
  "errors" : "Contact is  not found"
}
```
