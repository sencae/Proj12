package Person;

import org.joda.time.*;

public class Person {
    public LocalTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    /** Дата рождения персоны */
    private LocalTime dateOfBirth;
    /** ФИО персоны */
    private String fullName;
    /** Пол персоны */
    private char sex;

    private void findAge(String a){

    }
    public static void main(String[] args) {
Person a = new Person();

    }
}
