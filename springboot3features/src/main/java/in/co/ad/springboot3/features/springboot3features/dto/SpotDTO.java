package in.co.ad.springboot3.features.springboot3features.dto;

import java.util.List;

import in.co.ad.springboot3.features.springboot3features.domain.SpotCondition;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SpotDTO {

    private Integer id;

    private SpotCondition currentCondition;

    private List<SpotCondition> conditions;

    private Integer bladeId;
}