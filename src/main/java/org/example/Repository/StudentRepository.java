package org.example.Repository;
import org.example.Model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private final String url = System.getenv().getOrDefault(
            "DB_URL", "jdbc:mysql://localhost:3306/jdbc_practice");
    private final String username = System.getenv().getOrDefault("DB_USERNAME", "root");
    private final String password = System.getenv("DB_PASSWORD");

    public void createStudent(Student student){
        String sql ="""
                        INSERT INTO students(name, email, course)
                        VALUES (?, ?, ?)
                        """;
        try (
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement(sql);
            ){
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            int rowAffected = ps.executeUpdate();
            if (rowAffected==1){
                System.out.println("Student Created Successfull:");
            }else {
                System.out.println("Create Student Failed");
            }
        } catch (SQLException e) {
            System.out.println("Data Base Connection Failed!");
            e.printStackTrace();
        }
    }





    public void updateStudent(Student student, int id){
        String sql ="""
                        UPDATE students 
                        SET name =?,
                            email=?,
                            course=?
                        WHERE id = ?
                        """;
        try (
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement(sql);
        ){
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getCourse());
            ps.setInt(4, id);
            int rowAffected  = ps.executeUpdate();
            if (rowAffected==1){
                System.out.println("Student Update Successfull:");
            }else {
                System.out.println("Updation Student Failed");
            }

        } catch (SQLException e) {
            System.out.println("Data Base Connection Failed");
            e.printStackTrace();
        }
    }


    public void deleteStudent(int id){
        String sql = """
                        DELETE FROM students WHERE id = ?
                        """;
        try (
                Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = con.prepareStatement(sql);
                ){
            ps.setInt(1, id);
            int rowAffected = ps.executeUpdate();
            if (rowAffected==1){
                System.out.println("Student Deleted Successfull:");
            }else {
                System.out.println("Student Deletion Failed");
            }
        } catch (SQLException e) {
            System.out.println("Data Base Connection Failed");
            e.printStackTrace();
        }
    }


    public void getStudentById(int id){
        String sql = """ 
                            SELECT id, name, email, course FROM students
                            WHERE id = ?
                            """;

        try (
                Connection con = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = con.prepareStatement(sql);
             ){
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Student student = mapRow(rs);
                    System.out.println(student);
                }else {
                    System.out.println("Student with id: "+ id +" Not Found!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Data Base Connection Failed!");
            e.printStackTrace();
        }
    }



    public void getStudent(){
        String sql = """ 
                            SELECT id, name, email, course FROM students
                            """;

        try (
                Connection con = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = con.prepareStatement(sql);
        ){
            try (ResultSet rs = ps.executeQuery()){
                List<Student> sl = new ArrayList<Student>();
                while(rs.next()) {
                    Student student = mapRow(rs);
                    sl.add(student);
                    System.out.println(student);
                }
            }
        } catch (SQLException e) {
            System.out.println("Data Base Connection Failed!");
            e.printStackTrace();
        }
    }

    private Student mapRow(ResultSet resultSet) throws SQLException {
        // Keep the column-to-field mapping in one place for every query.
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setCourse(resultSet.getString("course"));
        return student;
    }



}
