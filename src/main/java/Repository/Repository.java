package Repository;

import Person.Person;
import org.joda.time.LocalDate;

public class Repository {
    private int size = 0;
    private Person[] masPersons = new Person[size];

    /**
     *
     * @param a Date of birth
     * @param s Full name
     * @param f gender
     */
    public void addPerson(LocalDate a, String s, char f) {
        Person per = new Person(a, s, f);
        addInRepos(per);
    }

    /**
     *
     * @param index index of person to remove
     */
    public void removePerson(int index){
        if(index==0) {
            Person[] newMasPersons = new Person[masPersons.length - 1];
            System.arraycopy(masPersons,1,newMasPersons,0,newMasPersons.length);
                masPersons = newMasPersons;
        }
        else if(index==masPersons.length-1) {
            Person[] newMasPersons = new Person[masPersons.length - 1];
            if (newMasPersons.length >= 0)
                System.arraycopy(masPersons, 0, newMasPersons, 0, newMasPersons.length);
            masPersons = newMasPersons;
        }
        else {
            Person[] newMasPersons = new Person[masPersons.length - 1];
            for (int i =0;i<masPersons.length;i++)
            {
                if(i<index)
                    newMasPersons[i]=masPersons[i];
                else if(i==index) {continue;
                }
                else
                    newMasPersons[i-1]=masPersons[i];

            }
            masPersons=newMasPersons;
        }
        }


    private void addInRepos(Person p) {
        if (size == 0 || masPersons.length == size) {
            size++;
            masPersons = resize();
        }
        masPersons[masPersons.length - 1] = p;
    }

    /**
     * function to get a full list of persons
     */
    public void showPersons() {
        for (Person p : masPersons)
            System.out.println(p.toString());
    }

    /**
     *
     * @return array with new size
     */
    private Person[] resize() {
        Person[] newMasPerson = new Person[size];
        System.arraycopy(masPersons, 0, newMasPerson, 0, masPersons.length);
        return newMasPerson;
    }

    /**
     * function to get one person
     * @param index index of person to take
     */
    public void getPerson(int index) {
        if(index<0||index>masPersons.length)
            System.out.println("неверный индекс");
        else
        System.out.println(masPersons[index].toString());
    }

    /**
     *
     * @param index index of person to take
     */
    public void getAgeOfPerson(int index){
        System.out.println(masPersons[index].findAge().getYears());
        }
}

