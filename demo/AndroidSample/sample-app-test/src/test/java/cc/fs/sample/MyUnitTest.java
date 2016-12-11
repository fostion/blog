package cc.fs.sample;

import org.junit.Test;

import cc.fs.sample.com.ComUtil;
import cc.fs.sample.utils.FileUtil;

import static org.junit.Assert.assertEquals;

public class MyUnitTest {

//    @Test
//    public void checkFileSizeFormatB() throws Exception {
//        assertEquals("966B", FileUtil.formatSize(966));
//    }

//    @Test
//    public void checkFileSizeFormatKB() throws Exception {
//        assertEquals("1.00KB", FileUtil.formatSize(1024));
//    }
//
//    @Test
//    public void checkFileSizeFormatMB() throws Exception {
//        assertEquals("1.00MB", FileUtil.formatSize(1024*1024));
//    }
//
//    @Test
//    public void checkFileSizeFormatGB() throws Exception {
//        assertEquals("1.00GB", FileUtil.formatSize(1024*1024*1024));
//    }

//    @Test
//    public void checkYangHuiSanjiao() throws Exception {
//        assertEquals(10, ComUtil.yanghui(20));
//    }
//
//    @Test
//    public void checkQuit3() throws Exception {
//        assertEquals(1, ComUtil.quit3(110));
//    }

    @Test
    public void reverseStr() throws Exception {
        assertEquals("blue is sky the", ComUtil.reverseStr("the sky is blue"));
    }

}
