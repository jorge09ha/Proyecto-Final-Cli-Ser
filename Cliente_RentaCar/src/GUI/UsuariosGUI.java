package GUI;

/**
 * @author Jorge Hernandez Araya
 */
import Conexion.ClienteHilo;
import Conexion.ClienteSocket;
import ClasesRentaCar.UserAdmin;
import javax.swing.*;
import ClasesRentaCar.Cliente;

public class UsuariosGUI extends javax.swing.JPanel {

    static String mensaje = null;

    public UsuariosGUI() {
        initComponents();
        iniciar();
    }
//iniciar botones de modificar eliminar invisibles

    public void iniciar() {
        btnmodificar.setVisible(false);
        btnborrar.setVisible(false);
    }
//Indica los campos vacíos

    public boolean camposVacios() {

        if ((txtid.equals("")) || (txtid.getText().equals("Ingrese la identificacion"))
                || (txtnombre.getText().equals("")) || (txtnombre.getText().equals("Ingrese el nombre"))
                || (txtapellido1.getText().equals("")) || (txtapellido1.getText().equals("Ingrese el apellido 1"))
                || (txtapellido2.getText().equals("")) || (txtapellido2.getText().equals("Ingrese el apellido 2"))
                || (txtUserName.getText().equals("")) || (txtUserName.getText().equals("Ingrese el nombre de usuario"))
                || (txtPass1.getText().equals("")) || (txtPass1.getText().equals("contraseña"))
                || (txtPass2.getText().equals("")) || (txtPass2.getText().equals("contraseña"))) {

            return true;
        } else {
            return false;
        }
    }
//ventanas para mostrar la respuesta que envió el servidor

    public void ventanasMsjs() {

        switch (mensaje) {

            case "correcto":
                JOptionPane.showMessageDialog(null, "Accion ejecutada de forma correcta.", "Info", 1);

                break;

            case "duplicado":
                JOptionPane.showMessageDialog(null, "La cédula ya existe.", "Error", 0);
                break;

            case "no existe":
                JOptionPane.showMessageDialog(null, "No existe en la base de datos", "No existe", 1);
                break;

            case "id vacio":
                JOptionPane.showMessageDialog(null, "El campo de identificación no puede estar vacio", "Campo vacio", 2);
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
        btnborrar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        Separator2 = new javax.swing.JSeparator();
        Separator1 = new javax.swing.JSeparator();
        Separator3 = new javax.swing.JSeparator();
        SeparatorV = new javax.swing.JSeparator();
        Separator4 = new javax.swing.JSeparator();
        Separator5 = new javax.swing.JSeparator();
        txt3 = new javax.swing.JLabel();
        txt1 = new javax.swing.JLabel();
        txt2 = new javax.swing.JLabel();
        txt4 = new javax.swing.JLabel();
        txt5 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtapellido1 = new javax.swing.JTextField();
        txtapellido2 = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPass1 = new javax.swing.JPasswordField();
        txtPass2 = new javax.swing.JPasswordField();
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
        Title.setText("USUARIOS ADMINISTRADORES");
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
        add(btnborrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 90, 30));

        btnAgregar.setBackground(new java.awt.Color(18, 90, 173));
        btnAgregar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setText("AGREGAR");
        btnAgregar.setBorder(null);
        btnAgregar.setBorderPainted(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 110, 30));

        btnBuscar.setBackground(new java.awt.Color(18, 90, 173));
        btnBuscar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setText("BUSCAR");
        btnBuscar.setBorder(null);
        btnBuscar.setBorderPainted(false);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 100, 30));

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
        add(btnmodificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 390, 100, 30));

        Separator2.setForeground(new java.awt.Color(0, 0, 0));
        Separator2.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 670, -1));

        Separator1.setForeground(new java.awt.Color(0, 0, 0));
        Separator1.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 260, 10));

        Separator3.setForeground(new java.awt.Color(0, 0, 0));
        Separator3.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 260, -1));

        SeparatorV.setForeground(new java.awt.Color(204, 204, 204));
        SeparatorV.setOrientation(javax.swing.SwingConstants.VERTICAL);
        SeparatorV.setPreferredSize(new java.awt.Dimension(200, 10));
        add(SeparatorV, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 10, 200));

        Separator4.setForeground(new java.awt.Color(0, 0, 0));
        Separator4.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 260, -1));

        Separator5.setForeground(new java.awt.Color(0, 0, 0));
        Separator5.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 260, -1));

        txt3.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt3.setForeground(new java.awt.Color(0, 0, 0));
        txt3.setText("Identificacion");
        add(txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        txt1.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt1.setForeground(new java.awt.Color(0, 0, 0));
        txt1.setText("Nombre");
        add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 190, -1));

        txt2.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt2.setForeground(new java.awt.Color(0, 0, 0));
        txt2.setText("Apellidos");
        add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 180, -1));

        txt4.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt4.setForeground(new java.awt.Color(0, 0, 0));
        txt4.setText("Nombre de Usuario");
        add(txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, 250, -1));

        txt5.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt5.setForeground(new java.awt.Color(0, 0, 0));
        txt5.setText("Contraseña");
        add(txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, 220, -1));

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
        add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 260, 30));

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
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });
        add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 260, 30));

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
        txtapellido1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellido1ActionPerformed(evt);
            }
        });
        add(txtapellido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 260, 30));

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
        txtapellido2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtapellido2ActionPerformed(evt);
            }
        });
        add(txtapellido2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 260, 30));

        txtUserName.setBackground(new java.awt.Color(255, 255, 255));
        txtUserName.setFont(new java.awt.Font("Roboto Light", 0, 14)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(102, 102, 102));
        txtUserName.setText("Ingrese el nombre de usuario");
        txtUserName.setBorder(null);
        txtUserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtUserNameMousePressed(evt);
            }
        });
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });
        add(txtUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, 260, 30));

        jPanel1.setBackground(new java.awt.Color(18, 90, 173));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconUSER.png"))); // NOI18N
        jPanel1.add(jLabel1);

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 40, 40));

        txtPass1.setBackground(new java.awt.Color(255, 255, 255));
        txtPass1.setForeground(new java.awt.Color(0, 0, 0));
        txtPass1.setText("contraseña");
        txtPass1.setBorder(null);
        txtPass1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPass1MousePressed(evt);
            }
        });
        add(txtPass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 260, 30));

        txtPass2.setBackground(new java.awt.Color(255, 255, 255));
        txtPass2.setForeground(new java.awt.Color(0, 0, 0));
        txtPass2.setText("contraseña");
        txtPass2.setBorder(null);
        txtPass2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPass2MousePressed(evt);
            }
        });
        txtPass2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPass2ActionPerformed(evt);
            }
        });
        add(txtPass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, 260, 30));

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

            int response = JOptionPane.showConfirmDialog(null, "Desea eliminar el usuario?", "Eliminar Usuario", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                UserAdmin usu = new UserAdmin();

                usu.setCedula(txtid.getText());
                ClienteHilo.objetoaJsonUSER(usu);

                String task = "eliminarUsuario";

                usu = (UserAdmin) ClienteSocket.clientToServer(task, usu.getCedula());

                usu = ClienteHilo.archivoJsonAObjetoUSER();///error null

                if ("correcto".equals(usu.getNombre())) {

                    txtnombre.setText("Ingrese el nombre");
                    txtapellido1.setText("Ingrese el apellido 1");
                    txtapellido2.setText("Ingrese el apellido 2");
                    txtid.setText("Ingrese la identificacion");
                    txtUserName.setText("Ingrese el nombre de usuario");
                    txtPass1.setText("contrasena");
                    txtPass2.setText("contrasena");

                    btnmodificar.setVisible(false);
                    btnborrar.setVisible(false);
                    btnAgregar.setVisible(true);
                    ventanasMsjs(); /////ventanas

                } else {
                    txtnombre.setText("Ingrese el nombre");
                    txtapellido1.setText("Ingrese el apellido 1");
                    txtapellido2.setText("Ingrese el apellido 2");
                    txtid.setText("Ingrese la identificacion");
                    txtUserName.setText("Ingrese el nombre de usuario");
                    txtPass1.setText("contrasena");
                    txtPass2.setText("contrasena");

                    btnmodificar.setVisible(false);
                    btnborrar.setVisible(false);
                    btnAgregar.setVisible(true);
                    ventanasMsjs(); /////ventanas
                }
            } else {
                JOptionPane.showMessageDialog(null, "Datos no Borrados", "Info", 1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Eliminar. " + e + "", "Error", 0);

        }
    }//GEN-LAST:event_btnborrarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        UserAdmin usu = new UserAdmin();
        try {

            if (camposVacios() == false) {

                int response = JOptionPane.showConfirmDialog(null, "Desea registrar al usuario?", "Agregar Cliente", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {

                    if (txtPass1.getText().equals(txtPass2.getText())) {

                        usu.setCedula(txtid.getText());
                        usu.setNombre(txtnombre.getText());
                        usu.setApellido1(txtapellido1.getText());
                        usu.setApellido2(txtapellido2.getText());
                        usu.setUser(txtUserName.getText());
                        usu.setPass(txtPass1.getText());

                        ClienteHilo.objetoaJsonUSER(usu);
                        String task = "registrarUsuario";
                        usu = (UserAdmin) ClienteSocket.clientToServer(task, usu.getCedula());

                        usu = ClienteHilo.archivoJsonAObjetoUSER();///error null

                        if ("correcto".equals(usu.getNombre())) { // se evalua si la propiedad nombre tiene como msg "correcto"

                            txtnombre.setText("Ingrese el nombre");
                            txtapellido1.setText("Ingrese el apellido 1");
                            txtapellido2.setText("Ingrese el apellido 2");
                            txtid.setText("Ingrese la identificacion");
                            txtUserName.setText("Ingrese el nombre de usuario");
                            txtPass1.setText("contrasena");
                            txtPass2.setText("contrasena");

                            btnmodificar.setVisible(false);
                            btnborrar.setVisible(false);
                            btnAgregar.setVisible(true);
                            ventanasMsjs(); /////ventanas
                        } else {
                            txtnombre.setText("Ingrese el nombre");
                            txtapellido1.setText("Ingrese el apellido 1");
                            txtapellido2.setText("Ingrese el apellido 2");
                            txtid.setText("Ingrese la identificacion");
                            txtUserName.setText("Ingrese el nombre de usuario");
                            txtPass1.setText("contrasena");
                            txtPass2.setText("contrasena");

                            btnmodificar.setVisible(false);
                            btnborrar.setVisible(false);
                            btnAgregar.setVisible(true);
                            ventanasMsjs(); /////ventanas
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Las contraseñas no son iguales", "Info", 1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Datos no Almacenados", "Info", 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hay campos en blanco.\n" + "Revise he intente nuevamente", "Campos en Blanco", 1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al agregar. " + e + "", "Error", 0);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        try {

            UserAdmin usu = new UserAdmin();

            usu.setCedula(txtid.getText());
            ClienteHilo.objetoaJsonUSER(usu);

            String task = "buscarUsuario";

            usu = (UserAdmin) ClienteSocket.clientToServer(task, usu.getCedula());
            usu = ClienteHilo.archivoJsonAObjetoUSER();

            usu = ClienteHilo.archivoJsonAObjetoUSER();///error null

            if ("correcto".equals(mensaje)) {

                txtid.setText(usu.getCedula());
                txtnombre.setText(usu.getNombre());
                txtapellido1.setText(usu.getApellido1());
                txtapellido2.setText(usu.getApellido2());
                txtUserName.setText(usu.getUser());
                txtPass1.setText(usu.getPass());
                txtPass2.setText(usu.getPass());

                btnmodificar.setVisible(true);
                btnborrar.setVisible(true);
                btnAgregar.setVisible(false);
                ventanasMsjs();
            } else {

                txtnombre.setText("Ingrese el nombre");
                txtapellido1.setText("Ingrese el apellido 1");
                txtapellido2.setText("Ingrese el apellido 2");
                txtid.setText("Ingrese la identificacion");
                txtUserName.setText("Ingrese el nombre de usuario");
                txtPass2.setText("contraseña");
                txtPass1.setText("contraseña");
                ventanasMsjs();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar. " + e + "", "Error", 0);

        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        // TODO add your handling code here:

        int response = JOptionPane.showConfirmDialog(null, "Desea modificar al usuario?", "Modificar Usuario", JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {

            if (txtPass1.getText().equals(txtPass2.getText())) {

                UserAdmin usu = new UserAdmin();

                usu.setCedula(txtid.getText());
                usu.setNombre(txtnombre.getText());
                usu.setApellido1(txtapellido1.getText());
                usu.setApellido2(txtapellido2.getText());
                usu.setUser(txtUserName.getText());
                usu.setPass(txtPass1.getText());

                ClienteHilo.objetoaJsonUSER(usu);
                String task = "modificarUsuario";
                usu = (UserAdmin) ClienteSocket.clientToServer(task, usu.getCedula());

                usu = ClienteHilo.archivoJsonAObjetoUSER();///error null

                if ("correcto".equals(mensaje)) {
                    txtnombre.setText("Ingrese el nombre");
                    txtapellido1.setText("Ingrese el apellido 1");
                    txtapellido2.setText("Ingrese el apellido 2");
                    txtid.setText("Ingrese la identificacion");
                    txtUserName.setText("Ingrese el nombre de usuario");
                    txtPass2.setText("contraseña");
                    txtPass1.setText("contraseña");
                    btnmodificar.setVisible(false);
                    btnborrar.setVisible(false);
                    btnAgregar.setVisible(true);
                    ventanasMsjs(); /////ventanas
                } else {

                    txtnombre.setText("Ingrese el nombre");
                    txtapellido1.setText("Ingrese el apellido 1");
                    txtapellido2.setText("Ingrese el apellido 2");
                    txtid.setText("Ingrese la identificacion");
                    txtUserName.setText("Ingrese el nombre de usuario");
                    txtPass2.setText("contraseña");
                    txtPass1.setText("contraseña");
                    btnmodificar.setVisible(false);
                    btnborrar.setVisible(false);
                    btnAgregar.setVisible(true);
                    ventanasMsjs(); /////ventanas
                }

            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinsiden.\n" + "Revise he intente nuevamente", "Error", 0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Datos no modificados", "Info", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnmodificarActionPerformed

    private void txtidMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtidMousePressed
        // TODO add your handling code here:
        if ("Ingrese la identificacion".equals(txtid.getText())) {
            txtid.setText("");
        }
    }//GEN-LAST:event_txtidMousePressed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtnombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtnombreMousePressed
        // TODO add your handling code here:
        if ("Ingrese el nombre".equals(txtnombre.getText())) {
            txtnombre.setText("");
        }
    }//GEN-LAST:event_txtnombreMousePressed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void txtapellido1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtapellido1MousePressed
        // TODO add your handling code here:
        if ("Ingrese el apellido 1".equals(txtapellido1.getText())) {
            txtapellido1.setText("");
        }
    }//GEN-LAST:event_txtapellido1MousePressed

    private void txtapellido1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellido1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtapellido1ActionPerformed

    private void txtapellido2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtapellido2MousePressed
        // TODO add your handling code here:
        if ("Ingrese el apellido 2".equals(txtapellido2.getText())) {
            txtapellido2.setText("");
        }
    }//GEN-LAST:event_txtapellido2MousePressed

    private void txtapellido2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtapellido2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtapellido2ActionPerformed

    private void txtUserNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserNameMousePressed
        // TODO add your handling code here:
        if ("Ingrese el nombre de usuario".equals(txtUserName.getText())) {
            txtUserName.setText("");
        }
    }//GEN-LAST:event_txtUserNameMousePressed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        // TODO add your handling code here:

        btnmodificar.setVisible(false);
        btnborrar.setVisible(false);
        btnAgregar.setVisible(true);

        txtid.setText("Ingrese la identificacion");
        txtnombre.setText("Ingrese el nombre");
        txtapellido1.setText("Ingrese el apellido 1");
        txtapellido2.setText("Ingrese el apellido 2");
        txtUserName.setText("Ingrese el nombre de usuario");
        txtPass1.setText("contraseña");
        txtPass2.setText("contraseña");
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtPass2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPass2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPass2ActionPerformed

    private void txtPass1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPass1MousePressed
        // TODO add your handling code here:
        if ("contraseña".equals(txtPass1.getText())) {
            txtPass1.setText("");
        }
    }//GEN-LAST:event_txtPass1MousePressed

    private void txtPass2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPass2MousePressed
        // TODO add your handling code here:
        if ("contraseña".equals(txtPass2.getText())) {
            txtPass2.setText("");
        }
    }//GEN-LAST:event_txtPass2MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator Separator1;
    private javax.swing.JSeparator Separator2;
    private javax.swing.JSeparator Separator3;
    private javax.swing.JSeparator Separator4;
    private javax.swing.JSeparator Separator5;
    private javax.swing.JSeparator SeparatorV;
    private javax.swing.JLabel Title;
    private javax.swing.JPanel body;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnborrar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txt1;
    private javax.swing.JLabel txt2;
    private javax.swing.JLabel txt3;
    private javax.swing.JLabel txt4;
    private javax.swing.JLabel txt5;
    private javax.swing.JPasswordField txtPass1;
    private javax.swing.JPasswordField txtPass2;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtapellido1;
    private javax.swing.JTextField txtapellido2;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
