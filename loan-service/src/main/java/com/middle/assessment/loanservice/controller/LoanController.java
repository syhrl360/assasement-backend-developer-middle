package com.middle.assessment.loanservice.controller;

import com.middle.assessment.loanservice.dto.LoanRecord;
import com.middle.assessment.loanservice.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loanRecord")
public class LoanController {



    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }


    @Operation(summary = "Get loan record by user id", description = "Return loan record by specific user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "if loan record not exists")
    })
    @GetMapping("/{id}")
    public LoanRecord findByUserId(@PathVariable Long userId) {
        return loanService.findByUserId(userId);
    }


    @Operation(summary = "Get all loan record", description = "Return all loan record")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "there is no loan record at all")
    })
    @GetMapping("/findAll")
    public List<LoanRecord> findAll(){
        return loanService.findAll();
    }

    @Operation(summary = "Insert loan record data", description = "Insert new loan record data to databases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "error when inserting loan record data")
    })
    @PostMapping("/insert")
    public void insert(@RequestBody LoanRecord loanRecord) {
        loanService.insert(loanRecord);
    }

    @Operation(summary = "Update loan record data", description = "Updating existing loan record data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "error when updating loan record data")
    })
    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody LoanRecord loanRecord) {
        loanRecord.setId(id);
        loanService.update(loanRecord);
    }


    @Operation(summary = "Delete loan record by id", description = "Delete loan record data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "error when deleting loan record data")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        loanService.delete(id);
    }
















}
