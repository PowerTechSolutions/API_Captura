object Captura {

    fun pegarRede(maquinaEscolhida: Int){

        var capturarede = CapturaRede()
        capturarede.iniciarSqlServer()

        capturarede.inserirBanco(maquinaEscolhida)

        capturarede.iniciarMysql()

        capturarede.inserirBanco(maquinaEscolhida)

    }

    fun pegarJanelas(maquinaEscolhida: Int){

        var capturajanela = CapturaJanelas()
        capturajanela.iniciarSql()

        capturajanela.inserirBanco(maquinaEscolhida)

        capturajanela.iniciarMysql()

        capturajanela.inserirBanco(maquinaEscolhida)

    }

    fun pegarusbs(maquinaEscolhida: Int){

        var capturausb = CapturaUsb()
        capturausb.iniciarSql()

        capturausb.inserirBanco(maquinaEscolhida)

        capturausb.iniciarMYSQL()

        capturausb.inserirBanco(maquinaEscolhida)

    }

}