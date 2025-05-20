package ru.zagrebin.laba2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private StringProperty lastname;
    private StringProperty name;
    private StringProperty patronymic;
    private StringProperty city;
    private StringProperty group;
    private IntegerProperty age;

    public Student(String lastname, String name, String patronymic, int age, String city, String group){
        this.lastname = new SimpleStringProperty(lastname);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.patronymic = new SimpleStringProperty(patronymic);
        this.group = new SimpleStringProperty(group);
        this.city = new SimpleStringProperty(city);
    }

    public Student(){
        this("","", "", 0, "", "");
    }


    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public String getGroup() {
        return group.get();
    }

    public StringProperty groupProperty() {
        return group;
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setPatronymic(String patronymic) {
        this.patronymic.set(patronymic);
    }

    public String getPatronymic() {
        return patronymic.get();
    }

    public StringProperty patronymicProperty() {
        return patronymic;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return String.format("""
                Фамилия: %s
                Имя: %s
                Возраст: %d
                """, lastname.get(), name.get(), age.get());
    }
}