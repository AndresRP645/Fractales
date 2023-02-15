package Fractales;

import static design_package.design_class.design_method;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

public class Fractal extends JFrame {

    private Copo copo;
    private Dragon dragon;
    private int nivel = 0;
    private Color c = Color.black;
    private boolean delete = false;
    private Graphics g;
    
    public Fractal() {
        initComponents();
        design_method(this, "CKG.png", "Reconocimiento de patrones");
        drawnpn.setSelected(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fractalGroup = new javax.swing.ButtonGroup();
        jMenuBar1 = new javax.swing.JMenuBar();
        Delete = new javax.swing.JMenu();
        draw = new javax.swing.JMenuItem();
        erase = new javax.swing.JMenuItem();
        color = new javax.swing.JMenuItem();
        drawnpn = new javax.swing.JCheckBoxMenuItem();
        fractalSelected = new javax.swing.JMenu();
        CopoNieve = new javax.swing.JRadioButtonMenuItem();
        curvaDragon = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);

        Delete.setText("Opciones");

        draw.setText("Dibujar");
        draw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawActionPerformed(evt);
            }
        });
        Delete.add(draw);

        erase.setText("Borrar");
        erase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eraseActionPerformed(evt);
            }
        });
        Delete.add(erase);

        color.setText("Color");
        color.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorActionPerformed(evt);
            }
        });
        Delete.add(color);

        drawnpn.setSelected(true);
        drawnpn.setText("Dibujar npn");
        Delete.add(drawnpn);

        jMenuBar1.add(Delete);

        fractalSelected.setText("Fractal");
        fractalGroup.add(fractalSelected);

        fractalGroup.add(CopoNieve);
        CopoNieve.setSelected(true);
        CopoNieve.setText("Copo de Nieve");
        fractalSelected.add(CopoNieve);

        fractalGroup.add(curvaDragon);
        curvaDragon.setText("Curva del Dragón");
        fractalSelected.add(curvaDragon);

        jMenuBar1.add(fractalSelected);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 384, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void drawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawActionPerformed
        if (CopoNieve.isSelected()) {
            copo = new Copo();
            copo.ban = JOptionPane.showConfirmDialog(null, "¿Ejecutar copo complejo?") == 0;
            copo.start();
        }
        if (curvaDragon.isSelected()) {
            dragon = new Dragon();
            dragon.start();
        }
    }//GEN-LAST:event_drawActionPerformed

    private void colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorActionPerformed
        c = JColorChooser.showDialog(null, "Selecciona color", c);
    }//GEN-LAST:event_colorActionPerformed

    private void eraseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eraseActionPerformed
        delete = false;
        repaint();
    }//GEN-LAST:event_eraseActionPerformed
    
    @Override
    public void paint(Graphics g) {
        this.g=g;
        if (delete) {
            if (CopoNieve.isSelected()) {
                g.setColor(c);
                copo.copoSimple(g);
                copo = new Copo();
            }
            else if(curvaDragon.isSelected()){
                g.setColor(c);
                dragon.dragon(g);
                copo = new Copo();
            }
                
        } else {
            super.paint(g);
        }
    }

    class Copo extends Thread implements Runnable {

        private boolean ban = false;
        private int x1 = 100;
        private int y1 = 327;

        private int x2 = 400;
        private int y2 = 327;

        private double sin60 = Math.sin(Math.toRadians(60));

        private int x3 = 250;
        private int y3 = (int) (y1 + (300 * Math.sin(Math.toRadians(-60))));
       
        public Copo() {
            delete = true;
        }

        private void copoSimple(Graphics g) {
            CopoRecursivo(g, nivel, x1, y1, x2, y2);
            CopoRecursivo(g, nivel, x2, y2, x3, y3);
            CopoRecursivo(g, nivel, x3, y3, x1, y1);
            if (ban) {
                copoComplejo(g);
            }
        }

        private void copoComplejo(Graphics g) {
            CopoRecursivo(g, nivel, x2, y2, x1, y1);
            CopoRecursivo(g, nivel, x3, y3, x2, y2);
            CopoRecursivo(g, nivel, x1, y1, x3, y3);

            int x1 = 250;
            int y1 = 413;

            int x2 = 399;
            int y2 = 153;

            int x3 = 100;
            int y3 = 153;

            CopoRecursivo(g, nivel, x1, y1, x2, y2);
            CopoRecursivo(g, nivel, x2, y2, x3, y3);
            CopoRecursivo(g, nivel, x3, y3, x1, y1);
            CopoRecursivo(g, nivel, x2, y2, x1, y1);
            CopoRecursivo(g, nivel, x3, y3, x2, y2);
            CopoRecursivo(g, nivel, x1, y1, x3, y3);
        }

        private void CopoRecursivo(Graphics g, int i, int xi, int yi, int xf, int yf) {
            if (i <= 0) {
                g.drawLine(xi, yi, xf, yf);
            } else {
                i--;

                int dx = (xf - xi);
                int dy = (yf - yi);

                int dx13 = (xf - xi) / 3;
                int dy13 = (yf - yi) / 3;

                int x1 = xi + dx13;
                int y1 = yi + dy13;

                int x2 = (int) (xi + (dx / 2) - (dy13 * sin60));
                int y2 = (int) (yi + (dy / 2) + (dx13 * sin60));

                int x3 = xf - dx13;
                int y3 = yf - dy13;

                CopoRecursivo(g, i, xi, yi, x1, y1);

                CopoRecursivo(g, i, x1, y1, x2, y2);

                CopoRecursivo(g, i, x2, y2, x3, y3);

                CopoRecursivo(g, i, x3, y3, xf, yf);

            }
        }

        @Override
        public void run() {
            try {
                String string = JOptionPane.showInputDialog("Ingresa el nivel de recursividad");
                int a = Integer.parseInt(string);
                if (drawnpn.isSelected()) {
                    for (nivel = 0; nivel <= a; nivel++) {
                        delete = false;
                        repaint();
                        Thread.sleep(100);
                        delete = true;
                        repaint();

                        Thread.sleep(1000);
                    }
                } else {
                    nivel = Integer.parseInt(string);
                    delete = false;
                    repaint();
                    Thread.sleep(2000);
                    delete = true;
                    repaint();
                }
            } catch (Exception ex) {
            }
        }
    }
    
    class Dragon extends Thread implements Runnable{
        
        private void DragonRecursivo(Graphics g, int n, int x1, int y1, int x2, int y2) {
        if (n <= 0) {
            g.drawLine(x1, y1, x2, y2);
        } else {
            n--;
            int dx = (x2 - x1) / 4;
            int dy = (y2 - y1) / 4;

            int sx1 = x1 + dx;
            int sy1 = y1 + dy;

            int sx2 = x1 + dx - dy;
            int sy2 = y1 + dy - dx;

            int sx3 = x1 + 2 * dx - dy;
            int sy3 = y1 + 2 * dy - dx;

            int sx4 = x1 + 2 * dx - dy;
            int sy4 = y1 + 2 * dy;

            int sx5 = x1 + 2 * dx;
            int sy5 = y1 + 2 * dy;

            int sx6 = x1 + 2 * dx + dy;
            int sy6 = y1 + 2 * dy + dx;

            int sx7 = x1 + 3 * dx + dy;
            int sy7 = y1 + 3 * dy + dx;

            int sx8 = x1 + 3 * dx;
            int sy8 = y1 + 3 * dy;

            /*1*/ DragonRecursivo(g, n, x1, y1, sx1, sy1);

            /*2*/ DragonRecursivo(g, n, sx1, sy1, sx2, sy2);

            /*3*/ DragonRecursivo(g, n, sx2, sy2, sx3, sy3);

            /*4*/ DragonRecursivo(g, n, sx3, sy3, sx4, sy4);

            /*5*/ DragonRecursivo(g, n, sx4, sy4, sx5, sy5);

            /*6*/ DragonRecursivo(g, n, sx5, sy5, sx6, sy6);

            /*7*/ DragonRecursivo(g, n, sx6, sy6, sx7, sy7);

            /*8*/ DragonRecursivo(g, n, sx7, sy7, sx8, sy8);

            /*9*/ DragonRecursivo(g, n, sx8, sy8, x2, y2);
        }
    }
        
        public void dragon(Graphics g){
            DragonRecursivo(g, nivel, 100, 250, 400, 250);
        }
        
        public void run() {
            try {
                String string = JOptionPane.showInputDialog("Ingresa el nivel de recursividad");
                int a = Integer.parseInt(string);
                if (drawnpn.isSelected()) {
                    for (nivel = 0; nivel <= a; nivel++) {
                        delete = false;
                        repaint();
                        Thread.sleep(100);
                        delete = true;
                        repaint();

                        Thread.sleep(1000);
                    }
                } else {
                    nivel = Integer.parseInt(string);
                    delete = false;
                    repaint();
                    Thread.sleep(2000);
                    delete = true;
                    repaint();
                }
            } catch (Exception ex) {
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButtonMenuItem CopoNieve;
    private javax.swing.JMenu Delete;
    private javax.swing.JMenuItem color;
    private javax.swing.JRadioButtonMenuItem curvaDragon;
    private javax.swing.JMenuItem draw;
    private javax.swing.JCheckBoxMenuItem drawnpn;
    private javax.swing.JMenuItem erase;
    private javax.swing.ButtonGroup fractalGroup;
    private javax.swing.JMenu fractalSelected;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
