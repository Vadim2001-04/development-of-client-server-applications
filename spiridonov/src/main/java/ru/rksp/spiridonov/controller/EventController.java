package ru.rksp.spiridonov.processor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rksp.spiridonov.processor.service.EventService;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/events")
public class EventCountController {

    @Autowired
    private EventService eventService;

    @PostMapping("/count")
    public ResponseEntity<String> getAndSaveCount() {
        try {
            Long count = eventService.getEventCount();
            eventService.saveAggregateToClickHouse(count);
            return ResponseEntity.ok("Count saved to ClickHouse: " + count);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/aggregates")
    public ResponseEntity<?> getAggregates() {
        try {
            List<Map<String, Object>> aggregates = eventService.getAggregatesFromClickHouse();
            return ResponseEntity.ok(aggregates);
        } catch (SQLException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}