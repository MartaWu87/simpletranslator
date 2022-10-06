package io10a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class GreetingService {

    private Logger logger = LoggerFactory.getLogger(GreetingService.class.getName());
    private HashMap<String, String> greetings = new HashMap<String, String>();

    {
        greetings.put("fr", "Bonjour");
        greetings.put("es", "Hola");
        greetings.put("gb", "Good Morning");
        greetings.put("de", "Guten Tag");
        greetings.put("ru", "Zdrastwujtie");
        greetings.put("ar", "Salam alejkum");
        greetings.put("it", "Salve");
        greetings.put("pl", "Dzień dobry");
        greetings.put("cz", "Ahoj");

    }

    public String sayHello(String name) {
        logger.info("user check existing country " + name);
        if (name == null) {
            throw new NotFoundException();
        }
        if (greetings.containsKey(name)) {
            return greetings.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().equals(name))
                    .map(Map.Entry::getValue).toList()
                    .stream().findFirst().get();
        }
        logger.warn("code not exist or is wrong :" + name);
        return "no language selected";
    }

    public void newHello(Language language) {
        if (greetings.containsKey(language.getName())) {
            logger.info("code exist : " + language.getName());
        } else {
            greetings.put(language.getName(), language.getValue());
            logger.info("code didn`t exist : " + language.getName());
        }
    }

    public void editHello(String name, Language language) {
        logger.info("user try edit something");
        if (language.getName().equals(name)) {
            greetings.put(name, language.getValue());
        } else {
            throw new NotFoundException();
        }
    }
    public String showAllHello() {
        logger.info("user check all positions");
        String mapAsString = greetings.keySet().stream()
                .map(key -> key + "=" + greetings.get(key))
                .collect(Collectors.joining("\n"));
        return mapAsString;
    }

    public void deleteHello(String name) {
        logger.info("user removed country");
        greetings.entrySet()
                .removeIf(entry -> entry.getKey().equals(name));
    }
}
