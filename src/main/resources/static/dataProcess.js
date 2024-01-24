
function displayClientInfo(client) {
 // Získanie referencie na elementy v HTML
//
  const userFirstName = document.getElementById('userFirstName');
  const personalData = document.getElementById('personalDataContent');
  const insuranceInformation = document.getElementById('insuranceDataContent');
  // Vymazanie predchádzajúcich údajov (ak existujú)


 userFirstName.textContent = "  " + client.personalData.firstName;
 personalData.innerHTML = "<br>" + "<b>Name:</b> " + "<br>" + client.personalData.firstName + "<br>" +"<br>" +
                          "<b>Last Name: </b>: "+ "<br>" + client.personalData.secondName  + "<br>" +"<br>" +
                          "<b>Email address: </b>: "+ "<br>" + client.loginInfo.email  + "<br>" +"<br>" +
                          "<b>Birth date: </b>: "+ "<br>" + client.personalData.birthDate  + "<br>" +"<br>" +
                          "<b>Phone number: </b>: "+ "<br>" + client.personalData.number;




insuranceInformation.innerHTML =   "<br>" + "<b>ID of insured:</b> " + "<br>" + client.insuranceInfo.identificationNumberOfInsured + "<br>" +"<br>" +
                                  "<b>Insurance company:</b> "+ "<br>" + client.insuranceInfo.nameOfInsuranceCompany + "<br>" +"<br>" +
                                  "<b>ID of insurance company:</b> "+ "<br>" + client.insuranceInfo.insuranceNumber + "<br>" +"<br>" +
                                  "<b>Birth number:</b> "+ "<br>" + client.insuranceInfo.birthNumber;}
