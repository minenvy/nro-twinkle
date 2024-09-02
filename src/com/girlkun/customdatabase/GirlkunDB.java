package com.girlkun.customdatabase;

// import com.girlkun.result.GirlkunResultSet;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.Statement;

public class GirlkunDB {

    private static final Logger logger = LoggerFactory.getLogger(GirlkunDB.class);
    private static String DRIVER;
    private static String URL;
    private static String DB_HOST;
    private static String DB_PORT;
    private static String DB_NAME;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static int MIN_CONN;
    private static int MAX_CONN;
    private static long MAX_LIFE_TIME;
    public static boolean LOG_QUERY;
    private static HikariConfig config;
    private static HikariDataSource ds;
    
    public static Connection conn;
    public static Statement stat;

    static {
        loadProperties();
        config = new HikariConfig();
        config.setDriverClassName(DRIVER);
        config.setJdbcUrl(URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        config.setMinimumIdle(MIN_CONN);
        config.setMaximumPoolSize(MAX_CONN);
        config.setMaxLifetime(MAX_LIFE_TIME);
        ds = new HikariDataSource(config);
    }

    public GirlkunDB() {
        // Constructor
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close() {
        if (ds != null) {
            ds.close();
        }
    }

    private static void loadProperties() {
        try {
            // Properties properties = new Properties();
            // properties.load(GirlkunDB.class.getClassLoader().getResourceAsStream("girlkundb.properties"));
            // DRIVER = properties.getProperty("girlkun.database.driver");
            // DB_HOST = properties.getProperty("girlkun.database.host");
            // DB_PORT = properties.getProperty("girlkun.database.port");
            // DB_NAME = properties.getProperty("girlkun.database.name");
            // DB_USER = properties.getProperty("girlkun.database.user");
            // DB_PASSWORD = properties.getProperty("girlkun.database.password");
            // MIN_CONN = Integer.parseInt(properties.getProperty("girlkun.database.minConn"));
            // MAX_CONN = Integer.parseInt(properties.getProperty("girlkun.database.maxConn"));
            // MAX_LIFE_TIME = Long.parseLong(properties.getProperty("girlkun.database.maxLifeTime"));
            // LOG_QUERY = Boolean.parseBoolean(properties.getProperty("girlkun.database.logQuery"));
            DRIVER = "com.mysql.cj.jdbc.Driver";
            DB_HOST = "localhost";
            DB_PORT = "3306";
            DB_NAME = "nro";
            DB_USER = "root";
            DB_PASSWORD = "123456";
            MIN_CONN = 1;
            MAX_CONN = 1;
            MAX_LIFE_TIME = 120000;
            LOG_QUERY = false;
            URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME; // Adjust for your DB type
            conn = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            stat = GirlkunDB.conn.createStatement();
        } catch (Exception e) {
            logger.error("Error loading database properties", e);
        }
    }

    public static ResultSet executeQuery(String query) throws Exception {
        return stat.executeQuery(query);
    }

//    public static ResultSet executeQuery(String query, String... objs) throws Exception {
//        Connection conn = getConnection();
//        PreparedStatement stmt = conn.prepareStatement(query);
//        for (int i = 0; i < objs.length; i++) {
//            stmt.setString(i + 1, objs[i]);
//        }
//        ResultSet rs = stmt.executeQuery();
//        return rs;
//    }

    public static int executeUpdate(String query) throws Exception {
        return stat.executeUpdate(query);
    }

//    public static int executeUpdate(String query, String... objs) throws Exception {
//        Connection conn = getConnection();
//        PreparedStatement stmt = conn.prepareStatement(query);
//        for (int i = 0; i < objs.length; i++) {
//            stmt.setString(i + 1, objs[i]);
//        }
//        return stmt.executeUpdate();
//    }
}
