package in.co.ad.springboot.ai.sample;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record ClaudeResponse(String completion,
                             String stopReason,
                             String model) {}