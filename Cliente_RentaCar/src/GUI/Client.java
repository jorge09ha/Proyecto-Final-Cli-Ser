package GUI;

import javax.swing.*;
import RentaCar.*;

public class Client extends javax.swing.JPanel {

    String search = "";

    public Client() {
        initComponents();
        iniciar();
    }

    public void iniciar() {
        btnmodificar.setVisible(false);
        btnborrar.setVisible(false);
    }

    public boolean camposVacios() {

        if ((txtid.equals("")) || (txtid.getText().equals("Ingrese la identificacion"))
                || (txtnombre.getText().equals("")) || (txtnombre.getText().equals("Ingrese el nombre"))
                || (txtapellido1.getText().equals("")) || (txtapellido1.getText().equals("Ingrese el apellido 1"))
                || (txtapellido2.getText().equals("")) || (txtapellido2.getText().equals("Ingrese el apellido 2"))
                || (txtemail.getText().equals("")) || (txtemail.getText().equals("Ingrese el correo electronico"))
                || (txttelefono.getText().equals("")) || (txttelefono.getText().equals("Ingrese el telefono"))) {
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
        txtnombre = new javax.swing.JTextField();
        Separator1 = new javax.swing.JSeparator();
        txt2 = new javax.swing.JLabel();
        txtapellido2 = new javax.swing.JTextField();
        Separator2 = new javax.swing.JSeparator();
        txt3 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        Separator3 = new javax.swing.JSeparator();
        txt4 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        Separator4 = new javax.swing.JSeparator();
        txt5 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        Separator5 = new javax.swing.JSeparator();
        SeparatorV = new javax.swing.JSeparator();
        btnRegistrar = new javax.swing.JButton();
        txtapellido1 = new javax.swing.JTextField();
        btnborrar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        setMinimumSize(new java.awt.Dimension(750, 460));
        setPreferredSize(new java.awt.Dimension(750, 430));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 215, -1, -1));

        Title.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        Title.setForeground(new java.awt.Color(0, 0, 0));
        Title.setText("CLIENTES");
        add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        txt1.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt1.setForeground(new java.awt.Color(0, 0, 0));
        txt1.setText("Nombre");
        add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 170, -1));

        txtnombre.setBackground(new java.awt.Color(255, 255, 255));
        txtnombre.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtnombre.setForeground(new java.awt.Color(102, 102, 102));
        txtnombre.setText("Ingrese el nombre");
        txtnombre.setBorder(null);
        txtnombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtnombreMousePressed(evt);
            }
        });
        add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 30));

        Separator1.setForeground(new java.awt.Color(0, 0, 0));
        Separator1.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 260, 10));

        txt2.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt2.setForeground(new java.awt.Color(0, 0, 0));
        txt2.setText("Apellidos");
        add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 170, -1));

        txtapellido2.setBackground(new java.awt.Color(255, 255, 255));
        txtapellido2.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtapellido2.setForeground(new java.awt.Color(102, 102, 102));
        txtapellido2.setText("Ingrese el apellido 2");
        txtapellido2.setBorder(null);
        txtapellido2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtapellido2MousePressed(evt);
            }
        });
        add(txtapellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 260, 30));

        Separator2.setForeground(new java.awt.Color(0, 0, 0));
        Separator2.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 670, -1));

        txt3.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt3.setForeground(new java.awt.Color(0, 0, 0));
        txt3.setText("Identificacion");
        add(txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 250, -1));

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
        add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 260, 30));

        Separator3.setForeground(new java.awt.Color(0, 0, 0));
        Separator3.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 260, -1));

        txt4.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt4.setForeground(new java.awt.Color(0, 0, 0));
        txt4.setText("Correo Electronico");
        add(txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 270, -1));

        txtemail.setBackground(new java.awt.Color(255, 255, 255));
        txtemail.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtemail.setForeground(new java.awt.Color(102, 102, 102));
        txtemail.setText("Ingrese el correo electronico");
        txtemail.setBorder(null);
        txtemail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtemailMousePressed(evt);
            }
        });
        add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 260, 30));

        Separator4.setForeground(new java.awt.Color(0, 0, 0));
        Separator4.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 260, -1));

        txt5.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt5.setForeground(new java.awt.Color(0, 0, 0));
        txt5.setText("Teléfono");
        add(txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 190, -1));

        txttelefono.setBackground(new java.awt.Color(255, 255, 255));
        txttelefono.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txttelefono.setForeground(new java.awt.Color(102, 102, 102));
        txttelefono.setText("Ingrese el telefono");
        txttelefono.setBorder(null);
        txttelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txttelefonoMousePressed(evt);
            }
        });
        txttelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttelefonoActionPerformed(evt);
            }
        });
        add(txttelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 260, 30));

        Separator5.setForeground(new java.awt.Color(0, 0, 0));
        Separator5.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 260, -1));

        SeparatorV.setForeground(new java.awt.Color(204, 204, 204));
        SeparatorV.setOrientation(javax.swing.SwingConstants.VERTICAL);
        SeparatorV.setPreferredSize(new java.awt.Dimension(200, 10));
        add(SeparatorV, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 10, 200));

        btnRegistrar.setBackground(new java.awt.Color(18, 90, 173));
        btnRegistrar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.setBorder(null);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 110, 30));

        txtapellido1.setBackground(new java.awt.Color(255, 255, 255));
        txtapellido1.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtapellido1.setForeground(new java.awt.Color(102, 102, 102));
        txtapellido1.setText("Ingrese el apellido 1");
        txtapellido1.setBorder(null);
        txtapellido1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtapellido1MousePressed(evt);
            }
        });
        add(txtapellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 260, 30));

        btnborrar.setBackground(new java.awt.Color(18, 90, 173));
        btnborrar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnborrar.setForeground(new java.awt.Color(255, 255, 255));
        btnborrar.setText("BORRAR");
        btnborrar.setBorder(null);
        btnborrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnborrarActionPerformed(evt);
            }
        });
        add(btnborrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 110, 30));

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

        btnmodificar.setBackground(new java.awt.Color(18, 90, 173));
        btnmodificar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnmodificar.setForeground(new java.awt.Color(255, 255, 255));
        btnmodificar.setText("MODIFICAR");
        btnmodificar.setBorder(null);
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });
        add(btnmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 110, 30));

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

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        try {
            if (camposVacios() == false) {
                int response = JOptionPane.showConfirmDialog(null, "Desea Registrar al usuario?", "Agregar Cliente", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    Cliente cli = new Cliente();
                    cli.setCedula(txtid.getText());
                    cli.setNombre(txtnombre.getText());
                    cli.setApellido1(txtapellido1.getText());
                    cli.setApellido2(txtapellido2.getText());
                    cli.setEmail(txtemail.getText());
                    cli.setTelefono(txttelefono.getText());
                    boolean resultado = ClientSocket.registrarcliente(cli);

                    if (resultado == false) {

                        txtnombre.setText("Ingrese el nombre");
                        txtapellido1.setText("Ingrese el apellido 1");
                        txtapellido2.setText("Ingrese el apellido 2");
                        txtid.setText("Ingrese la identificacion");
                        txtemail.setText("Ingrese el correo electronico");
                        txttelefono.setText("Ingrese el telefono");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Datos no Almacenados", "Info", 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hay campos en blanco.\n" + "Revise he intente nuevamente", "Campos en Blanco", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar. " + e + "", "Error", 0);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void txttelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txttelefonoMousePressed
        if ("Ingrese el telefono".equals(txttelefono.getText())) {
            txttelefono.setText("");
        }
    }//GEN-LAST:event_txttelefonoMousePressed

    private void txtnombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnombreMousePressed
        // TODO add your handling code here:
        if ("Ingrese el nombre".equals(txtnombre.getText())) {
            txtnombre.setText("");
        }
    }//GEN-LAST:event_txtnombreMousePressed

    private void txtapellido2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtapellido2MousePressed
        // TODO add your handling code here:
        if ("Ingrese el apellido 2".equals(txtapellido2.getText())) {
            txtapellido2.setText("");
        }
    }//GEN-LAST:event_txtapellido2MousePressed

    private void txtidMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtidMousePressed
        // TODO add your handling code here:
        if ("Ingrese la identificacion".equals(txtid.getText())) {
            txtid.setText("");
        }
    }//GEN-LAST:event_txtidMousePressed

    private void txtemailMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtemailMousePressed
        // TODO add your handling code here:
        if ("Ingrese el correo electronico".equals(txtemail.getText())) {
            txtemail.setText("");
        }
    }//GEN-LAST:event_txtemailMousePressed

    private void txtapellido1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtapellido1MousePressed
        // TODO add your handling code here:
        if ("Ingrese el apellido 1".equals(txtapellido1.getText())) {
            txtapellido1.setText("");
        }
    }//GEN-LAST:event_txtapellido1MousePressed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        try {
            Cliente cli = new Cliente();
            cli = ClientSocket.buscarcliente(txtid.getText());

            txtnombre.setText(cli.getNombre());
            txtapellido1.setText(cli.getApellido1());
            txtapellido2.setText(cli.getApellido2());
            txtid.setText(cli.getCedula());
            txtemail.setText(cli.getEmail());
            txttelefono.setText(cli.getTelefono());

            search = cli.getCedula();

            if (cli.getCedula() == null) {
                btnmodificar.setVisible(false);
                btnborrar.setVisible(false);
                btnRegistrar.setVisible(true);

                txtnombre.setText("Ingrese el nombre");
                txtapellido1.setText("Ingrese el apellido 1");
                txtapellido2.setText("Ingrese el apellido 2");
                txtid.setText("Ingrese la identificacion");
                txtemail.setText("Ingrese el correo electronico");
                txttelefono.setText("Ingrese el telefono");
            } else {
                btnmodificar.setVisible(true);
                btnborrar.setVisible(true);
                btnRegistrar.setVisible(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar. " + e + "", "Error", 0);
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        // TODO add your handling code here:
        boolean resultado = false;

        try {
            int response = JOptionPane.showConfirmDialog(null, "Desea modificar al cliente?", "Modificar Cliente", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                Cliente cli = new Cliente();
                cli.setCedula(txtid.getText());
                cli.setNombre(txtnombre.getText());
                cli.setApellido1(txtapellido1.getText());
                cli.setApellido2(txtapellido2.getText());
                cli.setEmail(txtemail.getText());
                cli.setTelefono(txttelefono.getText());

                resultado = ClientSocket.modificarcliente(cli, search);
            } else {
                JOptionPane.showMessageDialog(null, "Datos no modificados", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al modificar. " + e + "", "Error", 0);
        }
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btnborrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnborrarActionPerformed
        // TODO add your handling code here:
        try {
            int response = JOptionPane.showConfirmDialog(null, "Desea borrar al cliente?", "Borrar Cliente", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {

                ClientSocket.borrarcliente(txtid.getText());
                txtid.setText("Ingrese la identificacion");
                txtnombre.setText("Ingrese el nombre");
                txtapellido1.setText("Ingrese el apellido 1");
                txtapellido2.setText("Ingrese el apellido 2");
                txtemail.setText("Ingrese el correo electronico");
                txttelefono.setText("Ingrese el telefono");

                btnmodificar.setVisible(false);
                btnborrar.setVisible(false);
                btnRegistrar.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(null, "Datos no borrados", "Info", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al borrar. " + e + "", "Error", 0);
        }
    }//GEN-LAST:event_btnborrarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        // TODO add your handling code here:

        btnmodificar.setVisible(false);
        btnborrar.setVisible(false);
        btnRegistrar.setVisible(true);

        txtid.setText("Ingrese la identificacion");
        txtnombre.setText("Ingrese el nombre");
        txtapellido1.setText("Ingrese el apellido 1");
        txtapellido2.setText("Ingrese el apellido 2");
        txtemail.setText("Ingrese el correo electronico");
        txttelefono.setText("Ingrese el telefono");

    }//GEN-LAST:event_btncancelarActionPerformed

    private void txttelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator Separator1;
    private javax.swing.JSeparator Separator2;
    private javax.swing.JSeparator Separator3;
    private javax.swing.JSeparator Separator4;
    private javax.swing.JSeparator Separator5;
    private javax.swing.JSeparator SeparatorV;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel body;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnborrar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JLabel txt1;
    private javax.swing.JLabel txt2;
    private javax.swing.JLabel txt3;
    private javax.swing.JLabel txt4;
    private javax.swing.JLabel txt5;
    private javax.swing.JTextField txtapellido1;
    private javax.swing.JTextField txtapellido2;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
