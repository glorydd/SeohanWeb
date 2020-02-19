package com.seohan.common.Error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seohan.common.Domain.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by vivie on 2017-06-16.
 */

@Slf4j
@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e){
    	Result result = new Result().fail();
        final String errorMsg = e.getMessage();
        if(errorMsg == null){
            result.setMessage(Result.SERVER_ERROR_MESSAGE);
        }else{
            result.setMessage(errorMsg);
        }
        
        this.handleUnauth(e, result);

        this.handleLog(e);

        return result;
    }
    
    private void handleUnauth(Exception e, Result result){
        if(e instanceof  UnauthorizedException){
        	HttpStatus unauth = HttpStatus.UNAUTHORIZED;
        	result.setStatusCode(unauth);
        }
    }
    
    private void handleLog(Exception e){
    	if(log.isInfoEnabled()){
            e.printStackTrace();
        }else{
            log.error("Error ::: {}", e.getMessage());
        }
    }
}
