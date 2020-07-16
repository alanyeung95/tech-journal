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

<b>But normally, API gatewiay like ambassador will verify the jwt then pass the request to microservices</b>

## Using Jwt with Gsuite
https://developers.google.com/identity/protocols/oauth2/service-account

# static typing vs dymanic typing
## static typing
A statically typed language has a type system that is checked at compile time by the implementation (a compiler or interpreter). The type check rejects some programs, and programs that pass the check usually come with some guarantees; for example, the compiler guarantees not to use integer arithmetic instructions on string.

So in Java for example:
```
String s = "abcd";
```
s will "forever" be a String. During its life it may point to different Strings (since s is a reference in Java). It may have a null value but it will never refer to an Integer or a List. That's static typing.

## dymanic typing
Values used at run time are classified into types.
```
$s = "abcd";          // $s is a string
$s = 123;             // $s is now an integer
$s = array(1, 2, 3);  // $s is now an array
$s = new DOMDocument; // $s is an instance of the DOMDocument class
```
That's dynamic typing.

# Strong typing 
Compare that to Ruby:

val = "abc" + 123
which is a runtime error because in Ruby the object 123 is not implicitly converted just because it happens to be passed to a + method. In Ruby the programmer must make the conversion explicit:

val = "abc" + 123.to_s
Comparing PHP and Ruby is a good illustration here. Both are dynamically typed languages but PHP has lots of implicit conversions and Ruby (perhaps surprisingly if you're unfamiliar with it) doesn't.


# Weak typing
Weak typing implies that the compiler does not enforce a typing discpline, or perhaps that enforcement can easily be subverted.

Consider a classic PHP "starts with" problem:
```
if (strpos('abcdef', 'abc') == false) {
  // not found
}
```
The error here is that strpos() returns the index of the match, being 0. 0 is coerced into boolean false and thus the condition is actually true. The solution is to use === instead of == to avoid implicit conversion.

<br>Php is dymanic type and weak type. This example illustrates how a combination of implicit conversion and dynamic typing can lead programmers astray.</br>
