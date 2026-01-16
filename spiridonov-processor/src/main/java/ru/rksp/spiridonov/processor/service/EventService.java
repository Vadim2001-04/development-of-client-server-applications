package ru.rksp.spiridonov.processor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.rksp.spiridonov.processor.repository.RawEventRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
}
