package ru.rksp.spiridonov.processor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.rksp.spiridonov.processor.repository.RawEventRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventService {
    
    @Autowired
    private RawEventRepository rawEventRepository;
    
    @Autowired
    @Qualifier("clickHouseDataSource")
    private DataSource clickHouseDataSource;
    
    public Long getEventCount() {
        return rawEventRepository.count();
    }
    
    public void saveAggregateToClickHouse(Long count) throws SQLException {
        String sql = "INSERT INTO агрегаты_событий_доставки (дата_и_время_записи, количество_записей) VALUES (?, ?)";
        
        try (Connection conn = clickHouseDataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setObject(1, LocalDateTime.now());
            stmt.setLong(2, count);
            stmt.execute();
        }
    }
    
    public List<Map<String, Object>> getAggregatesFromClickHouse() throws SQLException {
        String sql = "SELECT дата_и_время_записи, количество_записей FROM агрегаты_событий_доставки ORDER BY дата_и_время_записи DESC LIMIT 10";
        List<Map<String, Object>> results = new ArrayList<>();
        
        try (Connection conn = clickHouseDataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("дата_и_время_записи", rs.getTimestamp("дата_и_время_записи"));
                row.put("количество_записей", rs.getLong("количество_записей"));
                results.add(row);
            }
        }
        
        return results;
    }
}
