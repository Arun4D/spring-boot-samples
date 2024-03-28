package in.co.ad.springboot3.features.springboot3features.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.co.ad.springboot3.features.springboot3features.domain.Estudiante;

@Repository
public interface StudentRepository extends CrudRepository<Estudiante, Integer>{

}
