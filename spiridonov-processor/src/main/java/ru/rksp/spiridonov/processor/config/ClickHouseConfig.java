package ru.rksp.spiridonov.processor.config;

import com.clickhouse.jdbc.ClickHouseDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
public class ClickHouseConfig {
    
    @Value("${clickhouse.url}")
    private String url;
    
    @Value("${clickhouse.username}")
    private String username;
    
    @Value("${clickhouse.password}")
    private String password;
    
    @Bean(name = "clickHouseDataSource")
    public DataSource clickHouseDataSource() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        return new ClickHouseDataSource(url, properties);
    }
}
