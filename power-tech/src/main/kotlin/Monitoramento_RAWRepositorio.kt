import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Monitoramento_RAWRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = Conexao.jdbcTemplate!!
    }

    fun buscarDataRede(FKMaquina: Int):LocalDateTime{

        var data:String = jdbcTemplate.queryForObject(
            """
            SELECT Data_Hora_Conexao FROM Redes_conectadas WHERE FKMaquina = 1;
            """, BeanPropertyRowMapper(String::class.java))

        var dataTrasform = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        return dataTrasform

    }

    fun buscarDataJanela(FKMaquina: Int):LocalDateTime{

        var data:String = jdbcTemplate.queryForObject(
            """
            SELECT Data_Hora_Conexao FROM Janelas_Abertas WHERE FKMaquina = 1;
            """, BeanPropertyRowMapper(String::class.java))

        var dataTrasform = LocalDateTime.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        return dataTrasform

    }

    fun buscarData(FKMaquina:Int,componente:String): LocalDateTime{

        var data = jdbcTemplate.queryForObject("""
            SELECT 
	            Data_Hora_Captura 
                FROM 
		            Monitoramento_RAW JOIN Componentes_monitorados 
		                ON FKComponente_Monitorado = IDComponente_monitorado 
			                JOIN Componentes_cadastrados 
				                ON FKComponente_cadastrado = IDComponente_cadastrado
					                JOIN Maquinas 
						                ON FKMaquina = IDMaquina
							                WHERE FKMaquina = $FKMaquina
								                AND Componentes_cadastrados.Apelido = "$componente"
                                                    LIMIT 1;
        """, BeanPropertyRowMapper(Monitoramento_RAW::class.java))

        var data_hora = data.Data_Hora_Captura

        var dataTrasform = LocalDateTime.parse(data_hora,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))

        return dataTrasform

    }

}