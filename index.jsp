<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Weather Application</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />
<link rel="stylesheet" href="style.css"/>
</head>
<body>

<div class="box">
     <div class="search">
     <form action="MyServlet" method="post" class="searchBtn">
         <input type="text" name="city" placeholder="Enter city" spellcheck="false">
         <button id="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>
     </form>    
     </div>
     
     <% if (request.getAttribute("errorMsg") != null) { %>
        <div class="erro-box">
        <p><%= request.getAttribute("errorMsg") %></p>
        </div>
    <% } else { %>
         <div class="weather">
        <div class="weatherIcon">
                <img src="" id="weather-icon">
                <input type="hidden" id="wc" value="${weatherCondition}"/>
         </div>
        <h1 class="temp">${temperature} °C</h1>
        <h2 class="city">${city}</h2>
        <div class="info">
            <div class="col">
                <img src="images/humidity.png">
                <div>
                    <p class="humidity">${humidity}%</p>
                    <p>Humidity</p>
                </div>
            </div>

            <div class="col">
                <img src="images/wind.png">
                <div>
                    <p class="wind">${windSpeed} Km/hr</p>
                    <p>Wind Speed</p>
                </div> 
            </div>
        </div>
     </div>
    <% } %>     
</div>
  
 <script src="MyScript.js"></script>
 <script> 
    //Retrieve temperature and weatherCondition for js file
    const temperature = ${temperature};
    const weatherCondition = "${weatherCondition}";
    
    //call the function to set weatherIcon
    setWeatherIcon(temperature, weatherCondition);
</script>
</body>
</html>