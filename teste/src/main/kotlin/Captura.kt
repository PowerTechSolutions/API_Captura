object Captura {

    fun pegarRede(maquinaEscolhida: Int){

        var capturarede = CapturaRede()
        capturarede.iniciar()

        var inserts = capturarede.inserirBanco(maquinaEscolhida)

    }

    fun pegarJanelas(maquinaEscolhida: Int){

        var capturajanela = CapturaJanelas()
        capturajanela.iniciar()

        var inserts = capturajanela.inserirBanco(maquinaEscolhida)

    }

    fun pegarusbs(maquinaEscolhida: Int){

        var capturausb = CapturaUsb()
        capturausb.iniciar()

        var inserts = capturausb.inserirBanco(maquinaEscolhida)

    }

}