package vn.edu.saigontech.SpringMVCDemo.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SpringMVCDemo.models.Specialization;
import vn.edu.saigontech.SpringMVCDemo.models.Student;


@Transactional
public class studentDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private specializationDAO specializationDAO;
	
	public List<Student> getAllStudent(){
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Student");
		
		List<Student> stuArr = query.list();
		
		for (Student stu: stuArr) {
			stu.setSpecialization(specializationDAO.getSpecializationByID(stu.getSpecialization().getId()));
		}
		
		return stuArr;
	}
	
	public List<Student> getAllStudentBySpecialization(Specialization spec){
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Student s where s.id = :id").setParameter("id", spec.getId());
		
		List<Student> stuArr = query.list();
		
		for (Student stu: stuArr) {
			stu.setSpecialization(specializationDAO.getSpecializationByID(1));
		}
		
		return stuArr;
	}
	
	public Student getStudentByID(int id){
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Student s where s.id = :id").setParameter("id", id);
		
		Student stu = (Student) query.list().get(0);
		
		stu.setSpecialization(specializationDAO.getSpecializationByID(stu.getSpecialization().getId()));
		
		return stu;
	}
}
