package net.zepalesque.redux.block.util.state.enums;

import net.minecraft.util.StringRepresentable;
import org.apache.commons.compress.utils.Lists;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public enum PetalPrismaticness implements StringRepresentable {

    NONE("none"), ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6");

    private final String id;
    PetalPrismaticness(String id) {
        this.id = id;
    }

    @Override
    public String getSerializedName() {
        return this.id;
    }

    public static PetalPrismaticness getFromIndex(int index)
    {
        return index == 0 ? ZERO : index == 1 ? ONE : index == 2 ? TWO : index == 3 ? THREE : index == 4 ? FOUR : index == 5 ? FIVE : index == 6 ? SIX : NONE;
    }
    public int index() {
        return this == ZERO ? 0 : this == ONE ? 1 : this == TWO ? 2 : this == THREE ? 3 : this == FOUR ? 4 : this == FIVE ? 5 : this == SIX ? 6 : -1;
    }

    public Collection<PetalPrismaticness> getNearby(int range, boolean nonePermitted) {
        if (this == NONE) {
            return nonePermitted ? List.of(NONE) : Collections.emptyList();
        }
        int i = this.index();
        List<PetalPrismaticness> list = Lists.newArrayList();
        for (int o = -range; o <= range; o++) {
            PetalPrismaticness p = getFromIndex(i + o);
            if (p != NONE) {
                list.add(p);
            }
        }
        if (nonePermitted) {
            list.add(NONE);
        }
        return list;
    }
}
