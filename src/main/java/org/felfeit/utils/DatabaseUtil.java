package org.felfeit.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseUtil {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Membaca konfigurasi
                String url = "jdbc:mysql://localhost:3306/jdbc_inventaris";
                String user = "root";
                String password = "";

                // Membuat Koneksi
                connection = DriverManager.getConnection(url, user, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
