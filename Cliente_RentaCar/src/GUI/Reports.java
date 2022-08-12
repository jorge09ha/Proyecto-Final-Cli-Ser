package GUI;

import rentACar.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
//import static itp_library.Dashboard.content;
import java.sql.Statement;

public class Reports extends javax.swing.JPanel {

    public Reports() {
        initComponents();
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
        btmRegistrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reportesTable = new javax.swing.JTable();

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
        Title.setText("REPORTES");
        add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btmRegistrar.setBackground(new java.awt.Color(18, 90, 173));
        btmRegistrar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btmRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btmRegistrar.setText("ACTUALIZAR");
        btmRegistrar.setBorder(null);
        btmRegistrar.setBorderPainted(false);
        btmRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmRegistrarActionPerformed(evt);
            }
        });
        add(btmRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 370, 130, 30));

        reportesTable.setBackground(new java.awt.Color(255, 255, 255));
        reportesTable.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        reportesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Marca", "Placa", "Cliente", "Salida", "Estado"
            }
        ));
        jScrollPane1.setViewportView(reportesTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 670, 250));
    }// </editor-fold>//GEN-END:initComponents

    private void btmRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmRegistrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btmRegistrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JPanel body;
    private javax.swing.JButton btmRegistrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable reportesTable;
    // End of variables declaration//GEN-END:variables
}
