package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private String lastName;
    private String firstName;
    private String middleName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private char gender;

    public Person(String lastName, String firstName, String middleName, LocalDate dateOfBirth, String phoneNumber,
            char gender) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName + " " +
                dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " " +
                phoneNumber + " " + gender;
    }
}
