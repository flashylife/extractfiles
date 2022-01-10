package xyz.floatinglife.main;

import xyz.floatinglife.service.FileCopyService;
import xyz.floatinglife.util.TextAreaOperationsUtils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 提取资源界面
 *
 * @author flashylife
 */
public class ExtractClassFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 4806301801341222332L;

    /**
     * 界面长度
     */
    private static final int WIDTH = 700;
    /**
     * 界面宽度
     */
    private static final int HEIGHT = 400;
    /**
     * 输出默认值
     */
    private static String outPut;
    /**
     * 输入默认值
     */
    private static String intPut;
    /**
     * 标题切换组件
     */
    JTabbedPane jtp;
    /**
     * 面板
     */
    final JPanel jp1, jp2, jp3;
    /**
     * 输入默认值
     */
    JScrollPane jsp;
    /**
     * 文本域
     */
    JTextArea jta1, jta2;
    /**
     * 提示框
     */
    JTextField jtf;
    /**
     * 点我提取按钮标签
     */
    JLabel jl1;

    /**
     * 构造方法
     * 默认初始化
     */
    public ExtractClassFrame() {
        jtp = new JTabbedPane();

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jta1 = new JTextArea();
        jta2 = new JTextArea();

        jsp = new JScrollPane(jta2);

        jtf = new JTextField();

        jl1 = new JLabel("点我提取资源", JLabel.CENTER);
    }

    /**
     * 设置属性并事件监听
     */
    public void setPro() {
        this.jta1.setFont(new Font("等线", 0, 15));
        this.jta1.setText(outPut);
        this.jta2.setText(intPut);
        this.jta2.setFont(new Font("等线", 0, 15));
        this.jtf.setEditable(false);
        this.jl1.setFont(new Font("楷体", Font.BOLD, 18));
        this.jl1.setForeground(Color.BLUE);
        this.jp1.setLayout(new BorderLayout());
        this.jp2.setLayout(new BorderLayout());
        this.jp1.add(jta1);
        this.jp2.add(jsp);
        this.jl1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.jl1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String str = "";
                FileCopyService service = FileCopyService.getCopyFileService();
                service.setOutText(jta1.getText());
                service.setInText(jta2.getText());
                try {
                    str = service.execute();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                jtf.setText(str);
                TextAreaOperationsUtils.getInstance().persistence(jta1.getText(), "output.ini");
                TextAreaOperationsUtils.getInstance().persistence(jta2.getText(), "input.ini");
            }
        });
        this.jp3.setLayout(new GridLayout(1, 2));
        this.jp3.add(jl1);
        this.jp3.add(jtf);
        this.jtp.add("输出路径配置", jp1);
        this.jtp.add("输入路径配置", jp2);
        this.add(jtp);
        this.add(jp3, BorderLayout.SOUTH);
    }

    /**
     * 设置默认值
     */
    private void setText() {
        outPut = TextAreaOperationsUtils.getInstance().init("output.ini");
        intPut = TextAreaOperationsUtils.getInstance().init("input.ini");
    }

    /**
     * 启动方法
     */
    public void start() {
        setText();
        setPro();
        this.setTitle("extractfiles by flashylife");
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        new ExtractClassFrame().start();
    }
}