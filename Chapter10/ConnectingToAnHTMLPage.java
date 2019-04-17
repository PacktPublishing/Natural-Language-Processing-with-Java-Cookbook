package com.packt.javanlp.cookbook.chapter11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class ConnectingToAnHTMLPage {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://www.w3.org/html/");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.connect();

			InputStreamReader inputStreamReader = new InputStreamReader((InputStream) httpURLConnection.getContent());
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder stringBuilder = new StringBuilder();
			String line = "";
			while (line != null) {
				line = bufferedReader.readLine();
				stringBuilder.append(line + "\n");
			}
			System.out.println(stringBuilder.toString());
			

			System.out.println(httpURLConnection.getContentLength());
			System.out.println(httpURLConnection.getContentType());
			System.out.println(new Date(httpURLConnection.getDate()));
			System.out.println(new Date(httpURLConnection.getExpiration()));
			System.out.println(new Date(httpURLConnection.getLastModified()));
			
		} catch (MalformedURLException ex) {
			// Handle exceptions
			ex.printStackTrace();
		} catch (IOException ex) {
			// Handle exceptions
			ex.printStackTrace();
		}
	}

}
