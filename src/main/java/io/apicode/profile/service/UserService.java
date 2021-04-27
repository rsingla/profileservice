package io.apicode.profile.service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import io.apicode.profile.model.Profile;

//CRUD operations
@Service
public class UserService {

	public static final String COL_NAME = "profiles";

	public Profile saveProfileDetails(Profile profile) throws InterruptedException, ExecutionException {
		String id = getID();
		ApiFuture<WriteResult> collectionsApiFuture = InitId(id).set(profile);

		//Get the Updated time 
		String time = collectionsApiFuture.get().getUpdateTime().toString();

		//Set the information before returning
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

	public String deleteProfile(String id) {
		InitId(id).delete();
		return "Document with Profile ID " + id + " has been deleted";
	}

	public String getID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	private DocumentReference InitId(String id) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(id);
		return documentReference;
	}

}