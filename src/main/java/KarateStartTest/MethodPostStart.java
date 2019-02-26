package KarateStartTest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MethodPostStart
{
    public static void main(String[] args)
    {
        System.out.println(executePost("https://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/party", "6449013711"));
    }

    public static String executePost(String targetURL, String urlParameters) {
        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Authorization",  "Token 3a7a25d6d5f2b2d5a9729c1f6321a99f32860dd0");
            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Отправляемые параметры
            //Класс DataOutputStream представляет поток вывода и предназначен для записи данных примитивных типов, таких, как int, double и т.д.
            // Для записи каждого из примитивных типов предназначен свой метод
            //%s- вместо будет вставлено urlParameters

            DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
            wr.writeBytes(String.format("{ \"query\": \"%s\" }", urlParameters));
            wr.close();

            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null)
            {
                response.append(line);
                response.append('\r');
            }

            rd.close();
            return response.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally
        {
            if (connection != null)
            {
                connection.disconnect();
            }
        }
    }
}
