package com.realdolmen.hbo5.wasdapp.wasdappcore.repo;

import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WasdappEntryRepository extends JpaRepository<WasdappEntry, Long> {
}
