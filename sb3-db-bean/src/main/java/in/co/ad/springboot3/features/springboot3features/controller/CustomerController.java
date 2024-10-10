package in.co.ad.springboot3.features.springboot3features.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.ad.springboot3.features.springboot3features.domain.CustomerDo;
import in.co.ad.springboot3.features.springboot3features.domain.DirectExchange;
import in.co.ad.springboot3.features.springboot3features.dto.CustomerDto;
import in.co.ad.springboot3.features.springboot3features.mapper.CustomerMapper;
import in.co.ad.springboot3.features.springboot3features.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final DirectExchange directExchange;

    public CustomerController (CustomerRepository customerRepository, CustomerMapper customerMapper,@Lazy DirectExchange directExchange) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.directExchange = directExchange;
    }

    @PostMapping("/")
    public ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto) {

        CustomerDo customerDo = customerRepository.save(customerMapper.toCustomerDo(customerDto));
        customerDto = customerMapper.toCustomerDto(customerDo);

        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") int id) {

        Optional<CustomerDo> customerDoOpt = customerRepository.findById(id);
        CustomerDto customerDto = null;
        if (customerDoOpt.isPresent())
            customerDto = customerMapper.toCustomerDto(customerDoOpt.get());
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CustomerDto>> getallCustomer() {
        Iterable<CustomerDo> all = customerRepository.findAll();

        List<CustomerDo> customerList= StreamSupport.stream(all.spliterator(), false).collect(Collectors.toList());

        List<CustomerDto> customerDtoList= customerList.stream().map(customerMapper::toCustomerDto).collect(Collectors.toList());
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @GetMapping("/dbconfig")
    public ResponseEntity<DirectExchange> getCustomerById() {

        return new ResponseEntity<>(directExchange, HttpStatus.OK);
    }
   
}
