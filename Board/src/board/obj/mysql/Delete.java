package board.obj.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
   
private Connection conn;      // 파라미터에 있던 아이들은 클래스 안의 멤버변수로 갖다 붙인다
   
   private String titleDelete;

   private Statement stmt;
   private String sql;
   private int cnt;
   
   public Delete() {      // 생성자함수는 일단 비워두자
      
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
   
   public void setDeleteTitle() {      
      System.out.println("삭제할 제목을 입력하세요: ");
//      String titleDelete = sc.next();      // String은 지역변수가 안되니 멤버변수로 위로 올려야한다.
      titleDelete = Register.sc.next();      
   }
   
   public void boardDeleteQuery() throws SQLException{
      conn = Register.getConnection();
      stmt = conn.createStatement();
      sql = "delete from boardsanga where title like '%"+titleDelete+"%'";
   }
   
   public void boardDeleteExecuter() throws SQLException {      
      cnt = stmt.executeUpdate(sql);
   }

   public void boardDeletePrint() {
      System.out.println(cnt + "건의 게시물이 삭제되었습니다.");
   }
}
//   public static void main(String[] args) {
//      
//      Delete del = new Delete();
//      del.setDeleteTitle();
//      try {
//         del.boardDeleteQuery();
//         del.boardDeleteExecuter();
//      } catch (SQLException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//      del.boardDeletePrint();
//   }





