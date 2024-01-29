
function displayClientInfo(client) {
 // Získanie referencie na elementy v HTML
//
  const userFirstName = document.getElementById('userFirstName');
  const personalData = document.getElementById('personalDataContent');
  const insuranceInformation = document.getElementById('insuranceDataContent');
const welcomeUserName = document.getElementById('userName');
const homePageContent = document.getElementById('homeDataContent')

 welcomeUserName.innerHTML = "Welcome, " +  client.personalData.firstName+ "!" ;



 userFirstName.innerHTML= "  " + client.personalData.firstName ;
 personalData.innerHTML = "<br>" + "<b>Name:</b> " + "<br>" + client.personalData.firstName + "<br>" +"<br>" +
                          "<b>Last Name: </b> "+ "<br>" + client.personalData.secondName  + "<br>" +"<br>" +
                          "<b>Email address: </b> "+ "<br>" + client.loginInfo.email  + "<br>" +"<br>" +
                          "<b>Birth date: </b> "+ "<br>" + client.personalData.birthDate  + "<br>" +"<br>" +
                          "<b>Phone number: </b>"+ "<br>" + client.personalData.number;




insuranceInformation.innerHTML =   "<br>" + "<b>ID of insured:</b> " + "<br>" + client.insuranceInfo.identificationNumberOfInsured + "<br>" +"<br>" +
                                  "<b>Insurance company:</b> "+ "<br>" + client.insuranceInfo.nameOfInsuranceCompany + "<br>" +"<br>" +
                                  "<b>ID of insurance company:</b> "+ "<br>" + client.insuranceInfo.insuranceNumber + "<br>" +"<br>" +
                                  "<b>Birth number:</b> "+ "<br>" + client.insuranceInfo.birthNumber;}

function calculatePZP(yearsOfDriving, ageOfCar, powerOfCar, km){
const startingPrice = 30;
const km = km * 0.0001;
const yearsOfDriving = yearsOfDriving * 0.5 ;
const ageOfCar = ageOfCar * 2 ;
const powerOfCar = powerOfCar  * 0.5 ;
const minimumPrice = startingPrice + powerOfCar;

const result = startingPrice + ageOfCar + powerOfCar + km  - ageOfInsured;
if(result < minimumPrice){
    return minimumPrice;
}
return result;
}
}

