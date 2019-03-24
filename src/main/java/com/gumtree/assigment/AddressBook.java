package com.gumtree.assigment;

import com.gumtree.assigment.enums.Gender;
import com.gumtree.assigment.enums.SortOrder;


import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Address book class to hold a number of contacts
 */
public class AddressBook {

    private static final String FILE_NAME = "/AddressBook";
    private List<Contact> contacts = new ArrayList<>();

    /**
     * Constructor of the address book. When created the address book
     * tries to read the contacts from the file specified by FILE_NAME
     */
    public AddressBook()  {
        try {
            readAddressBook(FILE_NAME);
        } catch (Exception e){
            System.err.println("Failed to read the address book");
            e.printStackTrace();
        }
    }

    /**
     * A method to read an address book from a text file located in the classpath
     * with the name given by the parameter. The file list the contacts in
     * the following format: name, gender, birth date. Separated with
     * commas with each contact per line.
     * @param filename The name of the file
     * @throws ParseException A parse exception if the content could not be parsed
     */
    private void readAddressBook(String filename) throws ParseException
    {
        InputStream inputStream = this.getClass().getResourceAsStream(filename);

        DateFormat sdf = new SimpleDateFormat("dd/MM/yy");

        try (Scanner scanner = new Scanner(inputStream)){
            while (scanner.hasNextLine()) {

                String[] line = scanner.nextLine().split(",\\s");
                String name = line[0];
                Gender gender = Gender.valueOf(line[1].toUpperCase());
                Date dateOfBirth = sdf.parse(line[2]);

                contacts.add(new Contact(name, gender, dateOfBirth));
            }
        }
    }

    /**
     * Gets the contacts in the address book as a list of contacts
     * @return List of contacts
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /**
     * A sort method for the address book.
     * @param sort A sorting function
     * @return The list of contacts sorted according to the sorting function
     */
    public List<Contact> sortContacts(Comparator<Contact> sort){
        return contacts.stream().sorted(sort).collect(Collectors.toList());
    }

    /**
     * A method that takes a compare function that takes two contacts
     * @param filter Filter function that take a Contact and returns
     *               a boolean
     * @return The contact list filtered based on the filter function
     */
    public List<Contact> filterContacts(Predicate<Contact> filter){
        return contacts.stream().filter(filter).collect(Collectors.toList());
    }

    /**
     * A method that takes a criterium function and returns the first
     * contact in the contact list that fulfills the criteria.
     * @param criterium A boolean function that takes a Contact
     * @return The first contact in the list that fulfills the criteria
     * function
     */
    public Contact getContact(Predicate<Contact> criterium){
        for(Contact c: contacts)
        {
            if (criterium.test(c)) return c;
        }
        return null;
    }

    /**
     * Help function that filters the contact list by gender.
     * The method uses the filterContacts internally
     * @param gender the gender to filter from
     * @return the contacts filtered by the gender specified
     */
    public List<Contact> filterByGender(Gender gender){
        return filterContacts((Contact contact) -> contact.getGender() == gender);
    }

    /**
     * Help function that orders the contact list by their age.
     * The method uses the sortContacts internally.
     * @param order An enum to specify the direction of the sorting
     * @return the contacts ordered by age
     */
    public List<Contact> sortByAge(SortOrder order){
        return sortContacts((Contact c1, Contact c2) ->
            (order == SortOrder.OLDER_TO_YOUNGER)?c1.getBirthDate().compareTo(c2.getBirthDate())
            :c2.getBirthDate().compareTo(c1.getBirthDate())
        );
    }

    /**
     * Help function to get the first contact in with the given name. if the user
     * specified the start of the name rather than the fullname a partial match is made.
     * The method uses the getContact method internally.
     * @param name the name of the contact
     * @return The first contact with the given name
     */
    public Contact getContactByName(String name){
        return getContact((Contact c) -> c.getName().toUpperCase().startsWith(name.toUpperCase()));
    }

}
