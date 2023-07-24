import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Main extends javax.swing.JFrame {
    private DefaultTableModel mainTBModel = new DefaultTableModel();;
    private DatabaseConnection db;
    private String table_name;

    public Main() {
        initComponents();
        
        //Inicializar conexión db
        db = new DatabaseConnection(true);
        
        // Cargar nombres de las tablas en la lista desplegable
        db.populateTableSelectCB(tableSelectCB);
        
        // Configurar que la tabla se actualice al seleccionar un item diferente en la lista desplegable
        tableSelectCB.addItemListener(new ItemChangeListener());
                
        //Asignar modelo mainTBModel a tabla tableViewTable
        mainTB.setModel(mainTBModel);
        
        // Cargar la primera tabla de la lista desplegable
        tableSelectCB.setSelectedIndex(0);
        updateTable();
    }
    
    public Main(Connection con, String table_name) {
        initComponents();
        
        // Inicializar conexión db vacía, y recibir db.con mediante parámetro
        db = new DatabaseConnection(false);
        db.setCon(con);
        
        // Cargar nombres de las tablas en la lista desplegable
        db.populateTableSelectCB(tableSelectCB);
        
        // Configurar que la tabla se actualice al seleccionar un item diferente en la lista desplegable
        tableSelectCB.addItemListener(new ItemChangeListener());
                
        //Asignar modelo mainTBModel a tabla tableViewTable
        mainTB.setModel(mainTBModel);
        
        // Cargar la primera tabla de la lista desplegable
        tableSelectCB.setSelectedItem(table_name);
        updateTable();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mainTB = new javax.swing.JTable();
        tableSelectCB = new javax.swing.JComboBox<>();
        nuevoBTN = new javax.swing.JButton();
        eliminarBTN = new javax.swing.JButton();
        editarBTN = new javax.swing.JButton();
        searchBarCB = new javax.swing.JComboBox<>();
        searchBar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Visualizar data");

        mainTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(mainTB);

        tableSelectCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableSelectCBActionPerformed(evt);
            }
        });

        nuevoBTN.setText("Nuevo");
        nuevoBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoBTNActionPerformed(evt);
            }
        });

        eliminarBTN.setText("Eliminar");
        eliminarBTN.setMaximumSize(new java.awt.Dimension(66, 25));
        eliminarBTN.setMinimumSize(new java.awt.Dimension(66, 25));
        eliminarBTN.setPreferredSize(new java.awt.Dimension(80, 25));
        eliminarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBTNActionPerformed(evt);
            }
        });

        editarBTN.setText("Editar");
        editarBTN.setMaximumSize(new java.awt.Dimension(66, 25));
        editarBTN.setMinimumSize(new java.awt.Dimension(66, 25));
        editarBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBTNActionPerformed(evt);
            }
        });

        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nuevoBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(editarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(eliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(searchBarCB, javax.swing.GroupLayout.Alignment.LEADING, 0, 188, Short.MAX_VALUE)
                                .addComponent(tableSelectCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(20, 20, 20)
                            .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tableSelectCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBarCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nuevoBTN))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1214, 443));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public DatabaseConnection getDb() {
        return db;
    }

    public void setDb(DatabaseConnection db) {
        this.db = db;
    }
    
    public void clearTable() {
        mainTBModel.setRowCount(0);
        mainTBModel.setColumnCount(0);
        searchBarCB.removeAllItems();
    }
    
    public void updateTable() {
        clearTable();
        table_name = tableSelectCB.getSelectedItem().toString();
        db.populateMainHeaders(table_name, mainTBModel, searchBarCB);
        db.populateMainTB(table_name, mainTBModel);
    }
    
    class ItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                updateTable();
            }
        }
    }
    
    private void tableSelectCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableSelectCBActionPerformed
    }//GEN-LAST:event_tableSelectCBActionPerformed

    private void nuevoBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoBTNActionPerformed
        // Crear y abrir un frame DataInsert
        DataInsert dataInsFrame = new DataInsert(db.getCon(), table_name);
        super.dispose();
        dataInsFrame.setVisible(true);
    }//GEN-LAST:event_nuevoBTNActionPerformed

    private void editarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarBTNActionPerformed
        try {
            // Obtener la llave primaria del registro seleccionado
            String id = mainTBModel.getValueAt(mainTB.getSelectedRow(), 0).toString();
            
            // Crear y abrir un frame DataModify, enviando como parámetro la llave primaria del registro seleccionado
            DataModify dataModFrame = new DataModify(db.getCon(), table_name, id);
            super.dispose();
            dataModFrame.setVisible(true);
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún registro.");
        }
    }//GEN-LAST:event_editarBTNActionPerformed

    private void eliminarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBTNActionPerformed
        try {    
            // Obtener la llave primaria del registro seleccionado
            String id = mainTBModel.getValueAt(mainTB.getSelectedRow(), 0).toString();

            // Eliminar el registro seleccionado y actualizar la tabla
            db.delete(table_name, mainTBModel, id);
            updateTable();
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningún registro.");
        }
    }//GEN-LAST:event_eliminarBTNActionPerformed

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        mainTBModel.setRowCount(0);
        table_name = tableSelectCB.getSelectedItem().toString();
        
        if(searchBar.getText().equals(""))
            db.populateMainTB(table_name, mainTBModel);
        else
            db.selectWhere(table_name, mainTBModel, searchBarCB.getSelectedItem().toString(), searchBar.getText());
    }//GEN-LAST:event_searchBarActionPerformed
    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editarBTN;
    private javax.swing.JButton eliminarBTN;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mainTB;
    private javax.swing.JButton nuevoBTN;
    private javax.swing.JTextField searchBar;
    private javax.swing.JComboBox<String> searchBarCB;
    private javax.swing.JComboBox<String> tableSelectCB;
    // End of variables declaration//GEN-END:variables
}