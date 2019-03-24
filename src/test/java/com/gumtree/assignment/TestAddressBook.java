package com.gumtree.assignment;

import com.gumtree.assigment.AddressBook;
import com.gumtree.assigment.Contact;
import com.gumtree.assigment.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


public class TestAddressBook {

    AddressBook addressBook = new AddressBook();

    @Test
    public void testReadAddressBook(){
        Assertions.assertEquals(addressBook.getContacts().size(), 5);
    }

    @Test
    public void testFilterContacts(){
        List<Contact> femaleContacts = addressBook.filterContacts((Contact c)->c.getGender()== Gender.FEMALE);

        Contact firstContact = femaleContacts.get(0);

        Assertions.assertEquals(femaleContacts.size(),2);

        Assertions.assertEquals(firstContact.getName(),"Gemma Lane");
    }

    @Test
    public void testSortContacts(){
        List<Contact> sortedLexically = addressBook.sortContacts((Contact c1, Contact c2) -> c1.getName().compareTo(c2.getName()));

        Contact firstContact =  sortedLexically.get(2);
        Assertions.assertEquals(firstContact.getName(), "Paul Robinson");
    }

}
