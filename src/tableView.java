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
        
        // Cargar nombres de las tablas en la lista desplegable
        db.populateComboBox(tableViewComboBox);
        
        // Configurar que la tabla se actualice al seleccionar un item diferente en la lista desplegable
        tableViewComboBox.addItemListener(new ItemChangeListener());
                
        //Asignar modelo tableViewModel a tabla tableView
        tableViewTable.setModel(tableViewModel);
        
        // Cargar la primera tabla de la lista desplegable
        tableViewComboBox.setSelectedIndex(0);
        updateTable();
    }
    
    public TableView(Connection con, String table_name) {
        initComponents();
        
        // Inicializar modelo default de la tabla
        tableViewModel = new DefaultTableModel();
        
        // Inicializar conexión db vacía, y recibir db.con mediante parámetro
        db = new DatabaseConnection(false);
        db.setCon(con);
        
        // Cargar nombres de las tablas en la lista desplegable
        db.populateComboBox(tableViewComboBox);
        
        // Configurar que la tabla se actualice al seleccionar un item diferente en la lista desplegable
        tableViewComboBox.addItemListener(new ItemChangeListener());
                
        //Asignar modelo tableViewModel a tabla tableViewTable
        tableViewTable.setModel(tableViewModel);
        
        // Cargar la primera tabla de la lista desplegable
        tableViewComboBox.setSelectedItem(table_name);
        updateTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableViewTable = new javax.swing.JTable();
        tableViewComboBox = new javax.swing.JComboBox<>();
        insertarBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        editarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableViewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tableViewTable);

        tableViewComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableViewComboBoxActionPerformed(evt);
            }
        });

        insertarBtn.setText("Nuevo");
        insertarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarBtnActionPerformed(evt);
            }
        });

        eliminarBtn.setText("Eliminar");
        eliminarBtn.setMaximumSize(new java.awt.Dimension(66, 25));
        eliminarBtn.setMinimumSize(new java.awt.Dimension(66, 25));
        eliminarBtn.setPreferredSize(new java.awt.Dimension(80, 25));
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        editarBtn.setText("Editar");
        editarBtn.setMaximumSize(new java.awt.Dimension(66, 25));
        editarBtn.setMinimumSize(new java.awt.Dimension(66, 25));
        editarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBtnActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tableViewComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(insertarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(editarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(eliminarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tableViewComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertarBtn)
                    .addComponent(eliminarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    
    public void clearTable() {
        tableViewModel.setRowCount(0);
        tableViewModel.setColumnCount(0);
    }
    
    public void updateTable() {
        clearTable();
        table_name = tableViewComboBox.getSelectedItem().toString();
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
    
    private void tableViewComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableViewComboBoxActionPerformed
    }//GEN-LAST:event_tableViewComboBoxActionPerformed

    private void insertarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarBtnActionPerformed
        DataInsertion dataInsFrame = new DataInsertion(db.getCon(), table_name);
        super.dispose();
        dataInsFrame.setVisible(true);
    }//GEN-LAST:event_insertarBtnActionPerformed

    private void editarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarBtnActionPerformed
        // Obtener la llave primaria del registro seleccionado
        String id = tableViewModel.getValueAt(tableViewTable.getSelectedRow(), 0).toString();
        
        DataModification dataModFrame = new DataModification(db.getCon(), table_name, id);
        super.dispose();
        dataModFrame.setVisible(true);
    }//GEN-LAST:event_editarBtnActionPerformed

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        String id = tableViewModel.getValueAt(tableViewTable.getSelectedRow(), 0).toString();
        db.delete(table_name, tableViewModel, id);
        
        updateTable();
    }//GEN-LAST:event_eliminarBtnActionPerformed
    
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
    private javax.swing.JButton editarBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JButton insertarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> tableViewComboBox;
    private javax.swing.JTable tableViewTable;
    // End of variables declaration//GEN-END:variables
}