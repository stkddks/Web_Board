
//얘는 MySQL   - instance

package board.obj.mysql;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class BoardMainMysql {
public static void main(String[] args) {
   Scanner sc = new Scanner(System.in);
   
   Register rg = new Register();
   Search sch = new Search();
   Delete del = new Delete();
   Update up = new Update();
   List li = new List();

   Connection conn = null;
   
   while (true) {
      try {
         conn = rg.getConnection();
//         conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "khbclass", "dkdlxl");
      } catch (SQLException e1) {
         e1.printStackTrace();
      }

      System.out.println("====게시판 작성=======");
      System.out.println("R:등록 S:검색 D:삭제 U:수정 L:목록");

      char protocol = sc.next().charAt(0);
      if (protocol == 'R' || protocol == 'r') {
         try {
            rg.setTitleContent();
            rg.titleContentProcess();
            rg.setAuthor();
            rg.setNal();
            rg.setReadCount();
//            rg.getConnection();
            rg.boardQuery();
            rg.boardExecuter();
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {   
            try {
               conn.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
      
      }

      else if (protocol == 'S' || protocol == 's') {
         try {
            sch.setSearchTitle();
            sch.boardSearchTitle();
            sch.boardSearchQuery();
            sch.boardSearchExecuter();
            sch.boardSearchProsess();
            sch.boardSearchReadcount();      // 조회수 올리기
         } catch (SQLException e) {
            e.printStackTrace();
         }finally {
            try {
               conn.close();
            } catch (SQLException e) {
               e.printStackTrace();
            }
         }
         
      
      }

      else if (protocol == 'D' || protocol == 'd') {
         try {
            del.setDeleteTitle();
            del.boardDeleteQuery();
            del.boardDeleteExecuter();
            del.boardDeletePrint();
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            try {
               conn.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }

      else if (protocol == 'U' || protocol == 'u') { // 함수에서는 에러를 던지고 // main에서는 던진 에러를 잡아주고!
         char option = 'n';
         try {
            up.setUpdateTitle();
            up.boardUpdateQuery();
            up.boardUpdateExecuter();
            up.boardUpdateOld();
            option = up.boardUpdateOption();
            if (option == 'n' || option == 'N') {
               continue;
            }
            up.boardUpdateConfirm();
            up.boardUpdateFinal();
         } catch (SQLException e) {
            e.printStackTrace();
         }
         if (option == 'n' || option == 'N') {
            continue;
         }
      }

      else if (protocol == 'L' || protocol == 'l') {
         try {
            li.boardListTitle();
            li.boardListQuery();
            li.boardListExcuter();
            li.boardListProcess();
            
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            try {
               conn.close();
            } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }

      else {
         System.out.println("해당 메뉴는 없습니다. 다시 입력해 주세요:)");
      }
   }


}

}