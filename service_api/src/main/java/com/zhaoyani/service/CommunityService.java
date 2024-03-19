package com.zhaoyani.service;

import com.zhaoyani.entity.Community;

import java.util.List;


public interface CommunityService extends BaseService<Community> {

    List<Community> findAll();

}
