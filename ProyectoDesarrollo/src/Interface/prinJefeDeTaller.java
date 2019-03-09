/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controller.DBConnection;
import Model.OrdenTrabajo;
import Model.Inventario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import javax.swing.JOptionPane;
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
    private String idJefe;
    
    public prinJefeDeTaller(DBConnection baseDatos, String idJefe) {
        initComponents();
        this.bD = baseDatos;
        this.idJefe = idJefe;
        
        desactivarCampos(false);
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
        labRefProducto = new javax.swing.JLabel();
        labCantidad = new javax.swing.JLabel();
        labEspecificaciones = new javax.swing.JLabel();
        tRefProducto = new javax.swing.JTextField();
        tEspecificaciones = new javax.swing.JTextField();
        bAceptar = new javax.swing.JButton();
        tCantidad = new javax.swing.JTextField();
        labNombreProducto = new javax.swing.JLabel();
        tNombreProducto = new javax.swing.JTextField();
        labValorUnitario = new javax.swing.JLabel();
        tValorUnitario = new javax.swing.JTextField();
        labDescripcion = new javax.swing.JLabel();
        tDescripcion = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        fecha = new javax.swing.JLabel();
        fechaYhora = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();

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

        iconUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO workshop.png"))); // NOI18N

        labLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N
        labLogo.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labLogo)
                .addGap(317, 317, 317)
                .addComponent(iconUsu)
                .addGap(2, 2, 2))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labLogo, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(iconUsu)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        bAgregar.setForeground(new java.awt.Color(51, 51, 51));
        bAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO add.png"))); // NOI18N
        bAgregar.setText("Agregar");
        bAgregar.setToolTipText("Para activar los campos presionar de nuevo el boton");
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
        bModf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO edit.png"))); // NOI18N
        bModf.setText("Modificar");
        bModf.setToolTipText("Para activar los campos presionar de nuevo el boton");
        bModf.setBorderPainted(false);
        bModf.setContentAreaFilled(false);
        bModf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bModfMouseReleased(evt);
            }
        });

        bConsul.setForeground(new java.awt.Color(51, 51, 51));
        bConsul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO search.png"))); // NOI18N
        bConsul.setText("Consultar");
        bConsul.setToolTipText("Para activar los campos presionar de nuevo el boton");
        bConsul.setBorderPainted(false);
        bConsul.setContentAreaFilled(false);
        bConsul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bConsulMouseReleased(evt);
            }
        });

        bAnular.setForeground(new java.awt.Color(51, 51, 51));
        bAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO delete.png"))); // NOI18N
        bAnular.setText("Anular");
        bAnular.setToolTipText("Para activar los campos presionar de nuevo el boton");
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

        bReportes.setForeground(new java.awt.Color(51, 51, 51));
        bReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO report.png"))); // NOI18N
        bReportes.setText("Reportes");
        bReportes.setToolTipText("Para activar los campos presionar de nuevo el boton");
        bReportes.setBorderPainted(false);
        bReportes.setContentAreaFilled(false);
        bReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReportesActionPerformed(evt);
            }
        });

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
                .addGap(50, 50, 50))
        );

        jSplitPane2.setLeftComponent(jPanel3);

        labRefProducto.setText("Ref Producto:");

        labCantidad.setText("Cantidad:");

        labEspecificaciones.setText("Especificaciones:");

        tRefProducto.setToolTipText("");
        tRefProducto.setPreferredSize(new java.awt.Dimension(152, 24));
        tRefProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tRefProductoActionPerformed(evt);
            }
        });

        tEspecificaciones.setToolTipText("");

        bAceptar.setText("Agregar");
        bAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bAceptarMouseClicked(evt);
            }
        });

        tCantidad.setToolTipText("");
        tCantidad.setPreferredSize(new java.awt.Dimension(152, 24));
        tCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCantidadActionPerformed(evt);
            }
        });

        labNombreProducto.setText("Nombre Producto:");

        tNombreProducto.setToolTipText("");
        tNombreProducto.setPreferredSize(new java.awt.Dimension(152, 24));
        tNombreProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNombreProductoActionPerformed(evt);
            }
        });

        labValorUnitario.setText("Valor Unitario:");

        tValorUnitario.setToolTipText("");
        tValorUnitario.setPreferredSize(new java.awt.Dimension(152, 24));
        tValorUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tValorUnitarioActionPerformed(evt);
            }
        });

        labDescripcion.setText("Descripcion:");

        tDescripcion.setToolTipText("");
        tDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tDescripcionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labRefProducto)
                            .addComponent(labEspecificaciones)
                            .addComponent(labCantidad)
                            .addComponent(labNombreProducto)
                            .addComponent(labValorUnitario)
                            .addComponent(labDescripcion))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tRefProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tEspecificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(bAceptar)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labRefProducto)
                    .addComponent(tRefProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labCantidad)
                    .addComponent(tCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tEspecificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labEspecificaciones))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tNombreProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labNombreProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labValorUnitario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labDescripcion))
                .addGap(422, 422, 422)
                .addComponent(bAceptar)
                .addGap(20, 20, 20))
        );

        jSplitPane2.setRightComponent(jPanel4);

        jPanel1.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        fecha.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        fecha.setText("dd-mm-yyyy");

        fechaYhora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        fechaYhora.setText("Fecha  y hora:");

        hora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        hora.setText("hh:mm:ss");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addComponent(fecha)
                    .addContainerGap(304, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(fechaYhora)
                    .addContainerGap(369, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(142, 142, 142)
                    .addComponent(hora)
                    .addContainerGap(256, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(fecha, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(fechaYhora)
                    .addContainerGap(7, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addComponent(hora)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(789, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            limpiarCampos();
            cambiarVisibilidadCamposInventario(false);
            cambiarVisibilidadCamposOrden(true);
            cambiarVisibilidadCamposOrdenAgregar(true);
        
            botonAceptar = 1;
            bAceptar.setText("Agregar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);
        }
        
        if(this.itemDeInventario.isSelected()){
            limpiarCampos();
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
            limpiarCampos();
            cbAnioFechaEntrega.removeAllItems();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            for (int i=year; i<=year+5; i++){
                cbAnioFechaEntrega.addItem(Integer.toString(i));
            }
            cambiarVisibilidadCamposInventario(false);
            cambiarVisibilidadCamposOrden(true);
            cambiarVisibilidadCamposOrdenModificar(true);
        
            botonAceptar = 2;
            bAceptar.setText("Modificar");
            bAceptar.setVisible(true);              
            bAceptar.setEnabled(true);
        }
        
        if(this.itemDeInventario.isSelected()){
            limpiarCampos();
            cambiarVisibilidadCamposOrden(false);
            cambiarVisibilidadCamposInventario(true);
            cambiarVisibilidadCamposInventarioModf(true);
        
            botonAceptar = 2;
            bAceptar.setText("Modificar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);
        }
        
    }//GEN-LAST:event_bModfMouseReleased

    private void bConsulMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConsulMouseReleased
        //muestra las opciones (sede o usuarios)
        if(evt.isPopupTrigger()){
         jefeDeTaller.show(evt.getComponent(),evt.getX(),evt.getY());
        }
        
        if(this.ordenesDeTrabajo.isSelected()){
            limpiarCampos();
            cambiarVisibilidadCamposInventario(false);
            cambiarVisibilidadCamposOrden(true);
            cambiarVisibilidadCamposOrdenConsultar(true);
        
            botonAceptar = 3;
            bAceptar.setText("Consultar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);
        }
        
        if(this.itemDeInventario.isSelected()){
            limpiarCampos();
            cambiarVisibilidadCamposOrden(false);
            cambiarVisibilidadCamposInventario(true);
            cambiarVisibilidadCamposInventarioConsultar(true);
            
            botonAceptar = 3;
            bAceptar.setText("Consultar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);
        }
        
    }//GEN-LAST:event_bConsulMouseReleased

    private void bAnularMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAnularMouseReleased
        //muestra las opciones (sede o usuarios)
        if(evt.isPopupTrigger()){
            jefeDeTaller.show(evt.getComponent(),evt.getX(),evt.getY());
        }

        if(this.ordenesDeTrabajo.isSelected()){
            limpiarCampos();
            cambiarVisibilidadCamposInventario(false);
            cambiarVisibilidadCamposOrden(true);
            cambiarVisibilidadCamposOrdenAnular(true);

            botonAceptar = 4;
            bAceptar.setText("Anular");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);

        }

        if(this.itemDeInventario.isSelected()){
            limpiarCampos();
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

    private void ordenesDeTrabajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordenesDeTrabajoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ordenesDeTrabajoActionPerformed

    private void itemDeInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemDeInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemDeInventarioActionPerformed

    private void bReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bReportesActionPerformed

    private void bAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAceptarMouseClicked
        // TODO add your handling code here:
        switch(botonAceptar){
            //Agregar
            case 1:
            if(this.ordenesDeTrabajo.isSelected()){
                agregarOrdenDeTrabajo();
            }
            if(this.itemDeInventario.isSelected()){
                agregarItemDeInventario();
            }
            break;
            //Modificar
            case 2:
            if(this.ordenesDeTrabajo.isSelected()){
                modificarOrdenDeTrabajo();
            }
            if(this.itemDeInventario.isSelected()){
                modificarItemDeInventario();
            }
            break;
            //Consultar
            case 3:
            if(this.ordenesDeTrabajo.isSelected()){
                consultarOrdenDeTrabajo();
            }
            if(this.itemDeInventario.isSelected()){
                consultarItemDeInventario();
            }
            break;
            //Despedir
            case 4:
            if(this.ordenesDeTrabajo.isSelected()){
                anularOrdenDeTrabajo();
            }
            if(this.itemDeInventario.isSelected()){
                eliminarItemDeInventario();
            }
            break;
        }
    }//GEN-LAST:event_bAceptarMouseClicked

    private void tRefProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tRefProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tRefProductoActionPerformed

    private void tCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCantidadActionPerformed

    private void tNombreProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNombreProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNombreProductoActionPerformed

    private void tValorUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tValorUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tValorUnitarioActionPerformed

    private void tDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tDescripcionActionPerformed
    public void desactivarCampos(boolean varControl){
        labRefProducto.setVisible(varControl);
        tRefProducto.setVisible(varControl);
        labEspecificaciones.setVisible(varControl);
        tEspecificaciones.setVisible(varControl);
        
        labCantidad.setVisible(varControl);
    }
    public void cambiarVisibilidadCamposOrden(boolean varControl){
        
        labEspecificaciones.setVisible(varControl);
        tEspecificaciones.setVisible(varControl);
        
        labCantidad.setVisible(varControl);
        labCantidad.setVisible(varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenAgregar(boolean varControl){
        
        labRefProducto.setVisible(!varControl);
        labEspecificaciones.setVisible(varControl);
        labCantidad.setVisible(varControl);
        
        tRefProducto.setVisible(!varControl);
        tRefProducto.setEnabled(!varControl);
        tEspecificaciones.setVisible(varControl);
        tEspecificaciones.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenModificar(boolean varControl){
        
        labRefProducto.setVisible(varControl);
        labEspecificaciones.setVisible(varControl);
        labCantidad.setVisible(varControl);
        
        tRefProducto.setVisible(varControl);
        tRefProducto.setEnabled(varControl);
        tEspecificaciones.setVisible(varControl);
        tEspecificaciones.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenConsultar(boolean varControl){
        
        labRefProducto.setVisible(varControl);
        labEspecificaciones.setVisible(!varControl);
        labCantidad.setVisible(!varControl);
        
        tRefProducto.setVisible(varControl);
        tRefProducto.setEnabled(varControl);
        tEspecificaciones.setVisible(!varControl);
        tEspecificaciones.setEnabled(!varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenAnular(boolean varControl){
        
        labRefProducto.setVisible(varControl);
        labEspecificaciones.setVisible(!varControl);
        labCantidad.setVisible(!varControl);
        
        tRefProducto.setVisible(varControl);
        tRefProducto.setEnabled(varControl);
        tEspecificaciones.setVisible(!varControl);
        tEspecificaciones.setEnabled(!varControl);
    }
    
    public void cambiarVisibilidadCamposInventario(boolean varControl){                
        
        labRefProducto.setVisible(varControl);
        labRefProducto.setText("ID Producto: ");
        tRefProducto.setVisible(varControl);
        
        labEspecificaciones.setVisible(varControl);
        labEspecificaciones.setText("Descripción: ");
        tEspecificaciones.setVisible(varControl);
        
        labCantidad.setVisible(!varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioAgregar(boolean varControl){
        
        labRefProducto.setVisible(!varControl);
        labEspecificaciones.setVisible(varControl);
        
        tRefProducto.setVisible(!varControl);
        tRefProducto.setEnabled(!varControl);
        tEspecificaciones.setVisible(varControl);
        tEspecificaciones.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioModf(boolean varControl){
        
        labRefProducto.setVisible(varControl);
        labEspecificaciones.setVisible(varControl);
        
        tRefProducto.setVisible(varControl);
        tRefProducto.setEnabled(varControl);
        tEspecificaciones.setVisible(varControl);
        tEspecificaciones.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioConsultar(boolean varControl){
        
        labRefProducto.setVisible(varControl);
        labEspecificaciones.setVisible(!varControl);
        
        tRefProducto.setVisible(varControl);
        tRefProducto.setEnabled(varControl);
        tEspecificaciones.setVisible(!varControl);
        tEspecificaciones.setEnabled(!varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioEliminar(boolean varControl){
        
        labRefProducto.setVisible(varControl);
        labEspecificaciones.setVisible(!varControl);
        
        tRefProducto.setVisible(varControl);
        tRefProducto.setEnabled(varControl);
        tEspecificaciones.setVisible(!varControl);
        tEspecificaciones.setEnabled(!varControl);
    }
    
    private boolean validarCamposAgregarOrdenDeTrabajo(){
        if(tEspecificaciones.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        return true;
    }
    
    private boolean validarCamposModificarOrdenDeTrabajo(){
        if(tRefProducto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        try{
            parseInt(tRefProducto.getText());
        }catch(NumberFormatException nan){
            return false;
        }
        if(tEspecificaciones.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        return true;
    }
    
    private boolean validarCamposConsultarOrdenDeTrabajo(){
        if(tRefProducto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        try{
            parseInt(tRefProducto.getText());
        }catch(NumberFormatException nan){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposAnularOrdenDeTrabajo(){
        if(tRefProducto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        try{
            parseInt(tRefProducto.getText());
        }catch(NumberFormatException nan){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposAgregarInventario(){
        if(tRefProducto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        try{
            parseInt(tRefProducto.getText());
        }catch(NumberFormatException nan){
            return false;
        }
        if(tEspecificaciones.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposModificarInventario(){
        if(tRefProducto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        try{
            Float.parseFloat(tRefProducto.getText());
        }catch(NumberFormatException nan){
            return false;
        }
        if(tEspecificaciones.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposConsultarInventario(){
        if(tRefProducto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        try{
            parseInt(tRefProducto.getText());
        }catch(NumberFormatException nan){
            return false;
        }
        return true;
    }
    
    private boolean validarCamposEliminarInventario(){
        if(tRefProducto.getText().replaceAll(" ", "").length()==0){
            return false;
        }
        try{
            parseInt(tRefProducto.getText());
        }catch(NumberFormatException nan){
            return false;
        }
        return true;
    }
    
    private void limpiarCampos(){
        tRefProducto.setText("");
        tEspecificaciones.setText("");
    }
    
    private boolean agregarOrdenDeTrabajo(){
        if(!validarCamposAgregarOrdenDeTrabajo()){
            return false;
        }
        String especificaciones = "";
        especificaciones = tEspecificaciones.getText();
        
        /*En espera de ser actualizada DBConnection
        String respuesta = bD.crearOrden(especificaciones, estado, fechaEntrega, idJefe);
        JOptionPane.showMessageDialog(this, respuesta);
        */
        return true;
    }
    
    private boolean agregarItemDeInventario(){
        if(!validarCamposAgregarInventario()){
            return false;
        }
        String nombreProducto = "";
        nombreProducto = tNombreCliente.getText();
        float valorUnitario = 0;
        valorUnitario = Float.parseFloat(tCosto.getText());
        int lote = 0;
        lote = parseInt(tIDCliente.getText());
        String descripcion = "";
        descripcion = tEspecificaciones.getText();
        int cantidadLote = 0;
        cantidadLote = parseInt(tCantidadLote.getText());
        
        String respuesta =bD.crearInventario(nombreProducto, valorUnitario, 
                descripcion, lote, cantidadLote, idJefe);
        JOptionPane.showMessageDialog(this, respuesta);
        
        return true;
    }
    
    private boolean modificarOrdenDeTrabajo(){
        if(!validarCamposModificarOrdenDeTrabajo()){
            return false;
        }
        String idOrdenDeTrabajo = "";
        idOrdenDeTrabajo = tRefProducto.getText();
        String descripcion = "";
        descripcion = tEspecificaciones.getText();
        String estado = "";
        estado = cbEstado.getItemAt(cbEstado.getSelectedIndex());
        String anioFechaEntrega = cbAnioFechaInicio.getSelectedItem().toString();
        String mesFechaEntrega = cbMesFechaInicio.getSelectedItem().toString();
        String diaFechaEntrega = cbDiaFechaInicio.getSelectedItem().toString();
        String fechaEntrega = "";
        fechaEntrega = diaFechaEntrega+"/"+mesFechaEntrega+"/"+anioFechaEntrega;
        
        String respuesta = bD.actualizarOrden(idOrdenDeTrabajo, idCliente, nombreCliente, costo, esCliente, 
           descripcion, telefonoCliente, estado, fechaEntrega, idJefe);        
        JOptionPane.showMessageDialog(this, respuesta);
        
        return true;
    }
    
    private boolean modificarItemDeInventario(){
        if(!validarCamposModificarInventario()){
            return false;
        }
        String idProducto = "";
        idProducto = tRefProducto.getText();
        String nombreProducto = "";
        nombreProducto = tNombreCliente.getText();
        String idJefe = "";
        float valorUnitario = 0;
        valorUnitario = Float.parseFloat(tCosto.getText());
        String descripcion = "";
        descripcion = tEspecificaciones.getText();
        int lote = 0;
        lote = parseInt(tIDCliente.getText());
        int cantidadLote = 0;
        cantidadLote = parseInt(tCantidadLote.getText());
        String respuesta = bD.actualizarInventario(idProducto, nombreProducto, valorUnitario, descripcion, lote, cantidadLote);       
        JOptionPane.showMessageDialog(this, respuesta);
        return true;
    }
    
    private boolean consultarOrdenDeTrabajo(){
        if(!validarCamposConsultarOrdenDeTrabajo()){
            return false;
        }
        String idOrden = "";
        idOrden = tRefProducto.getText();
        OrdenTrabajo orden = bD.leerOrdenPorId(idOrden);
        String esCliente = "";
        String mensaje = "";
        
        mensaje = "ID Orden de trabajo: "+orden.getIdOrden()+"\n"
                +"Nombre Cliente: "+orden.getNombreCliente()+"\n"
                +"ID Cliente: "+orden.getIdCliente()+"\n"
                +"Es Cliente: "+esCliente+"\n"
                +"Telefono cliente: "+orden.getTelefonoCliente()+"\n"
                +"Estado de la orden: "+orden.getEstadoOrden()+"\n"
                +"Costo: "+orden.getValorOrden()+"\n"
                +"Descripcion: "+ orden.getDescripcionOrden()+"\n"
                +"ID Jefe de taller: "+ orden.getIdJefe()+ "\n";
        JOptionPane.showMessageDialog(this, mensaje);        
        
        return true;
    }
    
    private boolean consultarItemDeInventario(){
        if(!validarCamposConsultarInventario()){
            return false;            
        }
        String idInventario = "";
        idInventario = tRefProducto.getText();
        Inventario inventario = bD.leerInventarioPorId(idInventario);
        
        String mensaje = "";
        mensaje = "ID producto: "+inventario.getIdProducto()+"\n"
                +"Nombre producto: "+inventario.getNombreProducto()+"\n"
                +"Valor unitario: "+inventario.getValorUnitario()+"\n"
                +"Descripcion: "+inventario.getDescripcion()+"\n"
                +"Lote: " +inventario.getLote()+"\n"
                +"Cantidad lote: "+ inventario.getCantidadLote()+"\n"
                +"ID Jefe de taller: "+ inventario.getIdJefe()+"\n";
        JOptionPane.showMessageDialog(this, mensaje);   
        
        return true;
    }
    
    private boolean anularOrdenDeTrabajo(){
        if(!validarCamposAnularOrdenDeTrabajo()){
            return false;
        }
        String idOrden = "";
        idOrden = tRefProducto.getText();
        OrdenTrabajo orden = bD.leerOrdenPorId(idOrden);
        String mensaje = "";
        String esCliente = "";
        if(orden.getEsCliente() == 1){
            esCliente = "Cliente de la empresa";
        }else{
            esCliente = "No es cliente de la empresa";
        }
        
        if(orden.equals(null)){
            JOptionPane.showMessageDialog(this, "La orden de trabajo que intenta Anular no existe"); 
            return false;
        }
        mensaje = "Esta seguro que desea anular la orden de trabajo con: "+"\n"
                +"ID Orden de trabajo: "+orden.getIdOrden()+"\n"
                +"Nombre Cliente: "+orden.getNombreCliente()+"\n"
                +"ID Cliente: "+orden.getEsCliente()+"\n"
                +"Es Cliente: "+esCliente+"\n"
                +"Telefono cliente: "+orden.getTelefonoCliente()+"\n"
                +"Estado de la orden: "+orden.getEstadoOrden()+"\n"
                +"Costo: "+orden.getValorOrden()+"\n"
                +"Descripcion: "+ orden.getDescripcionOrden()+"\n"
                +"ID Jefe de taller: "+ orden.getIdJefe()+ "\n";        
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
            if(opcion == 0){
                String respuesta = bD.eliminarOrden(idOrden);
                JOptionPane.showMessageDialog(this, respuesta);
            }
        return true;
    }
    
    private boolean eliminarItemDeInventario(){
        if(!validarCamposEliminarInventario()){
            return false;
        }
        String idInventario = "";
        idInventario = tRefProducto.getText();
        Inventario inventario = bD.leerInventarioPorId(idInventario);
        String mensaje = "";
        if(inventario.equals(null)){
            JOptionPane.showMessageDialog(this, "La orden de trabajo que intenta Anular no existe"); 
            return false;
        }
        mensaje = "Esta seguro que desea anular la orden de trabajo con: "+"\n"
                +"ID item de inventario: "+inventario.getIdProducto()+"\n"
                +"Nombre producto: "+inventario.getNombreProducto()+"\n"
                +"Costo: "+inventario.getValorUnitario()+"\n"
                +"Lote: "+ inventario.getLote()+"\n"
                +"Cantidad lote:"+inventario.getCantidadLote()+"\n"
                +"Descripcion: : "+ inventario.getDescripcion()+ "\n";        
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
            if(opcion == 0){
                String respuesta = bD.eliminarInventario(idInventario);
                JOptionPane.showMessageDialog(this, respuesta);
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
    private javax.swing.JLabel labCantidad;
    private javax.swing.JLabel labDescripcion;
    private javax.swing.JLabel labEspecificaciones;
    private javax.swing.JLabel labLogo;
    private javax.swing.JLabel labNombreProducto;
    private javax.swing.JLabel labRefProducto;
    private javax.swing.JLabel labValorUnitario;
    private javax.swing.JCheckBoxMenuItem ordenesDeTrabajo;
    private javax.swing.JTextField tCantidad;
    private javax.swing.JTextField tDescripcion;
    private javax.swing.JTextField tEspecificaciones;
    private javax.swing.JTextField tNombreProducto;
    private javax.swing.JTextField tRefProducto;
    private javax.swing.JTextField tValorUnitario;
    // End of variables declaration//GEN-END:variables
}
