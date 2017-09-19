package ua.andrew.hellobackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.andrew.hellobackend.dto.Contacts;
import ua.andrew.hellobackend.service.FindContactsService;

import java.util.List;

@RestController
@RequestMapping("/hello")
public class ContactsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactsController.class);

    @Autowired
    private FindContactsService findContactsService;

    @RequestMapping("/contacts")
    public List<Contacts> getDoesntMatchContacts(@RequestParam(value = "nameFilter") String regex) {
        LOGGER.debug("Request parameters: nameFilter=" + regex);

        return findContactsService.findDoesntMatch(regex);
    }
}