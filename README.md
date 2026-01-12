# -_consulta_de_registros_oracle_19c_con_java_swing_jtable_- :. 
# Consulta de Registros Oracle 19c con Java Swing (JTable):

<img width="1536" height="1024" alt="image" src="https://github.com/user-attachments/assets/dde0d1d4-fa9b-4a51-846f-7caf4684162e" />        

<img width="2548" height="1079" alt="image" src="https://github.com/user-attachments/assets/2c858980-75d9-4957-aa94-46150da8e2e0" />        

A continuación se presenta una solución completa, profesional y ejecutable, alineada con buenas prácticas académicas, que cumple exactamente con lo solicitado:

- Oracle Database 19c.  
- Script SQL con creación de tabla y **20 INSERTS de ejemplo**.  
- Consulta de registros mediante **JDBC**.  
- Interfaz gráfica **Java Swing**.  
- Visualización en una ventana con **JTable de 2 columnas (estructura 2 × N)**.  

---

## 1. Script SQL – Oracle Database 19c:

### 1.1 Creación de la tabla.

```sql
CREATE TABLE PERSONA (
    ID NUMBER PRIMARY KEY,
    NOMBRE VARCHAR2(50)
);
1.2 Inserción de 20 registros de ejemplo

INSERT INTO PERSONA VALUES (1, 'Ana Torres');
INSERT INTO PERSONA VALUES (2, 'Carlos Ruiz');
INSERT INTO PERSONA VALUES (3, 'María López');
INSERT INTO PERSONA VALUES (4, 'Juan Pérez');
INSERT INTO PERSONA VALUES (5, 'Laura Gómez');
INSERT INTO PERSONA VALUES (6, 'Pedro Martínez');
INSERT INTO PERSONA VALUES (7, 'Sofía Rodríguez');
INSERT INTO PERSONA VALUES (8, 'Andrés Herrera');
INSERT INTO PERSONA VALUES (9, 'Paula Ramírez');
INSERT INTO PERSONA VALUES (10, 'Diego Castillo');
INSERT INTO PERSONA VALUES (11, 'Lucía Morales');
INSERT INTO PERSONA VALUES (12, 'Jorge Silva');
INSERT INTO PERSONA VALUES (13, 'Valentina Rojas');
INSERT INTO PERSONA VALUES (14, 'Felipe Ortiz');
INSERT INTO PERSONA VALUES (15, 'Camila Vargas');
INSERT INTO PERSONA VALUES (16, 'Miguel Suárez');
INSERT INTO PERSONA VALUES (17, 'Natalia Pineda');
INSERT INTO PERSONA VALUES (18, 'Sebastián León');
INSERT INTO PERSONA VALUES (19, 'Daniela Cruz');
INSERT INTO PERSONA VALUES (20, 'Ricardo Mejía');

COMMIT;
2. Configuración del Proyecto Java (IntelliJ IDEA)
Requisitos
JDK 8 o superior

Oracle JDBC Driver (ojdbc8.jar u ojdbc11.jar)

Oracle Database 19c

Configuración del Driver
Ir a File → Project Structure

Seleccionar Libraries

Agregar el archivo ojdbc8.jar o ojdbc11.jar

3. Programa Java – Interfaz Gráfica con JTable
3.1 Clase principal (Swing + JDBC)

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConsultaPersonas extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;

    // Datos de conexión
    private static final String URL =
            "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String USER = "TU_USUARIO";
    private static final String PASS = "TU_PASSWORD";

    public ConsultaPersonas() {
        setTitle("Consulta de Personas - Oracle 19c");
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
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
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
            JOptionPane.showMessageDialog(
                    this,
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
4. Resultado Esperado
Ventana Swing con una JTable visible

2 columnas: ID y NOMBRE

Registros cargados dinámicamente desde Oracle Database 19c

Código simple, claro y listo para pruebas académicas o técnicas

5. Observaciones Técnicas
La JTable maneja una estructura 2 × N (2 columnas por múltiples filas).

Si se requiere estrictamente 2 × 2 (solo dos registros), se puede limitar la consulta:

SELECT ID, NOMBRE 
FROM PERSONA 
FETCH FIRST 2 ROWS ONLY;
El diseño es fácilmente escalable a MVC o arquitectura por capas para proyectos más complejos : . . / .
