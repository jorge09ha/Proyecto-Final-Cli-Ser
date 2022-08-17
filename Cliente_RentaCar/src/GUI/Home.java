package GUI;

import static Conexion.HomeSocket.homeToServer;
import javax.swing.JOptionPane;

/**
 * @author Jorge Hernandez Araya
 */
public class Home extends javax.swing.JPanel {

    static String autosdisponibles, autosrentados, adminuser, clientuser;

    public Home() {
        initComponents();
        homeToServer("home", "");

        aDisponibles.setText(autosdisponibles);
        aRentados.setText(autosrentados);
        lClientes.setText(clientuser);
        admUsers.setText(adminuser);

    }

    /*
    Clases para obtener los números visibles desde la pantalla home no 
    obtiene mediante la conexión de HomeSocket.
     */
    public static void autosDisponibles(String aut) {
        autosdisponibles = aut;
    }

    public static void autosRentados(String aut) {
        autosrentados = aut;
    }

    public static void adminUser(String aut) {
        adminuser = aut;
    }

    public static void clientUser(String aut) {
        clientuser = aut;
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
        Separator1 = new javax.swing.JSeparator();
        txt1 = new javax.swing.JLabel();
        txt2 = new javax.swing.JLabel();
        admUsers = new javax.swing.JLabel();
        lClientes = new javax.swing.JLabel();
        aRentados = new javax.swing.JLabel();
        aDisponibles = new javax.swing.JLabel();
        txt3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt4 = new javax.swing.JLabel();

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
        Title.setText("HOME");
        add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        Separator1.setForeground(new java.awt.Color(0, 0, 0));
        Separator1.setPreferredSize(new java.awt.Dimension(200, 10));
        add(Separator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 420, 10));

        txt1.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt1.setForeground(new java.awt.Color(0, 0, 0));
        txt1.setText("Autos Disponibles");
        add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 220, -1));

        txt2.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt2.setForeground(new java.awt.Color(0, 0, 0));
        txt2.setText("Autos Rentados");
        add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 90, 180, -1));

        admUsers.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        admUsers.setForeground(new java.awt.Color(0, 0, 0));
        admUsers.setText("0");
        add(admUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 300, -1, -1));

        lClientes.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        lClientes.setForeground(new java.awt.Color(0, 0, 0));
        lClientes.setText("0");
        add(lClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, -1, -1));

        aRentados.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        aRentados.setForeground(new java.awt.Color(0, 0, 0));
        aRentados.setText("0");
        add(aRentados, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, -1, -1));

        aDisponibles.setFont(new java.awt.Font("Roboto Black", 1, 48)); // NOI18N
        aDisponibles.setForeground(new java.awt.Color(0, 0, 0));
        aDisponibles.setText("0");
        add(aDisponibles, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, -1, -1));

        txt3.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt3.setForeground(new java.awt.Color(0, 0, 0));
        txt3.setText("Admin Users");
        add(txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 160, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/rentar.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-200, -10, -1, -1));

        txt4.setFont(new java.awt.Font("Roboto Black", 1, 18)); // NOI18N
        txt4.setForeground(new java.awt.Color(0, 0, 0));
        txt4.setText("Clientes Registrados");
        add(txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 230, -1));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator Separator1;
    private javax.swing.JLabel Title;
    private javax.swing.JLabel aDisponibles;
    private javax.swing.JLabel aRentados;
    private javax.swing.JLabel admUsers;
    private javax.swing.JPanel body;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lClientes;
    private javax.swing.JLabel txt1;
    private javax.swing.JLabel txt2;
    private javax.swing.JLabel txt3;
    private javax.swing.JLabel txt4;
    // End of variables declaration//GEN-END:variables
}
