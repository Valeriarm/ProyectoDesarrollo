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
        
        desactivarCampos();
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
        labCantidad = new javax.swing.JLabel();
        labEspecificaciones = new javax.swing.JLabel();
        tEspecificaciones = new javax.swing.JTextField();
        bAceptar = new javax.swing.JButton();
        tCantidad = new javax.swing.JTextField();
        labNombreProducto = new javax.swing.JLabel();
        tNombreProducto = new javax.swing.JTextField();
        labValorUnitario = new javax.swing.JLabel();
        tValorUnitario = new javax.swing.JTextField();
        labDescripcion = new javax.swing.JLabel();
        tDescripcion = new javax.swing.JTextField();
        labRefProductoCB = new javax.swing.JLabel();
        cbRefProducto = new javax.swing.JComboBox<>();
        labEstadoOrden = new javax.swing.JLabel();
        cbEstadoOrden = new javax.swing.JComboBox<>();
        labIdOrden = new javax.swing.JLabel();
        cbIdOrden = new javax.swing.JComboBox<>();
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

        labCantidad.setText("Cantidad:");

        labEspecificaciones.setText("Especificaciones:");

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

        labRefProductoCB.setText("Ref Producto:");

        cbRefProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una referencia" }));

        labEstadoOrden.setText("Estado:");

        cbEstadoOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un estado", "En proceso", "Terminada", "Anulada" }));
        cbEstadoOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoOrdenActionPerformed(evt);
            }
        });

        labIdOrden.setText("Id Orden de Trabajo:");

        cbIdOrden.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una  orden" }));
        cbIdOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIdOrdenActionPerformed(evt);
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
                            .addComponent(labEspecificaciones)
                            .addComponent(labCantidad)
                            .addComponent(labNombreProducto)
                            .addComponent(labDescripcion)
                            .addComponent(labValorUnitario)
                            .addComponent(labRefProductoCB)
                            .addComponent(labEstadoOrden)
                            .addComponent(labIdOrden))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tEspecificaciones)
                            .addComponent(tNombreProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tDescripcion)
                            .addComponent(tValorUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbRefProducto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbEstadoOrden, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbIdOrden, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(bAceptar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbRefProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labRefProductoCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEstadoOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labEstadoOrden))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbIdOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labIdOrden))
                .addGap(202, 202, 202)
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
            .addGap(0, 598, Short.MAX_VALUE)
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
                .addContainerGap(674, Short.MAX_VALUE)
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
            desactivarCampos();
            cambiarVisibilidadCamposOrdenAgregar(true);
            llenarComboboxInventario();
        
            botonAceptar = 1;
            bAceptar.setText("Agregar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);
        }
        
        if(this.itemDeInventario.isSelected()){
            limpiarCampos();
            desactivarCampos();
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
            desactivarCampos();
            cambiarVisibilidadCamposOrdenModificar(true);
            llenarComboboxOrden();
        
            botonAceptar = 2;
            bAceptar.setText("Modificar");
            bAceptar.setVisible(true);              
            bAceptar.setEnabled(true);
        }
        
        if(this.itemDeInventario.isSelected()){
            limpiarCampos();
            desactivarCampos();
            cambiarVisibilidadCamposInventarioModf(true);
            llenarComboboxInventario();
        
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
            desactivarCampos();
            cambiarVisibilidadCamposOrdenConsultar(true);
            llenarComboboxOrden();
        
            botonAceptar = 3;
            bAceptar.setText("Consultar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);
        }
        
        if(this.itemDeInventario.isSelected()){
            limpiarCampos();
            desactivarCampos();
            cambiarVisibilidadCamposInventarioConsultar(true);
            llenarComboboxInventario();
            
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
            desactivarCampos();
            cambiarVisibilidadCamposOrdenAnular(true);
            llenarComboboxOrden();

            botonAceptar = 4;
            bAceptar.setText("Anular");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(true);

        }

        if(this.itemDeInventario.isSelected()){
            limpiarCampos();
            desactivarCampos();
            cambiarVisibilidadCamposInventarioEliminar(true);
            llenarComboboxInventario();

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
                agregarReferencia();
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

    private void cbEstadoOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoOrdenActionPerformed

    private void cbIdOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIdOrdenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbIdOrdenActionPerformed
    
    public void llenarComboboxOrden(){
        String [] ordenes = bD.listarOrden().split("$");
        for (int i=0; i==ordenes.length; i++){
            cbIdOrden.addItem(ordenes[i]);
        }
    }
    
    public void llenarComboboxInventario(){
        String [] inventario = bD.listarOrden().split("$");
        for (int i=0; i==inventario.length; i++){
            cbIdOrden.addItem(inventario[i]);
        }
    }
    
    public void desactivarCampos(){
        labCantidad.setVisible(false);
        tCantidad.setVisible(false);
        tCantidad.setEnabled(false);
        labEspecificaciones.setVisible(false);
        tEspecificaciones.setVisible(false);
        tEspecificaciones.setEnabled(false);
        labNombreProducto.setVisible(false);
        tNombreProducto.setVisible(false);
        tNombreProducto.setEnabled(false);
        labValorUnitario.setVisible(false);
        tValorUnitario.setVisible(false);
        tValorUnitario.setEnabled(false);
        labDescripcion.setVisible(false);
        tDescripcion.setVisible(false);
        tDescripcion.setEnabled(false);
        labRefProductoCB.setVisible(false);
        cbRefProducto.setVisible(false);
        cbRefProducto.setEnabled(false);
        labEstadoOrden.setVisible(false);
        cbEstadoOrden.setVisible(false);
        cbEstadoOrden.setEnabled(false);
        labIdOrden.setVisible(false);
        cbIdOrden.setVisible(false);
        cbIdOrden.setEnabled(false);
    }
    
    public void cambiarVisibilidadCamposOrdenAgregar(boolean varControl){
        labRefProductoCB.setVisible(varControl);
        cbRefProducto.setVisible(varControl);
        cbRefProducto.setEnabled(varControl);
        labEspecificaciones.setVisible(varControl);
        tEspecificaciones.setVisible(varControl);
        tEspecificaciones.setEnabled(varControl);
        labCantidad.setVisible(varControl);
        tCantidad.setVisible(varControl);
        tCantidad.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenModificar(boolean varControl){
        labIdOrden.setVisible(varControl);
        cbIdOrden.setVisible(varControl);
        cbIdOrden.setEnabled(varControl);
        labRefProductoCB.setVisible(varControl);
        cbRefProducto.setVisible(varControl);
        cbRefProducto.setEnabled(varControl);
        labEstadoOrden.setVisible(varControl);
        cbEstadoOrden.setVisible(varControl);
        cbEstadoOrden.setEnabled(varControl);
        labEspecificaciones.setVisible(varControl);
        tEspecificaciones.setVisible(varControl);
        tEspecificaciones.setEnabled(varControl);
        labCantidad.setVisible(varControl);
        tCantidad.setVisible(varControl);
        tCantidad.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenConsultar(boolean varControl){
        labIdOrden.setVisible(varControl);
        cbIdOrden.setVisible(varControl);
        cbIdOrden.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposOrdenAnular(boolean varControl){
        labIdOrden.setVisible(varControl);
        cbIdOrden.setVisible(varControl);
        cbIdOrden.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioAgregar(boolean varControl){
        labNombreProducto.setVisible(varControl);
        tNombreProducto.setVisible(varControl);
        tNombreProducto.setEnabled(varControl);
        labCantidad.setVisible(varControl);
        tCantidad.setVisible(varControl);
        tCantidad.setEnabled(varControl);
        labValorUnitario.setVisible(varControl);
        tValorUnitario.setVisible(varControl);
        tValorUnitario.setEnabled(varControl);
        labDescripcion.setVisible(varControl);
        tDescripcion.setVisible(varControl);
        tDescripcion.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioModf(boolean varControl){
        labRefProductoCB.setVisible(varControl);
        cbRefProducto.setVisible(varControl);
        cbRefProducto.setEnabled(varControl);
        labNombreProducto.setVisible(varControl);
        tNombreProducto.setVisible(varControl);
        tNombreProducto.setEnabled(varControl);
        labCantidad.setVisible(varControl);
        tCantidad.setVisible(varControl);
        tCantidad.setEnabled(varControl);
        labValorUnitario.setVisible(varControl);
        tValorUnitario.setVisible(varControl);
        tValorUnitario.setEnabled(varControl);
        labDescripcion.setVisible(varControl);
        tDescripcion.setVisible(varControl);
        tDescripcion.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioConsultar(boolean varControl){
        labRefProductoCB.setVisible(varControl);
        cbRefProducto.setVisible(varControl);
        cbRefProducto.setEnabled(varControl);
    }
    
    public void cambiarVisibilidadCamposInventarioEliminar(boolean varControl){
        labRefProductoCB.setVisible(varControl);
        cbRefProducto.setVisible(varControl);
        cbRefProducto.setEnabled(varControl);
    }
    
    private boolean validarCamposAgregarOrdenDeTrabajo(){
        if(cbRefProducto.getSelectedItem().equals("Seleccione una referencia")){
            JOptionPane.showMessageDialog(this, 
                    "Por favor seleccione una referencia");
            return false;
        }
        try{
            Integer.valueOf(tCantidad.getText());
        }catch(NumberFormatException nan){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese una cantidad valida");
            return false;
        }
        if(String.valueOf(tEspecificaciones.getText()).replace(" ", "").length()==0){
            JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese las especificaciones");
            return false;
        }
        return true;
    }
    
    private boolean validarCamposModificarOrdenDeTrabajo(){
        if(cbIdOrden.getSelectedItem().equals("Seleccione una orden")){
            JOptionPane.showMessageDialog(this,
                    "Por favor selecciona una orden");
            return false;
        }
        if(cbRefProducto.getSelectedItem().equals("Seleccione una referencia")){
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione una referencia");
            return false;
        }
        try{
            Integer.valueOf(tCantidad.getText());
        }catch(NumberFormatException nan){
            JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese un valor valido para la cantidad");
            return false;
        }
        if(tEspecificaciones.getText().replace(" ", "").length()==0){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese las especificaciones");
            return false;
        }
        if(cbEstadoOrden.getSelectedItem().equals("Seleccione un estado")){
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione un estado");
            return false;
        }
        return true;
    }
    
    private boolean validarCamposConsultarOrdenDeTrabajo(){
        if(cbIdOrden.getSelectedItem()=="Seleccione una orden"){
            JOptionPane.showMessageDialog(this, 
                    "Por favor seleccione una orden");
            return false;
        }
        return true;
    }
    
    private boolean validarCamposAnularOrdenDeTrabajo(){
        if(cbIdOrden.getSelectedItem().equals("Seleccione una orden")){
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione una orden");
            return false;
        }
        return true;
    }
    
    private boolean validarCamposAgregarInventario(){
        if(tNombreProducto.getText().replace(" ", "").length()==0){
            JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese el nombre del producto");
            return false;
        }
        try{
            Integer.valueOf(tCantidad.getText());
        }catch(NumberFormatException nan){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese una cantidad Valida");
            return false;
        }
        try {
            Float.valueOf(tValorUnitario.getText());
        }catch(NumberFormatException nan){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un valor unitario valido");
            return false;
        }
        if(tDescripcion.getText().replace(" ", "").length()==0){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese la descripcion del producto");
            return false;
        }
        return true;
    }
    
    private boolean validarCamposModificarInventario(){
        if(cbRefProducto.getSelectedItem().equals("Seleccione una referencia")){
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione una referencia");
            return false;
        }
        if(tNombreProducto.getText().replace(" ", "").length()==0){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese el nombre del producto");
        }
        try{
            Integer.valueOf(tCantidad.getText());
        }catch(NumberFormatException nan){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese una cantidad valida");
            return false;
        }
        try{
            Float.valueOf(tValorUnitario.getText());
        }catch(NumberFormatException nan){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un valor unitario valido");
            return false;
        }
        if(tDescripcion.getText().replace(" ", "").length()==0){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese la descripcion del producto");
            return false;
        }
        return true;
    }
    
    private boolean validarCamposConsultarInventario(){
        if(cbRefProducto.getSelectedItem().equals("Seleccione una referencia")){
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione una referencia de producto");
            return false;
        }
        return true;
    }
    
    private boolean validarCamposEliminarInventario(){
        if(cbRefProducto.getSelectedItem().equals("Seleccione una referencia")){
            JOptionPane.showMessageDialog(this, 
                    "Por favor seleccione una referencia");
            return false;
        }
        return true;
    }
    
    private void limpiarCampos(){
        tCantidad.setText("");
        tEspecificaciones.setText("");
        tNombreProducto.setText("");
        tValorUnitario.setText("");
        tDescripcion.setText("");
        cbIdOrden.removeAllItems();
        cbRefProducto.removeAllItems();
    }
    
    private boolean agregarOrdenDeTrabajo(){
        if(!validarCamposAgregarOrdenDeTrabajo()){
            return false;
        }
        /*id_orden se genera automaticamente*/
        String ref_producto = String.valueOf(cbRefProducto.getSelectedItem());
        int cantidad = Integer.valueOf(tCantidad.getText());
        String especificaciones = tEspecificaciones.getText();
        String estado = "En Proceso";
        int anio = Calendar.getInstance().get(Calendar.YEAR);
        int mes = Calendar.getInstance().get(Calendar.MONTH);
        int dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        String fecha_creacion = String.valueOf(anio)+"-"+String.valueOf(mes)
                +"-"+String.valueOf(dia);
        
        /*En espera de ser actualizada DBConnection
        String respuesta = bD.crearOrden(ref_producto, cantidad, especificaciones, 
                estado, fecha_creacion, null, this.idJefe);
        JOptionPane.showMessageDialog(this, respuesta);
        */
        return true;
    }
    
    private boolean agregarReferencia(){
        if(!validarCamposAgregarInventario()){
            return false;
        }
        /*id_producto se genera automaticamente*/
        String nombreProducto = tNombreProducto.getText();
        int cantidad = Integer.valueOf(tCantidad.getText());
        float valorUnitario = Float.parseFloat(tValorUnitario.getText());
        String Descripcion = tDescripcion.getText();
        
        /*En espera de ser actualizada en DBConnection
        String respuesta = bD.crearInventario(nombreProducto, valorUnitario,
                Descripcion, this.idJefe);
        JOptionPane.showMessageDialog(this, respuesta);
        */
        return true;
    }
    
    private boolean modificarOrdenDeTrabajo(){
        if(!validarCamposModificarOrdenDeTrabajo()){
            return false;
        }
        String idOrdenDeTrabajo = String.valueOf(cbRefProducto.getSelectedItem());
        String descripcion = tEspecificaciones.getText();
        String estado = cbEstadoOrden.getItemAt(cbEstadoOrden.getSelectedIndex());
        
        return true;
    }
    
    private boolean modificarItemDeInventario(){
        if(!validarCamposModificarInventario()){
            return false;
        }
        String referencia = String.valueOf(cbRefProducto.getSelectedItem());
        float valorUnitario = Float.parseFloat(tValorUnitario.getText());
        String especificaciones = tEspecificaciones.getText();
        /*
        String respuesta = bD.actualizarInventario(idProducto, nombreProducto, valorUnitario, descripcion, lote, cantidadLote);       
        JOptionPane.showMessageDialog(this, respuesta);
        */
        return true;
    }
    
    private boolean consultarOrdenDeTrabajo(){
        if(!validarCamposConsultarOrdenDeTrabajo()){
            return false;
        }
        String idOrden = String.valueOf(cbRefProducto.getSelectedItem());
        OrdenTrabajo orden = bD.leerOrdenPorId(idOrden);
        
        String mensaje = "ID Orden de trabajo: "+orden.getIdOrden()+"\n"
                + "Especificaciones: "+orden.getEspecficaciones()+"\n"
                +"Estado de la orden: "+orden.getEstadoOrden()+"\n"
                +"Fecha de creacion: "+orden.getFechaCreacion()+"\n"
                +"Fecha de entrega: "+orden.getFechaEntrega()+"\n"
                +"ID Jefe de taller: "+ orden.getIdJefe()+ "\n";
        JOptionPane.showMessageDialog(this, mensaje);        
        return true;
    }
    
    private boolean consultarItemDeInventario(){
        if(!validarCamposConsultarInventario()){
            return false;            
        }
        String idInventario = "";
        idInventario = String.valueOf(cbRefProducto.getSelectedItem());
        Inventario inventario = bD.leerInventarioPorId(idInventario);
        String mensaje = "ID producto: "+inventario.getIdProducto()+"\n"
                +"Nombre producto: "+inventario.getNombreProducto()+"\n"
                +"Valor unitario: "+inventario.getValorUnitario()+"\n"
                +"Descripcion: "+inventario.getDescripcion()+"\n"
                +"Cantidad lote: "+ inventario.getCantidad()+"\n"
                +"ID Jefe de taller: "+ inventario.getIdJefe()+"\n";
        JOptionPane.showMessageDialog(this, mensaje);   
        
        return true;
    }
    
    private boolean anularOrdenDeTrabajo(){
        if(!validarCamposAnularOrdenDeTrabajo()){
            return false;
        }
        String idOrden = String.valueOf(cbRefProducto.getSelectedItem());
        OrdenTrabajo orden = bD.leerOrdenPorId(idOrden);
        String mensaje = "ID Orden de trabajo: "+orden.getIdOrden()+"\n"
                + "Especificaciones: "+orden.getEspecficaciones()+"\n"
                +"Estado de la orden: "+orden.getEstadoOrden()+"\n"
                +"Fecha de creacion: "+orden.getFechaCreacion()+"\n"
                +"Fecha de entrega: "+orden.getFechaEntrega()+"\n"
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
        String idInventario = String.valueOf(cbRefProducto.getSelectedItem());
        Inventario inventario = bD.leerInventarioPorId(idInventario);
        String mensaje = "ID producto: "+inventario.getIdProducto()+"\n"
                +"Nombre producto: "+inventario.getNombreProducto()+"\n"
                +"Valor unitario: "+inventario.getValorUnitario()+"\n"
                +"Descripcion: "+inventario.getDescripcion()+"\n"
                +"Cantidad lote: "+ inventario.getCantidad()+"\n"
                +"ID Jefe de taller: "+ inventario.getIdJefe()+"\n";
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
    private javax.swing.JComboBox<String> cbEstadoOrden;
    private javax.swing.JComboBox<String> cbIdOrden;
    private javax.swing.JComboBox<String> cbRefProducto;
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
    private javax.swing.JLabel labEstadoOrden;
    private javax.swing.JLabel labIdOrden;
    private javax.swing.JLabel labLogo;
    private javax.swing.JLabel labNombreProducto;
    private javax.swing.JLabel labRefProductoCB;
    private javax.swing.JLabel labValorUnitario;
    private javax.swing.JCheckBoxMenuItem ordenesDeTrabajo;
    private javax.swing.JTextField tCantidad;
    private javax.swing.JTextField tDescripcion;
    private javax.swing.JTextField tEspecificaciones;
    private javax.swing.JTextField tNombreProducto;
    private javax.swing.JTextField tValorUnitario;
    // End of variables declaration//GEN-END:variables
}
