package io.apicode.profile.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorDetails {

	Date errortime;
	String subject;
	List<String> messages;

	public ErrorDetails(Date errortime, String subject, List<String> errorMessages) {
		super();
		this.errortime = errortime;
		this.subject = subject;
		this.messages = errorMessages;
	}

	public Date getErrortime() {
		return errortime;
	}

	public void setErrortime(Date errortime) {
		this.errortime = errortime;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
