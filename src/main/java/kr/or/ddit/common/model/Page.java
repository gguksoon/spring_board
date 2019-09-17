package kr.or.ddit.common.model;

public class Page {
	private int page;		// 현재 페이지
	private int pagesize;	// 페이지 내에 보여줄 갯수
	
	public Page() {	}

	public Page(int page, int pagesize) {
		this.page = page;
		this.pagesize = pagesize;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	
	@Override
	public String toString() {
		return "Page [page=" + page + ", pagesize=" + pagesize + "]";
	}
}
