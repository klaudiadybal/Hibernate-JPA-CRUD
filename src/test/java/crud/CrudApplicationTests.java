package crud;

import crud.dao.DAO;
import crud.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
	public void canFindStudentById() {
		Student student = new Student("John", "Smith", "john@crud.com");
		dao.save(student);

		int id = student.getId();

		
	}

}

