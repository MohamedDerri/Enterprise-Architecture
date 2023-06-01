package miu.edu.Lab8PartB.Repository;

import miu.edu.Lab8PartB.domain.PersonJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonJPA, Integer> {

//    @Query("select distinct p from PersonJPA p join fetch p.pets")
//    List<PersonJPA> findAll();
}



