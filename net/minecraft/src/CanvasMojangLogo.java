package net.minecraft.src;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

class CanvasMojangLogo extends Canvas {
   private BufferedImage logo;

   public CanvasMojangLogo() {
      try {
         this.logo = ImageIO.read(PanelCrashReport.class.getResource("/gui/logo.png"));
      } catch (IOException var2) {
      }

      byte var1 = 100;
      this.setPreferredSize(new Dimension(var1, var1));
      this.setMinimumSize(new Dimension(var1, var1));
   }

   public void paint(Graphics var1) {
      super.paint(var1);
      var1.drawImage(this.logo, this.getWidth() / 2 - this.logo.getWidth() / 2, 32, (ImageObserver)null);
   }
}
