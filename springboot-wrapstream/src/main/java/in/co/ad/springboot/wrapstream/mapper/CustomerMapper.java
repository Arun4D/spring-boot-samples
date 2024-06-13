package in.co.ad.springboot.wrapstream.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import in.co.ad.springboot.wrapstream.domain.CustomerDo;
import in.co.ad.springboot.wrapstream.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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

    @SneakyThrows
    public String toCustomerDtoString(CustomerDto input) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(input);
    }
}
