package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton regisrarPacienteButton;
    private JButton buscarPacienteButton;
    public JPanel PanelMenu;

    public Menu() {
        regisrarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame registroPaciente = new JFrame();
                registroPaciente.setTitle("Registrar Paciente");
                registroPaciente.setContentPane(new Registrar_Pacientes().PanelRegistro);
                registroPaciente.setSize(500,400);
                registroPaciente.setLocationRelativeTo(null);
                registroPaciente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                registroPaciente.setVisible(true);
                ((JFrame) SwingUtilities.windowForComponent(regisrarPacienteButton)).dispose();
            }
        });
        buscarPacienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame buscarPaciente = new JFrame();
                buscarPaciente.setTitle("Buscar Paciente");
                buscarPaciente.setContentPane(new Busqueda_Pacintes().PanelBusqueda);
                buscarPaciente.setSize(500,400);
                buscarPaciente.setLocationRelativeTo(null);
                buscarPaciente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                buscarPaciente.setVisible(true);
                ((JFrame) SwingUtilities.getWindowAncestor(buscarPacienteButton)).dispose();
            }
        });
    }
}
