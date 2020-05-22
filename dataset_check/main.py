import psycopg2
import time
import re
import pymorphy2
import csv

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


while 1:
    time.sleep(5)
    conn = closing(psycopg2.connect(dbname='diplom', user='user', 
                            password='secret', host='localhost', port = 5433)) 
    cursor = conn.cursor()
    cursor.execute("""SELECT check_dataset.id, dataset.id ,filename, normalize FROM check_dataset  
                   JOIN dataset ON dataset.id=check_dataset.dataset_id  
                   WHERE completed_task=false AND in_work=false  
                   LIMIT 1""")
    row = cursor.fetchone()
    if row != None:
        row = {'id': row[0], 'dataset_id': row[1] ,'filename': row[2], 'normalize': row[3]}
        print(row)
        sql_update_query = """UPDATE check_dataset SET in_work = true WHERE id = %s"""
        cursor.execute(sql_update_query,  [row['id']])
        conn.commit()
        cursor.close()
        conn.close()
        correct = True
        with open(row['filename'], 'r', encoding='UTF-8', newline='') as csv_file:
            reader = csv.reader(csv_file, delimiter='|')
            for row in reader:
                if not( (row[1] == '1' or row[1] == '0') and len(row) == 2 ) :
                    correct = False
                    break
        if correct == True and normalize == True:
            with open(row['filename'], 'r', encoding='UTF-8', newline='') as csv_file, 
            open('temp_'+row['filename'], 'w', encoding='UTF-8', newline='') as csv_new_file:
                reader = csv.reader(csv_file, delimiter='|')
                writer = csv.writer(csv_new_file, delimiter='|')
                for row in reader:
                    col_values = []
                    row[0] = preprocessor(row[0])
                    col_values.append(row[0])
                    col_values.append(row[1])
                    writer.writerow(col_values)
            os.remove(row['filename'])
            os.rename('temp_'+row['filename'], row['filename'])
        conn = psycopg2.connect(dbname='diplom', user='user', 
                                password='secret', host='localhost', port = 5433))
        cursor = conn.cursor()
        sql_update_query = """UPDATE check_dataset SET completed_task = TRUE, in_work = FALSE WHERE id = %s"""
        cursor.execute(sql_update_query, [ row['id']])
        conn.commit()
        sql_update_query = """UPDATE dataset SET is_correct = %s, checking = TRUE WHERE id = %s"""
        cursor.execute(sql_update_query, [correct, row['dataset_id']])
        conn.commit()
        cursor.close
        conn.close
    else: 
        cursor.close
        conn.close
        print("No tasks")
    
                    
