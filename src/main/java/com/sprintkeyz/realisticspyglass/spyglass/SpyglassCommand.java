package com.sprintkeyz.realisticspyglass.spyglass;

import com.sprintkeyz.realisticspyglass.RealisticSpyglass;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpyglassCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spyglass")) {
            if (args.length == 1) {
                String first = args[0];
                if (first.equalsIgnoreCase("debug")) {
                    boolean debug = RealisticSpyglass.getInstance().getConfig().getBoolean("debug-messages");
                    if (!debug) {
                        sender.sendMessage(ChatColor.GREEN + "Enabled debug messages!");
                        RealisticSpyglass.getInstance().getConfig().set("debug-messages", true);
                    }

                    else {
                        RealisticSpyglass.getInstance().getConfig().set("debug-messages", false);
                        sender.sendMessage(ChatColor.RED + "Disabled debug messages!");
                    }
                }

                else if (first.equalsIgnoreCase("toggle")) {
                    boolean enabled = RealisticSpyglass.getInstance().getConfig().getBoolean("enabled");
                    if (!enabled) {
                        sender.sendMessage(ChatColor.GREEN + "Enabled realistic spyglasses!");
                        RealisticSpyglass.getInstance().getConfig().set("enabled", true);
                    }

                    else {
                        sender.sendMessage(ChatColor.RED + "Disabled realistic spyglasses!");
                        RealisticSpyglass.getInstance().getConfig().set("enabled", false);
                    }
                }
            }

            else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("blindtime")) {
                    try {
                        int blindTime = Integer.parseInt(args[1]);
                        if ((blindTime + "").contains("-") && blindTime != -1) {
                            blindTime = Math.abs(blindTime);
                            sender.sendMessage(ChatColor.YELLOW + "Your blind time was negative, so it was corrected!");
                            sender.sendMessage(ChatColor.GREEN + "Set blind time to " + blindTime + "s!");
                        }

                        else if (blindTime == -1) {
                            blindTime = Integer.MAX_VALUE;
                            sender.sendMessage(ChatColor.GREEN + "Enabled permanent blindness!");
                        }

                        else sender.sendMessage(ChatColor.GREEN + "Set blind time to " + blindTime + "s!");

                        RealisticSpyglass.getInstance().getConfig().set("blind-time", blindTime);
                    }

                    catch (Exception e) {
                        sender.sendMessage(ChatColor.RED + "'" + args[1] + "' is not a valid integer!");
                    }
                }
            }
        }
        return true;
    }
}
