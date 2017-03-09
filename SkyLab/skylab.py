#	Author: Victor Rezende Geraldini
#	Email:  victorgeraldini@gmail.compile
#
#	== Resize Photos - SkyHub Challenge == 
#	Consume a webservice endpoint (http://54.152.221.29/images.json) that returns a JSON of photos. There are 10 photos.
#	Generate three different formats for each photo. The dimensions are: small (320x240), medium (384x288) and large (640x480).
#	Write a webservice endpoint that lists (in JSON format) all the ten photos with their respective formats, providing their URLs.	

import json
import requests
import urllib.request

from bson.binary import Binary
from flask import Flask
from flask import jsonify
from flask import send_file
from io import BytesIO
from PIL import Image
from pymongo import MongoClient

app = Flask(__name__)

#Create or get an already created database using MongoDB.
#Considers the database is not created when the application starts running.
def getDatabase():
	client = MongoClient()
	database = client.skyhub_database
	
	return database

#Receive webservice content and create a list with the values that will be used from the images.
#Considers the webservice will always be available.
def getValues():
	#Getting json values from url
	url = 'http://54.152.221.29/images.json'
	urlOpen = urllib.request.urlopen(url).read().decode('utf-8')
	jsonObject = json.loads(urlOpen)
	
	#Create a list where the images will be stored into
	imgList = list()
	
	#Store images into the list
	for value in jsonObject['images']:
		request = requests.get(value['url'])
		imageBinaryContent = BytesIO(request.content).read()
		img = {
			'url': value['url'],
			'name': value['url'].rsplit('/', 1)[-1],
			'file': Binary(imageBinaryContent)
			}
		imgList.append(img)
		
	#Return the list
	return imgList

#Process 'imgList' values, creating three versions from the original image: its small, medium and large replicates.
def processValues(imgList):
	#Defining image dimensions
	small = (320, 240)
	medium = (384, 288)
	large = (640, 480)
	
	#Get database to store processed images
	database = getDatabase()
	processedImagesDB = database.processed_collection

	#Process all the images, creating their small, medium and large versions
	for element in imgList:
		data = BytesIO(element['file'])
		image = Image.open(data)

		#The process is basically the same for the three images
		smallImageBytes = BytesIO()
		smallImage = image.resize((small), Image.ANTIALIAS)
		smallImage.save(smallImageBytes, format=image.format, quality=100)
		smallImageData = smallImageBytes.getvalue()

		mediumImageBytes = BytesIO()
		mediumImage = image.resize((medium), Image.ANTIALIAS)
		mediumImage.save(mediumImageBytes, format=image.format, quality=100)
		mediumImageData = mediumImageBytes.getvalue()

		largeImageBytes = BytesIO()
		largeImage = image.resize((large), Image.ANTIALIAS)
		largeImage.save(largeImageBytes, format=image.format, quality=100)
		largeImageData = largeImageBytes.getvalue()

		processedImages = {
			'name': element['name'],
			'small': Binary(smallImageData),
			'medium': Binary(mediumImageData),
			'large': Binary(largeImageData)
		}

		#Add processed elements to database
		processedImagesDB.insert_one(processedImages)
		#Close image object
		image.close()

#Initial route used to return the json containing images URLs
@app.route('/')
def listImagesUrl():
	database = getDatabase() #Retrieve image data stored in database
	iterator = database.processed_collection.find({})
	result = []
	server = 'http://localhost:8080/'
	
	#Append data to the final json string
	for element in iterator:
		data = {
            'smallUrl': server + 'small/' + element['name'],
            'mediumUrl': server + 'medium/' + element['name'],
            'largeUrl': server + 'large/' + element['name'],
        }
		
		result.append(data)
	
	return jsonify(images=result)
	
#Route accessed by user to show the image in its small/medium/large version
@app.route('/<size>/<filename>')
def showImage(size, filename):
	database = getDatabase()
	img_database = database.processed_collection.find_one({'name': filename})
	img = BytesIO(img_database[size])
	
	return send_file(img, mimetype='image/jpeg')


if __name__ == '__main__':
	
	print("Getting values...")
	values = getValues()
	
	print("Processing images...")
	processValues(values)
	
	print("Running application at http://localhost:8080")
	app.run(host = "localhost", port = int("8080"))
	
	
	

