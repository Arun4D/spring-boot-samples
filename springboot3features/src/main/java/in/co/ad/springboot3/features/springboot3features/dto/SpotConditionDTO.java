package in.co.ad.springboot3.features.springboot3features.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SpotConditionDTO {

    protected Integer id;

    protected SpotDTO spot;
}