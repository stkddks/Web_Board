package board.obj.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Search {
   
   private Connection conn;      // 파라미터에 있던 아이들은 클래스 안의 멤버변수로 갖다 붙인다
   
   private String title;
   private String titleSearch;
   private String content;
   private String author;
   private String nal;
   private int readcount;      // 클래스 변수의 초깃값은 무조건 default값이기이에 0으로 세팅된다.
   private int no;

   private Statement stmt;
   private Statement stmt2;
   private  PreparedStatement pstmt;
   private String sql;
   private String sql2;
   private ResultSet rs;
   private int cnt;
   
   
   public void setSearchTitle() {      
      System.out.println("찾는 게시물의 제목을 입력하세요");
      titleSearch = Register.sc.next();
   }
   
   public void boardSearchTitle() {
      System.out.println("번호\t제목\t내용\t작성자\t날짜\t조회수\n");      
   }
   
   
   public void boardSearchQuery() throws SQLException{
	   sql = "select * from boardsanga where title like %?%";
	   pstmt = conn.prepareStatement(sql); 
	   pstmt.setString(1, titleSearch);
	   
//      conn = Register.getConnection();
//      stmt = conn.createStatement();
//      sql = "select * from boardsanga where title like '%" + titleSearch + "%'";
      
      
   }
   public void boardSearchExecuter() throws SQLException{
//      rs = stmt.executeQuery(sql);
      ResultSet rs = pstmt.executeQuery();
    }
   
   
   public void boardSearchProsess() throws SQLException{      
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
   
   
   public void boardSearchReadcount() throws SQLException {
	   sql2 = "update boardsanga set readcount=readcount+1 where title like %?%";
	   pstmt = conn.prepareStatement(sql); 
	   pstmt.setString(1, titleSearch);
	   cnt = pstmt.executeUpdate();
//      stmt2 = conn.createStatement();
//      sql2 = "update boardsanga set readcount=readcount+1 where title like '%" + titleSearch + "%'";
//      cnt = stmt2.executeUpdate(sql2);   
      
   }
//   public static void main(String[] args) {
//      
//      Search sch = new Search();
//      sch.setSearchTitle();
//      sch.boardSearchTitle();
//      
//      try {
//         sch.boardSearchQuery();
//         sch.boardSearchExecuter();
//         sch.boardSearchProsess();
//         sch.boardSearchReadcount();      // 조회수 올리기
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }
//      
//   }

}