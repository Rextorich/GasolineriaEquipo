/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IntAutos.java
 *
 * Created on 09/04/2018, 11:27:09 AM
 */
package Interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Estudiante
 */
public class IntAutos extends javax.swing.JFrame {

    /** Creates new form IntAutos */
    DefaultTableModel modelo;
    public IntAutos() {
        initComponents();
        BloquearEtiquetas();
        BloquearBotonesIniciar();
        cargarComboColor();
        cargarComboMarca();
        jComboBox_Color.setSelectedIndex(-1);
        jComboBox_Marca.setSelectedIndex(-1);
        jComboBox_Modelo.setSelectedIndex(-1);
        jComboBox_Anio.setSelectedIndex(-1);
        jComboBox_Capacidad.setSelectedIndex(-1);
        CargarTablaAutos("");
    }

    public void limpiarTextos() {
        jFormattedTextField_Placa.setText("");
        jComboBox_Marca.setSelectedIndex(-1);
        jComboBox_Modelo.setSelectedIndex(-1);
        jComboBox_Color.setSelectedIndex(-1);
        jComboBox_Anio.setSelectedIndex(-1);
        jComboBox_Capacidad.setSelectedIndex(-1);
        jTextField_Observacion.setText("");
    }

    public void mayusculas(java.awt.event.KeyEvent evt) {
        char ch = evt.getKeyChar();
        if (Character.isDigit(ch)) {
            evt.consume();
        } else {
            evt.setKeyChar(Character.toUpperCase(ch));
        }
    }

    public void BloquearEtiquetas() {
        jFormattedTextField_Placa.setEnabled(false);
        jComboBox_Marca.setEnabled(false);
        jComboBox_Modelo.setEnabled(false);
        jComboBox_Color.setEnabled(false);
        jComboBox_Anio.setEnabled(false);
        jComboBox_Capacidad.setEnabled(false);
        jTextField_Observacion.setEnabled(false);
    }

    public void BloquearBotonesIniciar() {
        jButton_Nuevo.setEnabled(true);
        jButton_Guardar.setEnabled(false);
        jButton_Actualizar.setEnabled(false);
        jButton_Borrar.setEnabled(false);
        jButton_Cancelar.setEnabled(false);
        jButton_Salir.setEnabled(true);
    }

    public void DesBloquearEtiquetas() {
        jFormattedTextField_Placa.setEnabled(true);
        jComboBox_Marca.setEnabled(true);
        jComboBox_Modelo.setEnabled(true);
        jComboBox_Color.setEnabled(true);
        jComboBox_Anio.setEnabled(true);
        jComboBox_Capacidad.setEnabled(true);
        jTextField_Observacion.setEnabled(true);
    }

    public void DesBloquearBotonesNuevo() {
        jButton_Nuevo.setEnabled(false);
        jButton_Guardar.setEnabled(true);
        jButton_Actualizar.setEnabled(false);
        jButton_Borrar.setEnabled(false);
        jButton_Cancelar.setEnabled(true);
        jButton_Salir.setEnabled(true);
    }

    /*public void claveDuplicada() {

        /*
         * Algoritmo Select
        1.- Conectar BD
        2.- Realizar sentencia SQL Insert
        3.- Preparar sentencia SQL Insert
        4.- Manejar el resultado
        5.- Realizar Select(Statement)
         
        String placa = jFormattedTextField_Placa.getText();
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "select count(*) as contar from autos WHERE AUT_PLACA = " + placa + "";

        try {
            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql);

            while (rs.next()) {//mientras el resulset tenga un siguiente o me verifique que tenga una fila

                int contar1 = rs.getInt("contar");
                if (contar1 > 0) {
                    JOptionPane.showMessageDialog(null, "auto ya esiste");

                } else {
                    Guardar();
                }
            }
        } catch (SQLException e) {
            //Logger.getLogger(IntAutos.class.getName()).log(Level.SEVERE, null, e);
            JOptionPane.showMessageDialog(null, "Eeeee");
            //JOptionPane.showMessageDialog(null, "auto ya esiste");
        }
    }*/
    
    String validCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";
    public void setValidCharacters ( String validCharacters ) { 
         this.validCharacters   =  validCharacters ;
    }
    public String getValidCharacters() {
         return  validCharacters ;
    }

   private void Guardar() {
       
        /*
         * Algoritmo Guardar
        1.- conectar BD
        2.- Coger valores a guardar
        3.- Realizar sentencia SQL Insert
        4.- Preparar la sentencia SQL(Prepared Statement)
        5.- Insertar el Dato
         */

        if (jFormattedTextField_Placa.getText().equals(getValidCharacters())){
            JOptionPane.showMessageDialog(rootPane, "Ingrese correctamente la Placa");
        } else if (jComboBox_Color.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese el Color");
        } else if (jComboBox_Marca.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese la Marca");
        } else if (jComboBox_Modelo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese el Modelo");
        } else if (jComboBox_Anio.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese el Año");
        } else if (jComboBox_Capacidad.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese la Capacidad");
        } else if (jTextField_Observacion.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Ingrese la Observacion");
        } else {

            Conexion cc = new Conexion();
            Connection cn = cc.conectar();
            String AUT_PLACA, COL_CODIGO, MOD_CODIGO, AUT_ANIO, AUT_OBSERVACION;
            Integer AUT_CAPACIDAD;
            AUT_PLACA = jFormattedTextField_Placa.getText();
            COL_CODIGO = String.valueOf(jComboBox_Color.getSelectedItem()).substring(0, 2);
            MOD_CODIGO = String.valueOf(jComboBox_Modelo.getSelectedItem()).substring(0, 2);
            AUT_ANIO = String.valueOf(jComboBox_Anio.getSelectedItem());
            AUT_OBSERVACION = jTextField_Observacion.getText();
            AUT_CAPACIDAD = Integer.valueOf(jComboBox_Capacidad.getSelectedItem().toString());
            String sql = "";
            sql = "insert into autos(AUT_PLACA, MOD_CODIGO, COL_CODIGO, AUT_ANIO, AUT_CAPACIDAD, AUT_OBSERVACION) "
                    + "values(?,?,?,?,?,?)";
            try {
                PreparedStatement psd = cn.prepareStatement(sql);
                psd.setString(1, AUT_PLACA);
                psd.setString(3, COL_CODIGO);
                psd.setString(2, MOD_CODIGO);
                psd.setString(4, AUT_ANIO);
                psd.setInt(5, AUT_CAPACIDAD);
                psd.setString(6, AUT_OBSERVACION);
                //JOptionPane.showMessageDialog(null, sql);
                int n = psd.executeUpdate();
                
                if (n > 0) {

                    JOptionPane.showMessageDialog(null, "Se inserto correctamente");
                    limpiarTextos();
                    BloquearEtiquetas();
                    BloquearBotonesIniciar();
                    CargarTablaAutos("");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
            }

        }

    }

    public void cargarComboColor() {
        /*
         * Algoritmo Select
        1.- Conectar BD
        2.- Realizar sentencia SQL Insert
        3.- Preparar sentencia SQL Insert
        4.- Manejar el resultado
        5.- Realizar Select(Statement)
         */
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "select * from color";
        try {
            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql); // navega una fila por una fila cogiendo campo por campo
            while (rs.next()) {//mientras el resulset tenga un siguiente o me verifique que tenga una fila
                String id = rs.getString("COL_CODIGO");
                String nombreColor = rs.getString("COL_NOMBRE");
                jComboBox_Color.addItem(id + " " + nombreColor);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void cargarComboMarca() {
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "select * from marca";
        try {
            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql); // navega una fila por una fila cogiendo campo por campo
            while (rs.next()) {//mientras el resulset tenga un siguiente o me verifique que tenga una fila
                String id = rs.getString("MAR_CODIGO");
                String nombreMarca = rs.getString("MAR_NOMBRE");
                jComboBox_Marca.addItem(nombreMarca);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void cargarComboModelo() {
        int valorCom = jComboBox_Marca.getSelectedIndex() + 1;
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql = "";
        sql = "select * from modelo WHERE MAR_CODIGO = " + valorCom + "";
        try {
            Statement psd = cn.createStatement();
            ResultSet rs = psd.executeQuery(sql); // navega una fila por una fila cogiendo campo por campo
            while (rs.next()) {//mientras el resulset tenga un siguiente o me verifique que tenga una fila
                String id = rs.getString("MOD_CODIGO");
                //String nombreMarca = rs.getString("MAR_CODIGO");
                String nombreModelo = rs.getString("MOD_NOMBRE");
                jComboBox_Modelo.addItem(id + " " + nombreModelo);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void limpiarModelo() {
        jComboBox_Modelo.removeAllItems();
    }
    
    public void CargarTablaAutos(String dato){
        String [] titulos = {"PLACA", "MODELO", "COLOR", "AÑO", "CAPACIDAD", "OBSERVACION"};
        //creamos una variable global llamada model
        //el default model tiene dos parametros ahora ingresamos titulos como cñolumnas
        modelo = new DefaultTableModel(null, titulos);  
        String [] registros = new String[6];
        
        //Conectarse a la base de datos
        Conexion cc = new Conexion();
        Connection cn = cc.conectar();
        String sql="";
        //hacer una consulta en la bd para sacar la informacion de las tablas
        sql="select autos.AUT_PLACA,color.COL_NOMBRE,modelo.MOD_NOMBRE,autos.AUT_ANIO,autos.AUT_CAPACIDAD,autos.AUT_OBSERVACION from autos, color, modelo where autos.AUT_PLACA LIKE '%"+dato+"%' AND autos.COL_CODIGO = color.COL_CODIGO AND autos.MOD_CODIGO = modelo.MOD_CODIGO";
        try {
            //preparar la sentencia sql
            Statement psd = cn.createStatement();
            //Manejar el resultado
            ResultSet rs = psd.executeQuery(sql);
             while (rs.next()) {//mientras el resulset tenga un siguiente o me verifique que tenga una fila
                //Obtenemos la informacion para guardarla en los rgistros de la tabla
                 registros[0]=rs.getString("autos.AUT_PLACA");
                 registros[1]=rs.getString("color.COL_NOMBRE");
                 registros[2]=rs.getString("modelo.MOD_NOMBRE");
                 registros[3]=rs.getString("autos.AUT_ANIO");
                 registros[4]=rs.getString("autos.AUT_CAPACIDAD");
                 registros[5]=rs.getString("autos.AUT_OBSERVACION");
                 //Agregando al modelo las filas
                 modelo.addRow(registros);                 
            }
             //mostrando datos en el modelo
                 jTable_Autos.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
              
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox_Anio = new javax.swing.JComboBox();
        jComboBox_Capacidad = new javax.swing.JComboBox();
        jTextField_Observacion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox_Color = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_Marca = new javax.swing.JComboBox();
        jComboBox_Modelo = new javax.swing.JComboBox();
        jFormattedTextField_Placa = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton_Nuevo = new javax.swing.JButton();
        jButton_Guardar = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jButton_Borrar = new javax.swing.JButton();
        jButton_Cancelar = new javax.swing.JButton();
        jButton_Salir = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Autos = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jTextField_BuscarPlaca = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("PLACA :");

        jLabel2.setText("AÑO :");

        jLabel3.setText("CAPACIDAD :");

        jLabel4.setText("OBSERVACIÓN :");

        jComboBox_Anio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018" }));
        jComboBox_Anio.setSelectedIndex(-1);

        jComboBox_Capacidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2", "4", "6", "8", "12", "14" }));
        jComboBox_Capacidad.setSelectedIndex(-1);

        jTextField_Observacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField_ObservacionKeyTyped(evt);
            }
        });

        jLabel6.setText("COLOR :");

        jLabel7.setText("MODELO :");

        jLabel8.setText("MARCA :");

        jComboBox_Marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_MarcaActionPerformed(evt);
            }
        });

        try {
            jFormattedTextField_Placa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("UUU-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextField_Placa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextField_PlacaFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jComboBox_Capacidad, javax.swing.GroupLayout.Alignment.LEADING, 0, 81, Short.MAX_VALUE)
                        .addComponent(jComboBox_Anio, javax.swing.GroupLayout.Alignment.LEADING, 0, 87, Short.MAX_VALUE)
                        .addComponent(jComboBox_Modelo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField_Observacion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox_Marca, javax.swing.GroupLayout.Alignment.LEADING, 0, 87, Short.MAX_VALUE)
                        .addComponent(jComboBox_Color, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jFormattedTextField_Placa, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(185, 185, 185))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox_Anio, jComboBox_Capacidad, jComboBox_Color, jComboBox_Marca, jComboBox_Modelo});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jFormattedTextField_Placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Anio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox_Capacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_Observacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox_Anio, jComboBox_Capacidad, jComboBox_Color, jComboBox_Marca, jComboBox_Modelo, jTextField_Observacion});

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton_Nuevo.setText("Nuevo");
        jButton_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NuevoActionPerformed(evt);
            }
        });

        jButton_Guardar.setText("Guardar");
        jButton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarActionPerformed(evt);
            }
        });

        jButton_Actualizar.setText("Actualizar");
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });

        jButton_Borrar.setText("Borrar");

        jButton_Cancelar.setText("Cancelar");

        jButton_Salir.setText("Salir");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_Salir)
                    .addComponent(jButton_Cancelar)
                    .addComponent(jButton_Borrar)
                    .addComponent(jButton_Actualizar)
                    .addComponent(jButton_Guardar)
                    .addComponent(jButton_Nuevo))
                .addGap(38, 38, 38))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton_Actualizar, jButton_Borrar, jButton_Cancelar, jButton_Guardar, jButton_Nuevo, jButton_Salir});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jButton_Nuevo)
                .addGap(18, 18, 18)
                .addComponent(jButton_Guardar)
                .addGap(18, 18, 18)
                .addComponent(jButton_Actualizar)
                .addGap(18, 18, 18)
                .addComponent(jButton_Borrar)
                .addGap(18, 18, 18)
                .addComponent(jButton_Cancelar)
                .addGap(18, 18, 18)
                .addComponent(jButton_Salir)
                .addGap(19, 19, 19))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable_Autos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable_Autos);

        jLabel5.setText("Buscar Por Placa:");

        jTextField_BuscarPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField_BuscarPlacaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField_BuscarPlacaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField_BuscarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField_BuscarPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NuevoActionPerformed
// TODO add your handling code here:
    DesBloquearEtiquetas();
    DesBloquearBotonesNuevo();
}//GEN-LAST:event_jButton_NuevoActionPerformed

private void jButton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarActionPerformed
// TODO add your handling code here:
    Guardar();
    CargarTablaAutos(jTextField_BuscarPlaca.getText());
}//GEN-LAST:event_jButton_GuardarActionPerformed

private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jButton_ActualizarActionPerformed

private void jComboBox_MarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_MarcaActionPerformed
// TODO add your handling code here:
    limpiarModelo();
    cargarComboModelo();

}//GEN-LAST:event_jComboBox_MarcaActionPerformed

private void jTextField_ObservacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_ObservacionKeyTyped
// TODO add your handling code here:
    mayusculas(evt);
}//GEN-LAST:event_jTextField_ObservacionKeyTyped

private void jFormattedTextField_PlacaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextField_PlacaFocusLost
// TODO add your handling code here:
//    claveDuplicada();
}//GEN-LAST:event_jFormattedTextField_PlacaFocusLost

private void jTextField_BuscarPlacaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarPlacaKeyPressed
// TODO add your handling code here:
    
}//GEN-LAST:event_jTextField_BuscarPlacaKeyPressed

private void jTextField_BuscarPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField_BuscarPlacaKeyReleased
// TODO add your handling code here:
    CargarTablaAutos(jTextField_BuscarPlaca.getText());
}//GEN-LAST:event_jTextField_BuscarPlacaKeyReleased

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
            java.util.logging.Logger.getLogger(IntAutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IntAutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IntAutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IntAutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new IntAutos().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Borrar;
    private javax.swing.JButton jButton_Cancelar;
    private javax.swing.JButton jButton_Guardar;
    private javax.swing.JButton jButton_Nuevo;
    private javax.swing.JButton jButton_Salir;
    private javax.swing.JComboBox jComboBox_Anio;
    private javax.swing.JComboBox jComboBox_Capacidad;
    private javax.swing.JComboBox jComboBox_Color;
    private javax.swing.JComboBox jComboBox_Marca;
    private javax.swing.JComboBox jComboBox_Modelo;
    private javax.swing.JFormattedTextField jFormattedTextField_Placa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Autos;
    private javax.swing.JTextField jTextField_BuscarPlaca;
    private javax.swing.JTextField jTextField_Observacion;
    // End of variables declaration//GEN-END:variables
}
