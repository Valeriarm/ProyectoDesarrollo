    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controller.DBConnection;
import Model.Report;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;



public class reportGerente extends javax.swing.JFrame {

    private DBConnection bD;
    private final String idGerente;
    private final int sede;
    
    public reportGerente(DBConnection baseDatos, String idGerente, int sede) {
        initComponents();
        llenarCBox();
        this.bD = baseDatos;
        this.idGerente = idGerente;
        this.sede = sede;
    }    
    
    @SuppressWarnings("unchecked")
    
    private void llenarCBox(){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for(int i=year-50; i<=year; i++){
            fechaInicioAnio.addItem(String.valueOf(i));
            fechaFinAnio.addItem(String.valueOf(i));
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        fechaInicioDia = new javax.swing.JComboBox<>();
        reportPanel = new javax.swing.JPanel();
        fechaInicioMes = new javax.swing.JComboBox<>();
        fechaInicioAnio = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        fechaFinDia = new javax.swing.JComboBox<>();
        jComboBox1 = new javax.swing.JComboBox<>();
        fechaFinMes = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        fechaFinAnio = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        checkSede = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("Fecha Fin");

        fechaInicioDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        reportPanel.setLayout(new javax.swing.BoxLayout(reportPanel, javax.swing.BoxLayout.LINE_AXIS));

        fechaInicioMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        fechaInicioMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaInicioMesActionPerformed(evt);
            }
        });

        fechaInicioAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaInicioAnioActionPerformed(evt);
            }
        });

        jButton1.setText("Mostrar Grafico");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        fechaFinDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inventario", "Orden de trabajo", "Ventas", "Cotizaciones" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        fechaFinMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        fechaFinMes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaFinMesActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Reportes Gerente");

        fechaFinAnio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fechaFinAnioActionPerformed(evt);
            }
        });

        jLabel2.setText("Objeto");

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dia", "Mes", "Anio" }));

        jLabel3.setText("Tiempo");

        jLabel4.setText("Fecha inicio");

        checkSede.setText("Sede");
        checkSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSedeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(560, Short.MAX_VALUE)
                .addComponent(checkSede)
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fechaInicioDia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fechaInicioMes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fechaInicioAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fechaFinDia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fechaFinMes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fechaFinAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(80, 80, 80))))
                        .addComponent(reportPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(checkSede)
                .addContainerGap(331, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(fechaInicioDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaFinDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(fechaFinMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaFinAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaInicioAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaInicioMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(reportPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(17, 17, 17)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fechaInicioMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaInicioMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicioMesActionPerformed

    private void fechaInicioAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaInicioAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaInicioAnioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        List valores = new ArrayList();
        String initDate ="";
        String finishDate = "";
        
        if ("Inventario".equals(String.valueOf(this.jComboBox1.getSelectedItem()))){
            valores = bD.reporteInventario();
        }else if ("Orden de trabajo".equals(String.valueOf(this.jComboBox1.getSelectedItem()))){
            initDate = String.valueOf(this.fechaInicioDia.getSelectedItem())+"-"+
            String.valueOf(this.fechaInicioMes.getSelectedItem())+"-"+
            String.valueOf(this.fechaInicioAnio.getSelectedItem());
            finishDate = String.valueOf(this.fechaFinDia.getSelectedItem())+"-"+
            String.valueOf(this.fechaFinMes.getSelectedItem())+"-"+
            String.valueOf(this.fechaFinAnio.getSelectedItem());
            System.out.println(initDate);
            System.out.println(finishDate);
            if(!this.checkSede.isSelected()){
                if(null != String.valueOf(this.jComboBox2.getSelectedItem()))switch (String.valueOf(this.jComboBox2.getSelectedItem())) {
                    case "Dia":
                    valores = bD.reporteGerenteOrdenesTrabajoDia(initDate, finishDate);
                    break;
                    case "Mes":
                    valores = bD.reporteGerenteOrdenesTrabajoMes(initDate, finishDate);
                    break;
                    case "Anio":
                    valores = bD.reporteGerenteOrdenesTrabajoAnio(initDate, finishDate);
                    break;
                    default:
                    break;
                }
              }else{
                if(null != String.valueOf(this.jComboBox2.getSelectedItem()))switch (String.valueOf(this.jComboBox2.getSelectedItem())) {
                    case "Dia":
                    valores = bD.reporteGerenteSedeOrdenesTrabajoDia(String.valueOf(this.sede), initDate, finishDate);
                    break;
                    case "Mes":
                    valores = bD.reporteGerenteSedeOrdenesTrabajoMes(String.valueOf(this.sede), initDate, finishDate);
                    break;
                    case "Anio":
                    valores = bD.reporteGerenteSedeOrdenesTrabajoAnio(String.valueOf(this.sede), initDate, finishDate);
                    break;
                    default:
                    break;
                    }
                }
        }else if ("Ventas".equals(String.valueOf(this.jComboBox1.getSelectedItem()))){
                initDate = String.valueOf(this.fechaInicioDia.getSelectedItem())+"-"+
                String.valueOf(this.fechaInicioMes.getSelectedItem())+"-"+
                String.valueOf(this.fechaInicioAnio.getSelectedItem());
                finishDate = String.valueOf(this.fechaFinDia.getSelectedItem())+"-"+
                String.valueOf(this.fechaFinMes.getSelectedItem())+"-"+
                String.valueOf(this.fechaFinAnio.getSelectedItem());
                System.out.println(initDate);
                System.out.println(finishDate);
                if(!this.checkSede.isSelected()){
                    if(null != String.valueOf(this.jComboBox2.getSelectedItem()))switch (String.valueOf(this.jComboBox2.getSelectedItem())) {
                        case "Dia":
                        valores = bD.reporteGerenteVentasDia(initDate, finishDate);
                        break;
                        case "Mes":
                        valores = bD.reporteGerenteVentasMes(initDate, finishDate);
                        break;
                        case "Anio":
                        valores = bD.reporteGerenteVentasAnio(initDate, finishDate);
                        break;
                        default:
                        break;
                    }
                }else{
                    if(null != String.valueOf(this.jComboBox2.getSelectedItem()))switch (String.valueOf(this.jComboBox2.getSelectedItem())) {
                        case "Dia":
                        valores = bD.reporteGerenteSedeVentasDia(String.valueOf(this.sede), initDate, finishDate);
                        break;
                        case "Mes":
                        valores = bD.reporteGerenteSedeVentasMes(String.valueOf(this.sede), initDate, finishDate);
                        break;
                        case "Anio":
                        valores = bD.reporteGerenteSedeVentasAnio(String.valueOf(this.sede), initDate, finishDate);
                        break;
                        default:
                        break;
                    }
                }
        }else if ("Cotizaciones".equals(String.valueOf(this.jComboBox1.getSelectedItem()))){
                initDate = String.valueOf(this.fechaInicioDia.getSelectedItem())+"-"+
                String.valueOf(this.fechaInicioMes.getSelectedItem())+"-"+
                String.valueOf(this.fechaInicioAnio.getSelectedItem());
                finishDate = String.valueOf(this.fechaFinDia.getSelectedItem())+"-"+
                String.valueOf(this.fechaFinMes.getSelectedItem())+"-"+
                String.valueOf(this.fechaFinAnio.getSelectedItem());
                System.out.println(initDate);
                System.out.println(finishDate);
                if(!this.checkSede.isSelected()){
                    if(null != String.valueOf(this.jComboBox2.getSelectedItem()))switch (String.valueOf(this.jComboBox2.getSelectedItem())) {
                        case "Dia":
                        //valores = bD.reporteGerenteOrdenesTrabajoDia(initDate, finishDate);
                        break;
                        case "Mes":
                        //valores = bD.reporteGerenteOrdenesTrabajoMes(initDate, finishDate);
                        break;
                        case "Anio":
                        //valores = bD.reporteGerenteOrdenesTrabajoAnio(initDate, finishDate);
                        break;
                        default:
                        break;
                }
            }
        }else{
                if(null != String.valueOf(this.jComboBox2.getSelectedItem()))switch (String.valueOf(this.jComboBox2.getSelectedItem())) {
                    case "Dia":
                    //valores = bD.reporteGerenteSedeOrdenesTrabajoDia(String.valueOf(this.sede), initDate, finishDate);
                    break;
                    case "Mes":
                    //valores = bD.reporteGerenteSedeOrdenesTrabajoMes(String.valueOf(this.sede), initDate, finishDate);
                    break;
                    case "Anio":
                    //valores = bD.reporteGerenteSedeOrdenesTrabajoAnio(String.valueOf(this.sede), initDate, finishDate);
                    break;
                    default:
                    break;
                }
            }
        DefaultCategoryDataset dcd = new DefaultCategoryDataset();
        Report report = new Report("", 0);
        for (int i=0; i<valores.size(); i++){
            report = (Report) valores.get(i);
            dcd.setValue(report.getValue(), "Cantidad", report.getName());
        }

        JFreeChart jchart = ChartFactory.createBarChart("Reporte",
            "Fecha", "Cantidad", dcd, PlotOrientation.VERTICAL,
            true, true, false);

        ChartPanel chartPanel = new ChartPanel(jchart);
        reportPanel.removeAll();
        reportPanel.add(chartPanel);
        reportPanel.updateUI();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void fechaFinMesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaFinMesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinMesActionPerformed

    private void fechaFinAnioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fechaFinAnioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaFinAnioActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void checkSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSedeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkSedeActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(reportGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(reportGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(reportGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(reportGerente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new reportGerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkSede;
    private javax.swing.JComboBox<String> fechaFinAnio;
    private javax.swing.JComboBox<String> fechaFinDia;
    private javax.swing.JComboBox<String> fechaFinMes;
    private javax.swing.JComboBox<String> fechaInicioAnio;
    private javax.swing.JComboBox<String> fechaInicioDia;
    private javax.swing.JComboBox<String> fechaInicioMes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel reportPanel;
    // End of variables declaration//GEN-END:variables
}
