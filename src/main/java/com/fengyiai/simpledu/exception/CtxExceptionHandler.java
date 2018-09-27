package com.fengyiai.simpledu.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// 全局异常捕捉，不能捕捉拦截器的异常
@ControllerAdvice
public class CtxExceptionHandler extends ResponseEntityExceptionHandler {

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

//    @ExceptionHandler
//    public ModelAndView handle(HttpServletRequest req, HttpServletResponse res, Object handler, Exception ex) {
//        ex.printStackTrace();
//
//        System.out.println("--------------------");
//
//        res.reset();
//        res.setCharacterEncoding("UTF-8");
//        res.setContentType("text/json");
//
//        ModelAndView model = new ModelAndView(new MappingJackson2JsonView());
//
//        if (ex instanceof CtxException) {
//            res.setStatus(HttpServletResponse.SC_OK);
//            model.addObject("code", ((CtxException) ex).getCode());
//            model.addObject("msg", ex.getMessage());
//        } else {
//            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//            model.addObject("code", 500);
//            model.addObject("msg", ex.getMessage());
//        }
//        return model;
//    }

}
