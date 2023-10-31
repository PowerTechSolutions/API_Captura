
import java.io.File
object CodigoPythonPeri {

    fun execpython(servicos:MutableList<ServicosMonitorados>) {

        val servicoCadastradorepositorio = ServicoCadastradoRepositorio()
        servicoCadastradorepositorio.iniciar()

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

disco = psutil.disk_usage('/')

try:
    mydb = mysql.connector.connect(host = 'localhost', user = 'root',password = '@myLOVEisthe0506',database = 'PowerTechSolutions')
    if mydb.is_connected():
        db_info = mydb.get_server_info()
        mycursor = mydb.cursor()
        sql_querryDISCO = 'INSERT INTO Monitoramento_RAW VALUES (NULL, CURRENT_TIMESTAMP(), %s,$componenteDISCO)'
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
        Runtime.getRuntime().exec("python3 $nomeArquivoPyDefault")

        println("Disco capturado")

    }

}