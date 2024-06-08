
import javax.swing.*;
import java.awt.*;

public class EstilosFrame extends JFrame {
    protected JPanel contentPane;

    public EstilosFrame(String title) {
        setForeground(new Color(255, 255, 255));
        setResizable(false);
        setType(Type.POPUP);
        setIconImage(Toolkit.getDefaultToolkit().getImage("img\\icono_trofeo.png"));
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 420);
        contentPane = new JPanel();
        contentPane.setBorder(UIManager.getBorder("TitledBorder.border"));
        contentPane.setPreferredSize(new Dimension(600, 420));
        contentPane.setBackground(new Color(152, 180, 216));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
    }

    protected void configurarBoton(JButton boton, int x, int y, int width, int height) {
        boton.setFont(new Font("Tahoma", Font.BOLD, 10));
        boton.setBounds(x, y, width, height);
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(Color.LIGHT_GRAY);
                boton.setForeground(Color.WHITE);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(Color.WHITE);
                boton.setForeground(Color.BLACK);
            }
        });
        contentPane.add(boton);
    }

    protected void agregarEtiquetaConImagen(String imagePath, int x, int y, int width, int height) {
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(imagePath);
        label.setIcon(imageIcon);
        label.setBounds(x, y, width, height);
        contentPane.add(label);
    }
    protected void agregarEtiqueta(String titulo,int x, int y, int width, int height) {
        JLabel label = new JLabel(titulo);
        label.setForeground(new Color(255, 255, 255));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBounds(x, y, width, height);
        contentPane.add(label);
    }
}

