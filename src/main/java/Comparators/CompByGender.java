package Comparators;

import Person.Person;

public class CompByGender implements PersonComparator {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getGender().compareTo(o2.getGender());
    }
}
