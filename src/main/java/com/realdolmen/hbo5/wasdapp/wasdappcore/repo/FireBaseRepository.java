package com.realdolmen.hbo5.wasdapp.wasdappcore.repo;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.cloud.FirestoreClient;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.UserWassdapp;
import com.realdolmen.hbo5.wasdapp.wasdappcore.domain.WasdappEntry;
import com.realdolmen.hbo5.wasdapp.wasdappcore.dto.WasdappEntryResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.springframework.stereotype.Repository;

@Repository
public class FireBaseRepository {

    private Firestore db;
    private CollectionReference collection;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public void init() {
        db = FirestoreClient.getFirestore();
        collection = db.collection("wasdapps");
    }

    public void createUser(String email, String password, String displayName) throws FirebaseAuthException {
        CreateRequest request = new CreateRequest();
        request.setEmail(email);
        request.setEmailVerified(false);
        request.setPassword(password);
        request.setDisplayName(displayName);
        request.setDisabled(false);
        auth.createUser(request);
    }

    public List<UserWassdapp> findAllUsers() throws FirebaseAuthException {
        ListUsersPage users = FirebaseAuth.getInstance().listUsers(null);
        List<UserWassdapp> userList = new ArrayList<>();
        for (ExportedUserRecord user : users.iterateAll()) {
            UserWassdapp userWassDapp = new UserWassdapp();
            userWassDapp.setEmail(user.getEmail());
            userWassDapp.setName(user.getDisplayName());
            userWassDapp.setAchterNaam("");
            userList.add(userWassDapp);
        }
        return userList;
    }

    public void addEntry(WasdappEntry entry) throws FileNotFoundException, IOException, InterruptedException, ExecutionException {
        init();
        if (!collection.get().get().isEmpty()) {
            long lastId = collection.orderBy("id", Query.Direction.DESCENDING).limit(1).get().get().toObjects(WasdappEntryResponse.class).get(0).getId();
            entry.setId(lastId + 1);
            collection.document(Long.toString(lastId + 1)).set(entry);
        } else {
            entry.setId(0L + 1);
            collection.document("1").set(entry);
        }
    }

    public void updateEntry(WasdappEntry entry, Long id) {
        init();
        collection.document(Long.toString(id)).set(entry);
    }

    public void addEntryList(List<WasdappEntry> entries) throws IOException, FileNotFoundException, InterruptedException, ExecutionException {
        init();
        for (WasdappEntry e : entries) {
            addEntry(e);
        }
    }

    public List<WasdappEntryResponse> findAll() throws InterruptedException, ExecutionException {
        init();
        List<WasdappEntryResponse> entries = new ArrayList<>();
        if (!collection.get().get().isEmpty()) {
            List<QueryDocumentSnapshot> docs = collection.get().get().getDocuments();
            for (QueryDocumentSnapshot doc : docs) {
                entries.add(doc.toObject(WasdappEntryResponse.class));
            }
        }
        return entries;
    }

    public WasdappEntryResponse findById(Long id) throws InterruptedException, ExecutionException {
        init();
        if (collection.document(Long.toString(id)).get().get().exists()) {
            return collection.document(Long.toString(id)).get().get().toObject(WasdappEntryResponse.class);
        }
        return null;
    }

    public void deleteById(Long id) throws InterruptedException, ExecutionException {
        init();
        if (collection.document(Long.toString(id)).get().get().exists()) {
            collection.document(Long.toString(id)).delete();
        }
    }
}
