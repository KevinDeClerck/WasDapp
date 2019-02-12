package com.realdolmen.hbo5.wasdapp.wasdappcore.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WasdappEntryRepository extends JpaRepository<WasdappEntry, Long> {
    List<WasdappEntry> findByNameContaining(String shouldContain);
}
