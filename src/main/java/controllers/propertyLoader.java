package controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class propertyLoader {

    private propertyLoader() {
    }

    private static final String path = "src/main/resources/app.properties";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    public static String loadProperty(String name) {
        //инициализируем специальный объект Properties
        //типа Hashtable для удобной работы с данными
        Properties prop = new Properties();
        if (tryGetProp(prop, path)) return prop.getProperty(name);
        return null;
    }

    public static boolean tryGetProp(Properties prop, String path) {
        try {
            //обращаемся к файлу и получаем данные
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader reader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            prop.load(reader);
            return true;
            } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
