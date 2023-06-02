`item` collection instance example

```
{
    "_id" : ObjectId("xxxxxxxx"),
    "type" : "typeA",
    "sessionId" : "session-one",
    "createdAt" : ISODate("2023-04-10T03:50:02.352+0000"),
}
```

`itemlists` collection instance example:

```
{
    "_id" : ObjectId("6433876a6da05c76c81a2740"),
    "sessionId" : "session-one",
    "__v" : NumberInt(0),
    "createdAt" : ISODate("2023-04-10T03:50:02.117+0000"),
    "metadata" : {
        "sessionStartTime" : 1681098602016.0,
    },
    "items" : [
        ObjectId("aaaa"),
        ObjectId("bbb"),
        ObjectId("ccc")
    ]
}
```

please write a mongo query to find all the session count that have at least one "typeA" message

```
db.itemlists.aggregate([
  {
    $match: {
      createdAt: {
        $gte: new Date("2020-01-29T02:00:00Z"),
        $lte: new Date("2023-11-29T06:59:59Z")
      }
    }
  },
  {
    $lookup: {
      from: "items",
      localField: "items",
      foreignField: "_id",
      as: "items"
    }
  },
  {
    $unwind: "$items"
  },
  {
    $match: {
      "messages.type": "typeA",
    }
  },
  {
    $group: {
      _id: {
        date: { $dateToString: { timezone: '+08:00', format: "%Y%m%d%H", date: { $toDate: "$createdAt" } } },
        sessionId: "$sessionId"
      },
      sessionCount: { $sum: 1 }
    }
  },

  {
    $group: {
      _id: "$_id.date",
      sessionCount: { $sum: 1 },
    }
  },
  {
    $sort: {
      "_id": 1
    }
  }
]);
```

output:

```
{
    "_id" : "2023052911",
    "sessionCount" : 52.0
}
{
    "_id" : "2023052912",
    "sessionCount" : 52.0
}
{
    "_id" : "2023052913",
    "sessionCount" : 49.0
}
```
