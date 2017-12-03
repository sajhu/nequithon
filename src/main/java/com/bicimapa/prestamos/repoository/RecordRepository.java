package com.bicimapa.prestamos.repoository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bicimapa.prestamos.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
