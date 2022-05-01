package client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;

/**
 * @author lllxh
 * @author cy
 */
public class ClientUi extends JFrame implements ActionListener {
    /**
     * frame1：聊天室窗口
     * frame2:好友列表
     */
    JFrame frame1=new JFrame();
    JFrame frame2=new JFrame();

    public Client client;
    public PrintWriter printWriter;
    public JPanel jPanel1=new JPanel();
    public JPanel jPanel2=new JPanel();
    public JPanel jPanel3=new JPanel();
    public JPanel jPanel4=new JPanel();
    public JPanel jPanel5=new JPanel();
    public JPanel jPanel6=new JPanel();
    public static JTextArea jTextArea1=new JTextArea(12,42);
    public static JTextArea jTextArea2 = new JTextArea(12,42);
    public JLabel jLabel=new JLabel("Hi~ o(*￣▽￣*)ブ");
    public static JComboBox jComboBox=new JComboBox();
    public JTextField jTextField=new JTextField(36);
    public JButton jButton1=new JButton("发送");
    public JButton jButton2=new JButton("刷新");
    //列表格式
    public static DefaultListModel defaultListModel1;
    public static JList jList1;

    public String name;
    public String message;

    public void getUi(String name){

        frame1.setTitle("WeChat-"+name);
        frame1.setSize(700,550);
        frame1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame1.setLocationRelativeTo(null);
        frame1.setResizable(false);
        frame2.setTitle(name+"de好友列表");
        frame2.setSize(190,30);
        frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame2.setLocation(1250,20);
        frame2.setResizable(false);
        jComboBox.addItem("所有人");
        this.name=name;
        jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
        defaultListModel1=new DefaultListModel();
        jList1=new JList(defaultListModel1);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setVisibleRowCount(18);
        jList1.setFixedCellHeight(28);
        jList1.setFixedCellWidth(185);
        JScrollPane jScrollPane1=new JScrollPane(jTextArea1);
        JScrollPane jScrollPane2=new JScrollPane(jTextArea2);
        JScrollPane jScrollPane3=new JScrollPane(jList1);
        jScrollPane1.setBorder(new TitledBorder("公共聊天室"));
        jScrollPane2.setBorder(new TitledBorder("个人频道"));
        jScrollPane3.setBorder(new TitledBorder("好友列表"));
        //网格布局
        jPanel1.setLayout(new GridLayout(2,1));
        jPanel1.add(jScrollPane1);
        jPanel1.add(jScrollPane2);
        jPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanel2.add(jLabel);
        jPanel2.add(jComboBox);
        jPanel3.setLayout(new BorderLayout());
        jPanel3.add(jTextField,BorderLayout.CENTER);
        jPanel3.add(jButton1,BorderLayout.EAST);
        jPanel4.setLayout(new GridLayout(2,1));
        jPanel4.add(jPanel2);
        jPanel4.add(jPanel3);
        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(jPanel1,BorderLayout.NORTH);
        jPanel5.add(jPanel4,BorderLayout.SOUTH);
        jPanel6.setLayout(new BorderLayout());
        jPanel6.add(jScrollPane3,BorderLayout.NORTH);
        jPanel6.add(jButton2,BorderLayout.SOUTH);
        Border border = jPanel5.getBorder();
        Border margin = new EmptyBorder(10,10,10,10);
        jPanel5.setBorder(new CompoundBorder(border,margin));
        frame1.add(jPanel5);
        Border border1 = jPanel6.getBorder();
        Border margin1 = new EmptyBorder(10,10,10,10);
        jPanel6.setBorder(new CompoundBorder(border1,margin1));
        frame2.add(jPanel6);
        //监听点击图标动作
        jButton1.addActionListener(this);
        //监听点击图标动作
        jButton2.addActionListener(this);
        jTextField.setSize(400,150);
        //自动换行
        jTextArea1.setLineWrap(true);
        jTextArea2.setLineWrap(true);
        //垂直滚动条
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        //never 横向滚动条
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //设置可见
        frame1.setVisible(true);
        frame2.setVisible(true);
        //自动适应窗口大小
        frame2.pack();
    }

    public void socket(){
        try{
            //将用户信息以字符串的形式保存
            String user=name;
            //创建一个客户端对象
            client=new Client(user);
            //创建输出流
            printWriter=new PrintWriter(Client.socket.getOutputStream());
            //发送创建好友列表标识
            printWriter.println("100");
            //发送用户信息
            printWriter.println(name);
            printWriter.flush();
            //发送进入聊天室的标识
            printWriter.println("400");
            printWriter.println("欢迎【"+user+"】来到聊天室≥Ö‿Ö≤");
            printWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public ClientUi(){
        frame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try{
                    printWriter=new PrintWriter(Client.socket.getOutputStream());
                    //发送下线标识
                    printWriter.println("500");
                    printWriter.println(name+":下线了");
                    printWriter.flush();
                    //软件关闭窗口
                    frame1.dispose();
                    frame2.dispose();
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        }
        );
        frame2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try{
                    printWriter=new PrintWriter(Client.socket.getOutputStream());
                    //发送下线标识
                    printWriter.println("500");
                    printWriter.println(name+":下线了");
                    printWriter.flush();
                    //软件关闭窗口
                    frame1.dispose();
                    frame2.dispose();
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        }
        );
    }

    @Override
    public void actionPerformed(ActionEvent event){
        jButton1.setText("发送");
        jButton2.setText("刷新");
        try{
            printWriter=new PrintWriter(Client.socket.getOutputStream());
            if ("发送".equals(event.getActionCommand())){
                if (!"".equals(jTextField.getText())){
                    if (jComboBox.getSelectedItem().equals("所有人")){
                        //发送群聊标识
                        printWriter.println("200");
                        printWriter.println("【群发消息】"+name+"说："+jTextField.getText());
                        printWriter.flush();

                    }
                    else {
                        String name1=(String) jComboBox.getSelectedItem();
                        message="【私聊消息】 "+name+"对"+name1+"说："+jTextField.getText();
                        //发送私聊标识
                        printWriter.println("300");
                        printWriter.println(name+":"+name1+"911"+message);
                        printWriter.flush();
                    }
                }
            }
            else if ("刷新".equals(event.getActionCommand())){
                printWriter=new PrintWriter(Client.socket.getOutputStream());
                //发送刷新的标识
                printWriter.println("600");
                printWriter.flush();
            }
        }catch (Exception e2){
            e2.printStackTrace();
        }
        //发完聊天栏 清空聊天缓冲区
        jTextField.setText("");
        //指针指向聚焦
        jTextField.requestFocus();
    }
}
