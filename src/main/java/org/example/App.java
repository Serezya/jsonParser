package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, ParseException {
        String json = readString("new_data.json");
        List<Employee> list = jsonToList(json);
        System.out.println(list.toString());

    }

    private static List<Employee> jsonToList(String json) throws ParseException {
        List<Employee> employeeList = new ArrayList<>();
        JSONArray jsonParser = (JSONArray) new JSONParser().parse(json);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        for (Object jsonArray : jsonParser) {
            employeeList.add(gson.fromJson(jsonArray.toString(), Employee.class));
        }
        return employeeList;
    }

    private static String readString(String s) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(s));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
