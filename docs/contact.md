# Contact API Spec

## Create Contact

Endpoind : POST /api/contacts

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body : 
```json
{
  "data": {
    "firstname": "muhamad",
    "lastname": "ammar",
    "email": "ammar@gmail.com",
    "phone": "0987656789"
  }
  
}
```
Response Body (Success) :
```json

{
  "data": {
    "id" : "random string",
    "firstname": "muhamad",
    "lastname": "ammar",
    "email": "ammar@gmail.com",
    "phone": "0987656789"
  }

}

```
Response Body (Failed) :
```json
{
"errors" : "email/ phone invalid..."
}
```
## Update Contact

Endpoind : PUT /api/contacts/{idContacts}

Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :
```json
{
  "firstname": "muhamad",
  "lastname": "ammar",
  "email": "ammar@gmail.com",
  "phone": "0987656789"
}
```

Response Body (Success) :

```json
{
  "data": {
    "id" : "random string",
    "firstname": "muhamad",
    "lastname": "ammar",
    "email": "ammar@gmail.com",
    "phone": "0987656789"
  }

}
```

Response Body (Failed) :
```json
{
"errors" : "email/ phone invalid..."
}
```
## Get Contact

Endpoind : GET /api/contacts/{idContacts}

Request Header :
- X-API-TOKEN : Token (Mandatory)


Response Body (Success) :
```json
{
  "data": {
    "id" : "random string",
    "firstname": "muhamad",
    "lastname": "ammar",
    "email": "ammar@gmail.com",
    "phone": "0987656789"
  }

}
```
Response Body (Failed, 404) :
```json
{
"errors" : "contac is not found"
}
```
## Search Contact

Endpoind : GET /api/contacts

Query Params : 

- name : String, contact firstname or lastname, using like query, optional
- phone : String, contact phone, using like query, optional
- email : String, contact email, using like query, optional
- page : Integer, start from 0, default 0
- size : Integer, default 10


Request Header :
- X-API-TOKEN : Token (Mandatory)


Response Body (Success) :
```json
{
  "data":[ 
    {
    "id" : "random string",
    "firstname": "muhamad",
    "lastname": "ammar",
    "email": "ammar@gmail.com",
    "phone": "0987656789"
  }
  ],
  "paging": {
    "totalPage" : 10,
    "currentPage" : 10,
    "size" : 10
  }
}
```

Response Body (Failed) :
```json
{
"errors" : "Unauthorized"
}
```
## Remove Contact

Endpoind : DELETE /api/contacts/{isContacts}

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
"errors" : "contact is not found"
}
```