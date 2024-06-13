package in.co.ad.springboot.wrapstream.service;

import in.co.ad.springboot.wrapstream.domain.CustomerDo;
import in.co.ad.springboot.wrapstream.mapper.CustomerMapper;
import in.co.ad.springboot.wrapstream.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;



    @KafkaListener(topics = "customer-topic", groupId = "ad-customer-grp")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group ad-customer-grp: " + message);
        customerRepository.save(customerMapper.toCustomerDo(message));

    }
}
