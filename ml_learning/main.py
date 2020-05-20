while True:
import pandas as pd
from sklearn.pipeline import Pipeline
from sklearn.linear_model import LogisticRegression
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.model_selection import GridSearchCV

df = pd.read_csv('random_response_shufle.csv', sep = "|",header=None, encoding='utf-8')
#Предобработка текста(удаление не нужных символов и нормальная форма):
import re
import pymorphy2
morph = pymorphy2.MorphAnalyzer()
# приведение к нормальной форме для уменьшение размера словаря уникальных слов
def norm(word):
    word = morph.parse(word)[0]
    return word.normal_form
# удаление не нужных символов из датасета
def preprocessor(text):
    text = re.sub('<[^>]*>', '', text)
    text = (re.sub('[\W]+', ' ', text.lower()))
    text_norm = ''
    text_norm = ''.join([norm(word)+" " for word in text.split()])
    return text_norm
Применение предобработки к датасету:
df[1] = df[1].apply(preprocessor)
Процесс токенизации:
def tokenizer(text):
    return text.split()
Получение стоп-слов из пакета nltk
import nltk
nltk.download('stopwords')
from nltk.corpus import stopwords
Тренировочная и тестовые выборки:
X_train = df.loc[:45000, 1].values
y_train = df.loc[:45000, 2].values
X_test = df.loc[5000:, 1].values
y_test = df.loc[5000:, 2].values



tfidf = TfidfVectorizer(strip_accents=None,
                        lowercase=False,
                        preprocessor=None)
param_grid = [{'vect__ngram_range': [(1, 1)],
               'vect__stop_words': [stop],
               'vect__tokenizer': [tokenizer],
               'clf__penalty': ['l1', 'l2'],
               'clf__C': [1.0, 10.0, 100.0]}]
lr_tfidf = Pipeline([('vect', tfidf),
                     ('clf', LogisticRegression(random_state=0))])
gs_lr_tfidf = GridSearchCV(lr_tfidf, param_grid,
                           scoring='accuracy',
                           cv=5,
                           verbose=1,
                           n_jobs=-1)
gs_lr_tfidf.fit(X_train, y_train)