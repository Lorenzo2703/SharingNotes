import os
from flask import Flask, request, jsonify
from flask_cors import CORS, cross_origin
from werkzeug.utils import secure_filename
from mega import Mega
import time
import json
import re
import base64


mega = Mega()
with open("../python/credentials.json") as f:
    data = json.load(f)

m = mega.login(data["username"], data["password"])


def fmega(file):
    folder = m.find('uploads')
    m.upload(file.filename, folder[0])
    return m.get_upload_link(m.upload(file.filename, folder[0]))


UPLOAD_FOLDER = '.'
ALLOWED_EXTENSIONS = {'pdf'}

app = Flask(__name__)
cors = CORS(app)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER


def allowed_file(filename):
    return '.' in filename and \
        filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS


@app.route('/upload', methods=['POST'])
def upload_file():
    url = ""
    if 'file' not in request.files:
        print('No file part')

    file = request.files['file']
    if file.filename == '':
        print('No selected file')

    if file and allowed_file(file.filename):
        filename = secure_filename(file.filename)
        file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
        url = fmega(file)
        time.sleep(3)
        os.remove(os.path.join(app.config['UPLOAD_FOLDER'], filename))

    return url


@app.route('/download', methods=['GET'])
@cross_origin()
def download():
    # stringUrl = re.sub('[$]', '#', request.args["fileurl"])
    stringUrl = request.args["fileUrl"]
    m.download_url(stringUrl, "./download")
    time.sleep(3)
    file = os.listdir("./download")[0]

    filez = base64.b64encode(open("ciao.pdf", "rb").read())
    # os.remove("./download"[0])
    jsonstring = jsonify(filez)
    print(jsonstring)

    return jsonstring


@ app.route('/getAll', methods=['GET'])
def getAll():
    filesInNode = m.get_files_in_node(target=1)

    extracted = []
    for file in filesInNode:
        extracted.append(filesInNode[file]['a']['n'])
        filez = m.find(filesInNode[file]['a']['n'])
        extracted.append(m.get_link(filez))

    return jsonify(extracted)


def pdf_encode(pdf_filename):
    return open(pdf_filename, "rb").read().encode("base64")


app.secret_key = 'super secret key'
app.config['SESSION_TYPE'] = 'filesystem'
app.config['MAX_CONTENT_LENGTH'] = 16 * 1000 * 1000
app.run(debug=True)
