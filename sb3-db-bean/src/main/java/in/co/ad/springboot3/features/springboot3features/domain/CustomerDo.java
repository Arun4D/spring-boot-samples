package in.co.ad.springboot3.features.springboot3features.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "customer")
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
public class CustomerDo {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String dob;

}
