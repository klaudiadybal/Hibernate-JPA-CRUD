package crud.dao;

import crud.entity.Student;

public interface DAO {
    void save(Student student);

    Student findById(int id);
}
