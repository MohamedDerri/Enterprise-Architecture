package miu.edu.Lab8PartB.Repository;

import miu.edu.Lab8PartB.domain.PersonMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMongoRepository extends MongoRepository<PersonMongo, Integer> {
}


