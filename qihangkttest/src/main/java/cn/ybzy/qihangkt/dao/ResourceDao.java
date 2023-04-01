package cn.ybzy.qihangkt.dao;

import org.apache.ibatis.annotations.Param;

public interface ResourceDao extends BaseDao{

	public int selectCountResByPath(@Param("path") String path);

}
