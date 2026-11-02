package org.example;
import org.example.Repository.StudentRepository;

public class Main {
    public static void main(String[] args) {

        StudentRepository st = new StudentRepository();
        //st.createStudent(new Student("OSama", "osama@gmail.com", "B TEch"));
        //st.updateStudent(new Student("Faizan", "faizan@gmail.com", "M Tech"),2);
        //st.deleteStudent(4);
        //st.getStudentById(4);
        st.getStudent();

    }



    }
