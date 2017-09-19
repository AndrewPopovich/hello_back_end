package ua.andrew.hellobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;

@EnableWebMvc
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ContactsNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity requestHandlingNoHandlerFound(ContactsNotFoundException ex) {
        Map<String, String> responseBody = new HashMap<>();

        responseBody.put("timestamp", "" + ex.getTimeStamp());
        responseBody.put("status", "404");
        responseBody.put("error", "Not found");
        responseBody.put("exception", ContactsNotFoundException.class.getName());
        responseBody.put("message", ex.getMessage());
        return new ResponseEntity<Object>(responseBody, HttpStatus.NOT_FOUND);
    }
}