package com.realdolmen.hbo5.wasdapp.wasdappcore.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasdappEntryRepository extends JpaRepository<WasdappEntry, Long> {
}
