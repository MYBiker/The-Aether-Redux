package net.zepalesque.redux.api;

import net.zepalesque.redux.client.gui.screen.config.PackConfigMenu;

public interface MixinMenuStorage {


    PackConfigMenu getMenu();

    void setMenu(PackConfigMenu menu);
}
