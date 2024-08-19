import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class EmpleadoForm extends JFrame implements ActionListener {
    // Componentes de la interfaz gráfica
    private JTextField txtNombre, txtSueldo, txtComision, txtCargo, txtDepto;
    private JButton btnAgregar, btnEliminar, btnGuardar, btnModificar, btnConsultar;

    // Constructor para inicializar la interfaz gráfica
    public EmpleadoForm() {
        setTitle("Gestión de Empleados");
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 20, 150, 25);
        add(txtNombre);

        JLabel lblSueldo = new JLabel("Sueldo:");
        lblSueldo.setBounds(20, 60, 100, 25);
        add(lblSueldo);

        txtSueldo = new JTextField();
        txtSueldo.setBounds(130, 60, 150, 25);
        add(txtSueldo);

        JLabel lblComision = new JLabel("Comisión:");
        lblComision.setBounds(20, 100, 100, 25);
        add(lblComision);

        txtComision = new JTextField();
        txtComision.setBounds(130, 100, 150, 25);
        add(txtComision);

        JLabel lblCargo = new JLabel("Cargo:");
        lblCargo.setBounds(20, 140, 100, 25);
        add(lblCargo);

        txtCargo = new JTextField();
        txtCargo.setBounds(130, 140, 150, 25);
        add(txtCargo);

        JLabel lblDepto = new JLabel("Departamento:");
        lblDepto.setBounds(20, 180, 100, 25);
        add(lblDepto);

        txtDepto = new JTextField();
        txtDepto.setBounds(130, 180, 150, 25);
        add(txtDepto);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(20, 220, 100, 25);
        add(btnAgregar);
        btnAgregar.addActionListener(this);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(130, 220, 100, 25);
        add(btnEliminar);
        btnEliminar.addActionListener(this);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(240, 220, 100, 25);
        add(btnGuardar);
        btnGuardar.addActionListener(this);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(20, 260, 100, 25);
        add(btnModificar);
        btnModificar.addActionListener(this);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(130, 260, 100, 25);
        add(btnConsultar);
        btnConsultar.addActionListener(this);

        setSize(400, 350);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Método para gestionar las acciones de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            agregarEmpleado();
        } else if (e.getSource() == btnEliminar) {
            eliminarEmpleado();
        } else if (e.getSource() == btnGuardar) {
            guardarEmpleado();
        } else if (e.getSource() == btnModificar) {
            modificarEmpleado();
        } else if (e.getSource() == btnConsultar) {
            consultarEmpleado();
        }
    }

    private void agregarEmpleado() {
        Connection con = ConexionDB.conectar();
        String sql = "INSERT INTO empleados (nomEmp, salEmp, comisionE, cargoE, codDepto) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, txtNombre.getText());
            ps.setInt(2, Integer.parseInt(txtSueldo.getText()));
            ps.setInt(3, Integer.parseInt(txtComision.getText()));
            ps.setString(4, txtCargo.getText());
            ps.setInt(5, Integer.parseInt(txtDepto.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Empleado agregado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar empleado: " + e.getMessage());
        }
    }

    private void eliminarEmpleado() {
        Connection con = ConexionDB.conectar();
        String sql = "DELETE FROM empleados WHERE nomEmp = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, txtNombre.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Empleado eliminado con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar empleado: " + e.getMessage());
        }
    }

    private void guardarEmpleado() {
        Connection con = ConexionDB.conectar();
        String sql = "UPDATE empleados SET salEmp = ?, comisionE = ?, cargoE = ?, codDepto = ? WHERE nomEmp = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(txtSueldo.getText()));
            ps.setInt(2, Integer.parseInt(txtComision.getText()));
            ps.setString(3, txtCargo.getText());
            ps.setInt(4, Integer.parseInt(txtDepto.getText()));
            ps.setString(5, txtNombre.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos del empleado guardados con éxito");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar datos del empleado: " + e.getMessage());
        }
    }

    private void modificarEmpleado() {
        guardarEmpleado();
    }

    private void consultarEmpleado() {
        Connection con = ConexionDB.conectar();
        String sql = "SELECT * FROM empleados WHERE nomEmp = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, txtNombre.getText());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                txtSueldo.setText(String.valueOf(rs.getInt("salEmp")));
                txtComision.setText(String.valueOf(rs.getInt("comisionE")));
                txtCargo.setText(rs.getString("cargoE"));
                txtDepto.setText(String.valueOf(rs.getInt("codDepto")));
            } else {
                JOptionPane.showMessageDialog(null, "Empleado no encontrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar empleado: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new EmpleadoForm();
    }
}

class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return con;
    }
}
