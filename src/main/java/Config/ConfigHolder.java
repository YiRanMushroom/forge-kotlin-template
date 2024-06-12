package Config;

import com.pleahmacaka.examplemod.ExampleMod;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

import static com.pleahmacaka.examplemod.ExampleModKt.MODID;

@Config(id = MODID)
public class ConfigHolder {
    public static ConfigHolder INSTANCE;

    public static void init(){
        INSTANCE = Configuration.registerConfig(ConfigHolder.class, ConfigFormats.yaml()).getConfigInstance();
    }

    @Configurable
    @Configurable.Comment({"This is an example configuration", "Default: true"})
    public boolean exampleBooleanConfig = true;
}
