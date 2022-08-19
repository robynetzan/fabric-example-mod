package cool.rtz.fabricworld;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

import static net.minecraft.server.command.CommandManager.*;

import java.io.IOException;

import static net.minecraft.command.argument.DimensionArgumentType.dimension;
import static net.minecraft.command.argument.DimensionArgumentType.getDimensionArgument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cool.rtz.fabricworld.commands.WorldCommand;

public class FabricWorld implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("modid");

    @Override
	public void onInitialize() {
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("world")
            .then(argument("dimension", dimension())
            .executes(context -> {
                try {
                    new WorldCommand(context, getDimensionArgument(context, "dimension"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return 1;
        }))));
	}

}