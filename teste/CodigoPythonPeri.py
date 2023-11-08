
import psutil
import time
import mysql.connector

disco = psutil.disk_usage('/')

try:
    mydb = mysql.connector.connect(host = '3.234.2.175', user = 'root',password = 'urubu100',database = 'PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        sql_querryDISCO = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), %s,3)'
        valDISCO = [disco.percent]
        mycursor.execute(sql_querryDISCO, valDISCO)
        mydb.commit()
finally:
    if(mydb.is_connected()):
        mycursor.close()
        mydb.close()
