export const translationService = {
    translate,
    detectLanguage,
};

function translate(text) {
    return fetch(`http://localhost:8080/${text}`)
}

function detectLanguage(text) {
    return fetch(`http://localhost:8080/${text}`)
}
