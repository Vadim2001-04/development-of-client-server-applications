package ru.rksp.spiridonov.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rksp.spiridonov.processor.model.RawEvent;

@Repository
public interface RawEventRepository extends JpaRepository<RawEvent, Long> {
}
