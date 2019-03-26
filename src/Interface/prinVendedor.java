/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controller.DBConnection;
import Model.Cotizacion;
import Model.OrdenTrabajo;
import Model.Inventario;
import Model.Venta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author Marthox
 * 
 */
public class prinVendedor extends javax.swing.JFrame {
    
    //la variable botonAceptar indica el uso que se le da al botón de la izquierda para cada boton principal
    //1:Agregar, 2:Modificar, 3:Consultar, 4:Anular 0 Eliminar, 0:nada
    private int botonAceptar = 0;  
    private DBConnection bD;
    private String idJefe;
    private String[] listaIds;
        //botones auxiliar       
    private int botonAux = 0;
    

    public prinVendedor(DBConnection baseDatos, String idVen) {
        //DBConnection baseDatos, String idJefe
        initComponents();
        bD = baseDatos;
        idJefe = idJefe;
        
        //Fecha
        Date fechaSist = new Date(); 
        SimpleDateFormat formato = new SimpleDateFormat("dd-MMM-yyyy");
        fecha.setText(formato.format(fechaSist));
        
        //Hora
        Timer tiempo = new Timer(100, new prinVendedor.hora());
        tiempo.start();
        

        bAceptar.setVisible(false);
        cambiarVisibilidadCampos(false);
       }

    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
            return true;
    }
    
    private boolean validarCamposVentas(String nombreCliente, String cedCliente, String telCliente, String valor, String descripcion,String producto){
    boolean validacion = true;
    String mensaje ="";
    
    if(nombreCliente.equals("")){mensaje = mensaje+"-Nombre de Cliente\n"; validacion = false;}
    if(cedCliente.equals("")){mensaje = mensaje+"-Cedula de Cliente\n"; validacion = false;}
    if(telCliente.equals("")){mensaje = mensaje+"-Telefono de Cliente\n"; validacion = false;}
    if(valor.equals("")){mensaje = mensaje+"-Valor\n"; validacion = false;}
    if(descripcion.equals("")){mensaje = mensaje+"-Descripción\n"; validacion = false;}
    
    if(!mensaje.equals("")) JOptionPane.showMessageDialog(this, mensaje);
         
    return validacion;
    }
       
    private boolean validarCamposCotizaciones(String nombreCliente,String telCliente, String valor, String email, String fecha, String producto){
    boolean validacion = true,fechaValida;
    String mensaje ="";
    
    if(nombreCliente.equals("")){mensaje = mensaje+"-Nombre de Cliente\n"; validacion = false;}
    if(telCliente.equals("")){mensaje = mensaje+"-Telefono de Cliente\n"; validacion = false;}
    if(valor.equals("")){mensaje = mensaje+"-Valor\n"; validacion = false;}
    if(email.equals("")){mensaje = mensaje+"-email\n"; validacion = false;}
    if(producto.equals("")){mensaje = mensaje+"-Producto\n"; validacion = false;}
    fechaValida = validarFecha(fecha);
    if(producto.equals("")){mensaje = mensaje+"-Producto\n"; validacion = false;}
    
    System.out.println(mensaje);
    
    if(!validacion){
        mensaje = "Los siguientes campos están vacios:\n"+mensaje;
        if(fechaValida) mensaje = "La fecha es invalida";
    }
    else{
        if(fechaValida){
        mensaje = "La fecha es invalida";
        validacion = false;
        }
    }
    
    System.out.println(mensaje);
    
    if(!validacion){
        mensaje = "Los siguientes campos están vacios:\n"+mensaje;
    }
    
    if(!mensaje.equals("")) JOptionPane.showMessageDialog(this, mensaje);
         
    return validacion;
    }
    
     private int obtenerMesNum(String mes){
        //ene, feb, mar, abr, may, jun, jul, ago, sep, oct, nov, dic
        switch(mes){
            case "ene":
                return 1;
            case "feb":
                return 2;
            case "mar":
                return 3;
            case "abr":
                return 4;
            case "may":
                return 5;
            case "jun":
                return 6;
            case "jul":
                return 7;
            case "ago":
                return 8;
            case "sep":
                return 9;
            case "oct":
                return 10;
            case "nov":
                return 11;
            case "dic":
                return 12;
        }
        return 0;
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
    //private prinVendedor() {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    
  
    
     //Retorna una lista con los Ids de las ventas en listaVentas la cual es una lista de strings,
    //donde N es la cantiadad de ventas
    private String[] obtenerListaIdsVentas(String[] listaVentas){
        String[] listaDeIds = new String[listaVentas.length];
        String[] venta;
        
        for(int i=0; i<(listaDeIds.length); i++){
            venta = listaVentas[i].split(",");
            listaDeIds[i] = venta[0];
        }
        
        return listaDeIds;
    }
    

    //Retorna una lista con los Ids de las cotizaciones en listaCotizacion el cual es una lista de strings,
    //donde N es la cantiadad de cotizacion
    private String[] obtenerListaIdsCotizacion(String[] listaCotizacion){
        String[] listaDeIds = new String[listaCotizacion.length];
        String[] cotizacion;
        
        for(int i=0; i<(listaDeIds.length); i++){
            cotizacion = listaCotizacion[i].split(",");
            listaDeIds[i] = cotizacion[0];
        }
        
        return listaDeIds;
    }
    
    //Retorna una lista con las opciones para combobox comboxEmple con el formato
    //{"nombre1 cedula1",..."nombreN cedulaN"} obtenidas de listaEmpleado el cual es una lista de strings del
    //tipo {"id1,nombre1,cedula1",..."idN,nombreN,cedulaN"} donde N es la cantiadad de empleados
    private String[] obtenerOpcionesVentas(String[] listaVentas){
        String[] opcionesVentas = new String[listaVentas.length+1];
        String[] venta;
        opcionesVentas[0] = "No seleccionado";
        
        for(int i=0,j=1; i<(listaVentas.length); i++,j++){
            venta = listaVentas[i].split(",");
            opcionesVentas[j] = venta[1]+" "+venta[2].replace("$","");
        }
        
        return opcionesVentas;
    }
    
    private String[] obtenerOpcionesCotizacion(String[] listaCotizacion){
        String[] opcionesCotizacion = new String[listaCotizacion.length+1];
        String[] cotizacion;
        opcionesCotizacion[0] = "No seleccionado";
        
        for(int i=0,j=1; i<(listaCotizacion.length); i++,j++){
            cotizacion = listaCotizacion[i].split(",");
            opcionesCotizacion[j] = cotizacion[1]+" "+cotizacion[2].replace("$","");
        }
        
        return opcionesCotizacion;
    }

    private void actualizarComboxCot(){
    
        String cotizaciones = bD.listarCotizaciones();
        
        if(cotizaciones.equals("")){//No hay cotizaciones
           String[] opciones = { "No seleccionado" };
           comboxCotizacion.setModel(new DefaultComboBoxModel (opciones));
    }else{//hay cotizaciones
    
    String[] listaCotizaciones = cotizaciones.split("\\$");
    listaIds = obtenerListaIdsCotizacion (listaCotizaciones);
    String[] opciones = obtenerOpcionesCotizacion(listaCotizaciones);
    comboxCotizacion.setModel(new DefaultComboBoxModel (opciones));
    }
    }

    private void actualizarComboxVenta(){
    
        String ventas = bD.listarVentas();
        
        if(ventas.equals("")){ //No hay ventas
           String[] opciones = { "No seleccionado" };
           comboxCotizacion.setModel(new DefaultComboBoxModel (opciones));
    }else{
        String[] listaVentas = ventas.split("\\$");
        listaIds = obtenerListaIdsVentas(listaVentas);
        String[] opciones = obtenerOpcionesVentas(listaVentas);
        comboxCotizacion.setModel(new DefaultComboBoxModel (opciones));
        }
    }      
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bAgregarVenta = new javax.swing.JButton();
        bModfVenta = new javax.swing.JButton();
        bConsulVenta = new javax.swing.JButton();
        bAnularVenta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bModfCot = new javax.swing.JButton();
        bAgregarCot = new javax.swing.JButton();
        bConsultarCot = new javax.swing.JButton();
        bAnularCot = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        fecha = new javax.swing.JLabel();
        fechaYhora = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        labIDcotizacion = new javax.swing.JLabel();
        comboxCotizacion = new javax.swing.JComboBox<>();
        labNombreCliente = new javax.swing.JLabel();
        tNombreCliente = new javax.swing.JTextField();
        tEmail = new javax.swing.JTextField();
        labEmail = new javax.swing.JLabel();
        labVlr = new javax.swing.JLabel();
        tVlr = new javax.swing.JTextField();
        tCCcliente = new javax.swing.JTextField();
        labCCcliente = new javax.swing.JLabel();
        tTel = new javax.swing.JTextField();
        labTel = new javax.swing.JLabel();
        labDescrip = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tDescrip = new javax.swing.JTextArea();
        labFecha = new javax.swing.JLabel();
        comboxDia = new javax.swing.JComboBox<>();
        comboxAno = new javax.swing.JComboBox<>();
        comboxMes = new javax.swing.JComboBox<>();
        labProducto = new javax.swing.JLabel();
        comboxProduc = new javax.swing.JComboBox<>();
        bAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.setToolTipText("");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel5.setText("Vendedor");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(463, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        bAgregarVenta.setForeground(new java.awt.Color(51, 51, 51));
        bAgregarVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO Invsell.png"))); // NOI18N
        bAgregarVenta.setBorderPainted(false);
        bAgregarVenta.setContentAreaFilled(false);
        bAgregarVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bAgregarVentaMouseClicked(evt);
            }
        });

        bModfVenta.setForeground(new java.awt.Color(51, 51, 51));
        bModfVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO Invsell.png"))); // NOI18N
        bModfVenta.setBorderPainted(false);
        bModfVenta.setContentAreaFilled(false);
        bModfVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bModfVentaMouseClicked(evt);
            }
        });
        bModfVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModfVentaActionPerformed(evt);
            }
        });

        bConsulVenta.setForeground(new java.awt.Color(51, 51, 51));
        bConsulVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO Invsell.png"))); // NOI18N
        bConsulVenta.setBorderPainted(false);
        bConsulVenta.setContentAreaFilled(false);
        bConsulVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bConsulVentaMouseClicked(evt);
            }
        });
        bConsulVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsulVentaActionPerformed(evt);
            }
        });

        bAnularVenta.setForeground(new java.awt.Color(51, 51, 51));
        bAnularVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO Invcancel.png"))); // NOI18N
        bAnularVenta.setBorderPainted(false);
        bAnularVenta.setContentAreaFilled(false);
        bAnularVenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bAnularVentaMouseClicked(evt);
            }
        });
        bAnularVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnularVentaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel1.setText("---------- Agregar ----------");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel2.setText("---------- Modificar ----------");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel3.setText("---------- Consultar ----------");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel4.setText("----------- Anular ------------");

        bModfCot.setForeground(new java.awt.Color(51, 51, 51));
        bModfCot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO Invpricing.png"))); // NOI18N
        bModfCot.setBorderPainted(false);
        bModfCot.setContentAreaFilled(false);
        bModfCot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bModfCotMouseClicked(evt);
            }
        });
        bModfCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModfCotActionPerformed(evt);
            }
        });

        bAgregarCot.setForeground(new java.awt.Color(51, 51, 51));
        bAgregarCot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO ordersadd.png"))); // NOI18N
        bAgregarCot.setBorderPainted(false);
        bAgregarCot.setContentAreaFilled(false);
        bAgregarCot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bAgregarCotMouseClicked(evt);
            }
        });
        bAgregarCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarCotActionPerformed(evt);
            }
        });

        bConsultarCot.setForeground(new java.awt.Color(51, 51, 51));
        bConsultarCot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO orderssearch.png"))); // NOI18N
        bConsultarCot.setToolTipText("");
        bConsultarCot.setBorderPainted(false);
        bConsultarCot.setContentAreaFilled(false);
        bConsultarCot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bConsultarCotMouseClicked(evt);
            }
        });
        bConsultarCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsultarCotActionPerformed(evt);
            }
        });

        bAnularCot.setForeground(new java.awt.Color(51, 51, 51));
        bAnularCot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO ordersabort.png"))); // NOI18N
        bAnularCot.setBorderPainted(false);
        bAnularCot.setContentAreaFilled(false);
        bAnularCot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bAnularCotMouseClicked(evt);
            }
        });
        bAnularCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAnularCotActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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
            .addGap(0, 202, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(78, 78, 78)
                    .addComponent(fecha)
                    .addContainerGap(64, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(fechaYhora)
                    .addContainerGap(125, Short.MAX_VALUE)))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(142, 142, 142)
                    .addComponent(hora)
                    .addContainerGap(16, Short.MAX_VALUE)))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(bAgregarVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAgregarCot, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(bModfVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bModfCot, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(bConsulVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bConsultarCot, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(bAnularVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAnularCot, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bAgregarVenta)
                    .addComponent(bAgregarCot, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bModfCot, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bModfVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bConsulVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bConsultarCot, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bAnularCot, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bAnularVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        labIDcotizacion.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labIDcotizacion.setForeground(new java.awt.Color(255, 255, 255));
        labIDcotizacion.setText("ID cotización:");

        comboxCotizacion.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        comboxCotizacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No seleccionado" }));
        comboxCotizacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboxCotizacionItemStateChanged(evt);
            }
        });
        comboxCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxCotizacionActionPerformed(evt);
            }
        });

        labNombreCliente.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labNombreCliente.setForeground(new java.awt.Color(255, 255, 255));
        labNombreCliente.setText("Nombre cliente:");

        tNombreCliente.setPreferredSize(new java.awt.Dimension(164, 20));
        tNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNombreClienteKeyTyped(evt);
            }
        });

        tEmail.setToolTipText("");
        tEmail.setPreferredSize(new java.awt.Dimension(164, 20));

        labEmail.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labEmail.setForeground(new java.awt.Color(255, 255, 255));
        labEmail.setText("Email:");

        labVlr.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labVlr.setForeground(new java.awt.Color(255, 255, 255));
        labVlr.setText("Valor:");

        tVlr.setToolTipText("");
        tVlr.setPreferredSize(new java.awt.Dimension(164, 20));
        tVlr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tVlrActionPerformed(evt);
            }
        });
        tVlr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tVlrKeyTyped(evt);
            }
        });

        tCCcliente.setPreferredSize(new java.awt.Dimension(164, 20));
        tCCcliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tCCclienteKeyTyped(evt);
            }
        });

        labCCcliente.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labCCcliente.setForeground(new java.awt.Color(255, 255, 255));
        labCCcliente.setText("Cedula cliente:");

        tTel.setToolTipText("");
        tTel.setPreferredSize(new java.awt.Dimension(164, 20));
        tTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tTelKeyTyped(evt);
            }
        });

        labTel.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labTel.setForeground(new java.awt.Color(255, 255, 255));
        labTel.setText("Telefono cliente:");

        labDescrip.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labDescrip.setForeground(new java.awt.Color(255, 255, 255));
        labDescrip.setText("Descripción venta:");

        tDescrip.setColumns(20);
        tDescrip.setRows(5);
        jScrollPane1.setViewportView(tDescrip);

        labFecha.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labFecha.setForeground(new java.awt.Color(255, 255, 255));
        labFecha.setText("Fecha:");

        comboxDia.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        comboxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        comboxDia.setPreferredSize(new java.awt.Dimension(37, 22));

        comboxAno.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        comboxAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944" }));
        comboxAno.setPreferredSize(new java.awt.Dimension(49, 22));

        comboxMes.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        comboxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic" }));
        comboxMes.setPreferredSize(new java.awt.Dimension(45, 22));

        labProducto.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labProducto.setForeground(new java.awt.Color(255, 255, 255));
        labProducto.setText("Producto:");

        comboxProduc.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        comboxProduc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No seleccionado" }));
        comboxProduc.setMinimumSize(new java.awt.Dimension(152, 20));
        comboxProduc.setName(""); // NOI18N
        comboxProduc.setPreferredSize(new java.awt.Dimension(190, 22));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labFecha)
                        .addGap(110, 110, 110)
                        .addComponent(comboxDia, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboxMes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboxAno, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(labEmail)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(labIDcotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(comboxCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(labTel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tTel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(labCCcliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tCCcliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(labNombreCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tNombreCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labProducto)
                                    .addComponent(labVlr)
                                    .addComponent(labDescrip))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tVlr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboxProduc, 0, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                        .addContainerGap())))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labIDcotizacion)
                    .addComponent(comboxCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNombreCliente)
                    .addComponent(tNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCCcliente)
                    .addComponent(tCCcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labEmail))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tVlr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labVlr))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labFecha)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labProducto)
                    .addComponent(comboxProduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labDescrip)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        bAceptar.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        bAceptar.setText("Agregar");
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bAceptar)
                .addGap(191, 191, 191))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAceptar)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTelKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){
            evt.consume();
        }
    }//GEN-LAST:event_tTelKeyTyped

    private void tCCclienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCCclienteKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){
            evt.consume();
        }
    }//GEN-LAST:event_tCCclienteKeyTyped

    private void tVlrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tVlrKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){
            evt.consume();
        }
    }//GEN-LAST:event_tVlrKeyTyped

    private void tNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNombreClienteKeyTyped
        char c = evt.getKeyChar();

        if(c != ' '){
            if(c<'A' || c>'Z'){
                if(c<'a' || c>'z'){
                    evt.consume();
                }
            }
        }
    }//GEN-LAST:event_tNombreClienteKeyTyped

    private void comboxCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxCotizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxCotizacionActionPerformed

    private void comboxCotizacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboxCotizacionItemStateChanged
        if(this.labIDcotizacion.getText() == "ID factura"){
            String id = listaIds[comboxCotizacion.getSelectedIndex()-1];
            Venta vent = bD.leerVentaPorId(id);
            System.out.println(vent);
            
            if(comboxCotizacion.getSelectedIndex() == 0){
                bAceptar.setEnabled(false);

                if(botonAceptar==2){ 
                    //limpiarCampos(); 
                    habilitarCamposModfVenta(false);
                    this.comboxCotizacion.setEnabled(false);}
            }else{
                bAceptar.setEnabled(true);
                
                if(vent != null){
                llenarCamposModfVenta();
            }
                habilitarCamposModfVenta(true);
            }

        }

        if(this.labIDcotizacion.getText() == "ID cotizacion"){
            String id = listaIds[comboxCotizacion.getSelectedIndex()-1];
            Cotizacion cot = bD.leerCotizacionPorId(id);
            System.out.println(cot);
            
            if(comboxCotizacion.getSelectedIndex() == 0){
                bAceptar.setEnabled(false);

                if(botonAceptar==2){ 
                    //limpiarCampos(); 
                    habilitarCamposModfCotizacion(false);
                    this.comboxCotizacion.setEnabled(false);
                }
            }else{
                bAceptar.setEnabled(true);

               if (cot != null) {
                llenarCamposModfCotizacion();
                }
               
                habilitarCamposModfCotizacion(true);
            }

        }
    }//GEN-LAST:event_comboxCotizacionItemStateChanged

    private void bAgregarVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAgregarVentaMouseClicked
        cambiarVisibilidadCampos(false);
        cambiarVisibilidadCamposVentas(true);
        botonAux = 1;
        
        botonAceptar = 1;
        bAceptar.setText("Agregar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(true);
    }//GEN-LAST:event_bAgregarVentaMouseClicked

    private void bAgregarCotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAgregarCotMouseClicked
           
        cambiarVisibilidadCampos(false);
        cambiarVisibilidadCamposCot(true);
        botonAux = 2;
        
        botonAceptar = 1;
        bAceptar.setText("Agregar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(true);
    }//GEN-LAST:event_bAgregarCotMouseClicked

    private void bModfVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bModfVentaMouseClicked
        
        cambiarVisibilidadCampos(false);
        CambiarVisibilidadModCamposVenta(true);
        botonAux = 3;
        
        botonAceptar = 2;
        bAceptar.setText("Modificar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bModfVentaMouseClicked

    private void bModfCotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bModfCotMouseClicked
        
        cambiarVisibilidadCampos(false);
        CambiarVisibilidadModCamposCot(true);
        botonAux = 4;
        
        botonAceptar = 2;
        bAceptar.setText("Modificar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bModfCotMouseClicked

    private void bConsulVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConsulVentaMouseClicked
        botonAux = 5;
        
        cambiarVisibilidadCampos(false);
        camposVentasConsulta(true);
        
        
        botonAceptar = 3;
        bAceptar.setText("Consultar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bConsulVentaMouseClicked

    private void bConsultarCotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConsultarCotMouseClicked
        botonAux = 6;
        
        cambiarVisibilidadCampos(false);
        camposCotConsulta(true);

        botonAceptar = 3;
        bAceptar.setText("Consultar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bConsultarCotMouseClicked

    private void bAnularVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAnularVentaMouseClicked
        cambiarVisibilidadCampos(false);
        camposVentasConsulta(true);
        botonAux = 7;
        
        botonAceptar = 4;
        bAceptar.setText("Despedir");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bAnularVentaMouseClicked

    private void bAnularCotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAnularCotMouseClicked
        cambiarVisibilidadCampos(false);
        camposCotConsulta(true);
        botonAux = 8;

        botonAceptar = 4;
        bAceptar.setText("Despedir");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bAnularCotMouseClicked

    private void tVlrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tVlrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tVlrActionPerformed

    private void bModfVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModfVentaActionPerformed
       this.actualizarComboxVenta();
    }//GEN-LAST:event_bModfVentaActionPerformed

    private void bAgregarCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarCotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bAgregarCotActionPerformed

    private void bAnularVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnularVentaActionPerformed
        // TODO add your handling code here:
        this.actualizarComboxVenta();
    }//GEN-LAST:event_bAnularVentaActionPerformed

    private void bModfCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModfCotActionPerformed
        // TODO add your handling code here:
        this.actualizarComboxCot();
    }//GEN-LAST:event_bModfCotActionPerformed

    private void bConsultarCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultarCotActionPerformed
        // TODO add your handling code here:
        this.actualizarComboxCot();
    }//GEN-LAST:event_bConsultarCotActionPerformed

    private void bAnularCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnularCotActionPerformed
        // TODO add your handling code here:
        this.actualizarComboxCot();
    }//GEN-LAST:event_bAnularCotActionPerformed

    private void bConsulVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsulVentaActionPerformed
        // TODO add your handling code here:
        this.actualizarComboxVenta();
    }//GEN-LAST:event_bConsulVentaActionPerformed

    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        // TODO add your handling code here:
        
        if(botonAceptar==1){
           if(botonAux == 1){
           this.agregarVenta();}
         
           else{
           if(botonAux == 2){
           this.agregarCotizacion();}
           }
           }
        else if(botonAceptar == 2){
            if(botonAux == 3){
                String id = listaIds[comboxCotizacion.getSelectedIndex()-1];
                Venta vent = bD.leerVentaPorId(id);
                
                 /**hay que seleccionar el tipo de usuario*/
                if(vent != null){
                    modificarVenta();
                }
                
                this.modificarVenta();
            }
            else{
                if(botonAux == 4){
                    String id = listaIds[comboxCotizacion.getSelectedIndex()-1];
                    Cotizacion cot = bD.leerCotizacionPorId(id);
                    
                     /**hay que seleccionar el tipo de usuario*/
                if (cot != null) {
                    modificarCotizacion();
                }
                    
                    this.modificarCotizacion();
                }
              }
            } else if(botonAceptar == 3){
                this.consultar();
            }else if(botonAceptar == 4){
                this.deshabilitar();
            }
            
    }//GEN-LAST:event_bAceptarActionPerformed

    
     public void cambiarVisibilidadCampos(boolean varControl){
        labIDcotizacion.setVisible(false);
        comboxCotizacion.setVisible(false);

      //elementos para ventas y cotizacion
      labNombreCliente.setVisible(varControl);
      tNombreCliente.setVisible(varControl);
      tNombreCliente.setText("");
      labVlr.setVisible(varControl);
      tVlr.setVisible(varControl);
      tVlr.setText("");
      labTel.setVisible(varControl);
      tTel.setVisible(varControl);
      tTel.setText("");
      labFecha.setVisible(varControl);
      comboxDia.setVisible(varControl);
      comboxMes.setVisible(varControl);
      comboxAno.setVisible(varControl);
      labProducto.setVisible(varControl);
      comboxProduc.setVisible(varControl);
      
      //elementos ventas
      labDescrip.setVisible(varControl);
      tDescrip.setVisible(varControl);
      tDescrip.setText("");
      jScrollPane1.setVisible(varControl);
      labCCcliente.setVisible(varControl);
      tCCcliente.setVisible(varControl);
      tCCcliente.setText("");
      
      //elementos para cotizacion
      labEmail.setVisible(varControl);
      tEmail.setVisible(varControl);
      tEmail.setEnabled(varControl);
      tEmail.setText("");
      
      
    }
     
    public void cambiarVisibilidadCamposVentas(boolean varControl){
     
     labIDcotizacion.setVisible(false);
        comboxCotizacion.setVisible(false);

      //elementos para ventas y cotizacion
      labNombreCliente.setVisible(varControl);
      tNombreCliente.setVisible(varControl);
      tNombreCliente.setEnabled(varControl);
      tNombreCliente.setText("");
      labVlr.setVisible(varControl);
      tVlr.setVisible(varControl);
      tVlr.setEnabled(varControl);
      tVlr.setText("");
      labTel.setVisible(varControl);
      tTel.setVisible(varControl);
      tTel.setEnabled(varControl);
      tTel.setText("");
      labFecha.setVisible(varControl);
      comboxDia.setVisible(varControl);
      comboxMes.setVisible(varControl);
      comboxAno.setVisible(varControl);
      labProducto.setVisible(varControl);
      comboxProduc.setVisible(varControl);
      
      //elementos ventas
      labDescrip.setVisible(varControl);
      tDescrip.setVisible(varControl);
      tDescrip.setText("");
      jScrollPane1.setVisible(varControl);
      labCCcliente.setVisible(varControl);
      tCCcliente.setVisible(varControl);
      tCCcliente.setText("");
      
      //elementos para cotizacion
      labEmail.setVisible(!varControl);
      tEmail.setVisible(!varControl);
      tEmail.setEnabled(!varControl);
      tEmail.setText("");
         
     }
    
    //Quita los campos usados en la función consultar y despedir (ej:empleado)
    //varControl: true para indicar que sea visible, false para lo opuesto
    public void cambiarVisibilidadCamposCot(boolean varControl){           
        labIDcotizacion.setVisible(false);
        comboxCotizacion.setVisible(false);

      //elementos para ventas  y cotizacion
      labNombreCliente.setVisible(varControl);
      tNombreCliente.setVisible(varControl);
      tNombreCliente.setEnabled(varControl);
      tNombreCliente.setText("");
      labVlr.setVisible(varControl);
      tVlr.setVisible(varControl);
      tVlr.setEnabled(varControl);
      tVlr.setText("");
      labTel.setVisible(varControl);
      tTel.setVisible(varControl);
      tTel.setEnabled(varControl);
      tTel.setText("");
      
      //elementos para ventas
      labDescrip.setVisible(!varControl);
      tDescrip.setVisible(!varControl);
      tDescrip.setEnabled(varControl);
      tDescrip.setText("");
      jScrollPane1.setVisible(!varControl);
      labCCcliente.setVisible(!varControl);
      tCCcliente.setVisible(!varControl);
      tCCcliente.setEnabled(varControl);
      tCCcliente.setText("");
      
      //elementos para cotizacion
      labEmail.setVisible(varControl);
      tEmail.setVisible(varControl);
      tEmail.setEnabled(varControl);
      tEmail.setText("");

    }
    
    public void CambiarVisibilidadModCamposVenta(boolean varControl){
       
      labIDcotizacion.setText("ID factura:");
      labIDcotizacion.setVisible(varControl);
      comboxCotizacion.setVisible(varControl);
              
      //elementos para ventas y cotizacion 
     
      labNombreCliente.setVisible(varControl);
      tNombreCliente.setVisible(varControl);
      tNombreCliente.setText("");
      tNombreCliente.setEnabled(!varControl);
      labVlr.setVisible(varControl);
      tVlr.setVisible(varControl);
      tVlr.setText("");
      tVlr.setEnabled(!varControl);
      labTel.setVisible(varControl);
      tTel.setVisible(varControl);
      tTel.setText("");
      tTel.setEnabled(!varControl);
      
     //elementos para ventas 
      labDescrip.setVisible(varControl);
      tDescrip.setVisible(varControl);
      tDescrip.setEnabled(varControl);
      tDescrip.setText("");
      tDescrip.setEnabled(!varControl);
      jScrollPane1.setVisible(varControl);
      labCCcliente.setVisible(varControl);
      tCCcliente.setVisible(varControl);
      tCCcliente.setEnabled(varControl);
      tCCcliente.setText("");
      tCCcliente.setEnabled(!varControl);
      
      //elementos para cotizacion

     labEmail.setVisible(!varControl);
     tEmail.setVisible(!varControl);
     tEmail.setEnabled(varControl);
     tEmail.setText("");

    }
    
    public void CambiarVisibilidadModCamposCot(boolean varControl){
       

      labIDcotizacion.setText("ID cotizacion:");
      labIDcotizacion.setVisible(varControl);
      comboxCotizacion.setVisible(varControl);

      //elementos para ventas y cotizacion 
     
      labNombreCliente.setVisible(varControl);
      tNombreCliente.setVisible(varControl);
      tNombreCliente.setText("");
      tNombreCliente.setEnabled(!varControl);
      labVlr.setVisible(varControl);
      tVlr.setVisible(varControl);
      tVlr.setText("");
      tVlr.setEnabled(!varControl);
      labTel.setVisible(varControl);
      tTel.setVisible(varControl);
      tTel.setText("");
      tTel.setEnabled(!varControl);
      
     //elementos para ventas 
      labDescrip.setVisible(!varControl);
      tDescrip.setVisible(!varControl);
      tDescrip.setText("");
      tDescrip.setEnabled(!!varControl);
      jScrollPane1.setVisible(!varControl);
      labCCcliente.setVisible(!varControl);
      tCCcliente.setVisible(!varControl);
      tCCcliente.setText("");
      tCCcliente.setEnabled(!varControl);
      
      //elementos para cotizacion

     labEmail.setVisible(varControl);
     tEmail.setVisible(varControl);
     tEmail.setEnabled(!varControl);
     tEmail.setText("");

    }
    
    public void camposVentasConsulta(boolean varControl){
    
        labIDcotizacion.setText("ID factura:");   
        
        labIDcotizacion.setVisible(varControl);
        comboxCotizacion.setVisible(varControl);

      //elementos para ventas y cotizacion 
     
      labNombreCliente.setVisible(!varControl);
      tNombreCliente.setVisible(!varControl);
      tNombreCliente.setText("");
      tNombreCliente.setEnabled(!varControl);
      labVlr.setVisible(!varControl);
      tVlr.setVisible(!varControl);
      tVlr.setText("");
      tVlr.setEnabled(!varControl);
      labTel.setVisible(!varControl);
      tTel.setVisible(!varControl);
      tTel.setText("");
      tTel.setEnabled(!varControl);
      
     //elementos para ventas 
      labDescrip.setVisible(!varControl);
      tDescrip.setVisible(!varControl);
      tDescrip.setText("");
      tDescrip.setEnabled(!varControl);
      jScrollPane1.setVisible(!varControl);
      labCCcliente.setVisible(!varControl);
      tCCcliente.setVisible(!varControl);
      tCCcliente.setText("");
      tCCcliente.setEnabled(!varControl);
      
      //elementos para cotizacion

     labEmail.setVisible(!varControl);
     tEmail.setVisible(!varControl);
     tEmail.setEnabled(!varControl);
     tEmail.setText("");

    }
    
    public void camposCotConsulta(boolean varControl){
    

      labIDcotizacion.setText("ID cotizacion:");  
        
      labIDcotizacion.setVisible(varControl);
      comboxCotizacion.setVisible(varControl);

      //elementos para ventas  
      labEmail.setVisible(!varControl);
      tEmail.setVisible(!varControl);
      labNombreCliente.setVisible(!varControl);
      tNombreCliente.setVisible(!varControl);
      labVlr.setVisible(!varControl);
      tVlr.setVisible(!varControl);
      labDescrip.setVisible(!varControl);
      tDescrip.setVisible(!varControl);
      jScrollPane1.setVisible(!varControl);
      labCCcliente.setVisible(!varControl);
      tCCcliente.setVisible(!varControl);
      labTel.setVisible(!varControl);
      tTel.setVisible(!varControl);
      
      //elementos para cotizacion

      labDescrip.setVisible(!varControl);
      tDescrip.setVisible(!varControl);
      jScrollPane1.setVisible(!varControl);

    }
    
    private void limpiarCampos(){

       tDescrip.setText("");
       tTel.setText("");
       tCCcliente.setText("");
       tVlr.setText("");
       tNombreCliente.setText("");
       tEmail.setText("");
       
    } 
    
    private void llenarCamposModfVenta(){
    
       String id = listaIds[comboxCotizacion.getSelectedIndex()-1];
       Venta vent = bD.leerVentaPorId(id);
       
       tDescrip.setText(vent.getDescripcionVenta());
       tTel.setText(vent.getTelefonoCliente());
       tCCcliente.setText(vent.getCedulaCliente());
       tVlr.setText(Float.toString(vent.getValorVenta()));
       tNombreCliente.setText(vent.getNombreCliente());
       comboxProduc.setSelectedItem(vent.getNombreProducto());
    }
    
    
    private void llenarCamposModfCotizacion(){
       String id = listaIds[comboxCotizacion.getSelectedIndex()-1];
       Cotizacion cot = bD.leerCotizacionPorId(id);
        
       tTel.setText(cot.getTelefonoCliente());
       tEmail.setText(cot.getEmail());
       tNombreCliente.setText(cot.getNombreCliente());
       tVlr.setText(Float.toString(cot.getValorUnitario()));
        
       String[] fecha = cot.getFecha().split("/");
       int dia = Integer.parseInt(fecha[0]);
       
       int mes = Integer.parseInt(fecha[1]);
       int ano = Integer.parseInt(fecha[2]);
       
       comboxDia.setSelectedIndex(dia-1); //El Combobox empieza desde 0
       comboxMes.setSelectedIndex(mes-1);
       comboxAno.setSelectedIndex((ano-2000)*-1); //El año 2000 es la posición 0, *-1 porque puede dar negativo
   
        
    }
    
    public void habilitarCamposModfVenta(boolean varControl){
    
       tDescrip.setEnabled(varControl);
       tTel.setEnabled(varControl);
       tCCcliente.setEnabled(varControl);
       tVlr.setEnabled(varControl);
       tNombreCliente.setEnabled(varControl);
       
    
    }
    
    public void habilitarCamposModfCotizacion(boolean varControl){
       tTel.setEnabled(varControl);
       tCCcliente.setEnabled(varControl);
       tVlr.setEnabled(varControl);
       tNombreCliente.setEnabled(varControl);
       tEmail.setEnabled(varControl);

    }
    
     private void agregarVenta(){
        String nombreCliente = tNombreCliente.getText();
        String telefonoCliente = tTel.getText();
        String cedulaCliente = tCCcliente.getText();
        String producto = comboxProduc.getSelectedItem().toString();
        String descripcion = tDescrip.getText();
        float valor;
        if(tVlr.getText().equals("")){
            valor = 0;
        }else{
            valor = Float.valueOf(tVlr.getText());
        }
        
        
        boolean validacion = validarCamposVentas( nombreCliente, cedulaCliente, telefonoCliente, tVlr.getText(), descripcion, producto);
        
        if(validacion){
           /* String respuesta = bD.crearVenta( nombreCliente, cedulaCliente, telefonoCliente, valor, descripcion,fecha, producto);
           if(respuesta.contains("La cedula")) limpiarCamposUsuarios();
            JOptionPane.showMessageDialog(this, respuesta);*/
        }
     } 
    
     private void agregarCotizacion(){
        String nombreCliente = tNombreCliente.getText();
        String telefonoCliente = tTel.getText();
        String producto = comboxProduc.getSelectedItem().toString();
        String email = tEmail.getText();
        float valor;
        if(tVlr.getText().equals("")){
            valor = 0;
        }else{
            valor = Float.valueOf(tVlr.getText());
        }
        
        String dia = comboxDia.getItemAt(comboxDia.getSelectedIndex());
        String mes = comboxMes.getItemAt(comboxMes.getSelectedIndex());
        String ano = comboxAno.getItemAt(comboxAno.getSelectedIndex());
        String fecha = dia+"/"+mes+"/"+ano;
        
        boolean validacion = validarCamposCotizaciones( nombreCliente, telefonoCliente, tVlr.getText(), email, fecha,producto);
        
        if(validacion){
           /* String respuesta = bD.crearCotizacion( nombreCliente, cedulaCliente, telefonoCliente, valor, descripcion,fecha, producto);
           if(respuesta.contains("La cedula")) limpiarCamposUsuarios();
            JOptionPane.showMessageDialog(this, respuesta);*/
        }
     }
     
     private void modificarCotizacion(){
     String mensaje = "";
     
     String email = tEmail.getText();
     String telCliente = tTel.getText();
     String producto = comboxProduc.getSelectedItem().toString();
     
     //mostrar datos guardados previamente
     String id = listaIds[comboxCotizacion.getSelectedIndex()-1];
     Cotizacion cot = bD.leerCotizacionPorId(id);
     
     //comparamos los campos
     if(!email.equals(cot.getEmail())) mensaje = mensaje+"Email\n";
     if(!telCliente.equals(cot.getTelefonoCliente())) mensaje = mensaje+"Telefono Cliente\n";
     if(!producto.equals(cot.getProducto())) mensaje = mensaje+"Producto\n";
     
     if(!mensaje.equals("")){
         mensaje = "Los siguientes campos se van a modificar:\n"+mensaje;
         int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
     
         if(opcion==0){
        //  String respuesta = bD.actualizarCotizacion();
        //    JOptionPane.showConfirmDialog(this, respuesta);
         }
         else{
         mensaje = "cambie un campo para modificar la cotizacion";
         JOptionPane.showMessageDialog(this, mensaje);
         }
     }
 }
    
    private void modificarVenta(){
     String mensaje = "";
     
     //producto (tener en cuenta)
     }
     
     private void consultar(){
         String id = listaIds[comboxCotizacion.getSelectedIndex()-1];
         Cotizacion cot = bD.leerCotizacionPorId(id);
         Venta vent = bD.leerVentaPorId(id);
         
         if(cot != null){
           String mensaje = "Nombre Cliente: "+cot.getNombreCliente()+"\n"+
                            "Telefono Cliente: "+cot.getTelefonoCliente()+"\n"+
                            "Email: "+cot.getEmail()+"\n"+
                            "Valor: "+cot.getValorUnitario()+"\n"+
                            "fecha: "+cot.getFecha()+"\n";
         }
         else{
             String mensaje = "Nombre Cliente: "+vent.getNombreCliente()+"\n"+
                              "Cedula Cliente: "+vent.getCedulaCliente()+"\n"+
                              "Telefono Cliente: "+vent.getTelefonoCliente()+"\n"+
                              "Valor: "+vent.getValorVenta()+"\n"+
                              "Descripción Venta: "+vent.getDescripcionVenta()+"\n";
         }
     }
     
     private void deshabilitar(){
         String id = listaIds[comboxCotizacion.getSelectedIndex()-1];
         Cotizacion cot = bD.leerCotizacionPorId(id);
         Venta vent = bD.leerVentaPorId(id);
         
         if(cot != null){
             String mensaje = "Seguro que desea deshebilitar la cotizacion:\n"+
                              "Nombre Cliente: "+cot.getNombreCliente()+"\n"+
                              "Telefono Cliente: "+cot.getTelefonoCliente()+"\n"+
                              "Valor: "+cot.getValorUnitario()+"\n"+
                              "Email: "+cot.getEmail()+"\n";
             
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
      
         }
         else{
            String mensaje = "Seguro que desea deshabilitar la venta:\n"+"Nombre Cliente:";
         }
         
         
     }
     
     private void limpiarCamposCotizaciones(){
     
     tNombreCliente.setText("");
     tTel.setText("");
     tEmail.setText("");
     tVlr.setText("");
     comboxDia.setSelectedIndex(0);
     comboxMes.setSelectedIndex(0);
     comboxAno.setSelectedIndex(0);
     }
    
     private void limpiarCamposVentas(){
     
     tNombreCliente.setText("");
     tTel.setText("");
     tCCcliente.setText("");
     tVlr.setText("");
     tDescrip.setText("");
     comboxDia.setSelectedIndex(0);
     comboxMes.setSelectedIndex(0);
     comboxAno.setSelectedIndex(0);
     }
    
     public void habilitarCamposCot(boolean varControl){
     
     comboxProduc.setEnabled(false);
     
     }
     
    public static void main(String args[]) {            
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new prinVendedor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bAgregarCot;
    private javax.swing.JButton bAgregarVenta;
    private javax.swing.JButton bAnularCot;
    private javax.swing.JButton bAnularVenta;
    private javax.swing.JButton bConsulVenta;
    private javax.swing.JButton bConsultarCot;
    private javax.swing.JButton bModfCot;
    private javax.swing.JButton bModfVenta;
    private javax.swing.JComboBox<String> comboxAno;
    private javax.swing.JComboBox<String> comboxCotizacion;
    private javax.swing.JComboBox<String> comboxDia;
    private javax.swing.JComboBox<String> comboxMes;
    private javax.swing.JComboBox<String> comboxProduc;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel fechaYhora;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labCCcliente;
    private javax.swing.JLabel labDescrip;
    private javax.swing.JLabel labEmail;
    private javax.swing.JLabel labFecha;
    private javax.swing.JLabel labIDcotizacion;
    private javax.swing.JLabel labNombreCliente;
    private javax.swing.JLabel labProducto;
    private javax.swing.JLabel labTel;
    private javax.swing.JLabel labVlr;
    private javax.swing.JTextField tCCcliente;
    private javax.swing.JTextArea tDescrip;
    private javax.swing.JTextField tEmail;
    private javax.swing.JTextField tNombreCliente;
    private javax.swing.JTextField tTel;
    private javax.swing.JTextField tVlr;
    // End of variables declaration//GEN-END:variables
}
