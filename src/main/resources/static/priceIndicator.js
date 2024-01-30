function updatePriceIndicator(price) {
  const priceIndicator = document.getElementById('priceIndicator');

  // Převedeme cenu na procentuální hodnotu mezi 0 a 100
  let percentage = 100;
  if(price < 500){
  percentage = price / 5 }

  // Změníme barvu polokruhu podle procentuální hodnoty
  const colors = getColorForPercentage(percentage);
  priceIndicator.style.background = `linear-gradient(to bottom, ${colors.start} 0%, ${colors.end} 100%)`;
}

function getColorForPercentage(percentage) {
  // Zde můžete implementovat logiku pro změnu barev podle vašich požadavků
  // Například, pokud chcete, aby bylo zelené na začátku, oranžové v polovině a červené na konci:
  const normalizedPercentage = Math.round(percentage);
   switch (true) {
      case normalizedPercentage < 14:
        return {start:'green' , end:'green' };
      case normalizedPercentage <= 20:
        return {start:'yellow' , end:'green' };
      case normalizedPercentage <= 30:
        return {start:'yellow' , end:'yellow' };
      case normalizedPercentage <= 45:
        return {start:'orange' , end:'yellow' };
      case normalizedPercentage <= 60:
        return {start:'orange' , end:'orange' };
      case normalizedPercentage <= 80:
        return {start:'red' , end:'orange' };
      case normalizedPercentage <= 100:
        return {start:'red' , end:'red' };
      default:
        return 'black';
    }
}