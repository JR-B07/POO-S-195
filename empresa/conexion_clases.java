import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
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

private void agregarEmpleado() {
    Connection con = ConexionDB.conectar();
    String sql = "INSERT INTO empleados (nomEmp, salEmp, comision, cargoE, codDepto) VALUES (?, ?, ?, ?, ?)";
    
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
    String sql = "UPDATE empleados SET salEmp = ?, comision = ?, cargoE = ?, codDepto = ? WHERE nomEmp = ?";
    
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
