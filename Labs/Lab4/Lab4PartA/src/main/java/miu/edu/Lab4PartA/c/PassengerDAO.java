package miu.edu.Lab4PartA.c;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerDAO extends JpaRepository<Passenger, Integer> {
}
