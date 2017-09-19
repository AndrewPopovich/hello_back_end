package ua.andrew.hellobackend.dao;

import ua.andrew.hellobackend.dto.Contacts;

import java.util.List;

public interface ContactsDAO {
    List<Contacts> getAllContacts();
}