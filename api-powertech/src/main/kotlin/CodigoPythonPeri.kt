
import java.io.File
object CodigoPythonPeri {

    fun execpython(servicos:MutableList<ServicosMonitorados>) {

        val servicoCadastradorepositorio = ServicoCadastradoRepositorio()
        servicoCadastradorepositorio.iniciarSql()

        var componenteDISCO = 0

        for (servico in servicos){

            var apelido = servicoCadastradorepositorio.buscarComponente(servico.FKComponente_cadastrado)

            when(apelido){
                "DISCO" -> {
                    componenteDISCO = servico.IDComponente_monitorado
                }
            }

        }


        var codigoPython ="""
import psutil
import time
import mysql.connector
import pyodbc

disco = psutil.disk_usage('/')

try:
    conn = pyodbc.connect(
        'Driver=ODBC Driver 17 for SQL Server;'
        'Server=ec2-34-194-127-191.compute-1.amazonaws.com;'
        'Database=PowerTechSolutions;'
        'UID=sa;'
        'PWD=myLOVEisthe0506'
    )
    cursor = conn.cursor()
    sql_querryDISCO = f'INSERT INTO Monitoramento_RAW (Uso,FKComponente_Monitorado) VALUES ({disco.percent},$componenteDISCO)'
    cursor.execute(sql_querryDISCO)
    conn.commit()
finally:
    cursor.close()
    conn.close()

try:
    mydb = mysql.connector.connect(host = 'localhost', user = 'root',password = '@myLOVEisthe0506',database = 'PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        sql_querryDISCO = 'INSERT INTO Monitoramento_RAW (Uso,FKComponente_Monitorado) VALUES (%s,$componenteDISCO)'
        valDISCO = [disco.percent]
        mycursor.execute(sql_querryDISCO, valDISCO)
        mydb.commit()
finally:
    if(mydb.is_connected()):
        mycursor.close()
        mydb.close()
"""

        val nomeArquivoPyDefault = "CodigoPythonPeri.py"

        File(nomeArquivoPyDefault).writeText(codigoPython)
        Runtime.getRuntime().exec("py $nomeArquivoPyDefault")

    }

}