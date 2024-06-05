const { ApolloServer, gql } = require("apollo-server");

// Define your schema here
const typeDefs = gql`
  type Post {
    id: ID!
    title: String!
    author: Author!
  }

  type Author {
    id: ID!
    name: String!
    posts: [Post]!
  }

  type Query {
    posts: [Post]
    author(id: ID!): Author
  }
`;

// Sample data
const authors = [
  { id: "1", name: "Jane Doe" },
  { id: "2", name: "John Doe" },
];

const posts = [
  { id: "1", title: "GraphQL Introduction", authorId: "1" },
  { id: "2", title: "Apollo Server Basics", authorId: "1" },
  { id: "3", title: "Simple GraphQL Setup", authorId: "2" },
];

// Define the resolvers
const resolvers = {
  Query: {
    posts: () => posts,
    author: (_, { id }) => authors.find((author) => author.id === id),
  },
  Author: {
    posts: (author) => posts.filter((post) => post.authorId === author.id),
  },
  Post: {
    author: (post) => authors.find((author) => author.id === post.authorId),
  },
};

// Create the Apollo Server
const server = new ApolloServer({ typeDefs, resolvers });

// Start the server
server.listen().then(({ url }) => {
  console.log(`🚀 Server ready at ${url}`);
});
