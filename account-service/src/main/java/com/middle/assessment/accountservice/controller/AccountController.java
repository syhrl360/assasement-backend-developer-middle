package com.middle.assessment.accountservice.controller;

import com.middle.assessment.accountservice.dto.UserAccount;
import com.middle.assessment.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/userAccount")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Get user account by user id", description = "Return user account by specific user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "user account not exists")
    })
    @GetMapping("/findByUserId/{id}")
    public UserAccount findByUserId(@PathVariable Long userId) {
        return accountService.findByUserId(userId);
    }


    @Operation(summary = "Get all user account", description = "Return all user account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "there is no user account at all")
    })
    @GetMapping("/findAll")
    public List<UserAccount> findAll(){
        return accountService.findAll();
    }

    @Operation(summary = "Insert user account data", description = "Insert new user account data to databases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "error when inserting user account data")
    })
    @PostMapping("/insert")
    public void insert(@RequestBody UserAccount userAccount) {
        accountService.insert(userAccount);
    }

    @Operation(summary = "Update user account data", description = "Updating existing user account data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "error when updating user account data")
    })
    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestBody UserAccount userAccount) {
        userAccount.setId(id);
        accountService.update(userAccount);
    }


    @Operation(summary = "Delete user account by id", description = "Delete user account data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "400", description = "error when deleting user account data")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}
