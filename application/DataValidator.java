package application;

public class DataValidator {
    public static void validateData(String[] data) throws InvalidDataException {
        if (data.length != 6) {
            throw new InvalidDataException("Введено недостаточно или слишком много данных");
        }
    }
}
