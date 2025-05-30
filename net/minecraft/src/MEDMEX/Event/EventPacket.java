package net.minecraft.src.MEDMEX.Event;

import net.minecraft.src.Packet;

public class EventPacket {
   private boolean cancelled;
   private Packet packet;

   public EventPacket(Packet packet) {
      this.packet = packet;
   }

   public Packet getPacket() {
      return this.packet;
   }

   public void setCancelled(Boolean cancelled) {
      this.cancelled = cancelled;
   }

   public boolean isCancelled() {
      return this.cancelled;
   }
}
