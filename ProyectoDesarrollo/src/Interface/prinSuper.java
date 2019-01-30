/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Controller.DBConnection;
import Model.Gerente;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author melissa
 */
public class prinSuper extends javax.swing.JFrame {
    
    //la variable botonAceptar indica el uso que se le da al botón de la izquierda para cada boton principal
    //1:Agregar, 2:Modificar, 3:Consultar, 4:Despedir, 0:nada
    private int botonAceptar = 0;     
    private DBConnection bD;
    private String[] listaIds;
    
    public prinSuper(DBConnection baseDatos) {
        initComponents();
        
        bD = baseDatos;
        
        //Fecha
        Date fechaSist = new Date(); 
        SimpleDateFormat formato = new SimpleDateFormat("dd-MMM-yyyy");
        fecha.setText(formato.format(fechaSist));
        
        //Hora
        Timer tiempo = new Timer(100, new prinSuper.hora());
        tiempo.start();
        
        cambiarVisibilidadCampos(false);
        labContra.setVisible(false);
        tContra.setVisible(false);
        bAceptar.setVisible(false);
        labEmple.setVisible(false);
        comboxEmple.setVisible(false);
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
    
    private boolean validarCampos(String nombreUsu, String nombre, String cedula, String correo, String cuenta, String direccion, String telefono, String salario, String fechaNac) {
        boolean validacion = true, fechaValida; // validacion, en un principio, es solo para los campos vacios
        String mensaje = ""; //En caso de hayan campos invalidos
        
        if(nombreUsu.equals("")){ mensaje = mensaje+"- Nombre de Usuario\n"; validacion = false; }       
        if(nombre.equals("")){ mensaje = mensaje+"- Nombre\n"; validacion = false; }
        if(cedula.equals("")){ mensaje = mensaje+"- Cedula\n"; validacion = false; }
        if(correo.equals("")){ mensaje = mensaje+"- Correo\n"; validacion = false; }
        if(cuenta.equals("")){ mensaje = mensaje+"- Cuenta\n"; validacion = false; }
        if(direccion.equals("")){ mensaje = mensaje+"- Direccion\n"; validacion = false; }
        if(telefono.equals("")){ mensaje = mensaje+"- Telefono\n"; validacion = false; }
        if(salario.equals("")){ mensaje = mensaje+"- Salario\n"; validacion = false; }
        fechaValida = validarFecha(fechaNac);
        
        System.out.println(mensaje);
        
        if(!validacion){ //Hay campos vacios
            mensaje = "Los siguientes campos están vacios:\n"+mensaje;
            if(fechaValida) mensaje = "La fecha de nacimiento es invalida\n\n"+mensaje;
        }else{
            if(fechaValida){ //No hay campos vacios, pero la fecha es invalida
                mensaje = "La fecha de nacimiento es invalida";
                validacion = false; //Se cambia ya que la fecha no es valida
            }
        }
        
        if(!mensaje.equals("")) JOptionPane.showMessageDialog(this, mensaje);
        
        return validacion;
    }

    //Retorna una lista con los Ids de los empleados en listaEmpleados el cual es una lista de strings del
    //tipo {"id1,nombre1,cedula1",..."idN,nombreN,cedulaN"} donde N es la cantiadad de empleados
    private String[] obtenerListaIds(String[] listaEmpleados){
        String[] listaDeIds = new String[listaEmpleados.length];
        String[] empleado;
        
        for(int i=0; i<(listaDeIds.length); i++){
            empleado = listaEmpleados[i].split(",");
            listaDeIds[i] = empleado[0];
        }
        
        return listaDeIds;
    }
    
    //Retorna una lista con las opciones para combobox comboxEmple con el formato
    //{"nombre1 cedula1",..."nombreN cedulaN"} obtenidas de listaEmpleado el cual es una lista de strings del
    //tipo {"id1,nombre1,cedula1",..."idN,nombreN,cedulaN"} donde N es la cantiadad de empleados
    private String[] obtenerOpciones(String[] listaEmpleados){
        String[] opciones = new String[listaEmpleados.length+1];
        String[] empleado;
        opciones[0] = "No seleccionado";
        
        for(int i=0,j=1; i<(listaEmpleados.length); i++,j++){
            empleado = listaEmpleados[i].split(",");
            opciones[j] = empleado[1]+" "+empleado[2].replace("$","");
        }
        
        return opciones;
    }
    
    private void actualizarComboxEmple(){
        String empleados = bD.listarGerentes();        
        
        if(empleados.equals("")){ //No Hay empleados
           String[] opciones = { "No seleccionado" };
           comboxEmple.setModel(new DefaultComboBoxModel(opciones));
        }else{ //Hay empleados
            String[] listaEmpleados = empleados.split("$");
            listaIds = obtenerListaIds(listaEmpleados);
            String[] opciones = obtenerOpciones(listaEmpleados);
            comboxEmple.setModel(new DefaultComboBoxModel(opciones));
            
        }
    }
    
    //Limpia los campos(jTextfields) de la interfaz
    private void limpiarCampos(){
        tNombreUsu.setText("");
        tContra.setText("");
        tNombre.setText("");
        tCedula.setText("");
        tCorreo.setText("");
        tCuentaBan.setText("");
        tDir.setText("");
        tTel.setText("");
        tSal.setText("");
        comboxDia.setSelectedIndex(0);
        comboxMes.setSelectedIndex(0);
        comboxAno.setSelectedIndex(0);
    }
    
    private void llenarCamposModf(){
        String id = listaIds[comboxEmple.getSelectedIndex()-1];
        Gerente ger = bD.leerGerentePorId(id);
        
        tNombreUsu.setText(ger.getNombreUsuario());
        tContra.setText(ger.getContrasena());
        tNombre.setText(ger.getNombre());
        tCedula.setText(ger.getCedula());
        tCorreo.setText(ger.getCorreo());
        tCuentaBan.setText(ger.getCuentaBancaria());
        comboxGenero.setSelectedIndex(ger.getGenero());
        tDir.setText(ger.getDireccion());
        tTel.setText(ger.getTelefono());
        tSal.setText(Float.toString(ger.getSalario()));
        
        String[] fechaNac = ger.getFechaNacimiento().split("/");
        int diaNac = Integer.parseInt(fechaNac[0]);
        int mesNac = obtenerMesNum(fechaNac[1]);
        int anoNac = Integer.parseInt(fechaNac[2]);
        
        comboxDia.setSelectedIndex(diaNac-1); //El Combobox empieza desde 0
        comboxMes.setSelectedIndex(mesNac-1);
        comboxAno.setSelectedIndex((anoNac-2000)*-1); //El año 2000 es la posición 0, *-1 porque puede dar negativo
    }
    
    //Retorna el valor numerico del mes, la variable mes tiene el formato puesto en el comboxMes
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
    
    // Calcula la edad de nacimiento apartir de un string de fecha, ejemplo del string "01/jul/1985"
    private int calcularEdad(String nacimiento){
        int edad,anoN,anoHoy,mesN,mesHoy,diaN,diaHoy;
        Date fechaSist = new Date(); 
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String fechaHoy = formato.format(fechaSist);
        
        String[] fechaAct = fechaHoy.split("/");        
        String[] fechaNac = nacimiento.split("/");
        
        anoN = Integer.parseInt(fechaNac[2]);
        anoHoy = Integer.parseInt(fechaAct[2]);
        mesN = obtenerMesNum(fechaNac[1]);
        mesHoy = Integer.parseInt(fechaAct[1]);
        diaN = Integer.parseInt(fechaNac[0]);
        diaHoy = Integer.parseInt(fechaAct[0]);
        
        edad = anoHoy-anoN;
        
        if(mesHoy<mesN) edad--;
        if(mesHoy==mesN){
            if(diaHoy<diaN){
                edad--;
            }            
        }
        
        return edad;
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        iconUsu = new javax.swing.JLabel();
        labLogo = new javax.swing.JLabel();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        bAgregar = new javax.swing.JButton();
        bModf = new javax.swing.JButton();
        bConsul = new javax.swing.JButton();
        bDespedir = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        labNombreUsu = new javax.swing.JLabel();
        labCedula = new javax.swing.JLabel();
        labCargo = new javax.swing.JLabel();
        labCorreo = new javax.swing.JLabel();
        labCuentaBan = new javax.swing.JLabel();
        labGenero = new javax.swing.JLabel();
        labDir = new javax.swing.JLabel();
        labTel = new javax.swing.JLabel();
        labSal = new javax.swing.JLabel();
        labSede = new javax.swing.JLabel();
        labFechaNac = new javax.swing.JLabel();
        tNombreUsu = new javax.swing.JTextField();
        tCedula = new javax.swing.JTextField();
        tCorreo = new javax.swing.JTextField();
        tCuentaBan = new javax.swing.JTextField();
        tDir = new javax.swing.JTextField();
        labNombre = new javax.swing.JLabel();
        tNombre = new javax.swing.JTextField();
        comboxGenero = new javax.swing.JComboBox<>();
        tTel = new javax.swing.JTextField();
        tSal = new javax.swing.JTextField();
        comboxSedes = new javax.swing.JComboBox<>();
        comboxDia = new javax.swing.JComboBox<>();
        comboxMes = new javax.swing.JComboBox<>();
        comboxAno = new javax.swing.JComboBox<>();
        comboxCargo = new javax.swing.JComboBox<>();
        bAceptar = new javax.swing.JButton();
        labEmple = new javax.swing.JLabel();
        comboxEmple = new javax.swing.JComboBox<>();
        labContra = new javax.swing.JLabel();
        tContra = new javax.swing.JTextField();
        labGenero1 = new javax.swing.JLabel();
        comboxEstado = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        fechaYhora = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new java.awt.BorderLayout());

        iconUsu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO menuSuper.png"))); // NOI18N

        labLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/logo.png"))); // NOI18N
        labLogo.setName(""); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labLogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                .addComponent(iconUsu)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconUsu)
                .addGap(15, 15, 15))
            .addComponent(labLogo, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jSplitPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSplitPane2.setEnabled(false);

        bAgregar.setForeground(new java.awt.Color(51, 51, 51));
        bAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO addUser.png"))); // NOI18N
        bAgregar.setText("Agregar");
        bAgregar.setBorderPainted(false);
        bAgregar.setContentAreaFilled(false);
        bAgregar.setPreferredSize(new java.awt.Dimension(125, 57));
        bAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bAgregarMouseClicked(evt);
            }
        });

        bModf.setForeground(new java.awt.Color(51, 51, 51));
        bModf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO modifyUser.png"))); // NOI18N
        bModf.setText("Modificar");
        bModf.setBorderPainted(false);
        bModf.setContentAreaFilled(false);
        bModf.setPreferredSize(new java.awt.Dimension(125, 57));
        bModf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bModfMouseClicked(evt);
            }
        });
        bModf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModfActionPerformed(evt);
            }
        });

        bConsul.setForeground(new java.awt.Color(51, 51, 51));
        bConsul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO searchUser.png"))); // NOI18N
        bConsul.setText("Consultar");
        bConsul.setBorderPainted(false);
        bConsul.setContentAreaFilled(false);
        bConsul.setMaximumSize(new java.awt.Dimension(119, 57));
        bConsul.setMinimumSize(new java.awt.Dimension(119, 57));
        bConsul.setName(""); // NOI18N
        bConsul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bConsulMouseClicked(evt);
            }
        });
        bConsul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsulActionPerformed(evt);
            }
        });

        bDespedir.setForeground(new java.awt.Color(51, 51, 51));
        bDespedir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO searchUser.png"))); // NOI18N
        bDespedir.setText("Despedir");
        bDespedir.setBorderPainted(false);
        bDespedir.setContentAreaFilled(false);
        bDespedir.setPreferredSize(new java.awt.Dimension(125, 57));
        bDespedir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDespedirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bModf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bDespedir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bConsul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(bAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bModf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bConsul, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bDespedir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );

        jSplitPane2.setLeftComponent(jPanel3);

        jPanel4.setEnabled(false);

        labNombreUsu.setText("Nombre de usuario:");

        labCedula.setText("Cedula:");

        labCargo.setText("Cargo:");

        labCorreo.setText("Correo:");

        labCuentaBan.setText("Cuenta Bancaria:");

        labGenero.setText("Genero:");

        labDir.setText("Dirección:");

        labTel.setText("Telefono:");

        labSal.setText("Salario:");

        labSede.setText("Sede:");

        labFechaNac.setText("Fecha de nacimiento:");

        tNombreUsu.setToolTipText("");
        tNombreUsu.setPreferredSize(new java.awt.Dimension(190, 22));
        tNombreUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNombreUsuActionPerformed(evt);
            }
        });

        tCedula.setToolTipText("");
        tCedula.setPreferredSize(new java.awt.Dimension(190, 22));
        tCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tCedulaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tCedulaKeyTyped(evt);
            }
        });

        tCorreo.setToolTipText("");
        tCorreo.setPreferredSize(new java.awt.Dimension(190, 22));

        tCuentaBan.setToolTipText("");
        tCuentaBan.setPreferredSize(new java.awt.Dimension(190, 22));
        tCuentaBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tCuentaBanKeyTyped(evt);
            }
        });

        tDir.setToolTipText("");
        tDir.setPreferredSize(new java.awt.Dimension(190, 22));

        labNombre.setText("Nombre:");
        labNombre.setToolTipText("");

        tNombre.setToolTipText("");
        tNombre.setName(""); // NOI18N
        tNombre.setPreferredSize(new java.awt.Dimension(190, 22));
        tNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNombreKeyTyped(evt);
            }
        });

        comboxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));
        comboxGenero.setPreferredSize(new java.awt.Dimension(190, 22));

        tTel.setToolTipText("");
        tTel.setPreferredSize(new java.awt.Dimension(190, 22));
        tTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tTelKeyTyped(evt);
            }
        });

        tSal.setToolTipText("");
        tSal.setPreferredSize(new java.awt.Dimension(190, 22));
        tSal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tSalKeyTyped(evt);
            }
        });

        comboxSedes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No disponible" }));
        comboxSedes.setToolTipText("El gerente que crea la sede es el encargado de asignarla a un gerente");
        comboxSedes.setEnabled(false);
        comboxSedes.setPreferredSize(new java.awt.Dimension(190, 22));
        comboxSedes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboxSedesItemStateChanged(evt);
            }
        });

        comboxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        comboxDia.setPreferredSize(new java.awt.Dimension(37, 22));

        comboxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic" }));
        comboxMes.setPreferredSize(new java.awt.Dimension(45, 22));

        comboxAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944" }));
        comboxAno.setPreferredSize(new java.awt.Dimension(49, 22));

        comboxCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente" }));
        comboxCargo.setEnabled(false);
        comboxCargo.setPreferredSize(new java.awt.Dimension(190, 22));

        bAceptar.setText("Agregar");
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });

        labEmple.setText("Empleado:");
        labEmple.setPreferredSize(new java.awt.Dimension(94, 14));

        comboxEmple.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No seleccionado" }));
        comboxEmple.setMinimumSize(new java.awt.Dimension(152, 20));
        comboxEmple.setName(""); // NOI18N
        comboxEmple.setPreferredSize(new java.awt.Dimension(190, 22));
        comboxEmple.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboxEmpleItemStateChanged(evt);
            }
        });
        comboxEmple.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxEmpleActionPerformed(evt);
            }
        });

        labContra.setText("Contraseña:");

        tContra.setToolTipText("");
        tContra.setPreferredSize(new java.awt.Dimension(190, 22));

        labGenero1.setText("Estado:");

        comboxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        comboxEstado.setPreferredSize(new java.awt.Dimension(190, 22));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(labFechaNac)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(comboxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboxAno, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(labEmple, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labNombreUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labContra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(labCuentaBan)
                                            .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(labGenero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labTel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labSal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labSede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(labGenero1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboxSedes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tContra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tNombreUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboxCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tCedula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tCuentaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tTel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tSal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboxEmple, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboxEstado, 0, 1, Short.MAX_VALUE)))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(bAceptar)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboxEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNombreUsu)
                    .addComponent(tNombreUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labContra)
                    .addComponent(tContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labNombre)
                    .addComponent(tNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCedula)
                    .addComponent(tCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(labCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCorreo)
                    .addComponent(tCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tCuentaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labCuentaBan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(labGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labDir)
                    .addComponent(tDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTel)
                    .addComponent(tTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labSal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tSal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(comboxSedes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(labSede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(11, 11, 11)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labGenero1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labFechaNac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bAceptar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane2.setRightComponent(jPanel4);

        jPanel1.add(jSplitPane2, java.awt.BorderLayout.CENTER);

        fechaYhora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        fechaYhora.setText("Fecha  y hora:");

        fecha.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        fecha.setText("dd-mm-yyyy");

        hora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        hora.setText("hh:mm:ss");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(fechaYhora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(hora)
                .addGap(0, 375, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fecha)
                    .addComponent(fechaYhora)
                    .addComponent(hora))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 555, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAgregarMouseClicked
        habilitarCamposmodf(true);
        cambiarVisibilidadCampos(true);
        limpiarCampos();
        
        botonAceptar = 1;
        bAceptar.setText("Agregar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(true);
        bAceptar.setForeground(Color.BLACK);
    }//GEN-LAST:event_bAgregarMouseClicked

    private void bModfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bModfMouseClicked
        cambiarVisibilidadCampos(true);
        habilitarCamposmodf(false);
        labContra.setVisible(true);
        tContra.setVisible(true);
        labEmple.setVisible(true);
        comboxEmple.setVisible(true);
        
        //cambiarVisibilidadCamposmodf(true);
        limpiarCampos();
        
        botonAceptar = 2;
        bAceptar.setText("Modificar");
        bAceptar.setVisible(true);              
        bAceptar.setEnabled(false);
        bAceptar.setForeground(Color.BLACK);
        
        comboxEmple.setSelectedIndex(0);
        
        actualizarComboxEmple();
    }//GEN-LAST:event_bModfMouseClicked

    private void bConsulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConsulMouseClicked
        habilitarCamposmodf(true);
        cambiarVisibilidadCampos(false);
        
        botonAceptar = 3;
        bAceptar.setText("Consultar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);
        bAceptar.setForeground(Color.BLACK);
        
        actualizarComboxEmple();
        comboxEmple.setSelectedIndex(0);
    }//GEN-LAST:event_bConsulMouseClicked

    private void bDespedirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDespedirMouseClicked
        habilitarCamposmodf(true);
        cambiarVisibilidadCampos(false);
        
        botonAceptar = 4;
        bAceptar.setText("Despedir");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);
        bAceptar.setForeground(Color.RED);
        
        actualizarComboxEmple();
        comboxEmple.setSelectedIndex(0);
    }//GEN-LAST:event_bDespedirMouseClicked

    private void tCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCedulaKeyPressed
    }//GEN-LAST:event_tCedulaKeyPressed

    private void tCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCedulaKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){            
            evt.consume();
        }
    }//GEN-LAST:event_tCedulaKeyTyped

    private void tTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTelKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){            
            evt.consume();
        }
    }//GEN-LAST:event_tTelKeyTyped

    private void tCuentaBanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCuentaBanKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){            
            evt.consume();
        }
    }//GEN-LAST:event_tCuentaBanKeyTyped

    private void tSalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tSalKeyTyped
        /*int largoTexto = tSal.getText().length();
        if(largoTexto>=8){
            evt.consume();
        }*/
        
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){            
            evt.consume();
        }
    }//GEN-LAST:event_tSalKeyTyped

    private void tNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNombreKeyTyped
        char c = evt.getKeyChar();
        
        if(c != ' '){
            if(c<'A' || c>'Z'){
                if(c<'a' || c>'z'){
                    evt.consume();
                }
            }
        }
            
    }//GEN-LAST:event_tNombreKeyTyped

    private void agregar(){
        String nombreUsu = tNombreUsu.getText();
        String nombre = tNombre.getText();
        String cedula = tCedula.getText();
        String cargo = "Gerente";
        String correo = tCorreo.getText();
        String cuenta = tCuentaBan.getText();
        int genero = comboxGenero.getSelectedIndex();
        String direccion = tDir.getText();
        String telefono = tTel.getText();
        float salario;
        if(tSal.getText().equals("")){
            salario = 0;
        }else{
            salario = Float.valueOf(tSal.getText());
        }
        String diaCumple = comboxDia.getItemAt(comboxDia.getSelectedIndex());
        String mesCumple = comboxMes.getItemAt(comboxMes.getSelectedIndex());
        String anoCumple = comboxAno.getItemAt(comboxAno.getSelectedIndex());
        String fechaNac = diaCumple+"/"+mesCumple+"/"+anoCumple;
                       
        boolean validacion = validarCampos(nombreUsu,nombre,cedula,correo,cuenta,direccion,telefono,tSal.getText(),fechaNac);
        if(validacion){
            //Fecha de reg
            Date fechaSist = new Date(); 
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaReg = formato.format(fechaSist);           
            
            String respuesta = bD.crearGerente(nombre, cedula, cargo, correo, genero, direccion, telefono, salario, fechaNac, cuenta, fechaReg, nombreUsu);
            if(respuesta.contains("La cedula")) limpiarCampos();
            JOptionPane.showMessageDialog(this, respuesta);
        }        
    }
    
    private void modificar(){        
        String mensaje = "";
        
        //Datos Modf
        String nombreUsu = tNombreUsu.getText();
        String contrasena = tContra.getText();
        String nombre = tNombre.getText();
        String cedula = tCedula.getText();
        String correo = tCorreo.getText();
        String cuenta = tCuentaBan.getText();
        int genero = comboxGenero.getSelectedIndex();
        String direccion = tDir.getText();
        String telefono = tTel.getText();
        float salario;
        if(tSal.getText().equals("")){
            salario = 0;
        }else{
            salario = Float.valueOf(tSal.getText());
        }        
        
        boolean estado;
        if(comboxEstado.getSelectedItem().toString().equals("Activo")){
            estado=true;
        }else{
            estado=false;
        }
        
        String diaCumple = comboxDia.getItemAt(comboxDia.getSelectedIndex());
        String mesCumple = comboxMes.getItemAt(comboxMes.getSelectedIndex());
        String anoCumple = comboxAno.getItemAt(comboxAno.getSelectedIndex());
        String fechaNac = diaCumple+"/"+mesCumple+"/"+anoCumple;
        
        //Datos Anteriores
        String id = listaIds[comboxEmple.getSelectedIndex()-1];
        Gerente ger = bD.leerGerentePorId(id);
        
        //Comparación
        if(!nombreUsu.equals(ger.getNombreUsuario())) mensaje = mensaje+"Nombre Usuario\n";
        if(!contrasena.equals(ger.getContrasena())) mensaje = mensaje+"Contraseña\n";
        if(!nombre.equals(ger.getNombre())) mensaje = mensaje+"Nombre\n";
        if(!cedula.equals(ger.getCedula())) mensaje = mensaje+"Cedula\n";
        if(!correo.equals(ger.getCorreo())) mensaje = mensaje+"Correo\n";
        if(!cuenta.equals(ger.getCuentaBancaria())) mensaje = mensaje+"Cuenta Bancaria\n";
        if(genero != ger.getGenero()) mensaje = mensaje+"Genero\n";
        if(!direccion.equals(ger.getDireccion())) mensaje = mensaje+"Direccion\n";
        if(!telefono.equals(ger.getTelefono())) mensaje = mensaje+"Telefono\n";
        if(salario != ger.getSalario()) mensaje = mensaje+"Salario\n";
        if(!fechaNac.equals(ger.getFechaNacimiento())) mensaje = mensaje+"Fecha de nacimiento\n";
        if(estado != ger.isHabilitado()) mensaje = mensaje+"Estado\n";
        
        
        if(!mensaje.equals("")){
            mensaje = "Los siguientes campos se van a modificar:\n"+mensaje;
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
            
            if(opcion==0){ //Modificar
                String respuesta = bD.actualizarGerente(id, nombre, cedula, "Gerente", correo, genero, direccion, telefono, salario, fechaNac, cuenta, nombreUsu, contrasena, ger.getFechaRegistro(), estado, ger.getFechaDespido());
                JOptionPane.showMessageDialog(this, respuesta);
            }
        }else{
            mensaje = "Cambie un campo para modificar al gerente";
            JOptionPane.showMessageDialog(this, mensaje);
        }
    }
    
    private void consultar(){
        String id = listaIds[comboxEmple.getSelectedIndex()-1];
        Gerente ger = bD.leerGerentePorId(id);
        
        String genero;
        if(ger.getGenero()==0){
            genero = "Masculino";
        }else{
            genero = "Femenino";
        }
        
        int edad = calcularEdad(ger.getFechaNacimiento());
        
        String mensaje = "Nombre: "+ger.getNombre()+"\n"+
                         "Cedula: "+ger.getCedula()+"\n"+
                         "Cargo: "+ger.getCargo()+"\n"+
                         "salario: "+ger.getSalario()+"\n"+
                         "Cuenta Bancaria: "+ger.getCuentaBancaria()+"\n"+
                         "Sede: "+"NO CODEADO"+"\n"+
                         "Fecha Registro: "+ger.getFechaRegistro()+"\n"+
                         "Edad: "+edad+"\n"+
                         "Fecha Nacimiento: "+ger.getFechaNacimiento()+"\n"+
                         "Correo: "+ger.getCorreo()+"\n"+
                         "Genero: "+genero+"\n"+
                         "Dirección: "+ger.getDireccion()+"\n"+
                         "Teléfono: "+ger.getTelefono()+"\n";
        
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    private void despedir(){
        String id = listaIds[comboxEmple.getSelectedIndex()-1];
        Gerente ger = bD.leerGerentePorId(id);
        
        if(ger==null){
          JOptionPane.showMessageDialog(this, "El gerente no se encunetra registrado en el sistema"); ///CAMBIAR EL MENSAJE? 
        }else{        
            String mensaje = "Seguro que desea despedir al gerente:\n"+"Nombre: "+ger.getNombre()+"\nCedula: "+ger.getCedula()+"\n"+
                             "Cargo: "+ger.getCargo()+"\nsalario: "+ger.getSalario()+"\n"+
                             "Sede: "+"NO CODEADO";
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
            if(opcion==0){ //Despedir
                Date fechaSist = new Date(); 
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                String fechaDespido = formato.format(fechaSist); 

                String respuesta = bD.despedirGerente(id, fechaDespido);
                JOptionPane.showMessageDialog(this, respuesta);
            }
        }
    }
    
    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        switch(botonAceptar){
            //Agregar
            case 1:
                agregar();
                break;            
            //Modificar
            case 2:
                modificar();
                break;            
            //Consultar
            case 3:
                consultar();
                break;                
            //Despedir
            case 4:
                despedir();
                break;
        }
    }//GEN-LAST:event_bAceptarActionPerformed

    private void comboxEmpleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboxEmpleItemStateChanged
        if(comboxEmple.getSelectedIndex() == 0){
            bAceptar.setEnabled(false);
            
            if(botonAceptar==2){ limpiarCampos(); habilitarCamposmodf(false);}
        }else{
            bAceptar.setEnabled(true);
            
            if(botonAceptar==2){ llenarCamposModf(); habilitarCamposmodf(true);}
        }
    }//GEN-LAST:event_comboxEmpleItemStateChanged

    private void comboxSedesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboxSedesItemStateChanged
    }//GEN-LAST:event_comboxSedesItemStateChanged

    private void bConsulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsulActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bConsulActionPerformed

    private void tNombreUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNombreUsuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNombreUsuActionPerformed

    private void comboxEmpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxEmpleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxEmpleActionPerformed

    private void bModfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bModfActionPerformed

    
    //Quita los campos usados en la función agregar, consultar y despedir (ej:nombre,cedula,genero,cargo)
    //varControl: true para indicar que sea visible los campos de agregar, false para los de consultar y despedir
    public void cambiarVisibilidadCampos(boolean varControl){
        labContra.setVisible(false);
        tContra.setVisible(false);
        
        labEmple.setVisible(!varControl);
        comboxEmple.setVisible(!varControl);
        
        labNombreUsu.setVisible(varControl);
        labNombre.setVisible(varControl);
        labCedula.setVisible(varControl);
        labCargo.setVisible(varControl);
        labCorreo.setVisible(varControl);
        labCuentaBan.setVisible(varControl);
        labGenero.setVisible(varControl);
        labDir.setVisible(varControl);
        labTel.setVisible(varControl);
        labSal.setVisible(varControl);
        labSede.setVisible(varControl);
        labFechaNac.setVisible(varControl);
        labContra.setVisible(false);
        
        comboxCargo.setVisible(varControl);
        comboxCargo.setSelectedIndex(0);
        comboxGenero.setVisible(varControl);
        comboxGenero.setSelectedIndex(0);
        comboxSedes.setVisible(varControl);
        comboxSedes.setSelectedIndex(0);
        comboxDia.setVisible(varControl);
        comboxDia.setSelectedIndex(0);
        comboxMes.setVisible(varControl);
        comboxMes.setSelectedIndex(0);
        comboxAno.setVisible(varControl);
        comboxAno.setSelectedIndex(0);
        
        tNombreUsu.setVisible(varControl);
        tNombreUsu.setText("");
        tNombre.setVisible(varControl);
        tNombre.setText("");
        tCedula.setVisible(varControl);
        tCedula.setText("");
        tCorreo.setVisible(varControl);
        tCorreo.setText("");
        tCuentaBan.setVisible(varControl);
        tCuentaBan.setText("");
        tDir.setVisible(varControl);
        tDir.setText("");
        tTel.setVisible(varControl);
        tTel.setText("");
        tSal.setVisible(varControl);
        tSal.setText("");
        tContra.setVisible(false);
        tContra.setText("");
    }
    
    //Habilita los campos usados en la función modificar
    //varControl: true para indicar que sea visible, false para lo opuesto
    public void habilitarCamposmodf(boolean varControl){           
        comboxCargo.setEnabled(varControl);
        comboxGenero.setEnabled(varControl);
        comboxSedes.setEnabled(varControl);
        comboxDia.setEnabled(varControl);
        comboxMes.setEnabled(varControl);
        comboxAno.setEnabled(varControl);      
        
        tContra.setEnabled(varControl);
        tNombreUsu.setEnabled(varControl);
        tNombre.setEnabled(varControl);
        tCedula.setEnabled(varControl);
        tCorreo.setEnabled(varControl);
        tCuentaBan.setEnabled(varControl);
        tDir.setEnabled(varControl);
        tTel.setEnabled(varControl);
        tSal.setEnabled(varControl);
    }
    
    public static void main(String args[]) {            
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new prinSuper().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bAgregar;
    private javax.swing.JButton bConsul;
    private javax.swing.JButton bDespedir;
    private javax.swing.JButton bModf;
    private javax.swing.JComboBox<String> comboxAno;
    private javax.swing.JComboBox<String> comboxCargo;
    private javax.swing.JComboBox<String> comboxDia;
    private javax.swing.JComboBox<String> comboxEmple;
    private javax.swing.JComboBox<String> comboxEstado;
    private javax.swing.JComboBox<String> comboxGenero;
    private javax.swing.JComboBox<String> comboxMes;
    private javax.swing.JComboBox<String> comboxSedes;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel fechaYhora;
    private javax.swing.JLabel hora;
    private javax.swing.JLabel iconUsu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JLabel labCargo;
    private javax.swing.JLabel labCedula;
    private javax.swing.JLabel labContra;
    private javax.swing.JLabel labCorreo;
    private javax.swing.JLabel labCuentaBan;
    private javax.swing.JLabel labDir;
    private javax.swing.JLabel labEmple;
    private javax.swing.JLabel labFechaNac;
    private javax.swing.JLabel labGenero;
    private javax.swing.JLabel labGenero1;
    private javax.swing.JLabel labLogo;
    private javax.swing.JLabel labNombre;
    private javax.swing.JLabel labNombreUsu;
    private javax.swing.JLabel labSal;
    private javax.swing.JLabel labSede;
    private javax.swing.JLabel labTel;
    private javax.swing.JTextField tCedula;
    private javax.swing.JTextField tContra;
    private javax.swing.JTextField tCorreo;
    private javax.swing.JTextField tCuentaBan;
    private javax.swing.JTextField tDir;
    private javax.swing.JTextField tNombre;
    private javax.swing.JTextField tNombreUsu;
    private javax.swing.JTextField tSal;
    private javax.swing.JTextField tTel;
    // End of variables declaration//GEN-END:variables
}
