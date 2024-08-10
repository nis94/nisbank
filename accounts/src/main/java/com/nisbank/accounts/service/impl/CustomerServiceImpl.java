package com.nisbank.accounts.service.impl;

import com.nisbank.accounts.dto.AccountDto;
import com.nisbank.accounts.dto.CardsDto;
import com.nisbank.accounts.dto.CustomerDetailsDto;
import com.nisbank.accounts.dto.LoansDto;
import com.nisbank.accounts.entity.Account;
import com.nisbank.accounts.entity.Customer;
import com.nisbank.accounts.exception.ResourceNotFoundException;
import com.nisbank.accounts.mapper.AccountMapper;
import com.nisbank.accounts.mapper.CustomerMapper;
import com.nisbank.accounts.repository.AccountsRepository;
import com.nisbank.accounts.repository.CustomersRepository;
import com.nisbank.accounts.service.CardsFeignClient;
import com.nisbank.accounts.service.ICustomerService;
import com.nisbank.accounts.service.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomersRepository customersRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountDto(AccountMapper.mapToAccountsDto(account, new AccountDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
