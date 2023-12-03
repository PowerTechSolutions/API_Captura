import com.github.britooo.looca.api.core.Looca
import org.springframework.jdbc.core.JdbcTemplate

class CapturaJanelas {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciarMysql(){
        jdbcTemplate = ConexaoMysql.jdbcTemplate!!
    }

    fun iniciarSql(){
        jdbcTemplate = ConexaoSqlServer.jdbcTemplate!!
    }

    var looca:Looca = Looca()

    var grupojanelas = looca.grupoDeJanelas
    var janelas = grupojanelas.janelasVisiveis

    fun inserirBanco(FKMaquina:Int){

        var total = grupojanelas.totalJanelasVisiveis
        var i = 0

        while (i<total){

            var idJanela = janelas[i].janelaId
            var pidJanela = janelas[i].pid
            var nomeJanela = janelas[i].titulo

            jdbcTemplate.update(
                "INSERT INTO Janelas_Abertas (IDJanela,PIDJanelas,Nome_Janelas,FKMaquina) VALUES (?,?,?,?)",
                idJanela,pidJanela,nomeJanela,FKMaquina
            )

            i+=1

        }

    }

}