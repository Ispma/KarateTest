package KarateStartTest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SecondVersionStart
{
    public static void main(String[] args)
    {
        //Тут в конце мы указываем ip нужного нам города
        System.out.println( executePost("https://suggestions.dadata.ru/suggestions/api/4_1/rs/detectAddressByIp?ip=78.111.246.6"));
    }

    public static String executePost(String targetURL)
    {
        HttpURLConnection connection = null;
        //В Java предоставляется подкласс HttpURLConnection, производный от класса URLConnection и поддерживающий соединения по сетевому протоколу НТТР.
        // Чтобы получить объект класса HttpURLConnection, следует вызвать метод openConnection() для объекта типа URL

        try
        {
            //Create connection
            URL url = new URL(targetURL);
            //Создаём переменную приведённого типа (HttpURLConnection) и вызываем соответствующим методом
            connection = (HttpURLConnection) url.openConnection();
            //Метод GET отправляет скрипту всю собранную информацию формы как часть URL
            //Метод POST передает данные таким образом, что пользователь сайта уже не видит передаваемые скрипту данные
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty( "Authorization", "Token 3a7a25d6d5f2b2d5a9729c1f6321a99f32860dd0");



            connection.setUseCaches(false);
            connection.setDoOutput(true);

            //Базовый класс InputStream представляет классы, которые получают данные из различных источников:
            //массив байтов
            //строка (String)
            //файл
            //InputStream - поток, BufferedReader - буферизирует поток, InputStreamReader - считывает поток
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuffer response = new StringBuffer();
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
