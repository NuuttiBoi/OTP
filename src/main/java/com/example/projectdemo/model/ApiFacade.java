package com.example.projectdemo.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * Class that hides the logic behind making a http call to apis.
 * @author Nuutti Turunen
 */
public class ApiFacade {
  String getAttributeValueFromJson(String urlString, String attributeName) throws IllegalArgumentException, IOException, ParseException {
    if (Objects.equals(urlString, "") || Objects.equals(attributeName, "")) {
      throw new IllegalArgumentException("URL or attribute name cannot be null");
    }
    HttpURLConnection con = null;
    try {
      URL url = new URL(urlString);
      con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
      try{
        con.getResponseCode();
      } catch (Exception e){
        throw new IOException("Invalid URL");
      }
      try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
        StringBuilder content = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
          content.append(inputLine);
        }
        JSONParser parser = new JSONParser();
        Object parsedObject = parser.parse(content.toString());
        if (!(parsedObject instanceof JSONObject)) {
          throw new ParseException(ParseException.ERROR_UNEXPECTED_TOKEN, "Expected a JSON object");
        }
        JSONObject jsonObject = (JSONObject) parsedObject;
        if (!jsonObject.containsKey(attributeName)) {
          throw new IllegalArgumentException("Attribute not found in JSON: " + attributeName);
        }
        return  String.valueOf(jsonObject.get(attributeName));
        }
    } finally {
      if (con != null) {
        con.disconnect();
      }
    }
  }
}

