package ai.elimu.util.content;

import ai.elimu.model.content.StoryBook;
import ai.elimu.web.content.storybook.StoryBookListController;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class EpubToStoryBookConverterLetsReadAsiaTest {
    
    private final Logger logger = Logger.getLogger(getClass());
    
    @Test
    public void testExtractChapterReferencesFromTOC() throws IOException {
        ResourceLoader resourceLoader = new ClassRelativeResourceLoader(StoryBookListController.class);
        Resource tocResource = resourceLoader.getResource("fil-lra-0acfe340-6116-4f8a-a45d-c925c8a1fd0e_toc.xhtml");
        logger.info("tocResource: " + tocResource);
        File tocFile = tocResource.getFile();
        
        List<String> chapterReferences = EpubToStoryBookConverterLetsReadAsia.extractChapterReferencesFromTOC(tocFile);
        assertThat(chapterReferences.isEmpty(), not(true));
        assertThat(chapterReferences.size(), is(12));
    }
    
    @Test
    public void testGetStoryBookFromEpub_Filipino() throws IOException {
        ResourceLoader resourceLoader = new ClassRelativeResourceLoader(StoryBookListController.class);
        Resource ePubResource = resourceLoader.getResource("fil-lra-0acfe340-6116-4f8a-a45d-c925c8a1fd0e.epub");
        logger.info("ePubResource: " + ePubResource);
        File ePubFile = ePubResource.getFile();
        
        StoryBook storyBook = EpubToStoryBookConverterLetsReadAsia.getStoryBookFromEpub(ePubFile);
        assertThat(storyBook, not(nullValue()));
        assertThat(storyBook.getTitle(), is("Hindi na Ako natatakot!"));
        assertThat(storyBook.getParagraphs().size(), is(11)); // TODO
    }
    
    @Test
    public void testGetStoryBookFromEpub_Filipino_2() throws IOException {
        ResourceLoader resourceLoader = new ClassRelativeResourceLoader(StoryBookListController.class);
        Resource ePubResource = resourceLoader.getResource("fil-lra-faa0d66e-564f-4d72-a1d3-ec46fb754205.epub");
        logger.info("ePubResource: " + ePubResource);
        File ePubFile = ePubResource.getFile();
        
        StoryBook storyBook = EpubToStoryBookConverterLetsReadAsia.getStoryBookFromEpub(ePubFile);
        assertThat(storyBook, not(nullValue()));
        assertThat(storyBook.getTitle(), is("Narinig mo ba?"));
        assertThat(storyBook.getParagraphs().size(), is(7)); // TODO
    }
}
