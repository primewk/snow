package net.minecraft.src;

import net.minecraft.src.MEDMEX.Client;
import net.minecraft.src.MEDMEX.Modules.Render.ESP;
import net.minecraft.src.MEDMEX.Utils.ChamsUtils;
import org.lwjgl.opengl.GL11;

public class TileEntitySignRenderer extends TileEntitySpecialRenderer {
   private SignModel signModel = new SignModel();

   public void renderTileEntitySignAt(TileEntitySign var1, double var2, double var4, double var6, float var8) {
      Block var9 = var1.getBlockType();
      GL11.glPushMatrix();
      float var10 = 0.6666667F;
      float var12;
      if (var9 == Block.signPost) {
         GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 0.75F * var10, (float)var6 + 0.5F);
         float var11 = (float)(var1.getBlockMetadata() * 360) / 16.0F;
         GL11.glRotatef(-var11, 0.0F, 1.0F, 0.0F);
         this.signModel.signStick.showModel = true;
      } else {
         int var16 = var1.getBlockMetadata();
         var12 = 0.0F;
         if (var16 == 2) {
            var12 = 180.0F;
         }

         if (var16 == 4) {
            var12 = 90.0F;
         }

         if (var16 == 5) {
            var12 = -90.0F;
         }

         GL11.glTranslatef((float)var2 + 0.5F, (float)var4 + 0.75F * var10, (float)var6 + 0.5F);
         GL11.glRotatef(-var12, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(0.0F, -0.3125F, -0.4375F);
         this.signModel.signStick.showModel = false;
      }

      this.bindTextureByName("/item/sign.png");
      GL11.glPushMatrix();
      GL11.glScalef(var10, -var10, -var10);
      this.signModel.func_887_a();
      if (ESP.instance.isEnabled() && Client.settingsmanager.getSettingByName("Misc ESP").getValBoolean()) {
         ChamsUtils.renderOne();
         this.signModel.func_887_a();
         ChamsUtils.renderTwo();
         this.signModel.func_887_a();
         ChamsUtils.renderThree();
         ChamsUtils.renderFour();
         GL11.glColor4f(0.078F, 1.0F, 0.921F, 1.0F);
         this.signModel.func_887_a();
         ChamsUtils.renderFive();
      }

      GL11.glPopMatrix();
      FontRenderer var17 = this.getFontRenderer();
      var12 = 0.016666668F * var10;
      GL11.glTranslatef(0.0F, 0.5F * var10, 0.07F * var10);
      GL11.glScalef(var12, -var12, var12);
      GL11.glNormal3f(0.0F, 0.0F, -1.0F * var12);
      GL11.glDepthMask(false);
      byte var13 = 0;

      for(int var14 = 0; var14 < var1.signText.length; ++var14) {
         String var15 = var1.signText[var14];
         if (var14 == var1.lineBeingEdited) {
            var15 = "> " + var15 + " <";
            var17.drawString(var15, (double)(-var17.getStringWidth(var15) / 2), (double)(var14 * 10 - var1.signText.length * 5), var13);
         } else {
            var17.drawString(var15, (double)(-var17.getStringWidth(var15) / 2), (double)(var14 * 10 - var1.signText.length * 5), var13);
         }
      }

      GL11.glDepthMask(true);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glPopMatrix();
   }

   public void renderTileEntityAt(TileEntity var1, double var2, double var4, double var6, float var8) {
      this.renderTileEntitySignAt((TileEntitySign)var1, var2, var4, var6, var8);
   }
}
