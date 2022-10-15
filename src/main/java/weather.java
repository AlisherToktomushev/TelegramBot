import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class weather {
    public static String getweather(String message,Model model) throws IOException {
        URL url =new URL("https://api.openweathermap.org/data/2.5/weather?q="+ message +"&units=metric&appid=9c7630b70b0c030c1ac072e539a38aea");
        Scanner in =new Scanner((InputStream) url.getContent());
        String result ="";
        while (in.hasNext()){
            result+=in.nextLine();
        }
        JSONObject object =new JSONObject(result);
        model.setName(object.getString("name"));
        JSONObject main =object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        JSONArray getarray= object.getJSONArray("weather");
        for (int i=0;i<getarray.length();i++){
            JSONObject object1 =getarray.getJSONObject(i);
            model.setIcon((String) object1.get("icon"));
            model.setMain((String) object1.get("main"));
        }
      return "City: " + model.getName() + "\n" +
              "Temperature: " + model.getTemp() + "C" + "\n" +
              "Humidity:" + model.getHumidity() + "%" + "\n" +
              "Main: " + model.getMain() + "\n" +
              "http://openweathermap.org/img/w/" + model.getIcon() + ".png";
    }
}
