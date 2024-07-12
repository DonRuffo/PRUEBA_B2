package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JTextField UsuarioLogin;
    private JPasswordField ContraLogin;
    private JButton ingresarButton;
    public JPanel PanelLogin;

    public Login() {
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String user="root";
                String pass="Lap20112023$";
                String url="jdbc:mysql://localhost:3306/sistema_hospitalario";


                try(Connection conec= DriverManager.getConnection(url,user,pass)){
                    System.out.println("Conexion Exitosa");
                    String usuario=UsuarioLogin.getText();
                    String contra=ContraLogin.getText();
                    String sql="select * from usuario where username='"+usuario+"' and password='"+contra+"'";

                    Statement st=conec.createStatement();
                    ResultSet resul=st.executeQuery(sql);

                    while(resul.next()){
                        if(contra.equals(resul.getString("password")) && usuario.equals(resul.getString("username"))){
                            JFrame menu=new JFrame();
                            menu.setTitle("Menú de opciones");
                            menu.setContentPane(new Menu().PanelMenu);
                            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            menu.setSize(400,300);
                            menu.setLocationRelativeTo(null);
                            menu.setVisible(true);
                            ((JFrame) SwingUtilities.getWindowAncestor(ingresarButton)).dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"No existe el usuario");
                            UsuarioLogin.setText("");
                            ContraLogin.setText("");
                        }
                    }

                }catch(SQLException v){
                    v.printStackTrace();
                }


            }
        });
    }
}
