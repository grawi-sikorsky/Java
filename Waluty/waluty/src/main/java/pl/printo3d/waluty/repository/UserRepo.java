package pl.printo3d.waluty.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<UserEntity, Long>
{
  List<UserEntity> findByUsername(String uname);
}
