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

		Long id = student.getId();
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
		Long id = student.getId();
		List<Student> foundStudents = dao.findByLastName(lastName);

		List<Long> ids = foundStudents.stream()
				.map(Student::getId)
				.toList();

		assertThat(foundStudents).isNotEmpty();
		assertThat(ids).contains(id);
	}

	@Test
	@Transactional
	@Rollback
	public void canFindAllStudents() {
		Student student1 = new Student("John", "Smith", "john@crud.com");
		Student student2 = new Student("Mary", "Johnson", "mary@crud.com");
		Student student3 = new Student("James", "Williams", "james@crud.com");

		dao.save(student1);
		dao.save(student2);
		dao.save(student3);

		List<Student> foundStudents = dao.findAll();

		assertThat(Long.valueOf(foundStudents.size())).isEqualTo(dao.count());
	}

	@Test
	@Transactional
	@Rollback
	public void canCountStudents() {
		Student student1 = new Student("John", "Smith", "john@crud.com");
		Student student2 = new Student("Mary", "Johnson", "mary@crud.com");
		Student student3 = new Student("James", "Williams", "james@crud.com");

		dao.save(student1);
		dao.save(student2);
		dao.save(student3);

		Long count = dao.count();

		assertThat(count).isEqualTo(3);

	}

	@Test
	@Transactional
	@Rollback
	public void canUpdateStudent() {
		Student student = new Student("John", "Smith", "john@crud.com");
		dao.save(student);
		Long id = student.getId();

		student.setFirstName("Louis");
		dao.update(student);

		assertThat(id).isEqualTo(student.getId());
		assertThat(student.getFirstName()).isEqualTo("Louis");

	}

	@Test
	@Transactional
	@Rollback
	public void canDeleteStudent() {
		Student student = new Student("John", "Smith", "john@crud.com");
		dao.save(student);

		Long countBefore = dao.count();
		dao.delete(student);

		Long countAfter = dao.count();

		assertThat(countAfter).isEqualTo(countBefore - 1);
	}


}

