## Redis persistence
RDB (Redis Database): RDB persistence performs point-in-time snapshots of your dataset at specified intervals.

AOF (Append Only File): AOF persistence logs every write operation received by the server. These operations can then be replayed again at server startup, reconstructing the original dataset. Commands are logged using the same format as the Redis protocol itself.

No persistence: You can disable persistence completely. This is sometimes used when caching.

RDB + AOF: You can also combine both AOF and RDB in the same instance.

## data type
![img](https://estuary.dev/static/4ff882a8a0e3d2ffe69114d10bc2ca58/ac23c/02_Redis_Data_Types_Data_Types_51e474ec05.avif)
