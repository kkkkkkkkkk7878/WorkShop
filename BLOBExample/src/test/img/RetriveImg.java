package test.img;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RetriveImg {

	public static void main(String[] args) {
		try(Scanner s= new Scanner(System.in);){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","admin123");
			PreparedStatement ps = con.prepareStatement("select id,img from imgtab where id=?");
			System.out.println("Enter The Id :");
			String id =s.nextLine();
			ps.setString(1, id);
			ResultSet rs =ps.executeQuery();
			if(rs.next()){
				Blob b = rs.getBlob(2);
				byte b1[] = b.getBytes(1, (int)b.length());
				FileOutputStream fos = new FileOutputStream("D:\\samplejava\\ram.txt");
				System.out.println("Image Retraived....!!");
				fos.write(b1);
				fos.close();
			}
			else{
				System.out.println("Invalid Id");
			}
			con.close();
		}
		catch(ClassNotFoundException ce){
			System.out.println("Details:"+ce.getMessage());
		}
		catch(SQLException se){
			System.out.println("Details:"+se.getMessage());
		}
		catch(FileNotFoundException fe){
			System.out.println("Details :"+fe.getMessage());
		}
		catch (IOException e) {
			e.printStackTrace();
			System.out.println("Details :"+e.getMessage());
		}

	}

}
