package com.realdolmen.hbo5.wasdapp.wasdappcore.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.FireBaseRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.rest.WasdappEntryMapper;
import com.realdolmen.hbo5.wasdapp.wasdappcore.rest.WasdappEntryResponseMapper;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutionException;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FireBaseService implements Serializable {

    @Autowired
    private FireBaseRepository repo;

    private WasdappEntryResponseMapper wasdappEntryResponseMapper;

    private WasdappEntryMapper wasdappEntryMapper;

    public void addEntry(WasdappEntryResponse entryResponse) throws IOException, FileNotFoundException, InterruptedException, ExecutionException {
        WasdappEntry entry = wasdappEntryResponseMapper.mapToEntry(entryResponse);
        if (entry.getAanmaakDatum() == null) {
            entry.setAanmaakDatum(Timestamp.valueOf(LocalDateTime.now()));
        }
        entry.setWijzigDatum(Timestamp.valueOf(LocalDateTime.now()));
        repo.addEntry(entry);
    }

    public void updateEntry(WasdappEntryResponse entryResponse) {
        WasdappEntry entry = wasdappEntryResponseMapper.mapToEntry(entryResponse);
        if (entry.getAanmaakDatum() == null) {
            entry.setAanmaakDatum(Timestamp.valueOf(LocalDateTime.now()));
        }
        entry.setWijzigDatum(Timestamp.valueOf(LocalDateTime.now()));
        repo.updateEntry(entry, entry.getId());
    }

    public void addEntryList(List<WasdappEntry> entries) throws IOException, InterruptedException, FileNotFoundException, ExecutionException {
        repo.addEntryList(entries);
    }

    public List<WasdappEntryResponse> findAll() throws InterruptedException, ExecutionException {
        return repo.findAll();
    }

    public WasdappEntryResponse findById(Long id) throws InterruptedException, ExecutionException {
        return repo.findById(id);
    }

    public void deleteById(Long id) throws InterruptedException, ExecutionException {
        repo.deleteById(id);
    }
    
    public void createUser(String email, String password, String displayName) throws FirebaseAuthException{
        repo.createUser(email, password, displayName);
    }
    
    public List<UserWassdapp> findAllUsers() throws FirebaseAuthException{
        return repo.findAllUsers();
    }

}
