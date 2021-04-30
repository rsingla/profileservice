package io.apicode.profile.model;

public class Util {

	public static Profile merge(Profile profile, Profile profileDB) {

		if (profile.getFirstName() == null) {
			profile.setFirstName(profileDB.getFirstName());
		}

		if (profile.getLastName() == null) {
			profile.setLastName(profileDB.getLastName());
		}

		if (profile.getDateOfBirth() == null) {
			profile.setDateOfBirth(profileDB.getDateOfBirth());
		}

		if (profile.getPhoneNumber() == null) {
			profile.setPhoneNumber(profileDB.getPhoneNumber());
		}

		if (profile.getTimezone() == null) {
			profile.setTimezone(profileDB.getTimezone());
		}

		profile.setEmail(profileDB.getEmail());

		if (profile.getAddress() == null) {
			profile.setAddress(profile.getAddress());
		} else if (profileDB.getAddress() != null) {
			Address address = profile.getAddress();
			Address addressDB = profileDB.getAddress();
			if (address.getStreet1() == null) {
				address.setStreet1(addressDB.getStreet1());
			}
			if (address.getStreet2() == null) {
				address.setStreet1(addressDB.getStreet2());
			}
			if (address.getCity() == null) {
				address.setCity(addressDB.getCity());
			}
			if (address.getState() == null) {
				address.setStreet1(addressDB.getState());
			}
			if (address.getZipCode() == null) {
				address.setStreet1(addressDB.getZipCode());
			}
			profile.setAddress(address);
		}
		
		return profile;

	}

}
