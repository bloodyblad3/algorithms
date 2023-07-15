package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtil {
    public static void savePersonData(Person person) throws IOException {
        String filename = person.getLastName() + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.write(person.toString());
        writer.newLine();
        writer.close();
    }
}

