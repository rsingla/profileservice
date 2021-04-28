package io.apicode.profile.service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import io.apicode.profile.model.Profile;

//CRUD operations
@Service
public class UserService {

	Logger log = Logger.getLogger("Profile Service");

	public static final String COL_NAME = "profiles";

	public Profile saveProfileDetails(Profile profile) throws InterruptedException, ExecutionException {
		
		String id = getID();
		ApiFuture<WriteResult> collectionsApiFuture = InitId(id).set(profile);

		// Get the Updated time
		String time = collectionsApiFuture.get().getUpdateTime().toString();

		// Set the information before returning
		profile.setId(id);
		profile.setTimeUpdated(time);

		return profile;
	}

	public Profile getProfileDetails(String id) throws InterruptedException, ExecutionException {
		ApiFuture<DocumentSnapshot> future = InitId(id).get();

		DocumentSnapshot document = future.get();

		Profile profile = null;

		if (document.exists()) {
			profile = document.toObject(Profile.class);
			return profile;
		} else {
			return null;
		}
	}

	public String updateProfileDetails(Profile profile, String id) throws InterruptedException, ExecutionException {
		ApiFuture<WriteResult> collectionsApiFuture = InitId(id).set(profile);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	/**
	 * @Profile - Retrieve info using email 
	 * 
	 * @param email
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public String getByEmail(String email) throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> future = InitFireStore().whereEqualTo("email", email).get();
		// future.get() blocks on response
		List<QueryDocumentSnapshot> documents = future.get().getDocuments();
		String id = null;
		for (DocumentSnapshot document : documents) {
			id = document.getId();
			log.info(document.toObject(Profile.class).toString());
		}
		return id;
	}

	public String deleteProfile(String id) {
		InitId(id).delete();
		return "Document with Profile ID " + id + " has been deleted";
	}

	private String getID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	private CollectionReference InitFireStore() {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference collectionReference = dbFirestore.collection(COL_NAME);
		return collectionReference;
	}

	private DocumentReference InitId(String id) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
		return documentReference;
	}

}