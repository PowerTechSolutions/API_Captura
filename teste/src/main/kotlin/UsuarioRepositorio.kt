import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class UsuarioRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = Conexao.jdbcTemplate!!
    }

    fun autenticar(cpf:String):Boolean{

        var usuario = jdbcTemplate.queryForObject(
            "SELECT * FROM Usuario_Dashboard WHERE Cpf = $cpf",
            BeanPropertyRowMapper(Usuario::class.java)
        )

        if (usuario != null){
            return true
        }else{
            return false
        }

    }

    fun resgatarinfo(cpf:String):Usuario{

        var usuario = jdbcTemplate.queryForObject(
            "SELECT * FROM Usuario_Dashboard WHERE Cpf = $cpf",
            BeanPropertyRowMapper(Usuario::class.java)
        )

        return usuario

    }

}