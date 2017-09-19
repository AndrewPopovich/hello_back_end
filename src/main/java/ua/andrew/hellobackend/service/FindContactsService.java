package ua.andrew.hellobackend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.andrew.hellobackend.dao.ContactsDAO;
import ua.andrew.hellobackend.dto.Contacts;
import ua.andrew.hellobackend.exception.ContactsNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FindContactsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FindContactsService.class);

    @Autowired
    private ContactsDAO contactsDAO;

    public List<Contacts> findDoesntMatch(String regex) {
        List<Contacts> result = contactsDAO.getAllContacts();
        Pattern pattern = Pattern.compile(regex);

        if (result.size() == 0) {
            LOGGER.debug("No contacts found! Throw NoHandlerFoundException!");
            throw new ContactsNotFoundException();
        }
        LOGGER.debug("Count of all contacts = " + result.size());

        result = Arrays.asList(result.parallelStream().filter(contact -> {
            Matcher matcher = pattern.matcher(contact.getName());
            return !matcher.matches();
        }).toArray(Contacts[]::new));

        return result;
    }
}