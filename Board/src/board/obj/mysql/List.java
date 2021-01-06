package board.obj.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class List {
   
private Connection conn;      // 파라미터에 있던 아이들은 클래스 안의 멤버변수로 갖다 붙인다
   
   private String title;
   private String content;
   private String author;
   private String nal;
   private int readcount;      // 클래스 변수의 초깃값은 무조건 default값이기이에 0으로 세팅된다.
   private int no;

   private Statement stmt;
   private String sql;
   private ResultSet rs;
   
   public List() {
      
   }
   
//   public Connection getConnection() throws SQLException{
//      try {
//         Class.forName("oracle.jdbc.driver.OracleDriver");
//      } catch (ClassNotFoundException e) {
//         e.printStackTrace();
//      }
//      conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
//      return conn;
//   }
   
   public void boardListTitle() {
      System.out.println("======전체 게시물 출력=======");
      System.out.print("번호\t제목\t내용\t작성자\t날짜\t조회수\n");
   }
   
   public void boardListQuery() throws SQLException{
      conn = Register.getConnection();
      stmt = conn.createStatement();
      sql = "select * from boardsanga order by no asc";      // 내림차순은 desc
   }
   
   public void boardListExcuter() throws SQLException{
      rs = stmt.executeQuery(sql);      
   }
   public void boardListProcess() throws SQLException{
      while (rs.next()) {
         no = rs.getInt("no");
         title = rs.getString("title");
         content = rs.getString("content");
         author = rs.getString("author");
         nal = rs.getString("nal");
         readcount = rs.getInt("readcount");
         System.out.print(no + "\t" + title + "\t" + content + "\t" + author + "\t" + nal + "\t" + readcount + "\n");
      }      
   }
   
//   public static void main(String[] args) {
//      
//      List li = new List();
//      li.boardListTitle();
//      try {
//         li.boardListQuery();
//         li.boardListExcuter();
//         li.boardListProcess();
//      } catch (SQLException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//   }
}