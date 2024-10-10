package in.co.ad.springboot3.features.springboot3features.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.co.ad.springboot3.features.springboot3features.domain.CustomerDo;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerDo, Integer>{
    Optional<Integer> findTopByOrderByIdDesc();

}
