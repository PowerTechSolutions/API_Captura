import java.io.File

object CodigoPythonConst {

    fun execpython(servicos:MutableList<ServicosMonitorados>) {

        val servicoCadastradorepositorio = ServicoCadastradoRepositorio()
        servicoCadastradorepositorio.iniciarSql()

        var CPU = 0
        var componenteCPU = 0
        var RAM = 0
        var componenteRAM = 0

        for (servico in servicos){

            var apelido = servicoCadastradorepositorio.buscarComponente(servico.FKComponente_cadastrado)

            when(apelido){
                "CPU" -> {
                    CPU += 1
                    componenteCPU = servico.IDComponente_monitorado
                }
                "RAM" -> {
                    RAM += 1
                    componenteRAM = servico.IDComponente_monitorado
                }
            }

        }


        var codigoPython ="""
import psutil
import time
import mysql.connector
import pyodbc

cpu = psutil.cpu_percent(interval=1)
ram = psutil.virtual_memory()

try:
    conn = pyodbc.connect(
        'Driver=ODBC Driver 17 for SQL Server;'
        'Server=ec2-34-194-127-191.compute-1.amazonaws.com;'
        'Database=PowerTechSolutions;'
        'UID=sa;'
        'PWD=myLOVEisthe0506'
    )
    cursor = conn.cursor()
    if $CPU == 1:
        sql_querryCPU = f"INSERT INTO Monitoramento_RAW (Uso,FKComponente_Monitorado) VALUES ({cpu},$componenteCPU)"
        cursor.execute(sql_querryCPU)
        conn.commit()
    if $RAM == 1:
        sql_querryRAM = f'INSERT INTO Monitoramento_RAW (Uso,FKComponente_Monitorado) VALUES ({ram.percent},$componenteRAM)'
        cursor.execute(sql_querryRAM)
        conn.commit()
finally:
    cursor.close()
    conn.close()

try:
    mydb = mysql.connector.connect(host = 'localhost', user = 'root',password = '@myLOVEisthe0506',database = 'PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        if $CPU == 1:
            sql_querryCPU = 'INSERT INTO Monitoramento_RAW (Uso,FKComponente_Monitorado) VALUES (%s,$componenteCPU)'
            valCPU = [cpu]
            mycursor.execute(sql_querryCPU, valCPU)
            mydb.commit()
        if $RAM == 1:
            sql_querryRAM = 'INSERT INTO Monitoramento_RAW (Uso,FKComponente_Monitorado) VALUES (%s,$componenteRAM)'
            valRAM = [ram.percent]
            mycursor.execute(sql_querryRAM, valRAM)
            mydb.commit()
finally:
    if(mydb.is_connected()):
        mycursor.close()
        mydb.close()
"""

        val nomeArquivoPyDefault = "CodigoPythonConst.py"

        File(nomeArquivoPyDefault).writeText(codigoPython)
        Runtime.getRuntime().exec("py $nomeArquivoPyDefault")

    }

}