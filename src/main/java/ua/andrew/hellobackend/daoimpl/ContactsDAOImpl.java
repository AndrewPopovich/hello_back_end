package ua.andrew.hellobackend.daoimpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.andrew.hellobackend.dao.ContactsDAO;
import ua.andrew.hellobackend.dto.Contacts;

import javax.transaction.Transactional;
import java.util.List;

@Repository("contactsDAO")
@Transactional
public class ContactsDAOImpl implements ContactsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Contacts> getAllContacts() {
        List result = null;
        String query = "FROM Contacts";

        try {
            result = sessionFactory
                    .getCurrentSession()
                    .createQuery(query)
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}