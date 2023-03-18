package guet.edu.cn.LoginIn;

import guet.edu.cn.bean.Product;
import guet.edu.cn.ulit.PasswordEncoder;

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

    int a=0;



    public LoginIn(String title){


        super(title);//设定界面大小
        setSize(400,280);
        setResizable(false);//禁止窗口缩放
        jPanel=(JPanel) this.getContentPane();
        jPanel.setLayout(null);//布局为空


        account=new JTextField("zhangsangeee");
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

                String sql1="INSERT INTO USERS VALUES(?,?)";

                PreparedStatement pstmt = null;
                PreparedStatement pstmt1 = null;
                Connection conn = null;
                ResultSet resultSet;
                String url="jdbc:oracle:thin:@106.55.182.14:1521:orcl";

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn= DriverManager.getConnection(url,"hls","Grcl1234HlS");
                    if (conn!=null){
                        System.out.println("链接成功");

                        pstmt=conn.prepareStatement(sql);//把PSTMT和SQL语句关联
                        pstmt1=conn.prepareStatement(sql1);
                        //pstmt.setString(1,username);


                        resultSet = pstmt.executeQuery();//执行查询，得到一个结果集

                        //resultSet.next();

                        //String P=resultSet.getString("USERNAME");
                        //System.out.println(P);



                        while (resultSet.next()){

                            if (username.equals(resultSet.getString("USERNAME"))) {

                                System.out.println("您已注册，请前往登录");
                                a=1;
                                break;
                            }
                        }



                        if (a!=1){
                            pstmt1.setString(1,username);
                            PasswordEncoder encoder = new PasswordEncoder("www.guet.edu.cn");
                            String passes= encoder.encode(pass);
                            System.out.println(passes);
                            pstmt1.setString(2,passes);

                            int rowsInserted = pstmt1.executeUpdate();
                            if (rowsInserted > 0) {
                                System.out.println("数据插入成功！");
                            }

                        }
                        /*pstmt1.setString(1,username);
                        PasswordEncoder encoder = new PasswordEncoder("www.guet.edu.cn");
                        String passes= encoder.encode(pass);
                        System.out.println(passes);
                        pstmt1.setString(2,passes);

                        int rowsInserted = pstmt.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("数据插入成功！");
                        }
                        pstmt1.close();

                         */


                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }finally {
                    try {
                        pstmt.close();
                        pstmt1.close();
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);//窗口可见



    }



}
