package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Busqueda_Pacintes {
    private JTextField HistorialBus;
    private JTextField NombreBus;
    private JTextField ApellidoBus;
    private JTextField TelefonoBus;
    private JTextField EdadBus;
    private JTextField DescripBus;
    private JButton buscarButton;
    private JButton registrarUnPacienteButton;
    private JTextField CedulaBus;
    private JButton cerrarSesiónButton;
    public JPanel PanelBusqueda;

    public Busqueda_Pacintes() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user="root";
                String pass="Lap20112023$";
                String url="jdbc:mysql://localhost:3306/sistema_hospitalario";

                String iden=CedulaBus.getText();

                String sql="SELECT * FROM PACIENTE WHERE cedula = '"+iden+"'";

                try(Connection conec2= DriverManager.getConnection(url,user,pass)){
                    System.out.println("Conexion exitosa");
                    Statement st2=conec2.createStatement();
                    ResultSet rs=st2.executeQuery(sql);

                    while(rs.next()){
                        HistorialBus.setText(rs.getString("n_historial_clinico"));
                        NombreBus.setText(rs.getString("nombre"));
                        ApellidoBus.setText(rs.getString("apellido"));
                        TelefonoBus.setText(rs.getString("telefono"));
                        EdadBus.setText(rs.getString("edad"));
                        DescripBus.setText(rs.getString("descripcion_enfermedad"));
                    }
                    rs.close();

                }catch(SQLException b){
                    b.printStackTrace();
                }
            }
        });
        registrarUnPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registroPaciente = new JFrame();
                registroPaciente.setTitle("Registrar Paciente");
                registroPaciente.setContentPane(new Registrar_Pacientes().PanelRegistro);
                registroPaciente.setSize(500,400);
                registroPaciente.setLocationRelativeTo(null);
                registroPaciente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                registroPaciente.setVisible(true);
                ((JFrame) SwingUtilities.windowForComponent(registrarUnPacienteButton)).dispose();
            }
        });
        cerrarSesiónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame loginFrame = new JFrame();
                loginFrame.setTitle("Login");
                loginFrame.setContentPane(new Login().PanelLogin);
                loginFrame.setSize(500, 300);
                loginFrame.setLocationRelativeTo(null);
                loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginFrame.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(cerrarSesiónButton)).dispose();
            }
        });
    }
}
