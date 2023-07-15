package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataParser {
    public static LocalDate parseDate(String dateStr) throws InvalidDataException {
        try {
            return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (Exception e) {
            throw new InvalidDataException("Неверный формат даты рождения");
        }
    }

    public static String parsePhoneNumber(String phoneNumberStr) throws InvalidDataException {
        try {
            int phoneNumber = Integer.parseInt(phoneNumberStr);
            if (phoneNumber < 1000000 || phoneNumber > 9999999) {
                throw new InvalidDataException("Неверный формат номера телефона");
            }
            return phoneNumberStr;
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Неверный формат номера телефона");
        }
    }

    public static char parseGender(String genderStr) throws InvalidDataException {
        if (genderStr.length() != 1 || (!genderStr.equalsIgnoreCase("f") && !genderStr.equalsIgnoreCase("m"))) {
            throw new InvalidDataException("Неверный формат пола");
        }
        return genderStr.toLowerCase().charAt(0);
    }
}
