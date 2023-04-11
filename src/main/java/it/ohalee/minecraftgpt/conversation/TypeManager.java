package it.ohalee.minecraftgpt.conversation;

import it.ohalee.minecraftgpt.Main;
import it.ohalee.minecraftgpt.Type;
import it.ohalee.minecraftgpt.util.Messages;
import org.bukkit.entity.Player;
import it.ohalee.minecraftgpt.OpenAI;

public class TypeManager {

    public static void startConversation(Main plugin, Player player, Type type) {
        if (Main.CACHE.asMap().containsKey(player)) {
            Main.CACHE.invalidate(player);
            player.sendMessage(Messages.format(plugin.getConfig().getString("command.toggle.disabled")));
            return;
        }

        Main.USER_TYPE.put(player, type);
        Main.CACHE.put(player, new StringBuilder());
        StringBuilder builder = Main.CACHE.getIfPresent(player);
        if (builder == null) builder = new StringBuilder();
        OpenAI.getResponse(plugin.getConfig().getConfigurationSection("chatgpt"), builder, 'Eres Roberta(Ayudante). Estas en server Minecraft Survival 1.19.4. Formato conv.: "+Alejandro: MENSAJE". Responde en Español. IP del servidor: uvalubia.me,tienda:uvalubia.tebex.io,foro:foro.uvalubia.me');
        player.sendMessage(Messages.format(plugin.getConfig().getString("command.toggle.enabled")));
    }

}
