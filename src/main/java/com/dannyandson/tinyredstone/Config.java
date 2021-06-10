package com.dannyandson.tinyredstone;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class Config {
    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static final String CATEGORY_FEATURE = "feature";
    public static final String CATEGORY_PERFORMANCE = "performance";
    public static ForgeConfigSpec.BooleanValue TORCH_LIGHT;
    public static ForgeConfigSpec.IntValue DISPLAY_MODE;
    public static ForgeConfigSpec.BooleanValue JSON_BLUEPRINT;
    public static ForgeConfigSpec.IntValue SUPER_REPEATER_MAX;
    public static ForgeConfigSpec.IntValue CIRCUIT_MAX_ITERATION;

    static {

        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();

        SERVER_BUILDER.comment("Feature Settings").push(CATEGORY_FEATURE);

        JSON_BLUEPRINT = SERVER_BUILDER.comment("Should it be possible to export or import the blueprint as json? (default:true)")
                .define("json_blueprint",true);

        SERVER_BUILDER.pop();

        SERVER_BUILDER.comment("Performance Settings").push(CATEGORY_PERFORMANCE);

        TORCH_LIGHT = SERVER_BUILDER.comment("Should redstone torches output light to the surrounding area? (default:false)")
                .define("torch_light",false);

        SUPER_REPEATER_MAX = SERVER_BUILDER.comment("Maximum redstone tick delay for super repeaters. 10 redstone ticks = 1 second." +
                "\nLarge numbers require more memory. (default:100)")
                .defineInRange("super_repeater_max",100,4,Integer.MAX_VALUE);

        CIRCUIT_MAX_ITERATION = SERVER_BUILDER.comment("How many blocks long can a line of redstone run in a single tick?" +
                "\nThis number determines approximately 2x how many zero tick super repeaters can extend a single redstone line?" +
                "\n(Since each repeater can extend signal 2 full blocks.)" +
                "\nVery large numbers may degrade performance and potentially risk crash. (default=32)")
                .defineInRange("max_zero_tick_run",32,4,512);

        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();

        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("Performance Settings").push(CATEGORY_PERFORMANCE);

        DISPLAY_MODE = CLIENT_BUILDER.comment("When should the information be displayed in the overlay? 0 = no, 1 = always, 2 = only in extended or debug, 3 = when you have a wrench in your hand, 4 = when you have any component in your hand")
                .defineInRange("display_mode", 1, 0, 4);

        CLIENT_BUILDER.pop();

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }


}
