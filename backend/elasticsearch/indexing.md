## Basic Concepts of Elasticsearch Indexing

1. **Documents and Indices**:
   - **Documents**: The basic unit of data in Elasticsearch, typically represented as JSON objects. Each document corresponds to a searchable entity in your data.
   - **Indices**: Logical partitions in Elasticsearch where documents are stored. An index can be thought of as an optimized collection of documents and is analogous to a database in relational databases.

2. **Inverted Index**:
   - The core of Elasticsearch's search capabilities is the inverted index, which is similar to a textbook index where for each word there's a list of pages where it appears. In Elasticsearch, the inverted index maps terms to the documents containing those terms. This structure allows for fast full-text searches.

## Indexing Process in Elasticsearch

Here’s a step-by-step overview of how data is indexed in Elasticsearch:

### Step 1: Data Ingestion
- **Document Addition**: When you add a document to an index in Elasticsearch, the document fields are processed by a chain of character filters, tokenizer, and token filters to extract indexable terms.
- **Analyzers and Tokenizers**: During indexing, analyzers (comprising character filters, a tokenizer, and token filters) process the text fields of the documents to convert them into terms (tokens) that are added to the inverted index. The choice of tokenizer and filters can drastically affect the search capabilities and performance.

### Step 2: Index Creation and Management
- **Mappings**: Before or during indexing, you can define mappings that specify how each field in a document should be handled. For instance, you can specify which fields should be searchable, which analyzer to use for each field, and whether a field should be stored in the index.
- **Sharding and Replication**: Elasticsearch allows indices to be subdivided into shards, which can be distributed across multiple nodes, facilitating scalability and resilience. Indices can also be replicated across several nodes to increase fault tolerance.

### Step 3: Inverted Index Utilization
- **Index Storage**: After processing, the terms are stored in an inverted index format on the disk. Elasticsearch uses Lucene under the hood for managing this low-level data structure.
- **Updating the Index**: When documents are updated or deleted, the changes are initially recorded in an in-memory data structure and later flushed to the inverted index. Elasticsearch also maintains a transaction log to ensure data is not lost in case of a failure.

### Step 4: Searching
- **Query Execution**: When a search query is executed, Elasticsearch uses the inverted index to quickly find the documents that match the query terms. It then uses various scoring algorithms to rank these documents based on relevance.

## Performance and Optimization
- **Index Refresh**: Elasticsearch periodically refreshes indices (making new documents searchable) and merges smaller index segments into larger ones to optimize the search performance.
- **Customization**: Users can customize indexing strategies (like choosing between different types of analyzers) based on the specific requirements of the content (e.g., language-specific analyzers for handling text in different languages).

## Use Cases
Elasticsearch is particularly effective for scenarios requiring full-text search, such as:
- Searching large volumes of text-heavy information, such as logs or web page content.
- Implementing complex search requirements, including faceted search, full-text search, and real-time querying in applications like e-commerce platforms and social media sites.

ref: https://www.elastic.co/blog/what-is-an-elasticsearch-index

## Dynamic Mapping
```
POST /blogs/_doc/1
{
  "title": "Introduction to Elasticsearch",
  "views": 1500,
  "tags": ["search", "elasticsearch", "text"],
  "published": "2020-01-01T12:10:30Z"
}
```

Here, Elasticsearch will automatically create mappings based on the inferred data types:

title: string field, mapped as text (with an additional keyword field if the fields setting is enabled by default).
views: integer field, mapped as integer.
tags: array of strings, each element mapped as text.
published: date field, mapped as date.

## Custom Mapping
```
PUT /blogs
{
  "mappings": {
    "properties": {
      "title": {
        "type": "text",
        "fields": {
          "raw": {
            "type": "keyword"
          }
        }
      },
      "views": {
        "type": "integer"
      },
      "tags": {
        "type": "text",
        "fielddata": true
      },
      "published": {
        "type": "date",
        "format": "strict_date_hour_minute_second"
      }
    }
  }
}
```

title: Indexed as text for full-text search, and a multi-field raw as keyword for aggregations or sorting.
views: Stored as an integer.
tags: Stored as text and enabling fielddata to allow aggregations on text fields, which is off by default for text fields due to memory consumption concerns.
published: Mapped as a date, with a specified format to strictly interpret dates only in the given format.

## Why Use Custom Mapping?
Custom mappings are particularly useful when you need:

1. Control Over Indexing Behavior: Specify whether fields are indexed for search or used for aggregations.
2. Optimized Search Performance: Define analyzers for text fields depending on the content language or intended search functionality.
3. Data Integrity: Enforce data types and formats, preventing indexing errors and ensuring that queries return expected results.
