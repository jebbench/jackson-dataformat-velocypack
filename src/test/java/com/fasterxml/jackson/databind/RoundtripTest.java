package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.databind.testutil.MediaItem;
import com.fasterxml.jackson.dataformat.velocypack.TestVelocypackMapper;

public class RoundtripTest extends BaseMapTest
{
    private final ObjectMapper MAPPER = new TestVelocypackMapper();
    
    public void testMedaItemRoundtrip() throws Exception
    {
        MediaItem.Content c = new MediaItem.Content();
        c.setBitrate(9600);
        c.setCopyright("none");
        c.setDuration(360000L);
        c.setFormat("lzf");
        c.setHeight(640);
        c.setSize(128000L);
        c.setTitle("Amazing Stuff For Something Or Oth\u00CBr!");
        c.setUri("http://multi.fario.us/index.html");
        c.setWidth(1400);

        c.addPerson("Joe Sixp\u00e2ck");
        c.addPerson("Ezekiel");
        c.addPerson("Sponge-Bob Squarepant\u00DF");
        
        MediaItem input = new MediaItem(c);
        input.addPhoto(new MediaItem.Photo());
        input.addPhoto(new MediaItem.Photo());
        input.addPhoto(new MediaItem.Photo());

        byte[] bytes = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsBytes(input);

        MediaItem output = MAPPER.readValue(bytes, MediaItem.class);
        assertNotNull(output);

        assertNotNull(output.getImages());
        assertEquals(input.getImages().size(), output.getImages().size());
        assertNotNull(output.getContent());
        assertEquals(input.getContent().getTitle(), output.getContent().getTitle());
        assertEquals(input.getContent().getUri(), output.getContent().getUri());

        // compare re-serialization as a simple check as well
        assertEquals(bytes, MAPPER.writerWithDefaultPrettyPrinter().writeValueAsBytes(output));
    }
}