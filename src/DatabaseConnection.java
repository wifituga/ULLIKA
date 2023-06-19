import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

public class DatabaseConnection {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public DatabaseConnection() {      
        Properties properties = new Properties();
        
        String url = "jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.sa-santiago-1.oraclecloud.com))(connect_data=(service_name=gaa80506deb0bc2_tutpfyjjx4ysrfb2_high.adb.oraclecloud.com))(security=(ssl_server_cert_dn=\"CN=adb.sa-santiago-1.oraclecloud.com, O=Oracle Corporation, L=Redwood City, ST=California, C=US\")))";
        properties.setProperty("user", "UL20201684");
        properties.setProperty("password", "ULima20201684#");
        properties.setProperty("javax.net.ssl.keyStore","C:\\Users\\QUINONES\\Desktop\\Ingeniería de Datos\\Documentos\\SQL Developer\\wallet_tutpfyjjx4ysrfb2");
        
        try {
            con = DriverManager.getConnection(url, properties);
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        ps = null;
        rs = null;
    }
        
    public String[] getColumnNames (String table_name) {
        try {
            String qry = "SELECT * FROM UL20203864." + table_name + " ORDER BY 1 ASC";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            
            String[] column_names = new String[column_count];
            for(int i=0; i<column_count; i++) {
                column_names[i] = rsmd.getColumnName(i+1);
            }
            
            return column_names;
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        
        return null;
    }
    
    public void populateComboBox(javax.swing.JComboBox<String> jComboBox1) {
        try {
            String qry = "SELECT table_name  FROM all_tables WHERE OWNER = 'UL20203864'";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                    jComboBox1.addItem(rs.getString(1));
                }
            }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void populateTable(String table_name, DefaultTableModel tableModel) {
        try {
            String qry = "SELECT * FROM UL20203864." + table_name + " ORDER BY 1 ASC";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            
            Object[] data = new Object[column_count];
            while(rs.next()) {
                for(int i=0; i<column_count; i++) {
                    data[i] = rs.getString(i+1);
                }
                tableModel.addRow(data);
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void returnColumn(String table_name, String column_name, DefaultTableModel tableModel) {
        try {
            String qry = "SELECT " + column_name + " FROM UL20203864." + table_name + " ORDER BY 1 ASC";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            
            Object[] data = new Object[column_count];
            while(rs.next()) {
                for(int i=0; i<column_count; i++) {
                    data[i] = rs.getString(i+1);
                }
                tableModel.addRow(data);
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void insertInto(String table_name, int id, String fname, String lname) {
        try {
            String qry = "INSERT INTO UL20203864." + table_name + " VALUES (?, ?, ?)";
            ps = con.prepareStatement(qry);
           
            ps.setString(1, Integer.toString(id));
            ps.setString(2, fname);
            ps.setString(3, lname);
            
            ps.executeUpdate();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
    }
}