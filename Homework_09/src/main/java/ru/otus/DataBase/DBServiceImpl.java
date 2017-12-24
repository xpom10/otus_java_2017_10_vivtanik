package ru.otus.DataBase;

import ru.otus.Executor.Executor;
import ru.otus.UserData.DataSet;
import ru.otus.UserData.UserDataSet;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBServiceImpl implements DBService {
    private final Connection connection;

    private final String CREATE_TABLES = "create table if not exists user (id bigint auto_increment, name varchar(256), age int(3), primary key (id))";
    private final String DROP_TABLE = "drop table user";
    private final String INSERT_USER = "insert into user (name,age) values (?,?)";
    private final String SELECT_USER_NAME = "select * from user where name = (?)";
    private final String SELECT_USER_ID = "select * from user where id = (?)";
    private final String SELECT_USERS = "select * from user";

    public DBServiceImpl() {
        this.connection = ConnectionHelper.getConnection();
    }

    @Override
    public String getMetaData() {
        try {
            return "Connected to: " + connection.getMetaData().getURL() + "\n" +
                    "DB name: " + connection.getMetaData().getDatabaseProductName() + "\n" +
                    "DB version: " + connection.getMetaData().getDatabaseProductVersion() + "\n" +
                    "Driver: " + connection.getMetaData().getDriverName();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    @Override
    public void prepareTables() throws SQLException {
        Executor exec = new Executor(getConnection());
        exec.execQuery(CREATE_TABLES);
        System.out.println("Table create");
    }


    @Override
    public <T extends DataSet> void save(T user) throws SQLException, IllegalAccessException, NoSuchFieldException {
        Executor exec = new Executor(getConnection());
        Field[] fields = user.getClass().getDeclaredFields();
        exec.execQuery(INSERT_USER, statement -> {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("name")) {
                    statement.setString(1, (String) field.get(user));
                } else if (field.getName().equals("age")) {
                    statement.setInt(2, (Integer) field.get(user));
                }
            }
            statement.execute();
        });

    }


    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException {
        Executor exec = new Executor(getConnection());
        return exec.execQuery(SELECT_USER_ID,
                statement -> {
                    statement.setLong(1,id);
                    statement.execute();
                }, result -> {
                    if (result.next()) {
                        Object user = clazz.newInstance();
                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            field.setAccessible(true);
                            field.set(user, result.getObject(field.getName()));
                        }
                        return (T)user;
                    } else return null;
                });
    }

    @Override
    public List<UserDataSet> getAllUsers() throws SQLException, IllegalAccessException, InstantiationException {
        Executor exec = new Executor(getConnection());
        return exec.execQuery(SELECT_USERS, result -> {
            List<UserDataSet> list = new ArrayList<>();
            while (result.next()) {
                list.add(new UserDataSet(result.getString("name"),result.getInt("age")));
            }
            return list;
        });

    }

    @Override
    public long getId(String name) throws SQLException, IllegalAccessException, InstantiationException {
        Executor exec = new Executor(getConnection());
        return exec.execQuery(SELECT_USER_NAME, statement -> {
            statement.setString(1,name);
            statement.execute();
        }, result -> {
            if (result.next()) {
                return result.getLong("id");
            } else return null;
        });

    }


    @Override
    public void deleteTables() throws SQLException {
        Executor exec = new Executor(getConnection());
        exec.execQuery(DROP_TABLE);
        System.out.println("Table dropped");
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection close!");
    }

    private Connection getConnection() {
        return connection;
    }
}
