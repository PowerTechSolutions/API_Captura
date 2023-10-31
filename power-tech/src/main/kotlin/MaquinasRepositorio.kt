import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class MaquinasRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = Conexao.jdbcTemplate!!
    }

    fun pegarMaquinas(IDFuncionario:Int):String{
        var maquinas = jdbcTemplate.query("""
            SELECT * FROM 
            Maquinas 
            WHERE FKFuncionario = $IDFuncionario
        """, BeanPropertyRowMapper(Maquinas::class.java))

        var resposta = "";

        for (maquina in maquinas){
            resposta += "\n\r\n\rNumeração: ${maquina.IDMaquina} | Apelido: ${maquina.Apelido}"
        }

        return resposta

    }



}