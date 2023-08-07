package crud;

import crud.dao.DAO;
import crud.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ComponentScan(basePackages = "crud.dao")
class CrudApplicationTests {

	DAO dao;

	@Autowired
	public CrudApplicationTests(DAO dao) {
		this.dao = dao;
	}

	@Test
	@Transactional
	@Rollback
	public void canSaveStudent() {
		Student student = new Student("John", "Smith", "john@crud.com");
		dao.save(student);

		assertThat(student.getId()).isNotNull();
	}


	@Test
	@Transactional
	@Rollback
	public void canFindStudentById() {
		Student student = new Student("John", "Smith", "john@crud.com");
		dao.save(student);

		int id = student.getId();
		Student foundStudent = dao.findById(id);

		assertThat(student.getId()).isEqualTo(foundStudent.getId());
		assertThat(student.getFirstName()).isEqualTo(foundStudent.getFirstName());
		assertThat(student.getLastName()).isEqualTo(foundStudent.getLastName());

	}

	@Test
	@Transactional
	@Rollback
	public void canFindStudentByLastName() {
		Student student = new Student("John", "Smith", "john@crud.com");
		dao.save(student);

		String lastName = student.getLastName();
		Integer id = student.getId();
		List<Student> foundStudents = dao.findByLastName(lastName);

		List<Integer> ids = foundStudents.stream()
				.map(Student::getId)
				.toList();

		assertThat(foundStudents).isNotEmpty();
		assertThat(ids).contains(id);
	}


}

