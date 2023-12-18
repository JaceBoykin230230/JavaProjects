import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OpenAI {

    public static String chatGPT(String prompt) throws IOException {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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";
        String model = "gpt-3.5-turbo";
        String role = "user";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \""+ role + "\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuilder response = new StringBuilder();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

           System.out.println(response);
           System.out.println();

           //read from json only the response
           ObjectMapper mapper = new ObjectMapper();
           JsonNode node = mapper.readTree(response.toString());
           //EventHandler.botAddChatResponse(botResponses, node.get("choices").get(0).get("message").get("content").asText());
           return node.get("choices").get(0).get("message").get("content").asText();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
