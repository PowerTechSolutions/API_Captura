import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class ServicoCadastradoRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = Conexao.jdbcTemplate!!
    }

    fun buscarComponente(IDComponente:Int):String{

        var componente = jdbcTemplate.queryForObject("""
            SELECT * FROM Componentes_cadastrados WHERE IDComponente_cadastrado = $IDComponente
        """,BeanPropertyRowMapper(ServicoCadastrados::class.java))

        var resposta = "${componente.Apelido}"

        return resposta

    }

}