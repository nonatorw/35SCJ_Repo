class MensagemView extends View {
    constructor(inElement) {
        super(inElement)
    }

    getTemplate(inModel) {
        return inModel.textoMensagem ? `<p class='alert alert-info'>${inModel.textoMensagem}</p>` : `<p></p>`
    }
}