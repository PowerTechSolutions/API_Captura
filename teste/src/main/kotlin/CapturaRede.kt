import com.github.britooo.looca.api.core.Looca
import org.springframework.jdbc.core.JdbcTemplate

class CapturaRede {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciarMysql(){
        jdbcTemplate = ConexaoMysql.jdbcTemplate!!
    }

    fun iniciarSqlServer(){
        jdbcTemplate = ConexaoSqlServer.jdbcTemplate!!
    }

    var looca:Looca = Looca()

    var rede = looca.rede.parametros
    var redeTrasform = "${rede.servidoresDns}"

    fun inserirBanco(maquinaescolhis:Int){

        jdbcTemplate.update("INSERT INTO Redes_conectadas (Servidor_DNS,FKMaquina) VALUES ('$redeTrasform',$maquinaescolhis);")

    }

}