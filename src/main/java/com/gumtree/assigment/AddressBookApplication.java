package com.gumtree.assigment;

import com.gumtree.assigment.enums.Gender;
import com.gumtree.assigment.enums.SortOrder;

import java.util.concurrent.TimeUnit;


public class AddressBookApplication
{
    public static void main(String[] args)
    {
        AddressBook addressBook = new AddressBook();

        System.out.println("GumTreeUK Technical Assignment");
        System.out.println("Number of males in address book: "+addressBook.filterByGender(Gender.MALE).size());
        System.out.println("Oldest person in address book: "+addressBook.sortByAge(SortOrder.OLDER_TO_YOUNGER).get(0).getName());

        long days = addressBook.getContactByName("Bill").getAgeDifference(addressBook.getContactByName("Paul"), TimeUnit.DAYS);

        System.out.println("How many days are Bill older than Paul: "+days);
    }
}
