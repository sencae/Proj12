package Comparators;

import Person.Person;

import java.util.Comparator;

public interface PersonComparator extends Comparator<Person> {
     int compare(Person o1, Person o2);
}
