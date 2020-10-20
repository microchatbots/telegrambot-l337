package groovycalamari.bots.l337;

import io.micronaut.context.annotation.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("l337")
public class L337Configuration {

    private List<String> allowedUsers;

    public List<String> getAllowedUsers() {
        return allowedUsers;
    }

    public void setAllowedUsers(List<String> allowedUsers) {
        this.allowedUsers = allowedUsers;
    }
}
