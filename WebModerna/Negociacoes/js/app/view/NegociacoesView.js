class NegociacoesView {
    constructor(element) {
        this._element = element
    }

    _getTemplate(model) {
        return `
        <table class="table table-hover table-bordered">
            <thead>
                <tr>
                    <th>Data</th>
                    <th>Quantidade</th>
                    <th>Valor</th>
                    <th>Volume</th>
                </tr>
            </thead>
        
            <tbody>
                ${model.negociacoes.map(idx => 
                   `<tr>
                        <td>${DateHelper.toDateString(idx.data)}</td>
                        <td>${idx.quantidade}</td>
                        <td>${idx.valor}</td>
                        <td>${idx.volume}</td>
                    </tr>`
                ).join('')}                
            </tbody>
                  
            <tfoot>
                <td colspan="3">Total</td>
                <td>
                    ${(function() {
                        let total = 0
                        model.negociacoes.forEach(element => total += element.volume)
                        return total
                    })()}

                    ${model.negociacoes.reduce((total, n) => total + n.volume, 0.0)}
                </td>
            </tfoot>
        </table>
        `;
    }

    update(model) {
        this._element.innerHTML = this._getTemplate(model)
    }
}