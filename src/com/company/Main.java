package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Excercise_01 {
    public void  countNumberOfLine(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            // Count the number of lines in the file
            int count = 0;

            while ((line = reader.readLine()) != null)
                ++count;

            System.out.println("Number of lines: " + count);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Excercise_02 {
    public void countNumberOfWord(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            int countWord = 0;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                countWord += words.length;
            }
            System.out.println("Number of words: " + countWord);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


class Contact {

    private String firstName, lastName, phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}


class ContactList {
    private List<Contact> contactList = new ArrayList<>();

    public ContactList() {
    }

    public ContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    void addContact(Contact contact) {
        contactList.add(contact);
    }

    void deleteContact(String firstName, String lastName) {
        contactList.removeIf(contact -> contact.getFirstName().equals(firstName) && contact.getLastName().equals(lastName));
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    void modifyContact(String firstName, String lastName, String newfirstName, String newlastName, String newphoneNumber) {
        deleteContact(firstName, lastName);
        contactList.add(new Contact(newfirstName, newlastName, newphoneNumber));
    }

    void printContact(PrintWriter out) {
        contactList.forEach(contact -> out.println(contact.getFirstName() + "\t" + contact.getLastName() + "\t" + contact.getPhoneNumber()));
    }
}

class Exercise_03 {

    public Exercise_03() {
    }

    void excercise_03(String fileName) {

        ContactList contactList = new ContactList();

        try (
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
        ){
            String line;
            while ((line = reader.readLine()) != null) {
                // Each word separated by tab
                String[] words = line.split("\t");

                contactList.addContact(new Contact(words[0], words[1], words[2]));

            }
            // print contact list
            contactList.getContactList().forEach(System.out::println);

            // delete contact
            contactList.deleteContact("Pham", "Doan Tien");

            //Save to file
            String output = System.getProperty("user.dir") + "/src/";
            try (PrintWriter out = new PrintWriter(output + "output.txt")) {
                contactList.printContact(out);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Student {
    private String firstName;
    private String lastName;
    private double average;

    public Student(String firstName, String lastName, double average) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.average = average;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getAverage() {
        return average;
    }
}

class StudentList {
    private List<Student> studentList = new ArrayList<>();

    public void addStudent(Student student) {
        studentList.add(student);
    }

    public void manageStudent() {
        System.out.println("Number of student: " + studentList.size());
        studentList.forEach(student -> System.out.println(student.getFirstName() + "\t" + student.getLastName()));
    }

    public void manageGrade() {
        studentList.forEach(student -> System.out.println(student.getFirstName() + "\t" + student.getLastName() + "\t" + student.getAverage()));
    }

    public void printStudent(PrintWriter out) {
        studentList.forEach(student -> out.println(student.getFirstName() + "\t" + student.getLastName() + "\t" + student.getAverage()));
    }

}

class Excercise_04 {

    public Excercise_04() {
    }

    void excercise_04(String fileName) {

        StudentList list = new StudentList();
        try (            BufferedReader reader = new BufferedReader(new FileReader(fileName));
        )   {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("first_name\tlast_name\taverage"))
                    continue;
                String[] words = line.split("\t");
                list.addStudent(new Student(words[0], words[1], Double.parseDouble(words[2])));
            }

            list.manageStudent();

            // Save to file
            try(PrintWriter out = new PrintWriter(System.getProperty("user.dir") + "/src/GradeStudent.txt")) {
            list.printStudent(out);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        String fileName = System.getProperty("user.dir") + "/src/input.txt";
        String fileName2 = System.getProperty("user.dir") + "/src/contact.txt";
        String fileName3 = System.getProperty("user.dir") + "/src/grade.txt";

        System.out.println("Excercise 1");

        Excercise_01 excercise_01 = new Excercise_01();
        excercise_01.countNumberOfLine(fileName);

        System.out.println("Excercise 2");

        Excercise_02 excercise_02 = new Excercise_02();
        excercise_02.countNumberOfWord(fileName);

        System.out.println("Excercise 3");

        Exercise_03 exercise_03 = new Exercise_03();
        exercise_03.excercise_03(fileName2);

        System.out.println("Excercise 4");

        Excercise_04 excercise_04 = new Excercise_04();
        excercise_04.excercise_04(fileName3);
    }
}

