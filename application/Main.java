package application;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия, Имя, Отчество, Дата Рождения, Номер Телефона, Пол):");
        String input = scanner.nextLine();
        String[] data = input.split(" ");

        try {
            DataValidator.validateData(data);

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            LocalDate dateOfBirth = DataParser.parseDate(data[3]);
            String phoneNumber = DataParser.parsePhoneNumber(data[4]);
            char gender = DataParser.parseGender(data[5]);

            Person person = new Person(lastName, firstName, middleName, dateOfBirth, phoneNumber, gender);
            FileWriterUtil.savePersonData(person);
            System.out.println("Данные успешно сохранены.");
        } catch (InvalidDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл:");
            e.printStackTrace();
        }
    }
}
