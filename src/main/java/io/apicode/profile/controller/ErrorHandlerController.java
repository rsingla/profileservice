package io.apicode.profile.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.common.collect.Lists;

import io.apicode.profile.model.APIException;
import io.apicode.profile.model.ErrorDetails;

@ControllerAdvice
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Profile Service Error";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(value = { APIException.class })
	protected ResponseEntity<Object> handleAPIConflict(APIException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), null);

		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		BindingResult bindingResult = ex.getBindingResult();
		List<String> messages = Lists.newArrayList();
		if (bindingResult != null && bindingResult.getErrorCount() > 0) {
			for (ObjectError error : bindingResult.getAllErrors()) {
				messages.add(error.getDefaultMessage());
			}
		}
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed", messages);
		return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	}

}