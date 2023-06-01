package miu.edu.Lab4PartA.d;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolDAO extends JpaRepository<School, Integer> {
}
