package com.zhaoyani.dao;

import com.zhaoyani.entity.Admin;

import java.util.List;

public interface AdminDao extends BaseDao<Admin> {


        List<Admin> findHouseOtherAdmin(List<Long> brokerIsd);
}
