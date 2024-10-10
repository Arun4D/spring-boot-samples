package in.co.ad.springboot3.features.springboot3features.domain;

import org.apache.commons.lang3.builder.Diffable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "t_spot_condition_scd")
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public abstract class SpotCondition implements Diffable<SpotCondition> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scd_id")
    @ToString.Include
    protected Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scd_spt_id")
    protected Spot spot;

}