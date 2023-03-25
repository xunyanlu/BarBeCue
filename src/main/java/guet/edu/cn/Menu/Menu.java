package guet.edu.cn.Menu;

import guet.edu.cn.Increase.Increase;
import guet.edu.cn.LoginIn.LoginIn;
import guet.edu.cn.bean.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Menu {

        private JFrame jFrame;
        private JPanel jPanel;
        private JButton increase;


        JMenu jMenu = new JMenu("基础信息管理");
        JMenu jMenu1 = new JMenu("增减");
        JMenuBar jMenuBar = new JMenuBar();
        JMenuBar jMenuBar1 = new JMenuBar();
        JMenuItem item01 = new  JMenuItem("查看商品");
        JMenuItem item02 = new JMenuItem("增加");
        JMenuItem item03 = new JMenuItem("销售商品");

        JButton delete;
        JButton pay;



        JTable table;
        JScrollPane jscrollpane = new JScrollPane();
        JScrollPane jscrollPane1=new JScrollPane();
        private String columnNames[] = {"id", "商品名称", "单价", "类型"};
        private Object[][] rowData = null;
        private Object[][] rowData1 = {
                {"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},
                {"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},
                {"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""},{"","","",""}
        };



        public JFrame getjFrame() {
            return jFrame;
        }

        public Menu(){

            delete = new JButton("删除商品");
            delete.setBounds(630, 300, 100, 30);

            pay = new JButton("结账");
            pay.setBounds(630, 250, 100, 30);


            jFrame = new JFrame("主页面");
            jPanel= (JPanel) jFrame.getContentPane();
            jFrame.setSize(1400,1100);
            jPanel.setLayout(null);


            item01.addActionListener(e -> {
                Connection conn = null;
                String url="jdbc:oracle:thin:@106.55.182.14:1521:orcl";
                String sql = "SELECT * FROM product";
                PreparedStatement pstmt;
                ResultSet rs;

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    conn= DriverManager.getConnection(url,"hls","Grcl1234HlS");
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();
                    List<Product> productList=new ArrayList<>();

                    while (rs.next()) {
                        //取出的数据，放入一个容器（数据的载体）
                        Product product=new Product();
                        product.setId(rs.getInt("ID"));
                        product.setName(rs.getString("NAME"));
                        product.setPrice(rs.getFloat("PRICE"));
                        product.setType(rs.getString("TYPE"));

                        productList.add(product);//每循环一次把拿到的商品的数据存入集合，好比每摘一个芒果，把芒果放入桶里
                    }
                    rowData=new Object[productList.size()][columnNames.length];

                    for (int i=0;i<rowData.length;i++){

                        Product product = productList.get(i);

                        rowData[i]=new Object[]{product.getId(),product.getName(),product.getPrice(),product.getType()};

                        System.out.println(rowData[i]);


                    }



                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }finally {
                    try {
                        conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                //创建菜单表格



                table = new JTable(rowData, columnNames);
                jscrollpane.setBounds(15, 31, 1000, 700);
                jscrollpane.setViewportView(table);
                table.setRowHeight(35);
                /**
                 * 字居中显示设置
                 */
                DefaultTableCellRenderer r = new DefaultTableCellRenderer();
                r.setHorizontalAlignment(JLabel.CENTER);
                table.setDefaultRenderer(Object.class, r);
                jPanel.add(jscrollpane);

            });
        item02.addActionListener(e -> {
            new Increase("增加");

            DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            r.setHorizontalAlignment(JLabel.CENTER);
            jPanel.add(jscrollpane);

            });
        item03.addActionListener(e -> {



            //到数据库查询商品，并以JLABEL的方式显示
            table = new JTable(rowData1, columnNames);
            jscrollpane.setBounds(15, 31, 600, 700);
            jscrollpane.setViewportView(table);
            table.setRowHeight(35);
            jPanel.add(pay);
            jPanel.add(delete);
            jPanel.repaint();
            /**
             * 字居中显示设置
             */
            //DefaultTableCellRenderer r = new DefaultTableCellRenderer();
            //r.setHorizontalAlignment(JLabel.CENTER);
            //table.setDefaultRenderer(Object.class, r);
            jPanel.add(jscrollpane);

            /**
             * 表数据居中显示
             */
            //去数据库查询商品，并以JLabel的方式显示

            JLabel product01 = new JLabel();
            /*ImageIcon icon= new ImageIcon("yrc.jpeg");
            icon.setImage(icon.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));

             */
            //headPhoto.setVerticalTextPosition(JLabel.CENTER);
            product01.setHorizontalTextPosition(JLabel.RIGHT);
            product01.setBounds(800, 40, 100, 150);

            String strMsg1 = "羊肉串";
            String strMsg2 = "58.00";
            String url = "'http://rs2k7eyd9.hn-bkt.clouddn.com/yrc.jpg?e=1679804815&token=c9rEIyXwBbSbobcEGRgub1mhH59rysYrX5853bDs:jmwU5OFknlLPzWYODQs8qOcES_U='";
            /*String strMsg = "<html><body bgcolor='#fae070' color='black'>" +strMsg1+
                            "<br>" + strMsg2 + "<body></html>";

             */
            String strMsg = "<html><body bgcolor='#fae070' color='black'>" +
                    "<img width='100' height='100' align='left' " +
                    "src=" + url + "/>"
                    + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + strMsg1 + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;￥"
                    + strMsg2 + "<body></html>";
            product01.setText(strMsg);
            jPanel.add(product01);
            jPanel.repaint();
            product01.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    /*把选择的商品添加到左侧*/
                    rowData1[0]= new String[]{"1", strMsg1, strMsg2, "肉类"};
                    jscrollpane.repaint();

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });



        });


            jMenu.add(item01);
            jMenu1.add(item02);
            jMenu.add(item03);
            jMenuBar.add(jMenu);
            jMenuBar1.add(jMenu1);
            jMenuBar.setBounds(0,0,100,30);
            jMenuBar1.setBounds(100,0,50,30);
            jPanel.add(jMenuBar);
            jPanel.add(jMenuBar1);





            //Frame.setVisible(true);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }




}
