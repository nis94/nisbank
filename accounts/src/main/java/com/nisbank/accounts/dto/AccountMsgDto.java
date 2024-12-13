package com.nisbank.accounts.dto;

public record AccountMsgDto(
   Long accountNumber, String name, String email, String mobileNumber
) {}
