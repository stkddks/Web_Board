package board.obj.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
   
private Connection conn;      // 파라미터에 있던 아이들은 클래스 안의 멤버변수로 갖다 붙인다
   
   private String titleDelete;

   private Statement stmt;
   private PreparedStatement pstmt;
   private String sql;
   private int cnt;
   
   public Delete() {  }    // 생성자함수는 일단 비워두자
      
   
   public void setDeleteTitle() {      
      System.out.println("삭제할 제목을 입력하세요: ");
//      String titleDelete = sc.next();      // String은 지역변수가 안되니 멤버변수로 위로 올려야한다.
      titleDelete = Register.sc.next();      
   }
   
   public void boardDeleteQuery() throws SQLException{
	   sql = "delete from boardsanga where title like %?%";
	   pstmt = conn.prepareStatement(sql); 
	   pstmt.setString(1, titleDelete);
	   
//      conn = Register.getConnection();
//      stmt = conn.createStatement();
//      sql = "delete from boardsanga where title like '%"+titleDelete+"%'";
   }
   
   public void boardDeleteExecuter() throws SQLException {      
	   cnt = pstmt.executeUpdate();
//      cnt = stmt.executeUpdate(sql);
   }

   public void boardDeletePrint() {
      System.out.println(cnt + "건의 게시물이 삭제되었습니다.");
   }
}





