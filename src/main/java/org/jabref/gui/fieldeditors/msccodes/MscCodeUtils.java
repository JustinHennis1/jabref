package org.jabref.gui.fieldeditors.msccodes;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MscCodeUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(MscCodeUtils.class);
    /**
     * Load MSC codes and descriptions from a JSON file into a HashMap
     *
     * @param jsonFilePath Path to the JSON file containing MSC codes
     * 
     * @return Map with MSC codes as keys and descriptions as values
     */
    public static Map<String, String> loadMscCodesFromJson(String jsonFilePath) {
        if (jsonFilePath == null) {
            LOGGER.error("param jsonFilePath is null {}", jsonFilePath);
            return Collections.emptyMap();
        }

        try {

            ObjectMapper mapper = new ObjectMapper();

            Path path = Path.of(jsonFilePath);

            return mapper.readValue(path.toFile(), new TypeReference<Map<String, String>>() { });
        } catch (IOException e) {
            LOGGER.error("Error in loadMscCodesFromJson, Message: {}", e.getMessage());
            return Collections.emptyMap();
        }
    }

    /* Load MSC codes and descriptions from a JSON resource URL into a HashMap
     * 
     * @param resourceUrl URL to the JSON resource containing MSC codes
     * @return Map with MSC codes as keys and descriptions as values
     */
    public static Map<String, String> loadMscCodesFromJson(URL resourceUrl) {
        try {
            if (resourceUrl == null) {
                LOGGER.error("param resourceUrl is null");
                return Collections.emptyMap();
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(resourceUrl, new TypeReference<Map<String, String>>() { });
        } catch (IOException e) {
            LOGGER.error("Failed to load MSC codes from JSON URL", e);
            return Collections.emptyMap();
        }
    }
}
