package kr.or.ddit.file.model;

public class File {
	private int fileSeq;			// 첨부파일번호
	private String fileName;    	// 업로드파일명
	private String realFileName;	// 실제파일경로
	private int postSeq;        	// 게시글 번호

	public File() {	}
	
	public File(int fileSeq, String fileName, String realFileName, int postSeq) {
		this.fileSeq = fileSeq;
		this.fileName = fileName;
		this.realFileName = realFileName;
		this.postSeq = postSeq;
	}
	
	public int getFileSeq() {
		return fileSeq;
	}
	public void setFileSeq(int fileSeq) {
		this.fileSeq = fileSeq;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	public int getPostSeq() {
		return postSeq;
	}
	public void setPostSeq(int postSeq) {
		this.postSeq = postSeq;
	}
	
	@Override
	public String toString() {
		return "File [fileSeq=" + fileSeq + ", fileName=" + fileName + ", realFileName=" + realFileName + ", postSeq="
				+ postSeq + "]";
	}
}
