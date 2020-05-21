import psycopg2
import time

while 1:
    time.sleep(5)
    with closing(psycopg2.connect(dbname='diplom', user='user', 
                            password='secret', host='localhost', port = 5433)) as conn:
        with conn.cursor() as cursor:
            cursor.execute("""SELECT id FROM check_dataset  
                           JOIN dataset ON dataset.id=check_dataset.dataset_id  
                            
                           WHERE completed_task=false AND in_work=false  
                           LIMIT 1""") 
