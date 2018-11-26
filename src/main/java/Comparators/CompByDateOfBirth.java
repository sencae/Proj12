package Comparators;

import Person.Person;

public class CompByDateOfBirth implements iPersonComparator {

    @Override
    public int compare(Person o1, Person o2) {
        if (o1 == null || o2 == null) {
            return 0;
        }
        return o1.getDateOfBirth().compareTo(o2.getDateOfBirth());
    }
}
