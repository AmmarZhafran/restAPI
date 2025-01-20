# User  API Spec

## Register User

Endppoind : POST /api/users

Request Body : 
````json
{
    "username" : "ammar",
    "password" : "12345",
    "name"     : "m ammar"
}
````

Response Body (Success) :
```json
{
  "data" : "OK"
}

```
Response Body (Failed) :
```json
{
 
  "errors" : "username must not blank, ???"
}

```

## Login User
Endppoind : POST /api/auth/login

Request Body :
````json
{
    "username" : "ammar",
    "password" : "12345"
}
````

Response Body (Success) :
```json
{
  "data" : {
    "token" : "TOKEN",
    "expiredAt": 1234567890 
  }
}

```
Response Body (Failed 401) :
```json
{
  
  "errors" : "password salah"
}

```
## Get User
Endppoind : GET /api/users/current

Request Header : 
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : {
    "useername" : "ammar",
    "name" : "m ammar"
  }
}

```
Response Body (Failed 401) :
```json
{

  "errors" : "Unauthorized"
}

```
## Update User
Endppoind : PUT /api/users/current


Request Header :
- X-API-TOKEN : Token (Mandatory)

Request Body :
````json
{
    
    "password" : "54321",
    "name"     : "m ammar zhafran"
}
````

Response Body (Success) :
```json
{
  "data" : "OK"
}

```
Response Body (Failed) :
```json
{
  "data" : "Failed",
  "errors" : "username must not blank, ???"
}

```

## Logout User
Endppoind : DELETE /api/auth/logout


Request Header :
- X-API-TOKEN : Token (Mandatory)

Response Body (Success) :
```json
{
  "data" : "OK"
}