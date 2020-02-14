class ListaNegociacoes {
    constructor() {
        this._negociacoes = []
    }

    adiciona(negociacoes) {
        this._negociacoes.push(negociacoes)
    }

    get negociacoes() {
        /*
         * Programacao defensiva: gera uma nova lista e concatena a lista existente 
         * => Imutabilidade: Serve para evitar injecao indevida de dados para manter a integridade do objeto.
         */
        return [].concat(this._negociacoes)
    }
}