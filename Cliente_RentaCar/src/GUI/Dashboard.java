package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;

/**
 * @author Jorge Hernandez Araya
 */
public class Dashboard extends javax.swing.JFrame {

    int xMouse;
    int yMouse;

    /**
     * Dashboard donde se muestran las distintas pantallas según su función
     * inicializa tomando el nombre del usuario bloqueado junto con la fecha.
     */
    public Dashboard() {

        initComponents();
        txtUserName.setText("Usermane: " + LoginGUI.userLogin);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int dia = now.getDayOfMonth();
        int month = now.getMonthValue();
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", " ;Septiembre",
            "Octubre", "Noviembre", "Diciemrbre"};
        fecha.setText("Hoy es " + dia + " de " + meses[month - 1] + " de " + year);

        Home p1 = new Home();
        p1.setSize(750, 430);
        p1.setLocation(0, 0);

        content.removeAll();
        content.add(p1, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Background = new javax.swing.JPanel();
        Title = new javax.swing.JPanel();
        red_squr = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        btn_home = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_rentar = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btn_returns = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_clients = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_autos = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btn_admUsers = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        app_name = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Header = new javax.swing.JPanel();
        txtUserName = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        content = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        Background.setBackground(new java.awt.Color(255, 255, 255));
        Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Title.setBackground(new java.awt.Color(18, 90, 173));
        Title.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                TitleMouseDragged(evt);
            }
        });
        Title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TitleMousePressed(evt);
            }
        });

        red_squr.setBackground(new java.awt.Color(18, 90, 173));
        red_squr.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        red_squr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                red_squrMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                red_squrMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                red_squrMousePressed(evt);
            }
        });

        exit.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exit.setText("X");
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitMousePressed(evt);
            }
        });

        javax.swing.GroupLayout red_squrLayout = new javax.swing.GroupLayout(red_squr);
        red_squr.setLayout(red_squrLayout);
        red_squrLayout.setHorizontalGroup(
            red_squrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        red_squrLayout.setVerticalGroup(
            red_squrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Proyecto Final G3 - Programacion Cliente Servidor");

        javax.swing.GroupLayout TitleLayout = new javax.swing.GroupLayout(Title);
        Title.setLayout(TitleLayout);
        TitleLayout.setHorizontalGroup(
            TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 476, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 502, Short.MAX_VALUE)
                .addComponent(red_squr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        TitleLayout.setVerticalGroup(
            TitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(red_squr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TitleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addContainerGap())
        );

        Background.add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, -1));

        Menu.setBackground(new java.awt.Color(18, 90, 173));
        Menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_home.setBackground(new java.awt.Color(21, 101, 192));
        btn_home.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_homeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_homeMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_homeMousePressed(evt);
            }
        });
        btn_home.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconHOME.png"))); // NOI18N
        btn_home.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Home");
        btn_home.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 200, 30));

        Menu.add(btn_home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 270, 50));

        btn_rentar.setBackground(new java.awt.Color(18, 90, 173));
        btn_rentar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_rentar.setPreferredSize(new java.awt.Dimension(270, 51));
        btn_rentar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_rentarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_rentarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_rentarMousePressed(evt);
            }
        });
        btn_rentar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconADD.png"))); // NOI18N
        btn_rentar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jLabel14.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Rentar");
        btn_rentar.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 200, 30));

        Menu.add(btn_rentar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, -1, -1));

        btn_returns.setBackground(new java.awt.Color(18, 90, 173));
        btn_returns.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_returns.setPreferredSize(new java.awt.Dimension(270, 51));
        btn_returns.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_returnsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_returnsMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_returnsMousePressed(evt);
            }
        });
        btn_returns.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconCALENDAR.png"))); // NOI18N
        btn_returns.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Retornar");
        btn_returns.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 200, 30));

        Menu.add(btn_returns, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, -1, -1));

        btn_clients.setBackground(new java.awt.Color(18, 90, 173));
        btn_clients.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_clients.setPreferredSize(new java.awt.Dimension(270, 51));
        btn_clients.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_clientsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_clientsMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_clientsMousePressed(evt);
            }
        });
        btn_clients.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconCLIENTS.png"))); // NOI18N
        btn_clients.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Clientes");
        btn_clients.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 190, 30));

        Menu.add(btn_clients, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, -1));

        btn_autos.setBackground(new java.awt.Color(18, 90, 173));
        btn_autos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_autos.setPreferredSize(new java.awt.Dimension(270, 51));
        btn_autos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_autosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_autosMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_autosMousePressed(evt);
            }
        });
        btn_autos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconAUTO.png"))); // NOI18N
        btn_autos.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Autos");
        btn_autos.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 180, 30));

        Menu.add(btn_autos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, -1, -1));

        btn_admUsers.setBackground(new java.awt.Color(18, 90, 173));
        btn_admUsers.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_admUsers.setPreferredSize(new java.awt.Dimension(270, 51));
        btn_admUsers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_admUsersMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_admUsersMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_admUsersMousePressed(evt);
            }
        });
        btn_admUsers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconUSER.png"))); // NOI18N
        btn_admUsers.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jLabel16.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Usuarios");
        btn_admUsers.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 190, 30));

        Menu.add(btn_admUsers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, -1, -1));

        app_name.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        app_name.setForeground(new java.awt.Color(255, 255, 255));
        app_name.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/favicon.png"))); // NOI18N
        app_name.setText("Rent a Car");
        Menu.add(app_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 210, -1));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setPreferredSize(new java.awt.Dimension(50, 5));
        Menu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 190, 20));

        Background.add(Menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 270, 640));

        Header.setBackground(new java.awt.Color(18, 90, 173));

        txtUserName.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txtUserName.setForeground(new java.awt.Color(255, 255, 255));
        txtUserName.setText("User: User name");

        fecha.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        fecha.setForeground(new java.awt.Color(255, 255, 255));
        fecha.setText("Hoy es Sábado 28 de Abril de 2018");

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(292, Short.MAX_VALUE))
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderLayout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        Background.add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 750, 180));

        content.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout contentLayout = new javax.swing.GroupLayout(content);
        content.setLayout(contentLayout);
        contentLayout.setHorizontalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        contentLayout.setVerticalGroup(
            contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        Background.add(content, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 750, 460));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void TitleMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitleMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_TitleMouseDragged

    private void TitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TitleMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_TitleMousePressed

    private void exitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMousePressed
        System.exit(0);
    }//GEN-LAST:event_exitMousePressed

    private void btn_homeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMousePressed
        setColor(btn_home);
        resetColor(btn_rentar);
        resetColor(btn_returns);
        resetColor(btn_clients);
        resetColor(btn_autos);
        resetColor(btn_admUsers);
        // Abrir sección
        Home p1 = new Home();
        p1.setSize(750, 430);
        p1.setLocation(0, 0);

        content.removeAll();
        content.add(p1, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_btn_homeMousePressed

    private void btn_rentarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rentarMousePressed
        resetColor(btn_home);
        setColor(btn_rentar);
        resetColor(btn_returns);
        resetColor(btn_clients);
        resetColor(btn_autos);
        resetColor(btn_admUsers);
        // Abrir sección
        RentarGUI p1 = new RentarGUI();
        p1.setSize(750, 430);
        p1.setLocation(0, 0);

        content.removeAll();
        content.add(p1, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_btn_rentarMousePressed

    private void btn_returnsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_returnsMousePressed
        resetColor(btn_home);
        resetColor(btn_rentar);
        setColor(btn_returns);
        resetColor(btn_clients);
        resetColor(btn_autos);
        resetColor(btn_admUsers);
        // Abrir sección
        RetornarGUI p1 = new RetornarGUI();
        p1.setSize(750, 430);
        p1.setLocation(0, 0);

        content.removeAll();
        content.add(p1, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_btn_returnsMousePressed

    private void btn_clientsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clientsMousePressed
        resetColor(btn_home);
        resetColor(btn_rentar);
        resetColor(btn_returns);
        setColor(btn_clients);
        resetColor(btn_autos);
        resetColor(btn_admUsers);
        // Abrir sección
        ClienteGUI p1 = new ClienteGUI();
        p1.setSize(750, 430);
        p1.setLocation(0, 0);

        content.removeAll();
        content.add(p1, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_btn_clientsMousePressed

    private void btn_autosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_autosMousePressed
        resetColor(btn_home);
        resetColor(btn_rentar);
        resetColor(btn_returns);
        resetColor(btn_clients);
        setColor(btn_autos);
        resetColor(btn_admUsers);
        // Abrir sección
        AutosGUI p1 = new AutosGUI();
        p1.setSize(750, 430);
        p1.setLocation(0, 0);

        content.removeAll();
        content.add(p1, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_btn_autosMousePressed

    private void red_squrMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_red_squrMousePressed
        System.exit(0);
    }//GEN-LAST:event_red_squrMousePressed

    private void btn_rentarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rentarMouseEntered
        if (btn_rentar.getBackground().getRGB() == -15574355)
            setColor(btn_rentar);
    }//GEN-LAST:event_btn_rentarMouseEntered

    private void btn_rentarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_rentarMouseExited
        if (btn_home.getBackground().getRGB() != -15574355 || btn_clients.getBackground().getRGB() != -15574355
                || btn_returns.getBackground().getRGB() != -15574355 || btn_autos.getBackground().getRGB() != -15574355
                || btn_admUsers.getBackground().getRGB() != -15574355)
            resetColor(btn_rentar);
    }//GEN-LAST:event_btn_rentarMouseExited

    private void btn_homeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseEntered
        if (btn_home.getBackground().getRGB() == -15574355)
            setColor(btn_home);
    }//GEN-LAST:event_btn_homeMouseEntered

    private void btn_homeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_homeMouseExited
        if (btn_rentar.getBackground().getRGB() != -15574355 || btn_clients.getBackground().getRGB() != -15574355
                || btn_returns.getBackground().getRGB() != -15574355 || btn_autos.getBackground().getRGB() != -15574355
                || btn_admUsers.getBackground().getRGB() != -15574355)
            resetColor(btn_home);
    }//GEN-LAST:event_btn_homeMouseExited

    private void btn_returnsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_returnsMouseEntered
        if (btn_returns.getBackground().getRGB() == -15574355)
            setColor(btn_returns);
    }//GEN-LAST:event_btn_returnsMouseEntered

    private void btn_returnsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_returnsMouseExited
        if (btn_rentar.getBackground().getRGB() != -15574355 || btn_home.getBackground().getRGB() != -15574355
                || btn_clients.getBackground().getRGB() != -15574355 || btn_autos.getBackground().getRGB() != -15574355
                || btn_admUsers.getBackground().getRGB() != -15574355)
            resetColor(btn_returns);
    }//GEN-LAST:event_btn_returnsMouseExited

    private void btn_clientsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clientsMouseEntered
        if (btn_clients.getBackground().getRGB() == -15574355)
            setColor(btn_clients);
    }//GEN-LAST:event_btn_clientsMouseEntered

    private void btn_clientsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_clientsMouseExited
        if (btn_rentar.getBackground().getRGB() != -15574355 || btn_home.getBackground().getRGB() != -15574355
                || btn_returns.getBackground().getRGB() != -15574355 || btn_autos.getBackground().getRGB() != -15574355
                || btn_admUsers.getBackground().getRGB() != -15574355)
            resetColor(btn_clients);
    }//GEN-LAST:event_btn_clientsMouseExited

    private void btn_autosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_autosMouseEntered
        if (btn_autos.getBackground().getRGB() == -15574355)
            setColor(btn_autos);
    }//GEN-LAST:event_btn_autosMouseEntered

    private void btn_autosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_autosMouseExited
        if (btn_rentar.getBackground().getRGB() != -15574355 || btn_home.getBackground().getRGB() != -15574355
                || btn_returns.getBackground().getRGB() != -15574355 || btn_clients.getBackground().getRGB() != -15574355
                || btn_admUsers.getBackground().getRGB() != -15574355)
            resetColor(btn_autos);
    }//GEN-LAST:event_btn_autosMouseExited

    private void red_squrMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_red_squrMouseEntered
        red_squr.setBackground(new Color(204, 0, 0));
        exit.setForeground(Color.white);
    }//GEN-LAST:event_red_squrMouseEntered

    private void red_squrMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_red_squrMouseExited
        red_squr.setBackground(new Color(255, 255, 255));
        exit.setForeground(new Color(102, 102, 102));
    }//GEN-LAST:event_red_squrMouseExited

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseEntered
        red_squr.setBackground(new Color(255, 0, 0));
        exit.setForeground(Color.white);
    }//GEN-LAST:event_exitMouseEntered

    private void exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseExited
        red_squr.setBackground(new Color(18, 90, 173));
        exit.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_exitMouseExited

    private void btn_admUsersMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_admUsersMouseEntered
        if (btn_admUsers.getBackground().getRGB() == -15574355)
            setColor(btn_admUsers);
    }//GEN-LAST:event_btn_admUsersMouseEntered

    private void btn_admUsersMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_admUsersMouseExited
        if (btn_rentar.getBackground().getRGB() != -15574355 || btn_home.getBackground().getRGB() != -15574355
                || btn_returns.getBackground().getRGB() != -15574355 || btn_clients.getBackground().getRGB() != -15574355
                || btn_autos.getBackground().getRGB() != -15574355)
            resetColor(btn_admUsers);
    }//GEN-LAST:event_btn_admUsersMouseExited

    private void btn_admUsersMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_admUsersMousePressed
        resetColor(btn_home);
        resetColor(btn_rentar);
        resetColor(btn_returns);
        resetColor(btn_clients);
        resetColor(btn_autos);
        setColor(btn_admUsers);

        // Abrir sección
        UsuariosGUI p1 = new UsuariosGUI();
        p1.setSize(750, 430);
        p1.setLocation(0, 0);

        content.removeAll();
        content.add(p1, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_btn_admUsersMousePressed

    void setColor(JPanel panel) {
        panel.setBackground(new Color(21, 101, 192));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(18, 90, 173));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Background;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Title;
    private javax.swing.JLabel app_name;
    private javax.swing.JPanel btn_admUsers;
    private javax.swing.JPanel btn_autos;
    private javax.swing.JPanel btn_clients;
    private javax.swing.JPanel btn_home;
    private javax.swing.JPanel btn_rentar;
    private javax.swing.JPanel btn_returns;
    public static javax.swing.JPanel content;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel red_squr;
    private javax.swing.JLabel txtUserName;
    // End of variables declaration//GEN-END:variables
}
