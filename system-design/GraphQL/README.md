## Tech

Backend setup

```
npm install apollo-server graphql
```

```
node index.js
```

UI query

```
query {
  posts {
    id
    title
    author {
      name
    }
  }
}
```

frontend code query

```
const query = `
  query {
    posts {
      id
      title
      author {
        name
      }
    }
  }
`;

fetch('http://localhost:4000/', { // Replace with your GraphQL endpoint URL
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
    'Accept': 'application/json',
  },
  body: JSON.stringify({ query })
})
.then(response => response.json())
.then(data => console.log('data returned:', data))
.catch(error => console.error('Error fetching data: ', error));

```

## Notes

GraphQL offers several benefits over traditional RESTful APIs, addressing many of the limitations found in REST. Here are the key advantages:

1. Efficient Data Loading
   Single Request: In a RESTful architecture, gathering data often requires multiple requests to different endpoints. In contrast, GraphQL allows clients to fetch all needed data in a single request by specifying exactly what is needed. This is particularly beneficial for complex systems with interconnected data or for applications like mobile apps where minimizing network requests is crucial.

No Over-fetching or Under-fetching: GraphQL avoids the issue of downloading unnecessary data (over-fetching) or needing additional requests for missing data (under-fetching) that is common with fixed REST endpoints. Each client can specify exactly what data it needs.

2. Strongly Typed Schema
   Self-documenting: GraphQL APIs are defined with a strong type system. The schema acts as a contract between the client and the server, and can also serve as excellent documentation for what queries, mutations, and data types are available in the API.

API Evolution Without Versioning: Adding new fields and types to a GraphQL API doesn’t affect existing queries. Clients can start using new API features without impacting existing queries, which reduces the need for versioning.

3. Real-Time Data with Subscriptions
   Built-in Support for Real-Time Data: GraphQL supports subscriptions natively. This enables clients to maintain a real-time connection to the server, receiving live updates when data changes. This is ideal for applications that require real-time features such as chat, live updates, or collaborative editing.
4. Improved Query Efficiency and Performance
   Batched Requests: GraphQL queries can include multiple sub-queries that are resolved in a single round-trip. RESTful services might require each resource to be retrieved with an individual HTTP request.

Smart Caching: With a strongly typed system, tools like Apollo Client can cache query results intelligently on the client side, minimizing the need to re-fetch data and thus enhancing the performance of applications.

5. Developer Experience and Tooling
   Enhanced Developer Experience: GraphQL offers introspection capabilities, allowing developer tools to provide helpful utilities like auto-completion, real-time error highlighting, and more, leading to a more robust development experience.

Rich Ecosystem and Community Support: The growing adoption of GraphQL has fostered a rich ecosystem of tools, libraries, extensions, and community support, which helps in accelerating development and resolving issues more efficiently.

6. Flexibility for Front-End Development
   Front-End Agility: Front-end teams can make significant changes to their data requirements without depending on backend teams to adjust API endpoints. This decouples frontend development from backend implementations, enabling faster iterations and feature developments.
