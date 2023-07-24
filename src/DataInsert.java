import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class DataInsert extends javax.swing.JFrame {
    private DefaultTableModel dataInsTBModel = new DefaultTableModel();
    private DatabaseConnection db = new DatabaseConnection(false);
    private String table_name;
    
    public DataInsert(Connection con, String table_name) {
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
        
        // Cargar nombres de las tablas en la lista desplegable
        db.populateTableSelectCB(tableSelectCB);
        
        // Configurar que la tabla se actualice al seleccionar un item diferente en la lista desplegable
        tableSelectCB.addItemListener(new ItemChangeListener());
        
        // Asignar modelo de dataInsTBModel a tabla dataInsTable
        dataInsTB.setModel(dataInsTBModel);
        
        // Cargar la primera tabla de la lista desplegable
        tableSelectCB.setSelectedItem(table_name);
        updateTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dataInsTB = new javax.swing.JTable();
        tableSelectCB = new javax.swing.JComboBox<>();
        guardarBTN = new javax.swing.JButton();
        cancelarBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Insertar data");

        dataInsTB.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(dataInsTB);

        tableSelectCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tableSelectCBActionPerformed(evt);
            }
        });

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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guardarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cancelarBTN))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tableSelectCB, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1010, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(tableSelectCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBTN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBTN))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1214, 236));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void clearTable() {
        dataInsTBModel.setRowCount(0);
        dataInsTBModel.setColumnCount(0);
    }
    
    private void updateTable() {
        clearTable();
        table_name = tableSelectCB.getSelectedItem().toString();
        db.populateHeaders(table_name, dataInsTBModel);
        db.populateDataInsTB(table_name, dataInsTBModel);
    }
    
    class ItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                updateTable();
            }
        }
    }
    
    private void main() {
        Main main = new Main(db.getCon(), table_name);
        super.dispose();
        main.setVisible(true);
    }
    
    private void tableSelectCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tableSelectCBActionPerformed
    }//GEN-LAST:event_tableSelectCBActionPerformed

    private void guardarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBTNActionPerformed
        // Enforcar el cursos en otra columna para registrar los cambios
        dataInsTB.editCellAt(0, 0);
        
        // Insertar fila en la base de datos
        db.insertInto(table_name, dataInsTBModel);
        main();
    }//GEN-LAST:event_guardarBTNActionPerformed

    private void cancelarBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBTNActionPerformed
        main();
    }//GEN-LAST:event_cancelarBTNActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarBTN;
    private javax.swing.JTable dataInsTB;
    private javax.swing.JButton guardarBTN;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> tableSelectCB;
    // End of variables declaration//GEN-END:variables
}