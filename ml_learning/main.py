import psycopg2
import time

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

def tokenizer(text):
    return text.split()

def learn(model_id, n_workers, filename ):
    df = pd.read_csv('../datasets/'+filename, sep = "|",header=None, encoding='utf-8')


    nltk.download('stopwords')

    stop = stopwords.words('russian')

    X_train = df.loc[:df.size*0.9, 0].values
    y_train = df.loc[:df.size*0.9, 1].values
    X_test = df.loc[df.size*0.1:, 0].values
    y_test = df.loc[df.size*0.1:, 1].values

    tfidf = TfidfVectorizer(strip_accents=None,
                            lowercase=False,
                            preprocessor=None)

    param_grid = [{'vect__ngram_range': [(1, 1)],
                   'vect__stop_words': [stop],
                   'vect__tokenizer': [tokenizer],
                   'clf__penalty': ['l2'], # , 'l1'
                   'clf__C': [1.0]}] #, 10.0, 100.0

    lr_tfidf = Pipeline([('vect', tfidf),
                         ('clf', LogisticRegression(random_state=0))])

    gs_lr_tfidf = GridSearchCV(lr_tfidf, param_grid,
                               scoring='accuracy',
                               cv=5,
                               verbose=25,
                               n_jobs=1)
    gs_lr_tfidf.fit(X_train, y_train)
    filename = str(model_id) + '.sav'
    pickle.dump(gs_lr_tfidf, open('../models/'+filename, 'wb'))
    return gs_lr_tfidf.best_score_        


while 1:
    time.sleep(5)
    conn = psycopg2.connect(dbname='diplom', user='user', 
                            password='secret', host='db', port = 5432)
    cursor = conn.cursor()
    cursor.execute("""SELECT queue_task_ml.id, n_workers, model_id, dataset.filename FROM queue_task_ml  
                   JOIN model ON model_id=model.id  
                   JOIN dataset ON model.dataset_id = dataset.id 
                   WHERE completed_task=false AND in_work=false  
                   LIMIT 1""")
    row = cursor.fetchone()
    if row != None:
        row = {'id': row[0], 'n_workers': row[1], 'model_id': row[2], 'filename': row[3]}
        print(row)
        sql_update_query = """UPDATE queue_task_ml SET in_work = true WHERE id = %s"""
        cursor.execute(sql_update_query,  [row['id']])
        conn.commit()
        cursor.close
        conn.close
        accuracy = learn(row['model_id'], row['n_workers'], row['filename'])
        print('Working with model task id =' + str(row['id']) )
        conn = psycopg2.connect(dbname='diplom', user='user', 
                            password='secret', host='db', port = 5432)
        cursor = conn.cursor()
        sql_update_query = """UPDATE queue_task_ml SET completed_task = TRUE, in_work = FALSE WHERE id = %s"""
        cursor.execute(sql_update_query, [ row['id']])
        conn.commit()
        sql_update_query = """UPDATE model SET test_accuracy = %s WHERE id = %s"""
        cursor.execute(sql_update_query, [ accuracy , row['model_id']])
        conn.commit()
        cursor.close
        conn.close
    else:
        cursor.close
        conn.close
        print("No tasks")
