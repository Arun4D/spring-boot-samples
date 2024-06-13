package in.co.ad.springboot.wrapstream.repository;

import java.util.Optional;

import in.co.ad.springboot.wrapstream.domain.CustomerDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerDo, Integer>{
    CustomerDo findTopByOrderByIdDesc();

}
