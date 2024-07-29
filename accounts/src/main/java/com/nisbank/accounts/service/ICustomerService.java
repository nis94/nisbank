package com.nisbank.accounts.service;

import com.nisbank.accounts.dto.CustomerDetailsDto;
import com.nisbank.accounts.dto.CustomerDto;

public interface ICustomerService {

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
