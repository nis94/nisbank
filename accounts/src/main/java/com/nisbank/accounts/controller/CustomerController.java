package com.nisbank.accounts.controller;

import com.nisbank.accounts.dto.CustomerDetailsDto;
import com.nisbank.accounts.dto.ErrorResponseDto;
import com.nisbank.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.nisbank.accounts.constants.AccountsConstants.*;

/**
 * @author Nir Bar Levav
 */

@Tag(
        name = "Customers in NisBank",
        description = "CRUD REST APIs in NisBank to FETCH customer details"
)
@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final ICustomerService iCustomersService;

    public CustomerController(ICustomerService iCustomersService) {
        this.iCustomersService = iCustomersService;
    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch Customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = STATUS_200,
                    description = MESSAGE_200
            ),
            @ApiResponse(
                    responseCode = STATUS_500,
                    description = MESSAGE_500,
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
            @RequestHeader(name = "X-Correlation-ID")
            String correlationId,
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {

        logger.debug("Correlation ID: {}", correlationId);
        CustomerDetailsDto customerDetailsDto = iCustomersService.fetchCustomerDetails(mobileNumber, correlationId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDetailsDto);
    }

}
