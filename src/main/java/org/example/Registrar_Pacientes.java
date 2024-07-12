package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registrar_Pacientes {
    private JTextField HistorialRegis;
    private JTextField NombreRegis;
    private JTextField ApellidoRegis;
    private JTextField TelefonoRegis;
    private JTextField EdadRegis;
    private JTextField DescripRegis;
    private JButton registrarButton;
    private JButton buscarUnPacienteButton;
    private JTextField CedulaRegis;
    public JPanel PanelRegistro;

    public Registrar_Pacientes() {
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user="root";
                String pass="Lap20112023$";
                String url="jdbc:mysql://localhost:3306/sistema_hospitalario";

                String sql="INSERT INTO PACIENTE (cedula, n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad) VALUES (?,?,?,?,?,?,?)";

                try(Connection conec1= DriverManager.getConnection(url,user,pass)){
                    System.out.println("Conexión exitosa");
                    PreparedStatement pst=conec1.prepareStatement(sql);
                    int historial=Integer.parseInt(HistorialRegis.getText());
                    int edad=Integer.parseInt(EdadRegis.getText());

                    pst.setString(1,CedulaRegis.getText());
                    pst.setInt(2,historial);
                    pst.setString(3,NombreRegis.getText());
                    pst.setString(4,ApellidoRegis.getText());
                    pst.setString(5,TelefonoRegis.getText());
                    pst.setInt(6,edad);
                    pst.setString(7,DescripRegis.getText());

                    int resultado=pst.executeUpdate();
                    if(resultado>0){
                        JOptionPane.showMessageDialog(null,"Registro exitosa");
                    }
                    pst.close();

                }catch(SQLException c){
                    c.printStackTrace();
                }
            }
        });
        buscarUnPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame buscarPaciente = new JFrame();
                buscarPaciente.setTitle("Buscar Paciente");
                buscarPaciente.setContentPane(new Busqueda_Pacintes().PanelBusqueda);
                buscarPaciente.setSize(500,400);
                buscarPaciente.setLocationRelativeTo(null);
                buscarPaciente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                buscarPaciente.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(buscarUnPacienteButton)).dispose();
            }
        });
    }
}
