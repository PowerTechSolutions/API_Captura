
import psutil
import time
import mysql.connector

cpu = psutil.cpu_percent(interval=1)
ram = psutil.virtual_memory()

try:
    mydb = mysql.connector.connect(host = 'localhost', user = 'root',password = '@myLOVEisthe0506',database = 'PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        if 1 == 1:
            sql_querryCPU = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), %s,1)'
            valCPU = [round(cpu,2)]
            mycursor.execute(sql_querryCPU, valCPU)
            mydb.commit()
        if 1 == 1:
            sql_querryRAM = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), %s,2)'
            valRAM = [round(ram.percent,2)]
            mycursor.execute(sql_querryRAM, valRAM)
            mydb.commit()
finally:
    if(mydb.is_connected()):
        mycursor.close()
        mydb.close()
