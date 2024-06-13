package in.co.ad.springboot.wrapstream.config;


import org.apache.kafka.common.config.types.Password;
import org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule;
import org.apache.kafka.common.security.plain.PlainLoginModule;
import org.apache.kafka.common.security.scram.ScramLoginModule;
import org.apache.kafka.common.security.scram.internals.ScramMechanism;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.util.*;

public class JaasConfigCustom extends Configuration {
    static final String LOGIN_CONTEXT_CLIENT = "KafkaClient";
    static final String LOGIN_CONTEXT_SERVER = "KafkaServer";
    static final String USERNAME = "myuser";
    static final String PASSWORD = "mypassword";
    private Map<String, AppConfigurationEntry[]> entryMap = new HashMap();

    public JaasConfigCustom() {
    }

    public static JaasConfigCustom createConfiguration(String clientMechanism, List<String> serverMechanisms) {
        JaasConfigCustom config = new JaasConfigCustom();
        config.createOrUpdateEntry("KafkaClient", loginModule(clientMechanism), defaultClientOptions(clientMechanism));
        Iterator var3 = serverMechanisms.iterator();

        while (var3.hasNext()) {
            String mechanism = (String) var3.next();
            config.addEntry("KafkaServer", loginModule(mechanism), defaultServerOptions(mechanism));
        }

        Configuration.setConfiguration(config);
        return config;
    }

    public static Password jaasConfigProperty(String mechanism, String username, String password) {
        return new Password(loginModule(mechanism) + " required username=" + username + " password=" + password + ";");
    }

    public static Password jaasConfigProperty(String mechanism, Map<String, Object> options) {
        StringBuilder builder = new StringBuilder();
        builder.append(loginModule(mechanism));
        builder.append(" required");
        Iterator var3 = options.entrySet().iterator();

        while (var3.hasNext()) {
            Map.Entry<String, Object> option = (Map.Entry) var3.next();
            builder.append(' ');
            builder.append((String) option.getKey());
            builder.append('=');
            builder.append(option.getValue());
        }

        builder.append(';');
        return new Password(builder.toString());
    }

    public void setClientOptions(String saslMechanism, String clientUsername, String clientPassword) {
        Map<String, Object> options = new HashMap();
        if (clientUsername != null) {
            options.put("username", clientUsername);
        }

        if (clientPassword != null) {
            options.put("password", clientPassword);
        }

        Class<?> loginModuleClass = ScramMechanism.isScram(saslMechanism) ? ScramLoginModule.class : PlainLoginModule.class;
        this.createOrUpdateEntry("KafkaClient", loginModuleClass.getName(), options);
    }

    public void createOrUpdateEntry(String name, String loginModule, Map<String, Object> options) {
        AppConfigurationEntry entry = new AppConfigurationEntry(loginModule, AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options);
        this.entryMap.put(name, new AppConfigurationEntry[]{entry});
    }

    public void addEntry(String name, String loginModule, Map<String, Object> options) {
        AppConfigurationEntry entry = new AppConfigurationEntry(loginModule, AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, options);
        AppConfigurationEntry[] existing = (AppConfigurationEntry[]) this.entryMap.get(name);
        AppConfigurationEntry[] newEntries = existing == null ? new AppConfigurationEntry[1] : (AppConfigurationEntry[]) Arrays.copyOf(existing, existing.length + 1);
        newEntries[newEntries.length - 1] = entry;
        this.entryMap.put(name, newEntries);
    }

    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        return (AppConfigurationEntry[]) this.entryMap.get(name);
    }

    private static String loginModule(String mechanism) {
        String loginModule;
        switch (mechanism) {
            case "PLAIN":
                loginModule = PlainLoginModule.class.getName();
                break;
            case "OAUTHBEARER":
                loginModule = OAuthBearerLoginModule.class.getName();
                break;
            default:
                if (!ScramMechanism.isScram(mechanism)) {
                    throw new IllegalArgumentException("Unsupported mechanism " + mechanism);
                }

                loginModule = ScramLoginModule.class.getName();
        }

        return loginModule;
    }

    public static Map<String, Object> defaultClientOptions(String mechanism) {
        switch (mechanism) {
            case "OAUTHBEARER":
                Map<String, Object> options = new HashMap();
                options.put("unsecuredLoginStringClaim_sub", "myuser");
                return options;
            default:
                return defaultClientOptions();
        }
    }

    public static Map<String, Object> defaultClientOptions() {
        Map<String, Object> options = new HashMap();
        options.put("username", "myuser");
        options.put("password", "mypassword");
        return options;
    }

    public static Map<String, Object> defaultServerOptions(String mechanism) {
        Map<String, Object> options = new HashMap();
        switch (mechanism) {
            case "PLAIN":
            case "DIGEST-MD5":
                options.put("user_myuser", "mypassword");
                break;
            case "OAUTHBEARER":
                options.put("unsecuredLoginStringClaim_sub", "myuser");
                break;
            default:
                if (!ScramMechanism.isScram(mechanism)) {
                    throw new IllegalArgumentException("Unsupported mechanism " + mechanism);
                }
        }

        return options;
    }
}
