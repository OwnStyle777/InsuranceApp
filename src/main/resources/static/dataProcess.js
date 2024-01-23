
function displayClientInfo(client) {
 // Získanie referencie na elementy v HTML
//
  const userFirstName = document.getElementById('userFirstName');
  const personalData = document.getElementById('personalDataContent');
  const insuranceInformation = document.getElementById('insuranceDataContent');
  // Vymazanie predchádzajúcich údajov (ak existujú)


 userFirstName.textContent = "  " + client.personalData.firstName;
 personalData.textContent = client.personalData.firstName + " " + client.personalData.secondName;
insuranceInformation.innerHTML =   "<br>" + "<b>ID of Insured:</b> " + "<br>" + client.insuranceInfo.identificationNumberOfInsured + "<br>" +"<br>" +
                                  "<b>Insurance Company</b>: "+ "<br>" + client.insuranceInfo.nameOfInsuranceCompany + "<br>" +"<br>" +
                                  "<b>Birth number:</b> "+ "<br>" + client.insuranceInfo.birthNumber;}
