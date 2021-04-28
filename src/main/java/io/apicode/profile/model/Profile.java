package io.apicode.profile.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Id", "first_name", "last_name", "date_of_birth", "phone_number", "timezone", "email", "address" })
public class Profile implements Serializable {

	@JsonProperty("Id")
	private String id;
	
	@JsonProperty("first_name")
	@NotNull(message = "First Name cannot be null")
	private String firstName;
	
	@JsonProperty("last_name")
	@NotNull(message = "Last Name cannot be null")
	private String lastName;
	
	@JsonProperty("date_of_birth")
	private String dateOfBirth;
	
	@JsonProperty("phone_number")
	private String phoneNumber;
	
	@JsonProperty("timezone")
	private String timezone;
	
	@JsonProperty("email")
	@NotNull(message = "Email cannot be null")
	@Email(message="Email Format is incorrect")
	private String email;
	
	@JsonProperty("address")
	@Valid
	private Address address;
	
	@JsonProperty("time_updated")
	private String timeUpdated;
	
	@JsonIgnore
	@Valid
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	private final static long serialVersionUID = -167823424587789469L;

	public Profile() {
	}

	@JsonProperty("Id")
	public String getId() {
		return id;
	}

	@JsonProperty("Id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("first_name")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("last_name")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("date_of_birth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("date_of_birth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("phone_number")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	@JsonProperty("phone_number")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty("timezone")
	public String getTimezone() {
		return timezone;
	}

	@JsonProperty("timezone")
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("address")
	public Address getAddress() {
		return address;
	}

	@JsonProperty("address")
	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	

	@JsonProperty("time_updated")
	public String getTimeUpdated() {
		return timeUpdated;
	}

	@JsonProperty("time_updated")
	public void setTimeUpdated(String timeUpdated) {
		this.timeUpdated = timeUpdated;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(Profile.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this)))
				.append('[');
		sb.append("id");
		sb.append('=');
		sb.append(((this.id == null) ? "<null>" : this.id));
		sb.append(',');
		sb.append("firstName");
		sb.append('=');
		sb.append(((this.firstName == null) ? "<null>" : this.firstName));
		sb.append(',');
		sb.append("lastName");
		sb.append('=');
		sb.append(((this.lastName == null) ? "<null>" : this.lastName));
		sb.append(',');
		sb.append("dateOfBirth");
		sb.append('=');
		sb.append(((this.dateOfBirth == null) ? "<null>" : this.dateOfBirth));
		sb.append(',');
		sb.append("phoneNumber");
		sb.append('=');
		sb.append(((this.phoneNumber == null) ? "<null>" : this.phoneNumber));
		sb.append(',');
		sb.append("timezone");
		sb.append('=');
		sb.append(((this.timezone == null) ? "<null>" : this.timezone));
		sb.append(',');
		sb.append("email");
		sb.append('=');
		sb.append(((this.email == null) ? "<null>" : this.email));
		sb.append(',');
		sb.append("address");
		sb.append('=');
		sb.append(((this.address == null) ? "<null>" : this.address));
		sb.append(',');
		sb.append("additionalProperties");
		sb.append('=');
		sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
		sb.append(',');
		if (sb.charAt((sb.length() - 1)) == ',') {
			sb.setCharAt((sb.length() - 1), ']');
		} else {
			sb.append(']');
		}
		return sb.toString();
	}
}