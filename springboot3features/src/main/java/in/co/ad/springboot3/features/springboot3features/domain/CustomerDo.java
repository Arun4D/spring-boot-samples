package in.co.ad.springboot3.features.springboot3features.domain;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Formula;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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

    @ElementCollection(targetClass = String.class)
    @Column(name = "phoneNumber")
    @CollectionTable(name = "phonenumbers", joinColumns = {
    @JoinColumn(name = "id", updatable = false, insertable = false) })
    //@Formula("Select phoneNumber from phonenumbers p where p.id = id")
    private Set<String> phoneNumber;

    @PrePersist
    @PreUpdate
    void removePhoneNumberList() {
        this.phoneNumber = new HashSet<>(); 
    }
}
