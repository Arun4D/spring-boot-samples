package in.co.ad.springboot3.features.springboot3features.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "t_spot_spt")
@Data
@NoArgsConstructor
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spt_id")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "spt_current_condition")
    private SpotCondition currentCondition;

    @OneToMany(mappedBy = "spot", fetch = FetchType.LAZY)
    private List<SpotCondition> conditions;

    @Column(name = "spt_bla_id")
    private Integer bladeId;
}