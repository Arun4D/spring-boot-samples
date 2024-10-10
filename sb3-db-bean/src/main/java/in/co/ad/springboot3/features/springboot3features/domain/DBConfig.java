package in.co.ad.springboot3.features.springboot3features.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "dbConfig")
@Table(name = "config")
@Getter
@Setter
@NoArgsConstructor
public class DBConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "c_key")
    private String key;
    @Column(name = "c_value")
    private String value;
}
