package groovycalamari.bots.l337;

import com.microchatbots.core.parser.SpaceParser;
import com.microchatbots.core.parser.TextParser;
import com.microchatbots.core.parser.UserParser;
import com.microchatbots.telegrambots.api.TelegramBotConfiguration;
import com.microchatbots.telegrambots.core.Update;
import com.microchatbots.telegrambots.handler.SendMessageRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.inject.Singleton;
import java.io.Serializable;
import java.util.Optional;

@Singleton
public class AccessRequestHander extends SendMessageRequestHandler {
    private static final Logger LOG = LoggerFactory.getLogger(AccessRequestHander.class);

    private final UserParser<Update> userParser;
    private final L337Configuration l337Configuration;

    public AccessRequestHander(SpaceParser<Update> spaceParser,
                               TextParser<Update> textParser,
                               UserParser<Update> userParser,
                               L337Configuration l337Configuration) {
        super(spaceParser, textParser);
        this.userParser = userParser;
        this.l337Configuration = l337Configuration;
    }

    @Override
    public Optional<String> getResponse(String text) {
        return Optional.of("Access denied");
    }

    @Override
    public boolean canHandle(TelegramBotConfiguration telegramBotConfiguration, Update update) {
        Optional<Serializable> userIdOptional = userParser.parseUserUniqueIdentifier(telegramBotConfiguration, update);
        if (userIdOptional.isPresent()) {
            String userId = userIdOptional.get().toString();
            if (LOG.isInfoEnabled()) {
                LOG.info("user id: {}", userId);
            }
            return !l337Configuration.getAllowedUsers().contains(userId);
        }
        return true;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }
}
