package guet.edu.cn.LoginIn;

import guet.edu.cn.bean.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoginIn extends JFrame{

    private JFrame jFrame;
    private JPanel jPanel;

    private JTextField account;
    private JTextField password;

    private JButton loginup;

    private String columnNames[] = {"USERNAME"};



    public LoginIn(String title){


        super(title);//设定界面大小
        setSize(400,280);
        setResizable(false);//禁止窗口缩放
        jPanel=(JPanel) this.getContentPane();
        jPanel.setLayout(null);//布局为空


        account=new JTextField("zhangsang");
        account.setBounds(105,130,190,35);
        jPanel.add(account);

        password=new JTextField("123456");
        String passes=password.getText();
        password.setBounds(105,160,190,35);
        jPanel.add(password);


        loginup = new JButton("注册");
        loginup.setBounds(105,200,190,35);
        jPanel.add(loginup);


        loginup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username=account.getText();
                String pass=password.getText();

                String sql="SELECT username"+
                        " FROM users";//SQL语句

                PreparedStatement pstmt;
                Connection conn = null;
                ResultSet resultSet;
                String url="jdbc:oracle:thin:@106.55.182.14:1521:orcl";

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn= DriverManager.getConnection(url,"hls","Grcl1234HlS");
                    if (conn!=null){
                        System.out.println("链接成功");

                        pstmt=conn.prepareStatement(sql);//把PSTMT和SQL语句关联
                        //pstmt.setString(1,username);


                        resultSet = pstmt.executeQuery();//执行查询，得到一个结果集

                        //resultSet.next();

                        //String P=resultSet.getString("USERNAME");
                        //System.out.println(P);



                        while (resultSet.next()){

                            if (username.equals(resultSet.getString("USERNAME"))){

                                System.out.println("您已注册，请前往登录");
                                break;

                            }else {
                                System.out.println("请注册");

                            }

                        }

                        /*if (USERMANE==null){
                            System.out.println("您已注册，请前往登录");
                        }else {
                            System.out.println("请注册");
                        }*/

                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);//窗口可见



    }



}
