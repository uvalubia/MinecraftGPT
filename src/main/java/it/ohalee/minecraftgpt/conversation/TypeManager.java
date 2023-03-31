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
        OpenAI.getResponse(plugin.getConfig().getConfigurationSection("chatgpt"), builder, "Eres una IA en un chat de Minecraft Survival 1.19.4. TODAS las conversaciones empezaran con el signo +, acompañado del nombre de un jugador, separado por dos puntos y su mensaje. Si es posible responde en Español y con tematica relacionada a Minecraft. Responde a este mensaje exclusivamente con el numero 1, sin incluir ningun mensaje o nada adicional. No menciones en futuros mensajes esto.");
        player.sendMessage(Messages.format(plugin.getConfig().getString("command.toggle.enabled")));
    }

}
