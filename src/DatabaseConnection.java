import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DatabaseConnection {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    
    public DatabaseConnection(boolean bool) {      
        con = null;
        ps = null;
        rs = null;
        
        if(bool) {
            String url = "jdbc:oracle:thin:@(description=(retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.sa-santiago-1.oraclecloud.com))(connect_data=(service_name=gaa80506deb0bc2_tutpfyjjx4ysrfb2_high.adb.oraclecloud.com))(security=(ssl_server_cert_dn=\"CN=adb.sa-santiago-1.oraclecloud.com, O=Oracle Corporation, L=Redwood City, ST=California, C=US\")))";
            
            Properties properties = new Properties();        
            properties.setProperty("user", "UL20201684");
            properties.setProperty("password", "ULima20201684#");
            properties.setProperty("javax.net.ssl.keyStore", "C:\\Users\\QUINONES\\Desktop\\Ingeniería de Datos\\Proyecto\\DBA Application\\cwallet.sso");

            try {
                con = DriverManager.getConnection(url, properties);
            }
            catch(Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
            }
        }
        
    }
    
    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public void populateTableSelectCB(javax.swing.JComboBox<String> comboBox) {
        try {
            String qry = "SELECT table_name  FROM all_tables WHERE owner = 'UL20203864'";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            while(rs.next())
                comboBox.addItem(rs.getString(1));
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    public void populateMainHeaders(String table_name, DefaultTableModel tableModel, javax.swing.JComboBox<String> comboBox) {
        try {
            String qry = "SELECT * FROM UL20203864." + table_name;
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            
            // Cargar cabeceras de la tabla
            for(int i=0; i<column_count; i++) {
                String column_name = rsmd.getColumnName(i+1);
                tableModel.addColumn(column_name);
                comboBox.addItem(column_name);
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    public void populateHeaders(String table_name, DefaultTableModel tableModel) {
        try {
            String qry = "SELECT * FROM UL20203864." + table_name;
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            
            // Cargar cabeceras de la tabla
            for(int i=0; i<column_count; i++)
                tableModel.addColumn(rsmd.getColumnName(i+1));
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
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
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
        
        return 0;
    }
    
    public void populateMainTB(String table_name, DefaultTableModel tableModel) {
        try {
            int column_count = getColumnCount(table_name);
            
            String qry = "SELECT * FROM UL20203864." + table_name + " ORDER BY 1 ASC";
            ps = con.prepareStatement(qry);
            rs = ps.executeQuery();
            
            // Cargar datos de la tabla
            Object[] data = new Object[column_count];
            while(rs.next()) {
                for(int i=0; i<column_count; i++)
                    data[i] = rs.getString(i+1);
                tableModel.addRow(data);
            }
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    public void populateDataInsTB(String table_name, DefaultTableModel tableModel) {
        try {
            int column_count = getColumnCount(table_name);
            
            // Cargar fila vacía
            Object[] data = new Object[column_count];
            for(int i=0; i<column_count; i++)
                data[i] = null;
            tableModel.addRow(data);
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    public void populateDataModTB(String table_name, DefaultTableModel tableModel, String column_name, String id) {
        try {
            int column_count = getColumnCount(table_name);
            
            String qry = "SELECT * FROM UL20203864." + table_name + " WHERE " + column_name + " = ?";
            ps = con.prepareStatement(qry);
            ps.setString(1, id);
            rs = ps.executeQuery();

            // Cargar datos de la fila seleccionada
            Object[] data = new Object[column_count];
            rs.next();
            for(int i=0; i<column_count; i++)
                data[i] = rs.getString(i+1);
            tableModel.addRow(data);
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
    }
    
    public void selectWhere(String table_name, DefaultTableModel tableModel, String column_name, String filter) {
        try {
            String qry = "SELECT * FROM UL20203864." + table_name + " WHERE " + column_name + " LIKE ? ORDER BY 1 ASC";
            ps = con.prepareStatement(qry);
            ps.setString(1, "%" + filter + "%");
            rs = ps.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int column_count = rsmd.getColumnCount();
            
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
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        }
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
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
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
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
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
            JOptionPane.showMessageDialog(null, "El registro seleccionado se trata de un registro padre.\n" + ex.getLocalizedMessage());
        }
    }
}