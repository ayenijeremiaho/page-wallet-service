package com.page_assessment_wallet_system.controller;

import com.page_assessment_wallet_system.controller.dto.Response;
import com.page_assessment_wallet_system.exception.WalletException;
import com.page_assessment_wallet_system.util.GeneralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({WalletException.class})
    public final ResponseEntity<?> handleException(Exception ex) {
        logger.info("Error occurred, error message is {}", ex.getMessage());

        if (ex instanceof WalletException) {
            Response response = GeneralService.prepareResponse("96", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else {
            Response response = GeneralService.prepareResponse("99", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
