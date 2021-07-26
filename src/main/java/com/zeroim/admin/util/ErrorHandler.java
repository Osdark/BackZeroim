package com.zeroim.admin.util;

import com.zeroim.admin.requests.util.ResError;
import com.zeroim.admin.requests.util.Response;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Response<String>> handleException(Exception e) {
        Response<String> response = new Response<>();
        response.setData(e.getClass().toString());
        response.setError(new ResError(404, e.getMessage()));
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(value = HttpClientErrorException.NotFound.class)
    public ResponseEntity<Response<String>> handleNotFound(HttpClientErrorException.NotFound n) {
        Response<String> response = new Response<>();
        response.setData(n.getClass().toString());
        response.setError(new ResError(404, n.getMessage()));
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Response<String>> handleIllegalArgumentException(IllegalArgumentException iae) {
        Response<String> response = new Response<>();
        response.setData(iae.getClass().toString());
        response.setError(new ResError(404, iae.getMessage()));
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Response<String>> handleNullPointerException(NullPointerException npe) {
        Response<String> response = new Response<>();
        response.setData(npe.getClass().toString());
        response.setError(new ResError(404, "not found"));
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<Response<String>> handleNullPointerException(ExpiredJwtException eje) {
        Response<String> response = new Response<>();
        response.setData("Session expired");
        response.setError(new ResError(500, "Session expired"));
        return ResponseEntity.status(500).body(response);
    }
}
