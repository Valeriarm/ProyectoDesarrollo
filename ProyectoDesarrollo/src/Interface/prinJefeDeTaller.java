/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controller.DBConnection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import javax.swing.Timer;

/**
 *
 * @author Marthox
 * 
 */
public class prinJefeDeTaller extends javax.swing.JFrame {
    
    //la variable botonAceptar indica el uso que se le da al botón de la izquierda para cada boton principal
    //1:Agregar, 2:Modificar, 3:Consultar, 4:Anular 0 Eliminar, 0:nada
    private int botonAceptar = 0;  
    private DBConnection bD;
    
    public prinJefeDeTaller(DBConnection baseDatos) {
        initComponents();
        bD = baseDatos;
        
        //Fecha
        Date fechaSist = new Date(); 
        SimpleDateFormat formato = new SimpleDateFormat("dd-MMM-yyyy");
        fecha.setText(formato.format(fechaSist));
        
        //Hora
        Timer tiempo = new Timer(100, new prinJefeDeTaller.hora());
        tiempo.start();
        
        cambiarVisibilidadCamposOrden(false);
        cambiarVisibilidadCamposOrden(false);
        bAceptar.setVisible(false);
        
        //Elementos del popup menú para modf,agregar y consultar
        this.ordenesDeTrabajo.setSelected(true);
        this.itemDeInventario.setSelected(false);
        
       }

    class hora implements ActionListener{
    
        public void actionPerformed(ActionEvent e){
        
        Date fechaHora = new Date();
        String pmAm = "hh:mm:ss a";
        SimpleDateFormat format = new SimpleDateFormat(pmAm);
        Calendar hoy = Calendar.getInstance();
        hora.setText(String.format(format.format(fechaHora), hoy));
      
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jefeDeTaller = new javax.swing.JPopupMenu();
        ordenesDeTrabajo = new javax.swing.JCheckBoxMenuItem();
        itemDeInventario = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        iconUsu = new javax.swing.JLabel();
        labLogo = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        bAgregar = new javax.swing.JButton();
        bModf = new javax.swing.JButton();
        bConsul = new javax.swing.JButton();
        bAnular = new javax.swing.JButton();
        bReportes = new javax.swing.JToggleButton();
        jPanel4 = new javax.swing.JPanel();
        labIDOrdenDeTrabajo = new javax.swing.JLabel();
        labTelefonoCliente = new javax.swing.JLabel();
        labCosto = new javax.swing.JLabel();
        labEstado = new javax.swing.JLabel();
        labFechaDeEntrega = new javax.swing.JLabel();
        labDescripcion = new javax.swing.JLabel();
        labIDJefeDeTaller = new javax.swing.JLabel();
        tIDOrdenDeTrabajo = new javax.swing.JTextField();
        tTelefonoCliente = new javax.swing.JTextField();
        tCosto = new javax.swing.JTextField();
        tEstado = new javax.swing.JTextField();
        tFechaEntrega = new javax.swing.JTextField();
        labIDCliente = new javax.swing.JLabel();
        tIDCliente = new javax.swing.JTextField();
        tDescripcion = new javax.swing.JTextField();
        tIDJefeDeTaller = new javax.swing.JTextField();
        bAceptar = new javax.swing.JButton();
        labNombreCliente = new javax.swing.JLabel();
        tNombreCliente = new javax.swing.JTextField();
        fecha = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        fechaYhora = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        jefeDeTaller.setToolTipText("");

        ordenesDeTrabajo.setSelected(true);
        ordenesDeTrabajo.setText("Orden de Trabajo");
        ordenesDeTrabajo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ordenesDeTrabajoMouseReleased(evt);
            }
        });
        ordenesDeTrabajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordenesDeTrabajoActionPerformed(evt);
            }
        });
        jefeDeTaller.add(ordenesDeTrabajo);

        itemDeInventario.setSelected(true);
        itemDeInventario.setText("Item de Inventario");
        itemDeInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                itemDeInventarioMouseReleased(evt);
            }
        });
        itemDeInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemDeInventarioActionPerformed(evt);
            }
        });
        jefeDeTaller.add(itemDeInventario);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new java.awt.BorderLayout());

        iconUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ICO menuSuper.png"))); // NOI18N

        labLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo.png"))); // NOI18N
        labLogo.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(labLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                .addComponent(iconUsu)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(iconUsu)
                    .addComponent(labLogo))
                .addGap(18, 18, 18))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        bAgregar.setForeground(new java.awt.Color(51, 51, 51));
        bAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ICO addUser.png"))); // NOI18N
        bAgregar.setText("Agregar");
        bAgregar.setBorderPainted(false);
        bAgregar.setContentAreaFilled(false);
        bAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bAgregarMouseReleased(evt);
            }
        });
        bAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarActionPerformed(evt);
            }
        });

        bModf.setForeground(new java.awt.Color(51, 51, 51));
        bModf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ICO modifyUser.png"))); // NOI18N
        bModf.setText("Modificar");
        bModf.setBorderPainted(false);
        bModf.setContentAreaFilled(false);
        bModf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bModfMouseReleased(evt);
            }
        });

        bConsul.setForeground(new java.awt.Color(51, 51, 51));
        bConsul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ICO searchUser.png"))); // NOI18N
        bConsul.setText("Consultar");
        bConsul.setBorderPainted(false);
        bConsul.setContentAreaFilled(false);
        bConsul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bConsulMouseReleased(evt);
            }
        });

        bAnular.setForeground(new java.awt.Color(51, 51, 51));
        bAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ICO fireUser.png"))); // NOI18N
        bAnular.setText("Anular");
        bAnular.setBorderPainted(false);
        bAnular.setContentAreaFilled(false);
        bAnular.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bAnularMouseReleased(evt);
            }
        });
        bAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnularActionPerformed(evt);
            }
        });

        bReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/growth.png"))); // NOI18N
        bReportes.setText("Reportes");
        bReportes.setBorderPainted(false);
        bReportes.setContentAreaFilled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bReportes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bModf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bConsul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(bAgregar)
                .addGap(18, 18, 18)
                .addComponent(bModf)
                .addGap(18, 18, 18)
                .addComponent(bConsul)
                .addGap(18, 18, 18)
                .addComponent(bAnular)
                .addGap(18, 18, 18)
                .addComponent(bReportes)
                .addContainerGap(164, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(jPanel3);

        labIDOrdenDeTrabajo.setText("ID Orden de trabajo:");

        labTelefonoCliente.setText("Telefono Cliente:");

        labCosto.setText("Costo:");

        labEstado.setText("Estado:");

        labFechaDeEntrega.setText("Fecha de entrega:");

        labDescripcion.setText("Descripción:");

        labIDJefeDeTaller.setText("ID Jefe de taller:");

        tIDOrdenDeTrabajo.setToolTipText("");

        tTelefonoCliente.setToolTipText("");

        tCosto.setToolTipText("");

        tEstado.setToolTipText("");
        tEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tEstadoActionPerformed(evt);
            }
        });

        tFechaEntrega.setToolTipText("");
        tFechaEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tFechaEntregaActionPerformed(evt);
            }
        });

        labIDCliente.setText("ID Cliente:");
        labIDCliente.setToolTipText("");

        tIDCliente.setToolTipText("");

        tDescripcion.setToolTipText("");

        tIDJefeDeTaller.setToolTipText("");

        bAceptar.setText("Agregar");

        labNombreCliente.setText("Nombre de Cliente:");

        tNombreCliente.setToolTipText("");

        fecha.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        fecha.setText("dd-mm-yyyy");

        hora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        hora.setText("hh:mm:ss");

        fechaYhora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        fechaYhora.setText("Fecha  y hora:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labFechaDeEntrega)
                            .addComponent(labIDOrdenDeTrabajo)
                            .addComponent(labTelefonoCliente)
                            .addComponent(labCosto)
                            .addComponent(labEstado)
                            .addComponent(labIDCliente)
                            .addComponent(labDescripcion)
                            .addComponent(labIDJefeDeTaller)
                            .addComponent(labNombreCliente))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(tIDOrdenDeTrabajo, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                                .addComponent(tTelefonoCliente)
                                .addComponent(tCosto)
                                .addComponent(tEstado)
                                .addComponent(tNombreCliente)
                                .addComponent(tIDCliente)
                                .addComponent(tFechaEntrega))
                            .addComponent(tIDJefeDeTaller, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tDescripcion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fechaYhora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bAceptar)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDOrdenDeTrabajo)
                    .addComponent(tIDOrdenDeTrabajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNombreCliente)
                    .addComponent(tNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDCliente)
                    .addComponent(tIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTelefonoCliente)
                    .addComponent(tTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labCosto)
                    .addComponent(tCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labEstado)
                    .addComponent(tEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labFechaDeEntrega)
                    .addComponent(tFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labIDJefeDeTaller, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tIDJefeDeTaller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labDescripcion))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fechaYhora)
                            .addComponent(fecha)
                            .addComponent(hora)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bAceptar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(jPanel4);

        jPanel1.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(411, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ordenesDeTrabajoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordenesDeTrabajoMouseReleased
        if(this.ordenesDeTrabajo.isSelected()){
            this.itemDeInventario.setSelected(false);
        }
    }//GEN-LAST:event_ordenesDeTrabajoMouseReleased

    private void itemDeInventarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemDeInventarioMouseReleased
        if(this.itemDeInventario.isSelected()){
            this.ordenesDeTrabajo.setSelected(false);
        }
    }//GEN-LAST:event_itemDeInventarioMouseReleased

    private void bAgregarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAgregarMouseReleased
       //muestra las opciones (Ordenes de trabajo o Items de inventario)
        if(evt.isPopupTrigger()){
         jefeDeTaller.show(evt.getComponent(),evt.getX(),evt.getY());
        } 
              
        if(this.ordenesDeTrabajo.isSelected()){
            cambiarVisibilidadCamposInventario(false);
            cambiarVisibilidadCamposOrden(true);
            cambiarVisibilidadCamposOrdenAgregar(true);
        
            botonAceptar = 1;
            bAceptar.setText("Agregar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);
        }
        
        if(this.itemDeInventario.isSelected()){
            cambiarVisibilidadCamposOrden(false);
            cambiarVisibilidadCamposInventario(true);
            cambiarVisibilidadCamposInventarioAgregar(true);
        
            botonAceptar = 1;
            bAceptar.setText("Agregar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);
        }
        
    }//GEN-LAST:event_bAgregarMouseReleased

    private void bModfMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bModfMouseReleased
       //muestra las opciones (sede o usuarios)
        if(evt.isPopupTrigger()){
         jefeDeTaller.show(evt.getComponent(),evt.getX(),evt.getY());
        } 
        
        if(this.ordenesDeTrabajo.isSelected()){
            cambiarVisibilidadCamposInventario(false);
            cambiarVisibilidadCamposOrden(true);
            cambiarVisibilidadCamposOrdenModificar(true);
        
            botonAceptar = 2;
            bAceptar.setText("Modificar");
            bAceptar.setVisible(true);              
            bAceptar.setEnabled(false);
        }
        
        if(this.itemDeInventario.isSelected()){
            cambiarVisibilidadCamposOrden(false);
            cambiarVisibilidadCamposInventario(true);
            cambiarVisibilidadCamposInventarioModf(true);
        
            botonAceptar = 2;
            bAceptar.setText("Modificar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(false);
        }
        
    }//GEN-LAST:event_bModfMouseReleased

    private void bConsulMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConsulMouseReleased
        //muestra las opciones (sede o usuarios)
        if(evt.isPopupTrigger()){
         jefeDeTaller.show(evt.getComponent(),evt.getX(),evt.getY());
        }
        
        if(this.ordenesDeTrabajo.isSelected()){
            cambiarVisibilidadCamposInventario(false);
            cambiarVisibilidadCamposOrden(true);
            cambiarVisibilidadCamposOrdenConsultar(true);
        
            botonAceptar = 3;
            bAceptar.setText("Consultar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(false);
        }
        
        if(this.itemDeInventario.isSelected()){
            cambiarVisibilidadCamposOrden(false);
            cambiarVisibilidadCamposInventario(true);
            cambiarVisibilidadCamposInventarioConsultar(true);
            
            botonAceptar = 3;
            bAceptar.setText("Consultar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(false);
        }
        
    }//GEN-LAST:event_bConsulMouseReleased

    private void bAnularMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAnularMouseReleased
        //muestra las opciones (sede o usuarios)
        if(evt.isPopupTrigger()){
            jefeDeTaller.show(evt.getComponent(),evt.getX(),evt.getY());
        }

        if(this.ordenesDeTrabajo.isSelected()){
            cambiarVisibilidadCamposInventario(false);
            cambiarVisibilidadCamposOrden(true);
            cambiarVisibilidadCamposOrdenAnular(true);

            botonAceptar = 4;
            bAceptar.setText("Anular");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);

        }

        if(this.itemDeInventario.isSelected()){
            cambiarVisibilidadCamposOrden(false);
            cambiarVisibilidadCamposInventario(true);
            cambiarVisibilidadCamposInventarioEliminar(true);

            botonAceptar = 4;
            bAceptar.setText("Eliminar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);
        }
    }//GEN-LAST:event_bAnularMouseReleased

    private void bAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bAnularActionPerformed

    private void bAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bAgregarActionPerformed

    private void tFechaEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tFechaEntregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tFechaEntregaActionPerformed

    private void tEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tEstadoActionPerformed

    private void ordenesDeTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordenesDeTrabajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ordenesDeTrabajoActionPerformed

    private void itemDeInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDeInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemDeInventarioActionPerformed

    public void cambiarVisibilidadCamposOrden(boolean varControl){
        
        labIDOrdenDeTrabajo.setVisible(varControl);
        labIDOrdenDeTrabajo.setText("ID Orden de trabajo: ");
        tIDOrdenDeTrabajo.setVisible(varControl);
        
        labNombreCliente.setVisible(varControl);
        labNombreCliente.setText("Nombre de producto: ");
        tNombreCliente.setVisible(varControl);
        
        labCosto.setVisible(varControl);
        labCosto.setText("Costo: ");
        tCosto.setVisible(varControl);
        
        labIDCliente.setVisible(varControl);
        labIDCliente.setText("ID Cliente: ");
        tIDCliente.setVisible(varControl);
        
        labDescripcion.setVisible(varControl);
        labDescripcion.setText("Descripción: ");
        tDescripcion.setVisible(varControl);
        
        labTelefonoCliente.setVisible(varControl);
        labTelefonoCliente.setText("Telefono Cliente: ");
        tTelefonoCliente.setVisible(varControl);
        
        labEstado.setVisible(varControl);
        labEstado.setText("Estado: ");
        tEstado.setVisible(varControl);
        
        labFechaDeEntrega.setVisible(varControl);
        labFechaDeEntrega.setText("Fecha de entrega: ");
        labFechaDeEntrega.setVisible(varControl);
        
        labIDJefeDeTaller.setVisible(varControl);
        labIDJefeDeTaller.setText("ID Jefe de taller:");
        tIDJefeDeTaller.setVisible(varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenAgregar(boolean varControl){
        
        labIDOrdenDeTrabajo.setVisible(varControl);
        labNombreCliente.setVisible(varControl);
        labCosto.setVisible(varControl);
        labIDCliente.setVisible(varControl);
        labDescripcion.setVisible(varControl);
        labTelefonoCliente.setVisible(varControl);
        labEstado.setVisible(varControl);
        labFechaDeEntrega.setVisible(varControl);
        labIDJefeDeTaller.setVisible(varControl);
        
        tIDOrdenDeTrabajo.setVisible(varControl);
        tIDOrdenDeTrabajo.setEnabled(varControl);
        tNombreCliente.setVisible(varControl);
        tNombreCliente.setEnabled(varControl);
        tCosto.setVisible(varControl);
        tCosto.setEnabled(varControl);
        tIDCliente.setVisible(varControl);
        tIDCliente.setEnabled(varControl);
        tDescripcion.setVisible(varControl);
        tDescripcion.setEnabled(varControl);
        tTelefonoCliente.setVisible(varControl);
        tTelefonoCliente.setEnabled(varControl);
        tEstado.setVisible(varControl);
        tEstado.setEnabled(varControl);
        tFechaEntrega.setVisible(varControl);
        tFechaEntrega.setEnabled(varControl);
        tIDJefeDeTaller.setVisible(varControl);
        tIDJefeDeTaller.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenModificar(boolean varControl){
        
        labIDOrdenDeTrabajo.setVisible(!varControl);
        labNombreCliente.setVisible(varControl);
        labCosto.setVisible(varControl);
        labIDCliente.setVisible(varControl);
        labDescripcion.setVisible(varControl);
        labTelefonoCliente.setVisible(varControl);
        labEstado.setVisible(varControl);
        labFechaDeEntrega.setVisible(varControl);
        labIDJefeDeTaller.setVisible(!varControl);
        
        tIDOrdenDeTrabajo.setVisible(!varControl);
        tIDOrdenDeTrabajo.setEnabled(!varControl);
        tNombreCliente.setVisible(varControl);
        tNombreCliente.setEnabled(varControl);
        tCosto.setVisible(varControl);
        tCosto.setEnabled(varControl);
        tIDCliente.setVisible(varControl);
        tIDCliente.setEnabled(varControl);
        tDescripcion.setVisible(varControl);
        tDescripcion.setEnabled(varControl);
        tTelefonoCliente.setVisible(varControl);
        tTelefonoCliente.setEnabled(varControl);
        tEstado.setVisible(varControl);
        tEstado.setEnabled(varControl);
        tFechaEntrega.setVisible(varControl);
        tFechaEntrega.setEnabled(varControl);
        tIDJefeDeTaller.setVisible(!varControl);
        tIDJefeDeTaller.setEnabled(!varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenConsultar(boolean varControl){
        
        labIDOrdenDeTrabajo.setVisible(varControl);
        labNombreCliente.setVisible(!varControl);
        labCosto.setVisible(!varControl);
        labIDCliente.setVisible(!varControl);
        labDescripcion.setVisible(!varControl);
        labTelefonoCliente.setVisible(!varControl);
        labEstado.setVisible(!varControl);
        labFechaDeEntrega.setVisible(!varControl);
        labIDJefeDeTaller.setVisible(!varControl);
        
        tIDOrdenDeTrabajo.setVisible(varControl);
        tIDOrdenDeTrabajo.setEnabled(varControl);
        tNombreCliente.setVisible(!varControl);
        tNombreCliente.setEnabled(!varControl);
        tCosto.setVisible(!varControl);
        tCosto.setEnabled(!varControl);
        tIDCliente.setVisible(!varControl);
        tIDCliente.setEnabled(!varControl);
        tDescripcion.setVisible(!varControl);
        tDescripcion.setEnabled(!varControl);
        tTelefonoCliente.setVisible(!varControl);
        tTelefonoCliente.setEnabled(!varControl);
        tEstado.setVisible(!varControl);
        tEstado.setEnabled(!varControl);
        tFechaEntrega.setVisible(!varControl);
        tFechaEntrega.setEnabled(!varControl);
        tIDJefeDeTaller.setVisible(!varControl);
        tIDJefeDeTaller.setEnabled(!varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenAnular(boolean varControl){
        
        labIDOrdenDeTrabajo.setVisible(varControl);
        labNombreCliente.setVisible(!varControl);
        labCosto.setVisible(!varControl);
        labIDCliente.setVisible(!varControl);
        labDescripcion.setVisible(!varControl);
        labTelefonoCliente.setVisible(!varControl);
        labEstado.setVisible(!varControl);
        labFechaDeEntrega.setVisible(!varControl);
        labIDJefeDeTaller.setVisible(!varControl);
        
        tIDOrdenDeTrabajo.setVisible(varControl);
        tIDOrdenDeTrabajo.setEnabled(varControl);
        tNombreCliente.setVisible(!varControl);
        tNombreCliente.setEnabled(!varControl);
        tCosto.setVisible(!varControl);
        tCosto.setEnabled(!varControl);
        tIDCliente.setVisible(!varControl);
        tIDCliente.setEnabled(!varControl);
        tDescripcion.setVisible(!varControl);
        tDescripcion.setEnabled(!varControl);
        tTelefonoCliente.setVisible(!varControl);
        tTelefonoCliente.setEnabled(!varControl);
        tEstado.setVisible(!varControl);
        tEstado.setEnabled(!varControl);
        tFechaEntrega.setVisible(!varControl);
        tFechaEntrega.setEnabled(!varControl);
        tIDJefeDeTaller.setVisible(!varControl);
        tIDJefeDeTaller.setEnabled(!varControl);
    }
    
    public void cambiarVisibilidadCamposInventario(boolean varControl){                
        
        labIDOrdenDeTrabajo.setVisible(varControl);
        labIDOrdenDeTrabajo.setText("ID Producto: ");
        tIDOrdenDeTrabajo.setVisible(varControl);
        
        labNombreCliente.setVisible(varControl);
        labNombreCliente.setText("Nombre de producto: ");
        tNombreCliente.setVisible(varControl);
        
        labCosto.setVisible(varControl);
        labCosto.setText("Valor unitario: ");
        tCosto.setVisible(varControl);
        
        labIDCliente.setVisible(varControl);
        labIDCliente.setText("Lote: ");
        tIDCliente.setVisible(varControl);
        
        labDescripcion.setVisible(varControl);
        labDescripcion.setText("Descripción: ");
        tDescripcion.setVisible(varControl);
        
        labTelefonoCliente.setVisible(!varControl);
        tTelefonoCliente.setVisible(!varControl);
        
        labEstado.setVisible(!varControl);
        tEstado.setVisible(!varControl);
        
        labFechaDeEntrega.setVisible(!varControl);
        labFechaDeEntrega.setVisible(!varControl);
        
        labIDJefeDeTaller.setVisible(varControl);
        labIDJefeDeTaller.setText("Cantidad lote: ");
        tIDJefeDeTaller.setVisible(varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioAgregar(boolean varControl){
        
        labIDOrdenDeTrabajo.setVisible(varControl);
        labNombreCliente.setVisible(varControl);
        labCosto.setVisible(varControl);
        labIDCliente.setVisible(varControl);
        labDescripcion.setVisible(varControl);
        labIDJefeDeTaller.setVisible(varControl);
        
        tIDOrdenDeTrabajo.setVisible(varControl);
        tIDOrdenDeTrabajo.setEnabled(varControl);
        tNombreCliente.setVisible(varControl);
        tNombreCliente.setEnabled(varControl);
        tCosto.setVisible(varControl);
        tCosto.setEnabled(varControl);
        tIDCliente.setVisible(varControl);
        tIDCliente.setEnabled(varControl);
        tDescripcion.setVisible(varControl);
        tDescripcion.setEnabled(varControl);
        tIDJefeDeTaller.setVisible(varControl);
        tIDJefeDeTaller.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioModf(boolean varControl){
        
        labIDOrdenDeTrabajo.setVisible(varControl);
        labNombreCliente.setVisible(varControl);
        labCosto.setVisible(varControl);
        labIDCliente.setVisible(varControl);
        labDescripcion.setVisible(varControl);
        labIDJefeDeTaller.setVisible(varControl);
        
        tIDOrdenDeTrabajo.setVisible(varControl);
        tIDOrdenDeTrabajo.setEnabled(!varControl);
        tNombreCliente.setVisible(varControl);
        tNombreCliente.setEnabled(varControl);
        tCosto.setVisible(varControl);
        tCosto.setEnabled(varControl);
        tIDCliente.setVisible(varControl);
        tIDCliente.setEnabled(!varControl);
        tDescripcion.setVisible(varControl);
        tDescripcion.setEnabled(varControl);
        tIDJefeDeTaller.setVisible(varControl);
        tIDJefeDeTaller.setEnabled(!varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioConsultar(boolean varControl){
        
        labIDOrdenDeTrabajo.setVisible(varControl);
        labNombreCliente.setVisible(!varControl);
        labCosto.setVisible(!varControl);
        labIDCliente.setVisible(!varControl);
        labDescripcion.setVisible(!varControl);
        labIDJefeDeTaller.setVisible(!varControl);
        
        tIDOrdenDeTrabajo.setVisible(varControl);
        tIDOrdenDeTrabajo.setEnabled(varControl);
        tNombreCliente.setVisible(!varControl);
        tNombreCliente.setEnabled(!varControl);
        tCosto.setVisible(!varControl);
        tCosto.setEnabled(!varControl);
        tIDCliente.setVisible(!varControl);
        tIDCliente.setEnabled(!varControl);
        tDescripcion.setVisible(!varControl);
        tDescripcion.setEnabled(!varControl);
        tIDJefeDeTaller.setVisible(!varControl);
        tIDJefeDeTaller.setEnabled(!varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioEliminar(boolean varControl){
        
        labIDOrdenDeTrabajo.setVisible(varControl);
        labNombreCliente.setVisible(!varControl);
        labCosto.setVisible(!varControl);
        labIDCliente.setVisible(!varControl);
        labDescripcion.setVisible(!varControl);
        labIDJefeDeTaller.setVisible(!varControl);
        
        tIDOrdenDeTrabajo.setVisible(varControl);
        tIDOrdenDeTrabajo.setEnabled(varControl);
        tNombreCliente.setVisible(!varControl);
        tNombreCliente.setEnabled(!varControl);
        tCosto.setVisible(!varControl);
        tCosto.setEnabled(!varControl);
        tIDCliente.setVisible(!varControl);
        tIDCliente.setEnabled(!varControl);
        tDescripcion.setVisible(!varControl);
        tDescripcion.setEnabled(!varControl);
        tIDJefeDeTaller.setVisible(!varControl);
        tIDJefeDeTaller.setEnabled(!varControl);
    }
    
    private boolean validarCamposAgregarOrdenDeTrabajo(){
        if(tIDOrdenDeTrabajo.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tNombreCliente.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tCosto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tIDCliente.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tDescripcion.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tIDJefeDeTaller.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tTelefonoCliente.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tEstado.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tFechaEntrega.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(labIDJefeDeTaller.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposModificarOrdenDeTrabajo(){
        if(tIDOrdenDeTrabajo.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tNombreCliente.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tCosto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tIDCliente.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tDescripcion.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tIDJefeDeTaller.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tTelefonoCliente.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tEstado.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tFechaEntrega.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(labIDJefeDeTaller.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposConsultarOrdenDeTrabajo(){
        if(tIDOrdenDeTrabajo.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposAnularOrdenDeTrabajo(){
        if(tIDOrdenDeTrabajo.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposAgregarInventario(){
        if(tIDOrdenDeTrabajo.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tNombreCliente.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tCosto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tIDCliente.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tDescripcion.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tIDJefeDeTaller.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposModificarInventario(){
        if(tIDOrdenDeTrabajo.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tNombreCliente.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tCosto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tIDCliente.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tDescripcion.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        if(tIDJefeDeTaller.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposConsultarInventario(){
        if(tIDOrdenDeTrabajo.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposEliminarInventario(){
        if(tIDOrdenDeTrabajo.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        return true;
    }
    
    public static void main(String args[]) {            
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new prinJefeDeTaller().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bAgregar;
    private javax.swing.JButton bAnular;
    private javax.swing.JButton bConsul;
    private javax.swing.JButton bModf;
    private javax.swing.JToggleButton bReportes;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel fechaYhora;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel iconUsu;
    private javax.swing.JCheckBoxMenuItem itemDeInventario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JPopupMenu jefeDeTaller;
    private javax.swing.JLabel labCosto;
    private javax.swing.JLabel labDescripcion;
    private javax.swing.JLabel labEstado;
    private javax.swing.JLabel labFechaDeEntrega;
    private javax.swing.JLabel labIDCliente;
    private javax.swing.JLabel labIDJefeDeTaller;
    private javax.swing.JLabel labIDOrdenDeTrabajo;
    private javax.swing.JLabel labLogo;
    private javax.swing.JLabel labNombreCliente;
    private javax.swing.JLabel labTelefonoCliente;
    private javax.swing.JCheckBoxMenuItem ordenesDeTrabajo;
    private javax.swing.JTextField tCosto;
    private javax.swing.JTextField tDescripcion;
    private javax.swing.JTextField tEstado;
    private javax.swing.JTextField tFechaEntrega;
    private javax.swing.JTextField tIDCliente;
    private javax.swing.JTextField tIDJefeDeTaller;
    private javax.swing.JTextField tIDOrdenDeTrabajo;
    private javax.swing.JTextField tNombreCliente;
    private javax.swing.JTextField tTelefonoCliente;
    // End of variables declaration//GEN-END:variables
}
