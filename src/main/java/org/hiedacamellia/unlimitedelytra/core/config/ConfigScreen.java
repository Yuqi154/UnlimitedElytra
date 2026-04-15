package org.hiedacamellia.unlimitedelytra.core.config;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ConfigScreen extends Screen {

    EditBox AirResistance;
    EditBox FireWorkAcceleration;

    public ConfigScreen() {
        super(Component.translatable("config.unlimitedelytra.title"));
    }

    @Override
    protected void init() {
        super.init();

        this.addRenderableWidget(new Button.Builder(Component.literal(Config.EnableElytra.get().toString()),e -> {
            Config.EnableElytra.set(!Config.EnableElytra.get());
            e.setMessage(Component.literal(Config.EnableElytra.get().toString()));
        }).bounds(this.width - 60, 60,40,15)
                .tooltip(Tooltip.create(Component.translatable("config.unlimitedelytra.enableelytra.desc"))).build());

        this.addRenderableWidget(new Button.Builder(Component.literal(Config.InfiniteFireWorkAcceleration.get().toString()),e -> {
            Config.InfiniteFireWorkAcceleration.set(!Config.InfiniteFireWorkAcceleration.get());
            e.setMessage(Component.literal(Config.InfiniteFireWorkAcceleration.get().toString()));
        }).bounds(this.width - 60, 100,40,15)
                .tooltip(Tooltip.create(Component.translatable("config.unlimitedelytra.infinitefireworkacceleration.desc"))).build());


        this.addRenderableWidget(new Button.Builder(Component.translatable("config.unlimitedelytra.comfirm"),e -> {
            onClose();
        }).bounds(this.width/2 - 20  , this.height-30,40,15).build());

        AirResistance = new EditBox(this.font, this.width  - 60, 80,40,15, Component.literal(Config.AirResistance.get().toString()));
        FireWorkAcceleration = new EditBox(this.font, this.width  - 60, 120,40,15, Component.literal(Config.FireWorkAcceleration.get().toString()));
        AirResistance.setMaxLength(10);
        FireWorkAcceleration.setMaxLength(10);
        AirResistance.setValue(Config.AirResistance.get().toString());
        FireWorkAcceleration.setValue(Config.FireWorkAcceleration.get().toString());
        this.addRenderableWidget(AirResistance);
        this.addRenderableWidget(FireWorkAcceleration);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics,mouseX,mouseY,partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);
        int h = graphics.guiHeight();
        int w = graphics.guiWidth();

        String titleStr = Component.translatable("config.unlimitedelytra.title").getString();
        graphics.drawString(this.font, titleStr, w / 2 - font.width(titleStr) / 2,  20, 0xFFFFFF,false);

        String enableelytra = Component.translatable("config.unlimitedelytra.enableelytra").getString();
        graphics.drawString(this.font, enableelytra, 20,  60, 0xFFFFFF,false);
        String airresistance = Component.translatable("config.unlimitedelytra.airresistance").getString();
        graphics.drawString(this.font, airresistance, 20,  80, 0xFFFFFF,false);
        String infinitefireworkacceleration = Component.translatable("config.unlimitedelytra.infinitefireworkacceleration").getString();
        graphics.drawString(this.font, infinitefireworkacceleration, 20,  100, 0xFFFFFF,false);
        String fireworkacceleration = Component.translatable("config.unlimitedelytra.fireworkacceleration").getString();
        graphics.drawString(this.font, fireworkacceleration, 20,  120, 0xFFFFFF,false);
    }

    @Override
    public void onClose() {
        super.onClose();
        
        if (AirResistance != null && AirResistance.getValue() != null && !AirResistance.getValue().isEmpty()) {
            try {
                Config.AirResistance.set(Double.valueOf(AirResistance.getValue()));
            } catch (NumberFormatException e) {
                // Ignore invalid input
            }
        }
        
        if (FireWorkAcceleration != null && FireWorkAcceleration.getValue() != null && !FireWorkAcceleration.getValue().isEmpty()) {
            try {
                Config.FireWorkAcceleration.set(Double.valueOf(FireWorkAcceleration.getValue()));
            } catch (NumberFormatException e) {
                // Ignore invalid input
            }
        }
        
        Config.SPEC.save();
    }

}
