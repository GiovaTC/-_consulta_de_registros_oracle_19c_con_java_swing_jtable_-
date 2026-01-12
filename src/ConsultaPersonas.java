import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.stream.events.StartElement;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConsultaPersonas extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    // datos de conexion .
    private static final String URL =
            "jdbc:oracle:thin:@//localhost:1521/orcl";
    private static final String USER = "system";
    private static final String PASS = "Tapiero123";

    public ConsultaPersonas() {
        setTitle("Consulta de personas - oracle 19 c");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tabla 2 x N (2 columnas)
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        cargarDatos();
    }

    private void cargarDatos() {
        try (Connection conn = DriverManager.getConnection(URL, USER,  PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT ID, NOMBRE FROM PERSONA")) {

            while (rs.next()) {
                Object[] fila = {
                        rs.getInt("ID"),
                        rs.getString("NOMBRE")
                };
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
            "Error al consultar la base de datos:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
       SwingUtilities.invokeLater(() -> {
          new ConsultaPersonas().setVisible(true);
       });
    }
}   