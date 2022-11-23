package org.example;

import java.sql.*;

public class SelectAllTest {
    public static void main(String[] args) {

        //DB연결을 위한 인터페이스
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        //DBMC접속 , jdbc URL 은 DBMS에서 정한 방식으로 입력,
        //DBMS와 연결을 하고 Connection을 구현하고 있는 객체를 반환.
        try {
            conn =
                    DriverManager.getConnection(
                            "jdbc:mysql://127.0.0.1:3307/examplesdb?useUnicode=true&serverTimezone=Asia/Seoul",
                            "urstory","u1234");

            if(conn != null){
                System.out.println("DMBS 연결 성공!!");
                System.out.println(conn.getClass().getName()); // getClass().getName() 자바 리플렉션
            }

            //SQL을 작성하고 SQL을 실행
            //insert into role(role_id, name) values (값, '값);
            // conn아 지금 연결된 DBMS에 이 SQL을 준비해줘, 그런데 물음표 부분을 남겨놔
            // 준비한 것을 참조하도록 PreparedStatement를 반환
           ps = conn.prepareStatement("select role_id, name from role");

            //select 문이 실행되면 실행된 결과는 DBMS에 있다.
           rs= ps.executeQuery(); //select문 실행

            // 더이상 읽어 올게 없을떄까지 반복한다.
            while(rs.next()){
                int roleId = rs.getInt("role_id");
                String name = rs.getString("name");
                System.out.println(roleId + " "+name);
            }


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }finally {
            try {
                //rs 자원 해제
                if(rs != null){
                    rs.close();
                }
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
