package jp.co.gaban.chat_spring.service;

/**
 * Created by takeuchidaiki on 2024/06/01
 */
public class PageBean {

    private int totalCount;
    private int currentPage;
    private int maxPage;

    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getMaxPage() {
        return maxPage;
    }
    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
}
