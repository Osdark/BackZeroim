package com.zeroim.admin.util;

import com.zeroim.admin.requests.util.ResError;
import com.zeroim.admin.requests.util.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response<String>> handleException(Exception e) {
        Response<String> response = new Response<>();
        response.setData(e.getClass().toString());
        response.setError(new ResError(404, e.getMessage()));
        return ResponseEntity.badRequest().body(response);
    }
}
