package Repository;


import Comparators.*;
import Person.Person;
import RepositorySorters.*;
import org.joda.time.LocalDate;





public class Repository {
    private isorter sorter = new SorterByGender();
    private int size = 0;
    private Person[] masPersons = new Person[size];

    /**
     *
     * @param i Id
     * @param a Date of birth
     * @param s Full name
     * @param f gender
     */
    public void addPerson(Integer i,LocalDate a, String s, char f) {
        addInRepos(new Person(i,a, s, f));
    }

    /**
     *
     * @param index index of person to remove
     */
    public void removePerson(int index){
        size--;
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
    public void sortByFio() {
       sorter.sort(masPersons,new CompByFio());
    }
    public void sortByDoB(){
        sorter.sort(masPersons,new CompByDateOfBirth());
    }
    public void sortByGender(){
        sorter.sort(masPersons,new CompByGender());
    }

    public static void main(String[] args) {

        Repository f = new Repository();
        f.addPerson(1,LocalDate.now(),"aaaaaaa",'m');
        f.addPerson(2,new LocalDate(1991,12,12),"bbbbb",'w');
        f.addPerson(3,new LocalDate(1991,11,11),"bbfsabb",'m');
        f.addPerson(4,new LocalDate(1993,10,4),"cccc",'w');
        f.addPerson(5,new LocalDate(1991,12,12),"bbbbb",'w');
        f.showPersons();
        System.out.println();
        f.sortByGender();
        f.showPersons();

    }

}


