# API Gateway
1.  it is an API management tool between client and backend services.
2.  as a reverse proxy to accept all API call
3.  noramlly the domain is the root api

## Pros
1.  analytics and monitoring tools
2.  after adding some new API services ,your clients will still can find all your services in the same place.

# JWT (JSON Web Token)
## Intro
In its compact form, JSON Web Tokens consist of three parts separated by dots (.), which are:

1. Header
2. Payload
3. Signature (To create the signature part you have to take the encoded header, the encoded payload, a `secret`, the algorithm specified in the header, and sign that.)

```
{
  "alg": "RS256",
  "typ": "JWT",
  "kid": "abcdef1234567890"
}
.
{
  "iss": "123456-compute@developer.gserviceaccount.com",
  "sub": "123456-compute@developer.gserviceaccount.com",
  "aud": "https://firestore.googleapis.com/",
  "iat": 1511900000,
  "exp": 1511903600
}
```

Therefore, a JWT typically looks like the following.
```
xxxxx.yyyyy.zzzzz
```

## Security
A not so clever hacker can get the payload (which is publically readable), change some data, base64 encode it and replace in the token. 

But when the payload or header changes, it becomes inconstant with the signature. And since signature can be only constructed with secret which is not publically available, hence JWT is one of the safest way to authenticate HTTP requests.

## Usage
Whenever the user wants to access a protected route or resource, the user agent should send the JWT, typically in the Authorization header using the Bearer schema. The content of the header should look like the following:
```
Authorization: Bearer <token>
```

we can also set the expire time when generate the jwt token

Any server or service can valide the jwt token

## Using Jwt with Gsuite
https://developers.google.com/identity/protocols/oauth2/service-account
