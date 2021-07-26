package com.zeroim.admin.adapters.util;

import com.zeroim.admin.requests.util.ResError;
import com.zeroim.admin.requests.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DefaultControllerUtils implements ControllerUtils {
    @Override
    public <T> ResponseEntity<Response<T>> getResponseEntityOk(Response<T> response, ResError error) {
        response.setError(error);
        return ResponseEntity.ok().body(response);
    }

    @Override
    public <T> ResponseEntity<Response<T>> getBadResponseEntity(Response<T> response, ResError error,
                                                                 HttpStatus status, String message) {
        error.setErrorCode(status.value());
        error.setMessage(message);
        response.setError(error);
        return ResponseEntity.status(status).build();
    }

    @Override
    public <T> ResponseEntity<Response<T>> getResponseEntity(Response<T> response, ResError error, HttpStatus status) {
        response.setError(error);
        return ResponseEntity.status(status).body(response);
    }
}
