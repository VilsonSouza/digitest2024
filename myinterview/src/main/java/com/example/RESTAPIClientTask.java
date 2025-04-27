package com.example;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RESTAPIClientTask {

    private static final String API_URL = "https://3ospphrepc.execute-api.us-west-2.amazonaws.com/prod/RDSLambda";

    public static void main(String[] args) {
        try {
            String response = makeApiRequest(API_URL);
            Map<String, Integer> genderCount = countRecordsByGender(response);
            printGenderCount(genderCount);
            String fileName = "john_doe_gender_count.csv";
            generateCSV(genderCount, fileName);
            uploadFileToS3(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String makeApiRequest(String apiUrl) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(apiUrl);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
        }
    }

    private static Map<String, Integer> countRecordsByGender(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonResponse);

        Map<String, Integer> genderCount = new HashMap<>();
        if (rootNode.isArray()) {
            for (JsonNode record : rootNode) {
                JsonNode genderNode = record.get("gender");
                if (genderNode != null && !genderNode.isNull()) {
                    String gender = genderNode.asText();
                    genderCount.put(gender, genderCount.getOrDefault(gender, 0) + 1);
                }
            }
        }
        return genderCount;
    }

    private static void printGenderCount(Map<String, Integer> genderCount) {
        System.out.println("Contagem de registros por gênero:");
        for (Map.Entry<String, Integer> entry : genderCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    private static void generateCSV(Map<String, Integer> genderCount, String fileName) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Gender,Count\n");
            for (Map.Entry<String, Integer> entry : genderCount.entrySet()) {
                writer.append(entry.getKey()).append(",").append(String.valueOf(entry.getValue())).append("\n");
            }
        }
        System.out.println("Arquivo CSV gerado: " + fileName);
    }

    private static void uploadFileToS3(String fileName) {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials("", "");

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("us-west-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

        File file = new File(fileName);

        s3Client.putObject(new PutObjectRequest("interview-digiage", fileName, file));
        System.out.println("Arquivo enviado para o S3 com sucesso!");
    }
}
