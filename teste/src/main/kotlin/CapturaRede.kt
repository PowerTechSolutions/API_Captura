import com.github.britooo.looca.api.core.Looca
import com.github.britooo.looca.api.group.rede.Rede
import com.github.britooo.looca.api.group.rede.RedeInterface
import org.springframework.jdbc.core.JdbcTemplate

class CapturaRede {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = Conexao.jdbcTemplate!!
    }

    var looca:Looca = Looca()

    var rede = looca.rede.parametros
    var redeTrasform = "${rede.servidoresDns}"

    fun inserirBanco(maquinaescolhis:Int):Int{

        var inserts:Int = jdbcTemplate.update(
            "INSERT INTO Redes_conectadas (Servidor_DNS,FKMaquina) VALUES (?,?)",
            redeTrasform,maquinaescolhis
            )

        return inserts

    }

}