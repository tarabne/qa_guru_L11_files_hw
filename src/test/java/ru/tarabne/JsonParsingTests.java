package ru.tarabne;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tarabne.models.Person;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonParsingTests {
    private final ClassLoader cl = FilesParsingTests.class.getClassLoader();

    @Test
    void jsonParsingWithJacksonTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("Person.json");
             Reader reader = new InputStreamReader(is)) {
                 ObjectMapper objectMapper = new ObjectMapper();
                 Person person = objectMapper.readValue(reader, Person.class);

                 Assertions.assertEquals(38, person.getAge());
                 Assertions.assertEquals("Иванов Виктор", person.getName());
                 Assertions.assertEquals("MALE", person.getAdditionalInfo().getGender());
                 Assertions.assertArrayEquals(new String[]{"лежание","бегание","пивопитие"},
                    person.getAdditionalInfo().getHobbies().toArray());
        }
    }
}
