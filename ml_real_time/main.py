from http.server import BaseHTTPRequestHandler, HTTPServer # python3
import re
import pymorphy2
import pickle
import json
import pandas as pd
import numpy as np
import nltk
from nltk.corpus import stopwords
import pickle

from sklearn.pipeline import Pipeline
from sklearn.linear_model import LogisticRegression
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import GridSearchCV
from contextlib import closing

models = {}

morph = pymorphy2.MorphAnalyzer()
# приведение к нормальной форме для уменьшение размера словаря уникальных слов
def norm(word):
    word = morph.parse(word)[0]
    return word.normal_form
def tokenizer(text):
    return text.split()
# удаление не нужных символов из датасета
def preprocessor(text):
    text = re.sub('<[^>]*>', '', text)
    text = (re.sub('[\W]+', ' ', text.lower()))
    text_norm = ''
    text_norm = ''.join([norm(word)+" " for word in text.split()]) 
    return text_norm

class HandleRequests(BaseHTTPRequestHandler):
    def _set_headers(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()
            
    def do_DELETE(self):
        content_len = int(self.headers.get('content-length', 0))
        post_body = self.rfile.read(content_len)
        data = json.loads(post_body)
        id_model = data['id']
        del model[id_model]
        self.wfile.write(bytearray(json.dumps('ok'), 'utf-8'))

    def do_GET(self):
        self._set_headers()
        self.wfile.write(bytearray("Hello from resolver!",'utf-8'))

    def do_POST(self):
        '''Reads post request body'''
        self._set_headers()
        content_len = int(self.headers.get('content-length', 0))
        post_body = self.rfile.read(content_len)
        data = json.loads(post_body)
        id_model = data['id']
        if not id_model in models:
            models[id_model] = pickle.load(open(str(id_model)+'.sav', 'rb'))
            print("load model with id " + str(id_model))
        answers = []
        for text in data['data']:
            answer = {'text': text, 'answer': int(models[id_model].predict([preprocessor(text)])[0])}
            answers.append(answer)
            print (answer)
        self.wfile.write(bytearray(json.dumps(answers), 'utf-8'))
        
host = 'localhost'
port = 8082
HTTPServer((host, port), HandleRequests).serve_forever()
