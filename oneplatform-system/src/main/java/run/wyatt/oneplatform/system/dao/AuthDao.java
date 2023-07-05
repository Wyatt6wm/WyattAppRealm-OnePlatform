package run.wyatt.oneplatform.system.dao;

import org.apache.ibatis.annotations.Mapper;
import run.wyatt.oneplatform.system.model.entity.Auth;

import java.util.List;

/**
 * @author Wyatt
 * @date 2023/6/12 15:04
 */
@Mapper
public interface AuthDao {

    Long insert(Auth record);
    List<Auth> findAuthsByUserId(Long userId);
    int update(Auth auth);
}
