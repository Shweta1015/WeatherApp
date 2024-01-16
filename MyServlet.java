package com;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MyServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//API Setting
		String apiKey = "c051378a8601740ca00740766b5fd022";
		//Getting city name from user
		String city = request.getParameter("city");
		//creating URL for the OpenWeatherMap API request
		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
		
		 try{//API Integration
		URL url = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();    //Typecasting into http
		connection.setRequestMethod("GET");
		
		//used for reading input(byte code) of user through keyboard/files etc
		InputStream inputStream = connection.getInputStream();
		InputStreamReader reader = new InputStreamReader(inputStream);
		
		//to store in string
		StringBuilder content = new StringBuilder(); 
		
		//to take input from reader
		Scanner sc = new Scanner(reader);
		while(sc.hasNext()) {
			content.append(sc.nextLine());
		}
		sc.close();
		
		//TypeCasting(parsing of data in JSON)
		Gson gson= new Gson();
		JsonObject jsonObject = gson.fromJson(content.toString(), JsonObject.class);
		
		//Date & Time
		long dateTime = jsonObject.get("dt").getAsLong()*1000;
		String date = new Date(dateTime).toString();
		
		//Temperature
		double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
		int temperatureCelcius = (int)(temperatureKelvin - 273.15);
		
		//Humidity
		int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt(); 
		
		//Wind Speed
		double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();
		
		//Weather Condition
		String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
		
		//Setting data to forwarding to JSP page
		request.setAttribute("city", city);
		request.setAttribute("date", date);
		request.setAttribute("temperature", temperatureCelcius);
		request.setAttribute("humidity", humidity);
		request.setAttribute("windSpeed", windSpeed);
		request.setAttribute("weatherCondition", weatherCondition);
		request.setAttribute("WeatherData", content.toString());
		
		connection.disconnect();    //to disconnect from api after fetching data
		 } catch(IOException e) {
			 String errorMsg = "Please enter correct city";
			 request.setAttribute("errorMsg", errorMsg);
			 request.getRequestDispatcher("index.jsp").forward(request, response);;
		 }
		
		request.getRequestDispatcher("index.jsp").forward(request, response);  //forwarding request data to jsp
	}  

}
