package com.finalproject.mohel.DAO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.finalproject.mohel.VO.BoardVO;
import com.finalproject.mohel.VO.ExercisePagingVO;
import com.finalproject.mohel.VO.ExerciseVO;

@Mapper
@Repository
public interface ExerciseDAO {
	//���� ����
	public int resolveStatus(String nickname,int no);
	public List<ExerciseVO> exerciseList(ExercisePagingVO pVO);
	public int exerciseInsert(BoardVO vo); 
	//public int exerciseSelect(BoardVO vo);
	public BoardVO exerciseSelect(int no);
	public int exerciseUpdate(BoardVO vo);
	public int exerciseDelete(int no, String nickname);

	public void cntHit (int no);

	//�ѷ��ڵ��
	public int totalRecord(ExercisePagingVO pVO);
	public int totalRecord1(ExerciseVO vo, ExercisePagingVO pVO);
	public int totalRecord2(ExerciseVO vo, ExercisePagingVO pVO);
	public int totalRecord3(ExerciseVO vo, ExercisePagingVO pVO);
	
	public int exerciseMemberInsert(ExerciseVO vo);
	public int exerciseMemberUpdate(ExerciseVO vo);
	public List<ExerciseVO> exerciseMemberShow(int no);
	public int exerciseMemberDelete(ExerciseVO vo);
	
	public int exerciseStateUpdate(ExerciseVO vo);
	public int exerciseStateDel(ExerciseVO vo);
	
	//�ı���
	public int exerciseReviewWrite(ExerciseVO vo);
	//�ı���
	public List<ExerciseVO> exerciseReviewList(int no);
	
	//���̵� ���� Ƚ��
	public int exerciseCountUp(ExerciseVO vo);
	

	//-------------------------------------------
	// ����� �
	
	public List<ExerciseVO> every_exerciseList(ExercisePagingVO pVO);
	public int every_exerciseInsert(ExerciseVO vo); 
	public ExerciseVO every_exerciseSelect(int no);
	public int every_exerciseUpdate(ExerciseVO vo);
	public int every_exerciseDelete(int no, String nickname);
	
	public void every_cntHit (int no);
}
