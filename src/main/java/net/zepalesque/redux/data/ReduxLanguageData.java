package net.zepalesque.redux.data;

import com.aetherteam.aether.data.providers.AetherLanguageProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.biome.Biome;
import net.zepalesque.redux.Redux;
import net.zepalesque.redux.api.blockhandler.WoodHandler;
import net.zepalesque.redux.block.ReduxBlocks;
import net.zepalesque.redux.data.resource.ReduxBiomes;
import net.zepalesque.redux.data.resource.ReduxDamageTypes;
import net.zepalesque.redux.effect.ReduxEffects;
import net.zepalesque.redux.entity.ReduxEntityTypes;
import net.zepalesque.redux.item.ReduxItems;

public class ReduxLanguageData extends AetherLanguageProvider {


    public ReduxLanguageData(PackOutput output) {
        super(output, Redux.MODID);
    }

    @Override
    protected void addTranslations() {


        addItem(ReduxItems.VANILLA_GUMMY_SWET, "Vanilla Gummy Swet");
        addLore(ReduxItems.VANILLA_GUMMY_SWET, "A sweet smelling gummy, it can be found in random chests in Bronze and Silver dungeons. It restores the player's hunger to full. Very useful for boss fights.");

        addItem(ReduxItems.SUBZERO_CROSSBOW, "Subzero Crossbow");
        addLore(ReduxItems.SUBZERO_CROSSBOW, "An icy crossbow found in Gold Dungeons. This slows enemies when they are hit.");

        addItem(ReduxItems.ZANBERRY, "Chromaberry");
        addLore(ReduxItems.ZANBERRY, "A type of berry found on bushes in the Highfields. This can be eaten, and nourishes slightly more hunger than Blueberries.");

        addItem(ReduxItems.BLUE_SWET_JELLY, "Blue Swet Jelly");
        addLore(ReduxItems.BLUE_SWET_JELLY, "A delicious treat made with the remains of a Blue Swet.");
        addItem(ReduxItems.GOLDEN_SWET_JELLY, "Golden Swet Jelly");
        addLore(ReduxItems.GOLDEN_SWET_JELLY, "A delicious treat made with the remains of a Golden Swet.");
        addItem(ReduxItems.VANILLA_SWET_JELLY, "Vanilla Swet Jelly");
        addLore(ReduxItems.VANILLA_SWET_JELLY, "A delicious treat made with the remains of a Vanilla Swet.");
        addItem(ReduxItems.GOLDEN_SWET_BALL, "Golden Swet Ball");
        addLore(ReduxItems.GOLDEN_SWET_BALL, "A glowing, gooey orb of a Golden Swet's gel. It can be used to craft Spectral Darts.");
        addItem(ReduxItems.VANILLA_SWET_BALL, "Vanilla Swet Ball");
        addLore(ReduxItems.VANILLA_SWET_BALL, "A gooey, sweet-smelling orb of a Vanilla Swet's gel. True to the name, this smells like vanilla.");
        addItem(ReduxItems.OATS, "Oats");
        addLore(ReduxItems.OATS, "The Aether's main source of grain. Phygs find these tasty. They can also be crafted into the delicious Blueberry Pie!");

        addItem(ReduxItems.BUNDLE_OF_AETHER_GRASS, "Bundle of Aether Grass");
        addLore(ReduxItems.BUNDLE_OF_AETHER_GRASS, "A bunch of Aether Grass. Flying Cows like to munch on these.");

        addItem(ReduxItems.BLUEBERRY_PIE, "Blueberry Pie");
        addLore(ReduxItems.BLUEBERRY_PIE, "A pie made of the Aether's native blueberries. These treats taste amazing, and can make a great gift for a friend.");
        addItem(ReduxItems.ENCHANTED_BLUEBERRY_PIE, "Enchanted Blueberry Pie");
        addLore(ReduxItems.ENCHANTED_BLUEBERRY_PIE, "An enchanted variant of the Blueberry Pie. This fancy treat gives you a short regeneration effect!");

        addItem(ReduxItems.LUXBUDS, "Luxbuds");
        addLore(ReduxItems.LUXBUDS, "The blighted, mutated variant of Oats, found by breaking Luxweed, which can be infused into a more powerful purified form. Despite being blighted, eating these occasionally wards off various effects from the blight's harmful plants.");

        addItem(ReduxItems.PURIFIED_LUXBUDS, "Purified Luxbuds");
        addLore(ReduxItems.PURIFIED_LUXBUDS, "The purified form of Luxbuds. Unlike their raw counterpart, these will ALWAYS give you a resistance to the blight, and for a longer duration.");

        addItem(ReduxItems.ENCHANTED_RING, "Enchanted Ring");
        addLore(ReduxItems.ENCHANTED_RING, "A ring with a large amount of magical potential. You can use it to craft many other types of rings with a variety of abilities!");

        addItem(ReduxItems.RING_OF_WISDOM, "Ring of Wisdom");
        addLore(ReduxItems.RING_OF_WISDOM, "One of many variations of the enchanted ring, this one is crafted using Holystone and a strange green crystal not native to the Aether, known as an Emerald");

        addItem(ReduxItems.SENTRY_RING, "Sentry Ring");
        addLore(ReduxItems.SENTRY_RING, "One of many different variations of the enchanted ring. When worn, hitting mobs will release buring blue embers that can strike nearby mobs!");

        addItem(ReduxItems.SHROOM_RING, "Shroom Ring");
        addLore(ReduxItems.SHROOM_RING, "One of many different variations of the enchanted ring. When worn, taking damage with low health will have a chance to give an Adrenaline effect, giving a temporary stat boost in turn for a stat debuff when it expires.");

        addItem(ReduxItems.VAMPIRE_AMULET, "Vampire Amulet");
        addLore(ReduxItems.VAMPIRE_AMULET, "A pendant found in Bronze Dungeon reward chests. It gives the wearer life steal at night, but decreases their damage resistance at day");

        addItem(ReduxItems.AIRBOUND_CAPE, "Airbound Cape");
        addLore(ReduxItems.AIRBOUND_CAPE, "A cape found in the Bronze Dungeon. It allows the wearer to double-jump!");

        addItem(ReduxItems.GRAND_VICTORY_MEDAL, "Grand Victory Medal");
        addLore(ReduxItems.GRAND_VICTORY_MEDAL, "A pendant dropped by the Valkyrie Queen. In Valkyrie culture, having one of these shows you are a noble warrior.");

        addItem(ReduxItems.PHOENIX_EMBLEM, "Phoenix Emblem");
        addLore(ReduxItems.PHOENIX_EMBLEM, "An ancient emblem once used by followers of the Sun Spirit. It allows the wearer to shoot fireballs!");

        addItem(ReduxItems.VANILLA_SWET_SPAWN_EGG, "Vanilla Swet Spawn Egg");
        addLore(ReduxItems.VANILLA_SWET_SPAWN_EGG, "A spawn egg. Spawns a Vanilla Swet");

        addItem(ReduxItems.BLIGHTED_SPORES, "Blighted Spores");
        addLore(ReduxItems.BLIGHTED_SPORES, "Spores from the Blightwillow tree. They act like an opposite to Ambrosium, turning some things into their blighted variant");

        addItem(ReduxItems.SPECTRAL_DART, "Spectral Dart");
        addLore(ReduxItems.SPECTRAL_DART, "The ammo for Spectral Dart Shooters. Crafted with Skyroot Sticks and Golden Swet Balls, these special darts cause enemies to temporarily glow when hit!");
        addItem(ReduxItems.SPECTRAL_DART_SHOOTER, "Spectral Dart Shooter");
        addLore(ReduxItems.SPECTRAL_DART_SHOOTER, "A Dart Shooter which shoots Spectral Darts, it's darts cause enemies to temporarily glow when hit!");

        addItem(ReduxItems.MUSIC_DISC_LABYRINTHS_VENGEANCE, "Slider Music Disc");
        addDiscDesc(ReduxItems.MUSIC_DISC_LABYRINTHS_VENGEANCE, "Emile van Krieken - Labyrinth's Vengeance");
        addLore(ReduxItems.MUSIC_DISC_LABYRINTHS_VENGEANCE, "A music disc that plays Labyrinth's Vengeance by Emile van Krieken.");

        addItem(ReduxItems.MOUSE_EAR_SOUP, "Mouse Ear Soup");
        addLore(ReduxItems.MOUSE_EAR_SOUP, "Mouse Ear Soup is a surprisingly good dish, but it will certainly not get you praise from most. However, while most people will raise their eyebrows wondering why in Veradex you have leather ears in a bowl of water, a select few will recognize your true culinary genious, gaining you their utmost and complete respect.");

        addItem(ReduxItems.QUICKROOT, "Quickroot");
        addLore(ReduxItems.QUICKROOT, "A root found under Quicksoil. This can be eaten, and gives the regeneration effect!");

        addBlock(ReduxBlocks.QUICKROOTS, "Quickroots");

        addBlock(ReduxBlocks.AETHER_SHORT_GRASS, "Aether Grass");
        addLore(ReduxBlocks.AETHER_SHORT_GRASS, "Blades of the Aether's grass. It feels slightly cool to the touch.");

        addBlock(ReduxBlocks.AEVELIUM, "Aevelium");
        addLore(ReduxBlocks.AEVELIUM, "A fungal grass-like growth that spreads across Aether Dirt. Found in the Cloudcaps.");

        addBlock(ReduxBlocks.JELLYSHROOM, "Jellyshroom");
        addBlock(ReduxBlocks.POTTED_SPRINGSHROOM, "Potted Jellyshroom");
        addLore(ReduxBlocks.JELLYSHROOM, "A jelly-based mushroom, which is found in the Cloudcaps.");

        addBlock(ReduxBlocks.CLOUD_CAP_BLOCK, "Cloud Cap Block");
        addLore(ReduxBlocks.CLOUD_CAP_BLOCK, "The cap of the large Cloudcap Mushrooms, found in the Cloudcaps.");
        addBlock(ReduxBlocks.CLOUDCAP_SPORES, "Cloudcap Spores");
        addLore(ReduxBlocks.CLOUDCAP_SPORES, "A block of spores from the large Cloudcap Mushrooms, found in the Cloudcaps.");

        addBlock(ReduxBlocks.JELLYSHROOM_JELLY_BLOCK, "Jellyshroom Jelly Block");
        addLore(ReduxBlocks.JELLYSHROOM_JELLY_BLOCK, "Jelly from a large Jellyshroom, which can be found in the Cloudcaps.");

        addBlock(ReduxBlocks.COARSE_AETHER_DIRT, "Coarse Aether Dirt");
        addLore(ReduxBlocks.COARSE_AETHER_DIRT, "A variant of Aether Dirt that does not regrow any grass.");

        addBlock(ReduxBlocks.BLIGHTMOSS_BLOCK, "Blightmoss Block");
        addLore(ReduxBlocks.BLIGHTMOSS_BLOCK, "Moss that has been infected by the Blight. This can be found in some caves in the Aether.");

        addBlock(ReduxBlocks.GOLDEN_VINES, "Golden Vines");
        addBlock(ReduxBlocks.GOLDEN_VINES_PLANT, "Golden Vines Plant");
        addLore(ReduxBlocks.GOLDEN_VINES, "A type of gold-colored vine that commonly grows under the leaves of Golden Skyroot trees.");
        addBlock(ReduxBlocks.GILDED_VINES, "Gilded Vines");
        addBlock(ReduxBlocks.GILDED_VINES_PLANT, "Gilded Vines Plant");
        addLore(ReduxBlocks.GILDED_VINES, "A type of off-white vine that commonly grows under the leaves of Gilded Skyroot trees.");

        addBlock(ReduxBlocks.BLIGHTMOSS_CARPET, "Blightmoss Carpet");
        addLore(ReduxBlocks.BLIGHTMOSS_CARPET, "A thin blanket of Blightmoss. This can be found in some caves in the Aether.");

        addBlock(ReduxBlocks.LUXWEED, "Luxweed");
        addBlock(ReduxBlocks.POTTED_LUXWEED, "Potted Luxweed");
        addLore(ReduxBlocks.LUXWEED, "A plant in the blight biome, which is a distant relative to Wyndsprouts. It gives off a subtle glow, lighting the area around it.");

        addBlock(ReduxBlocks.WYNDSPROUTS, "Wyndsprouts");
        addBlock(ReduxBlocks.POTTED_WYNDSPROUTS, "Potted Wyndsprouts");
        addLore(ReduxBlocks.WYNDSPROUTS, "A common plant found in the Aether. They occasionally drop Oats, the main edible source of grain in the Aether.");

        addBlock(ReduxBlocks.SKYSPROUTS, "Skysprouts");
        addBlock(ReduxBlocks.POTTED_SKYSPROUTS, "Potted Skysprouts");
        addLore(ReduxBlocks.SKYSPROUTS, "A relative of the common Wyndsprouts, this flowering grass is found in the Highfields.");

        addBlock(ReduxBlocks.ZANBERRY_BUSH, "Zanberry Bush");
        addLore(ReduxBlocks.ZANBERRY_BUSH, "A nice bush of Zanberries!");

        addBlock(ReduxBlocks.ZANBERRY_SHRUB, "Zanberry Shrub");
        addBlock(ReduxBlocks.POTTED_ZANBERRY_SHRUB, "Potted Zanberry Shrub");
        addLore(ReduxBlocks.ZANBERRY_SHRUB, "The stem of the Zanberry Bush. This will grow into a full bush, and will have Zanberries, which can be picked!");

        addBlock(ReduxBlocks.CLOUDCAP_MUSHLING, "Cloudcap Mushling");
        addBlock(ReduxBlocks.POTTED_CLOUDCAP_MUSHLING, "Potted Cloudcap Mushling");
        addLore(ReduxBlocks.CLOUDCAP_MUSHLING, "A mushroom found commonly in the Cloudcaps and occasionally in the Highfields. It glows faintly.");

        addBlock(ReduxBlocks.TALL_CLOUDCAP, "Tall Cloudcap");
        addLore(ReduxBlocks.TALL_CLOUDCAP, "The taller variant of the Cloudcap Mushling. This can be grown into Giant Cloudcap Mushrooms");

        addBlock(ReduxBlocks.SPIROLYCTIL, "Spirolyctil");
        addBlock(ReduxBlocks.POTTED_SPIROLYCTIL, "Potted Spirolyctil");
        addLore(ReduxBlocks.SPIROLYCTIL, "A shiny blue flower found in the Blight. It almost seems like an island of peace in the ocean of chaos.");

        addBlock(ReduxBlocks.BLIGHTSHADE, "Blightshade");
        addBlock(ReduxBlocks.POTTED_BLIGHTSHADE, "Potted Blightshade");
        addLore(ReduxBlocks.BLIGHTSHADE, "Dark purple flowers found in the Blight. They release a gas that will make their target temporarily see darkness around them.");

        addBlock(ReduxBlocks.BLIGHTWILLOW_LEAVES, "Blightwillow Leaves");
        addLore(ReduxBlocks.BLIGHTWILLOW_LEAVES, "The leaves of the Blightwillow tree. These sometimes drop Blightwillow Saplings.");
        addBlock(ReduxBlocks.BLIGHTWILLOW_ROOTS, "Blightwillow Roots");
        addLore(ReduxBlocks.BLIGHTWILLOW_ROOTS, "The roots of the Blightwillow tree. These can be found connected to Blightwillow Trees.");
        addBlock(ReduxBlocks.FLOWERING_FIELDSPROUT_LEAVES, "Flowering Fieldsprout Leaves");
        addLore(ReduxBlocks.FLOWERING_FIELDSPROUT_LEAVES, "The colorful leaves of Flowering Fieldsprout trees! These will occasionally drop Flowering Fieldsprout Saplings.");
        addBlock(ReduxBlocks.FIELDSPROUT_PETALS, "Fieldsprout Petals");
        addLore(ReduxBlocks.FIELDSPROUT_PETALS, "The vibrant petals of Flowering Fieldsprout trees, which are found commonly underneath them.");
        addBlock(ReduxBlocks.GOLDEN_LEAF_PILE, "Golden Leaf Pile");
        addLore(ReduxBlocks.GOLDEN_LEAF_PILE, "A pile of Golden Oak Leaves. These can be found in the Gilded Thicket.");
        addBlock(ReduxBlocks.GILDED_LEAF_PILE, "Gilded Leaf Pile");
        addLore(ReduxBlocks.GILDED_LEAF_PILE, "A pile of Gilded Oak Leaves. These can be found in the Gilded Thicket.");
        addBlock(ReduxBlocks.BLIGHTWILLOW_SAPLING, "Blightwillow Sapling");
        addBlock(ReduxBlocks.POTTED_BLIGHTWILLOW_SAPLING, "Potted Blightwillow Sapling");
        addLore(ReduxBlocks.BLIGHTWILLOW_SAPLING, "The sapling of the Blightwillow tree. It can be grown by waiting or using Bone Meal.");

        addBlock(ReduxBlocks.FLOWERING_FIELDSPROUT_SAPLING, "Flowering Fieldsprout Sapling");
        addBlock(ReduxBlocks.POTTED_FLOWERING_FIELDSPROUT_SAPLING, "Potted Flowering Fieldsprout Sapling");
        addLore(ReduxBlocks.FLOWERING_FIELDSPROUT_SAPLING, "The sapling of the Flowering Fieldsprout tree. It can be grown by waiting or using Bone Meal.");

        addBlock(ReduxBlocks.AEVELIUM_GROWTH, "Aevelium Growth");
        addBlock(ReduxBlocks.POTTED_AEVELIUM_GROWTH, "Potted Aevelium Growth");
        addLore(ReduxBlocks.AEVELIUM_GROWTH, "Some grass-like growth that grows on Aevelium in the Cloudcaps.");

        addBlock(ReduxBlocks.AEVELIUM_SPROUTS, "Aevelium Sprouts");
        addLore(ReduxBlocks.AEVELIUM_SPROUTS, "A smaller variant of Aevelium Growth. Can be found in the Cloudcaps.");

        addBlock(ReduxBlocks.GILDED_OAK_LEAVES, "Gilded Oak Leaves");
        addLore(ReduxBlocks.GILDED_OAK_LEAVES, "The leaves of the Gilded Oak tree. These sometimes drop Gilded Oak Saplings.");
        addBlock(ReduxBlocks.GILDED_OAK_SAPLING, "Gilded Oak Sapling");
        addBlock(ReduxBlocks.POTTED_GILDED_OAK_SAPLING, "Potted Gilded Oak Sapling");
        addLore(ReduxBlocks.GILDED_OAK_SAPLING, "The sapling of the Gilded Oak tree. It can be grown by waiting or using Bone Meal.");

        addBlock(ReduxBlocks.BLIGHTED_SKYROOT_LEAVES, "Blighted Skyroot Leaves");
        addLore(ReduxBlocks.BLIGHTED_SKYROOT_LEAVES, "The leaves of the blighted variant of the Skyroot Tree. These sometimes drop Blighted Skyroot Saplings.");
        addBlock(ReduxBlocks.BLIGHTED_SKYROOT_SAPLING, "Blighted Skyroot Sapling");
        addBlock(ReduxBlocks.POTTED_BLIGHTED_SKYROOT_SAPLING, "Potted Blighted Skyroot Sapling");
        addLore(ReduxBlocks.BLIGHTED_SKYROOT_SAPLING, "The sapling of the blighted variant of the Skyroot Tree. It can be grown by waiting or using Bone Meal.");

        addBlock(ReduxBlocks.GLACIA_LEAVES, "Glacia Leaves");
        addLore(ReduxBlocks.GLACIA_LEAVES, "The leaves of the Glacia tree. These sometimes drop Glacia Saplings.");
        addBlock(ReduxBlocks.GLACIA_SAPLING, "Glacia Sapling");
        addBlock(ReduxBlocks.POTTED_GLACIA_SAPLING, "Potted Glacia Sapling");
        addLore(ReduxBlocks.GLACIA_SAPLING, "The sapling of the Glacia tree. It can be grown by waiting or using Bone Meal.");

        addBlock(ReduxBlocks.PURPLE_GLACIA_LEAVES, "Purple Glacia Leaves");
        addLore(ReduxBlocks.PURPLE_GLACIA_LEAVES, "The leaves of the purple variant of the Glacia tree. These sometimes drop Purple Glacia Saplings.");
        addBlock(ReduxBlocks.PURPLE_GLACIA_SAPLING, "Purple Glacia Sapling");
        addBlock(ReduxBlocks.POTTED_PURPLE_GLACIA_SAPLING, "Potted Purple Glacia Sapling");
        addLore(ReduxBlocks.PURPLE_GLACIA_SAPLING, "The sapling of the purple variant of the Glacia tree. It can be grown by waiting or using Bone Meal.");

        addBlock(ReduxBlocks.DIVINITE, "Divinite");
        addLore(ReduxBlocks.DIVINITE, "A rock found in the Aether. This rock is commonly used in structures built by Valkyries. Additionally, when enchanted, it buds into a dazzlingly bright glowing stone!");
        addBlock(ReduxBlocks.DIVINITE_SLAB, "Divinite Slab");
        addLore(ReduxBlocks.DIVINITE_SLAB, "Crafted from Divinite. Slabs are half blocks, versatile for decoration and smooth slopes. Try adding some to a building's roofing!");
        addBlock(ReduxBlocks.DIVINITE_STAIRS, "Divinite Stairs");
        addLore(ReduxBlocks.DIVINITE_STAIRS, "Crafted from Divinite. Stairs are useful for adding verticality to builds and are often used for decoration too!");
        addBlock(ReduxBlocks.DIVINITE_WALL, "Divinite Wall");
        addLore(ReduxBlocks.DIVINITE_WALL, "Crafted from Divinite. Can be used for decorative enclosures and defences. Great for keeping nasty intruders away!");

        addBlock(ReduxBlocks.HOLYSILT, "Holysilt");
        addLore(ReduxBlocks.HOLYSILT, "Fine particles of broken up holystone and other minerals. Can be found underground, and around the blight. Careful, it's not very stable and may fall beneath your feet!");

        addBlock(ReduxBlocks.IRIDIA, "Iridia");
        addBlock(ReduxBlocks.POTTED_IRIDIA, "Potted Iridia");
        addLore(ReduxBlocks.IRIDIA, "An iridescent flower found in the Highfields.");

        addBlock(ReduxBlocks.GILDED_HOLYSTONE, "Gilded Holystone");
        addLore(ReduxBlocks.GILDED_HOLYSTONE, "The enchanted form of Mossy Holystone. This rock covered in golden moss glitters in the sunlight.");
        addBlock(ReduxBlocks.GILDED_HOLYSTONE_SLAB, "Gilded Holystone Slab");
        addLore(ReduxBlocks.GILDED_HOLYSTONE_SLAB, "Crafted from Gilded Holystone. Slabs are half blocks, versatile for decoration and smooth slopes. Try adding some to a building's roofing!");
        addBlock(ReduxBlocks.GILDED_HOLYSTONE_STAIRS, "Gilded Holystone Stairs");
        addLore(ReduxBlocks.GILDED_HOLYSTONE_STAIRS, "Crafted from Gilded Holystone. Stairs are useful for adding verticality to builds and are often used for decoration too!");
        addBlock(ReduxBlocks.GILDED_HOLYSTONE_WALL, "Gilded Holystone Wall");
        addLore(ReduxBlocks.GILDED_HOLYSTONE_WALL, "Crafted from Gilded Holystone. Can be used for decorative enclosures and defences. Great for keeping nasty intruders away!");

        addBlock(ReduxBlocks.BLIGHTMOSS_HOLYSTONE, "Blightmoss Holystone");
        addLore(ReduxBlocks.BLIGHTMOSS_HOLYSTONE, "The blighted form of Mosssy Holystone. The viscious moss on this rock wilts in the sunlight and flourishes in the moonlight.");
        addBlock(ReduxBlocks.BLIGHTMOSS_HOLYSTONE_SLAB, "Blightmoss Holystone Slab");
        addLore(ReduxBlocks.BLIGHTMOSS_HOLYSTONE_SLAB, "Crafted from Blightmoss Holystone. Slabs are half blocks, versatile for decoration and smooth slopes. Try adding some to a building's roofing!");
        addBlock(ReduxBlocks.BLIGHTMOSS_HOLYSTONE_STAIRS, "Blightmoss Holystone Stairs");
        addLore(ReduxBlocks.BLIGHTMOSS_HOLYSTONE_STAIRS, "Crafted from Blightmoss Holystone. Stairs are useful for adding verticality to builds and are often used for decoration too!");
        addBlock(ReduxBlocks.BLIGHTMOSS_HOLYSTONE_WALL, "Blightmoss Holystone Wall");
        addLore(ReduxBlocks.BLIGHTMOSS_HOLYSTONE_WALL, "Crafted from Blightmoss Holystone. Can be used for decorative enclosures and defences. Great for keeping nasty intruders away!");

        addBlock(ReduxBlocks.FROSTED_HOLYSTONE, "Frosted Holystone");
        addLore(ReduxBlocks.FROSTED_HOLYSTONE, "An icy variant for holystone. It's cold to the touch");
        addBlock(ReduxBlocks.FROSTED_HOLYSTONE_SLAB, "Frosted Holystone Slab");
        addLore(ReduxBlocks.FROSTED_HOLYSTONE_SLAB, "Crafted from Frosted Holystone. Slabs are half blocks, versatile for decoration and smooth slopes. Try adding some to a building's roofing!");
        addBlock(ReduxBlocks.FROSTED_HOLYSTONE_STAIRS, "Frosted Holystone Stairs");
        addLore(ReduxBlocks.FROSTED_HOLYSTONE_STAIRS, "Crafted from Frosted Holystone. Stairs are useful for adding verticality to builds and are often used for decoration too!");
        addBlock(ReduxBlocks.FROSTED_HOLYSTONE_WALL, "Frosted Holystone Wall");
        addLore(ReduxBlocks.FROSTED_HOLYSTONE_WALL, "Crafted from Frosted Holystone. Can be used for decorative enclosures and defences. Great for keeping nasty intruders away!");

        addBlock(ReduxBlocks.CARVED_STONE_BRICKS, "Carved Stone Bricks");
        addLore(ReduxBlocks.CARVED_STONE_BRICKS, "Bricks made of carved stone. These can be found in the Bronze dungeon.");
        addBlock(ReduxBlocks.CARVED_STONE_PILLAR, "Carved Stone Pillar");
        addLore(ReduxBlocks.CARVED_STONE_PILLAR, "A pillar made of carved stone. Can be found in the Bronze dungeon.");
        addBlock(ReduxBlocks.CARVED_STONE_BRICK_SLAB, "Carved Stone Brick Slab");
        addLore(ReduxBlocks.CARVED_STONE_BRICK_SLAB, "Crafted from Carved Stone Bricks. Slabs are half blocks, versatile for decoration and smooth slopes. Try adding some to a building's roofing!");
        addBlock(ReduxBlocks.CARVED_STONE_BRICK_STAIRS, "Carved Stone Brick Stairs");
        addLore(ReduxBlocks.CARVED_STONE_BRICK_STAIRS, "Crafted from Carved Stone Bricks. Stairs are useful for adding verticality to builds and are often used for decoration too!");
        addBlock(ReduxBlocks.CARVED_STONE_BRICK_WALL, "Carved Stone Brick Wall");
        addLore(ReduxBlocks.CARVED_STONE_BRICK_WALL, "Crafted from Carved Stone Bricks. Can be used for decorative enclosures and defences. Great for keeping nasty intruders away!");

        addBlock(ReduxBlocks.VERIDIUM_LANTERN, "Veridium Lantern");
        addLore(ReduxBlocks.VERIDIUM_LANTERN, "A lantern made of Veridium. You can place it on the ground or hang it on the ceiling!");

        addBlock(ReduxBlocks.VERIDIUM_CHAIN, "Veridium Chain");
        addLore(ReduxBlocks.VERIDIUM_CHAIN, "A chain made of pure Veridium. This is crafted with a Veridium Ingot and two Veridium Nuggets.");

        addItem(ReduxItems.VERIDIUM_NUGGET, "Veridium Nugget");
        addLore(ReduxItems.VERIDIUM_NUGGET, "A small chunk of Veridium. This can be crafted to and from Veridium Ingots.");

        addItem(ReduxItems.VERIDIUM_PICKAXE, "Veridium Pickaxe");
        addLore(ReduxItems.VERIDIUM_PICKAXE, "A pickaxe made of Veridium. This can be infused by right-clicking with an Ambrosium Shard to make it far more powerful for a short time!");
        addItem(ReduxItems.INFUSED_VERIDIUM_PICKAXE, "Infused Veridium Pickaxe");
        addLore(ReduxItems.INFUSED_VERIDIUM_PICKAXE, "A pickaxe made of Veridium. This can be infused by right-clicking with an Ambrosium Shard to make it far more powerful for a short time!");

        addItem(ReduxItems.VERIDIUM_SHOVEL, "Veridium Shovel");
        addLore(ReduxItems.VERIDIUM_SHOVEL, "A shovel made of Veridium. This can be infused by right-clicking with an Ambrosium Shard to make it far more powerful for a short time!");
        addItem(ReduxItems.INFUSED_VERIDIUM_SHOVEL, "Infused Veridium Shovel");
        addLore(ReduxItems.INFUSED_VERIDIUM_SHOVEL, "A shovel made of Veridium. This can be infused by right-clicking with an Ambrosium Shard to make it far more powerful for a short time!");

        addItem(ReduxItems.VERIDIUM_HOE, "Veridium Hoe");
        addLore(ReduxItems.VERIDIUM_HOE, "A hoe made of Veridium. This can be infused by right-clicking with an Ambrosium Shard to make it far more powerful for a short time!");
        addItem(ReduxItems.INFUSED_VERIDIUM_HOE, "Infused Veridium Hoe");
        addLore(ReduxItems.INFUSED_VERIDIUM_HOE, "A hoe made of Veridium. This can be infused by right-clicking with an Ambrosium Shard to make it far more powerful for a short time!");

        addItem(ReduxItems.VERIDIUM_AXE, "Veridium Axe");
        addLore(ReduxItems.VERIDIUM_AXE, "An axe made of Veridium. This can be infused by right-clicking with an Ambrosium Shard to make it far more powerful for a short time!");
        addItem(ReduxItems.INFUSED_VERIDIUM_AXE, "Infused Veridium Axe");
        addLore(ReduxItems.INFUSED_VERIDIUM_AXE, "An axe made of Veridium. This can be infused by right-clicking with an Ambrosium Shard to make it far more powerful for a short time!");

        addItem(ReduxItems.VERIDIUM_SWORD, "Veridium Sword");
        addLore(ReduxItems.VERIDIUM_SWORD, "A sword made of Veridium. This can be infused by right-clicking with an Ambrosium Shard to make it far more powerful for a short time!");
        addItem(ReduxItems.INFUSED_VERIDIUM_SWORD, "Infused Veridium Sword");
        addLore(ReduxItems.INFUSED_VERIDIUM_SWORD, "A sword made of Veridium. This can be infused by right-clicking with an Ambrosium Shard to make it far more powerful for a short time!");

        addItem(ReduxItems.VERIDIUM_INGOT, "Veridium Ingot");
        addLore(ReduxItems.VERIDIUM_INGOT, "An bar of pure Veridium, a metal that when coming in contact with ambrosium, takes on a glowing light blue color, strengthening temporarily.");

        addItem(ReduxItems.RAW_VERIDIUM, "Raw Veridium");
        addLore(ReduxItems.RAW_VERIDIUM, "A chunk of Raw Veridium. This can be smelted into an ingot.");

        addItem(ReduxItems.RAW_GRAVITITE, "Raw Gravitite");
        addLore(ReduxItems.RAW_GRAVITITE, "A chunk of the Aether's rarest ore. This can be enchanted into Enchanted Gravitite blocks.");

        addItem(ReduxItems.COCKATRICE_FEATHER, "Cockatrice Feather");
        addLore(ReduxItems.COCKATRICE_FEATHER, "A feather from a Cockatrice. It is said that it will protect the user from Cockatrices' venom.");

        addItem(ReduxItems.OAT_MUFFIN, "Oat Muffin");
        addLore(ReduxItems.OAT_MUFFIN, "A nice little snack made of oats. It's delicious with a porkchop for breakfast");

        addBlock(ReduxBlocks.LIGHTROOTS, "Lightroots");
        addItem(ReduxItems.LIGHTROOT_CLUMP, "Lightroot Clump");
        addLore(ReduxItems.LIGHTROOT_CLUMP, "A clump of the glowing roots that grow in the Cloudcaps. These can be eaten, and give night vision.");

        addItem(ReduxItems.SENTRY_CHIP, "Sentry Chip");
        addLore(ReduxItems.SENTRY_CHIP, "A circuitboard piece from a Sentry. Nobody knows who created these, just that the Valkyries have utilized them for all of recorded history.");


        addBlock(ReduxBlocks.GOLDEN_CLOVER, "Golden Clover");
        addBlock(ReduxBlocks.POTTED_GOLDEN_CLOVER, "Potted Golden Clover");
        addLore(ReduxBlocks.GOLDEN_CLOVER, "A four-leaved clover found in the Gilded Groves. Makes a nice decoration, and can be placed in a flower pot!");

        addBlock(ReduxBlocks.SPLITFERN, "Splitfern");
        addBlock(ReduxBlocks.POTTED_SPLITFERN, "Potted Splitfern");
        addLore(ReduxBlocks.SPLITFERN, "A soft, fern-like plant with smooth leaves, with a split-end top.");

        addBlock(ReduxBlocks.AURUM, "Aurum");
        addBlock(ReduxBlocks.POTTED_AURUM, "Potted Aurum");
        addLore(ReduxBlocks.AURUM, "An orange flower found in the Gilded Groves. Some say it brings luck!");

        addBlock(ReduxBlocks.LUMINA, "Lumina");
        addBlock(ReduxBlocks.POTTED_LUMINA, "Potted Lumina");
        addLore(ReduxBlocks.LUMINA, "A flower found in the Frosted Forest. It gives off a glow, lighting the area around it");

        addBlock(ReduxBlocks.DAGGERBLOOM, "Daggerbloom");
        addBlock(ReduxBlocks.POTTED_DAGGERBLOOM, "Potted Daggerbloom");
        addLore(ReduxBlocks.DAGGERBLOOM, "A flower found in the Frosted Forest. Its petals are as cold as ice");

        addBlock(ReduxBlocks.VERIDIUM_ORE, "Veridium Ore");
        addLore(ReduxBlocks.VERIDIUM_ORE, "The ore of Veridium. This can be found around the Aether");

        addBlock(ReduxBlocks.VERIDIUM_BLOCK, "Block of Veridium");
        addLore(ReduxBlocks.VERIDIUM_BLOCK, "A block of pure Veridium. This can be crafted from Veridium Ingots.");

        addBlock(ReduxBlocks.RAW_VERIDIUM_BLOCK, "Block of Raw Veridium");
        addLore(ReduxBlocks.RAW_VERIDIUM_BLOCK, "A block of raw Veridium. This can be crafted from Raw Veridium.");

        addBiome(ReduxBiomes.CLOUDCAP_JUNGLE, "Cloudcaps");
        addBiome(ReduxBiomes.GLACIAL_TAIGA, "Frosted Forests");
        addBiome(ReduxBiomes.GILDED_GROVES, "Gilded Groves");
        addBiome(ReduxBiomes.HIGHFIELDS, "Highfields");
        addBiome(ReduxBiomes.THE_BLIGHT, "The Blight");

        addGuiText("bittersweet_charm_poison_chance", "10% chance to poison targets of melee attacks");
        addGuiText("wisdom_ring_xp_increase", "+20-30% XP from mobs");
        addGuiText("grand_medal_regen", "Regeneration which increases as health drops");
        addGuiText("grand_medal_queen_refight", "Allows replaying the Valkyrie Queen fight without collecting more medals");
        addGuiText("airbound_cape_jump_boost", "Grants ability to double jump");
        addGuiText("phoenix_emblem_fireball", "Grants ability to shoot fireballs");
        addGuiText("sentry_ring_embers", "Targets of melee attacks release burning embers");
        addGuiText("shroom_ring_adrenaline", "Taking damage at low health gives an Adrenaline effect for temporary stat boosts, at a price of a debuff soon after");
        addGuiText("cockatrice_feather_immunity", "Grants immunity to Inebriation");

        addGuiText("player.dialog.has_grand_medal", "I'm ready, I have a Grand Victory Medal!");

        addGuiText("vampire_amulet_night_ability", "After being worn for one overworld day (as symbolized by a gold highlight), will grant the ability to absorb dealt damage");
        addGuiText("vampire_amulet_day_debuff", "Causes more damage to be taken at day");

        addGuiText("shift_info", "Hold [%s] for more info...");
        addGuiText("infusion_tooltip", "Can be infused by right-clicking with an Ambrosium Shard");

        addGuiText("luxbuds_tooltip", "Occasionally gives the helpful Blightward effect when eaten");
        addGuiText("purified_luxbuds_tooltip", "Gives the helpful Blightward effect when eaten");


        addGuiText("config_file", "Config File: %s.json");
        addGuiText("config_category", "Current Category: %s");
        addGuiText("config_top", "Top of Hierarchy");
        addGuiText("config_page", "Page %s");

        addEntityType(ReduxEntityTypes.VANILLA_SWET, "Vanilla Swet");
        addEntityType(ReduxEntityTypes.EMBER, "Ember");
        addEntityType(ReduxEntityTypes.SPECTRAL_DART, "Spectral Dart");
        addEntityType(ReduxEntityTypes.VOLATILE_FIRE_CRYSTAL, "Volatile Fire Crystal");

        addPackConfigEnum("classic", "Classic");
        addPackConfigEnum("modern", "Modern");
        addPackConfigCategory("mob", "Mobs", "Changes to the textures of the Aether's mobs");
        addPackConfig("better_aechor_plants", "Better Aechor Plants", "Makes Aechor Plants more consistent with their petal items");
        addPackConfig("classic_cockatrices", "Cockatrice Appearance", "Switch between classic and modern textures for the upgraded Cockatrice models");
        addPackConfig("tintable_grass", "Tinted Grass", "Use modified models to allow tintable Aether Grass blocks and plants. Only disable if you know what you're doing!");
        addPackConfigCategory("dungeon", "Dungeons", "Changes to the textures of the Aether's dungeons");
        addPackConfigCategory("item", "Items", "Changes to Items from the Aether");
        addPackConfig("swet_ball_type", "Swet Ball Type", "Switch between three different behaviors for Swet Balls: The original, consistently named, and replacing them with Swet Gel");
        addPackConfigEnum("original_ball", "Original");
        addPackConfigEnum("consistent_name", "Consistent Name");
        addPackConfigEnum("gel", "Swet Gel");
        addPackConfig("veridium_type", "Veridium Type", "Switch between three different sprites for the Veridium toolset - The glowing blue Modern variant, the darker and more sleek Shadow variant, and the Classic one based on the old textures for the tools");
        addPackConfigEnum("shadow", "Shadow");
        addPackConfigCategory("gui", "GUI", "Changes to Aether GUI elements");
        addPackConfig("menu_panorama", "Menu Panorama", "Use Redux's custom main menu panorama");
        addPackConfig("use_jappafied_textures", "Use Jappafied Textures", "Uses textures designed to fit with the Jappafied Aethers resource pack slightly better");
        addPackConfig("auto_apply", "Automatic Application", "Enables the resource pack automatically when removed");
        addPackConfig("smelter_menu_type", "Smelter Menu Type", "Switch between three different types of Aether Smelter Menus: The originals, classic-styled improvemenets, and modernized menus");
        addPackConfigCategory("audio", "Audio", "Changes to different sounds heard in the Aether");
        addPackConfig("better_aechor_sounds", "Better Aechor Plant Sounds", "Improves the sounds of the Aechor Plants, giving them more natural and plant-like sounds.");
        addPackConfig("better_aerwhale_sounds", "Better Aerwhale Sounds", "Improves the sounds of Aerwhales, giving them their sounds heard in early builds of the Aether II: Highlands.");
        addPackConfig("better_cockatrice_sounds", "Better Cockatrice Sounds", "Improves the sounds of Cockatrices, giving them their sounds heard in early builds of the Aether II: Highlands.");
        addPackConfig("better_mimic_awaken_sound", "Better Mimic Awaken Sound", "Improves the sound of a Mimic appearing, making you slightly hear a sound similar to when you awaken the Slider.");
        addPackConfig("better_moa_sounds", "Better Moa Sounds", "Improves the sounds of Moas, giving them their sounds heard in early builds of the Aether II: Highlands.");
        addPackConfig("better_sentry_sounds", "Better Sentry Sounds", "Improves the sounds of Sentries, giving them some of their sounds heard in early builds of the Aether II: Highlands.");
        addPackConfig("better_slider_sounds", "Better Slider Sounds", "Improves the sounds of the Slider, with more labyrinth-themed ambient droning and a more Sentry-like hurt sound.");
        addPackConfig("better_tempest_sounds", "Better Tempest Sounds (Requires: The Aether: Genesis)", "Improves the sounds of the Aether: Genesis's Tempests, giving them their sounds heard in early builds of the Aether II: Highlands.");


        addPackTitle("overrides", "Redux - Aether Overrides");
        addPackDescription("overrides", "Configurable resource overrides for the Aether: Redux");


        addSubtitle("entity", "mimic_slam", "Mimic slams");
        addSubtitle("entity", "sentry_pounce", "Sentry pounces");
        addSubtitle("entity", "sentry_land", "Sentry lands");
        addSubtitle("entity", "sentry_ambient", "Sentry grumbles");
        addSubtitle("entity", "ember_bounce", "Ember impacts");
        addSubtitle("item.accessory", "equip_bittersweet_charm", "Bittersweet Charm jingles");
        addSubtitle("item.accessory", "equip_enchanted_ring", "Enchanted Ring thunks");
        addSubtitle("item.accessory", "equip_shroom_ring", "Shroom Ring thunks");
        addSubtitle("item.accessory", "equip_grand_medal", "Grand Victory Medal clinks");
        addSubtitle("item.accessory", "equip_wisdom_ring", "Ring of Wisdom clanks");
        addSubtitle("item.accessory", "equip_sentry_ring", "Sentry Ring clanks");
        addSubtitle("item.accessory", "equip_vampire_amulet", "Vampire Amulet clinks");
        addSubtitle("generic", "boost_jump", "Something double-jumps");
        addSubtitle("generic", "fireball_shoot", "Fireball shoots");
        addSubtitle("block", "blightshade_spray", "Blightshade sprays");
        addSubtitle("item", "convert_ambrosium", "Ambrosium enchants");
        addSubtitle("item", "convert_swet_ball", "Swet Ball squelches");
        addSubtitle("item", "convert_blighted_spores", "Blighted Spores bewitch");
        addSubtitle("item", "infuse_item", "Item infuses");
        addSubtitle("block", "quickroots_pick", "Quickroot pops");
        addSubtitle("block", "lightroots_pick", "Lightroot Clump pops");
        addSubtitle("block", "fungus_bounce", "Springshroom bounces");

        addKeyInfo("category", "The Aether: Redux");
        addKeyInfo("shoot_fireball.desc", "Shoot Fireball");

        addEffect(ReduxEffects.BLIGHTWARD, "Blightward");

        addTooltip("ambrosium_charge", "Infusion Level: %s");

        addGuiText("welcome_line1", "Welcome to the Aether: Redux!");
        addGuiText("welcome_line2", "This mod has a lot of configuration in the configs, resource packs, and data packs.");
        addGuiText("welcome_line3", "Be sure to check them all out to see all of the cool features!");
        addGuiText("welcome_line4", "This message will only appear once.");
        addGuiText("continue_delay", "The continue button will unlock in %s seconds.");
        addGuiText("continue_delay_expired", "The continue button has been unlocked.");
        addGuiText("continue_delay_unfocused", "Delay paused until window refocused! Time remaining: %s seconds");
        addGuiText("continue_delay_unmoved", "Please move the mouse to begin continue button cooldown.");
        addGuiText("continue_to_menu", "Continue to Main Menu");
        addGuiText("jei.infusion_charge", "Increases Infusion Level by %s");
        addGuiText("jei.infusion", "Infusion");

        addAdvancement("fall_from_aether", "Falling with Style!");
        addAdvancementDesc("fall_from_aether", "Fall out of the Aether");
        addAdvancement("infuse_veridium", "Next Level");
        addAdvancementDesc("infuse_veridium", "Infuse a Veridium weapon or tool by right-clicking it with an Ambrosium Shard");
        addAdvancement("enter_blight", "Shattered Glass");
        addAdvancementDesc("enter_blight", "Enter the Blight biome, a dangerous zone filled with hazardous plants and creatures");
        addAdvancement("convert_with_blighted_spores", "Dark Magic");
        addAdvancementDesc("convert_with_blighted_spores", "Blight a block by right-clicking it while holding some Blighted Spores");
        addAdvancement("obtain_subzero_crossbow", "Below Zero");
        addAdvancementDesc("obtain_subzero_crossbow", "Obtain the Subzero Crossbow, a chilling ranged weapon found in the Gold Dungeon");

        addAdvancement("obtain_purified_luxbuds", "Blight's Bane");
        addAdvancementDesc("obtain_purified_luxbuds", "Obtain Purified Luxbuds, which give extended resistance to many of the Blight's dangers when eaten");

        addAdvancement("double_jump", "Take THAT, Physics!");
        addAdvancementDesc("double_jump", "Obtain an Airbound cape from a Bronze Dungeon and use it to double-jump");
//        addAdvancement("mine_extended", "Telekinesis");
//        addAdvancementDesc("mine_extended", "Obtain the Valkyrie Ring from the Silver Dungeon and use it to mine a block farther away than you could before");
        addAdvancement("kill_sheepuff_with_fireball", "Pyromaniac");
        addAdvancementDesc("kill_sheepuff_with_fireball", "Obtain the Phoenix Emblem from the Gold Dungeon and kill a Sheepuff with a fireball");

        addAdvancement("enter_highfields", "Prismacolor Photosynthesis");
        addAdvancementDesc("enter_highfields", "Enter the Highfields, a biome filled with colorful flora and abundant fauna");

        addAdvancement("enter_gilded_groves", "All That Glitters");
        addAdvancementDesc("enter_gilded_groves", "Enter the Gilded Groves, a golden forest filled with Golden Amber");

        addAdvancement("enter_frosted_forests", "Walking in a Winter Wonderland");
        addAdvancementDesc("enter_frosted_forests", "Enter the Frosted Forests, an ice-cold forest covered in snow");

        addAdvancement("enter_cloudcap_jungle", "Moolander");
        addAdvancementDesc("enter_cloudcap_jungle", "Enter the Cloudcaps, a fungal forest with various types of mushrooms");

        addDeath(ReduxDamageTypes.CHROMATIC_SHRUB, "%1$s was poked to death by a chromatic shrub, how sad");
        addDeath(ReduxDamageTypes.BLIGHTED_FUNGI, "%1$s was poked to death by a patch of blighted fungi");
        addDeath(ReduxDamageTypes.EMBER, "%1$s was hit by a flying ember");
        addDeathByPlayer(ReduxDamageTypes.EMBER, "%1$s was hit by %2$s's flying ember");

        for (WoodHandler woodHandler : Redux.Handlers.Wood.WOOD_HANDLERS) {
            woodHandler.generateLanguageData(this);
        }

    }


    public void addTooltip(String key, String name) {
        this.add("tooltip." + Redux.MODID + "." + key, name);
    }

    public void addPackConfig(String key, String name) {
        this.addGuiText("pack_config.config." + key, name);
    }
    public void addPackConfigDesc(String key, String name) {
        this.addGuiText("pack_config.config_desc." + key, name);
    }
    public void addPackConfigCategory(String key, String name) {
        this.addGuiText("pack_config.category." + key, name);
    }
    public void addPackConfigCategoryDesc(String key, String name) {
        this.addGuiText("pack_config.category_desc." + key, name);
    }
    public void addPackConfigEnum(String key, String name) {
        this.addGuiText("pack_config.enums." + key, name);
    }

    public void addPackConfigCategory(String key, String name, String desc) {
        this.addPackConfigCategory(key, name);
        this.addPackConfigCategoryDesc(key, desc);
    }


    public void addDeath(ResourceKey<DamageType> key, String name) {
        this.add("death.attack." + this.id + "." + key.location().getPath(), name);
    }
    public void addDeathByPlayer(ResourceKey<DamageType> key, String name) {
        this.add("death.attack." + this.id + "." + key.location().getPath() + ".player", name);
    }

    public void addPackConfig(String key, String name, String desc) {
        this.addPackConfig(key, name);
        this.addPackConfigDesc(key, desc);
    }

    @Override
    public void addBiome(ResourceKey<Biome> biome, String name) {
        super.addBiome(biome, name);
        this.add("biomes." + this.id + "." + biome.location().getPath(), name);
    }
}
