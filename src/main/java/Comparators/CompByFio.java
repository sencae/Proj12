package Comparators;

import Person.Person;

public class CompByFio implements iPersonComparator {
    @Override
    public int compare(Person o1, Person o2) {
        if (o1 == null || o2 == null) {
            return -1;
        }
        return o1.getFullName().compareTo(o2.getFullName());
    }


}
