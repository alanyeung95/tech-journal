# Intro
Elasticsearch is a distributed text search engine backed by Lucene. 
ElasticSearch is very good for specific task — indexing and searching big datasets. 
It is used when you have some secondary info about your data and you need to know actual records to select. 

## Simple query string
```
GET /_search
{
  "query": {
    "simple_query_string" : {
        "query": "\"fried eggs\" +(eggplant | potato) -frittata",
        "fields": ["title^5", "body"],
        "default_operator": "and"
    }
  }
}
```

## Exact match
```
GET /_search
{
    "query": {
        "term": {
            "user": {
                "value": "Kimchy",
                "boost": 1.0
            }
        }
    }
}
```
## Fuzzy search
we can set fuzziness (Optional, string) Maximum edit distance allowed for matching.
```
GET /_search
{
    "query": {
        "fuzzy": {
            "user": {
                "value": "ki",
                "fuzziness": "AUTO",
                "max_expansions": 50,
                "prefix_length": 0,
                "transpositions": true,
                "rewrite": "constant_score"
            }
        }
    }
}
```

## analyzer & filter
Text filter in analyzer that can have better search result
```
GET /_analyze
{
  "tokenizer": "standard",
  "filter": [ "stop" ],
  "text": "a quick fox jumps over the lazy dog"
}
```
The filter produces the following tokens:
```
[ quick, fox, jumps, over, lazy, dog ]
```


# Compare with MongoDB
MongoDB is a general purpose database. But it does have text-searching feature

```
db.stores.find( { $text: { $search: "\"coffee shop\"" } } )
```

## Performance difference
<img src="https://bitcom.systems/assets/blog/moving-mongo-to-elasticsearch/es_mongo_global.png" alt="drawing" />

MongoDb query

```
OurCollection.find({ 
        $text: { $search: finalKeyword, $caseSensitive: false }, 
        hidden: { $ne: true }, 
        $or: [ { draft: false }, { 'source.id': user } ] 
    }, { score: { $meta: 'textScore' } 
})
.sort({ score: { $meta: 'textScore' } })
.skip(pagination.skip)
.limit(pagination.limit)
```
Elasticsearch query

```
index: 'OurCollection',
body: {
  query: {
    bool: {
      must: {  
          match: { 
              name: { query, operator: 'and', "fuzziness": "AUTO" }
          }
      },
      should: [ { term: { 'source.id': user } } ],
      must_not: [
        { bool: {
          must: { term: { draft: true } },
          must_not: { term: { 'source.id': user } }
        } },
        { term: { hidden: true } }
      ]
    }
  }
},
from: pagination.skip,
size: pagination.size

```


Pros on using ES: 
1. query speed
2. analyzer & my_custom_stop_words_filter
3. more flexibility on query. e.g. fuzzy search



Cons:
1.  ElasticSearch is slow on adding new data. its indexing time is much longer. This lengthy and throughout indexing process is required to allow fast search
2.  Security. Elasticsearch has no features for authentication or authorization. Neither has it capabilities to manage its users' privileges and permissions.
3.  Only support JSON format 
