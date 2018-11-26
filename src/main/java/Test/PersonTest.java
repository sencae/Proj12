package Test;

import Person.Person;
import org.joda.time.Years;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person p = new Person();

    @Test
    void findAge() {
        Years expected = Years.years(17);
        Years actual = p.findAge();
        assertEquals(expected, actual);
    }
}