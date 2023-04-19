package com.campEZ.Project0.camping.dao;

import com.campEZ.Project0.entity.Camparea;
import com.campEZ.Project0.entity.Camping;

import java.util.List;
import java.util.Optional;

public interface CampingDAO {

  //캠핑장 등록, 캠핑 구역 등록
  List<Object> campingSave(Camping camping, Camparea camparea);
  //캠핑장 수정, 캠핑 구역 수정
  int campingUpdate(Camping camping, Camparea camparea, int cnumber);
  //캠핑장 삭제
  int campingDelete(int cnumber);
  //캠핑장 조회
  Optional<Camping> campingDetail(int cnumber);
  //  내 캠핑장 조회
  List<Camping> campingFindByManagerMid(String mid);
  //캠핑장 검색
  List<Camping> campingSearch(CampingFilterCondition campingFilterCondition);

}
