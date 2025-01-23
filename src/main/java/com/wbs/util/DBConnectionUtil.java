package com.wbs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConnectionUtil
{
    private static final Properties properties = new Properties();

    static { //TODO what is this
        try (InputStream input = DBConnectionUtil.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                System.exit(1);
            }

            // Load the properties file
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDbUrl(String databaseName) {
        return properties.getProperty("db.url") + databaseName;
    }

    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }
}
