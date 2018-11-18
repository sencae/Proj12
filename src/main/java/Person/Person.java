package Person;

import Repository.Repository;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.Scanner;

public class Person {


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    private void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    private void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public char getSex() {
        return sex;
    }

    private void setSex(char sex) {
        this.sex = sex;
    }


    private LocalDate dateOfBirth;

    private String fullName;

    private char sex;

    @Override
    public String toString() {
        return "PersonTest{" +
                "dateOfBirth=" + dateOfBirth +
                ", fullName='" + fullName + '\'' +
                ", sex=" + sex +
                '}';
    }


     public Person(LocalDate a, String s, char f){
    this.setSex(f);
    this.setFullName(s);
    this.setDateOfBirth(a);
    }

    /**
     *
     * @return person's age
     */
    public Years findAge(){
        return Years.yearsBetween(dateOfBirth,LocalDate.now());
}
    public static void main(String[] args) {
        Repository f = new Repository();
        f.addPerson(LocalDate.now(),"aaaaaaa",'m');
        f.addPerson(new LocalDate(1991,12,12),"bbbbb",'w');
        f.addPerson(new LocalDate(1991,11,11),"bbfsabb",'m');
        f.addPerson(new LocalDate(1993,10,4),"cccc",'w');

        f.showPersons();

        f.getAgeOfPerson(1);
        f.getAgeOfPerson(2);

    }
}
