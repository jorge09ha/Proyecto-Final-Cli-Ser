package GUI;

import java.util.*;
import RentaCar.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Rentar extends javax.swing.JPanel {

    public Rentar() {
        initComponents();
        presentarTableAuto();
    }

    private void presentarTableCliente() {
        DefaultTableModel modelo = (DefaultTableModel) TableCliente.getModel();
        modelo.setRowCount(0);

        Cliente cli = new Cliente();
        cli = ClientSocket.buscarcliente(txtid.getText());

        int numFila = 0;
        modelo.insertRow(numFila, new Object[]{cli.getNombre(), cli.getApellido1(), cli.getApellido2(), cli.getEmail(), cli.getTelefono()});
    }

    private void presentarTableAuto() {
        DefaultTableModel modelo = (DefaultTableModel) TableAuto.getModel();

        TableAuto.setModel(modelo);

        TableModel modeloDatos = TableAuto.getModel();

        try {
            ResultSet rs = ClientSocket.listEstados("D");

            ResultSetMetaData metaData = rs.getMetaData();

            // Names of columns
            Vector<String> columnNames = new Vector<String>();
            int columnCount = (metaData.getColumnCount()) - 1;

            columnNames.add("Placa");
            columnNames.add("Marca");
            columnNames.add("Modelo");
            columnNames.add("Año");
            columnNames.add("Transmision");

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

    private void showAuto() {
        DefaultTableModel modelo = (DefaultTableModel) TableAuto.getModel();
        modelo.setRowCount(0);

        Auto aut = new Auto();
        aut = ClientSocket.buscarAuto(txtPlaca.getText());

        int numFila = 0;
        modelo.insertRow(numFila, new Object[]{aut.getPlaca(), aut.getMarca(), aut.getModelo(), aut.getAnnio(), aut.getTransmision(), aut.getRentar()});

    }

    public boolean camposVacios() {

        if ((txtid.equals("")) || (txtid.getText().equals("Ingrese la identificacion"))
                || (txtPlaca.getText().equals("")) || (txtPlaca.getText().equals("Ingrese la placa"))) {
            return true;
        } else {
            return false;
        }
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
        txt3 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btmRentar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableAuto = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        TableCliente = new javax.swing.JTable();
        btmActualizar = new javax.swing.JButton();
        txtid = new javax.swing.JTextField();
        txtPlaca = new javax.swing.JTextField();
        btnBucarPlaca = new javax.swing.JButton();

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
        Title.setText("RENTAR AUTO");
        add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        txt1.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt1.setForeground(new java.awt.Color(0, 0, 0));
        txt1.setText("Cliente");
        add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));

        txt3.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt3.setForeground(new java.awt.Color(0, 0, 0));
        txt3.setText("Elegir Auto");
        add(txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, -1, -1));

        btnBuscar.setBackground(new java.awt.Color(18, 90, 173));
        btnBuscar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("BUSCAR");
        btnBuscar.setBorder(null);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, 100, 40));

        btmRentar.setBackground(new java.awt.Color(18, 90, 173));
        btmRentar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btmRentar.setForeground(new java.awt.Color(255, 255, 255));
        btmRentar.setText("RENTAR");
        btmRentar.setBorder(null);
        btmRentar.setBorderPainted(false);
        btmRentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmRentarActionPerformed(evt);
            }
        });
        add(btmRentar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 390, 100, 40));

        TableAuto.setBackground(new java.awt.Color(255, 255, 255));
        TableAuto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Marca", "Modelo", "Año", "Transmision"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableAuto.setEnabled(false);
        TableAuto.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(TableAuto);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 700, 110));

        jScrollPane3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N

        TableCliente.setBackground(new java.awt.Color(255, 255, 255));
        TableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido 1", "Apellido 2", "Email", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableCliente.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(TableCliente);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 700, 50));

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
        add(btmActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 100, 40));

        txtid.setBackground(new java.awt.Color(255, 255, 255));
        txtid.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtid.setForeground(new java.awt.Color(102, 102, 102));
        txtid.setText("Ingrese la identificacion");
        txtid.setBorder(null);
        txtid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtidMousePressed(evt);
            }
        });
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });
        add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 260, 30));

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
        add(txtPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 260, 30));

        btnBucarPlaca.setBackground(new java.awt.Color(18, 90, 173));
        btnBucarPlaca.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBucarPlaca.setForeground(new java.awt.Color(255, 255, 255));
        btnBucarPlaca.setText("BUSCAR");
        btnBucarPlaca.setBorder(null);
        btnBucarPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBucarPlacaActionPerformed(evt);
            }
        });
        add(btnBucarPlaca, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 100, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        try {
            Cliente cli = new Cliente();
            cli = ClientSocket.buscarcliente(txtid.getText());

            if (cli.getCedula() == null) {
                JOptionPane.showMessageDialog(null, "El usuario no existe.\n" + "Puede agregarlos desde la sección clientes", "Info", 1);
            } else {
                presentarTableCliente();
            }

        } catch (Exception e) {

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btmActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmActualizarActionPerformed
        // TODO add your handling code here:;

        try {

            presentarTableAuto();

        } catch (Exception e) {
        }

    }//GEN-LAST:event_btmActualizarActionPerformed

    private void txtidMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtidMousePressed
        // TODO add your handling code here:
        if ("Ingrese la identificacion".equals(txtid.getText())) {
            txtid.setText("");
        }
    }//GEN-LAST:event_txtidMousePressed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void btmRentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmRentarActionPerformed
        // TODO add your handling code here:
        try {
            if (camposVacios() == false) {
                int response = JOptionPane.showConfirmDialog(null, "El cliente cedula: " + txtid.getText()
                        + " rentara el auto placa: " + txtPlaca.getText() + "\n" + "¿Desea continuar?",
                        "Agregar Auto", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {

                    Cliente cli = new Cliente();
                    cli = ClientSocket.buscarcliente(txtid.getText());

                    Auto aut = new Auto();
                    aut = ClientSocket.verEstados(txtPlaca.getText(), "D");

                    if (aut != null && cli != null) {
                        ClientSocket.addRentados(aut, cli);
                    } else {
                        JOptionPane.showMessageDialog(null, "HAY UN ERROR", "ERROR", 0);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Datos no Almacenados", "Info", 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hay campos en blanco.\n" + "Revise he intente nuevamente", "Campos en Blanco", 1);
            }
        } catch (Exception e) {

        }


    }//GEN-LAST:event_btmRentarActionPerformed

    private void txtPlacaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPlacaMousePressed
        if ("Ingrese la placa".equals(txtPlaca.getText())) {
            txtPlaca.setText("");
        }
    }//GEN-LAST:event_txtPlacaMousePressed

    private void txtPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlacaActionPerformed

    private void btnBucarPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBucarPlacaActionPerformed
        // TODO add your handling code here:
        try {
            Auto aut = new Auto();
            aut = ClientSocket.verEstados(txtPlaca.getText(), "D");

            if (aut.getPlaca() == null) {
                presentarTableAuto();
            } else {
                showAuto();
            }

        } catch (Exception e) {

        }

    }//GEN-LAST:event_btnBucarPlacaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableAuto;
    private javax.swing.JTable TableCliente;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel body;
    private javax.swing.JButton btmActualizar;
    private javax.swing.JButton btmRentar;
    private javax.swing.JButton btnBucarPlaca;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel txt1;
    private javax.swing.JLabel txt3;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}