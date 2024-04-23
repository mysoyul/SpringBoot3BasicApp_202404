package com.basic.myspringboot.exception.advice;

import com.basic.myspringboot.exception.BusinessException;
import com.basic.myspringboot.exception.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class DefaultExceptionAdvice {
	private final Logger LOGGER = LoggerFactory.getLogger(DefaultExceptionAdvice.class);


    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleException(BusinessException e) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", "[안내] " + e.getMessage());
        result.put("httpStatus", e.getHttpStatus().value());

        return new ResponseEntity<>(result, e.getHttpStatus());
    }
    
    @ExceptionHandler(SystemException.class)
    protected ResponseEntity<Object> handleException(SystemException e) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", "[시스템 오류] " + e.getMessage());
        result.put("httpStatus", e.getHttpStatus().value());

        return new ResponseEntity<>(result, e.getHttpStatus());
    }

    //숫자타입의 값에 문자열타입의 값을 입력으로 받았을때 발생하는 오류
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleException(HttpMessageNotReadableException e) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("message", e.getMessage());
        result.put("httpStatus", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {
        Map<String, Object> result = new HashMap<String, Object>();
        ResponseEntity<Object> ret = null;
        
        if (e instanceof BusinessException) {
        	BusinessException b = (BusinessException) e;
        	result.put("message", "[안내]\n" + e.getMessage());
        	result.put("httpStatus", b.getHttpStatus().value());
        } else if ( e instanceof SystemException) {
    		SystemException s = (SystemException)e;
            result.put("message", "[시스템 오류]\n" + s.getMessage());
            result.put("httpStatus", s.getHttpStatus().value());
            ret = new ResponseEntity<>(result, s.getHttpStatus());
            
            LOGGER.error(s.getMessage(), s);
    	 } else {
    		String msg = "예상치 못한 문제가 발생했습니다.\n관리자에게 연락 하시기 바랍니다.";
	        result.put("message", msg);
	        result.put("httpStatus", HttpStatus.INTERNAL_SERVER_ERROR.value());
	        ret = new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	        e.printStackTrace();
	        
            LOGGER.error(e.getMessage(), e);
    	}
        return ret;
    }
}