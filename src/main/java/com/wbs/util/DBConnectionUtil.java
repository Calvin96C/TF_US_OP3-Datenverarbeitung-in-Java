package com.wbs.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConnectionUtil
{
    private static final Properties credentialProperties = new Properties();
    private static final Properties configProperties = new Properties();

    static {
        // Store config info in a Property object
        try (InputStream configInput  = DBConnectionUtil.class.getClassLoader().getResourceAsStream("config.properties"))
        {
            configProperties.load(configInput);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // Retrieve the credentials file path
        String credentialsPath = configProperties.getProperty("credentials.path");
        if (credentialsPath == null || credentialsPath.isEmpty()) {
            throw new RuntimeException("Missing 'credentials.path' in config.properties");
        }

        // use the configProperties file path to retrieve the actual credentials
        try (FileInputStream input = new FileInputStream(credentialsPath))
        {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                System.exit(1);
            }
            // Load the credentialProperties file
            credentialProperties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDbUrl(String databaseName) {
        return credentialProperties.getProperty("db.url") + databaseName;
    }

    public static String getDbUsername() {
        return credentialProperties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return credentialProperties.getProperty("db.password");
    }
}
