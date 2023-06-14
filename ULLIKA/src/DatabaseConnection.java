import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public DatabaseConnection() {
        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "SYSTEM", "#ManuelIngenieroWiFi(2023-20)");
        }
        
        catch(Exception ex) {
            System.out.println(ex);
        }
        
        ps = null;
        rs = null;
    }
    
    public void insertInto(int id, String fname, String lname) {
        try {
            String sql = "INSERT INTO EMPLOYEES VALUES (?, ?, ?)";
            this.ps = this.con.prepareStatement(sql);
            
            this.ps.setString(1, Integer.toString(id));
            this.ps.setString(2, fname);
            this.ps.setString(3, lname);
            
            this.ps.executeUpdate();
        }   
        
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void selectAll() {
        try {
            String sql =    "SELECT * FROM EMPLOYEES " +
                            "ORDER BY 1 ASC";
            this.ps = this.con.prepareStatement(sql);
            this.rs = ps.executeQuery();
            
            while(rs.next()) {
                String id = rs.getString(1);
                String fname = rs.getString(2);
                String lname = rs.getString(3);
                System.out.print(id + "\t" + fname + "\t\t" + lname + "\n");
            }
        }   
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void selectRow(String column) {
        try {
            String sql = "SELECT " + column + " FROM EMPLOYEES";
            this.ps = this.con.prepareStatement(sql);
            this.rs = ps.executeQuery();
            
            while(rs.next()) {
                String result = rs.getString(1);
                System.out.println(result);
            }
        }   
        
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        //db.insertInto(5, "César", "Rosales");
        db.selectAll();
        //db.selectRow("id");
        //db.selectRow("lname");
    }
}