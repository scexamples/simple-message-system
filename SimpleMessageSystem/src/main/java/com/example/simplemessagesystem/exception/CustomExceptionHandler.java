package com.example.simplemessagesystem.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@ExceptionHandler(value = {InvalidMethodArgumentException.class})
	public ResponseEntity<ApiError> invalidCompanyIdRequest(InvalidMethodArgumentException ex, WebRequest request) {
		
		logger.error("Request: " + request.getDescription(false) + " raised " + ex);
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), "Invalid companyId supplied in request");
		return new ResponseEntity<>(apiError, null, apiError.getStatus());
	   
	 }
	
	@ExceptionHandler(value = {DataNotFoundException.class})
	public ResponseEntity<ApiError> noCompaniesFound(DataNotFoundException ex, WebRequest request) {
		
		logger.error("Request: " + request.getDescription(false) + " raised " + ex);
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "No companies found by message service");
		return new ResponseEntity<>(apiError, null, apiError.getStatus());
	   
	 }
	
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ApiError> handleAllExceptions(Exception ex, WebRequest request) {
        
    	logger.error("Request: " + request.getDescription(false) + " raised " + ex);        
        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "An error occurred while processing the request");
        return new ResponseEntity<>(apiError, null, apiError.getStatus());
    }
}
