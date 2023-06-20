import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;

public class TableView extends javax.swing.JFrame {
    private DefaultTableModel tableViewModel;
    private DatabaseConnection db;
    private String table_name;

    public TableView() {
        initComponents();
        // Inicializar modelo default de la tabla
        tableViewModel = new DefaultTableModel();
        //Inicializar conexión db, cronometrando su tiempo de establecimiento
        long startTime = System.currentTimeMillis();
        db = new DatabaseConnection();
        System.out.println("Tiempo de establecimiento de conexión a la base de datos: " + (System.currentTimeMillis() - startTime) / 1000 + " segundo(s)\n");
        
        this.initializeFrame(0);
    }
    
    public TableView(Connection con, int index) {
        initComponents();
        // Inicializar modelo default de la tabla
        tableViewModel = new DefaultTableModel();
        // Inicializar conexión db vacía, y referenciar db.con de TableView mediante parámetro
        db = new DatabaseConnection(false);
        this.db.setCon(con);
        
        this.initializeFrame(index);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableViewTable = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableViewTable);

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Insertar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(1016, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1214, 388));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public DatabaseConnection getDb() {
        return db;
    }

    public void setDb(DatabaseConnection db) {
        this.db = db;
    }
    
    public void initializeFrame(int index) {
        // Limpiar la tabla
        clearTable();
        //Asignar modelo de tabla a tabla tableView
        this.tableViewTable.setModel(tableViewModel);
        // Llenar lista desplegable con los nombres de las tablas
        db.populateComboBox(jComboBox1);
        // Configurar que la tabla se actualice al seleccionar una tabla diferente en la lista desplegable
        jComboBox1.addItemListener(new ItemChangeListener());
        // Cargar la primera tabla de la lista desplegable
        jComboBox1.setSelectedIndex(index);
        updateTable();
    }
    
    public void clearTable() {
        tableViewModel.setRowCount(0);
        tableViewModel.setColumnCount(0);
    }
    
    public void updateTable() {
        clearTable();
        table_name = jComboBox1.getSelectedItem().toString();
        db.populateTableViewTable(table_name, tableViewModel);
    }
    
    class ItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                updateTable();
       }
    }
}
    
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DataInsertion dataInsertionFrame = new DataInsertion(this.db.getCon(), this.jComboBox1.getSelectedIndex());
        super.dispose();
        dataInsertionFrame.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TableView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TableView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TableView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TableView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableViewTable;
    // End of variables declaration//GEN-END:variables
}