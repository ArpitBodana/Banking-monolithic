package com.banking.user_service.Controllers;



import com.banking.user_service.Models.DepositOrDebit;
import com.banking.user_service.Models.Transactions;
import com.banking.user_service.Models.TransferDetails;
import com.banking.user_service.Services.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@Tag(name="Payment APIs",description = "CRUD Operations for Payment details")
public class PaymentController {
    @Autowired
    PaymentService paymentService;


    @PutMapping("/depositToAccount")
    public ResponseEntity<String> depositMoney(@RequestBody DepositOrDebit deposit){
        return new ResponseEntity<>(paymentService.depositToAccount(deposit), HttpStatus.OK);
    }

    @PutMapping("/debitFromAccount")
    public ResponseEntity<String> debitMoney(@RequestBody DepositOrDebit debit){
        return  new ResponseEntity<>(paymentService.debitFromAccount(debit),HttpStatus.OK);
    }

    @PutMapping("/transferToAccount")
    public  ResponseEntity<String> transferMoney(@RequestBody TransferDetails transferDetails){
        return  new ResponseEntity<>(paymentService.transferToAnotherAccount(transferDetails),HttpStatus.OK);
    }

    @GetMapping("/getTransactions")
    public ResponseEntity<List<Transactions>> getAllTransactions(){
        return new ResponseEntity<>(paymentService.getAllTransactions(),HttpStatus.OK);
    }

    @GetMapping("/getMyTransactions")
    public ResponseEntity<List<Transactions>> getMyTransactions(@RequestParam Long id){
        return new ResponseEntity<>(paymentService.getMyTransactions(id),HttpStatus.OK);
    }


}
