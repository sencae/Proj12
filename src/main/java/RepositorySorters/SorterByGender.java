package RepositorySorters;

import Comparators.PersonComparator;
import Person.Person;

import java.util.Arrays;

public class SorterByGender implements isorter {
    @Override
    public void sort(Person[] p, PersonComparator c) {
        Arrays.sort(p,c);
    }
}
