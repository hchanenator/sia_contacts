/**
 * 
 */
package org.herb.Contacts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.herb.Contacts.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * @author herb
 *
 */
@Repository
public class ContactRepository {

	private JdbcTemplate jdbc;

	/**
	 * @param jdbc
	 */
	@Autowired
	public ContactRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Contact> findAll() {
		
		return jdbc.query("select id, firstName, lastName, phoneNumber, emailAddress from contacts order by lastName", 
				new RowMapper<Contact>() {
					
					public Contact mapRow(ResultSet rs, int rownum) throws SQLException {
						
						Contact contact = new Contact();
						contact.setId(rs.getLong(1));
						contact.setFirstName(rs.getString(2));
						contact.setLastName(rs.getString(3));
						contact.setPhoneNumber(rs.getString(4));
						contact.setEmailAddress(rs.getString(5));
						return contact;
					}
				});
	}
	
	/**
	 * 
	 * @param contact
	 */
	public void save(Contact contact) {
		jdbc.update("insert into contacts (firstName, lastName, phoneNumber, emailAddress) values (?, ?, ?, ?)", 
				contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmailAddress());
	}
}
