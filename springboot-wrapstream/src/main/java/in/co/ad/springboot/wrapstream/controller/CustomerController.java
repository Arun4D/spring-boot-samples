package in.co.ad.springboot.wrapstream.controller;

import in.co.ad.springboot.wrapstream.domain.CustomerDo;
import in.co.ad.springboot.wrapstream.dto.CustomerDto;
import in.co.ad.springboot.wrapstream.mapper.CustomerMapper;
import in.co.ad.springboot.wrapstream.repository.CustomerRepository;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;


    @PostMapping("/")
    public ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto) {

        sendMessage(customerMapper.toCustomerDtoString(customerDto));
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathParam("id") int id) {

        Optional<CustomerDo> customerDoOpt = customerRepository.findById(id);
        CustomerDto customerDto = null;
        if (customerDoOpt.isPresent())
            customerDto = customerMapper.toCustomerDto(customerDoOpt.get());
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @GetMapping("/latestId")
    public ResponseEntity<CustomerDto> getCustomerLastedId() {

        CustomerDo customerDo = customerRepository.findTopByOrderByIdDesc();
        CustomerDto customerDto = customerMapper.toCustomerDto(customerDo);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    public void sendMessage(String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("customer-topic", message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}
