package in.co.ad.springboot3.features.springboot3features.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.ad.springboot3.features.springboot3features.domain.Spot;

public interface SpotRepository extends JpaRepository<Spot, Integer> {

    List<Spot> findByBladeId( Integer bladeId );

}
