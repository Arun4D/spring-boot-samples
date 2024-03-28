package in.co.ad.springboot3.features.springboot3features.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CountryDTO {

    @JsonIgnore
    private Long id;

    private String countryName;

    @JsonCreator
    CountryDTO(
            @JsonProperty("id") final Long id,
            @JsonProperty("countryName") final String countryName) {
        this.id = id;
        this.countryName = countryName;
    }
}