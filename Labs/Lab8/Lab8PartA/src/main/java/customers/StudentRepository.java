package customers;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {

    Student findByName(String name);
    Student findByPhone(String phone);
    Student findByAddressCity(String city);
    List<Student> findByGradesCourseName(String name);
    //List<Student> findByGradesCourseNameAndGradesGrade(String courseName, String grade);

    //List<Student> findByGradesGradeAndGradesCourseName(String grade, String courseName);
    @Query("{'grades.grade': 'A', 'grades.courseName': ?0}")
    List<Student> findStudentsByCourseName(@Param("courseName") String courseName);
}
