package crud.dao;

import crud.entity.Student;

import java.util.List;

public interface DAO {
    void save(Student student);

    Student findById(int id);

    List<Student> findByLastName(String lastName);
}
