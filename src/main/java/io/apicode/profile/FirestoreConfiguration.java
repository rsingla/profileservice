package io.apicode.profile;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirestoreConfiguration {

	@Value("${firebase.credential.path}")
	public String credentialPath;
	
	@Value("${firebase.db.url}")
	String fireBaseDBUrl;
	
	@Autowired
	ResourceLoader resourceLoader;
	
	public Resource loadDBCredentialFile() {
	    return resourceLoader.getResource(credentialPath);
	}

	@PostConstruct
	public void initialize() {
		try {
			Resource resource = loadDBCredentialFile();
			FileInputStream serviceAccount = new FileInputStream(resource.getFile());

			FirebaseOptions options = FirebaseOptions.builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl(fireBaseDBUrl).build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}