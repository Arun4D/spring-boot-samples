package in.co.ad.springboot3.features.springboot3features.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.co.ad.springboot3.features.springboot3features.domain.CustomerDo;
import in.co.ad.springboot3.features.springboot3features.dto.CustomerDto;
import in.co.ad.springboot3.features.springboot3features.dto.LatestCustomerId;
import in.co.ad.springboot3.features.springboot3features.event.CustomerEventHandler;
import in.co.ad.springboot3.features.springboot3features.mapper.CustomerMapper;
import in.co.ad.springboot3.features.springboot3features.repository.CustomerRepository;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomerEventHandler customerEventHandler;

    @Autowired
    private LatestCustomerId latestCustomerId;

    @PostMapping("/")
    public ResponseEntity<CustomerDto> createCustomer(CustomerDto customerDto) {

        CustomerDo customerDo = customerRepository.save(customerMapper.toCustomerDo(customerDto));
        customerDto = customerMapper.toCustomerDto(customerDo);

        customerEventHandler.handleAuthorAfterSave(customerDo);

        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    @PostMapping(value ="/upload" , consumes = { MediaType.MULTIPART_FORM_DATA_VALUE  })

    public ResponseEntity<CustomerDto> createCustomerWithImages(@RequestPart("input") String input,
    @RequestPart("image") MultipartFile image) {

        CustomerDo customerDo = customerRepository.save(customerMapper.toCustomerDo(input ));
        //transferTo(image);
        saveImage(image);
        CustomerDto customerDto = customerMapper.toCustomerDto(customerDo);

        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    private void transferTo(MultipartFile image) {
        File file = new File("/home/arun/tmp/targetFile.tmp");

        try {
            image.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }

    private void saveImage(MultipartFile image) {
        try (InputStream initialStream = image.getInputStream()) {
            byte[] buffer = new byte[initialStream.available()];
            initialStream.read(buffer);

            File targetFile = new File("/home/arun/tmp/targetFile.tmp");

            try (OutputStream outStream = new FileOutputStream(targetFile)) {
                outStream.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public ResponseEntity<Integer> getCustomerLastedId() {

        Optional<Integer> maxId = customerRepository.findTopByOrderByIdDesc();

        Integer id = 0;
        if (maxId.isPresent())
            id = maxId.get();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/beanLatestId")
    public ResponseEntity<Integer> getCustomerLatestBeanId() {

        Integer id = -2;
        if (latestCustomerId != null)
            id = latestCustomerId.getId();
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
