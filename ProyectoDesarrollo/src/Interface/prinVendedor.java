/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controller.DBConnection;
import Model.Cotizacion;
import Model.OrdenTrabajo;
import Model.Venta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    private final String idVendedor;
    private String[] listaIds;
    ArrayList<String> productos;
    ArrayList<Integer> cantidades;
    private String consulta;
    

    public prinVendedor(DBConnection baseDatos, String idVendedor) {
        //DBConnection baseDatos, String idVendedor
        initComponents();
        bD = baseDatos;
        this.idVendedor = idVendedor;
        
        this.productos = new ArrayList<>();
        this.cantidades = new ArrayList<>();
        
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
    
    private boolean validarCamposVentas(String nombreCliente, String telCliente, String cedCliente, String valor, String descripcion,String[] producto,int[] Cantidad,String idVendedor){
    boolean validacion = true;
    String mensaje ="";
    
    if(nombreCliente.equals("")){mensaje = mensaje+"-Nombre de Cliente\n"; validacion = false;}
    if(cedCliente.equals("")){mensaje = mensaje+"-Cedula de Cliente\n"; validacion = false;}
    if(telCliente.equals("")){mensaje = mensaje+"-Telefono de Cliente\n"; validacion = false;}
    if(valor.equals("")){mensaje = mensaje+"-Valor\n"; validacion = false;}
    if(descripcion.equals("")){mensaje = mensaje+"-Descripción\n"; validacion = false;}
    if(producto.equals("")){mensaje = mensaje+"-Producto\n"; validacion = false;}
    if(tCant.getText().equals("")){mensaje = mensaje+"-Cantidad\n"; validacion = false;}
    
    if(!validacion){
        mensaje = "Los siguientes campos están vacios:\n"+mensaje;
    }
    
    if(!mensaje.equals("")) JOptionPane.showMessageDialog(this, mensaje);
         
    return validacion;
    }
       
    private boolean validarCamposCotizaciones(String nombreCliente,String telCliente, String valor, String email, String fecha, String producto){
     String mensaje ="";
     boolean validacion = true;
        try{
            Integer.valueOf(tVlr.getText());
        }catch(NumberFormatException nan){
            
            mensaje = mensaje + "- cantidad valida para el valor\n";
            return validacion;
        }
        
        if(tTel.getText().replace(" ", "").length()==0){
            mensaje = mensaje + "- numero telefonico\n";
            return validacion;
        }
        
        if(tNombreCliente.getText().replace(" ", "").length()==0){
            mensaje = mensaje + "- Nombre Cliente\n";
            return validacion;
        }
        
        if(tEmail.getText().replace(" ", "").length()==0){
           mensaje = mensaje + "- email\n";

            return validacion;
        }
        
        if(!validacion){
        mensaje = "Los siguientes campos están vacios:\n"+mensaje;
    }
        
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
        labCant = new javax.swing.JLabel();
        tCant = new javax.swing.JTextField();
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
                .addContainerGap(485, Short.MAX_VALUE))
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
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addComponent(bAnularCot, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        labCant.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labCant.setForeground(new java.awt.Color(255, 255, 255));
        labCant.setText("Cantidad:");

        tCant.setToolTipText("");
        tCant.setPreferredSize(new java.awt.Dimension(164, 20));
        tCant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tCantActionPerformed(evt);
            }
        });
        tCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tCantKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labCant)
                        .addGap(115, 115, 115)
                        .addComponent(tCant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
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
                                    .addComponent(comboxProduc, 0, 0, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(tVlr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labVlr)
                    .addComponent(tVlr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCant)
                    .addComponent(tCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labFecha)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labProducto)
                    .addComponent(comboxProduc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labDescrip)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(bAceptar)
                .addContainerGap())
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
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAgregarVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAgregarVentaMouseClicked
        this.productos = new ArrayList<>();
        
        cambiarVisibilidadCampos(false);
        cambiarVisibilidadCamposVentas(true);
        llenarComboBoxProducto();
        botonAceptar = 1;
        bAceptar.setText("Agregar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(true);
    }//GEN-LAST:event_bAgregarVentaMouseClicked

    private void bAgregarCotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAgregarCotMouseClicked
           
        cambiarVisibilidadCampos(false);
        cambiarVisibilidadCamposCot(true);
        
        botonAceptar = 5;
        bAceptar.setText("Agregar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(true);
    }//GEN-LAST:event_bAgregarCotMouseClicked

    private void bModfVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bModfVentaMouseClicked
         this.productos = new ArrayList<>();
         this.cantidades = new ArrayList<>();
        cambiarVisibilidadCampos(false);
        CambiarVisibilidadModCamposVenta(true);
        this.comboxProduc.removeAllItems();
        llenarComboBoxVenta();
        botonAceptar = 2;
        consulta="modificar";
        bAceptar.setText("Modificar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bModfVentaMouseClicked

    private void bModfCotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bModfCotMouseClicked
        
        cambiarVisibilidadCampos(false);
        CambiarVisibilidadModCamposCot(true);
        llenarComboBoxCotizacion();

        botonAceptar = 6;
        consulta  = "modificar";
        bAceptar.setText("Modificar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bModfCotMouseClicked

    private void bConsulVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConsulVentaMouseClicked
        cambiarVisibilidadCampos(false);
        camposVentasConsulta(true);
        llenarComboBoxVenta();
        
        botonAceptar = 3;
        consulta="consultar";
        bAceptar.setText("Consultar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bConsulVentaMouseClicked

    private void bConsultarCotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConsultarCotMouseClicked
        cambiarVisibilidadCampos(false);
        camposCotConsulta(true);
        llenarComboBoxCotizacion();
        
        botonAceptar = 7;
        consulta="consultar";
        bAceptar.setText("Consultar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bConsultarCotMouseClicked

    private void bAnularVentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAnularVentaMouseClicked
        cambiarVisibilidadCampos(false);
        camposVentasConsulta(true);
        llenarComboBoxVenta();
        botonAceptar = 4;
        bAceptar.setText("Anular");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bAnularVentaMouseClicked

    private void bAnularCotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAnularCotMouseClicked
        cambiarVisibilidadCampos(false);
        camposCotConsulta(true);
        llenarComboBoxCotizacion();
        botonAceptar = 8;
        bAceptar.setText("Anular");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);

        comboxCotizacion.setSelectedIndex(0);
    }//GEN-LAST:event_bAnularCotMouseClicked

    private void bModfVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModfVentaActionPerformed

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
        
    }//GEN-LAST:event_bModfCotActionPerformed

    private void bConsultarCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultarCotActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_bConsultarCotActionPerformed

    private void bAnularCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAnularCotActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_bAnularCotActionPerformed

    private void bConsulVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsulVentaActionPerformed
        // TODO add your handling code here:
        this.actualizarComboxVenta();
    }//GEN-LAST:event_bConsulVentaActionPerformed

    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        // TODO add your handling code here:
        
        if(botonAceptar==1){
            System.out.println("prueba de acceso1");
            agregarUnaVenta();
           agregarVenta();
        }
        else if(botonAceptar == 5){
            System.out.println("prueba de acceso5");
           agregarCotizacion();   
        }
        else if(botonAceptar == 2){
                modificarVenta();
        }
        else if(botonAceptar == 6){
            System.out.println("prueba de acceso6");
               modificarCotizacion();
        }
        else if(botonAceptar == 3){
                consultarVenta();
            }
        else if(botonAceptar == 7){
                consultarCotizacion();
            }
        else if(botonAceptar == 4){
                deshabilitarVenta();
            }
        else if(botonAceptar == 8){
                deshabilitarCotizacion();
            }
            
    }//GEN-LAST:event_bAceptarActionPerformed

    private void tCantKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCantKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tCantKeyTyped

    private void tCantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tCantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tCantActionPerformed

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

    private void tVlrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tVlrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tVlrActionPerformed

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

    }//GEN-LAST:event_comboxCotizacionActionPerformed

    private void comboxCotizacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboxCotizacionItemStateChanged

        if(botonAceptar == 6 || botonAceptar == 7 || botonAceptar == 8){

            if(comboxCotizacion.getSelectedIndex() == 0){
                bAceptar.setEnabled(false);
                cambiarVisibilidadCamposVentas(true);

            }else{
                bAceptar.setEnabled(true);}

        }

        if(botonAceptar == 2 || botonAceptar == 3 || botonAceptar == 4){

            if(comboxCotizacion.getSelectedIndex() == 0){
                bAceptar.setEnabled(false);
                CambiarVisibilidadModCamposCot(true);

            }else{
                bAceptar.setEnabled(true);}

        }

        //elementos para ventas y cotizacion

        if(botonAceptar == 6 ){

            tNombreCliente.setVisible(true);
            tNombreCliente.setText("");
            tNombreCliente.setEnabled(true);
            tVlr.setVisible(true);
            tVlr.setText("");
            tVlr.setEnabled(true);
            tTel.setVisible(true);
            tTel.setText("");
            tTel.setEnabled(true);

            //elementos para cotizacion

            labEmail.setVisible(true);
            tEmail.setVisible(true);
            tEmail.setEnabled(true);
            tEmail.setText("");
        }

        if(botonAceptar == 2 ){

            //elementos para ventas y cotizacion

            tNombreCliente.setVisible(true);
            tNombreCliente.setText("");
            tNombreCliente.setEnabled(true);
            tVlr.setVisible(true);
            tVlr.setText("");
            tVlr.setEnabled(true);
            tTel.setVisible(true);
            tTel.setText("");
            tTel.setEnabled(true);

            //elementos para ventas
            tDescrip.setVisible(true);
            tDescrip.setEnabled(true);
            tDescrip.setText("");
            tDescrip.setEnabled(true);
            jScrollPane1.setVisible(true);
            labCCcliente.setVisible(true);
            tCCcliente.setVisible(true);
            tCCcliente.setEnabled(true);
            tCCcliente.setText("");
            tCCcliente.setEnabled(true);
            labCant.setVisible(true);
            tCant.setVisible(true);
            tCant.setEnabled(true);
            tCant.setText("");
        }

    }//GEN-LAST:event_comboxCotizacionItemStateChanged

    
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
      labCant.setVisible(varControl);
      tCant.setVisible(varControl);
      tCant.setText("");
      
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
      labFecha.setVisible(!varControl);
      comboxDia.setVisible(!varControl);
      comboxMes.setVisible(!varControl);
      comboxAno.setVisible(!varControl);
      labProducto.setVisible(varControl);
      comboxProduc.setVisible(varControl);
      
      //elementos ventas
      labDescrip.setVisible(varControl);
      tDescrip.setVisible(varControl);
      tDescrip.setText("");
      jScrollPane1.setVisible(varControl);
      labCCcliente.setVisible(varControl);
      tCCcliente.setVisible(varControl);
      tCCcliente.setEnabled(varControl);
      tCCcliente.setText("");
      labCant.setVisible(varControl);
      tCant.setVisible(varControl);
      tCant.setText("");
      
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
      labCant.setVisible(!varControl);
      tCant.setVisible(!varControl);
      tCant.setText("");
      
      //elementos para cotizacion
      labEmail.setVisible(varControl);
      tEmail.setVisible(varControl);
      tEmail.setEnabled(varControl);
      tEmail.setText("");
      labFecha.setVisible(varControl);
      comboxDia.setVisible(varControl);
      comboxMes.setVisible(varControl);
      comboxAno.setVisible(varControl);
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
      labCant.setVisible(varControl);
      tCant.setVisible(varControl);
      tCant.setEnabled(!varControl);
      tCant.setText("");
      
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
      labCant.setVisible(!varControl);
      tCant.setVisible(!varControl);
      tCant.setText("");
      
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
      labCant.setVisible(!varControl);
      tCant.setVisible(!varControl);
      tCant.setText("");
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
      labCant.setVisible(!varControl);
      tCant.setVisible(!varControl);
      tCant.setText("");
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

    
    public void habilitarCamposModfVenta(boolean varControl){
    
       tDescrip.setEnabled(varControl);
       tTel.setEnabled(varControl);
       tCCcliente.setEnabled(varControl);
       tVlr.setEnabled(varControl);
       tNombreCliente.setEnabled(varControl);
       tCant.setEnabled(varControl);
    
    }
    
    private boolean validarCamposAgregarVenta(){
        if(comboxProduc.getSelectedItem().equals("Seleccione un producto")){
            JOptionPane.showMessageDialog(this, 
                    "Por favor seleccione una referencia");
            return false;
        }
        
        if(String.valueOf(tDescrip.getText()).replace(" ", "").length()==0){
            JOptionPane.showMessageDialog(this, 
                    "Por favor ingrese la descripcion");
            return false;
        }
        return true;
    }
    
    
    public void habilitarCamposModfCotizacion(boolean varControl){
       tTel.setEnabled(varControl);
       tCCcliente.setEnabled(varControl);
       tVlr.setEnabled(varControl);
       tNombreCliente.setEnabled(varControl);
       tEmail.setEnabled(varControl);

    }
    
         private boolean agregarUnaVenta(){
        if(!validarCamposAgregarVenta()){
            return false;
        }
        if(productos.contains(comboxProduc.getSelectedItem().toString())){
            productos.set(this.comboxProduc.getSelectedIndex(), 
                    String.valueOf(this.comboxProduc.getSelectedItem()));
            cantidades.set(this.comboxProduc.getSelectedIndex(), 
                    Integer.valueOf(tCant.getText()));
        }else{
            productos.add(comboxProduc.getSelectedItem().toString());
            cantidades.add(Integer.valueOf(tCant.getText()));
        }
        JOptionPane.showMessageDialog(this, "Producto "+comboxProduc.getSelectedItem().toString()+" Agregado");
        return true;
    }
    
    
     private boolean agregarVenta(){
         comboxProduc.removeAllItems();
         llenarComboBoxProducto();
         
         if(!validarCamposAgregarVenta()){
            return false;
        }
        int[] cant = new int[cantidades.size()]; 
        String[] products = new String[productos.size()]; 
         
        for (int i=0; i<cant.length;i++){
            cant[i]=cantidades.get(i);
            products[i]=productos.get(i).split(",")[0];
        }
        
        String nombreCliente = tNombreCliente.getText();
        String telefonoCliente = tTel.getText();
        String cedulaCliente = tCCcliente.getText();
        String descripcion = tDescrip.getText();
        String IDvendedor = "";
        float valor;
        if(tVlr.getText().equals("")){
            valor = 0;
        }else{
            valor = Float.valueOf(tVlr.getText());
        }
        boolean validacion = validarCamposVentas( nombreCliente, cedulaCliente, telefonoCliente, tVlr.getText(), descripcion, products, cant,IDvendedor);
        
        if(validacion){
            String respuesta = bD.crearVenta( nombreCliente, telefonoCliente, cedulaCliente, valor, descripcion, products, cant,idVendedor);
            this.productos = new ArrayList<>();
            this.cantidades = new ArrayList<>();
            JOptionPane.showMessageDialog(this, respuesta);
            llenarComboBoxProducto();

        }
        
        return true;
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
           String respuesta = bD.crearCotizacion( nombreCliente,  telefonoCliente, email, valor,fecha, idVendedor);
           if(respuesta.contains("La cedula")) limpiarCamposCotizaciones();
            JOptionPane.showMessageDialog(this, respuesta);
        }
     }
     
     private boolean validarCamposModfCotizacion(){
     
         try{
            Integer.valueOf(tVlr.getText());
        }catch(NumberFormatException nan){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese una cantidad valida");
            return false;
        }


         if(tTel.getText().replace(" ", "").length()==0){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un numero telefonico");
            return false;
        }
         
        if(tEmail.getText().replace(" ", "").length()==0){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un email");
            return false;
        }
        
        return true;
         
     }
     
     public void llenarComboBoxProducto(){
     
         String [] productos = bD.listarProducto(this.idVendedor, consulta).split("\\$");
        for(int i=0; i<productos.length; i++){
            comboxProduc.addItem(productos[i]);
        }

     }
     
     
     public void llenarComboBoxVenta(){
     
         String [] ventas = bD.listarVenta(this.idVendedor, consulta).split("\\$");
        for(int i=0; i<ventas.length; i++){
            comboxCotizacion.addItem(ventas[i]);
        }

     }
     
     public void llenarComboBoxCotizacion(){
     
         String [] cotizaciones = bD.listarCotizacion(this.idVendedor, consulta).split("\\$");
        for(int i=0; i<cotizaciones.length; i++){
            comboxCotizacion.addItem(cotizaciones[i]);
        }
            System.out.println("4");
     }
     
     private boolean modificarCotizacion(){
     if(!validarCamposModfCotizacion()){
         System.out.println("1");   
         return false;
            
        }
        System.out.println("2");
        String idCotizacion = String.valueOf(comboxCotizacion.getSelectedItem());
        String nombreCliente = tNombreCliente.getText();
        float valor = Float.parseFloat(tVlr.getText());
        String telefono = tTel.getText();
        String email = tEmail.getText();
        
        String respuesta = bD.actualizarCotizacion(idCotizacion, nombreCliente, telefono,email, valor);
        JOptionPane.showMessageDialog(this, respuesta);
        limpiarCampos();
        CambiarVisibilidadModCamposCot(true);
        llenarComboBoxCotizacion();
        System.out.println("3");
        return true;
        
 }
    
     private boolean validarCamposModificarVenta(){
        try{
            Integer.valueOf(tCant.getText());
        }catch(NumberFormatException nan){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese una cantidad valida");
            return false;
        }
        if(comboxCotizacion.getSelectedItem().equals("Seleccione una venta")){
            JOptionPane.showMessageDialog(this,
                    "Por favor selecciona una venta");
            return false;
        }
        
        if(tDescrip.getText().replace(" ", "").length()==0){
            JOptionPane.showMessageDialog(this,
                    "Por favor ingrese la descripcion");
            return false;
        }

        return true;
    }
     
    private boolean modificarVenta(){
    if(!validarCamposModificarVenta()){
            return false;
        }
    
    int[] cant = new int[cantidades.size()];
    String[] product = new String[productos.size()];
        for (int i=0; i<cant.length;i++){
            cant[i]=cantidades.get(i);
            product[i]=productos.get(i).split(",")[0];
        }
    
        String idVenta = String.valueOf(comboxCotizacion.getSelectedItem());
        String telefono = tTel.getText();
        String descripcion = tDescrip.getText();
        String nombreCliente = tNombreCliente.getText();
        String cedCliente = tCCcliente.getText();
        float valor = Float.parseFloat(tVlr.getText());
        
        String respuesta = bD.actualizarVenta(idVenta, nombreCliente, cedCliente,descripcion,telefono ,cant,product, valor,idVendedor);
        JOptionPane.showMessageDialog(this, respuesta);
        limpiarCampos();
        llenarComboBoxVenta();
        this.comboxProduc.removeAllItems();
        llenarComboBoxProducto();
        return true;
     }
     
     private boolean consultarVenta(){
               
          if(!validarCamposConsultarVenta()){
            return false;            
        }
         
        String id = String.valueOf(comboxCotizacion.getSelectedItem());
        Venta vent = bD.leerVentaPorId(id);
         
  
            String mensaje = "Nombre Cliente: "+vent.getNombreCliente()+"\n"+
                              "Cedula Cliente: "+vent.getCedulaCliente()+"\n"+
                              "Telefono Cliente: "+vent.getTelefonoCliente()+"\n"+
                              "Valor: "+vent.getValorVenta()+"\n"+
                              "Descripción Venta: "+vent.getDescripcionVenta()+"\n";
         
           JOptionPane.showMessageDialog(this, mensaje);
           limpiarCampos();
           llenarComboBoxVenta();
           return true;
     }
    
     private boolean validarCamposConsultarCotizacion(){
        if(comboxCotizacion.getSelectedItem().equals("Seleccione una cotizacion")){
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione una cotizacion");
            return false;
        }
        return true;
    }
     
    private boolean validarCamposConsultarVenta(){
        if(comboxCotizacion.getSelectedItem().equals("Seleccione una venta")){
            JOptionPane.showMessageDialog(this,
                    "Por favor seleccione una venta");
            return false;
        }
        return true;
    } 
     
    
     private boolean consultarCotizacion(){
         
           if(!validarCamposConsultarCotizacion()){
            return false;            
        }
         
        String id = String.valueOf(comboxCotizacion.getSelectedItem());
         Cotizacion cot = bD.leerCotizacionPorId(id);
         
           String mensaje = "Nombre Cliente: "+cot.getNombreCliente()+"\n"+
                            "Telefono Cliente: "+cot.getTelefonoCliente()+"\n"+
                            "Email: "+cot.getEmail()+"\n"+
                            "Valor: "+cot.getValorUnitario()+"\n"+
                            "fecha: "+cot.getFecha()+"\n";
           
           JOptionPane.showMessageDialog(this, mensaje);
           limpiarCampos();
           llenarComboBoxCotizacion();
           return true;
     }
     
     private void deshabilitarVenta(){
         String id = String.valueOf(comboxCotizacion.getSelectedItem());
         Venta vent = bD.leerVentaPorId(id);
         
             String mensaje = "Seguro que desea deshebilitar la factura:\n"+
                              "Nombre Cliente: "+vent.getNombreCliente()+"\n"+
                              "Telefono Cliente: "+vent.getTelefonoCliente()+"\n"+
                              "Cedula Cliente: "+vent.getCedulaCliente()+"\n"+
                              "Valor: "+vent.getValorVenta()+"\n"+
                              "Descripcion: "+vent.getDescripcionVenta()+"\n";
             
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);

         if(opcion == 0){
                String respuesta = bD.eliminarVenta(id);
                JOptionPane.showMessageDialog(this, respuesta);
                limpiarCampos();
                llenarComboBoxVenta();
            }
         
     }
     
     private void deshabilitarCotizacion(){
         String id = String.valueOf(comboxCotizacion.getSelectedItem());
         Cotizacion cot = bD.leerCotizacionPorId(id);
         
             String mensaje = "Seguro que desea deshebilitar la cotizacion:\n"+
                              "Nombre Cliente: "+cot.getNombreCliente()+"\n"+
                              "Telefono Cliente: "+cot.getTelefonoCliente()+"\n"+
                              "Valor: "+cot.getValorUnitario()+"\n"+
                              "Email: "+cot.getEmail()+"\n";
             
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
      
         if(opcion == 0){
                String respuesta = bD.eliminarCotizacion(id);
                JOptionPane.showMessageDialog(this, respuesta);
                limpiarCamposCotizaciones();
                llenarComboBoxCotizacion();
            }
     }
     
     private void limpiarCamposCotizaciones(){
     
     tNombreCliente.setText("");
     tTel.setText("");
     tEmail.setText("");
     tVlr.setText("");
     comboxCotizacion.removeAllItems();
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
    private javax.swing.JLabel labCant;
    private javax.swing.JLabel labDescrip;
    private javax.swing.JLabel labEmail;
    private javax.swing.JLabel labFecha;
    private javax.swing.JLabel labIDcotizacion;
    private javax.swing.JLabel labNombreCliente;
    private javax.swing.JLabel labProducto;
    private javax.swing.JLabel labTel;
    private javax.swing.JLabel labVlr;
    private javax.swing.JTextField tCCcliente;
    private javax.swing.JTextField tCant;
    private javax.swing.JTextArea tDescrip;
    private javax.swing.JTextField tEmail;
    private javax.swing.JTextField tNombreCliente;
    private javax.swing.JTextField tTel;
    private javax.swing.JTextField tVlr;
    // End of variables declaration//GEN-END:variables
}
