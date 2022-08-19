package cool.rtz.fabricworld.commands;

import java.io.IOException;  // Import the IOException class to handle errors
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

import cool.rtz.fabricworld.types.Location;
import cool.rtz.util.FileUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class WorldCommand {
    final CommandContext<ServerCommandSource> context;
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    public WorldCommand(CommandContext<ServerCommandSource> context, ServerWorld dimension) throws CommandSyntaxException, IOException {
        this.context = context;
        ServerCommandSource source = context.getSource();
        final ServerPlayerEntity player = source.getPlayer();
        if (player == null) {
            throw new SimpleCommandExceptionType(Text.translatable("fabricworld.error.mustBePlayer")).create();
        }

        MinecraftServer server = source.getServer();

        // Save current location to a .json
        Location location = new Location(player.getPos(), player.getWorld());
        FileUtils<Location> utils = new FileUtils<Location>(server);
        utils.write(player.getUuidAsString(), location);

        // Move player
        player.moveToWorld(dimension);
        
        // Load previous location from this world to .dat

        // Teleport to that location
        // player.setPosition(vec3d);
    }
}
