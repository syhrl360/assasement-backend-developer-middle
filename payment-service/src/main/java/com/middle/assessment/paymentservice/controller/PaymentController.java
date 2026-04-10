package com.middle.assessment.paymentservice.controller;


import com.middle.assessment.paymentservice.dto.PaymentRecord;
import com.middle.assessment.paymentservice.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentRecord")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Operation(summary = "Get payment record by user id", description = "Return payment record by specific user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "payment record not exists")
    })
    @GetMapping("/findByUserId/{userId}")
    public PaymentRecord findByUserId(@PathVariable Long userId) {
        return paymentService.findByUserId(userId);
    }


    @Operation(summary = "Get all payment record", description = "Return all payment record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "there is no payment record at all")
    })
    @GetMapping("/findAll")
    public List<PaymentRecord> findAll(){
        return paymentService.findAll();
    }

    @Operation(summary = "Insert payment record data", description = "Insert new payment record data to databases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "error when inserting payment record data")
    })
    @PostMapping("/insert")
    public void insert(@RequestBody PaymentRecord loanRecord) {
        paymentService.insert(loanRecord);
    }

    @Operation(summary = "Update payment record data", description = "Updating existing payment record data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "error when updating payment record data")
    })
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody PaymentRecord loanRecord) {
        loanRecord.setId(id);
        paymentService.update(loanRecord);
    }


    @Operation(summary = "Delete payment record by id", description = "Delete payment record data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "error when deleting payment record data")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentService.delete(id);
    }
}
