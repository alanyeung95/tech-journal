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
 ## Journal
 ```
 https://www.mongodb.com/blog/post/how-mongodbs-journaling-works
 ```
 ```
  https://codertw.com/%E8%B3%87%E6%96%99%E5%BA%AB/11097/
 ```
