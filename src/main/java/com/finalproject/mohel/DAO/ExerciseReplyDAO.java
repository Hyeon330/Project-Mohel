package com.finalproject.mohel.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.VO.ExerciseReplyVO;

@Mapper
@Repository
public interface ExerciseReplyDAO {
	//��۵��
		public int exerciseReplyWrite(ExerciseReplyVO vo);
		//��۸��
		public List<ExerciseReplyVO> exerciseReplyList(int no);
		//��ۼ���
		public int exerciseEdit(ExerciseReplyVO vo);
		//��ۻ���
		public int exerciseReplyDel(int no, String nickname);
}
