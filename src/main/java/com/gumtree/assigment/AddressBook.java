package com.gumtree.assigment;

import com.gumtree.assigment.enums.Gender;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AddressBook {

    private static final String FILE_NAME = "/AddressBook";
    private List<Contact> contacts = new ArrayList<Contact>();

    public AddressBook()  {
        try {
            readAddressBook(FILE_NAME);
        } catch (Exception e){
            System.err.println("Failed to read the address book");
            e.printStackTrace();
        }
    }

    public void readAddressBook(String filename) throws ParseException
    {
        InputStream inputStream = this.getClass().getResourceAsStream(filename);
        Scanner scanner = new Scanner(inputStream);
        DateFormat sdf = new SimpleDateFormat("dd/MM/yy");

        while (scanner.hasNextLine()){

            String[] line = scanner.nextLine().split(",\\s");
            String name = line[0];
            Gender gender = Gender.valueOf(line[1].toUpperCase());
            Date dateOfBirth = sdf.parse(line[2]);

            contacts.add(new Contact(name, gender, dateOfBirth));
        }
        scanner.close();
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Contact> sortContacts(Comparator<Contact> sort){
        return contacts.stream().sorted(sort).collect(Collectors.toList());
    }

    public List<Contact> filterContacts(Predicate<Contact> filter){
        return contacts.stream().filter(filter).collect(Collectors.toList());
    }


}
