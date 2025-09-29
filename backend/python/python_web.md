## Project structure

For Flask to find your templates, your project should have a specific structure:

````
my_app/
├── app.py
└── templates/
    └── index.html```
````

The `render_template` function tells Flask to look for a file inside the templates folder.

## `app.py`

```
from flask import Flask, render_template

app = Flask(__name__)

@app.route('/')
def home():
    # Pass a variable to the template
    user_name = "Alice"
    return render_template('index.html', name=user_name)

if __name__ == '__main__':
    app.run(debug=True)

```

## `templates/index.html`

This HTML file uses Jinja2 syntax to display the name variable passed from the Flask view.
html

```
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Flask and Jinja2</title>
</head>
<body>
    <h1>Hello, {{ name }}!</h1>
    <p>This is a web application using Flask and Jinja2.</p>
</body>
</html>
```

## Advantages

1. The simplest for beginners. Flask is a micro-framework that is easy to understand, and Python's syntax is very readable
2. Benefits from Python's robust ecosystem, which is excellent for data science, machine learning, and scripting. Extensions are available for almost any task.
