package com.example.demo;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        //JDBC 드라이버 로딩
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //DriverManager.getConnection을 이용해 데이터베이스 연결
        Connection conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "scott",
                "tiger"
        );
        //PreparedStatement를 활용하여 sql문 실행
        PreparedStatement pstmt=conn.prepareStatement("SELECT * FROM MEMBER");
        //ResultSet타입의 rs변수에 SELECT문의 결과 저장
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            var user = new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age"));
                System.out.println(user);
        }
        rs.close();
        pstmt.close();
        conn.close(); //db연결 종료
    }
}
