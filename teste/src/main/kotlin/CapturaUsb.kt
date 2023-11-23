import com.github.britooo.looca.api.core.Looca
import org.springframework.jdbc.core.JdbcTemplate

class CapturaUsb {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciarSql(){
        jdbcTemplate = ConexaoSqlServer.jdbcTemplate!!
    }

    fun iniciarMYSQL(){
        jdbcTemplate = ConexaoMysql.jdbcTemplate!!
    }

    var looca:Looca = Looca()
    var grupoUsb = looca.dispositivosUsbGrupo

    fun inserirBanco(FKMaquina:Int){

        var total = grupoUsb.totalDispositvosUsbConectados
        var i = 0

        while (i<total){

            var nomeUSB = grupoUsb.dispositivosUsb[i].nome

            jdbcTemplate.update(
                "INSERT INTO Dispositivos_USB (Nome_Dispositivo,FKMaquina) VALUES (?,?)",
                nomeUSB,FKMaquina
            )

            i++

        }

    }

}