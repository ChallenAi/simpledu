package com.fengyiai.simpledu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CtxExceptionHandler {

    @ExceptionHandler(CtxException.class)
    public ResponseEntity<Map<String, Object>> handle(CtxException ex){
        System.out.println(ex.getMessage());
        Map<String, Object> resp = new HashMap<>();
        resp.put("code", ex.getCode());
        resp.put("msg", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handle(RuntimeException ex){
        System.out.println(ex.getMessage());
        Map<String, Object> resp = new HashMap<>();
        resp.put("code", 500);
        resp.put("msg", ex.getMessage());
        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, Object>> handle(MissingServletRequestParameterException ex){
        System.out.println(ex.getParameterName());
        Map<String, Object> resp = new HashMap<>();
        resp.put("code", 400);
        resp.put("msg", ex.getParameterName() + " not found");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

//    @ExceptionHandler(Throwable.class)
//    public ResponseEntity<Map<String, Object>> handle(NoHandlerFoundException ex){
//        System.out.println("----------");
//        Map<String, Object> resp = new HashMap<>();
//        resp.put("code", 500);
//        resp.put("msg", ex.getMessage());
//        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<Map<String, Object>> handle(NoHandlerFoundException ex){
//        Map<String, Object> resp = new HashMap<>();
//        resp.put("code", 404);
//        resp.put("msg", ex.getMessage());
//        return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
