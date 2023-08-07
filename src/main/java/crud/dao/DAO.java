package crud.dao;

import crud.entity.Student;

import java.util.List;

public interface DAO {
    void save(Student student);

    Student findById(Long id);

    List<Student> findByLastName(String lastName);

    Long count();

    List<Student> findAll();

    void update(Student student);

    void delete(Student student);
}
