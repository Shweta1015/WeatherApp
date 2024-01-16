const WeatherIcon = {
    clear: 'images/clear.png',
    cloud: 'images/cloudy.png',
    rain: 'images/rainy.png',
    snow: 'images/snowy.png',
    haze: 'images/hazey.png',
    mist: 'images/misty.png',
}
function setWeatherIcon(temperature, weatherCondition) {
    const weatherIcon = document.getElementById('weather-icon');
    const weatherImagePath = getWeatherImage(temperature, weatherCondition); 
    
    console.log("Image path:", weatherImagePath); 

    weatherIcon.src = weatherImagePath;
};

function getWeatherImage(temperature, weatherCondition){
    
    if(temperature <= 0){
		return 'images/snowy.png';
		
	} else if (temperature >0 && temperature <=5){
		return 'images/misty.png';
		
	} else if (temperature >=6 && temperature <=10){
		return 'images/hazey.png';
		
	} else if (temperature >=11 && temperature <= 20){
		return 'images/rainy.png';
		
	}else if(temperature >=21 && temperature <=25){
		return 'images/cloudy.png';
		
	} else{
		return 'images/clear.png';
	}
}
