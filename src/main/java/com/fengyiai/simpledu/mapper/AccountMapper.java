package com.fengyiai.simpledu.mapper;

import com.fengyiai.simpledu.model.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
}