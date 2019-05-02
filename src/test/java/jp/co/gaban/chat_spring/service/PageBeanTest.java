package jp.co.gaban.chat_spring.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by DaikiTakeuchi on 2019/05/02.
 */
public class PageBeanTest {

    private static final int TOTAL_COUNT = 15;
    private static final int CURRENT_PAGE = 1;
    private static final int MAX_PAGE = 2;

    @Test
    public void get() {
        PageBean pageBean = new PageBean();
        pageBean.setTotalCount(TOTAL_COUNT);
        pageBean.setCurrentPage(CURRENT_PAGE);
        pageBean.setMaxPage(MAX_PAGE);

        assertEquals(TOTAL_COUNT, pageBean.getTotalCount());
        assertEquals(CURRENT_PAGE, pageBean.getCurrentPage());
        assertEquals(MAX_PAGE, pageBean.getMaxPage());
    }
}
