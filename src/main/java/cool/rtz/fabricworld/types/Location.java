package cool.rtz.fabricworld.types;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public NbtCompound toNbt() {
        return this.createNbt(new NbtCompound());
    }

    public NbtCompound createNbt(NbtCompound tag) {
        tag.putString("WorldRegistryKey", world.getRegistryKey().getValue().toString());
        tag.putDouble("x", position.x);
        tag.putDouble("y", position.y);
        tag.putDouble("z", position.z);
        return tag;
    }

    public String toJson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting(); 
        Gson gson = builder.create(); 
        String jsonString = gson.toJson(this);
        return jsonString;
    }
}
