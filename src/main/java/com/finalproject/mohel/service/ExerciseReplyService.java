package com.finalproject.mohel.service;

import java.util.List;

import com.finalproject.mohel.VO.ExerciseReplyVO;


public interface ExerciseReplyService {
	//��۵��
	public int exerciseReplyWrite(ExerciseReplyVO vo);
	//��۸��
	public List<ExerciseReplyVO> exerciseReplyList(int no);
	//��ۼ���
	public int exerciseReplyEdit(ExerciseReplyVO vo);
	//��ۻ���
	public int exerciseReplyDel(int no, String nickname);	
}
