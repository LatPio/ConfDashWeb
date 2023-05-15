package com.flystonedev.cutomer.service;


import com.flystonedev.cutomer.model.Customer;
import com.flystonedev.cutomer.records.CustomerRegistrationRequest;
import com.flystonedev.cutomer.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

//    public CustomerService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

//    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);


    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        //todo: validation email is vaild
        //todo: validation email not taken

        customerRepository.save(customer);
    }

//    public List<CustomerResponse> listOfAllCustomers(){
//        List<Customer> customersList = customerRepository.findAll();
//        return customersList.stream().map(customerMapper::map).collect(Collectors.toList());
//    }
//    public CustomerResponse get(Integer id){
//        return customerRepository.findById(id).map(customer -> customerMapper.map(customer)).orElse(null);
//    }
//    public CustomerResponse update(CustomerResponse customerResponse){
//        CustomerResponse exist = get(customerResponse.id());
//        if (exist == null) {
//            return null;
//        }
//        Customer updated = customerRepository.save(customerMapper.map(customerResponse));
//        return customerMapper.map(updated);
//    }
    public void delete(Integer id){
        customerRepository.deleteById(id);
    }

}
