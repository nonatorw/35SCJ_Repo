class NegociacaoController {
    constructor() {
        let $ = document.querySelector.bind(document)

        this._inputData         = $("#data")
        this._inputQuantidade   = $("#quantidade")
        this._inputValor        = $("#valor")

        this._listaNegociacoes  = new ListaNegociacoes();
        this._negociacoesView   = new NegociacoesView($("#negociacoesView"));
        this._negociacoesView.update(this._listaNegociacoes)

        this._mensagem          = new Mensagem()
        this._mensagemView      = new MensagemView($("#mensagemView"))
        this._mensagemView.update(this._mensagem)
    }

    addNegociacao(event) {
        event.preventDefault()

        this._listaNegociacoes.adiciona(this._createNegociacao())
        this._negociacoesView.update(this._listaNegociacoes)
        this._mensagem.textoMensagem = "Negociacao criada com successo!"
        this._mensagemView.update(this._mensagem)
        this._resetForm()

        console.log(typeof (this._inputData.value))
        console.log("> Data da Controller: ", this._inputData.value)
        console.log("> Negociacao: ", this._listaNegociacoes.negociacoes)
    }

    _createNegociacao() {
        return new Negociacao(DateHelper.toDate(this._inputData.value)
                             ,this._inputQuantidade.value
                             ,this._inputValor.value)
    }

    _resetForm() {
        this._inputData.value            = ''
        this._inputQuantidade.value      = 1
        this._inputValor.value           = 0.00

        this._inputData.focus()
    }

    deleteNegociacao() {
        this._listaNegociacoes.esvazia()
        this._mensagem.textoMensagem = "Negociações eliminadas com sucesso!"
        this._negociacoesView.update(this._listaNegociacoes)
        this._mensagemView.update(this._mensagem)
        this._resetForm()
    }
}