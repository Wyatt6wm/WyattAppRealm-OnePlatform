package run.wyatt.oneplatform.user.dao;

import org.apache.ibatis.annotations.Mapper;
import run.wyatt.oneplatform.user.model.entity.Role;

import java.util.List;

/**
 * @author Wyatt
 * @date 2023/6/13 12:38
 */
@Mapper
public interface RoleDao {
    List<Role> findActivatedRolesByUserId(Long userId);
}