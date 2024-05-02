package com.git.hitzaki.auth.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.git.hitzaki.auth.auth.model.po.AuthMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper 接口
 * @author hitzaki
 */
public interface AuthMenuMapper extends BaseMapper<AuthMenu> {
    @Select("SELECT	* FROM auth_menu WHERE id IN (SELECT menu_id FROM auth_permission WHERE role_id IN ( SELECT role_id FROM auth_user_role WHERE user_id = #{userId} ))")
    List<AuthMenu> selectPermissionByUserId(@Param("userId") String userId);
}
