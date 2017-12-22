import ru.otus.DataBase.DBService;
import ru.otus.DataBase.DBServiceImpl;
import ru.otus.UserData.UserDataSet;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        UserDataSet user1 = new UserDataSet("Mike",25);
        UserDataSet user2 = new UserDataSet("Nick",24);
        UserDataSet user3 = new UserDataSet("Stan",27);
        UserDataSet user4 = new UserDataSet("Howard",29);
        UserDataSet userFromDB;
        List<UserDataSet> list;

        try(DBService dbService = new DBServiceImpl()) {
            dbService.prepareTables();
            dbService.save(user1);
            dbService.save(user2);
            dbService.save(user3);
            dbService.save(user4);
            System.out.println("user Stan have id = " + dbService.getId("Stan"));
            list = dbService.getAllUsers();
            userFromDB = dbService.load(2,UserDataSet.class);
            dbService.deleteTables();
        }

        for (UserDataSet userDataSet : list) {
            System.out.println(userDataSet.toString());
        }
        System.out.println("user initialize from DB: " + userFromDB.toString());
    }
}
