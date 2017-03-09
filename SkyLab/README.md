# Resize Photos - SkyHub Challenge

The objective here was to create an algorithm able to complete the **SkyHub Challenge**, which is:

```
• Consume a webservice endpoint (http://54.152.221.29/images.json) that returns a JSON of
photos. There are 10 photos.

• Generate three different formats for each photo. The dimensions are: small (320x240),
medium (384x288) and large (640x480).

• Write a webservice endpoint that lists (in JSON format) all the ten photos with their
respective formats, providing their URLs.
```

The solution I adopted is based in an implementation using _Python_. It receives the json containing the URLs that provides the images, processes it, retrieves the images, processes them, stores them into a database using MongoDB, then provides an endpoint with a json containing the processed images URLs.

## Why Python?
After some time searching about the tools I could use to complete this challenge, decided to use Python because:

- Python have some tools available, easy to install and to run, that made this challenge easier to complete than if I had choosen to use another language;
- It's a programming language I was already somewhat familiarized with and knew that would be simple to use.

## Execution
In order to execute the application, you must install some tools, like:
- **Python** (I used _Python 3.6_)
- **Pillow** (To manipulate the images)
- **MongoDB** (Should be running in your machine when you try to run the application)
- **PyMongo**
- **Flask** (Microframework used to run the application)

After installing these tools, you can run the aplication by executing the script at `skylab.py` (for example, running on terminal, using `python skylab.py`).

