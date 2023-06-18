import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DatabaseConnection {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public DatabaseConnection() {      
        Properties properties = new Properties();
        
        String url = "jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.sa-santiago-1.oraclecloud.com))(connect_data=(service_name=gaa80506deb0bc2_tutpfyjjx4ysrfb2_high.adb.oraclecloud.com))(security=(ssl_server_cert_dn=\"CN=adb.sa-santiago-1.oraclecloud.com, O=Oracle Corporation, L=Redwood City, ST=California, C=US\")))";
        properties.setProperty("user", "UL20201684");
        properties.setProperty("password", "ULima20201684#");
        properties.setProperty("javax.net.ssl.keyStore", "C:\\Users\\QUINONES\\Desktop\\Ingeniería de Datos\\Documentos\\SQL Developer\\wallet_tutpfyjjx4ysrfb2");
        try {
            con = DriverManager.getConnection(url, properties);
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        ps = null;
        rs = null;
    }
    
    public int countColumns(String table_name) {
        try {
            String qry = "SELECT COUNT(*) FROM all_tab_columns WHERE OWNER = 'UL20203864' AND table_name = '" + table_name + "'";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                return rs.getInt(1);
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        
        return 0;
    }
    
    public void selectAll(String table_name) {
        try {
            int column_count = this.countColumns(table_name);
            
            String qry = "SELECT * FROM UL20203864." + table_name + " ORDER BY 1 ASC";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                for(int i=0; i<column_count; i++) {
                    System.out.print(rs.getString(i+1) + "\t");
                }
                System.out.print("\b\n");
            }
            System.out.println();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void selectColumn(String table_name, String column_name) {
        try {
            String qry = "SELECT " + column_name + " FROM UL20203864." + table_name;
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                System.out.println(rs.getString(1));
            }
            System.out.println();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void insertInto(int id, String fname, String lname) {
        try {
            String qry = "INSERT INTO EMPLOYEES VALUES (?, ?, ?)";
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
        long startTime = System.currentTimeMillis();
        DatabaseConnection db = new DatabaseConnection();
        System.out.println("Tiempo de establecimiento de conexión a la base de datos: " + (System.currentTimeMillis() - startTime) / 1000 + " segundo(s)\n");
        
        db.selectAll("FACULTAD");
        //db.insertInto(5, "César", "Rosales");
        db.selectColumn("FACULTAD", "nombre_facultad");
    }
}