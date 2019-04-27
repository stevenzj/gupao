/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1.mapper;

import com.st.mybatis.test1.entity.Account;
import com.st.mybatis.test1.entity.AccountAndPassport;
import com.st.mybatis.test1.entity.Passport;

/**
 * @Title: AccountMapper
 * @Description:
 * @Author zhujing
 * @Date 2019/4/26
 * @Version V1.0
 */
public interface AccountMapper {
    public Account getById(Long id);

    public AccountAndPassport getAll(Long id);

    public Passport getByAccountId(Long accountId);

    public AccountAndPassport getAllNew(Long id);
}
