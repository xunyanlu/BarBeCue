package guet.edu.cn.LoginUp;

import cn.edu.guet.ulit.PasswordEncoder;
import guet.edu.cn.LoginIn.LoginIn;
import guet.edu.cn.Menu.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginUp extends JFrame {

    private JPanel jPanel;
    private JTextField account;
    private JTextField password;
    private JButton login;
    private JButton loginup;

    public LoginUp(String title){

        super(title);//设定界面大小
        setSize(400,280);
        setResizable(false);//禁止窗口缩放
        jPanel=(JPanel) this.getContentPane();
        jPanel.setLayout(null);//布局为空

        account=new JTextField("zhangsan");
        account.setBounds(105,130,190,35);
        jPanel.add(account);

        password=new JTextField("123456");
        String passes=password.getText();
        password.setBounds(105,160,190,35);
        jPanel.add(password);

        login = new JButton("登录");
        login.setBounds(105,200,190,35);
        jPanel.add(login);

        loginup = new JButton("注册");
        loginup.setBounds(300,200,80,35);
        jPanel.add(loginup);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username=account.getText();
                String pass=password.getText();



                String sql="SELECT *"+
                        "FROM users"+
                        " WHERE username=? ";//SQL语句


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
                        pstmt.setString(1,username);

                        resultSet = pstmt.executeQuery();//执行查询，得到一个结果集
                        resultSet.next();

                        String PASS=resultSet.getString("PASSWORD");

                        PasswordEncoder encoder = new PasswordEncoder("www.guet.edu.cn");

                        if(encoder.matches(PASS,passes)){
                            System.out.println("登录成功");
                            setVisible(false);
                            Menu main = new Menu();
                            main.getjFrame().setVisible(true);
                        }else {
                            System.out.println("用户名或密码错误");
                        }


                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }finally {
                    try {
                        conn.close();//释放数据库链接资源
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }


            }
        });

        loginup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username=account.getText();
                String pass=password.getText();

                String sql="SELECT *"+
                        "FROM users"+
                        " WHERE username=? ";//SQL语句

                PreparedStatement pstmt;
                Connection conn = null;
                ResultSet resultSet;
                String url="jdbc:oracle:thin:@106.55.182.14:1521:orcl";

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn= DriverManager.getConnection(url,"hls","Grcl1234HlS");

                    if (conn!=null){
                        System.out.println("链接成功");
                        new LoginIn("注册窗口");
                    }


                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });






        setVisible(true);//窗口可见


    }



}
