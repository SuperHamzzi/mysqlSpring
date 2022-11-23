package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTeset02 {
    public static void main(String[] args) {

        //DB연결을 위한 인터페이스
        Connection conn = null;
        PreparedStatement ps =null;
        //DBMC접속 , jdbc URL 은 DBMS에서 정한 방식으로 입력,
        //DBMS와 연결을 하고 Connection을 구현하고 있는 객체를 반환.
        try {
            conn =
                    DriverManager.getConnection(
                            "jdbc:mysql://127.0.0.1:3307/examplesdb?useUnicode=true&serverTimezone=Asia/Seoul",
                            "urstory","u1234");


            //자동 커밋을 false로 설정.
            conn.setAutoCommit(false);


            //SQL을 작성하고 SQL을 실행
            //insert into role(role_id, name) values (값, '값);
            // conn아 지금 연결된 DBMS에 이 SQL을 준비해줘, 그런데 물음표 부분을 남겨놔
            // 준비한 것을 참조하도록 PreparedStatement를 반환
           ps = conn.prepareStatement("delete from role where role_id =?");

            //물음표에 값을 채워줘 . 바인딩 , 바인딩 까지 하면 SQL을실행할 준비.
            ps.setInt(1,3); // 1번쨰 물음표에 정수 값을 설정.

            //SQL 실행 executeUpdate(); - insert, update, delete 할 때 사용
            int updateCount = ps.executeUpdate();
            System.out.println("수정된 건수 : " + updateCount);
            conn.commit();
        } catch (Exception ex) {
            try {
                conn.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // handle any errors
        }finally {
            try {
                //ps 자원 해제
                if(ps !=null)
                    ps.close();


                // 연결 끊기
                if(conn != null)
                conn.close();
            } catch (SQLException e) {
                System.out.println("SQLException" + e.getMessage());
            }
        }
    }
}
