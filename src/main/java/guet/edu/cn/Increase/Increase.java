package guet.edu.cn.Increase;

import oracle.security.crypto.core.Padding;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Increase extends JFrame {

    private JFrame jFrame;
    private JPanel jPanel;

    private JTextField ID;
    private JTextArea id;
    private JTextField name;
    private JTextField price;
    private JTextField type;

    private JButton increase;

    public Increase(String title){
        super(title);//设定界面大小
        setSize(400,280);
        setResizable(false);//禁止窗口缩放
        jPanel=(JPanel) this.getContentPane();
        jPanel.setLayout(null);//布局为空


        ID=new JTextField("");
        ID.setBounds(105,40,190,30);
        jPanel.add(ID);


        name=new JTextField("");
        name.setBounds(105,80,190,30);
        jPanel.add(name);

        price = new JTextField("");
        price.setBounds(105,120,190,30);
        jPanel.add(price);

        type = new JTextField("");
        type.setBounds(105,160,190,30);
        jPanel.add(type);


        increase = new JButton("添加");
        increase.setBounds(105,200,190,35);
        jPanel.add(increase);

        increase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id= ID.getText();
                String NAME=name.getText();
                String Py=price.getText();
                String ty=type.getText();

                String sql="INSERT INTO PRODUCT VALUES(?,?,?,?)";

                PreparedStatement pstmt=null;
                Connection conn = null;
                ResultSet resultSet;
                String url="jdbc:oracle:thin:@106.55.182.14:1521:orcl";

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn= DriverManager.getConnection(url,"hls","Grcl1234HlS");
                    if (conn!=null) {
                        System.out.println("链接成功");
                        pstmt=conn.prepareStatement(sql);//把PSTMT和SQL语句关联


                        pstmt.setString(1,id);
                        pstmt.setString(2,NAME);
                        pstmt.setString(3,Py);
                        pstmt.setString(4,ty);

                        int rowsInserted = pstmt.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("数据插入成功！");
                        }

                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });


        setVisible(true);//窗口可见


    }

}
