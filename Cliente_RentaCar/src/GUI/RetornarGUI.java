package GUI;

import ClasesRentaCar.Auto;
import ClasesRentaCar.Cliente;
import ClasesRentaCar.Rentar;
import Conexion.ClienteHilo;
import Conexion.ClienteSocket;
import static Conexion.HomeSocket.homeToServer;
import static Conexion.ClienteSocket.clientToServer;
import static GUI.Home.autosrentados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * @author Jorge Hernandez Araya
 */
public class RetornarGUI extends javax.swing.JPanel {
    
    static String URL = "jdbc:mysql://localhost:3306/rentacar";
    static String USERNAME = "root";
    static String PASS = "admin01";
    
    static String mensaje = null;
    public Rentar rentSELECT = new Rentar();
    
    public RetornarGUI() {
        initComponents();
        presentarTableAuto();
        iniciar();
    }
//************************

    private void iniciar() {
        homeToServer("home", "");
        if (autosrentados.equals("0") || autosrentados == null) {
            estadoAUTOS.setText("NO HAY AUTOS RENTADOS");
            txtPlaca.setText("Ingrese la placa");
            txtPlaca.setVisible(false);
            btmActualizar.setVisible(true);
            btmRetornar.setVisible(false);
            btnCancelar.setVisible(false);
            btmBPlaca.setVisible(false);
        } else {
            estadoAUTOS.setText("AUTOS RENTADOS: " + autosrentados);
            txtPlaca.setText("Ingrese la placa");
            txtPlaca.setVisible(true);
             btmBPlaca.setVisible(true);
            btmActualizar.setVisible(true);
            btmRetornar.setVisible(false);
            btnCancelar.setVisible(false);
        }
    }
    
    public static Connection getConnection() {
        
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(URL, USERNAME, PASS);
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return conn;
    }
    
    public static ResultSet listRentados() {

        //ArrayList<Auto> autosList = new ArrayList<Auto>();
        Connection conn = getConnection();
        Auto aut = new Auto();
        Cliente cli = new Cliente();
        
        try {
            String sql = "SELECT * FROM rentados  ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            return rs;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 0);
        }
        return null;
        
    }
    //************************

    private void presentarTableAuto() {
        DefaultTableModel modelo = (DefaultTableModel) TableRentados.getModel();
        TableRentados.setModel(modelo);
        //TableModel modeloDatos = TableRentados.getModel();

        try {
            ResultSet rs = listRentados();
            ResultSetMetaData metaData = rs.getMetaData();

            // Names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = (metaData.getColumnCount()) - 1;
            
            columnNames.add("Placa");
            columnNames.add("Marca");
            columnNames.add("Modelo");
            columnNames.add("Año");
            columnNames.add("Transmision");
            columnNames.add("Cedula");
            columnNames.add("Nombre");
            columnNames.add("Apellido1");
            columnNames.add("Apellido2");
            columnNames.add("Correo");
            //columnNames.add("Telefono");

            // Data of the table
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }
            
            modelo.setDataVector(data, columnNames);
            //TableAuto.setEnabled(false);

        } catch (Exception e) {
            
        }
    }
    
    private void presentarTableRentarSelect(Rentar re) {
        try {
            DefaultTableModel modelo = (DefaultTableModel) TableRentados.getModel();
            modelo.setRowCount(0);
            
            int numFila = 0;
            modelo.insertRow(numFila, new Object[]{re.getPlaca(), re.getMarca(), re.getModelo(), re.getAnnio(), re.getTransmision(),
                re.getCedula(), re.getNombre(), re.getApellido1(), re.getApellido2(), re.getEmail(), re.getTelefono()});
        } catch (Exception e) {
            
        }
    }
    
    public boolean camposVacios() {
        
        if ((txtPlaca.equals("")) || (txtPlaca.getText().equals("Ingrese la placa"))
                || (txtPlaca.equals(null))) {
            return true;
        } else {
            return false;
        }
    }
//ventanas para mostrar la respuesta que envió el servidor

    public void ventanasMsjs() {
        System.out.println("ESTE ES EL BENDITO MESJE DE ENTRADA: " + mensaje);
        switch (mensaje) {
            
            case "correcto":
                JOptionPane.showMessageDialog(null, "Accion ejecutada de forma correcta.", "Info", 1);
                
                break;
            
            case "duplicado":
                JOptionPane.showMessageDialog(null, "La placa ya existe.", "Error", 0);
                break;
            
            case "no existe":
                JOptionPane.showMessageDialog(null, "No existe en la base de datos", "No existe", 1);
                break;
            
            case "id vacio":
                JOptionPane.showMessageDialog(null, "El campo de placa no puede estar vacio", "Campo vacio", 2);
                break;
            
            case "error base":
                JOptionPane.showMessageDialog(null, "Error al conectar la base de datos.", "Error", 1);
                break;
        }
    }
//distintos mensajes que envías el peor según la consulta

    public static void mensajes(String msg) {
        mensaje = msg;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        txt1 = new javax.swing.JLabel();
        Separator1 = new javax.swing.JSeparator();
        btmBPlaca = new javax.swing.JButton();
        btmRetornar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableRentados = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btmActualizar = new javax.swing.JButton();
        txtPlaca = new javax.swing.JTextField();
        estadoAUTOS = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setMinimumSize(new java.awt.Dimension(750, 430));
        setPreferredSize(new java.awt.Dimension(750, 430));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 215, -1, -1));

        Title.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        Title.setForeground(new java.awt.Color(0, 0, 0));
        Title.setText("RETORNAR AUTO");
        add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txt1.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt1.setForeground(new java.awt.Color(0, 0, 0));
        txt1.setText("Buscar por placa");
        add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        Separator1.setForeground(new java.awt.Color(0, 0, 0));
        Separator1.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 260, -1));

        btmBPlaca.setBackground(new java.awt.Color(18, 90, 173));
        btmBPlaca.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btmBPlaca.setForeground(new java.awt.Color(255, 255, 255));
        btmBPlaca.setText("SELECCIONAR");
        btmBPlaca.setBorder(null);
        btmBPlaca.setBorderPainted(false);
        btmBPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmBPlacaActionPerformed(evt);
            }
        });
        add(btmBPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 110, 30));

        btmRetornar.setBackground(new java.awt.Color(18, 90, 173));
        btmRetornar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btmRetornar.setForeground(new java.awt.Color(255, 255, 255));
        btmRetornar.setText("RETORNAR");
        btmRetornar.setBorder(null);
        btmRetornar.setBorderPainted(false);
        btmRetornar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmRetornarActionPerformed(evt);
            }
        });
        add(btmRetornar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 110, 30));

        TableRentados.setBackground(new java.awt.Color(204, 204, 204));
        TableRentados.setForeground(new java.awt.Color(0, 0, 0));
        TableRentados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Marca", "Modelo", "Año", "Transmision", "Cedula", "Nombre", "Apellido1", "Apellido2", "Correo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableRentados);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 750, 130));

        btnCancelar.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("CANCELAR");
        btnCancelar.setBorder(null);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 380, 110, 30));

        btmActualizar.setBackground(new java.awt.Color(18, 90, 173));
        btmActualizar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btmActualizar.setForeground(new java.awt.Color(255, 255, 255));
        btmActualizar.setText("ACTUALIZAR");
        btmActualizar.setBorder(null);
        btmActualizar.setBorderPainted(false);
        btmActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmActualizarActionPerformed(evt);
            }
        });
        add(btmActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 110, 30));

        txtPlaca.setBackground(new java.awt.Color(255, 255, 255));
        txtPlaca.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtPlaca.setForeground(new java.awt.Color(102, 102, 102));
        txtPlaca.setText("Ingrese la placa");
        txtPlaca.setBorder(null);
        txtPlaca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPlacaMousePressed(evt);
            }
        });
        txtPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlacaActionPerformed(evt);
            }
        });
        add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 260, 30));

        estadoAUTOS.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        estadoAUTOS.setForeground(new java.awt.Color(255, 0, 51));
        estadoAUTOS.setText("NO HAY AUTOS RENTADOS");
        add(estadoAUTOS, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btmBPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmBPlacaActionPerformed
        // TODO add your handling code here:
        try {
            if (camposVacios() == false) {
                
                rentSELECT.setPlaca(txtPlaca.getText());
                ClienteHilo.objetoaJsonRENTAR(rentSELECT);
                
                String task = "buscarRentar";
                
                rentSELECT = (Rentar) ClienteSocket.clientToServer(task, rentSELECT.getPlaca());
                rentSELECT = ClienteHilo.archivoJsonAObjetoRENTAR();
                
                rentSELECT = ClienteHilo.archivoJsonAObjetoRENTAR();///error null

                rentSELECT = rentSELECT;
                
                if ("correcto".equals(mensaje)) {
                    txtPlaca.setText(rentSELECT.getPlaca());
                    presentarTableRentarSelect(rentSELECT);
                    btmRetornar.setVisible(true);
                    btnCancelar.setVisible(true);
                    ventanasMsjs();
                } else {
                    txtPlaca.setText("Ingrese la placa");
                    ventanasMsjs();
                }
                
            } else {
                JOptionPane.showMessageDialog(null, "Hay campos en blanco.\n" + "Revise he intente nuevamente", "Campos en Blanco", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar. " + e + "", "Error", 0);
            
        }

    }//GEN-LAST:event_btmBPlacaActionPerformed

    private void btmRetornarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmRetornarActionPerformed
        // TODO add your handling code here:
        try {
            
            rentSELECT.setPlaca(txtPlaca.getText());
            ClienteHilo.objetoaJsonRENTAR(rentSELECT);
            
            String task = "retornar";
            
            rentSELECT = (Rentar) ClienteSocket.clientToServer(task, rentSELECT.getPlaca());
            rentSELECT = ClienteHilo.archivoJsonAObjetoRENTAR();
            
            System.out.println(rentSELECT.getPlaca());
            
            if ("correcto".equals(mensaje)) {
                txtPlaca.setText(rentSELECT.getPlaca());
                presentarTableRentarSelect(rentSELECT);
                ventanasMsjs();
            } else {
                txtPlaca.setText("Ingrese la placa");
                ventanasMsjs();
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar. " + e + "", "Error", 0);
            
        }
    }//GEN-LAST:event_btmRetornarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        btmRetornar.setVisible(false);
        btnCancelar.setVisible(false);
        txtPlaca.setText("Ingrese la placa");
        presentarTableAuto();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btmActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmActualizarActionPerformed
        // TODO add your handling code here:;
        try {
            presentarTableAuto();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btmActualizarActionPerformed

    private void txtPlacaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPlacaMousePressed
        // TODO add your handling code here:
        if ("Ingrese la placa".equals(txtPlaca.getText())) {
            txtPlaca.setText("");
        }
    }//GEN-LAST:event_txtPlacaMousePressed

    private void txtPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlacaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator Separator1;
    private javax.swing.JTable TableRentados;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel body;
    private javax.swing.JButton btmActualizar;
    private javax.swing.JButton btmBPlaca;
    private javax.swing.JButton btmRetornar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel estadoAUTOS;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel txt1;
    private javax.swing.JTextField txtPlaca;
    // End of variables declaration//GEN-END:variables
}
