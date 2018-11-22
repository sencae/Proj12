package Comparators;

import Person.Person;

public class CompByFio implements PersonComparator {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getFullName().compareTo(o2.getFullName());
    }


}
