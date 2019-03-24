package com.gumtree.assigment;

import com.gumtree.assigment.enums.Gender;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A class to hold a contact. The class holds
 * the name, gender and birth date of the person.
 */
public class Contact {

    private String name;
    private Gender gender;
    private Date birthDate;

    /**
     * Constructor of the contact class
     * @param name The name of the contact
     * @param gender The gender of the contact
     * @param birthDate The birth date of the contact
     */
    public Contact(String name, Gender gender, Date birthDate) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Returns the difference in age between the contact and the contact supplied
     * as a parameter. The time unit is specified as a asecond parameter.
     * @param contact The contact to compare the age with
     * @param unit The time unit
     * @return The difference in age
     */
    public long getAgeDifference(Contact contact, TimeUnit unit) {
        long msec = Math.abs(birthDate.getTime() - contact.getBirthDate().getTime());

        return unit.convert(msec, TimeUnit.MILLISECONDS);
    }
}
