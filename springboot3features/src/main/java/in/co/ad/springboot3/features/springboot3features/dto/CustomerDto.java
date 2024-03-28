package in.co.ad.springboot3.features.springboot3features.dto;

import java.util.Set;

import lombok.Data;

@Data
public class CustomerDto {

    private int id;
    private String firstName;
    private String lastName;
    private String dob;
    private Set<String> phoneNumber;
}
