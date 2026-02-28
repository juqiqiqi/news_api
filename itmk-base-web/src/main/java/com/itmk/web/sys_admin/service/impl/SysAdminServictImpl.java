package com.itmk.web.sys_admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.sys_admin.entity.SysAdmin;
import com.itmk.web.sys_admin.mapper.SysAdminMapper;
import com.itmk.web.sys_admin.service.SysAdminService;
import org.springframework.stereotype.Service;

@Service
public class SysAdminServictImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {
}
