```
CREATE TABLE store_events (
	user_id INTEGER NOT NULL,
  	app_id INTEGER NOT NULL,
	event_type VARCHAR ( 64 ) NOT NULL,
    event_time_utc TIMESTAMP NOT NULL
);
```

```
INSERT INTO store_events (user_id,app_id,event_type,event_time_utc) VALUES (
  '1',
  '1',
  'store_app_view',
  current_timestamp
); SELECT * FROM store_events;

```