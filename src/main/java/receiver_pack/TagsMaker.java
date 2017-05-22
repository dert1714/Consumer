package receiver_pack;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by valera on 22.05.2017.
 */
public class TagsMaker {
    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/", "postgres",
                "ASdf56");
        return connection;
    }

    private String getName(Connection conn, String table, String key, int id) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        String res ="";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT " + key + " FROM " + table + " WHERE " + "id=" + id);
            while (rs.next()) {
                res = (String) rs.getObject(1);
            }
            return res;
        }finally{
            if  (rs != null ) rs.close();
            if (stmt != null) stmt.close();
        }
    }

    public Map<String,String> selectRequest(Connection conn, String table, String name_key) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        Map<String,String> tags = new HashMap<>();
//        ArrayList<String> tmp_lst = null;
//        ArrayList res = new ArrayList();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM " + table + " WHERE name=" + "\'" + name_key + "\'");
            while ( rs.next() ) {
//                tmp_lst = new ArrayList<>();
                int id = (Integer)rs.getObject(1);
                String name = (String)rs.getObject(2);
                int region_id = (int)rs.getObject(3);
                String region = getName(conn,"regions","region",region_id);
                int hbar_id = (int)rs.getObject(4);
                String hbar = getName(conn,"hbars","hbar",hbar_id);
                tags.put("name", name);
                tags.put("region", region);
                tags.put("hbar", hbar);
//                tmp_lst.add(name);
//                tmp_lst.add(region);
//                tmp_lst.add(hbar);
//                res.add(tmp_lst);
            }
//            System.out.println(res);
            return tags;
        } finally {
            if  (rs != null ) rs.close();
            if (stmt != null) stmt.close();
        }
    }
}
