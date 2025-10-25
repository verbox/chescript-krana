package org.chescript.krana.common;

import junit.framework.TestCase;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.chescript.krana.common.model.CardImage;
import org.chescript.krana.common.model.CardPhenotype;
import org.chescript.krana.common.model.io.CardPhenotypeLoader;

public class CardPhenotypeLoaderTest extends TestCase {

    public void testParseSingleObjectFromString() throws Exception {
        String json = "{\"name\":\"Hero\",\"label\":\"H\",\"description\":\"desc\",\"strength\":3,\"willpower\":2,\"lore\":0,\"cardImage\":{\"imageId\":\"i\",\"filename\":\"f.png\"}}";
        List<CardPhenotype> list = CardPhenotypeLoader.parseFromJsonString(json);
        assertNotNull(list);
        assertEquals(1, list.size());
        CardPhenotype p = list.get(0);
        assertEquals("Hero", p.getName());
        assertNotNull(p.getCardImage());
        assertEquals("f.png", p.getCardImage().getFilename());
    }

    public void testParseArrayFromFile() throws Exception {
        String json = "[\n" +
                "  {\"name\":\"A\",\"label\":\"A\",\"description\":\"d\",\"strength\":1,\"willpower\":1,\"lore\":0,\"cardImage\":{\"imageId\":\"i1\",\"filename\":\"f1.png\"}},\n" +
                "  {\"name\":\"B\",\"label\":\"B\",\"description\":\"d\",\"strength\":2,\"willpower\":2,\"lore\":0,\"cardImage\":{\"imageId\":\"i2\",\"filename\":\"f2.png\"}}\n" +
                "]";
        Path tmp = Files.createTempFile("cards", ".json");
        Files.writeString(tmp, json);
        List<CardPhenotype> list = CardPhenotypeLoader.loadFromFile(tmp);
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals("A", list.get(0).getName());
        Files.deleteIfExists(tmp);
    }
}

