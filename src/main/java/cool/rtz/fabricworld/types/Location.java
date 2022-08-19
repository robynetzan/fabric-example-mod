package cool.rtz.fabricworld.types;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class Location {
    public final Vec3d position;
    public final ServerWorld world;
    
    public Location(ServerPlayerEntity player) {
        this.world = player.getWorld();
        this.position = Vec3d.ZERO.add(player.getPos());
    }

    public Location(Vec3d position, ServerWorld world) {
        this.position = position;
        this.world = world;
    }
}
