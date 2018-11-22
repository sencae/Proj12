package Comparators;

import Person.Person;

public class CompByDateOfBirth implements PersonComparator{

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
    }
}
