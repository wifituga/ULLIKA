import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import javax.swing.JFrame;

public class DataInsertion extends javax.swing.JFrame {
    private DefaultTableModel dataInsertionModel;
    private DatabaseConnection db;
    private String table_name;
    
    public DataInsertion(Connection con, int index) {
        initComponents();
        // No terminar el programa al cerrar el frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Inicializar modelo default de la tabla
        dataInsertionModel = new DefaultTableModel();
        // Inicializar conexión db vacía, y referenciar db.con de TableView mediante parámetro
        db = new DatabaseConnection(false);
        this.db.setCon(con);
        
        //Limpar la tabla
        clearTable();
        // Asignar modelo de tabla a tabla dataInsertion
        dataInsertionTable.setModel(dataInsertionModel);
        // Llenar lista desplegable con los nombres de las tablas
        db.populateComboBox(jComboBox1);
        // Configurar que la tabla se actualice al seleccionar una tabla diferente en la lista desplegable
        jComboBox1.addItemListener(new ItemChangeListener());
        // Cargar la primera tabla de la lista desplegable
        jComboBox1.setSelectedIndex(index);
        updateTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dataInsertionTable = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dataInsertionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(dataInsertionTable);

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1010, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jButton2)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1214, 236));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void clearTable() {
        dataInsertionModel.setRowCount(0);
        dataInsertionModel.setColumnCount(0);
    }
    
    private void updateTable() {
        clearTable();
        table_name = jComboBox1.getSelectedItem().toString();
        db.populateDataInsertionTable(table_name, dataInsertionModel);
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Insertar fila en la base de datos
        db.insertInto(table_name, dataInsertionModel);
        
        TableView tableView = new TableView(this.db.getCon(), this.jComboBox1.getSelectedIndex());
        super.dispose();
        tableView.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dataInsertionTable;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}