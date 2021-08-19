package com.page_assessment_wallet_system.controller;

import com.page_assessment_wallet_system.controller.dto.Response;
import com.page_assessment_wallet_system.controller.dto.TransferRequest;
import com.page_assessment_wallet_system.paystack.dto.*;
import com.page_assessment_wallet_system.paystack.service.PaystackService;
import com.page_assessment_wallet_system.util.GeneralService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/wallet")
public class WalletController {

    private final PaystackService paystackService;

    public WalletController(PaystackService paystackService) {
        this.paystackService = paystackService;
    }

    @PostMapping("fund")
    private ResponseEntity<?> fundTransfer(@RequestBody TransferRequest request) {
        PaymentResponse result = paystackService.initiatePayment(request);
        Response response = GeneralService.prepareResponse(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("verify/{reference}")
    private ResponseEntity<?> verifyFundTransfer(@PathVariable String reference) {
        PaymentEnquiryResponse result = paystackService.confirmPayment(reference);
        Response response = GeneralService.prepareResponse(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("accountBalance")
    private ResponseEntity<?> accountBalance() {
        BalanceEnquiryResponse result = paystackService.getAccountBalance();
        Response response = GeneralService.prepareResponse(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping("transactions")
    private ResponseEntity<?> transactionHistory() {
        TransactionHistoryResponse result = paystackService.getTransactionHistory();
        Response response = GeneralService.prepareResponse(result);
        return ResponseEntity.ok(response);
    }

}
