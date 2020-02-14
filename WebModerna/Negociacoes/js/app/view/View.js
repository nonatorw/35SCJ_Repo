class View {
    constructor(inElement) {
        this._element = inElement
    }

    update(model) {
        this._element.innerHTML = this.getTemplate(model)
    }

    getTemplate() {
        throw new Error("Este método deve ser implementado!")
    }
}