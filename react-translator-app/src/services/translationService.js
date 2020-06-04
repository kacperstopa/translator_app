export const translationService = {
    translate,
    detectLanguage,
    getTranslationsHistory,
};

function translate(from, to, text) {
    const requestOptions = {
        method: 'POST',
        body: JSON.stringify({
            from: from,
            to: to,
            text: text,
        })
    };
    return fetch(`http://localhost:8080/translate`, requestOptions)
}

function detectLanguage(text) {
    const requestOptions = {
        method: 'POST',
        body: JSON.stringify({
            text: text,
        })
    };
    return fetch(`http://localhost:8080/detect`, requestOptions)
}

function getTranslationsHistory() {
    return fetch(`http://localhost:8080/history`)
}
