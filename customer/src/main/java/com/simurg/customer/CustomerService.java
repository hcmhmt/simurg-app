package com.simurg.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService{

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    @Override
    public void registerCustomer(CustomerRegistrationRequest request) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .build();

        // todo: check if email
        // todo: check if email not taken
        // todo: check if fraudster

        customerRepository.saveAndFlush(customerEntity);
        Boolean fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                Boolean.class,
                customerEntity.getCustomerId()
        );

        if (fraudCheckResponse){
            throw new IllegalStateException("fraudster");
        }

        // todo: send ntf

    }
}
