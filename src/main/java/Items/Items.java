package Items;

import org.bukkit.Material;

public class Items {
    public static Material[] shortitem = {
            // Wooden tools
            Material.WOODEN_AXE, Material.WOODEN_PICKAXE, Material.WOODEN_SWORD, Material.WOODEN_SHOVEL, Material.WOODEN_HOE,
            // Stone tools
            Material.STONE_AXE, Material.STONE_PICKAXE, Material.STONE_SWORD, Material.STONE_SHOVEL, Material.STONE_HOE,
            // Basic blocks
            Material.COBBLESTONE, Material.DIRT, Material.COARSE_DIRT, Material.GRASS_BLOCK, Material.PODZOL, Material.MYCELIUM,
            Material.SAND, Material.RED_SAND, Material.GRAVEL, Material.CLAY, Material.TERRACOTTA,
            Material.SANDSTONE, Material.RED_SANDSTONE, Material.SMOOTH_SANDSTONE,
            Material.ANDESITE, Material.DIORITE, Material.GRANITE, Material.CALCITE, Material.TUFF, Material.DRIPSTONE_BLOCK,
            Material.DEEPSLATE, Material.COBBLED_DEEPSLATE, Material.MUD, Material.PACKED_MUD, Material.MUD_BRICKS,
            // All wood types (including 1.20+)
            Material.OAK_LOG, Material.BIRCH_LOG, Material.SPRUCE_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG,
            Material.CHERRY_LOG, Material.MANGROVE_LOG, Material.BAMBOO_BLOCK,
            Material.OAK_PLANKS, Material.BIRCH_PLANKS, Material.SPRUCE_PLANKS, Material.JUNGLE_PLANKS,
            Material.ACACIA_PLANKS, Material.DARK_OAK_PLANKS, Material.CHERRY_PLANKS, Material.MANGROVE_PLANKS, Material.BAMBOO_PLANKS,
            Material.BAMBOO_MOSAIC, Material.OAK_WOOD, Material.STRIPPED_OAK_LOG, Material.STRIPPED_OAK_WOOD,
            // Basic items
            Material.STICK, Material.COAL, Material.CHARCOAL, Material.FLINT, Material.BONE, Material.BONE_MEAL,
            Material.STRING, Material.FEATHER, Material.LEATHER, Material.RABBIT_HIDE, Material.ROTTEN_FLESH,
            Material.COPPER_INGOT, Material.RAW_COPPER, Material.BRUSH,
            // Food
            Material.BREAD, Material.COOKED_BEEF, Material.COOKED_PORKCHOP, Material.COOKED_CHICKEN, Material.COOKED_MUTTON,
            Material.COOKED_RABBIT, Material.COOKED_COD, Material.COOKED_SALMON, Material.APPLE, Material.COOKIE,
            Material.BEETROOT_SOUP, Material.MUSHROOM_STEW, Material.SUSPICIOUS_STEW, Material.DRIED_KELP,
            // Plants & crops
            Material.WHEAT, Material.WHEAT_SEEDS, Material.BEETROOT, Material.BEETROOT_SEEDS, Material.CARROT, Material.POTATO,
            Material.PUMPKIN, Material.CARVED_PUMPKIN, Material.MELON, Material.MELON_SLICE, Material.SUGAR_CANE,
            Material.CACTUS, Material.BROWN_MUSHROOM, Material.RED_MUSHROOM, Material.BAMBOO, Material.KELP, Material.SEAGRASS,
            Material.SWEET_BERRIES, Material.GLOW_BERRIES, Material.COCOA_BEANS, Material.TORCHFLOWER, Material.TORCHFLOWER_SEEDS,
            Material.PITCHER_PLANT, Material.PITCHER_POD, Material.PINK_PETALS, Material.SPORE_BLOSSOM,
            Material.HANGING_ROOTS, Material.MOSS_BLOCK, Material.MOSS_CARPET, Material.AZALEA, Material.FLOWERING_AZALEA,
            // Crafted items
            Material.CRAFTING_TABLE, Material.FURNACE, Material.CHEST, Material.BARREL, Material.SMOKER, Material.BLAST_FURNACE,
            Material.TORCH, Material.SOUL_TORCH, Material.CAMPFIRE, Material.SOUL_CAMPFIRE, Material.LADDER,
            Material.OAK_FENCE, Material.OAK_FENCE_GATE, Material.OAK_DOOR, Material.OAK_TRAPDOOR,
            Material.OAK_STAIRS, Material.OAK_SLAB, Material.OAK_PRESSURE_PLATE, Material.OAK_BUTTON,
            Material.CHERRY_DOOR, Material.CHERRY_FENCE, Material.CHERRY_HANGING_SIGN, Material.BAMBOO_DOOR, Material.BAMBOO_RAFT,
            Material.BOWL, Material.WHITE_BED, Material.WHITE_WOOL, Material.WHITE_CARPET,
            Material.PAINTING, Material.ITEM_FRAME, Material.GLOW_ITEM_FRAME, Material.FLOWER_POT,
            Material.GLASS, Material.GLASS_PANE, Material.TINTED_GLASS, Material.BRICK, Material.BRICKS, Material.CANDLE,
            // Mob drops
            Material.EGG, Material.SPIDER_EYE, Material.GUNPOWDER, Material.SLIME_BALL, Material.SNIFFER_EGG,
            // Flowers & dyes
            Material.DANDELION, Material.POPPY, Material.BLUE_ORCHID, Material.ALLIUM, Material.AZURE_BLUET,
            Material.OXEYE_DAISY, Material.CORNFLOWER, Material.LILY_OF_THE_VALLEY, Material.SUNFLOWER, Material.LILAC,
            Material.ROSE_BUSH, Material.PEONY, Material.TORCHFLOWER,
            Material.WHITE_DYE, Material.RED_DYE, Material.ORANGE_DYE, Material.YELLOW_DYE, Material.LIME_DYE,
            Material.GREEN_DYE, Material.CYAN_DYE, Material.LIGHT_BLUE_DYE, Material.BLUE_DYE, Material.PURPLE_DYE,
            Material.MAGENTA_DYE, Material.PINK_DYE, Material.BLACK_DYE, Material.GRAY_DYE, Material.LIGHT_GRAY_DYE,
            Material.BROWN_DYE, Material.INK_SAC,
            // Misc
            Material.SNOWBALL, Material.ICE, Material.PACKED_ICE, Material.SNOW_BLOCK, Material.CLAY_BALL,
            Material.LILY_PAD, Material.VINE, Material.GLOW_LICHEN, Material.OCHRE_FROGLIGHT
    };
    // 15-40 minute runs: Mid game items requiring iron/nether access
    public static Material[] miditem = {
            // Iron tools & armor
            Material.IRON_INGOT, Material.IRON_BLOCK, Material.IRON_NUGGET, Material.RAW_IRON, Material.RAW_IRON_BLOCK,
            Material.IRON_PICKAXE, Material.IRON_AXE, Material.IRON_SWORD, Material.IRON_SHOVEL, Material.IRON_HOE,
            Material.IRON_CHESTPLATE, Material.IRON_HELMET, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
            // Gold items
            Material.GOLD_INGOT, Material.GOLD_BLOCK, Material.GOLD_NUGGET, Material.RAW_GOLD, Material.RAW_GOLD_BLOCK,
            Material.GOLDEN_PICKAXE, Material.GOLDEN_AXE, Material.GOLDEN_SWORD, Material.GOLDEN_SHOVEL, Material.GOLDEN_HOE,
            Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
            Material.GOLDEN_APPLE, Material.GOLDEN_CARROT, Material.GLISTERING_MELON_SLICE,
            // Chainmail & Leather armor
            Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS,
            Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
            Material.LEATHER_HORSE_ARMOR, Material.IRON_HORSE_ARMOR,
            // Iron crafted items
            Material.BUCKET, Material.WATER_BUCKET, Material.LAVA_BUCKET, Material.MILK_BUCKET, Material.POWDER_SNOW_BUCKET,
            Material.AXOLOTL_BUCKET, Material.COD_BUCKET, Material.SALMON_BUCKET, Material.PUFFERFISH_BUCKET, Material.TROPICAL_FISH_BUCKET, Material.TADPOLE_BUCKET,
            Material.FLINT_AND_STEEL, Material.SHIELD, Material.SHEARS, Material.COMPASS, Material.RECOVERY_COMPASS, Material.CLOCK,
            Material.IRON_DOOR, Material.IRON_TRAPDOOR, Material.IRON_BARS, Material.CHAIN, Material.HOPPER, Material.CAULDRON,
            Material.TRIPWIRE_HOOK, Material.HEAVY_WEIGHTED_PRESSURE_PLATE, Material.LIGHT_WEIGHTED_PRESSURE_PLATE,
            // Minecarts & rails
            Material.MINECART, Material.CHEST_MINECART, Material.FURNACE_MINECART, Material.HOPPER_MINECART, Material.TNT_MINECART,
            Material.RAIL, Material.POWERED_RAIL, Material.DETECTOR_RAIL, Material.ACTIVATOR_RAIL,
            // Nether items
            Material.OBSIDIAN, Material.CRYING_OBSIDIAN, Material.NETHERRACK, Material.NETHER_BRICKS, Material.RED_NETHER_BRICKS, Material.CHISELED_NETHER_BRICKS,
            Material.SOUL_SAND, Material.SOUL_SOIL, Material.BASALT, Material.SMOOTH_BASALT, Material.POLISHED_BASALT,
            Material.BLACKSTONE, Material.POLISHED_BLACKSTONE, Material.GILDED_BLACKSTONE, Material.CHISELED_POLISHED_BLACKSTONE,
            Material.NETHER_WART, Material.NETHER_WART_BLOCK, Material.WARPED_FUNGUS, Material.CRIMSON_FUNGUS,
            Material.WARPED_ROOTS, Material.CRIMSON_ROOTS, Material.WARPED_WART_BLOCK, Material.SHROOMLIGHT,
            Material.CRIMSON_STEM, Material.WARPED_STEM, Material.CRIMSON_PLANKS, Material.WARPED_PLANKS,
            Material.MAGMA_BLOCK, Material.MAGMA_CREAM, Material.GLOWSTONE, Material.GLOWSTONE_DUST,
            Material.BLAZE_ROD, Material.BLAZE_POWDER, Material.GHAST_TEAR, Material.ENDER_PEARL, Material.ENDER_EYE,
            Material.FIRE_CHARGE, Material.QUARTZ, Material.QUARTZ_BLOCK, Material.NETHER_GOLD_ORE, Material.ANCIENT_DEBRIS,
            // Redstone
            Material.REDSTONE, Material.REDSTONE_BLOCK, Material.REDSTONE_TORCH, Material.REDSTONE_LAMP,
            Material.REPEATER, Material.COMPARATOR, Material.OBSERVER, Material.DISPENSER, Material.DROPPER,
            Material.PISTON, Material.STICKY_PISTON, Material.TNT, Material.LEVER, Material.STONE_BUTTON, Material.COPPER_BULB,
            Material.NOTE_BLOCK, Material.DAYLIGHT_DETECTOR, Material.TRIPWIRE_HOOK, Material.LIGHTNING_ROD,
            // Books & enchanting prep
            Material.BOOK, Material.WRITABLE_BOOK, Material.WRITTEN_BOOK, Material.BOOKSHELF, Material.CHISELED_BOOKSHELF,
            Material.PAPER, Material.MAP, Material.FILLED_MAP, Material.GLOBE_BANNER_PATTERN,
            Material.LECTERN, Material.LAPIS_LAZULI, Material.LAPIS_BLOCK,
            // Food & potions prep
            Material.BAKED_POTATO, Material.PUMPKIN_PIE, Material.MUSHROOM_STEW, Material.RABBIT_STEW, Material.SUSPICIOUS_STEW,
            Material.HONEY_BOTTLE, Material.HONEYCOMB, Material.HONEY_BLOCK, Material.HONEYCOMB_BLOCK,
            Material.SUGAR, Material.SPIDER_EYE, Material.FERMENTED_SPIDER_EYE, Material.POISONOUS_POTATO,
            // Combat & transport
            Material.BOW, Material.ARROW, Material.SPECTRAL_ARROW, Material.TIPPED_ARROW, Material.CROSSBOW,
            Material.FISHING_ROD, Material.TRIDENT, Material.SPYGLASS, Material.BRUSH,
            Material.SADDLE, Material.LEAD, Material.NAME_TAG, Material.TURTLE_HELMET,
            Material.WOLF_ARMOR, Material.ARMADILLO_SCUTE, Material.GOAT_HORN,
            // Blocks
            Material.STONE_BRICKS, Material.CHISELED_STONE_BRICKS, Material.MOSSY_STONE_BRICKS, Material.CRACKED_STONE_BRICKS,
            Material.POLISHED_ANDESITE, Material.POLISHED_DIORITE, Material.POLISHED_GRANITE,
            Material.POLISHED_DEEPSLATE, Material.DEEPSLATE_BRICKS, Material.CRACKED_DEEPSLATE_BRICKS,
            Material.PRISMARINE_SHARD, Material.PRISMARINE_CRYSTALS,
            // Copper items
            Material.COPPER_BLOCK, Material.EXPOSED_COPPER, Material.WEATHERED_COPPER, Material.OXIDIZED_COPPER,
            Material.CUT_COPPER, Material.WAXED_COPPER_BLOCK, Material.COPPER_GRATE, Material.COPPER_DOOR,
            // Mob drops
            Material.OCHRE_FROGLIGHT, Material.VERDANT_FROGLIGHT, Material.PEARLESCENT_FROGLIGHT,
            Material.ECHO_SHARD, Material.DISC_FRAGMENT_5,
            // Pottery & archaeology
            Material.SUSPICIOUS_SAND, Material.SUSPICIOUS_GRAVEL, Material.DECORATED_POT
    };
    // 40+ minute runs: Late game/rare items
    public static Material[] longitem = {
            // Diamond tools & armor
            Material.DIAMOND, Material.DIAMOND_BLOCK,
            Material.DIAMOND_PICKAXE, Material.DIAMOND_AXE, Material.DIAMOND_SWORD, Material.DIAMOND_SHOVEL, Material.DIAMOND_HOE,
            Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
            Material.DIAMOND_HORSE_ARMOR,
            // Netherite tools & armor
            Material.NETHERITE_INGOT, Material.NETHERITE_BLOCK, Material.NETHERITE_SCRAP, Material.ANCIENT_DEBRIS,
            Material.NETHERITE_PICKAXE, Material.NETHERITE_AXE, Material.NETHERITE_SWORD, Material.NETHERITE_SHOVEL, Material.NETHERITE_HOE,
            Material.NETHERITE_CHESTPLATE, Material.NETHERITE_HELMET, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS,
            Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
            // Boss & rare drops
            Material.NETHER_STAR, Material.BEACON, Material.DRAGON_EGG, Material.DRAGON_HEAD, Material.DRAGON_BREATH,
            Material.ELYTRA, Material.TOTEM_OF_UNDYING, Material.ENCHANTED_GOLDEN_APPLE,
            Material.WITHER_SKELETON_SKULL, Material.SKELETON_SKULL, Material.ZOMBIE_HEAD, Material.CREEPER_HEAD,
            Material.PIGLIN_HEAD, Material.PLAYER_HEAD,
            // End items
            Material.END_STONE, Material.END_STONE_BRICKS, Material.PURPUR_BLOCK, Material.PURPUR_PILLAR, Material.PURPUR_STAIRS,
            Material.CHORUS_FRUIT, Material.POPPED_CHORUS_FRUIT, Material.CHORUS_FLOWER, Material.CHORUS_PLANT,
            Material.SHULKER_BOX, Material.WHITE_SHULKER_BOX, Material.ORANGE_SHULKER_BOX, Material.MAGENTA_SHULKER_BOX,
            Material.LIGHT_BLUE_SHULKER_BOX, Material.YELLOW_SHULKER_BOX, Material.LIME_SHULKER_BOX, Material.PINK_SHULKER_BOX,
            Material.PURPLE_SHULKER_BOX, Material.BLUE_SHULKER_BOX, Material.CYAN_SHULKER_BOX, Material.SHULKER_SHELL,
            Material.END_CRYSTAL, Material.END_ROD, Material.ENDER_CHEST,
            // Ocean monument & underwater
            Material.PRISMARINE, Material.PRISMARINE_BRICKS, Material.DARK_PRISMARINE, Material.SEA_LANTERN,
            Material.SPONGE, Material.WET_SPONGE, Material.TRIDENT, Material.NAUTILUS_SHELL, Material.HEART_OF_THE_SEA,
            Material.CONDUIT, Material.TURTLE_EGG, Material.TURTLE_HELMET,
            // Enchanting & brewing
            Material.ENCHANTING_TABLE, Material.ENCHANTED_BOOK, Material.EXPERIENCE_BOTTLE,
            Material.BREWING_STAND, Material.POTION, Material.SPLASH_POTION, Material.LINGERING_POTION,
            Material.ANVIL, Material.CHIPPED_ANVIL, Material.DAMAGED_ANVIL, Material.GRINDSTONE, Material.SMITHING_TABLE,
            // Emeralds & trading
            Material.EMERALD, Material.EMERALD_BLOCK,
            // Music discs (all)
            Material.MUSIC_DISC_13, Material.MUSIC_DISC_CAT, Material.MUSIC_DISC_BLOCKS, Material.MUSIC_DISC_CHIRP,
            Material.MUSIC_DISC_FAR, Material.MUSIC_DISC_MALL, Material.MUSIC_DISC_MELLOHI, Material.MUSIC_DISC_STAL,
            Material.MUSIC_DISC_STRAD, Material.MUSIC_DISC_WARD, Material.MUSIC_DISC_11, Material.MUSIC_DISC_WAIT,
            Material.MUSIC_DISC_PIGSTEP, Material.MUSIC_DISC_OTHERSIDE, Material.MUSIC_DISC_5, Material.MUSIC_DISC_RELIC,
            Material.MUSIC_DISC_CREATOR, Material.MUSIC_DISC_CREATOR_MUSIC_BOX, Material.MUSIC_DISC_PRECIPICE,
            Material.JUKEBOX,
            // Rare blocks
            Material.LODESTONE, Material.RESPAWN_ANCHOR, Material.BELL, Material.LANTERN, Material.SOUL_LANTERN,
            Material.DECORATED_POT, Material.SUSPICIOUS_SAND, Material.SUSPICIOUS_GRAVEL,
            // Spawners & infested
            Material.SPAWNER, Material.TRIAL_SPAWNER, Material.VAULT, Material.OMINOUS_TRIAL_KEY, Material.TRIAL_KEY,
            Material.INFESTED_STONE, Material.INFESTED_COBBLESTONE, Material.INFESTED_STONE_BRICKS,
            // Advanced redstone & skulk
            Material.TARGET, Material.SCULK_SENSOR, Material.CALIBRATED_SCULK_SENSOR, Material.SCULK_SHRIEKER,
            Material.SCULK, Material.SCULK_VEIN, Material.SCULK_CATALYST,
            // Rare ores & materials
            Material.COAL_BLOCK, Material.COPPER_BLOCK, Material.AMETHYST_SHARD, Material.AMETHYST_BLOCK, Material.AMETHYST_CLUSTER,
            Material.BUDDING_AMETHYST, Material.LARGE_AMETHYST_BUD, Material.MEDIUM_AMETHYST_BUD, Material.SMALL_AMETHYST_BUD,
            Material.ECHO_SHARD, Material.RECOVERY_COMPASS,
            // Special items
            Material.SPYGLASS, Material.BUNDLE, Material.GOAT_HORN, Material.ARMADILLO_SCUTE, Material.WOLF_ARMOR,
            Material.TNT, Material.BONE_BLOCK, Material.SLIME_BLOCK, Material.HONEY_BLOCK,
            // Banners & decorative
            Material.WHITE_BANNER, Material.ORANGE_BANNER, Material.MAGENTA_BANNER, Material.LIGHT_BLUE_BANNER,
            Material.SHIELD, Material.ARMOR_STAND, Material.GLOW_ITEM_FRAME, Material.ITEM_FRAME,
            // Rare mob drops & materials
            Material.PHANTOM_MEMBRANE, Material.RABBIT_FOOT, Material.GLOW_INK_SAC, Material.BREEZE_ROD,
            Material.WIND_CHARGE, Material.MACE, Material.HEAVY_CORE,
            // Nether special
            Material.CRIMSON_STEM, Material.WARPED_STEM, Material.SHROOMLIGHT, Material.WEEPING_VINES, Material.TWISTING_VINES,
            // Armor trims (1.20+)
            Material.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, Material.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE,
            Material.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, Material.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE,
            Material.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE, Material.HOST_ARMOR_TRIM_SMITHING_TEMPLATE,
            Material.WARD_ARMOR_TRIM_SMITHING_TEMPLATE, Material.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE,
            Material.VEX_ARMOR_TRIM_SMITHING_TEMPLATE, Material.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE,
            Material.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE, Material.RIB_ARMOR_TRIM_SMITHING_TEMPLATE,
            Material.EYE_ARMOR_TRIM_SMITHING_TEMPLATE, Material.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE,
            Material.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, Material.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE,
            Material.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE, Material.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE,
            // Banner patterns
            Material.CREEPER_BANNER_PATTERN, Material.SKULL_BANNER_PATTERN, Material.FLOWER_BANNER_PATTERN,
            Material.MOJANG_BANNER_PATTERN, Material.PIGLIN_BANNER_PATTERN, Material.FLOW_BANNER_PATTERN,
            // Special end game
            Material.DRAGON_BREATH, Material.SHULKER_SHELL, Material.HEART_OF_THE_SEA, Material.NETHER_STAR
    };
}
