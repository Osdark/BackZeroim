package com.zeroim.admin.adapters.util;

import com.zeroim.admin.requests.util.ResError;
import com.zeroim.admin.requests.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ControllerUtils {
    <T> ResponseEntity<Response<T>> getResponseEntityOk(Response<T> response, ResError error);

    <T> ResponseEntity<Response<T>> getBadResponseEntity(Response<T> response, ResError error,
                                                         HttpStatus status, String message);

    <T> ResponseEntity<Response<T>> getResponseEntity(Response<T> response, ResError error, HttpStatus status);
}
