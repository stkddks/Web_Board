package board.obj.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
   

   private Connection conn;      // 파라미터에 있던 아이들은 클래스 안의 멤버변수로 갖다 붙인다
   
   private String title;
   private String titleSearch;
   private String titleContent;
   private String titleUpdate;
   private String contentUpdate;
   private String authorUpdate;
   private String nalUpdate;
   private int readcountUpdate;
   private int indexI;
   private String content;
   private String author;
   private String nal;
   private int readcount;      // 클래스 변수의 초깃값은 무조건 default값이기이에 0으로 세팅된다.

   private Statement stmt;
   private String sql;
   private String sql1;
   private ResultSet rs;
   private int cnt;
   private char option;
   
   
   
   public void update() {
   }
   public void setUpdateTitle() {
      System.out.println("수정할 제목을 입력하세요: ");
      titleSearch = Register.sc.next();      
   }
   
   public void boardUpdateQuery() throws SQLException{
//      Statement stmt = null;
//      String sql = null;
      conn = Register.getConnection();
      stmt = conn.createStatement();
      sql = "select * from boardsanga where title='" + titleSearch + "'";
      
   }

   public void boardUpdateExecuter() throws SQLException{
      rs = stmt.executeQuery(sql); // 여기서는 select문을 실행해야하니까 executeQuery를 쓴다.
   }
   
   public void boardUpdateOld() throws SQLException{
      
      System.out.println("=== 변경하기 전 게시글입니다===");
      while (rs.next()) {
         title = rs.getString("title");
         content = rs.getString("content");
         author = rs.getString("author");
         nal = rs.getString("nal");
         readcount = rs.getInt("readcount");
         System.out.print(title + "\t\t" + content + "\t\t" + author + "\t\t" + nal + "\t\t" + readcount + "\n");
      }
      
   }
   public char boardUpdateOption() {
      System.out.println("정말로 수정하시겠습니까? (y/n)");
      option = Register.sc.next().charAt(0);
      return option;         // 자판기에서 동전을 넣고 음료와 커피를 누르면 음료가 나오는데 그때 이 내용물이 리턴값이다.
   }
   
   
   public void boardUpdateConfirm() {
      
      if (option == 'Y' || option == 'y') {
         System.out.println("제목|내용:");
         titleContent = Register.sc.next();
         indexI = titleContent.indexOf("|");
         
         titleUpdate = titleContent.substring(0, indexI);
         contentUpdate = titleContent.substring(indexI + 1);
         
         System.out.println("작성자입력: ");
         authorUpdate = Register.sc.next();
         System.out.println("날짜: ");
         nalUpdate = Register.sc.next();
         System.out.println("조회수: ");
         readcountUpdate = Register.sc.nextInt();
      }         
   }
   
   public void boardUpdateFinal() throws SQLException{
   
      conn = Register.getConnection();
      stmt = conn.createStatement();
      sql1 = "update boardsanga set title='" + titleUpdate + "',content='" + contentUpdate + "', author='"
            + authorUpdate + "', nal='" + nalUpdate + "', readcount=" + readcountUpdate + " where title='"
            + titleSearch + "'";
      System.out.print("제목\t내용\t작가\t날짜\t\t조회수\n");
      System.out.println(titleUpdate + "\t" + contentUpdate + "\t" + authorUpdate + "\t" + nalUpdate + "\t" + readcountUpdate);
      System.out.println();
      cnt =stmt.executeUpdate(sql1);
      System.out.println(cnt+"건 게시글이 수정되었습니다");
      
   }
   
   public void boardUpdateExecuter2() throws SQLException{
   }
}
//   public static void main(String[] args) {
//      
//      
//      Update up = new Update();
//      up.setUpdateTitle();
//      try {
//         up.boardUpdateQuery();
//         up.boardUpdateExecuter();
//         up.boardUpdateOld();
//         up.boardUpdateOption();
//         up.boardUpdateConfirm();
//         up.boardUpdateFinal();
//      } catch (SQLException e1) {
//         e1.printStackTrace();
//      }
//   }
   
//   public void Executer() {
//      
//      Update up = new Update();
//      up.setUpdateTitle();
//      try {
//         up.boardUpdateQuery();
//         up.boardUpdateExecuter();
//         up.boardUpdateOld();
//         up.boardUpdateOption();
//         up.boardUpdateConfirm();
//         up.boardUpdateFinal();
//      } catch (SQLException e1) {
//         e1.printStackTrace();
//      }
//   }






