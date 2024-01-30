function updatePriceIndicator(price) {
  const priceIndicator = document.getElementById('priceIndicator');

  let percentage = 100;
  if(price < 500){
  percentage = price / 5 }



  // Změníme barvu polokruhu podle procentuální hodnoty
  const colors = getColorForPercentage(percentage);
  priceIndicator.style.background = `linear-gradient(to bottom, ${colors.start} 0%, ${colors.end} 100%)`;
  priceIndicator.innerHTML = "<b>" + colors.level + "</b>";
     //style of text

       priceIndicator.style.textAlign = 'center';
        priceIndicator.style.lineHeight = '265px';
       priceIndicator.style.fontFamily = 'Raleway, sans-serif';
       priceIndicator.style.fontSize = '1.9em';
       priceIndicator.style.color = 'white';

}
function getColorForPercentage(percentage) {
  // Zde můžete implementovat logiku pro změnu barev podle vašich požadavků
  // Například, pokud chcete, aby bylo zelené na začátku, oranžové v polovině a červené na konci:
  const normalizedPercentage = Math.round(percentage);
   switch (true) {
      case normalizedPercentage < 14:
        return {start:'green' , end:'green',level:'Lowest'  };
      case normalizedPercentage <= 20:
        return {start:'yellow' , end:'green',level:'Economy'  };
      case normalizedPercentage <= 30:
        return {start:'yellow' , end:'yellow',level:'Mid Tier'  };
      case normalizedPercentage <= 45:
        return {start:'orange' , end:'yellow',level:'Standard'  };
      case normalizedPercentage <= 60:
        return {start:'orange' , end:'orange',level:'Premium'  };
      case normalizedPercentage <= 80:
        return {start:'red' , end:'orange',level:'High-end'  };
      case normalizedPercentage <= 100:
        return {start:'red' , end:'red',level:'Luxury' };
      default:
        return 'black';
    }
}