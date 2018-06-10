package test.img;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertImg {

	public static void main(String[] args) {
	 try(Scanner s = new Scanner(System.in);){
		 Class.forName("oracle.jdbc.driver.OracleDriver");
		 Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin123");
		 PreparedStatement ps =con.prepareStatement("insert into imgtab values(?,?)");
		 System.out.println("Enter The Id");
		 String id = s.nextLine();
		 System.out.println("Enter The Image path :");
		 String imgpath=s.nextLine();
		 File f = new File(imgpath);
		 FileInputStream fis = new FileInputStream(f);
		 ps.setString(1, id);
		 ps.setBinaryStream(2, fis, (int)f.length());
		 int i = ps.executeUpdate();
		 if(i==1){
			 System.out.println("1 Row Created Successfully...!!");
		 }
		 con.close();
	 }
	 catch(ClassNotFoundException ce){
		 System.out.println("Details :"+ce.getMessage());
	 }
	 catch(SQLException se){
		 System.out.println("Details :"+se.getMessage());
	 }
	 catch(FileNotFoundException fe){
		 System.out.println("Detail :"+fe.getMessage());
	 }
	}

}
