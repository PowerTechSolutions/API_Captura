import com.github.britooo.looca.api.core.Looca
import org.springframework.jdbc.core.JdbcTemplate

class CapturaUsb {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = Conexao.jdbcTemplate!!
    }

    var looca:Looca = Looca()

    var grupoUsb = looca.dispositivosUsbGrupo

    fun inserirBanco(FKMaquina:Int):Int{

        var total = grupoUsb.totalDispositvosUsbConectados
        var i = 0
        var inserts = 0

        while (i<total){

            var nomeUSB = grupoUsb.dispositivosUsb[i].nome

            inserts += jdbcTemplate.update(
                "INSERT INTO Dispositivos_USB (Nome_Dispositivo,FKMaquina) VALUES (?,?)",
                nomeUSB,FKMaquina
            )

            i++

        }

        return inserts

    }

}