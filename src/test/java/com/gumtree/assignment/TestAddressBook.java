package com.gumtree.assignment;

import com.gumtree.assigment.enums.SortOrder;
import com.gumtree.assigment.AddressBook;
import com.gumtree.assigment.Contact;
import com.gumtree.assigment.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Tests for the AddressBook class.
 */
public class TestAddressBook {

    private AddressBook addressBook = new AddressBook();

    // Test to read the address book from file. The address book should contain 5
    // contacts
    @Test
    public void testReadAddressBook(){
        Assertions.assertEquals(5, addressBook.getContacts().size());
    }

    // Test to filter the contacts in the address book based on a filter function.
    // The filter function takes a Contact as a parameter and returns true if the
    // contact is to be included or false otherwise.
    @Test
    public void testFilterContacts(){
        List<Contact> femaleContacts = addressBook.filterContacts((Contact c)->c.getGender()== Gender.FEMALE);

        Contact firstContact = femaleContacts.get(0);

        Assertions.assertEquals(2, femaleContacts.size());

        Assertions.assertEquals("Gemma Lane", firstContact.getName());
    }

    // Test to sort the contacts in an address book according to a comparison function. The comparison
    // function takes two contacts c1, c2 and returns -1 if c1<c2, 0 if c1==c2 and 1 if c1>c2.
    @Test
    public void testSortContacts(){
        List<Contact> sortedLexically = addressBook.sortContacts((Contact c1, Contact c2) -> c1.getName().compareTo(c2.getName()));

        Contact firstContact =  sortedLexically.get(2);
        Assertions.assertEquals("Paul Robinson", firstContact.getName());
    }

    // Test to get the first contact in the address book that is female.
    @Test
    public void testGetContact(){
        Contact contact = addressBook.getContact((Contact c) -> c.getGender()==Gender.FEMALE);

        Assertions.assertEquals("Gemma Lane", contact.getName());
    }

    // Test filtering the contacts by gender
    @Test
    public void testFilterByGender(){
        List<Contact> maleContacts = addressBook.filterByGender(Gender.MALE);

        Assertions.assertEquals(3, maleContacts.size());
        Assertions.assertEquals("Bill McKnight", maleContacts.get(0).getName());
    }

    // Test filtering the contacts by gender
    @Test
    public void testSortByAge(){
        List<Contact> olderToYounger = addressBook.sortByAge(SortOrder.OLDER_TO_YOUNGER);
        Assertions.assertEquals("Wes Jackson", olderToYounger.get(0).getName());
    }

    //  Test to get a contact by name
    @Test
    public void testGetByName(){
        Contact contact = addressBook.getContactByName("Sarah");

        Assertions.assertEquals("Sarah Stone", contact.getName());
    }

    // Test to get the age difference between Sarah and Paul
    @Test
    public void testAgeDifference(){
        Contact sarah = addressBook.getContactByName("Sarah");
        Contact paul = addressBook.getContactByName("Paul");


        Assertions.assertEquals(1578, paul.getAgeDifference(sarah, TimeUnit.DAYS) );
    }
}
