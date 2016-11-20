package cc.fs.sample;

import org.junit.Test;

import cc.fs.sample.utils.FileUtil;

import static org.junit.Assert.assertEquals;

public class MyUnitTest {

    @Test
    public void checkFileSizeFormatB() throws Exception {
        assertEquals("966B", FileUtil.formatSize(966));
    }

    @Test
    public void checkFileSizeFormatKB() throws Exception {
        assertEquals("1.00KB", FileUtil.formatSize(1024));
    }

    @Test
    public void checkFileSizeFormatMB() throws Exception {
        assertEquals("1.00MB", FileUtil.formatSize(1024*1024));
    }

    @Test
    public void checkFileSizeFormatGB() throws Exception {
        assertEquals("1.00GB", FileUtil.formatSize(1024*1024*1024));
    }

}
