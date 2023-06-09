package org.ibs.mqtthandler.csv;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVWriter;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@Service
public class csvHandler {
    public String pathToJSON = "./src/main/java/org/ibs/mqtthandler/json/";




    public csvHandler() throws IOException {
    }

    public void generateCsv(String id) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = getJsonFile(id);
        File csvFile = new File("./src/main/java/org/ibs/mqtthandler/csvFiles/" + id + ".csv");

        try {
            // Read JSON data from file
            JsonNode jsonData = objectMapper.readTree(jsonFile);

            // Create CSV writer
            CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile));

            // Write headers to CSV file
            String[] headers = {"id", "data"};
            csvWriter.writeNext(headers);


                String idText = jsonData.has("id") ? jsonData.get("id").asText() : "";
                String dataValue = jsonData.has("data") ? jsonData.get("data").asText() : "";

                String[] row = {idText, dataValue};
                csvWriter.writeNext(row);


            // Close CSV writer
            csvWriter.close();

            System.out.println("CSV file created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File getCSVFile(String filename) {
        String path = this.pathToJSON;
        File file = new File(path, filename +".csv");
        File parent = file.getParentFile();
        if (parent != null)
            parent.mkdirs();
        return file;
    }

    public ResponseEntity<FileSystemResource> getCSVFile2(String filename){
        String path = "./src/main/java/org/ibs/mqtthandler/csvFiles/";
        File file = new File(path, filename + ".csv");
        File parent = file.getParentFile();
        if (parent != null) {
            parent.mkdirs();
        }

        // Create a resource from the file
        FileSystemResource resource = new FileSystemResource(file);

        // Set the Content-Disposition header to trigger a file download
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", file.getName());

        // Set the content type as CSV
        headers.setContentType(MediaType.parseMediaType("text/csv"));

        // Return the file as a ResponseEntity
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }

    public File getJsonFile(String filename) {
        String path = this.pathToJSON;
        File file = new File(path, filename +".json");
        File parent = file.getParentFile();
        if (parent != null)
            parent.mkdirs();
        return file;
    }
}
