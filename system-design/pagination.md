##  Cursor-based Pagination

### Table
```
CREATE TABLE articles (
    article_id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    body TEXT,
    publication_date TIMESTAMP WITHOUT TIME ZONE,
    author_id INTEGER
);
```

### Code
```
from flask import Flask, request, jsonify
import psycopg2
import psycopg2.extras

app = Flask(__name__)

# Database connection parameters
DB_HOST = "your_db_host"
DB_NAME = "your_db_name"
DB_USER = "your_db_user"
DB_PASS = "your_db_password"

# Establish a connection to the database
def get_db_connection():
    conn = psycopg2.connect(
        dbname=DB_NAME,
        user=DB_USER,
        password=DB_PASS,
        host=DB_HOST
    )
    return conn

@app.route('/articles', methods=['GET'])
def get_articles():
    size = int(request.args.get('size', '20'))  # Default and maximum size per page
    last_article_id = request.args.get('cursor', None)  # Cursor points to the last article_id of the previous page
    
    conn = get_db_connection()
    cursor = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)
    
    # SQL to paginate with cursor
    if last_article_id:
        query = """
        SELECT * FROM articles
        WHERE article_id > %s
        ORDER BY article_id ASC
        LIMIT %s;
        """
        cursor.execute(query, (last_article_id, size))
    else:
        query = """
        SELECT * FROM articles
        ORDER BY article_id ASC
        LIMIT %s;
        """
        cursor.execute(query, (size,))
    
    articles = cursor.fetchall()
    conn.close()

    # Prepare the next cursor value
    next_cursor = articles[-1]['article_id'] if articles else None
    
    return jsonify({
        'articles': [dict(article) for article in articles],
        'next_cursor': next_cursor
    })

if __name__ == '__main__':
    app.run(debug=True)
```

## Offset Pagination
Offset pagination is straightforward to implement but can become inefficient for very large datasets because it requires the database to count and skip over rows based on the given offset, which can be slow if the offset is large.

```
from flask import Flask, request, jsonify
import psycopg2
import psycopg2.extras

app = Flask(__name__)

# Database connection parameters
DB_HOST = "your_db_host"
DB_NAME = "your_db_name"
DB_USER = "your_db_user"
DB_PASS = "your_db_password"

# Establish a connection to the database
def get_db_connection():
    conn = psycopg2.connect(
        dbname=DB_NAME,
        user=DB_USER,
        password=DB_PASS,
        host=DB_HOST
    )
    return conn

@app.route('/articles', methods=['GET'])
def get_articles():
    page = int(request.args.get('page', '1'))  # Default page number
    size = int(request.args.get('size', '20'))  # Default and maximum size per page
    offset = (page - 1) * size  # Calculate offset

    conn = get_db_connection()
    cursor = conn.cursor(cursor_factory=psycopg2.extras.DictCursor)
    
    # SQL to paginate with offset
    query = """
    SELECT * FROM articles
    ORDER BY article_id ASC
    LIMIT %s OFFSET %s;
    """
    cursor.execute(query, (size, offset))
    
    articles = cursor.fetchall()
    conn.close()

    return jsonify({
        'articles': [dict(article) for article in articles],
        'page': page,
        'size': size
    })

if __name__ == '__main__':
    app.run(debug=True)
```
