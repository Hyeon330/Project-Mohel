package com.finalproject.mohel.VO;

public class ExercisePagingVO {
	// ����¡
		private int onePageRecord = 9; // �� �������� ��� �� ������ ��
		private int pageNum = 1; // ���� ������
		private int totalRecord;
		private int totalRecord1; // �� ���ڵ� ��
		private int totalRecord2; // �� ���ڵ� ��
		private int totalRecord3; // �� ���ڵ� ��
		private int totalPage; // �� ������ ��
		private int totalPage1; // �� ������ ��
		private int totalPage2; // �� ������ ��
		private int totalPage3; // �� ������ ��
		private int offsetIndex = 0;
		private int onePageCount = 5; // �� ���� ǥ���� ���ڵ� ��
		private int startPage = 1;

		// �˻�
		private String searchKey;
		private String searchWord;

		public int getOnePageRecord() {
			return onePageRecord;
		}

		public void setOnePageRecord(int onePageRecord) {
			this.onePageRecord = onePageRecord;
		}

		public int getPageNum() {
			return pageNum;
		}

		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
			// offset ��ġ ���
			offsetIndex = (pageNum - 1) * onePageRecord;
			// ������ ��ȣ�� ���۰�
			startPage = ((pageNum - 1) / onePageCount * onePageCount) + 1;
		}

		//
		public int getTotalRecord() {
			return totalRecord;
		}
		public void setTotalRecord(int totalRecord) {
			this.totalRecord = totalRecord;

			// �� ������ ��
			if (totalRecord % onePageRecord == 0) { // ������ ���ڵ尡 ���� ���
				totalPage = totalRecord / onePageRecord;
			} else { // ������ ���ڵ尡 �ִ� ��� (1~4��)
				totalPage = totalRecord / onePageRecord + 1;
			}
		}
		
		//
		public int getTotalRecord1() {
			return totalRecord1;
		}
		public void setTotalRecord1(int totalRecord1) {
			this.totalRecord1 = totalRecord1;

			// �� ������ ��
			if (totalRecord1 % onePageRecord == 0) { // ������ ���ڵ尡 ���� ���
				totalPage1 = totalRecord1 / onePageRecord;
			} else { // ������ ���ڵ尡 �ִ� ��� (1~4��)
				totalPage1 = totalRecord1 / onePageRecord + 1;
			}
		}
		
		public int getTotalRecord2() {
			return totalRecord2;
		}

		public void setTotalRecord2(int totalRecord2) {
			this.totalRecord2 = totalRecord2;

			// �� ������ ��
			if (totalRecord2 % onePageRecord == 0) { // ������ ���ڵ尡 ���� ���
				totalPage2 = totalRecord2 / onePageRecord;
			} else { // ������ ���ڵ尡 �ִ� ��� (1~4��)
				totalPage2 = totalRecord2 / onePageRecord + 1;
			}
		}
		public int getTotalRecord3() {
			return totalRecord3;
		}

		public void setTotalRecord3(int totalRecord3) {
			this.totalRecord3 = totalRecord3;

			// �� ������ ��
			if (totalRecord3 % onePageRecord == 0) { // ������ ���ڵ尡 ���� ���
				totalPage3 = totalRecord3 / onePageRecord;
			} else { // ������ ���ڵ尡 �ִ� ��� (1~4��)
				totalPage3 = totalRecord3 / onePageRecord + 1;
			}
		}
		
		//

		public int getTotalPage() {
			return totalPage;
		}

		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}


		public int getTotalPage1() {
			return totalPage1;
		}

		public void setTotalPage1(int totalPage1) {
			this.totalPage1 = totalPage1;
		}

		public int getTotalPage2() {
			return totalPage2;
		}

		public void setTotalPage2(int totalPage2) {
			this.totalPage2 = totalPage2;
		}

		public int getTotalPage3() {
			return totalPage3;
		}

		public void setTotalPage3(int totalPage3) {
			this.totalPage3 = totalPage3;
		}

		public int getOffsetIndex() {
			return offsetIndex;
		}

		public void setOffsetIndex(int offsetIndex) {
			this.offsetIndex = offsetIndex;
		}

		public int getOnePageCount() {
			return onePageCount;
		}

		public void setOnePageCount(int onePageCount) {
			this.onePageCount = onePageCount;
		}

		public int getStartPage() {
			return startPage;
		}

		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}

		public String getSearchKey() {
			return searchKey;
		}

		public void setSearchKey(String searchKey) {
			this.searchKey = searchKey;
		}

		public String getSearchWord() {
			return searchWord;
		}

		public void setSearchWord(String searchWord) {
			this.searchWord = searchWord;
		}
}
