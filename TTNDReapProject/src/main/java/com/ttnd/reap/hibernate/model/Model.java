package com.ttnd.reap.hibernate.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ttnd.reap.hibernate.hibernateUtil.HibernateUtil;

public class Model {
	static Map<String, List<Integer>> map;
	static List<Integer> defaltBadgesEarned;
	static {
		map = new HashMap<String, List<Integer>>();
		// User (Default role) (3 Gold, 2 Silver, 1 Bronze)
		List<Integer> al1 = new ArrayList();
		al1.add(3);
		al1.add(2);
		al1.add(1);
		map.put("User", al1);

		// Supervisor (6 Gold, 3 Silver, 2 Bronze)
		List<Integer> al2 = new ArrayList();
		al2.add(6);
		al2.add(3);
		al2.add(2);
		map.put("Supervisor", al2);

		// Practice Head (9 Gold, 6 Silver, 3 Bronze)
		List<Integer> al3 = new ArrayList();
		al3.add(9);
		al3.add(6);
		al3.add(3);
		map.put("Practice Head", al3);

        defaltBadgesEarned = new ArrayList();
        defaltBadgesEarned.add(0);
        defaltBadgesEarned.add(0);
        defaltBadgesEarned.add(0);
	}

	public static Set getBadgesSet() {
		Badges badges = new Badges();
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Badges.class);
		List list = criteria.list();
		return new HashSet(list);
	}

	public static List<Integer> getDefaultBadgesRemaining(String role) {
		return map.get(role);
	}
    public static List<Integer> getDefaultBadgesEarned()
    {
    	return defaltBadgesEarned;
    }
}
