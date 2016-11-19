from flask import Flask, jsonify

application = Flask(__name__)

@application.route("/")
def hello():
    return "What The Crop?"

@application.route("/best/<float:lon>/<float:lat>/<int:listSize>",  methods=['GET'])
def best(lon, lat, listSize):
    data = []

    data.append({"name" : "leCrop", "price" :110})
    data.append({"name" : "leCrop", "price" :110})
    return jsonify(crop=data)

if __name__ == "__main__":
    application.run()
