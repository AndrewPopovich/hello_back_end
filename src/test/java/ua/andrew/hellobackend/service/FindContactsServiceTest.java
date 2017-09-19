package ua.andrew.hellobackend.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import ua.andrew.hellobackend.dao.ContactsDAO;
import ua.andrew.hellobackend.dto.Contacts;
import ua.andrew.hellobackend.exception.ContactsNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FindContactsServiceTest {

    private FindContactsService service;

    private List<Contacts> contactsList;

    private ContactsDAO contactsDAO;

    @Before
    public void init() {
        contactsList = new ArrayList<>();
        service = new FindContactsService();

        Contacts firstContact = new Contacts();
        firstContact.setName("Andrew");
        firstContact.setId(1);

        Contacts secondContact = new Contacts();
        secondContact.setName("Vasya");
        secondContact.setId(2);

        Contacts thirdContact = new Contacts();
        thirdContact.setName("Mike");
        thirdContact.setId(3);

        contactsList.add(firstContact);
        contactsList.add(secondContact);
        contactsList.add(thirdContact);
    }

    @Test
    public void testFindDoesntMatch() {
        contactsDAO = mock(ContactsDAO.class);

        when(contactsDAO.getAllContacts()).thenReturn(contactsList);
        ReflectionTestUtils.setField(service, "contactsDAO", contactsDAO);

        assertEquals("Something wrong with findDoesntMatch method!", 2,
                service.findDoesntMatch("^A.*$").size());

        verify(contactsDAO).getAllContacts();

        assertEquals("Something wrong when send empty String in method!", 3,
                service.findDoesntMatch("").size());

        assertEquals("Must return empty list!", 0,
                service.findDoesntMatch("^.*[aei].*$").size());
    }

    @Test(expected = ContactsNotFoundException.class)
    public void testFindDoesntMatchThrowEx() {
        contactsDAO = mock(ContactsDAO.class);

        when(contactsDAO.getAllContacts()).thenReturn(new ArrayList<>());
        ReflectionTestUtils.setField(service, "contactsDAO", contactsDAO);

        service.findDoesntMatch("^.*[aei].*$");

        verify(contactsDAO).getAllContacts();
    }
}