package com.jason.module.security.service.impl;

import com.jason.module.security.entity.UserGroupRe;
import com.jason.module.security.dao.UserGroupReMapper;
import com.jason.module.security.service.UserGroupReService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-用户组关联表 服务实现类
 * </p>
 *
 * @author lpli
 * @since 2019-01-05
 */
@Service
public class UserGroupReServiceImpl extends ServiceImpl<UserGroupReMapper, UserGroupRe> implements UserGroupReService {

}
