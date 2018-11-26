package Comparators;

import Person.Person;

public class CompByGender implements iPersonComparator {

    @Override
    public int compare(Person o1, Person o2) {
        if (o1 == null || o2 == null) {
            return -1;
        }
        return o1.getGender().compareTo(o2.getGender());
    }
}
