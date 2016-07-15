package com.ttnd.reap.hibernate.daoImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.ttnd.reap.hibernate.dao.RegisterDao;
import com.ttnd.reap.hibernate.hibernateUtil.HibernateUtil;
import com.ttnd.reap.hibernate.model.BadgesEarned;
import com.ttnd.reap.hibernate.model.BadgesRemaining;
import com.ttnd.reap.hibernate.model.Model;
import com.ttnd.reap.hibernate.model.Employee;

@Repository("registerDAO")
public class RegisterDaoImpl implements RegisterDao {

	@Override

	public Boolean register(String first_name, String last_name, String dob, String email, String password,
			String gender, String serviceName, String practiceName, String role) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = dateFormat.parse(dob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		System.out.println("In Check register");
	      try {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
 		Transaction transaction = session.beginTransaction();
 		Employee employee = new Employee(first_name, last_name, date, email, password, gender, serviceName,
				practiceName, role);
		BadgesRemaining badgesRemaining = new BadgesRemaining();
		badgesRemaining.setEid(employee);
		badgesRemaining.setBid(Model.getBadgesSet());
		badgesRemaining.setValue(Model.getDefaultBadgesRemaining((employee.getRole())));
		
		
		
		BadgesEarned badgesEarned=new BadgesEarned();
		badgesEarned.setEmployee(employee);
		badgesEarned.setBadges(Model.getBadgesSet());
		badgesEarned.setValue(Model.getDefaultBadgesEarned());

		session.save(employee);
		session.save(badgesRemaining);
		session.save(badgesEarned);
		transaction.commit();
		session.close();
		return true;
       }
       catch(Exception e)
       {
    	   return false;
       }
	}

}
