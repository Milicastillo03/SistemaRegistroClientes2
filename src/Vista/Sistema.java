package Vista;

import Modelo.Cliente;
import Modelo.ClienteDao;
import Modelo.Cobro;
import Modelo.CobroDao;
import Modelo.Configuracion;
import Modelo.Detalle;
import Modelo.Eventos;
import Modelo.Maquinas;
import Modelo.MaquinasDao;
import Modelo.Reparacion;
import Modelo.ReparacionDao;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Sistema extends javax.swing.JFrame {

    Cliente cl = new Cliente();
    ClienteDao client = new ClienteDao();
    Maquinas maq = new Maquinas();
    MaquinasDao MaqDao = new MaquinasDao();
    Reparacion rep = new Reparacion();
    ReparacionDao RepDao = new ReparacionDao();
    Cobro c = new Cobro();
    CobroDao Cdao = new CobroDao();
    Detalle Dc = new Detalle();
    Configuracion conf = new Configuracion();
    Eventos event = new Eventos();
    DefaultTableModel modelo = new DefaultTableModel();
    
    DefaultTableModel tmp = new DefaultTableModel();
    int item;
    double Total = 0.00;
    

    public Sistema() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtIdreparacion.setVisible(false);
        txtIdCliente.setVisible(false);
        txtIdMaquinas.setVisible(false);
        txtIdReparacion.setVisible(false);
        txtIdCobro.setVisible(false);
        txtIdConfiguracion.setVisible(false);
        ListarConfiguracion();

    }

    private void LimpiarCliente() {
        txtIdCliente.setText("");
        txtDniCliente.setText("");
        txtNombreCliente.setText("");
        txtApellidoCliente.setText("");
        txtTelefonoCliente.setText("");

    }

    public void ListarCliente() {
        List<Cliente> ListarCl = client.ListarCliente();
        modelo = (DefaultTableModel) tableCliente.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < ListarCl.size(); i++) {

            ob[0] = ListarCl.get(i).getId();
            ob[1] = ListarCl.get(i).getDni();
            ob[2] = ListarCl.get(i).getNombre();
            ob[3] = ListarCl.get(i).getApellido();
            ob[4] = ListarCl.get(i).getTelefono();

            modelo.addRow(ob);

        }

        tableCliente.setModel(modelo);
    }

    private void LimpiarMaquinas() {
        txtClientes.setText("");
        txtIdMaquinas.setText("");
        txtNumSerieMaquina.setText("");
        txtMarcaMaquina.setText("");
        txtModeloMaquina.setText("");
        txtTipoMaquina.setText("");

    }
    
    public void ListarMaquinas() {
        List<Maquinas> ListarMaq = MaqDao.ListarMaquinas();
        modelo = (DefaultTableModel) tableMaquina.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < ListarMaq.size(); i++) {
            
            ob[0] = ListarMaq.get(i).getClientes();
            ob[1] = ListarMaq.get(i).getId_Maquinas();
            ob[2] = ListarMaq.get(i).getNumero_de_serie();
            ob[3] = ListarMaq.get(i).getMarca();
            ob[4] = ListarMaq.get(i).getModelo();
            ob[5] = ListarMaq.get(i).getTipo();

            modelo.addRow(ob);

        }

        tableMaquina.setModel(modelo);
    }
    
    public void ListarReparacion() {
        List<Reparacion> ListarRep = RepDao.ListarReparacion();
        modelo = (DefaultTableModel) tableReparacion.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < ListarRep.size(); i++) {
            
            ob[0] = ListarRep.get(i).getMaquinas();
            ob[1] = ListarRep.get(i).getId_Reparacion();
            ob[2] = ListarRep.get(i).getCodigo();
            ob[3] = ListarRep.get(i).getEstado();
            ob[4] = ListarRep.get(i).getDescripcion();
            ob[5] = ListarRep.get(i).getPrecio();

            modelo.addRow(ob);

        }

        tableReparacion.setModel(modelo);
    }
    
    public void ListarConfiguracion() {
        
       conf = RepDao.BuscarDatos();
        txtIdConfiguracion.setText(""+conf.getId_Configuracion());
        txtNombreConfiguracion.setText(""+conf.getNombre());
        txtDescripcionConfiguracion.setText(""+conf.getDescripcion());
        txtTelefonoConfiguracion.setText(""+conf.getTelefono());
        txtDireccionConfiguracion.setText(""+conf.getDireccion()); 
        
    }
    
    public void ListarCobro() {
        List<Cobro> ListarCobro = Cdao.ListarCobro();
        modelo = (DefaultTableModel) tableCobro.getModel();
        Object[] ob = new Object[6];

        for (int i = 0; i < ListarCobro.size(); i++) {
            
            ob[0] = ListarCobro.get(i).getId_Cobro();
            ob[1] = ListarCobro.get(i).getClientes();
            ob[2] = ListarCobro.get(i).getMaquinas();
            ob[3] = ListarCobro.get(i).getReparacion();
            ob[4] = ListarCobro.get(i).getTotal();
            
            modelo.addRow(ob);

        }

        tableCobro.setModel(modelo);
    }
    
    private void LimpiarReparacion() {
        txtMaquinas.setText("");
        txtIdReparacion.setText("");
        txtCodigoReparacion.setText("");
        cbxEstado.setSelectedItem(null);
        txtDescripcionReparacion.setText("");
        txtPrecioReparacion.setText("");

    }

    private void TotalPagar() {
        Total = 0.00;
        int numFila = tableNCobro.getRowCount();
        for (int i = 0; i < numFila; i++) {
           double cal = Double.parseDouble(String.valueOf(tableNCobro.getModel().getValueAt(i, 4)));
           Total = Total + cal; 
        }
        
        LabelTotal.setText(String.format("%.2f", Total));
        
    }
    
    private void LimpiarCobro() {
        txtCodigoNCobro.setText("");
        txtDescripcionCobro.setText("");
        txtCantidadCobro.setText("");
        txtPrecioCobro.setText("");
        txtIdreparacion.setText("");
        
    }
    
    private void RegistrarCobro() {
        String clientes = txtNombreClienteNCobro.getText();
        String maquinas = LabelMaquinas.getText();
        String reparacion = LabelReparacion.getText();
        double monto = Total;
        c.setClientes(clientes);
        c.setMaquinas(maquinas);
        c.setReparacion(reparacion);
        c.setTotal(monto);
        Cdao.RegistrarCobro(c);
        
    }
    
    private void RegistrarDetalle() {
        int id = Cdao.IdCobro();
        for (int i = 0; i < tableNCobro.getRowCount(); i++) {
            String cod = tableNCobro.getValueAt(i, 0).toString();
            String cant = tableNCobro.getValueAt(i, 2).toString();
            double precio = Double.parseDouble(tableNCobro.getValueAt(i, 3).toString());
            
            Dc.setCodigo_Reparacion(cod);
            Dc.setCantidad(cant);
            Dc.setPrecio(precio);
            Dc.setId_Cobros(id);
            Cdao.RegistrarDetalle(Dc);
                    
                    
        }
        
        
    }
    
    public void LimpiarTable() {
        for (int i = 0; i < modelo.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnNuevoCobro = new javax.swing.JButton();
        btnMaquinas = new javax.swing.JButton();
        btnReparacion = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnConfiguracion = new javax.swing.JButton();
        btnClientes = new javax.swing.JButton();
        btnCobro = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableNCobro = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnEliminarCobro = new javax.swing.JButton();
        txtCodigoNCobro = new javax.swing.JTextField();
        txtDescripcionCobro = new javax.swing.JTextField();
        txtCantidadCobro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioCobro = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDniNCobro = new javax.swing.JTextField();
        txtNombreClienteNCobro = new javax.swing.JTextField();
        btnGenerarNuevoCobro = new javax.swing.JButton();
        Totalpagar = new javax.swing.JLabel();
        LabelTotal = new javax.swing.JLabel();
        txtTelefonoNC = new javax.swing.JTextField();
        txtIdreparacion = new javax.swing.JTextField();
        LabelMaquinas = new javax.swing.JLabel();
        LabelReparacion = new javax.swing.JLabel();
        txtApellidoNC = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtDniCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtApellidoCliente = new javax.swing.JTextField();
        txtTelefonoCliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCliente = new javax.swing.JTable();
        btnGuardarCliente = new javax.swing.JButton();
        btnActualizarCliente = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnNuevoCliente = new javax.swing.JButton();
        txtIdCliente = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtNumSerieMaquina = new javax.swing.JTextField();
        txtMarcaMaquina = new javax.swing.JTextField();
        txtModeloMaquina = new javax.swing.JTextField();
        txtTipoMaquina = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableMaquina = new javax.swing.JTable();
        btnGuardarMaquina = new javax.swing.JButton();
        btnEliminarMaquina = new javax.swing.JButton();
        btnActualizarMaquina = new javax.swing.JButton();
        btnNuevaMaquina = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        txtClientes = new javax.swing.JTextField();
        txtIdMaquinas = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtCodigoReparacion = new javax.swing.JTextField();
        txtDescripcionReparacion = new javax.swing.JTextField();
        txtPrecioReparacion = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableReparacion = new javax.swing.JTable();
        btnGuardarReparacion = new javax.swing.JButton();
        btnEliminarReparacion = new javax.swing.JButton();
        btnNuevaReparacion = new javax.swing.JButton();
        btnActualizarReparacion = new javax.swing.JButton();
        txtIdReparacion = new javax.swing.JTextField();
        cbxEstado = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtMaquinas = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableCobro = new javax.swing.JTable();
        txtIdCobro = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtDireccionConfiguracion = new javax.swing.JTextField();
        txtNombreConfiguracion = new javax.swing.JTextField();
        txtDescripcionConfiguracion = new javax.swing.JTextField();
        txtTelefonoConfiguracion = new javax.swing.JTextField();
        btnActualizarConfiguracion = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txtIdConfiguracion = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        btnNuevoCobro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnNuevoCobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nventa.png"))); // NOI18N
        btnNuevoCobro.setText("Nuevo Cobro");
        btnNuevoCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoCobroActionPerformed(evt);
            }
        });

        btnMaquinas.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnMaquinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/6311a4d1128c4d0b57a8f3e6b5f7c85e-removebg-preview.jpg"))); // NOI18N
        btnMaquinas.setText("Maquinas");
        btnMaquinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaquinasActionPerformed(evt);
            }
        });

        btnReparacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnReparacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/compras.png"))); // NOI18N
        btnReparacion.setText("Reparacion");
        btnReparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReparacionActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Bobinados Castillo.jpg"))); // NOI18N

        btnConfiguracion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/config.png"))); // NOI18N
        btnConfiguracion.setText("Configuración");
        btnConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracionActionPerformed(evt);
            }
        });

        btnClientes.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Clientes.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });

        btnCobro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnCobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cobro.jpg"))); // NOI18N
        btnCobro.setText("Cobro");
        btnCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNuevoCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMaquinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReparacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCobro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevoCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 610));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 1060, 130));

        tableNCobro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PRECIO U.", "PRECIO TOTAL"
            }
        ));
        jScrollPane1.setViewportView(tableNCobro);
        if (tableNCobro.getColumnModel().getColumnCount() > 0) {
            tableNCobro.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableNCobro.getColumnModel().getColumn(1).setPreferredWidth(50);
            tableNCobro.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableNCobro.getColumnModel().getColumn(3).setPreferredWidth(40);
            tableNCobro.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setText("Codigo");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setText("Descripcion");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("Cantidad");

        btnEliminarCobro.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminarCobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btnEliminarCobro.setText("ELIMINAR");
        btnEliminarCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCobroActionPerformed(evt);
            }
        });

        txtCodigoNCobro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoNCobroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoNCobroKeyTyped(evt);
            }
        });

        txtDescripcionCobro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionCobroKeyTyped(evt);
            }
        });

        txtCantidadCobro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantidadCobroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadCobroKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setText("Precio");

        txtPrecioCobro.setEditable(false);
        txtPrecioCobro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCobroKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setText("DNI");

        jLabel10.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel10.setText("NOMBRE");

        txtDniNCobro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDniNCobroKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniNCobroKeyTyped(evt);
            }
        });

        txtNombreClienteNCobro.setEditable(false);
        txtNombreClienteNCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreClienteNCobroActionPerformed(evt);
            }
        });
        txtNombreClienteNCobro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteNCobroKeyTyped(evt);
            }
        });

        btnGenerarNuevoCobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/print.png"))); // NOI18N
        btnGenerarNuevoCobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarNuevoCobroActionPerformed(evt);
            }
        });

        Totalpagar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        Totalpagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/money.png"))); // NOI18N
        Totalpagar.setText("TOTAL A PAGAR:");

        LabelTotal.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        LabelTotal.setText("-----");

        txtIdreparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdreparacionActionPerformed(evt);
            }
        });

        LabelMaquinas.setText("Maquina Informacion");

        LabelReparacion.setText("Reparacion Informacion");

        txtApellidoNC.setEditable(false);
        txtApellidoNC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoNCKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("APELLIDO");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(txtCodigoNCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(txtDescripcionCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(114, 114, 114)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtCantidadCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtPrecioCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(111, 111, 111)
                        .addComponent(jLabel7)
                        .addGap(100, 100, 100)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEliminarCobro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(txtDniNCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(txtNombreClienteNCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtApellidoNC, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(txtTelefonoNC, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(126, 126, 126)
                        .addComponent(jLabel10)
                        .addGap(92, 92, 92)
                        .addComponent(jLabel6)
                        .addGap(106, 106, 106)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelMaquinas)
                    .addComponent(LabelReparacion))
                .addGap(28, 28, 28)
                .addComponent(btnGenerarNuevoCobro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(Totalpagar)
                .addGap(39, 39, 39)
                .addComponent(LabelTotal)
                .addGap(74, 74, 74))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(btnEliminarCobro)
                    .addComponent(txtIdreparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoNCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcionCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidadCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Totalpagar)
                            .addComponent(LabelTotal)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnGenerarNuevoCobro))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel9)
                                .addComponent(jLabel6))
                            .addComponent(LabelMaquinas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDniNCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombreClienteNCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelReparacion)
                            .addComponent(txtApellidoNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab1", jPanel6);

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel13.setText("DNI:");

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setText("NOMBRE:");

        jLabel15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel15.setText("APELLIDO:");

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel16.setText("TELEFONO:");

        txtDniCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniClienteKeyTyped(evt);
            }
        });

        txtNombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreClienteKeyTyped(evt);
            }
        });

        txtApellidoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoClienteKeyTyped(evt);
            }
        });

        txtTelefonoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoClienteKeyTyped(evt);
            }
        });

        tableCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "DNI", "NOMBRE", "APELLIDO", "TELEFONO"
            }
        ));
        tableCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableCliente);
        if (tableCliente.getColumnModel().getColumnCount() > 0) {
            tableCliente.getColumnModel().getColumn(0).setPreferredWidth(50);
            tableCliente.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableCliente.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableCliente.getColumnModel().getColumn(3).setPreferredWidth(30);
            tableCliente.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        btnGuardarCliente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        btnGuardarCliente.setText("GUARDAR");
        btnGuardarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnActualizarCliente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnActualizarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar (2).png"))); // NOI18N
        btnActualizarCliente.setText("ACTUALIZAR");
        btnActualizarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarClienteActionPerformed(evt);
            }
        });

        btnEliminarCliente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btnEliminarCliente.setText("ELIMINAR");
        btnEliminarCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnNuevoCliente.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnNuevoCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        btnNuevoCliente.setText("NUEVO");
        btnNuevoCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel13))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtApellidoCliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                                .addComponent(txtNombreCliente, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(btnEliminarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnActualizarCliente))))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtDniCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtTelefonoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(77, 77, 77)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarCliente)
                            .addComponent(btnActualizarCliente))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevoCliente)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab2", jPanel2);

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel18.setText("NUMERO DE SERIE:");

        jLabel19.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel19.setText("MARCA:");

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel20.setText("MODELO:");

        jLabel21.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel21.setText("TIPO:");

        txtNumSerieMaquina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNumSerieMaquina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumSerieMaquinaKeyTyped(evt);
            }
        });

        txtMarcaMaquina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtMarcaMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaMaquinaActionPerformed(evt);
            }
        });
        txtMarcaMaquina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMarcaMaquinaKeyTyped(evt);
            }
        });

        txtModeloMaquina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtModeloMaquina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtModeloMaquinaKeyTyped(evt);
            }
        });

        txtTipoMaquina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtTipoMaquina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTipoMaquinaKeyTyped(evt);
            }
        });

        tableMaquina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLIENTES", "ID", "NUMERO DE SERIE", "MARCA", "MODELO", "TIPO"
            }
        ));
        tableMaquina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMaquinaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableMaquina);
        if (tableMaquina.getColumnModel().getColumnCount() > 0) {
            tableMaquina.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableMaquina.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableMaquina.getColumnModel().getColumn(2).setPreferredWidth(30);
            tableMaquina.getColumnModel().getColumn(3).setPreferredWidth(50);
            tableMaquina.getColumnModel().getColumn(4).setPreferredWidth(40);
            tableMaquina.getColumnModel().getColumn(5).setPreferredWidth(50);
        }

        btnGuardarMaquina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardarMaquina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        btnGuardarMaquina.setText("GUARDAR");
        btnGuardarMaquina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarMaquinaActionPerformed(evt);
            }
        });

        btnEliminarMaquina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminarMaquina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btnEliminarMaquina.setText("ELIMINAR");
        btnEliminarMaquina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarMaquinaActionPerformed(evt);
            }
        });

        btnActualizarMaquina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnActualizarMaquina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar (2).png"))); // NOI18N
        btnActualizarMaquina.setText("ACTUALIZAR");
        btnActualizarMaquina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarMaquinaActionPerformed(evt);
            }
        });

        btnNuevaMaquina.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnNuevaMaquina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        btnNuevaMaquina.setText("NUEVO");
        btnNuevaMaquina.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaMaquinaActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel23.setText("CLIENTE:");

        txtClientes.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtClientesKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnEliminarMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevaMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumSerieMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMarcaMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(txtTipoMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                            .addComponent(txtModeloMaquina)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(btnGuardarMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizarMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 727, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumSerieMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtMarcaMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtModeloMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtTipoMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardarMaquina)
                            .addComponent(btnActualizarMaquina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevaMaquina))
                        .addGap(56, 56, 56))))
        );

        jTabbedPane2.addTab("tab3", jPanel7);

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel22.setText("CODIGO:");

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel24.setText("DESCRIPCION:");

        jLabel25.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel25.setText("PRECIO:");

        txtCodigoReparacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtCodigoReparacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoReparacionKeyTyped(evt);
            }
        });

        txtDescripcionReparacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtDescripcionReparacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionReparacionKeyTyped(evt);
            }
        });

        txtPrecioReparacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtPrecioReparacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioReparacionKeyTyped(evt);
            }
        });

        tableReparacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MAQUINA", "ID REPARACION", "CODIGO", "ESTADO", "DESCRIPCION", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableReparacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableReparacionMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableReparacion);
        if (tableReparacion.getColumnModel().getColumnCount() > 0) {
            tableReparacion.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableReparacion.getColumnModel().getColumn(1).setPreferredWidth(30);
            tableReparacion.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableReparacion.getColumnModel().getColumn(3).setPreferredWidth(30);
            tableReparacion.getColumnModel().getColumn(4).setPreferredWidth(30);
        }

        btnGuardarReparacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnGuardarReparacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GuardarTodo.png"))); // NOI18N
        btnGuardarReparacion.setText("GUARDAR");
        btnGuardarReparacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardarReparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarReparacionActionPerformed(evt);
            }
        });

        btnEliminarReparacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnEliminarReparacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar.png"))); // NOI18N
        btnEliminarReparacion.setText("ELIMINAR");
        btnEliminarReparacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminarReparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarReparacionActionPerformed(evt);
            }
        });

        btnNuevaReparacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnNuevaReparacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        btnNuevaReparacion.setText("NUEVO");
        btnNuevaReparacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevaReparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaReparacionActionPerformed(evt);
            }
        });

        btnActualizarReparacion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnActualizarReparacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar (2).png"))); // NOI18N
        btnActualizarReparacion.setText("ACTUALIZAR");
        btnActualizarReparacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizarReparacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarReparacionActionPerformed(evt);
            }
        });

        cbxEstado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "REPARADO", "NO REPARADO" }));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("ESTADO:");

        jLabel17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel17.setText("MAQUINA:");

        txtMaquinas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMaquinasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(268, 268, 268)
                        .addComponent(txtIdReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarReparacion, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(btnEliminarReparacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnActualizarReparacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNuevaReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel25)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrecioReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(46, 46, 46))
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel22)
                                        .addComponent(jLabel17))
                                    .addGap(36, 36, 36))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtCodigoReparacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbxEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMaquinas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDescripcionReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(txtCodigoReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescripcionReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtPrecioReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnGuardarReparacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnActualizarReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEliminarReparacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevaReparacion))))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab4", jPanel8);

        tableCobro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLIENTE", "MAQUINA", "REPARACION", "TOTAL"
            }
        ));
        jScrollPane5.setViewportView(tableCobro);
        if (tableCobro.getColumnModel().getColumnCount() > 0) {
            tableCobro.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableCobro.getColumnModel().getColumn(1).setPreferredWidth(60);
            tableCobro.getColumnModel().getColumn(2).setPreferredWidth(50);
            tableCobro.getColumnModel().getColumn(3).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIdCobro, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 978, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdCobro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab5", jPanel9);

        jLabel27.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel27.setText("DIRECCIÓN");

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel28.setText("NOMBRE");

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel29.setText("DESCRIPCION");

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel30.setText("TEFEFONO");

        txtDireccionConfiguracion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        txtNombreConfiguracion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtNombreConfiguracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreConfiguracionKeyTyped(evt);
            }
        });

        txtDescripcionConfiguracion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtDescripcionConfiguracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionConfiguracionKeyTyped(evt);
            }
        });

        txtTelefonoConfiguracion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        txtTelefonoConfiguracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoConfiguracionKeyTyped(evt);
            }
        });

        btnActualizarConfiguracion.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        btnActualizarConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Actualizar (2).png"))); // NOI18N
        btnActualizarConfiguracion.setText("ACTUALIZAR");
        btnActualizarConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarConfiguracionActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel32.setText("DATOS DE LA EMPRESA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtNombreConfiguracion)
                        .addGap(18, 18, 18)
                        .addComponent(txtDescripcionConfiguracion))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGap(63, 63, 63)
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel29))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtIdConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(154, 154, 154)
                                    .addComponent(jLabel32)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtTelefonoConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(67, 67, 67)
                                .addComponent(txtDireccionConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(190, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(452, 452, 452)
                .addComponent(btnActualizarConfiguracion)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(299, 299, 299)
                .addComponent(jLabel27)
                .addGap(297, 297, 297))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel32))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(txtIdConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcionConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDireccionConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefonoConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(btnActualizarConfiguracion)
                .addGap(90, 90, 90))
        );

        jTabbedPane2.addTab("tab6", jPanel3);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 1060, 460));

        pack();
    }// </editor-fold>                        

    private void txtMarcaMaquinaActionPerformed(java.awt.event.ActionEvent evt) {                                                

    }                                               

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                  

        if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtApellidoCliente.getText()) || !"".equals(txtTelefonoCliente.getText())) {
            cl.setDni(txtDniCliente.getText());
            cl.setNombre(txtNombreCliente.getText());
            cl.setApellido(txtApellidoCliente.getText());
            cl.setTelefono(txtTelefonoCliente.getText());
            client.RegistrarCliente(cl);

            LimpiarTable();
            LimpiarCliente();
            ListarCliente();

            JOptionPane.showMessageDialog(null, "Cliente Registrado");

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");

        }


    }                                                 

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {                                            

        LimpiarTable();

        ListarCliente();
        jTabbedPane2.setSelectedIndex(1);


    }                                           

    private void tableClienteMouseClicked(java.awt.event.MouseEvent evt) {                                          

        int fila = tableCliente.rowAtPoint(evt.getPoint());

        txtIdCliente.setText(tableCliente.getValueAt(fila, 0).toString());
        txtDniCliente.setText(tableCliente.getValueAt(fila, 1).toString());
        txtNombreCliente.setText(tableCliente.getValueAt(fila, 2).toString());
        txtApellidoCliente.setText(tableCliente.getValueAt(fila, 3).toString());
        txtTelefonoCliente.setText(tableCliente.getValueAt(fila, 4).toString());

    }                                         

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                   

        if (!"".equals(txtIdCliente.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdCliente.getText());
                client.EliminarCliente(id);
                LimpiarTable();
                LimpiarCliente();
                ListarCliente();

            }

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }

    }                                                  

    private void btnActualizarClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                     

        if ("".equals(txtIdCliente.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila");

        } else {

            cl.setDni(txtDniCliente.getText());
            cl.setNombre(txtNombreCliente.getText());
            cl.setApellido(txtApellidoCliente.getText());
            cl.setTelefono(txtTelefonoCliente.getText());
            cl.setId(Integer.parseInt(txtIdCliente.getText()));

            if (!"".equals(txtDniCliente.getText()) || !"".equals(txtNombreCliente.getText()) || !"".equals(txtApellidoCliente.getText()) || !"".equals(txtTelefonoCliente.getText()) || !"".equals(txtIdCliente.getText()));
            client.ModificarCliente(cl);
            JOptionPane.showMessageDialog(null, "Cliente modificado con éxito.");
            LimpiarTable();
            LimpiarCliente();
            ListarCliente();

        }


    }                                                    

    private void btnNuevoClienteActionPerformed(java.awt.event.ActionEvent evt) {                                                

        LimpiarCliente();
    }                                               

    private void btnGuardarMaquinaActionPerformed(java.awt.event.ActionEvent evt) {                                                  

        if (!"".equals(txtNumSerieMaquina.getText()) || !"".equals(txtMarcaMaquina.getText()) || !"".equals(txtModeloMaquina.getText()) || !"".equals(txtTipoMaquina.getText()) || !"".equals(txtClientes.getText())) {
            maq.setClientes(txtClientes.getText());
            maq.setNumero_de_serie(txtNumSerieMaquina.getText());
            maq.setMarca(txtMarcaMaquina.getText());
            maq.setModelo(txtModeloMaquina.getText());
            maq.setTipo(txtTipoMaquina.getText());
            MaqDao.RegistrarMaquinas(maq);
            
            LimpiarTable();
            ListarMaquinas();
            LimpiarMaquinas();

            JOptionPane.showMessageDialog(null, "Maquina Registrada con exito");

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }


    }                                                 

    private void txtIdreparacionActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    private void btnMaquinasActionPerformed(java.awt.event.ActionEvent evt) {                                            
        
        LimpiarTable();
        ListarMaquinas();
        jTabbedPane2.setSelectedIndex(2);
        
    }                                           

    private void tableMaquinaMouseClicked(java.awt.event.MouseEvent evt) {                                          
        
        int fila = tableMaquina.rowAtPoint(evt.getPoint());
        
        txtClientes.setText(tableMaquina.getValueAt(fila, 0).toString());
        txtIdMaquinas.setText(tableMaquina.getValueAt(fila, 1).toString());
        txtNumSerieMaquina.setText(tableMaquina.getValueAt(fila, 2).toString());
        txtMarcaMaquina.setText(tableMaquina.getValueAt(fila, 3).toString());
        txtModeloMaquina.setText(tableMaquina.getValueAt(fila, 4).toString());
        txtTipoMaquina.setText(tableMaquina.getValueAt(fila, 5).toString());
        
        
    }                                         

    private void btnEliminarMaquinaActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        
        if (!"".equals(txtIdMaquinas.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?");
            if (pregunta == 0) {
                int id = Integer.parseInt(txtIdMaquinas.getText());
                MaqDao.EliminarMaquina(id);
                
                LimpiarTable ();
                ListarMaquinas ();
                LimpiarMaquinas ();
                
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }

        
    }                                                  

    private void btnActualizarMaquinaActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        
        if ("".equals(txtIdMaquinas.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila");

        } else {

            maq.setClientes(txtClientes.getText());
            maq.setId_Maquinas(Integer.parseInt(txtIdMaquinas.getText()));
            maq.setNumero_de_serie(txtNumSerieMaquina.getText());
            maq.setMarca(txtMarcaMaquina.getText());
            maq.setModelo(txtModeloMaquina.getText());
            maq.setTipo(txtTipoMaquina.getText());
            

            if (!"".equals(txtClientes.getText()) || !"".equals(txtIdMaquinas.getText()) || !"".equals(txtNumSerieMaquina.getText()) || !"".equals(txtMarcaMaquina.getText()) || !"".equals(txtModeloMaquina.getText()) || !"".equals(txtTipoMaquina.getText()));
            MaqDao.ModificarMaquinas(maq);
            JOptionPane.showMessageDialog(null, "Cliente modificado con éxito.");
            LimpiarTable();
            LimpiarMaquinas();
            ListarMaquinas();

        }
        
    }                                                    

    private void btnNuevaMaquinaActionPerformed(java.awt.event.ActionEvent evt) {                                                
        
        LimpiarMaquinas();
        
    }                                               

    private void btnGuardarReparacionActionPerformed(java.awt.event.ActionEvent evt) {                                                     
       
        
        if (!"".equals(txtMaquinas.getText()) || !"".equals(txtCodigoReparacion.getText()) || !"".equals(cbxEstado.getSelectedItem()) || !"".equals(txtDescripcionReparacion.getText()) || !"".equals(txtPrecioReparacion.getText())) {
            rep.setMaquinas(txtMaquinas.getText());
            rep.setCodigo(txtCodigoReparacion.getText());
            rep.setEstado(cbxEstado.getSelectedItem().toString());
            rep.setDescripcion(txtDescripcionReparacion.getText());
            rep.setPrecio(Double.parseDouble(txtPrecioReparacion.getText()));
            RepDao.RegistrarReparacion(rep);
            
            LimpiarTable();
            ListarReparacion();
            LimpiarReparacion();

            JOptionPane.showMessageDialog(null, "Reparacion Registrada con exito");

        } else {
            JOptionPane.showMessageDialog(null, "Los campos estan vacios");
        }
        
    }                                                    

    private void btnReparacionActionPerformed(java.awt.event.ActionEvent evt) {                                              
        
        LimpiarTable();
        ListarReparacion();
        jTabbedPane2.setSelectedIndex(3);
        
    }                                             

    private void tableReparacionMouseClicked(java.awt.event.MouseEvent evt) {                                             
       
        int fila = tableReparacion.rowAtPoint(evt.getPoint());
        
        txtMaquinas.setText(tableReparacion.getValueAt(fila, 0).toString());
        txtIdReparacion.setText(tableReparacion.getValueAt(fila, 1).toString());
        txtCodigoReparacion.setText(tableReparacion.getValueAt(fila, 2).toString());
        cbxEstado.setSelectedItem(tableReparacion.getValueAt(fila, 3).toString());
        txtDescripcionReparacion.setText(tableReparacion.getValueAt(fila, 4).toString());
        txtPrecioReparacion.setText(tableReparacion.getValueAt(fila, 5).toString());
        
    }                                            

    private void btnEliminarReparacionActionPerformed(java.awt.event.ActionEvent evt) {                                                      
        
        if (!"".equals(txtIdReparacion.getText())) {
            int pregunta = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?");
            if (pregunta == 0) {
                int id_Reparacion = Integer.parseInt(txtIdReparacion.getText());
                RepDao.EliminarReparacion(id_Reparacion);
                
                LimpiarTable ();
                ListarReparacion ();
                LimpiarReparacion();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila");
        }
        
    }                                                     

    private void btnActualizarReparacionActionPerformed(java.awt.event.ActionEvent evt) {                                                        
       
        if ("".equals(txtIdReparacion.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila");

        } else {

            rep.setMaquinas(txtMaquinas.getText());
            rep.setId_Reparacion(Integer.parseInt(txtIdReparacion.getText()));
            rep.setCodigo(txtCodigoReparacion.getText());
            rep.setEstado(cbxEstado.getSelectedItem().toString());
            rep.setDescripcion(txtDescripcionReparacion.getText());
            rep.setPrecio(Double.parseDouble(txtPrecioReparacion.getText()));
            
            if (!"".equals(txtMaquinas.getText()) || !"".equals(txtIdReparacion.getText()) || !"".equals(txtCodigoReparacion.getText()) || !"".equals(cbxEstado.getSelectedItem()) || !"".equals(txtDescripcionReparacion.getText()) || !"".equals(txtPrecioReparacion.getText()));
            RepDao.ModificarReparacion(rep);
            
            JOptionPane.showMessageDialog(null, "Reparacion modificada con éxito.");
            LimpiarTable();
            ListarReparacion();
            LimpiarReparacion();

        }
        
        
    }                                                       

    private void btnNuevaReparacionActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        LimpiarReparacion();
    }                                                  

    private void txtCodigoNCobroKeyPressed(java.awt.event.KeyEvent evt) {                                           
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCodigoNCobro.getText())) {
                String cod = txtCodigoNCobro.getText();
                rep = RepDao.BuscarRep(cod);
                if (rep.getMaquinas() != null) {
                    txtDescripcionCobro.setText(""+rep.getDescripcion());
                    cbxEstado.setSelectedItem(""+rep.getEstado());
                    txtPrecioCobro.setText(""+rep.getPrecio());
                    txtCantidadCobro.requestFocus();
                    
                } else {
                    LimpiarCobro();
                    txtCodigoNCobro.requestFocus();
                  
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese el codigo de la reparacion");
                txtCodigoNCobro.requestFocus();
            }
        }
        
    }                                          

    private void txtCantidadCobroKeyPressed(java.awt.event.KeyEvent evt) {                                            
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtCantidadCobro.getText())) {
                String cod = txtCantidadCobro.getText();
                String descripcion = txtDescripcionCobro.getText();
                int cant = Integer.parseInt(txtCantidadCobro.getText());
                double precio = Double.parseDouble(txtPrecioCobro.getText());
                double total = cant * precio;
                
                item = item + 1;
                DefaultTableModel tmp = (DefaultTableModel) tableNCobro.getModel();
                for (int i = 0; i < tableNCobro.getRowCount(); i++ ) {
                    if (tableNCobro.getValueAt(i, 1).equals(txtDescripcionCobro.getText())) {
                        JOptionPane.showMessageDialog(null, "La reparacion ya esta registrada");
                        return;
                        
                    }
                    
                }
                
                ArrayList lista = new ArrayList();
                lista.add(item);
                lista.add(cod);
                lista.add(descripcion);
                lista.add(cant);
                lista.add(precio);
                lista.add(total);
                Object[] O = new Object[5];
                O[0] = lista.get(1);
                O[1] = lista.get(2);
                O[2] = lista.get(3);
                O[3] = lista.get(4);
                O[4] = lista.get(5);
                tmp.addRow(O);
                tableNCobro.setModel(tmp);
                TotalPagar();
                LimpiarCobro();
                txtCodigoNCobro.requestFocus();
                
                
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese cantidad");
            }
        }
    }                                           

    private void btnEliminarCobroActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        
        modelo = (DefaultTableModel) tableNCobro.getModel();
        modelo.removeRow(tableNCobro.getSelectedRow());
        TotalPagar();
        txtCodigoNCobro.requestFocus();
    }                                                

    private void txtDniNCobroKeyPressed(java.awt.event.KeyEvent evt) {                                        
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!"".equals(txtDniNCobro.getText())) {
                String dni =(txtDniNCobro.getText());
                cl = client.BuscarCliente(dni);
                if (cl.getNombre() !=null) {
                    txtNombreClienteNCobro.setText(""+cl.getNombre());
                    txtApellidoNC.setText(""+cl.getApellido());
                    txtTelefonoNC.setText(""+cl.getTelefono());
                   
                } else {
                    
                    txtDniNCobro.setText("");
                    JOptionPane.showMessageDialog(null, "El cliente no existe");
                    
                }
                
                
            }
            
            
            
        }
        
    }                                       

    private void txtNombreClienteNCobroActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        
    }                                                      

    private void btnGenerarNuevoCobroActionPerformed(java.awt.event.ActionEvent evt) {                                                     
        if (tableCobro.getRowCount() > 0){
            if (!"".equals(txtNombreClienteNCobro.getText())) {
               RegistrarCobro();
               RegistrarDetalle(); 
               
            } else {
                JOptionPane.showMessageDialog(null, "Debes buscar un cliente");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron reparaciones");
        }
            
        
        
    }                                                    

    private void btnNuevoCobroActionPerformed(java.awt.event.ActionEvent evt) {                                              
       
        jTabbedPane2.setSelectedIndex(0);
        
    }                                             

    private void btnCobroActionPerformed(java.awt.event.ActionEvent evt) {                                         
        
        jTabbedPane2.setSelectedIndex(4);
        LimpiarTable();
        ListarCobro();
        
    }                                        

    private void btnActualizarConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {                                                           
        
        if ("".equals(txtIdConfiguracion.getText())) {
            JOptionPane.showMessageDialog(null, "Seleccione la fila");

        } else {

            conf.setNombre(txtNombreConfiguracion.getText());
            conf.setDescripcion(txtDescripcionConfiguracion.getText());
            conf.setTelefono(txtTelefonoConfiguracion.getText());
            conf.setDireccion(txtDireccionConfiguracion.getText());
            conf.setId_Configuracion(Integer.parseInt(txtIdConfiguracion.getText()));

            if (!"".equals(txtNombreConfiguracion.getText()) || !"".equals(txtDescripcionConfiguracion.getText()) || !"".equals(txtTelefonoConfiguracion.getText()) || !"".equals(txtDireccionConfiguracion.getText()));
            RepDao.ModificarDatos(conf);
            JOptionPane.showMessageDialog(null, "Datos modificados con éxito.");
            
            ListarConfiguracion();

        }
        
        
    }                                                          

    private void btnConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        
        jTabbedPane2.setSelectedIndex(5);
        
    }                                                

    private void txtCodigoNCobroKeyTyped(java.awt.event.KeyEvent evt) {                                         
       
        event.numberKeyPress(evt);
        
    }                                        

    private void txtDescripcionCobroKeyTyped(java.awt.event.KeyEvent evt) {                                             
        
        event.textKeyPress(evt);
        
    }                                            

    private void txtCantidadCobroKeyTyped(java.awt.event.KeyEvent evt) {                                          
        
        event.numberKeyPress(evt);
        
    }                                         

    private void txtPrecioCobroKeyTyped(java.awt.event.KeyEvent evt) {                                        
        
        event.numberDecimalKeyPress(evt, txtPrecioCobro);
        
    }                                       

    private void txtDniNCobroKeyTyped(java.awt.event.KeyEvent evt) {                                      
        
        event.numberKeyPress(evt);
        
    }                                     

    private void txtNombreClienteNCobroKeyTyped(java.awt.event.KeyEvent evt) {                                                
        
        event.textKeyPress(evt);
        
    }                                               

    private void txtApellidoNCKeyTyped(java.awt.event.KeyEvent evt) {                                       
        
        event.textKeyPress(evt);
        
    }                                      

    private void txtDniClienteKeyTyped(java.awt.event.KeyEvent evt) {                                       
        
        event.numberKeyPress(evt);

        
    }                                      

    private void txtNombreClienteKeyTyped(java.awt.event.KeyEvent evt) {                                          
        
        event.textKeyPress(evt);
        
    }                                         

    private void txtApellidoClienteKeyTyped(java.awt.event.KeyEvent evt) {                                            
        
        event.textKeyPress(evt);
        
    }                                           

    private void txtTelefonoClienteKeyTyped(java.awt.event.KeyEvent evt) {                                            
        
        event.numberKeyPress(evt);
        
    }                                           

    private void txtClientesKeyTyped(java.awt.event.KeyEvent evt) {                                     
        
        event.numberKeyPress(evt);
        
    }                                    

    private void txtNumSerieMaquinaKeyTyped(java.awt.event.KeyEvent evt) {                                            
        
        event.numberKeyPress(evt);
        
    }                                           

    private void txtMarcaMaquinaKeyTyped(java.awt.event.KeyEvent evt) {                                         
        
        event.textKeyPress(evt);
        
    }                                        

    private void txtModeloMaquinaKeyTyped(java.awt.event.KeyEvent evt) {                                          
        
        event.textKeyPress(evt);
    }                                         

    private void txtTipoMaquinaKeyTyped(java.awt.event.KeyEvent evt) {                                        
        
        event.numberKeyPress(evt);
        
    }                                       

    private void txtMaquinasKeyTyped(java.awt.event.KeyEvent evt) {                                     
       
        event.numberKeyPress(evt);
        
    }                                    

    private void txtCodigoReparacionKeyTyped(java.awt.event.KeyEvent evt) {                                             
        
        event.numberKeyPress(evt);
        
    }                                            

    private void txtDescripcionReparacionKeyTyped(java.awt.event.KeyEvent evt) {                                                  
        event.textKeyPress(evt);
    }                                                 

    private void txtPrecioReparacionKeyTyped(java.awt.event.KeyEvent evt) {                                             
        
        event.numberDecimalKeyPress(evt, txtPrecioReparacion);
        
    }                                            

    private void txtNombreConfiguracionKeyTyped(java.awt.event.KeyEvent evt) {                                                
        
        event.textKeyPress(evt);
        
    }                                               

    private void txtDescripcionConfiguracionKeyTyped(java.awt.event.KeyEvent evt) {                                                     
        
        event.textKeyPress(evt);
        
    }                                                    

    private void txtTelefonoConfiguracionKeyTyped(java.awt.event.KeyEvent evt) {                                                  
        
        event.numberKeyPress(evt);
        
    }                                                 

    
    
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
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel LabelMaquinas;
    private javax.swing.JLabel LabelReparacion;
    private javax.swing.JLabel LabelTotal;
    private javax.swing.JLabel Totalpagar;
    private javax.swing.JButton btnActualizarCliente;
    private javax.swing.JButton btnActualizarConfiguracion;
    private javax.swing.JButton btnActualizarMaquina;
    private javax.swing.JButton btnActualizarReparacion;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnCobro;
    private javax.swing.JButton btnConfiguracion;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnEliminarCobro;
    private javax.swing.JButton btnEliminarMaquina;
    private javax.swing.JButton btnEliminarReparacion;
    private javax.swing.JButton btnGenerarNuevoCobro;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarMaquina;
    private javax.swing.JButton btnGuardarReparacion;
    private javax.swing.JButton btnMaquinas;
    private javax.swing.JButton btnNuevaMaquina;
    private javax.swing.JButton btnNuevaReparacion;
    private javax.swing.JButton btnNuevoCliente;
    private javax.swing.JButton btnNuevoCobro;
    private javax.swing.JButton btnReparacion;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable tableCliente;
    private javax.swing.JTable tableCobro;
    private javax.swing.JTable tableMaquina;
    private javax.swing.JTable tableNCobro;
    private javax.swing.JTable tableReparacion;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextField txtApellidoNC;
    private javax.swing.JTextField txtCantidadCobro;
    private javax.swing.JTextField txtClientes;
    private javax.swing.JTextField txtCodigoNCobro;
    private javax.swing.JTextField txtCodigoReparacion;
    private javax.swing.JTextField txtDescripcionCobro;
    private javax.swing.JTextField txtDescripcionConfiguracion;
    private javax.swing.JTextField txtDescripcionReparacion;
    private javax.swing.JTextField txtDireccionConfiguracion;
    private javax.swing.JTextField txtDniCliente;
    private javax.swing.JTextField txtDniNCobro;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdCobro;
    private javax.swing.JTextField txtIdConfiguracion;
    private javax.swing.JTextField txtIdMaquinas;
    private javax.swing.JTextField txtIdReparacion;
    private javax.swing.JTextField txtIdreparacion;
    private javax.swing.JTextField txtMaquinas;
    private javax.swing.JTextField txtMarcaMaquina;
    private javax.swing.JTextField txtModeloMaquina;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombreClienteNCobro;
    private javax.swing.JTextField txtNombreConfiguracion;
    private javax.swing.JTextField txtNumSerieMaquina;
    private javax.swing.JTextField txtPrecioCobro;
    private javax.swing.JTextField txtPrecioReparacion;
    private javax.swing.JTextField txtTelefonoCliente;
    private javax.swing.JTextField txtTelefonoConfiguracion;
    private javax.swing.JTextField txtTelefonoNC;
    private javax.swing.JTextField txtTipoMaquina;
    // End of variables declaration                   
}
