/**
 * <p>Copyright (c) TongCheng 2019</p>
 */
package com.st.mybatis.test1.mapper;

import com.st.mybatis.test1.entity.Passport;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @Title: PassportMapper
 * @Description:
 * @Author zhujing
 * @Date 2019/4/26
 * @Version V1.0
 */
public interface PassportMapper {
    public Passport getById(Long id);

    public List<Passport> getList(RowBounds rowBounds);
}
