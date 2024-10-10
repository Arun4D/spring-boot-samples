package in.co.ad.springboot3.features.springboot3features.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.co.ad.springboot3.features.springboot3features.domain.CustomerDo;
import in.co.ad.springboot3.features.springboot3features.domain.Spot;
import in.co.ad.springboot3.features.springboot3features.dto.CustomerDto;
import in.co.ad.springboot3.features.springboot3features.dto.SpotDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpotMapper {

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

    public Spot toSpotDo(SpotDTO spotDTO) {
        return modelMapper.map(spotDTO, Spot.class);
    }

    public CustomerDto toSpotDTO(Spot spot) {
        return modelMapper.map(customerDo, CustomerDto.class);
    }
}
