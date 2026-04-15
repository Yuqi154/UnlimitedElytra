package org.hiedacamellia.unlimitedelytra;

import java.util.function.Supplier;

import org.hiedacamellia.unlimitedelytra.core.config.ScreenProvider;

import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

public class ClientSetup {
    public static void setup(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (Supplier<IConfigScreenFactory>) ScreenProvider::new);
    }
}
