package com.realdolmen.hbo5.wasdapp.wasdappcore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.FireBaseRepository;
import com.realdolmen.hbo5.wasdapp.wasdappcore.repo.WasdappEntryRepository;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WasdappCoreApplication {

    @Autowired
    WasdappEntryRepository wasdappEntryRepository;

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        FileInputStream serviceAccount
                = new FileInputStream(".\\wasdapptestkey.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://wasdapptest.firebaseio.com")
                .build();

        FirebaseApp app = FirebaseApp.initializeApp(options);
        FirebaseAuth.getInstance(app);
        
        SpringApplication.run(WasdappCoreApplication.class, args);

    }

}
