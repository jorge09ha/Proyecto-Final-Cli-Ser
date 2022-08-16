package GUI;

import rentACar.Auto;
import javax.swing.*;
import static Conexion.ClienteSocket.clientToServer;

public class Autos extends javax.swing.JPanel {

    public Autos() {
        initComponents();
        iniciar();
    }

    public void iniciar() {
        btnmodificar.setVisible(false);
        btnborrar.setVisible(false);
        cbAnnio.setSelectedItem(null);
        cbTransm.setSelectedItem(null);
    }

    public boolean camposVacios() {

        if ((txtid.equals("")) || (txtid.getText().equals("Ingrese la placa"))
                || (txtMarca.getText().equals("")) || (txtMarca.getText().equals("Ingrese la marca"))
                || (txtModelo.getText().equals("")) || (txtModelo.getText().equals("Ingrese el modelo"))
                || (cbAnnio.getSelectedItem() == null) || (cbTransm.getSelectedItem() == null)) {
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
        btnborrar = new javax.swing.JButton();
        btmAgregar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        txt2 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        Separator2 = new javax.swing.JSeparator();
        txt4 = new javax.swing.JLabel();
        txtModelo = new javax.swing.JTextField();
        Separator3 = new javax.swing.JSeparator();
        txt5 = new javax.swing.JLabel();
        txt6 = new javax.swing.JLabel();
        cbAnnio = new javax.swing.JComboBox<>();
        cbTransm = new javax.swing.JComboBox<>();
        btnbuscar = new javax.swing.JButton();
        txt3 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        Separator6 = new javax.swing.JSeparator();
        btncancelar = new javax.swing.JButton();

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
        Title.setText("AGREGAR AUTO");
        add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnborrar.setBackground(new java.awt.Color(18, 90, 173));
        btnborrar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnborrar.setForeground(new java.awt.Color(255, 255, 255));
        btnborrar.setText("BORRAR");
        btnborrar.setBorder(null);
        btnborrar.setBorderPainted(false);
        btnborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnborrarActionPerformed(evt);
            }
        });
        add(btnborrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 100, 30));

        btmAgregar.setBackground(new java.awt.Color(18, 90, 173));
        btmAgregar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btmAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btmAgregar.setText("AGREGAR");
        btmAgregar.setBorder(null);
        btmAgregar.setBorderPainted(false);
        btmAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmAgregarActionPerformed(evt);
            }
        });
        add(btmAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 110, 30));

        btnmodificar.setBackground(new java.awt.Color(18, 90, 173));
        btnmodificar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnmodificar.setForeground(new java.awt.Color(255, 255, 255));
        btnmodificar.setText("MODIFICAR");
        btnmodificar.setBorder(null);
        btnmodificar.setBorderPainted(false);
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });
        add(btnmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 100, 30));

        txt2.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt2.setForeground(new java.awt.Color(0, 0, 0));
        txt2.setText("Marca");
        add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 100, -1));

        txtMarca.setBackground(new java.awt.Color(255, 255, 255));
        txtMarca.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtMarca.setForeground(new java.awt.Color(102, 102, 102));
        txtMarca.setText("Ingrese la marca");
        txtMarca.setBorder(null);
        txtMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtMarcaMousePressed(evt);
            }
        });
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });
        add(txtMarca, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 260, 30));

        Separator2.setForeground(new java.awt.Color(0, 0, 0));
        Separator2.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 260, 10));

        txt4.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt4.setForeground(new java.awt.Color(0, 0, 0));
        txt4.setText("Modelo");
        add(txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 150, -1));

        txtModelo.setBackground(new java.awt.Color(255, 255, 255));
        txtModelo.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtModelo.setForeground(new java.awt.Color(102, 102, 102));
        txtModelo.setText("Ingrese el modelo");
        txtModelo.setBorder(null);
        txtModelo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtModeloMousePressed(evt);
            }
        });
        txtModelo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtModeloActionPerformed(evt);
            }
        });
        add(txtModelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 260, 30));

        Separator3.setForeground(new java.awt.Color(0, 0, 0));
        Separator3.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 260, 10));

        txt5.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt5.setForeground(new java.awt.Color(0, 0, 0));
        txt5.setText("Año");
        add(txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 140, -1));

        txt6.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt6.setForeground(new java.awt.Color(0, 0, 0));
        txt6.setText("Transmisión");
        add(txt6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 180, -1));

        cbAnnio.setBackground(new java.awt.Color(255, 255, 255));
        cbAnnio.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbAnnio.setForeground(new java.awt.Color(0, 0, 0));
        cbAnnio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2022", "2021", "2020", "2019", "2018", "2017", "2016" }));
        add(cbAnnio, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 270, 30));

        cbTransm.setBackground(new java.awt.Color(255, 255, 255));
        cbTransm.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        cbTransm.setForeground(new java.awt.Color(0, 0, 0));
        cbTransm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "manual", "automatico" }));
        add(cbTransm, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 270, 30));

        btnbuscar.setBackground(new java.awt.Color(18, 90, 173));
        btnbuscar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnbuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscar.setText("BUSCAR");
        btnbuscar.setBorder(null);
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        add(btnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 110, 30));

        txt3.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt3.setForeground(new java.awt.Color(0, 0, 0));
        txt3.setText("Placa");
        add(txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 90, -1));

        txtid.setBackground(new java.awt.Color(255, 255, 255));
        txtid.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtid.setForeground(new java.awt.Color(102, 102, 102));
        txtid.setText("Ingrese la placa");
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
        add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 260, 30));

        Separator6.setForeground(new java.awt.Color(0, 0, 0));
        Separator6.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 670, -1));

        btncancelar.setBackground(new java.awt.Color(255, 102, 102));
        btncancelar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btncancelar.setForeground(new java.awt.Color(255, 255, 255));
        btncancelar.setText("CANCELAR");
        btncancelar.setBorder(null);
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        add(btncancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 110, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnborrarActionPerformed
        // TODO add your handling code here:
        try {
            int response = JOptionPane.showConfirmDialog(null, "Desea borrar el auto?", "Borrar Auto", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {

                //ClientSocket.borrarAuto(txtid.getText());
                txtid.setText("Ingrese la placa");
                txtMarca.setText("Ingrese la marca");
                txtModelo.setText("Ingrese el modelo");
                cbAnnio.setSelectedItem(null);
                cbTransm.setSelectedItem(null);

                btnmodificar.setVisible(false);
                btnborrar.setVisible(false);
                btmAgregar.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Datos no borrados", "Info", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al borrar. " + e + "", "Error", 0);
        }
    }//GEN-LAST:event_btnborrarActionPerformed

    private void btmAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmAgregarActionPerformed
        // TODO add your handling code here:
        try {
            if (camposVacios() == false) {
                int response = JOptionPane.showConfirmDialog(null, "Desea Registrar el auto?", "Agregar Auto", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    Auto aut = new Auto();
                    aut.setPlaca(txtid.getText());
                    aut.setMarca(txtMarca.getText());
                    aut.setModelo(txtModelo.getText());
                    aut.setAnnio(cbAnnio.getSelectedItem().toString());
                    aut.setTransmision(cbTransm.getSelectedItem().toString());
                    aut.setRentar("D");
                   // ClientSocket.registrarAuto(aut);

                } else {
                    JOptionPane.showMessageDialog(null, "Datos no Almacenados", "Info", 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hay campos en blanco.\n" + "Revise he intente nuevamente", "Campos en Blanco", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar. " + e + "", "Error", 0);
        }
    }//GEN-LAST:event_btmAgregarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        // TODO add your handling code here:
        try {
            int response = JOptionPane.showConfirmDialog(null, "Desea modificar el auto?", "Modificar Cliente", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                Auto aut = new Auto();
                aut.setPlaca(txtid.getText());
                aut.setMarca(txtMarca.getText());
                aut.setModelo(txtModelo.getText());
                aut.setAnnio(cbAnnio.getSelectedItem().toString());
                aut.setTransmision(cbTransm.getSelectedItem().toString());
                aut.setRentar("D");

               // ClientSocket.modificarAuto(aut);
            } else {
                JOptionPane.showMessageDialog(null, "Datos no modificados", "Info", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar. " + e + "", "Error", 0);
        }
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void txtMarcaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMarcaMousePressed
        // TODO add your handling code here:
        if ("Ingrese la marca".equals(txtMarca.getText())) {
            txtMarca.setText("");
        }
    }//GEN-LAST:event_txtMarcaMousePressed

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    private void txtModeloMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtModeloMousePressed
        // TODO add your handling code here:
        if ("Ingrese el modelo".equals(txtModelo.getText())) {
            txtModelo.setText("");
        }
    }//GEN-LAST:event_txtModeloMousePressed

    private void txtModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModeloActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        try {

            int response = JOptionPane.showConfirmDialog(null, "Desea Buscar al cliente?\nCedula: " + txtid.getText(), "Buscar Cliente", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {

                Auto aut = new Auto();

                aut.setPlaca(txtid.getText());
                //objetoaJson(aut);

                String task = "buscarcliente";

                aut = (Auto) clientToServer(task, aut.getPlaca());

                txtid.setText(aut.getPlaca());
                txtMarca.setText(aut.getMarca());
                txtModelo.setText(aut.getModelo());
                txtMarca.setText(aut.getMarca());
                cbAnnio.setSelectedItem(aut.getAnnio());
                cbTransm.setSelectedItem(aut.getTransmision());

                if (aut == null) {
                    btnmodificar.setVisible(false);
                    btnborrar.setVisible(false);
                    btmAgregar.setVisible(true);

                    txtid.setText("Ingrese la placa");
                    txtMarca.setText("Ingrese la marca");
                    txtModelo.setText("Ingrese el modelo");
                    cbAnnio.setSelectedItem(null);
                    cbTransm.setSelectedItem(null);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Datos no Almacenados", "Info", 1);
                btnmodificar.setVisible(true);
                btnborrar.setVisible(true);
                btmAgregar.setVisible(false);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar. " + e + "", "Error", 0);

        }

    }//GEN-LAST:event_btnbuscarActionPerformed

    private void txtidMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtidMousePressed
        // TODO add your handling code here:
        if ("Ingrese la placa".equals(txtid.getText())) {
            txtid.setText("");
        }
    }//GEN-LAST:event_txtidMousePressed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        // TODO add your handling code here:

        btnmodificar.setVisible(false);
        btnborrar.setVisible(false);
        btmAgregar.setVisible(true);

        txtid.setText("Ingrese la placa");
        txtMarca.setText("Ingrese la marca");
        txtModelo.setText("Ingrese el modelo");
        cbAnnio.setSelectedItem(null);
        cbTransm.setSelectedItem(null);

    }//GEN-LAST:event_btncancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator Separator2;
    private javax.swing.JSeparator Separator3;
    private javax.swing.JSeparator Separator6;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel body;
    private javax.swing.JButton btmAgregar;
    private javax.swing.JButton btnborrar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JComboBox<String> cbAnnio;
    private javax.swing.JComboBox<String> cbTransm;
    private javax.swing.JLabel txt2;
    private javax.swing.JLabel txt3;
    private javax.swing.JLabel txt4;
    private javax.swing.JLabel txt5;
    private javax.swing.JLabel txt6;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtid;
    // End of variables declaration//GEN-END:variables
}
