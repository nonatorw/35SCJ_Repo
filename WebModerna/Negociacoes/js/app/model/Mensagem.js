class Mensagem {
    constructor (inTextoMensagem = "") {
        this._textoMensagem = inTextoMensagem
    }

    get textoMensagem() {
        return this._textoMensagem
    }

    set textoMensagem(inTextoMensagem) {
        this._textoMensagem = inTextoMensagem
    }
}