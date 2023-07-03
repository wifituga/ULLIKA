import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
        String url = "jdbc:oracle:thin:@(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.sa-santiago-1.oraclecloud.com))(connect_data=(service_name=gaa80506deb0bc2_tutpfyjjx4ysrfb2_high.adb.oraclecloud.com))(security=(ssl_server_cert_dn=\"CN=adb.sa-santiago-1.oraclecloud.com, O=Oracle Corporation, L=Redwood City, ST=California, C=US\")))";
        
        Properties properties = new Properties();        
        properties.setProperty("user", "UL20201684");
        properties.setProperty("password", "ULima20201684#");
        properties.setProperty("javax.net.ssl.keyStore", "C:\\Users\\QUINONES\\Desktop\\Ingeniería de Datos\\Proyecto\\DBA Application\\cwallet.sso");
        
        try {
            con = DriverManager.getConnection(url, properties);
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        ps = null;
        rs = null;
    }

    public DatabaseConnection(boolean bool) {      
        this.con = null;
        this.ps = null;
        this.rs = null;
    }
    
    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public void populateComboBox(javax.swing.JComboBox<String> jComboBox) {
        try {
            String qry = "SELECT table_name  FROM all_tables WHERE owner = 'UL20203864'";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            while(rs.next()) {
                    jComboBox.addItem(rs.getString(1));
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void populateTableViewTable(String table_name, DefaultTableModel tableModel) {
        try {
            String qry = "SELECT * FROM UL20203864." + table_name + " ORDER BY 1 ASC";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            
            // Cargar cabeceras de la tabla
            for(int i=0; i<column_count; i++) {
                tableModel.addColumn(rsmd.getColumnName(i+1));
            }
            
            // Cargar datos de la tabla
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
    
    public void populateDataInsTable(String table_name, DefaultTableModel tableModel) {
        try {
            String qry = "SELECT * FROM UL20203864." + table_name + " ORDER BY 1 ASC";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            
            // Cargar cabeceras de la tabla
            for(int i=0; i<column_count; i++) {
                tableModel.addColumn(rsmd.getColumnName(i+1));
            }
            
            // Cargar fila vacía
            Object[] data = new Object[column_count];
            for(int i=0; i<column_count; i++) {
                data[i] = null;
            }
            tableModel.addRow(data);
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void populateDataModTable(String table_name, DefaultTableModel tableModel, String id) {
        try {
            // Obtener el nombre de la primera columna de la tabla
            DatabaseMetaData dmd = con.getMetaData();
            ResultSet columns = dmd.getColumns(null, "UL20203864", table_name, null);
            columns.next();
            String column_name = columns.getString("COLUMN_NAME");
            
            String qry = "SELECT * FROM UL20203864." + table_name + " WHERE " + column_name + " = ?";
            ps = con.prepareStatement(qry);
            ps.setString(1, id);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();

            // Cargar cabeceras de la tabla
            for(int i=0; i<column_count; i++) {
                tableModel.addColumn(rsmd.getColumnName(i+1));
            }

            // Cargar datos de la fila seleccionada
            Object[] data = new Object[column_count];
            rs.next();
            for(int i=0; i<column_count; i++) {
                data[i] = rs.getString(i+1);
            }
            tableModel.addRow(data);
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public int getColumnCount(String table_name) {
        try {
            String qry = "SELECT COUNT(*) FROM all_tab_columns " +
            "WHERE owner = 'UL20203864' AND table_name = ?";
            ps = con.prepareStatement(qry);
            ps.setString(1, table_name);
            rs = ps.executeQuery();
            
            rs.next();
            return rs.getInt(1);
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        
        return 0;
    }
    
    public void insertInto(String table_name, DefaultTableModel tableModel) {
        try {
            int column_count = this.getColumnCount(table_name);
            
            String attrib = "(";
            for(int i=0; i<column_count; i++) {
                attrib = attrib + "'" + tableModel.getValueAt(0, i).toString() + "', ";
            }
            attrib = attrib.substring(0, attrib.length() - 2) + ")";
            
            String qry = "INSERT INTO UL20203864." + table_name + " VALUES " + attrib;
            ps = con.prepareStatement(qry);
            ps.executeUpdate();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void update(String table_name, DefaultTableModel tableModel, String id) {
        try {
            int column_count = this.getColumnCount(table_name);
            
            String attrib = "";
            for(int i=0; i<column_count; i++) {
                attrib = attrib + tableModel.getColumnName(i) + " = '" + tableModel.getValueAt(0, i).toString() + "', ";
            }
            attrib = attrib.substring(0, attrib.length() - 2);
            
            String qry = "UPDATE UL20203864." + table_name + " SET " + attrib + " WHERE " + tableModel.getColumnName(0) + " = ?";
            ps = con.prepareStatement(qry);
            ps.setString(1, id);
            ps.executeUpdate();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void delete(String table_name, DefaultTableModel tableModel, String id) {
        try {
            String qry = "DELETE FROM UL20203864." + table_name + " WHERE " + tableModel.getColumnName(0) + " = ?";
            ps = con.prepareStatement(qry);
            ps.setString(1, id);
            ps.executeUpdate();
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
    }
}