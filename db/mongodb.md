## Compound index
Composite indexes can be used to enforce uniqueness of combinations of fields.

### Index order
Try to use compound index with high `Cardinality` (have a more unique value for every document in the collection)

Consider `sex` and `name` field. `name` field has higher cardinality as we can narrow more record. 
On the other hand, if we put `sex` first, we may only narrow the result to 50%

https://stackoverflow.com/questions/33545339/how-does-the-order-of-compound-indexes-matter-in-mongodb-performance-wise

## Stats
db.stats can be used to check the info of a db
```
> db.stats();
{
    "db" : "test",
    "collections" : 5,    // connection 數量
    "objects" : 28,        // 物件數量
    "avgObjSize" : 307.42857142857144,    // 平均物件大小
    "dataSize" : 8608,    // 資料大小
    "storageSize" : 1110016,    // storage大小
    "numExtents" : 6,    // 事件數量
    "indexes" : 4,        // 索引數量
    "indexSize" : 32704,    // 索引大小
    "fileSize" : 67108864,    // 檔案大小
    "nsSizeMB" : 16,
    "extentFreeList" : {
        "num" : 3,
        "totalSize" : 147456
    },
    "dataFileVersion" : {
        "major" : 4,
        "minor" : 22
    },
    "ok" : 1
}
```

## Aggregate
use multiple `pipelines` (or different subprocesses) to achieve complex query

### example1: 
我們希望可以找出男性中第二年輕的人，我們可以按照下面的步驟建立管道，來找出第二年輕的男性。

先篩選出sex為M的user。
將每個user的name與age投射出來。
根據age進行排序。
跳過1名user。
限制輸出結果為1。
根據以上的步驟我們建立出來的聚合如下。
```
db.user.aggregate(
	{ "$match" : { "sex" : "M"}},
	{ "$project" : { "name" : 1 , "age" : 1 }},
	{ "$sort" : { "age" : 1 }},
	{ "$skip" : 1 },
	{ "$limit" : 1 }
)
```
reference: https://ithelp.ithome.com.tw/articles/10185952

### example2: 
Retrieve Distinct Values
The following aggregation operation uses the $group stage to retrieve the distinct item values from the sales collection:
```
db.sales.aggregate( [ { $group : { _id : "$item" } } ] )
```
## Query/Cursor explain
```
db.test.find({"x" : 1}).limit(1).explain("executionStats")
db.test.find({"x" : 999999}).limit(1).explain("executionStats")
```

Several informs we can get from cursor explain: `executionTimeMillis, totalDocsExamined`

We can also know if the query is using an index: A query that uses an index has a cursor of type `BtreeCursor`, otherwise it will only use `BasicCursor`

Reference: https://ithelp.ithome.com.tw/articles/10185596

## ReplicaSet
https://blog.toright.com/posts/4508/mongodb-replica-set-高可用性架構搭建.html

## Sharding
Divide data into different machine

### Benefit:
1. Reduce the disk usage on a single server
2. Faster query (index size decrease and distributed query)

### Reference:
https://xiezhenye.com/2012/12/mongodb-sharding-机制分析.html

## Replica Set Arbiter
- In some circumstances (such as you have a primary and a secondary but cost constraints prohibit adding another secondary), you may choose to add an arbiter to your replica set. 
- An arbiter does not have a copy of data set and cannot become a primary. However, an arbiter participates in elections for primary. An arbiter has exactly 1 election vote.


## Other operation
backup a database
```
mongodump --uri="mongodb://mongodb0.example.com:27017" [additional options]
```

drop a database
```
use temp
db.dropDatabase()
```

import json array
```
mongoimport --db dbName --collection collectionName --file fileName.json --jsonArray
```


## Journal
```
https://www.mongodb.com/blog/post/how-mongodbs-journaling-works
```
```
https://codertw.com/%E8%B3%87%E6%96%99%E5%BA%AB/11097/
```


## Question
### Q1
```
Which of the following MongoDB query is equivalent to the following SQL query:

UPDATE users SET status = "C" WHERE age > 25
```
```
db.users.update(
 { age: { $gt: 25 } },
 { $set: { status: "C" } },
 { multi: true })
 ```
 ```
 $set is used to set the value of a particular field in a document. The syntax of set is $set:{column_name : column_value}. Also, {multi:true} is needed to update all the documents. Otherwise only the first found document is updated.
 ```
 
### Q2
 ```
In our posts collection, which command can be used to find all the posts whose author names begin lie between "A" and "C" in dictionary order?

A - db.posts.find( { post_author : { $gte : "A" , $lte : "C" } } );
 ```
 
### Q3
```
What does the following query do when performed on the posts collection?

db.posts.update({_id:1},{$set:{Author:�Tom"}})

A - Sets the complete document with _id as 1 with the document specified in second parameter by replacing it completely

B - Adds a new field Author in the searched collection if not already present

C - Updates only the Author field of the document with _id as 1

D - Both b and c
Answer : D
Explanation

$set sets the specific fields in the matched documents or adds them as a new field if not already present.
```

### Q4
```
Consider the following posts document:

{
 	_id: 1,
	post_text: �This is my first post�,
	author: �Tom�,
	tags: [�tutorial�,�quiz�,�facebook�,�learning�,�fun�]
}

Which of the following queries will return the documents but with only the first two tags in the tags array?

A - db.posts.find({author:"Tom"},{tags:{$slice:2}})

B - db.posts.find({author:"Tom"}).limit({tags:2})

C - db.posts.find({author:"Tom"}).limit($slice:{tags:2})

D - Both a and c are valid. $slice works both with projection and limit.
Answer : A
Explanation

The $slice operator controls the number of items of an array that a query returns.

```

### Q5
```
What does the output x of the following MongoDB aggregation query result into:
db.posts.aggregate( [ { $group: { _id: "$author", x: { $sum: �$likes� } } } ] )

A - Average of likes on all the posts of an author, grouped by author

B - Number of posts by an author

C - Sum of likes on all the posts by an author, grouped by author

D - Sum of likes on all the posts by all the authors
Answer : C
Explanation

The above query first does a grouping on author field and then calculates the number of likes on all the posts that were grouped together.

```

### Q6
```
{
            _id: 1,
            tags: [�tutorial�, �fun�, �learning�],
            post_text: �This is my first post�,	
            //other elements of document  	
}

What does the following command return:

db.posts.find( { 'tags.0': �tutorial� } )

C - All the posts having the first element of the tags array as tutorial

```

