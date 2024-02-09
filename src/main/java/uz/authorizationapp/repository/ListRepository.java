package uz.authorizationapp.repository;

import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.authorizationapp.entity.Lists;

@Repository
public interface ListRepository extends JpaRepository<Lists,Long> {
}
