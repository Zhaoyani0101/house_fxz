package com.zhaoyani.dao;

import com.zhaoyani.entity.Community;

import java.util.List;

public interface CommunityDao extends BaseDao<Community> {

    List<Community> findAll();

}
