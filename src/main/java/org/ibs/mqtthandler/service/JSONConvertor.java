package org.ibs.mqtthandler.service;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.ibs.mqtthandler.domain.Mqtt;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class JSONConvertor {

    String filePath = "./src/main/java/org/ibs/mqtthandler/json/";

    public void writeObjectToJSON(Mqtt obj){

        // Create a Gson object
        Gson gson = new Gson();

        // Convert the object to JSON
        String json = gson.toJson(obj);

        // Define the file path and name

        String fileName = obj.getId() + ".json";

        File file = new File(filePath, fileName);

        try (FileWriter fileWriter = new FileWriter(file)) {
            // Write the JSON string to the file
            fileWriter.write(json);
            System.out.println("JSON data has been written to the file.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readObjectFromJson(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath + fileName +".json"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String jsonString = stringBuilder.toString();

        // Parse the JSON string
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();

        // Extract the "data" field from the JSON object
        String data = jsonObject.get("data").getAsString();

        return data;
    }

}
