import com.google.gson.Gson;
import ru.otus.myJson.Car;
import ru.otus.myJson.MyJson;
import ru.otus.myJson.Specification;

public class Main {


    public static void main(String[] args) throws IllegalAccessException {

        Specification specification = new Specification();
        Car car = new Car("kia","rio",9, specification);
        Gson gson = new Gson();

        MyJson json = new MyJson();
        String myJson = json.serializeJSON(car);
        System.out.println(myJson);

        Car car1 = gson.fromJson(myJson,Car.class);
        String toGSON = gson.toJson(car1);
        System.out.println(toGSON);

        System.out.println(toGSON.equals(myJson));

    }
}
