import com.google.gson.Gson;
import ru.otus.myJson.Car;
import ru.otus.myJson.Color;
import ru.otus.myJson.MyJson;

public class Main {


    public static void main(String[] args) throws IllegalAccessException {

        Color color = new Color();
        Car car1 = new Car("kia","rio",9,color);
        Gson gson = new Gson();

        MyJson json = new MyJson();
        String myJson = json.serializeJSON(car1);
        String gJson = gson.toJson(car1);
        System.out.println(myJson);
        System.out.println(gJson);

    }
}
