package RepositorySorters;

import Comparators.iPersonComparator;
import Person.Person;


public class BubbleSorter implements isorter {
    @Override
    public void sort(Person[] p, iPersonComparator c) {
        Person buf;
        boolean swapped;
        for (int i = 0; i < p.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < p.length - i - 1; j++) {
                if (c.compare(p[j], p[j + 1]) >= 1) {
                    buf = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = buf;
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }
}
