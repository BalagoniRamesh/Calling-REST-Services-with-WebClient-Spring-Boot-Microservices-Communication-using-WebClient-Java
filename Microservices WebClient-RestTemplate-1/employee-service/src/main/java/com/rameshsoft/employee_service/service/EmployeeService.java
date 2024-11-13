package com.rameshsoft.employee_service.service;

import com.rameshsoft.employee_service.entity.Employee;
import com.rameshsoft.employee_service.repository.EmployeeRepository;
import com.rameshsoft.employee_service.response.AddressResponse;
import com.rameshsoft.employee_service.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private WebClient webClient;

    public EmployeeResponse getEmployeeId(int id){

       Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);


//       //Using WebClient
        AddressResponse addressResponse =
                webClient
                        .get()
                        .uri("/address/" + id)
                        .retrieve()
                        .bodyToMono(AddressResponse.class)
                        .block();
        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;

//        //RestTemplate - Fist way
//        RestTemplate restTemplate = new RestTemplate();
//
//        String endPoint = "http://localhost:8081/address-service/address/{id}";
//        AddressResponse addressResponse = restTemplate.getForObject(endPoint, AddressResponse.class, id);
//        employeeResponse.setAddressResponse(addressResponse);
//        return employeeResponse;

     /*   //RestTemplate-Second way
        RestTemplate restTemplate = new RestTemplate();

        String endPoint = "http://localhost:8081/address-service/address/{id}";
        // Use getForEntity to get the full ResponseEntity with both body and status code
        ResponseEntity<AddressResponse> responseEntity = restTemplate.getForEntity(endPoint, AddressResponse.class, id);

        // Check if the status code is 200 (OK)
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            AddressResponse addressResponse = responseEntity.getBody();
            employeeResponse.setAddressResponse(addressResponse);
            return employeeResponse;
        }
        else {
            // Handle the error (e.g., log, throw exception, or return default response)
            // For example, set a default or empty AddressResponse
            AddressResponse emptyAddress = new AddressResponse(); // Create a default empty AddressResponse
            employeeResponse.setAddressResponse(emptyAddress);
            return employeeResponse;
        }*/
    }
}
