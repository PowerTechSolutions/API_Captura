import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

object ConexaoSqlServer {

    var jdbcTemplate: JdbcTemplate? = null
        get() {
            if (field == null){

                val dataSource = BasicDataSource()

                dataSource.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                dataSource.url = "jdbc:sqlserver://34.194.127.191;database=PowerTechSolutions;encrypt=false;trustServerCertificate=false"
                dataSource.username = "sa"
                dataSource.password = "myLOVEisthe0506"

                val novojdbcTmeplate = JdbcTemplate(dataSource)

                field = novojdbcTmeplate

            }

            return field

        }

    fun iniciarSqlServer(){
        jdbcTemplate = jdbcTemplate!!
    }

}