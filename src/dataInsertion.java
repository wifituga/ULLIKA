import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class DataInsertion extends javax.swing.JFrame {
    private DefaultTableModel dataInsModel;
    private DatabaseConnection db;
    private String table_name;
    
    public DataInsertion(Connection con, String table_name) {
        initComponents();
        
        // No terminar el programa al cerrar el frame actual
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Crear y abrir un frame TableView al cerrrar el frame actual
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                backToTableView();
            }
        });
        
        // Inicializar modelo default de la tabla
        dataInsModel = new DefaultTableModel();
        
        // Inicializar conexión db vacía, y recibir db.con mediante parámetro
        db = new DatabaseConnection(false);
        db.setCon(con);
        
        // Cargar nombres de las tablas en la lista desplegable
        db.populateComboBox(dataInsComboBox);
        
        // Configurar que la tabla se actualice al seleccionar un item diferente en la lista desplegable
        dataInsComboBox.addItemListener(new ItemChangeListener());
        
        // Asignar modelo de dataInsModel a tabla dataInsTable
        dataInsTable.setModel(dataInsModel);
        
        // Cargar la primera tabla de la lista desplegable
        dataInsComboBox.setSelectedItem(table_name);
        updateTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dataInsTable = new javax.swing.JTable();
        dataInsComboBox = new javax.swing.JComboBox<>();
        guardarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Insertar data");

        dataInsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(dataInsTable);

        dataInsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataInsComboBoxActionPerformed(evt);
            }
        });

        guardarBtn.setText("Guardar");
        guardarBtn.setPreferredSize(new java.awt.Dimension(80, 25));
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
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
                        .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cancelarBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1160, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dataInsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 1010, Short.MAX_VALUE)))))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(dataInsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelarBtn))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(1214, 236));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void clearTable() {
        dataInsModel.setRowCount(0);
        dataInsModel.setColumnCount(0);
    }
    
    private void updateTable() {
        clearTable();
        table_name = dataInsComboBox.getSelectedItem().toString();
        db.populateDataInsTable(table_name, dataInsModel);
    }
    
    class ItemChangeListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                updateTable();
            }
        }
    }
    
    private void backToTableView() {
        TableView tableViewFrame = new TableView(db.getCon(), table_name);
        super.dispose();
        tableViewFrame.setVisible(true);
    }
    
    private void dataInsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataInsComboBoxActionPerformed

    }//GEN-LAST:event_dataInsComboBoxActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        // Enforcar el cursos en otra columna para registrar los cambios
        try {
            Robot robot = new Robot();
            dataInsTable.editCellAt(0, 0);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
        catch(AWTException e) {
            e.printStackTrace();
        }
        
        // Insertar fila en la base de datos
        db.insertInto(table_name, dataInsModel);
        backToTableView();
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        backToTableView();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JComboBox<String> dataInsComboBox;
    private javax.swing.JTable dataInsTable;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}