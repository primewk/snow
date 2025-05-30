package net.minecraft.src;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.src.MEDMEX.Client;
import net.minecraft.src.MEDMEX.Modules.Render.ESP;
import net.minecraft.src.MEDMEX.Modules.Render.Nametags;
import net.minecraft.src.MEDMEX.Modules.Render.NoRender;
import net.minecraft.src.MEDMEX.Utils.ChamsUtils;
import org.lwjgl.opengl.GL11;

public class RenderLiving extends Render {
   protected ModelBase mainModel;
   protected ModelBase renderPassModel;

   public RenderLiving(ModelBase var1, float var2) {
      this.mainModel = var1;
      this.shadowSize = var2;
   }

   public void setRenderPassModel(ModelBase var1) {
      this.renderPassModel = var1;
   }

   public void doRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      if (!(var1 instanceof EntityPlayer) || !NoRender.instance.isEnabled() || !Client.settingsmanager.getSettingByName("NoRender Players").getValBoolean() || var1 == Minecraft.theMinecraft.thePlayer) {
         if (!(var1 instanceof EntityAnimal) || !NoRender.instance.isEnabled() || !Client.settingsmanager.getSettingByName("NoRender Animals").getValBoolean()) {
            if (!(var1 instanceof EntityMob) || !NoRender.instance.isEnabled() || !Client.settingsmanager.getSettingByName("NoRender Mobs").getValBoolean()) {
               GL11.glPushMatrix();
               GL11.glDisable(2884);
               this.mainModel.onGround = this.func_167_c(var1, var9);
               if (this.renderPassModel != null) {
                  this.renderPassModel.onGround = this.mainModel.onGround;
               }

               this.mainModel.isRiding = var1.isRiding();
               if (this.renderPassModel != null) {
                  this.renderPassModel.isRiding = this.mainModel.isRiding;
               }

               try {
                  float var10 = var1.prevRenderYawOffset + (var1.renderYawOffset - var1.prevRenderYawOffset) * var9;
                  float var11 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var9;
                  float var12 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var9;
                  this.func_22012_b(var1, var2, var4, var6);
                  float var13 = this.func_170_d(var1, var9);
                  this.rotateCorpse(var1, var13, var10, var9);
                  float var14 = 0.0625F;
                  GL11.glEnable(32826);
                  GL11.glScalef(-1.0F, -1.0F, 1.0F);
                  this.preRenderCallback(var1, var9);
                  GL11.glTranslatef(0.0F, -24.0F * var14 - 0.0078125F, 0.0F);
                  float var15 = var1.prevLegYaw + (var1.legYaw - var1.prevLegYaw) * var9;
                  float var16 = var1.legSwing - var1.legYaw * (1.0F - var9);
                  if (var15 > 1.0F) {
                     var15 = 1.0F;
                  }

                  this.loadDownloadableImageTexture(var1.skinUrl, var1.getEntityTexture());
                  GL11.glEnable(3008);
                  this.mainModel.setLivingAnimations(var1, var16, var15, var9);
                  this.mainModel.render(var16, var15, var13, var11 - var10, var12, var14);
                  if (ESP.instance.isEnabled() && Client.settingsmanager.getSettingByName("ESP Mode").getValString().equalsIgnoreCase("Outline") && ESP.instance.shouldRenderEntity(var1)) {
                     Color c = ESP.instance.getColor(var1);
                     this.mainModel.render(var16, var15, var13, var11 - var10, var12, var14);
                     ChamsUtils.renderOne();
                     this.mainModel.render(var16, var15, var13, var11 - var10, var12, var14);
                     ChamsUtils.renderTwo();
                     this.mainModel.render(var16, var15, var13, var11 - var10, var12, var14);
                     ChamsUtils.renderThree();
                     ChamsUtils.renderFour();
                     GL11.glColor4f((float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F, (float)c.getAlpha() / 255.0F);
                     this.mainModel.render(var16, var15, var13, var11 - var10, var12, var14);
                     ChamsUtils.renderFive();
                  }

                  for(int var17 = 0; var17 < 4; ++var17) {
                     if (this.shouldRenderPass(var1, var17, var9)) {
                        this.renderPassModel.render(var16, var15, var13, var11 - var10, var12, var14);
                        GL11.glDisable(3042);
                        GL11.glEnable(3008);
                     }
                  }

                  this.renderEquippedItems(var1, var9);
                  float var25 = var1.getEntityBrightness(var9);
                  int var18 = this.getColorMultiplier(var1, var25, var9);
                  if (ESP.instance.isEnabled() && Client.settingsmanager.getSettingByName("ESP Mode").getValString().equalsIgnoreCase("Filled") && ESP.instance.shouldRenderEntity(var1)) {
                     GL11.glPushMatrix();
                     GL11.glPushAttrib(1048575);
                     GL11.glPolygonMode(1028, 6913);
                     GL11.glDisable(3553);
                     GL11.glDisable(2896);
                     GL11.glDisable(2929);
                     GL11.glEnable(2848);
                     GL11.glEnable(3042);
                     Color c = ESP.instance.getColor(var1);
                     GL11.glColor4f((float)c.getRed() / 255.0F, (float)c.getGreen() / 255.0F, (float)c.getBlue() / 255.0F, (float)c.getAlpha() / 255.0F);
                     this.mainModel.render(var16, var15, var13, var11 - var10, var12, var14);
                     GL11.glPopAttrib();
                     GL11.glPopMatrix();
                  }

                  if ((var18 >> 24 & 255) > 0 || var1.hurtTime > 0 || var1.deathTime > 0) {
                     GL11.glDisable(3553);
                     GL11.glDisable(3008);
                     GL11.glEnable(3042);
                     GL11.glBlendFunc(770, 771);
                     GL11.glDepthFunc(514);
                     if (var1.hurtTime > 0 || var1.deathTime > 0) {
                        GL11.glColor4f(var25, 0.0F, 0.0F, 0.4F);
                        this.mainModel.render(var16, var15, var13, var11 - var10, var12, var14);

                        for(int var19 = 0; var19 < 4; ++var19) {
                           if (this.func_27005_b(var1, var19, var9)) {
                              GL11.glColor4f(var25, 0.0F, 0.0F, 0.4F);
                              this.renderPassModel.render(var16, var15, var13, var11 - var10, var12, var14);
                           }
                        }
                     }

                     if ((var18 >> 24 & 255) > 0) {
                        float var26 = (float)(var18 >> 16 & 255) / 255.0F;
                        float var20 = (float)(var18 >> 8 & 255) / 255.0F;
                        float var21 = (float)(var18 & 255) / 255.0F;
                        float var22 = (float)(var18 >> 24 & 255) / 255.0F;
                        GL11.glColor4f(var26, var20, var21, var22);
                        this.mainModel.render(var16, var15, var13, var11 - var10, var12, var14);

                        for(int var23 = 0; var23 < 4; ++var23) {
                           if (this.func_27005_b(var1, var23, var9)) {
                              GL11.glColor4f(var26, var20, var21, var22);
                              this.renderPassModel.render(var16, var15, var13, var11 - var10, var12, var14);
                           }
                        }
                     }

                     GL11.glDepthFunc(515);
                     GL11.glDisable(3042);
                     GL11.glEnable(3008);
                     GL11.glEnable(3553);
                  }

                  GL11.glDisable(32826);
               } catch (Exception var24) {
                  var24.printStackTrace();
               }

               GL11.glEnable(2884);
               GL11.glPopMatrix();
               this.passSpecialRender(var1, var2, var4, var6);
            }
         }
      }
   }

   protected void func_22012_b(EntityLiving var1, double var2, double var4, double var6) {
      GL11.glTranslatef((float)var2, (float)var4, (float)var6);
   }

   protected void rotateCorpse(EntityLiving var1, float var2, float var3, float var4) {
      GL11.glRotatef(180.0F - var3, 0.0F, 1.0F, 0.0F);
      if (var1.deathTime > 0) {
         float var5 = ((float)var1.deathTime + var4 - 1.0F) / 20.0F * 1.6F;
         var5 = MathHelper.sqrt_float(var5);
         if (var5 > 1.0F) {
            var5 = 1.0F;
         }

         GL11.glRotatef(var5 * this.getDeathMaxRotation(var1), 0.0F, 0.0F, 1.0F);
      }

   }

   protected float func_167_c(EntityLiving var1, float var2) {
      return var1.getSwingProgress(var2);
   }

   protected float func_170_d(EntityLiving var1, float var2) {
      return (float)var1.ticksExisted + var2;
   }

   protected void renderEquippedItems(EntityLiving var1, float var2) {
   }

   protected boolean func_27005_b(EntityLiving var1, int var2, float var3) {
      return this.shouldRenderPass(var1, var2, var3);
   }

   protected boolean shouldRenderPass(EntityLiving var1, int var2, float var3) {
      return false;
   }

   protected float getDeathMaxRotation(EntityLiving var1) {
      return 90.0F;
   }

   protected int getColorMultiplier(EntityLiving var1, float var2, float var3) {
      return 0;
   }

   protected void preRenderCallback(EntityLiving var1, float var2) {
   }

   protected void passSpecialRender(EntityLiving var1, double var2, double var4, double var6) {
      if (Minecraft.isDebugInfoEnabled()) {
         this.renderLivingLabel(var1, Integer.toString(var1.entityId), var2, var4, var6, 64);
      }

   }

   protected void renderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9) {
      if (!Nametags.instance.isEnabled() || !(var1 instanceof EntityPlayer)) {
         float var10 = var1.getDistanceToEntity(this.renderManager.livingPlayer);
         if (var10 <= (float)var9) {
            FontRenderer var11 = this.getFontRendererFromRenderManager();
            float var12 = 1.6F;
            float var13 = 0.016666668F * var12;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)var3 + 0.0F, (float)var5 + 2.3F, (float)var7);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-RenderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(RenderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-var13, -var13, var13);
            GL11.glDisable(2896);
            GL11.glDepthMask(false);
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            Tessellator var14 = Tessellator.instance;
            byte var15 = 0;
            if (var2.equals("deadmau5")) {
               var15 = -10;
            }

            GL11.glDisable(3553);
            var14.startDrawingQuads();
            int var16 = var11.getStringWidth(var2) / 2;
            var14.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
            var14.addVertex((double)(-var16 - 1), (double)(-1 + var15), 0.0D);
            var14.addVertex((double)(-var16 - 1), (double)(8 + var15), 0.0D);
            var14.addVertex((double)(var16 + 1), (double)(8 + var15), 0.0D);
            var14.addVertex((double)(var16 + 1), (double)(-1 + var15), 0.0D);
            var14.draw();
            GL11.glEnable(3553);
            var11.drawString(var2, (double)(-var11.getStringWidth(var2) / 2), (double)var15, 553648127);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            var11.drawString(var2, (double)(-var11.getStringWidth(var2) / 2), (double)var15, -1);
            GL11.glEnable(2896);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
         }

      }
   }

   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRenderLiving((EntityLiving)var1, var2, var4, var6, var8, var9);
   }
}
