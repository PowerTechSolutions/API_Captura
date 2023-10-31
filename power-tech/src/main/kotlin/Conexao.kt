
import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

object Conexao {

        var jdbcTemplate: JdbcTemplate? = null
            get() {
                if (field == null){

                    val dataSource = BasicDataSource()

                    dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
                    dataSource.url = "jdbc:mysql://localhost:3306/PowerTechSolutions"
                    dataSource.username = "root"
                    dataSource.password = "@myLOVEisthe0506"

                    val novojdbcTmeplate = JdbcTemplate(dataSource)

                    field = novojdbcTmeplate

                }

                return field

            }

    fun iniciar(){
        jdbcTemplate = jdbcTemplate!!
    }

}

