/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;


import Controller.DBConnection;
import Model.*;
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
 * @author melissa, Valeria
 */
public class prinGerente extends javax.swing.JFrame {
    
    //la variable botonAceptar indica el uso que se le da al botón de la izquierda para cada boton principal
    //1:Agregar, 2:Modificar, 3:Consultar, 4:Despedir, 0:nada
    private int botonAceptar = 0;
    private DBConnection bD;
    private String[] listaIds, listaIdsSede;
    private String idGerente;
    
    
    public prinGerente(DBConnection baseDatos, String idGer) {
        initComponents();
        
        bD = baseDatos;
        idGerente = idGer;
        
        
        //Fecha
        Date fechaSist = new Date(); 
        SimpleDateFormat formato = new SimpleDateFormat("dd-MMM-yyyy");
        fecha.setText(formato.format(fechaSist));
        
        //Hora
        Timer tiempo = new Timer(100, new prinGerente.hora());
        tiempo.start();
        
        cambiarVisibilidadCampos(false);
        bAceptar.setVisible(false);
        labEmple.setVisible(false);
        comboxEmple.setVisible(false);
        
        //Elementos del popup menú para modf,agregar y consultar
        this.usuarios.setSelected(true);
        this.sede.setSelected(false);
        
        //Elementos del popup menú para reportes
        this.usuario.setSelected(true);
        this.sedes.setSelected(false);
        this.ventas.setSelected(false);
        
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
    
    
    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
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
        if(nombre.equals("")){
            mensaje = mensaje+"- Nombre\n"; validacion = false;
        }else{
            if(nombre.charAt(0) == ' ') validacion = false;
        } 
        if(cedula.equals("")){ mensaje = mensaje+"- Cedula\n"; validacion = false; }
        if(correo.equals("")){ mensaje = mensaje+"- Correo\n"; validacion = false; }
        if(cuenta.equals("")){ mensaje = mensaje+"- Cuenta\n"; validacion = false; }
        if(direccion.equals("")){ mensaje = mensaje+"- Direccion\n"; validacion = false; }
        if(telefono.equals("")){ mensaje = mensaje+"- Telefono\n"; validacion = false; }
        if(salario.equals("")){ mensaje = mensaje+"- Salario\n"; validacion = false; }
        fechaValida = validarFecha(fechaNac);
        
        if(!validacion){ //Hay campos vacios
            mensaje = "Los siguientes campos están vacios:\n"+mensaje;
            if((!nombre.equals("")) && (nombre.charAt(0) == ' ')) mensaje = "Nombre de Usuario Invalido\n"+mensaje;
            if(!fechaValida) mensaje = "La fecha de nacimiento es invalida\n\n"+mensaje;
        }else{
            if(!fechaValida){ //No hay campos vacios, pero la fecha es invalida
                mensaje = "La fecha de nacimiento es invalida";
                validacion = false; //Se cambia ya que la fecha no es valida
            }
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
    
        
    private String obtenerNumMes(int mes){
        //ene, feb, mar, abr, may, jun, jul, ago, sep, oct, nov, dic
        switch(mes){
            case 1:
                return "ene";
            case 2:
                return "feb";
            case 3:
                return "may";
            case 4:
                return "abr";
            case 5:
                return "may";
            case 6:
                return "jun";
            case 7:
                return "jul";
            case 8:
                return "ago";
            case 9:
                return "sep";
            case 10:
                return "oct";    
            case 11:
                return "nov";
            case 12:
                return "dic";
                
        }
        
        return "";
    }
    
 
    private int calcularEdad(String nacimiento){
        int edad,anoN,anoHoy,mesN,mesHoy,diaN,diaHoy;
        Date fechaSist = new Date(); 
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaHoy = formato.format(fechaSist);
        
        String[] fechaAct = fechaHoy.split("-");        
        String[] fechaNac = nacimiento.split("-");
        
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
    
    
    private void actualizarComboxEmpleVendedores(){
        String empleados = bD.listarVendedores();        
        
        if(empleados.equals("")){ //No Hay empleados
           String[] opciones = { "No seleccionado" };
           comboxEmple.setModel(new DefaultComboBoxModel(opciones));
        }else{ //Hay empleados
            String[] listaEmpleados = empleados.split("\\$");
            listaIds = obtenerListaIds(listaEmpleados);
            String[] opciones = obtenerOpciones(listaEmpleados);
            comboxEmple.setModel(new DefaultComboBoxModel(opciones));
            
        }
    }
    
    
    private void actualizarComboxEmpleJefes(){
        String empleados = bD.listarJefesTaller();        
        
        if(empleados.equals("")){ //No Hay empleados
           String[] opciones = { "No seleccionado" };
           comboxEmple.setModel(new DefaultComboBoxModel(opciones));
        }else{ //Hay empleados
            String[] listaEmpleados = empleados.split("\\$");
            listaIds = obtenerListaIds(listaEmpleados);
            String[] opciones = obtenerOpciones(listaEmpleados);
            comboxEmple.setModel(new DefaultComboBoxModel(opciones));
            
        }
        
    }
    
    
    private void actualizarComboxVendedoresYJefes(){
        Gerente ger = bD.leerGerentePorId(idGerente);
        String empleados = bD.listarVendedoresYJefes(ger.getSede());        
        
        if(empleados.equals("")){ //No Hay empleados
           String[] opciones = { "No seleccionado" };
           comboxEmple.setModel(new DefaultComboBoxModel(opciones));
        }else{ //Hay empleados
            String[] listaEmpleados = empleados.split("\\$");
            listaIds = obtenerListaIds(listaEmpleados);
            String[] opciones = obtenerOpciones(listaEmpleados);
            comboxEmple.setModel(new DefaultComboBoxModel(opciones));
        }
    }
    
    
    private String[] obtenerOpcionesSedes(String[] listaSedes){
        String[] opciones = new String[listaSedes.length+1];
        String[] sede;
        opciones[0] = "No seleccionado";
        
        for(int i=0,j=1; i<(listaSedes.length); i++,j++){
            sede = listaSedes[i].split(",");
            opciones[j] = sede[1]+" "+sede[2].replace("$","");
        }
        
        return opciones;
    }
       
    
    private String[] obtenerListaIdsSedes(String[] listaSedes){
        String[] listaDeIds = new String[listaSedes.length];
        String[] sede;
        
        for(int i=0; i<(listaDeIds.length); i++){
            sede = listaSedes[i].split(",");
            listaDeIds[i] = sede[0];
        }
        
        return listaDeIds;
    }
    
    private void actualizarComboxSedes(){
        String sedes = "";
        if(botonAceptar==7 || botonAceptar==6 || botonAceptar==8){ sedes = bD.listarSedes(true); }
        else { sedes = bD.listarSedes(false); }
        
        if(sedes.equals("")){ //No Hay empleados
           String[] opciones = { "No seleccionado" };
           comboxSedes.setModel(new DefaultComboBoxModel(opciones));
        }else{ //Hay empleados
            String[] listaSedes = sedes.split("\\$");
            listaIdsSede = obtenerListaIdsSedes(listaSedes);
            String[] opciones = obtenerOpcionesSedes(listaSedes);
            comboxSedes.setModel(new DefaultComboBoxModel(opciones));
            
        }
    }
        
    private void actualizarComboxSedesMod(){
        String sedes = "";
        if(botonAceptar==7 || botonAceptar==6 || botonAceptar==8){ sedes = bD.listarSedes(true); }
        else { sedes = bD.listarSedes(false); }
        
        if(sedes.equals("")){ //No Hay empleados
           String[] opciones = { "No seleccionado" };
           comboxEmple.setModel(new DefaultComboBoxModel(opciones));
        }else{ //Hay empleados
            String[] listaSedes = sedes.split("\\$");
            listaIdsSede = obtenerListaIdsSedes(listaSedes);
            String[] opciones = obtenerOpcionesSedes(listaSedes);
            comboxEmple.setModel(new DefaultComboBoxModel(opciones));
            
        }
    }
   

    private void llenarCamposModfVendedor(){
        String id = listaIds[comboxEmple.getSelectedIndex()-1];
        Vendedor ven = bD.leerVendedorPorId(id);
        
        tNombreUsu.setText(ven.getNombreUsuario());
        tContra.setText(ven.getContrasena());
        tNombre.setText(ven.getNombre());
        tCorreo.setText(ven.getCorreo());
        tCuentaBan.setText(ven.getCuentaBancaria());
        comboxGenero.setSelectedIndex(ven.getGenero());
        tDir.setText(ven.getDireccion());
        tTel.setText(ven.getTelefono());
        tSal.setText(Float.toString(ven.getSalario()));
        
        String[] fechaNac = ven.getFechaNacimiento().split("-");
        int diaNac = Integer.parseInt(fechaNac[2]);
        String mesNac = obtenerNumMes(Integer.valueOf(fechaNac[1]));
        int anoNac = Integer.parseInt(fechaNac[0]);
        
        comboxDia.setSelectedIndex(diaNac-1); //El Combobox empieza desde 0
        comboxMes.setSelectedItem(mesNac);
        comboxAno.setSelectedIndex((anoNac-2000)*-1); //El año 2000 es la posición 0, *-1 porque puede dar negativo
        comboxSedes.setSelectedIndex(ven.getIdSede());
    }
    
    
    private void llenarCamposModfJefeTaller(){
        String id = listaIds[comboxEmple.getSelectedIndex()-1];
        JefeTaller jef = bD.leerJefeTallerPorId(id);
        
        tNombreUsu.setText(jef.getNombreUsuario());
        tContra.setText(jef.getContrasena());
        tNombre.setText(jef.getNombre());
        tCorreo.setText(jef.getCorreo());
        tCuentaBan.setText(jef.getCuentaBancaria());
        comboxGenero.setSelectedIndex(jef.getGenero());
        tDir.setText(jef.getDireccion());
        tTel.setText(jef.getTelefono());
        tSal.setText(Float.toString(jef.getSalario()));
        
        String[] fechaNac = jef.getFechaNacimiento().split("-");
        int diaNac = Integer.parseInt(fechaNac[2]);
        int mesNac = obtenerMesNum(fechaNac[1]);
        int anoNac = Integer.parseInt(fechaNac[0]);
        
        comboxDia.setSelectedIndex(diaNac-1); 
        comboxMes.setSelectedIndex(mesNac);
        comboxAno.setSelectedIndex((anoNac-2000)*-1); 
        
        //actualizarComboxSedes();
        comboxSedes.setSelectedIndex(jef.getIdSede());
    }

    
    private void agregarVendedor(){
        String nombreUsu = tNombreUsu.getText();
        String nombre = tNombre.getText();
        String cedula = tCedula.getText();
        String cargo = comboxCargo.getSelectedItem().toString();
        String correo = tCorreo.getText();
        String cuenta = tCuentaBan.getText();
        int genero = comboxGenero.getSelectedIndex();
        int sedeGerente = bD.leerGerentePorId(idGerente).getIdSede();
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
        String fechaNac = anoCumple+"-"+obtenerMesNum(mesCumple)+"-"+diaCumple;
                       
        boolean validacion = validarCampos(nombreUsu,nombre,cedula,correo,cuenta,direccion,telefono,tSal.getText(),fechaNac);
        if(validacion){
            //Fecha de reg
            Date fechaSist = new Date(); 
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaReg = formato.format(fechaSist);      
            
            String respuesta = bD.crearVendedor(nombre, cedula,cargo,telefono,direccion,genero,fechaNac,correo,salario, cuenta,fechaReg, nombreUsu, sedeGerente);
            if(respuesta.contains("La cedula") && (!respuesta.contains("El nombre de usuario"))) limpiarCamposUsuarios();
            JOptionPane.showMessageDialog(this, respuesta);
        }        
    }
    
    
    private void agregarJefeTaller(){
    String nombreUsu = tNombreUsu.getText();
    String nombre = tNombre.getText();
    String cedula = tCedula.getText();
    String cargo = comboxCargo.getSelectedItem().toString();
    String correo = tCorreo.getText();
    String cuenta = tCuentaBan.getText();
    int genero = comboxGenero.getSelectedIndex();
    int sedeGerente = bD.leerGerentePorId(idGerente).getIdSede();
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
    String fechaNac = anoCumple+"-"+obtenerMesNum(mesCumple)+"-"+diaCumple;
    
    boolean validacion = validarCampos(nombreUsu,nombre,cedula,correo,cuenta,direccion,telefono,tSal.getText(),fechaNac);
    if(validacion){
        //Fecha de reg
        Date fechaSist = new Date(); 
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaReg = formato.format(fechaSist);       

        String respuesta = bD.crearJefeTaller(nombreUsu,nombre,cedula,cargo, telefono,direccion,genero,fechaNac,correo,salario, cuenta, fechaReg, idGerente, sedeGerente);
        if(respuesta.contains("La cedula")&& (!respuesta.contains("El nombre de usuario"))) limpiarCamposUsuarios();
        JOptionPane.showMessageDialog(this, respuesta);
    }        
}
    
    private boolean validarCamposSedes(String nombre, String direccion){
        boolean validacion = true; 
        String mensaje = ""; //
        
        if(nombre.equals("")){
            mensaje = mensaje+"- Nombre\n"; validacion = false;
        }else{
            if(nombre.charAt(0) == ' ') validacion = false;
        }        
        if(direccion.equals("")){ mensaje = mensaje+"- Direccion\n"; validacion = false; }
        
        
        if(!validacion){ //Hay campos vacios            
            mensaje = "Los siguientes campos están vacios:\n"+mensaje;
            if((!nombre.equals("")) && (nombre.charAt(0) == ' ')) mensaje = "Nombre de Sede Invalido\n"+mensaje;
        }
       
        return validacion;   
    }
    
    
    private void agregarSede(){
    String nombreSede = tNombre.getText();
    String direccion = tDir.getText();

    boolean validacion = validarCamposSedes(nombreSede, direccion);
    
    if(validacion){
        Date fechaSist = new Date(); 
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fechaReg = formato.format(fechaSist);          

        String respuesta = bD.crearSede( nombreSede,direccion, fechaReg);
        JOptionPane.showMessageDialog(this, respuesta);
    }        
}
    
    
    private void modificarVendedor(){        
        String mensaje = "";
        String respuesta ="";
        String nombreUsu = tNombreUsu.getText();
        String contrasena = tContra.getText();
        String nombre = tNombre.getText();
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
        String fechaNac = anoCumple+"-"+obtenerMesNum(mesCumple)+"-"+diaCumple;
        
        //Datos Anteriores
        String id = listaIds[comboxEmple.getSelectedIndex()];
        Vendedor ven = bD.leerVendedorPorId(id);
        
        //Comparación
        if(!nombreUsu.equals(ven.getNombreUsuario())) mensaje = mensaje+"Nombre Usuario\n";
        if(!contrasena.equals(ven.getContrasena())) mensaje = mensaje+"Contraseña\n";
        if(!nombre.equals(ven.getNombre())) mensaje = mensaje+"Nombre\n";
        if(!correo.equals(ven.getCorreo())) mensaje = mensaje+"Correo\n";
        if(!cuenta.equals(ven.getCuentaBancaria())) mensaje = mensaje+"Cuenta Bancaria\n";
        if(genero != ven.getGenero()) mensaje = mensaje+"Genero\n";
        if(!direccion.equals(ven.getDireccion())) mensaje = mensaje+"Direccion\n";
        if(!telefono.equals(ven.getTelefono())) mensaje = mensaje+"Telefono\n";
        if(salario != ven.getSalario()) mensaje = mensaje+"Salario\n";
        if(!fechaNac.equals(ven.getFechaNacimiento())) mensaje = mensaje+"Fecha de nacimiento\n";
        if(ven.isHabilitado()) mensaje = mensaje+"Estado\n";
        
        
        boolean validacion = validarFecha(fechaNac);
        //Fecha de reg
        if (validacion){
        Date fechaSist = new Date(); 
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaReg = formato.format(fechaSist);
        
            if(!mensaje.equals("")){
                mensaje = "Los siguientes campos se van a modificar:\n"+mensaje;
                int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);

                if(opcion==0){ //Modificar
                    respuesta = bD.actualizarVendedor(id, nombre,telefono,direccion,genero,fechaNac, correo, salario, cuenta, fechaReg,nombreUsu, contrasena, true, ven.getFechaDespido());
                    JOptionPane.showMessageDialog(this, respuesta);
                }
            }else{
                mensaje = "Cambie un campo para modificar al Vendedor";
                JOptionPane.showMessageDialog(this, mensaje);
            }
        }else{
            JOptionPane.showMessageDialog(this, respuesta);
        }
    }
    
    
    private void modificarJefeTaller(){        
        String mensaje = "";
        
        //Datos Modf
        String nombreUsu = tNombreUsu.getText();
        String contrasena = tContra.getText();
        String nombre = tNombre.getText();
        String correo = tCorreo.getText();
        String cuenta = tCuentaBan.getText();
        //String cargo = comboxCargo.getSelectedItem().toString();
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
        String fechaNac = anoCumple+"-"+obtenerMesNum(mesCumple)+"-"+diaCumple;
        
        
        //Datos Anteriores
        String id = listaIds[comboxEmple.getSelectedIndex()-1];
        JefeTaller jef = bD.leerJefeTallerPorId(id);
        
        //Comparación
        if(!nombreUsu.equals(jef.getNombreUsuario())) mensaje = mensaje+"Nombre Usuario\n";
        if(!contrasena.equals(jef.getContrasena())) mensaje = mensaje+"Contraseña\n";
        if(!nombre.equals(jef.getNombre())) mensaje = mensaje+"Nombre\n";
        if(!correo.equals(jef.getCorreo())) mensaje = mensaje+"Correo\n";
        if(!cuenta.equals(jef.getCuentaBancaria())) mensaje = mensaje+"Cuenta Bancaria\n";
        if(genero != jef.getGenero()) mensaje = mensaje+"Genero\n";
        if(!direccion.equals(jef.getDireccion())) mensaje = mensaje+"Direccion\n";
        if(!telefono.equals(jef.getTelefono())) mensaje = mensaje+"Telefono\n";
        if(salario != jef.getSalario()) mensaje = mensaje+"Salario\n";
        if(!fechaNac.equals(jef.getFechaNacimiento())) mensaje = mensaje+"Fecha de nacimiento\n";
        if(jef.isHabilitado()) mensaje = mensaje+"Estado\n";
        
        if(!mensaje.equals("")){
            mensaje = "Los siguientes campos se van a modificar:\n"+mensaje;
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
            
            if(opcion==0){ //Modificar
                String respuesta = bD.actualizarJefe(id, contrasena,nombreUsu,nombre,telefono,direccion,genero, fechaNac,correo,salario,cuenta,jef.getFechaRegistro(),true,jef.getFechaDespido());
                JOptionPane.showMessageDialog(this, respuesta);
            }
        }else{
            mensaje = "Cambie un campo para modificar al Jefe de Taller";
            JOptionPane.showMessageDialog(this, mensaje);
        }
    }
    
    
    private void modificarSede(){
        String mensaje = "";
        
        //Datos Modf
        String nombre = tNombre.getText();
        String direccion = tDir.getText();
        String telefono = tTel.getText();
        
        //Datos Anteriores
        String id = listaIds[comboxEmple.getSelectedIndex()-1];
        Sede sede = bD.leerSedePorId(id);
        String fechaCreacion = sede.getFechaCreacion();
        String fechaFinalizacion = null;
        //Comparación
        
        if(!nombre.equals(sede.getNombreSede())) mensaje = mensaje+"Nombre\n";
        
        if(!direccion.equals(sede.getDireccion())) mensaje = mensaje+"Direccion\n";
        if(!direccion.equals(sede.getDireccion())) mensaje = mensaje+"Direccion\n";
        
        if(!mensaje.equals("")){
            mensaje = "Los siguientes campos se van a modificar:\n"+mensaje;
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
            
        
            
            if(opcion==0){ //Modificar
                String respuesta = bD.actualizarSede(id, nombre, direccion, fechaCreacion, fechaFinalizacion, idGerente);
                JOptionPane.showMessageDialog(this, respuesta);
            }
        }else{
            mensaje = "Cambie un campo para modificar al Jefe de Taller";
            JOptionPane.showMessageDialog(this, mensaje);
        }
    }
    
     private void consultarSede(int index){
        String id = listaIdsSede[index-1];
        Sede sede = bD.leerSedePorId(id);
        
        String mensaje = "Nombre: "+sede.getNombreSede()+"\n"+
                         "Fecha Creacion: "+sede.getFechaCreacion()+"\n"+
                         "Dirección: "+sede.getDireccion()+"\n";
        
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    private void consultar(int index){
        String id = listaIds[index-1];
        Vendedor vendedor = bD.leerVendedorPorId(id);
        JefeTaller jefe = bD.leerJefeTallerPorId(id);
        String genero;
        
        if(vendedor != null){
            if(vendedor.getGenero()==0){
                genero = "Masculino";
            }else{
                genero = "Femenino";
            }
            
            int edad = calcularEdad(vendedor.getFechaNacimiento());
        
            String mensaje = "Nombre: "+vendedor.getNombre()+"\n"+
                         "Cedula: "+vendedor.getCedula()+"\n"+
                         "Cargo: "+vendedor.getCargo()+"\n"+
                         "salario: "+vendedor.getSalario()+"\n"+
                         "Cuenta Bancaria: "+vendedor.getCuentaBancaria()+"\n"+
                         "Sede: "+ vendedor.getSede()+"\n"+
                         "Fecha Registro: "+vendedor.getFechaRegistro()+"\n"+
                         "Edad: "+edad+"\n"+
                         "Fecha Nacimiento: "+vendedor.getFechaNacimiento()+"\n"+
                         "Correo: "+vendedor.getCorreo()+"\n"+
                         "Genero: "+genero+"\n"+
                         "Dirección: "+vendedor.getDireccion()+"\n"+
                         "Teléfono: "+vendedor.getTelefono()+"\n";
            
        
            JOptionPane.showMessageDialog(this, mensaje);
        }else{
           if(jefe.getGenero()==0){
                genero = "Masculino";
            }else{
                genero = "Femenino";
            }
       
            int edad = calcularEdad(jefe.getFechaNacimiento());
        
            String mensaje = "Nombre: "+jefe.getNombre()+"\n"+
                         "Cedula: "+jefe.getCedula()+"\n"+
                         "Cargo: "+jefe.getCargo()+"\n"+
                         "salario: "+jefe.getSalario()+"\n"+
                         "Cuenta Bancaria: "+jefe.getCuentaBancaria()+"\n"+
                         "Sede: "+jefe.getSede()+"\n"+
                         "Fecha Registro: "+jefe.getFechaRegistro()+"\n"+
                         "Edad: "+edad+"\n"+
                         "Fecha Nacimiento: "+jefe.getFechaNacimiento()+"\n"+
                         "Correo: "+jefe.getCorreo()+"\n"+
                         "Genero: "+genero+"\n"+
                         "Dirección: "+jefe.getDireccion()+"\n"+
                         "Teléfono: "+jefe.getTelefono()+"\n";
        
            JOptionPane.showMessageDialog(this, mensaje);
        }
    }
    
    
    private void despedir(int index){
        String id = listaIds[index-1];
        Vendedor vendedor = bD.leerVendedorPorId(id);
        JefeTaller jefe = bD.leerJefeTallerPorId(id);
        
        if(vendedor != null){
            String mensaje = "Seguro que desea despedir al vendedor:\n"+"Nombre: "+vendedor.getNombre()+"\nCedula: "+vendedor.getCedula()+"\n"+
                             "Cargo: "+vendedor.getCargo()+"\nsalario: "+vendedor.getSalario()+"\n"+
                             "Sede: "+vendedor.getSede();
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
            if(opcion==0){ //Despedir
                Date fechaSist = new Date(); 
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaDespido = formato.format(fechaSist); 

                String respuesta = bD.despedirVendedor(id, fechaDespido);
                JOptionPane.showMessageDialog(this, respuesta);
            }
        }else{
            String mensaje = "Seguro que desea despedir al jefe de taller:\n"+"Nombre: "+jefe.getNombre()+"\nCedula: "+jefe.getCedula()+"\n"+
                             "Cargo: "+jefe.getCargo()+"\nsalario: "+jefe.getSalario()+"\n"+
                             "Sede: "+jefe.getSede();
            int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
            if(opcion==0){ //Despedir
                Date fechaSist = new Date(); 
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                String fechaDespido = formato.format(fechaSist); 

                String respuesta = bD.despedirJefeTaller(id, fechaDespido);
                JOptionPane.showMessageDialog(this, respuesta);
            }
        }
    }
        
    private void deshabilitarSede(int index){
        String id = listaIds[index-1];
        Sede sede = bD.leerSedePorId(id);        

        String mensaje = "Seguro que desea deshabilitar la sede:\n"+"Nombre: "+sede.getNombreSede()+"\nFecha de Creacion: "+sede.getDireccion()+"\n"+
                         "Direccion: "+sede.getDireccion();
        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "", 0);
        if(opcion==0){ //Despedir
            Date fechaSist = new Date(); 
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaDeshabilitado = formato.format(fechaSist); 

            //String respuesta = bD.eliminarSede(id);
            //JOptionPane.showMessageDialog(this, respuesta);
        }
        
    }
        
    //Limpia los campos(jTextfields) de la interfaz
    private void limpiarCamposUsuarios(){
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
    
    
    public void habilitarCamposmodf(boolean varControl){           
        comboxCargo.setEnabled(false);
        comboxGenero.setEnabled(varControl);
        comboxSedes.setEnabled(varControl);
        comboxDia.setEnabled(varControl);
        comboxMes.setEnabled(varControl);
        comboxAno.setEnabled(varControl);      
        
        tContra.setEnabled(varControl);
        tNombreUsu.setEnabled(varControl);
        tNombre.setEnabled(varControl);
        tCedula.setEnabled(false);
        tCorreo.setEnabled(varControl);
        tCuentaBan.setEnabled(varControl);
        tDir.setEnabled(varControl);
        tTel.setEnabled(varControl);
        tSal.setEnabled(varControl);
    }
    
    //Quita los campos usados en la función agregar, consultar y despedir (ej:nombre,cedula,genero,cargo)
    //varControl: true para indicar que sea visible los campos de agregar, false para los de consultar y despedir
    public void cambiarVisibilidadCampos(boolean varControl){
        labContra.setVisible(false);
        tContra.setVisible(false);
        
        labEmple.setVisible(!varControl);
        comboxEmple.setVisible(!varControl);
        
        labEmple.setText("Empleado:");
        labNombreUsu.setVisible(varControl);
        labNombre.setText("Nombre:");
        labNombre.setVisible(varControl);
        labCedula.setVisible(varControl);
        labCargo.setVisible(varControl);
        labCorreo.setVisible(varControl);
        labCuentaBan.setVisible(varControl);
        labGenero.setVisible(varControl);
        labDir.setVisible(varControl);
        labTel.setVisible(varControl);
        labSal.setVisible(varControl);
        labSede.setVisible(false);
        labFechaNac.setText("Fecha de nacimiento:");
        labFechaNac.setVisible(varControl);
        
        comboxCargo.setVisible(varControl);
        comboxCargo.setSelectedIndex(0);
        comboxGenero.setVisible(varControl);
        comboxGenero.setSelectedIndex(0);
        comboxSedes.setVisible(false);
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
    }
    
    //Quita los campos usados en la función consultar y despedir (ej:empleado)
    //varControl: true para indicar que sea visible, false para lo opuesto
    public void cambiarVisibilidadCamposmodf(boolean varControl){           
        labEmple.setVisible(varControl);
        comboxEmple.setVisible(varControl);
        
        labEmple.setText("Empleado:");
        comboxCargo.setEnabled(!varControl);
        comboxGenero.setEnabled(!varControl);
        comboxSedes.setEnabled(!varControl);
        comboxDia.setEnabled(!varControl);
        comboxMes.setEnabled(!varControl);
        comboxAno.setEnabled(!varControl);      
        
        labContra.setVisible(varControl);
        labCedula.setVisible(!varControl);
        tContra.setVisible(varControl);
        tContra.setEnabled(!varControl);
        tContra.setText("");
        
        
        tNombreUsu.setEnabled(!varControl);
        tNombreUsu.setText("");
        tNombre.setEnabled(!varControl);
        tNombre.setText("");
        tCedula.setVisible(!varControl);
        tCedula.setText("");
        tCorreo.setEnabled(!varControl);
        tCorreo.setText("");
        tCuentaBan.setEnabled(!varControl);
        tCuentaBan.setText("");
        tDir.setEnabled(!varControl);
        tDir.setText("");
        tTel.setEnabled(!varControl);
        tTel.setText("");
        tSal.setEnabled(!varControl);
        tSal.setText("");
    }
    
    
    public void cambiarVisibilidadCamposSede(boolean varControl){
        
        labNombre.setText("Nombre de sede:");
        //labFechaNac.setText("Fecha de creación:");
        labFechaNac.setVisible(varControl);
        
        labContra.setVisible(false);
        tContra.setVisible(false);
        
        labEmple.setVisible(varControl);
        comboxEmple.setVisible(varControl);
        
        labNombreUsu.setVisible(varControl);
        labCedula.setVisible(varControl);
        labCargo.setVisible(varControl);
        labCorreo.setVisible(varControl);
        labCuentaBan.setVisible(varControl);
        labGenero.setVisible(varControl);
        labSal.setVisible(varControl);
        labSede.setVisible(varControl);
        labTel.setVisible(varControl);
        
        comboxCargo.setVisible(varControl);
        comboxCargo.setSelectedIndex(0);
        comboxGenero.setVisible(varControl);
        comboxGenero.setSelectedIndex(0);
        comboxSedes.setVisible(varControl);
        comboxSedes.setSelectedIndex(0);
        comboxDia.setVisible(varControl);
        comboxDia.setSelectedIndex(0);
        comboxAno.setVisible(varControl);
        comboxAno.setSelectedIndex(0);
        comboxMes.setVisible(varControl);
        comboxMes.setSelectedIndex(0);
        
        tNombreUsu.setVisible(varControl);
        tNombreUsu.setText("");
        tCedula.setVisible(varControl);
        tCedula.setText("");
        tCorreo.setVisible(varControl);
        tCorreo.setText("");
        tCuentaBan.setVisible(varControl);
        tCuentaBan.setText("");
        tSal.setVisible(varControl);
        tSal.setText("");
        tTel.setVisible(varControl);
        tTel.setText("");
  
    }
    
    
    public void cambiarVisibilidadCamposSedeModf(boolean varControl){
        
        labNombre.setText("Nombre de sede:");
        labFechaNac.setText("Fecha de creación:");
        labEmple.setText("Sede:");
        labEmple.setVisible(!varControl);
        comboxEmple.setVisible(!varControl);
        
        labContra.setVisible(false);
        tContra.setVisible(false);
        
        labNombreUsu.setVisible(varControl);
        labCedula.setVisible(varControl);
        labCargo.setVisible(varControl);
        labCorreo.setVisible(varControl);
        labCuentaBan.setVisible(varControl);
        labGenero.setVisible(varControl);
        labSal.setVisible(varControl);
        labSede.setVisible(varControl);
        
        comboxCargo.setVisible(varControl);
        comboxCargo.setSelectedIndex(0);
        comboxGenero.setVisible(varControl);
        comboxGenero.setSelectedIndex(0);
        comboxSedes.setVisible(varControl);
        comboxSedes.setSelectedIndex(0);
       
        comboxDia.setEnabled(varControl);
        comboxMes.setEnabled(varControl);
        comboxAno.setEnabled(varControl);
        tNombre.setEnabled(varControl);
        tDir.setEnabled(varControl);
        tTel.setEnabled(varControl);
        
        tNombreUsu.setVisible(varControl);
        tNombreUsu.setText("");
        tCedula.setVisible(varControl);
        tCedula.setText("");
        tCorreo.setVisible(varControl);
        tCorreo.setText("");
        tCuentaBan.setVisible(varControl);
        tCuentaBan.setText("");
        tSal.setVisible(varControl);
        tSal.setText("");
  
    }
    
          

       
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        users = new javax.swing.JPopupMenu();
        usuarios = new javax.swing.JCheckBoxMenuItem();
        sede = new javax.swing.JCheckBoxMenuItem();
        opReporte = new javax.swing.JPopupMenu();
        usuario = new javax.swing.JCheckBoxMenuItem();
        sedes = new javax.swing.JCheckBoxMenuItem();
        ventas = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
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
        jPanel3 = new javax.swing.JPanel();
        bAgregarUsr = new javax.swing.JButton();
        bModf = new javax.swing.JButton();
        bConsul = new javax.swing.JButton();
        bDeshabilitarSede = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bAgregarSede = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        bModfSede = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        bConsulSede = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        bDespedirUsr = new javax.swing.JButton();
        fechaYhora = new javax.swing.JLabel();
        fecha = new javax.swing.JLabel();
        hora = new javax.swing.JLabel();

        users.setToolTipText("");

        usuarios.setSelected(true);
        usuarios.setText("Usuario");
        usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                usuariosMouseReleased(evt);
            }
        });
        users.add(usuarios);

        sede.setSelected(true);
        sede.setText("Sedes");
        sede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sedeMouseReleased(evt);
            }
        });
        users.add(sede);

        usuario.setSelected(true);
        usuario.setText("Gestion usuarios");
        usuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                usuarioMouseReleased(evt);
            }
        });
        opReporte.add(usuario);

        sedes.setSelected(true);
        sedes.setText("Gestion Sedes");
        sedes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sedesMouseReleased(evt);
            }
        });
        opReporte.add(sedes);

        ventas.setSelected(true);
        ventas.setText("Gestion Ventas");
        ventas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ventasMouseReleased(evt);
            }
        });
        opReporte.add(ventas);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel5.setText("Gerente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));

        labNombreUsu.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labNombreUsu.setForeground(new java.awt.Color(255, 255, 255));
        labNombreUsu.setText("Nombre de usuario:");

        labCedula.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labCedula.setForeground(new java.awt.Color(255, 255, 255));
        labCedula.setText("Cedula:");

        labCargo.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labCargo.setForeground(new java.awt.Color(255, 255, 255));
        labCargo.setText("Cargo:");

        labCorreo.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labCorreo.setForeground(new java.awt.Color(255, 255, 255));
        labCorreo.setText("Correo:");

        labCuentaBan.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labCuentaBan.setForeground(new java.awt.Color(255, 255, 255));
        labCuentaBan.setText("Cuenta Bancaria:");

        labGenero.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labGenero.setForeground(new java.awt.Color(255, 255, 255));
        labGenero.setText("Genero:");

        labDir.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labDir.setForeground(new java.awt.Color(255, 255, 255));
        labDir.setText("Dirección:");

        labTel.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labTel.setForeground(new java.awt.Color(255, 255, 255));
        labTel.setText("Telefono:");

        labSal.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labSal.setForeground(new java.awt.Color(255, 255, 255));
        labSal.setText("Salario:");

        labSede.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labSede.setForeground(new java.awt.Color(255, 255, 255));
        labSede.setText("Sede:");

        labFechaNac.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labFechaNac.setForeground(new java.awt.Color(255, 255, 255));
        labFechaNac.setText("Fecha de nacimiento:");

        tNombreUsu.setToolTipText("");
        tNombreUsu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNombreUsuKeyTyped(evt);
            }
        });

        tCedula.setToolTipText("");
        tCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tCedulaKeyTyped(evt);
            }
        });

        tCorreo.setToolTipText("");
        tCorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tCorreoKeyTyped(evt);
            }
        });

        tCuentaBan.setToolTipText("");
        tCuentaBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tCuentaBanKeyTyped(evt);
            }
        });

        tDir.setToolTipText("");
        tDir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tDirKeyTyped(evt);
            }
        });

        labNombre.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labNombre.setForeground(new java.awt.Color(255, 255, 255));
        labNombre.setText("Nombres:");
        labNombre.setToolTipText("");

        tNombre.setToolTipText("");
        tNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tNombreKeyTyped(evt);
            }
        });

        comboxGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Femenino" }));

        tTel.setToolTipText("");
        tTel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tTelKeyTyped(evt);
            }
        });

        tSal.setToolTipText("");
        tSal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tSalKeyTyped(evt);
            }
        });

        comboxSedes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sin sede" }));

        comboxDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        comboxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic" }));

        comboxAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944" }));

        comboxCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No seleccionado", "Vendedor", "Jefe de taller" }));
        comboxCargo.setEnabled(false);

        bAceptar.setFont(new java.awt.Font("Segoe UI Light", 0, 16)); // NOI18N
        bAceptar.setText("Agregar");
        bAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bAceptarMouseClicked(evt);
            }
        });
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });

        labEmple.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labEmple.setForeground(new java.awt.Color(255, 255, 255));
        labEmple.setText("Empleado");

        comboxEmple.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No seleccionado" }));
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

        labContra.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        labContra.setForeground(new java.awt.Color(255, 255, 255));
        labContra.setText("Contraseña:");

        tContra.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labFechaNac)
                        .addGap(19, 19, 19)
                        .addComponent(comboxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboxMes, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labDir)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labNombreUsu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labCedula)
                                .addComponent(labCargo)
                                .addComponent(labCorreo)
                                .addComponent(labCuentaBan)
                                .addComponent(labGenero)
                                .addComponent(labNombre)
                                .addComponent(labTel)
                                .addComponent(labSal)
                                .addComponent(labSede)
                                .addComponent(labEmple, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(labContra))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tNombreUsu)
                            .addComponent(tCedula)
                            .addComponent(tCorreo)
                            .addComponent(tCuentaBan)
                            .addComponent(comboxGenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tSal)
                            .addComponent(comboxSedes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboxCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboxEmple, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tContra)
                            .addComponent(tNombre)
                            .addComponent(tDir)
                            .addComponent(tTel, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(bAceptar)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labEmple)
                    .addComponent(comboxEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCargo)
                    .addComponent(comboxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labCorreo)
                    .addComponent(tCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tCuentaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labCuentaBan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labGenero)
                    .addComponent(comboxGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labDir)
                    .addComponent(tDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labTel)
                    .addComponent(tTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labSal, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tSal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labSede)
                    .addComponent(comboxSedes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labFechaNac)
                    .addComponent(comboxDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboxMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboxAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(bAceptar))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        bAgregarUsr.setBackground(new java.awt.Color(255, 255, 255));
        bAgregarUsr.setForeground(new java.awt.Color(51, 51, 51));
        bAgregarUsr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO addUser.png"))); // NOI18N
        bAgregarUsr.setToolTipText("");
        bAgregarUsr.setBorderPainted(false);
        bAgregarUsr.setContentAreaFilled(false);
        bAgregarUsr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bAgregarUsrMouseClicked(evt);
            }
        });

        bModf.setBackground(new java.awt.Color(255, 255, 255));
        bModf.setForeground(new java.awt.Color(51, 51, 51));
        bModf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO modifyUser.png"))); // NOI18N
        bModf.setBorderPainted(false);
        bModf.setContentAreaFilled(false);
        bModf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bModfMouseClicked(evt);
            }
        });

        bConsul.setBackground(new java.awt.Color(255, 255, 255));
        bConsul.setForeground(new java.awt.Color(51, 51, 51));
        bConsul.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO searchUser.png"))); // NOI18N
        bConsul.setBorderPainted(false);
        bConsul.setContentAreaFilled(false);
        bConsul.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bConsulMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bConsulMouseReleased(evt);
            }
        });

        bDeshabilitarSede.setBackground(new java.awt.Color(255, 255, 255));
        bDeshabilitarSede.setForeground(new java.awt.Color(51, 51, 51));
        bDeshabilitarSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO workshopabort.png"))); // NOI18N
        bDeshabilitarSede.setBorderPainted(false);
        bDeshabilitarSede.setContentAreaFilled(false);
        bDeshabilitarSede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDeshabilitarSedeMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel1.setText("-------- Agregar --------");

        bAgregarSede.setBackground(new java.awt.Color(255, 255, 255));
        bAgregarSede.setForeground(new java.awt.Color(51, 51, 51));
        bAgregarSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO workshopadd.png"))); // NOI18N
        bAgregarSede.setToolTipText("");
        bAgregarSede.setBorderPainted(false);
        bAgregarSede.setContentAreaFilled(false);
        bAgregarSede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bAgregarSedeMouseClicked(evt);
            }
        });
        bAgregarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarSedeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel2.setText("-------- Deshabilitar --------");

        bModfSede.setBackground(new java.awt.Color(255, 255, 255));
        bModfSede.setForeground(new java.awt.Color(51, 51, 51));
        bModfSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO workshopedit.png"))); // NOI18N
        bModfSede.setBorderPainted(false);
        bModfSede.setContentAreaFilled(false);
        bModfSede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bModfSedeMouseClicked(evt);
            }
        });
        bModfSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bModfSedeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel3.setText("-------- Modificar --------");

        bConsulSede.setBackground(new java.awt.Color(255, 255, 255));
        bConsulSede.setForeground(new java.awt.Color(51, 51, 51));
        bConsulSede.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO workshopsearch.png"))); // NOI18N
        bConsulSede.setBorderPainted(false);
        bConsulSede.setContentAreaFilled(false);
        bConsulSede.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bConsulSedeMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 15)); // NOI18N
        jLabel4.setText("-------- Consultar --------");

        bDespedirUsr.setBackground(new java.awt.Color(255, 255, 255));
        bDespedirUsr.setForeground(new java.awt.Color(51, 51, 51));
        bDespedirUsr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ICO fireUser.png"))); // NOI18N
        bDespedirUsr.setBorderPainted(false);
        bDespedirUsr.setContentAreaFilled(false);
        bDespedirUsr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bDespedirUsrMouseClicked(evt);
            }
        });

        fechaYhora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        fechaYhora.setText("Fecha  y hora:");

        fecha.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        fecha.setText("dd-mm-yyyy");

        hora.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        hora.setText("hh:mm:ss");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(bModf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bAgregarUsr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bModfSede, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bAgregarSede, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(bConsul, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bConsulSede, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fechaYhora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hora))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(bDespedirUsr, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bDeshabilitarSede, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bAgregarSede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bAgregarUsr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bModf, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bModfSede, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bConsul, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bConsulSede, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bDespedirUsr, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bDeshabilitarSede, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fecha)
                    .addComponent(fechaYhora)
                    .addComponent(hora))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usuariosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuariosMouseReleased
        if(this.usuarios.isSelected()){
            this.sede.setSelected(false);
        }
    }//GEN-LAST:event_usuariosMouseReleased

    private void sedeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sedeMouseReleased
        if(this.sede.isSelected()){
            this.usuarios.setSelected(false);
        }
    }//GEN-LAST:event_sedeMouseReleased

    private void usuarioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usuarioMouseReleased
        if(this.usuario.isSelected()){
            this.sedes.setSelected(false);
            this.ventas.setSelected(false);
        }
    }//GEN-LAST:event_usuarioMouseReleased

    private void sedesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sedesMouseReleased
        if(this.sedes.isSelected()){
            this.usuario.setSelected(false);
            this.ventas.setSelected(false);
        }
    }//GEN-LAST:event_sedesMouseReleased

    private void ventasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasMouseReleased
        if(this.ventas.isSelected()){
            this.usuario.setSelected(false);
            this.sedes.setSelected(false);
        }
    }//GEN-LAST:event_ventasMouseReleased

    private void bConsulMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConsulMouseReleased
        //muestra las opciones (sede o usuarios)
        if(evt.isPopupTrigger()){
         users.show(evt.getComponent(),evt.getX(),evt.getY());
        }
        
        if(this.usuarios.isSelected()){
            cambiarVisibilidadCamposmodf(false);
            cambiarVisibilidadCampos(false);
        
            botonAceptar = 3;
            bAceptar.setText("Consultar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(false);
        
            comboxEmple.setSelectedIndex(0);
        }
        
        if(this.sede.isSelected()){
            cambiarVisibilidadCamposmodf(false);
            cambiarVisibilidadCampos(false);
            cambiarVisibilidadCamposSedeModf(false);
            
            botonAceptar = 3;
            bAceptar.setText("Consultar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(false);
        }
        
    }//GEN-LAST:event_bConsulMouseReleased

    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        actualizarComboxSedes();
        int index = comboxEmple.getSelectedIndex();
        if(botonAceptar==1){
            if (comboxCargo.getSelectedItem()== "Vendedor"){
             this.agregarVendedor();
             limpiarCamposUsuarios();
            }else{
                this.agregarJefeTaller();
                limpiarCamposUsuarios();
            }}else if(botonAceptar==2){
                actualizarComboxVendedoresYJefes();
                String id = listaIds[comboxEmple.getSelectedIndex()];
                Vendedor vendedor = bD.leerVendedorPorId(id);
                JefeTaller jefe = bD.leerJefeTallerPorId(id);
                /**hay que seleccionar el tipo de usuario*/
                if(vendedor != null){
                    modificarVendedor();
                }else {
                    if (jefe != null) {
                    modificarJefeTaller();
                    }
                }
            }else if(botonAceptar==3){
                actualizarComboxVendedoresYJefes();
                System.out.println("Entro al if");
                System.out.println(index);
                this.consultar(index);
            }else if(botonAceptar==4){
                actualizarComboxVendedoresYJefes();
                this.despedir(index);
            }else if (botonAceptar == 5){
                this.agregarSede();
            }else if (botonAceptar == 6){
                actualizarComboxSedes();
                this.modificarSede();
            }else if (botonAceptar == 7){
                actualizarComboxSedes();
                this.consultarSede(index);
            }
                
            //}else if (botonAceptar == 8){
                
           // }
    }  


    private void comboxEmpleItemStateChanged(java.awt.event.ItemEvent evt) {                                              
        // TODO add your handling code here:]
        Vendedor vendedor = null;
        JefeTaller jefe = null;
        Sede sede = null;
        if (botonAceptar==2 || botonAceptar==3 || botonAceptar==4){
            String id = listaIds[comboxEmple.getSelectedIndex()-1];
            vendedor = bD.leerVendedorPorId(id);
            jefe = bD.leerJefeTallerPorId(id);
        }else{
            String idSedes = listaIdsSede[comboxEmple.getSelectedIndex()-1];
            sede = bD.leerSedePorId(idSedes);
        }
        if(comboxEmple.getSelectedIndex() == 0){
            bAceptar.setEnabled(false);
            
            if(botonAceptar==2){ 
                //limpiarCamposUsuarios(); 
                habilitarCamposmodf(false);
                this.comboxCargo.setEnabled(false);
            }else if (botonAceptar==6){
                habilitarCamposmodf(false);
            }
        }else{
            bAceptar.setEnabled(true);
            if(vendedor != null){
                llenarCamposModfVendedor();
            }else if (jefe != null) {
                llenarCamposModfJefeTaller();
                }else if (sede != null){
                     
                }
            habilitarCamposmodf(true);
        }       
          
    }//GEN-LAST:event_bAceptarActionPerformed

    private void bAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAceptarMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_bAceptarMouseClicked

    private void comboxEmpleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxEmpleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxEmpleActionPerformed

    private void bAgregarUsrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAgregarUsrMouseClicked

        cambiarVisibilidadCamposmodf(false);
        cambiarVisibilidadCampos(true);
        tCedula.setEnabled(true);
        
        botonAceptar = 1;
        bAceptar.setText("Agregar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(true);
        
    }//GEN-LAST:event_bAgregarUsrMouseClicked

    private void bAgregarSedeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bAgregarSedeMouseClicked

        cambiarVisibilidadCamposmodf(false);
        cambiarVisibilidadCampos(true);
        cambiarVisibilidadCamposSede(false);
        
        botonAceptar = 5;
        bAceptar.setText("Agregar");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(true);

    }//GEN-LAST:event_bAgregarSedeMouseClicked

    private void bModfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bModfMouseClicked
          cambiarVisibilidadCampos(true);
            cambiarVisibilidadCamposmodf(true);
            actualizarComboxVendedoresYJefes();
            actualizarComboxSedes();
        
            botonAceptar = 2;
            bAceptar.setText("Modificar");
            bAceptar.setVisible(true);              
            bAceptar.setEnabled(false);
        
            comboxEmple.setSelectedIndex(0);
    }//GEN-LAST:event_bModfMouseClicked

    private void bModfSedeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bModfSedeMouseClicked
            cambiarVisibilidadCamposmodf(false);
            cambiarVisibilidadCampos(true);
            cambiarVisibilidadCamposSede(false);
            cambiarVisibilidadCamposSedeModf(false);
            this.actualizarComboxSedesMod();
        
            botonAceptar = 6;
            bAceptar.setText("Modificar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(false);
    }//GEN-LAST:event_bModfSedeMouseClicked

    private void bConsulMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConsulMouseClicked
            cambiarVisibilidadCamposmodf(false);
            cambiarVisibilidadCampos(false);
            this.actualizarComboxVendedoresYJefes();
        
            botonAceptar = 3;
            bAceptar.setText("Consultar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(false);
        
            comboxEmple.setSelectedIndex(0);
    }//GEN-LAST:event_bConsulMouseClicked

    private void bConsulSedeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bConsulSedeMouseClicked
            cambiarVisibilidadCamposmodf(false);
            cambiarVisibilidadCampos(false);
            cambiarVisibilidadCamposSedeModf(false);
            actualizarComboxSedesMod();
            
            botonAceptar = 7;
            bAceptar.setText("Consultar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(false);
    }//GEN-LAST:event_bConsulSedeMouseClicked

    private void bDespedirUsrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDespedirUsrMouseClicked
    cambiarVisibilidadCamposmodf(false);
        cambiarVisibilidadCampos(false);
        actualizarComboxVendedoresYJefes();
        
        botonAceptar = 4;
        bAceptar.setText("Despedir");
        bAceptar.setVisible(true);
        bAceptar.setEnabled(false);
        
       comboxEmple.setSelectedIndex(0);
    }//GEN-LAST:event_bDespedirUsrMouseClicked

    private void bDeshabilitarSedeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bDeshabilitarSedeMouseClicked
            cambiarVisibilidadCamposmodf(false);
            cambiarVisibilidadCampos(false);
            cambiarVisibilidadCamposSedeModf(false);
            actualizarComboxSedesMod();
            
            botonAceptar = 8;
            bAceptar.setText("Deshabilitar");
            bAceptar.setVisible(true);
            bAceptar.setEnabled(false);
    }//GEN-LAST:event_bDeshabilitarSedeMouseClicked

    private void bModfSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bModfSedeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bModfSedeActionPerformed

    private void tNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNombreKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(c != ' '){
            if(c<'A' || c>'Z'){
                if(c<'a' || c>'z'){
                    evt.consume();
                }
            }
        } 
    }//GEN-LAST:event_tNombreKeyTyped

    private void tCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCedulaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){            
            evt.consume();
        }
    }//GEN-LAST:event_tCedulaKeyTyped

    private void tCuentaBanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCuentaBanKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){            
            evt.consume();
        }
    }//GEN-LAST:event_tCuentaBanKeyTyped

    private void tTelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tTelKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){            
            evt.consume();
        }
    }//GEN-LAST:event_tTelKeyTyped

    private void tSalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tSalKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c<'0' || c>'9'){            
            evt.consume();
        }
    }//GEN-LAST:event_tSalKeyTyped

    private void tNombreUsuKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tNombreUsuKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c == '$'){            
            evt.consume();
        }
    }//GEN-LAST:event_tNombreUsuKeyTyped

    private void tCorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tCorreoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c == '$'){            
            evt.consume();
        }
    }//GEN-LAST:event_tCorreoKeyTyped

    private void tDirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tDirKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(c == '$'){            
            evt.consume();
        }
    }//GEN-LAST:event_tDirKeyTyped

    private void bAgregarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarSedeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bAgregarSedeActionPerformed

    

    
    
    public static void main(String args[]) {            
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 
               // new prinGerente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bAgregarSede;
    private javax.swing.JButton bAgregarUsr;
    private javax.swing.JButton bConsul;
    private javax.swing.JButton bConsulSede;
    private javax.swing.JButton bDeshabilitarSede;
    private javax.swing.JButton bDespedirUsr;
    private javax.swing.JButton bModf;
    private javax.swing.JButton bModfSede;
    private javax.swing.JComboBox<String> comboxAno;
    private javax.swing.JComboBox<String> comboxCargo;
    private javax.swing.JComboBox<String> comboxDia;
    private javax.swing.JComboBox<String> comboxEmple;
    private javax.swing.JComboBox<String> comboxGenero;
    private javax.swing.JComboBox<String> comboxMes;
    private javax.swing.JComboBox<String> comboxSedes;
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
    private javax.swing.JLabel labCargo;
    private javax.swing.JLabel labCedula;
    private javax.swing.JLabel labContra;
    private javax.swing.JLabel labCorreo;
    private javax.swing.JLabel labCuentaBan;
    private javax.swing.JLabel labDir;
    private javax.swing.JLabel labEmple;
    private javax.swing.JLabel labFechaNac;
    private javax.swing.JLabel labGenero;
    private javax.swing.JLabel labNombre;
    private javax.swing.JLabel labNombreUsu;
    private javax.swing.JLabel labSal;
    private javax.swing.JLabel labSede;
    private javax.swing.JLabel labTel;
    private javax.swing.JPopupMenu opReporte;
    private javax.swing.JCheckBoxMenuItem sede;
    private javax.swing.JCheckBoxMenuItem sedes;
    private javax.swing.JTextField tCedula;
    private javax.swing.JTextField tContra;
    private javax.swing.JTextField tCorreo;
    private javax.swing.JTextField tCuentaBan;
    private javax.swing.JTextField tDir;
    private javax.swing.JTextField tNombre;
    private javax.swing.JTextField tNombreUsu;
    private javax.swing.JTextField tSal;
    private javax.swing.JTextField tTel;
    private javax.swing.JPopupMenu users;
    private javax.swing.JCheckBoxMenuItem usuario;
    private javax.swing.JCheckBoxMenuItem usuarios;
    private javax.swing.JCheckBoxMenuItem ventas;
    // End of variables declaration//GEN-END:variables
}
