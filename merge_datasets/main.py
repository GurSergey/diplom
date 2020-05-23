import psycopg2
import time

while 1:
    time.sleep(5)
    conn = psycopg2.connect(dbname='diplom', user='user', 
                                    password='secret', host='localhost', port = 5433) 
    cursor = conn.cursor()
    cursor.execute("""SELECT merge_dataset.id, dataset_id, source_datasets, filename FROM merge_dataset  
                   JOIN dataset ON dataset.id=merge_dataset.dataset_id  
                   WHERE completed_task=false AND in_work=false  
                   LIMIT 1""")
    row = cursor.fetchone()
    if row != None:
        row = {'id': row[0], 'dataset_id': row[1], 'source_datasets': row[2], 'filename': row[3]}
        print("In work merge task " + str(row['id']))
        sql_update_query = """UPDATE merge_dataset SET in_work = true WHERE id = %s"""
        cursor.execute(sql_update_query,  [row['id']])
        conn.commit()
        
        file_id = tuple(row['source_datasets'].split('|'))
        cursor.execute("""SELECT filename FROM dataset 
                       WHERE id IN %s""", (file_id, ))
        cursor.close
        conn.close
        filenames = []
        for row_files in cursor:
            filenames.append(row_files[0])

        with open('../datasets/'+row['filename'], 'w') as outfile:
            for fname in filenames:
                with open('../datasets/'+fname) as infile:
                    for line in infile:
                        outfile.write(line)
        conn = psycopg2.connect(dbname='diplom', user='user', 
                                password='secret', host='localhost', port = 5433)
        cursor = conn.cursor()
        sql_update_query = """UPDATE merge_dataset SET completed_task = TRUE, in_work = FALSE WHERE id = %s"""
        cursor.execute(sql_update_query, [ row['id']])
        conn.commit()
        sql_update_query = """UPDATE dataset SET is_correct = TRUE, checking = TRUE WHERE id = %s"""
        cursor.execute(sql_update_query, [ row['dataset_id']])
        conn.commit()
        cursor.close
        conn.close
    else: 
        cursor.close
        conn.close
        print("No tasks")
