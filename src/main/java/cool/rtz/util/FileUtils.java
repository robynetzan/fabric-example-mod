package cool.rtz.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cool.rtz.fabricworld.FabricWorld;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.WorldSavePath;

public class FileUtils<T> {

	final MinecraftServer server;

	public FileUtils (MinecraftServer server) {
		this.server = server;
	}
	
	private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .disableHtmlEscaping()
            .create();

	private void writeJson(OutputStreamWriter writer, T data) {
        GSON.toJson(data, writer);
    }

	private File getFile(String filename) {
        return server.getSavePath(WorldSavePath.ROOT)
                .resolve("data")
                .resolve(filename + ".json")
                .toFile();
    }

	private OutputStreamWriter getWriter(String filename) throws FileNotFoundException {
        return new OutputStreamWriter(new FileOutputStream(this.getFile(filename)));
    }

	public void write(String filename, T data) {
        FabricWorld.LOGGER.trace("writing");
        try {
            OutputStreamWriter writer = getWriter(filename);
            writeJson(writer, data);
            writer.close();
        } catch (Exception e) {
            FabricWorld.LOGGER.error(e.toString());
        }
    }
}
