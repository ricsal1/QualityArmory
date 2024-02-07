package me.zombie_striker.qg.miscitems;

import me.zombie_striker.customitemmanager.ArmoryBaseObject;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.UUID;

public interface ThrowableItems extends ArmoryBaseObject {

    HashMap<Entity, ThrowableHolder> throwItems = new HashMap<>();

    double getThrowSpeed();

    void setThrowSpeed(double throwspeed);

    class ThrowableHolder {
        private Entity holder;
        private UUID owner;
        private Grenade grenade;

        private Object timer;

        public ThrowableHolder(UUID owner, Entity holder, Grenade grenade) {
            this.holder = holder;
            this.owner = owner;
            this.grenade = grenade;
        }

        public Entity getHolder() {
            return holder;
        }

        public void setHolder(Entity e) {
            this.holder = e;
        }

        public void setTimer(Object bt) {
            this.timer = bt;
        }

        public Object getTimer() {
            return timer;
        }

        public UUID getOwner() {
            return owner;
        }

        public Grenade getGrenade() {
            return grenade;
        }


        public void setGrenade(Grenade grenade) {
            this.grenade = grenade;
        }
    }
}
