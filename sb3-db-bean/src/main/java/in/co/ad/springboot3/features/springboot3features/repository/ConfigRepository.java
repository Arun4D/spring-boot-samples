package in.co.ad.springboot3.features.springboot3features.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.co.ad.springboot3.features.springboot3features.domain.DBConfig;

@Repository
public interface ConfigRepository extends CrudRepository<DBConfig, Integer>{
    List<DBConfig> findByKeyAndValue(String key, String value);

}
