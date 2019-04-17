package com.packt.weatherbot2.weather;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class WeatherBot implements RequestHandler<String, String> {

	@Override
	public String handleRequest(String city, Context context) {
		context.getLogger().log("Input: " + city);

		// TODO: implement your handler
		switch (city) {
			case "London":
				return "The current temperature is 56";
			case "Miami":
				return "The current temperature is 86";
			case "Barrow":
				return "The current temperature is -26";
		}
		return "Error - Unknown city";
	}

}
