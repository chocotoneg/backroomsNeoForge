package net.mc3699.backrooms.entity.behavior;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;

public class StalkGoal extends Goal {

    public StalkGoal(Monster mob) {
        this.mob = mob;
    }


    private final Monster mob;
    private Player targetPlayer;

    @Override
    public boolean canUse() {
        targetPlayer = mob.level().getNearestPlayer(mob, 100d);
        return targetPlayer != null && targetPlayer.isAlive();
    }

    @Override
    public void start() {
        mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        if(targetPlayer != null)
        {
            mob.getLookControl().setLookAt(targetPlayer, 10f,10f);
            PathNavigation navigation = mob.getNavigation();
            navigation.moveTo(targetPlayer, 0.4f);
        } else {
            mob.getNavigation().stop();
        }
    }
}
