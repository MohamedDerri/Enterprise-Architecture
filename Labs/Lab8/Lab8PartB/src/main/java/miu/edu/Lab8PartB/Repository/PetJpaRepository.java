package miu.edu.Lab8PartB.Repository;

import miu.edu.Lab8PartB.domain.PetJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetJpaRepository extends JpaRepository<PetJPA, Integer> {
}
