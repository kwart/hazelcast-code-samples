import com.hazelcast.config.Config;
import com.hazelcast.config.UserCodeDeploymentConfig;
import com.hazelcast.config.UserCodeDeploymentConfig.ClassCacheMode;
import com.hazelcast.core.Hazelcast;

public class Member {

    public static void main(String[] args) {
        Config config = new Config();
        UserCodeDeploymentConfig userCodeDeploymentConfig = new UserCodeDeploymentConfig();
        userCodeDeploymentConfig.setEnabled(true);
        userCodeDeploymentConfig.setClassCacheMode(ClassCacheMode.OFF);
        config.setUserCodeDeploymentConfig(userCodeDeploymentConfig);
        Hazelcast.newHazelcastInstance(config);
    }
}
