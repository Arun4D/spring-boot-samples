package in.co.ad.springboot3.features.springboot3features.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.co.ad.springboot3.features.springboot3features.domain.CustomerDo;
import in.co.ad.springboot3.features.springboot3features.dto.CustomerDto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final ModelMapper modelMapper;

     public CustomerDo toCustomerDo(String input) {
        ObjectMapper mapper = new ObjectMapper();
        CustomerDto customerDto = null;
        try {
            customerDto = mapper.readValue(input, CustomerDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return modelMapper.map(customerDto, CustomerDo.class);
    }

    public CustomerDo toCustomerDo(CustomerDto customerDto) {
        return modelMapper.map(customerDto, CustomerDo.class);
    }

    public CustomerDto toCustomerDto(CustomerDo customerDo) {
        return modelMapper.map(customerDo, CustomerDto.class);
    }
}
