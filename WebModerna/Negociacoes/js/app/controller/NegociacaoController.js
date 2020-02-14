class NegociacaoController {
    constructor() {
        let $ = document.querySelector.bind(document)
        this._inputData         = $("#data")
        this._inputQuantidade   = $("#quantidade")
        this._inputValor        = $("#valor")
        this._listaNegociacoes  = new ListaNegociacoes();
        this._negociacoesView   = new NegociacoesView($("#negociacoesView"));

        this._negociacoesView.update(this._listaNegociacoes)
    }

    addNegociacao(event) {
        event.preventDefault()
        console.log(typeof (this._inputData.value))
        console.log("> Data da Controller: ", this._inputData.value)

        this._listaNegociacoes.adiciona(this._criaNegociacao())
        this._negociacoesView.update(this._listaNegociacoes)
        this._resetForm()

        console.log("> Negociacao: ", this._listaNegociacoes.negociacoes)
    }

    _criaNegociacao() {
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
}