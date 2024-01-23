function processLoginData(data) {
    // Tu môžete spracovať údaje, ktoré ste získali z backendu
    const authToken = data.authToken;
    const client = data.client;
    console.log(client);
       if (Array.isArray(client) && client.length > 0) {
                // Předpokládáme, že první prvek pole obsahuje potřebná data
                displayClientInfo(client[0]);
            } else {
                // Pokud není pole k dispozici, můžete provést další akce nebo upravit kód podle potřeby
                console.error("Chyba: Pole s daty není dostupné nebo je prázdné.");
            }


    // Tu môžete robiť, čo potrebujete s týmito údajmi, napríklad zobraziť informácie o klientovi
    displayClientInfo(client);

    // Taktiež môžete pracovať s autentizačným tokenom, ak je to potrebné
    // Napríklad, uložiť ho do sessionStorage alebo localStorage
    // sessionStorage.setItem('authToken', authToken);
}

function displayClientInfo(client) {
    // Tu môžete zobraziť informácie o klientovi na stránke
    // Napríklad, aktualizovať obsah elementov na stránke podľa informácií z objektu klient
}
