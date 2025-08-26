## VS Restful

### High flexibility
Fixed-structure data exchange: The REST API requires client requests to follow a fixed structure to receive a resource. This rigid structure is easy to use, but it’s not always the most efficient means to exchange exactly the data needed.

### Overfetching and underfetching
REST APIs always return a whole dataset. For example, from a person object in the REST API, you would receive the person’s name, date of birth, address, and phone number. You would get all of this data even if you only needed a phone number.

Similarly, if you wanted to know a person’s phone number and last purchase, you would need multiple REST API requests. The URL /person would return the phone number and the URL /purchase would return purchase history.

Social media developers had to write a lot of code just to process API requests, which affected performance and user experience.

GraphQL emerged as a query-based solution. Queries can return the exact data in only one API request and response exchange.

ref: https://aws.amazon.com/compare/the-difference-between-graphql-and-rest

## Example
Example of returned data in GraphQL
When you use GraphQL, only the data specified in the structure given by the client is returned.

request:
```
GET /graphql?query{post(id: 1) {id title content}} returns only the first post:
```

response:
```
{

  "data": {

    "posts": [

      {

        "id": "1",

        "title": "First Post",

        "content": "This is the content of the first post."

      },

]}}
```

## When to use GraphQL vs. REST

For example, GraphQL is likely a better choice if you have these considerations:

1. You have limited bandwidth, and you want to minimize the number of requests and responses
2. You have client requests that vary significantly, and you expect very different responses

