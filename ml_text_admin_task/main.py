import psycopg2
import time

import re
import pymorphy2
import pickle
import json
import pandas as pd
import numpy as np
import nltk
from nltk.corpus import stopwords
import pickle
import csv

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

while 1:
    time.sleep(5)
    conn = psycopg2.connect(dbname='diplom', user='user', 
                            password='secret', host='db', port = 5432)
    cursor = conn.cursor()
    cursor.execute("""SELECT id, model_id, filename FROM queue_task_admin_file    
                   WHERE completed_task=false AND in_work=false  
                   LIMIT 1""")
    row = cursor.fetchone()
    if row != None:
        row = {'id': row[0], 'model_id': row[1], 'filename': row[2]}
        print("Start task admin file id " + str(row['id']))
        sql_update_query = """UPDATE queue_task_admin_file SET in_work = true WHERE id = %s"""
        cursor.execute(sql_update_query,  [row['id']])
        conn.commit()
        cursor.close
        conn.close
        
        model = pickle.load(open('../models/'+str(row['model_id'])+'.sav', 'rb'))
        print("load model with id " + str(row['model_id']))
        
        
        with open('../text_admin_file/'+str(row['filename'])+'.csv', 'w', encoding='UTF-8', newline='') as csv_file:
            task = open('../text_admin_file/'+str(row['filename']), "r")
            writer = csv.writer(csv_file, delimiter='|')
            for line in task:
                col_values = []
                col_values.append(line)
                col_values.append(int(model.predict([preprocessor(line)])[0]))
                writer.writerow(col_values)
            
        conn = psycopg2.connect(dbname='diplom', user='user', 
                                password='secret', host='db', port = 5432)
        cursor = conn.cursor()
        sql_update_query = """UPDATE queue_task_admin_file SET completed_task = TRUE,
        in_work = FALSE WHERE id = %s"""
        cursor.execute(sql_update_query, [row['id']])
        conn.commit()
        cursor.close
        conn.close
        print("Completed task admin file id " + str(row['id']))
    else: 
        cursor.close
        conn.close
        print("No tasks")
