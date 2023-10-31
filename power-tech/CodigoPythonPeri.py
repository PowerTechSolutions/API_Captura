
import psutil
import time
import mysql.connector

disco = psutil.disk_usage('/')

try:
    mydb = mysql.connector.connect(host = 'localhost', user = 'root',password = '@myLOVEisthe0506',database = 'PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        sql_querryDISCO = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), %s,3)'
        valDISCO = [round(disco.percent,2)]
        mycursor.execute(sql_querryDISCO, valDISCO)
        mydb.commit()
finally:
    if(mydb.is_connected()):
        mycursor.close()
        mydb.close()
