package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 登录UI
 * @author lllxh
 * @author cy
 */
public class LoginUi extends Frame implements ActionListener {
    JFrame frame = new JFrame();
    JButton button1 = new JButton();
    JButton button2 = new JButton();
    JTextField textField = new JTextField();

    public LoginUi(){
        init();
    }
    /**
     * 初始化登录界面
     */
    public void init(){
        //设置标题
        frame.setTitle("Login");
        //设置窗体大小
        frame.setSize(500,300);
        //设置窗体默认关闭模式
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //设置窗体相对于另一个组件的居中位置，参数null表示窗体相对于屏幕的中央位置
        frame.setLocationRelativeTo(null);
        //设置不可编辑大小
        frame.setResizable(false);
        //设置布局模式
        FlowLayout f1 = new FlowLayout(FlowLayout.CENTER,10,10);
        frame.setLayout(f1);
        // 实例化ImageIcon图标类的对象，该对象加载磁盘上的图片文件到内存中，
        ImageIcon icon = new ImageIcon("res\\2.jpg");
        // 用标签来接收图片，实例化JLabel标签对象，该对象显示icon图标
        JLabel labIcon = new JLabel(icon);
        //设置标签大小
        //labIcon.setSize(30,20);setSize方法只对窗体有效，如果想设置组件的大小只能用
        Dimension dim = new Dimension(500,150);
        labIcon.setPreferredSize(dim);

        frame.add(labIcon);
        // 实例化JLabel标签对象
        JLabel labName = new JLabel("用户名：");
        frame.add(labName);


        Dimension dim1 = new Dimension(400,30);
        textField.setPreferredSize(dim1);
        frame.add(textField);

        button1.addActionListener(this);
        button2.addActionListener(this);
        //设置按钮的大小
        Dimension dim2 = new Dimension(100,30);
        //设置按钮的显示内容
        button1.setText("登录");
        button2.setText("取消");
        button1.setSize(dim2);
        button2.setSize(dim2);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanel.add(button1);
        jPanel.add(button2);
        frame.add(jPanel);
        //设置窗体可见
        frame.setVisible(true);

    }

    /**
     * 按钮事件触发
     * @param event
     */
    @Override
    public void actionPerformed(ActionEvent event){
        button2.setText("登录");
        button2.setText("取消");
        if("取消".equals(event.getActionCommand())) {
            System.exit(0);
        }

        if("登录".equals(event.getActionCommand())) {
            if("".equals(textField.getText())) {
                JOptionPane.showMessageDialog(null,"用户名不能为空");
            }
            else {
                frame.setVisible(false);
                ClientUi clientUi=new ClientUi();
                clientUi.getUi(textField.getText());
                clientUi.socket();
            }
        }
    }
}
