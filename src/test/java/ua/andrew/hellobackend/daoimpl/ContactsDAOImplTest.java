package ua.andrew.hellobackend.daoimpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.andrew.hellobackend.Application;
import ua.andrew.hellobackend.dao.ContactsDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ContactsDAOImplTest {

    @Value("${dataBase.url}")
    private String DATABASE_URL;

    @Value("${dataBase.userName}")
    private String DATABASE_USERNAME;

    @Value("${dataBase.password}")
    private String DATABASE_PASSWORD;

    @Autowired
    private ApplicationContext context;

    private ContactsDAO contactsDAO;

    @Before
    public void addRowsToTheTable() {
        contactsDAO = (ContactsDAO) context.getBean("contactsDAO");

        try (Connection c = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             Statement s = c.createStatement()) {

            s.execute("INSERT INTO contacts (name) VALUES ('Andrew');\n" +
                    "INSERT INTO contacts (name) VALUES ('Vasya');\n" +
                    "INSERT INTO contacts (name) VALUES ('Mike');\n" +
                    "INSERT INTO contacts (name) VALUES ('Teresa');\n" +
                    "INSERT INTO contacts (name) VALUES ('Bob');");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void testGetAllContacts() {
        assertEquals("Something wrong with getAllContacts method!", 5,
                contactsDAO.getAllContacts().size());
    }
}