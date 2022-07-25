package de.ugur.jdaexample;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class SlashCommand extends ListenerAdapter {

    private final JDA jda;

    public SlashCommand(JDA jda, String guildId) {
        this.jda = jda;
        registerCommands(guildId);
        jda.addEventListener(this);
    }

    private void registerCommands(String guildId) {
        Guild guild = jda.getGuildById(guildId);

        guild.upsertCommand("test", "test").setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR)).queue(); //You can remove the .setDefaultPermissions() if you want :D
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("test")) { // Checks if the / Command equals test
            event.reply("TEST")
                    .addActionRows(ActionRow.of(Button.primary("test", "test"))) // A simple Button
                    .queue();
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getComponentId().equals("test")) {
            event.editMessage("Buttons :3").setActionRows(ActionRow.of(Button.primary("test", "test").asDisabled())).queue(); // Edits the message and disables the button after clicking at it
        }
    }
}
