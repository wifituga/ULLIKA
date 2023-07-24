import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class DataModify extends javax.swing.JFrame {
    private DefaultTableModel dataModTBModel = new DefaultTableModel();
    private DatabaseConnection db = new DatabaseConnection(false);
    private String table_name;
    private String id;
    
    public DataModify(Connection con, String table_name, String id) {
        initComponents();
        
        // No terminar el programa al cerrar el frame actual
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Crear y abrir un frame Main al cerrrar el frame actual
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                main();
            }
        });
        
        // Inicializar conexión db vacía, y recibir db.con mediante parámetro
        db.setCon(con);
        
        // Asignar modelo de dataModTBModel a tabla dataModTable
        dataModTB.setModel(dataModTBModel);
        
        this.table_name = table_name;
        this.id = id;
        updateTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dataModTB = new javax.swing.JTable();
        guardarBTN = new javax.swing.JButton();
        cancelarBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modificar data");

        dataModTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(dataModTB);

        guardarBTN.setText("Guardar");
        guardarBTN.setPreferredSize(new java.awt.Dimension(80, 25));
        guardarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBTNActionPerformed(evt);
            }
        });

        cancelarBTN.setText("Cancelar");
        cancelarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guardarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cancelarBTN)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarBTN)
                    .addComponent(guardarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1214, 192));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void clearTable() {
        dataModTBModel.setRowCount(0);
        dataModTBModel.setColumnCount(0);
    }
    
    private void updateTable() {
        clearTable();
        db.populateHeaders(table_name, dataModTBModel);
        db.populateDataModTB(table_name, dataModTBModel, dataModTB.getColumnName(0), id);
    }
    
    private void main() {
        Main main = new Main(db.getCon(), table_name);
        super.dispose();
        main.setVisible(true);
    }
    
    private void guardarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBTNActionPerformed
        // Enforcar el cursos en otra columna para registrar los cambios
        dataModTB.editCellAt(0, 0);
        
        // Modificar fila en la base de datos
        db.update(table_name, dataModTBModel, id);
        main();
    }//GEN-LAST:event_guardarBTNActionPerformed

    private void cancelarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBTNActionPerformed
        main();
    }//GEN-LAST:event_cancelarBTNActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarBTN;
    private javax.swing.JTable dataModTB;
    private javax.swing.JButton guardarBTN;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}