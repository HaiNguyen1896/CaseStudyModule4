//package com.example.module4_shoesshop.firebase;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import org.springframework.boot.SpringApplication;
//
//import java.io.FileInputStream;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Objects;
//
//public class CrudFile {
//    public static void main(String[] args) throws IOException {
//        ClassLoader classLoader = CrudFile.class.getClassLoader();
//        File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
//        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
//
//        FirebaseOptions options = new FirebaseOptions.Builder()
//                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                .setDatabaseUrl("https://console.firebase.google.com/project/file-audio-test/storage/file-audio-test.appspot.com/files")
//                .build();
//
//        FirebaseApp.initializeApp(options);
//        SpringApplication.run(CrudFile.class, args);
//    }
//}
