class DateHelper {
    constructor() {
        throw new Error("Esta não é uma classe instanciável!")
    }

    static toDate(dateString) {
        if (!(/\d{4}-\d{2}-\d{2}/.test(dateString))) {
            throw new Error("Data deve estar no formato \"YYYY-MM-DD\"")
        }

        return new Date(
            ...dateString.split('-')
                         .map((it, idx) => it = idx == 1 ? it + 1 : it)
        )
    }

    static toDateString(data) {
        return `${data.getDate()}/${data.getMonth()+1}/${data.getFullYear()}`
    }
}