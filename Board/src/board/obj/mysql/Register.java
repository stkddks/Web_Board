package board.obj.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//import sun.jvm.hotspot.debugger.win32.coff.WindowsNTSubsystem;

public class Register {
   
   public static Scanner sc;      // private -> public 이렇게 하면 내가 굳이 new를 쓰지 않아도 클래스만 불러오면 쓸 수 있다
   public static Connection conn;      // 파라미터에 있던 아이들은 클래스 안의 멤버변수로 갖다 붙인다
   
   private String title;
   private String titleContent;
   private int indexI;
   private String content;
   private String author;
   private String nal;
   private int readcount;
      
   private Statement stmt;
   private PreparedStatement pstmt ;
   private String sql;
   private int cnt; 

   static{      // static 은 main 과 같은 공간에 잡힌다. 
      // 인스턴스는 메모리공간을 호출해서 쓰는 방식? 생성자로써 메모리공간을 초기화하는 데 쓰이는 것이고 static은 메모리 공간이 안에서 초기화되는 방식
      // 생성자는 static 구조 안으로 들어와야 말이 맞겠죠?
      sc = new Scanner(System.in);
   }
   
   
   public Register() {
      sc = new Scanner(System.in);
   }
   
   public static Connection getConnection() throws SQLException{   // static 으로 설정해주면 클래스로 직접 호출할 수 있다.
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } 
      
      conn = DriverManager.getConnection("jdbc:mysql://bbr123.cafe24.com:3306/bbr123?characterEncoding=utf8", "bbr123","alstjr95!");
      return conn;
   }
   
   public void setTitleContent() {
      System.out.println("제목|내용:");      // 사용자도 할때 제목과 내용을 파이프로 구분시킨다.
      titleContent = sc.next();
   }
   
   public void titleContentProcess() {      
      indexI = titleContent.indexOf("|");   // 문자열은 배열로 인식하시에 파이프(indexI)로 구분시켜 방구분을 해준다.
      title = titleContent.substring(0, indexI);
      content = titleContent.substring(indexI+1);   // 제목따로 내용따로 분리가 되었다.
   }
   
   public void setAuthor() {
      System.out.println("작성자입력: ");
      author = sc.next();
   }
   
   public void setNal() {
      System.out.println("날짜: ");
      nal = sc.next();   // date는 겹쳐서 오류난다?      
   }
   
   public void setReadCount() {
      System.out.println("조회수: ");
      readcount = sc.nextInt();
   }
   
   public void boardQuery() throws SQLException{   //sql   
	   // PreparedStatement 를 쓸때 
	   sql = "insert into boardsanga (title, content, author,nal,readcount) values(?,?,?,?,?)";
	   PreparedStatement pstmt = conn.prepareStatement(sql); 
	   pstmt.setString(1, title);
	   pstmt.setString(2, content);
	   pstmt.setString(3, author);
	   pstmt.setString(4, nal);
	   pstmt.setInt(5, readcount);
	   
	   // Statement 를 쓸때 
//      stmt = conn.createStatement();     
//      sql = "insert into boardsanga (title, content, author,nal,readcount) values('" + title + "', '" + content + "', '" + author + "', '" + nal + "', " + readcount + ")";
   }
   
   public void boardExecuter() throws SQLException{   // sql실행
      cnt = pstmt.executeUpdate();
//      cnt = stmt.executeUpdate(sql);
      System.out.println(cnt+"긴 게시글이 등록되었습니다.");
   }
}

//   public static void main(String[] args) {
//      
//      Register rg = new Register();
//      rg.setTitleContent();
//      rg.titleContentProcess();
//      rg.setAuthor();
//      rg.setNal();
//      rg.setReadCount();
//      
//      try {
//         rg.getConnection();
//         rg.boardQuery();
//         rg.boardExecuter();
//      } catch (SQLException e) {
//         e.printStackTrace();
//      }
//   }
