object Captura {

    fun pegarRede(maquinaEscolhida: Int){

        var capturarede = CapturaRede()
        capturarede.iniciar()

        var inserts = capturarede.inserirBanco(maquinaEscolhida)

        println("$inserts Registro(s) inseridos em redes")

    }

    fun pegarJanelas(maquinaEscolhida: Int){

        var capturajanela = CapturaJanelas()
        capturajanela.iniciar()

        var inserts = capturajanela.inserirBanco(maquinaEscolhida)

        println("$inserts Registro(s) inseridos em janelas")

    }

    fun pegarusbs(maquinaEscolhida: Int){

        var capturausb = CapturaUsb()
        capturausb.iniciar()

        var inserts = capturausb.inserirBanco(maquinaEscolhida)

        println("$inserts Registro(s) inseridos em usbs")

    }

}