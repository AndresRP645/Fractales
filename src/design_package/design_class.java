package design_package;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class design_class{
    public static void design_method(JFrame jFrame,String Logo,String Title){
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setTitle(Title);
        jFrame.setIconImage(new ImageIcon(Logo).getImage());
        ((JPanel)jFrame.getContentPane()).setOpaque(false); 
        jFrame.repaint();
   }
}
