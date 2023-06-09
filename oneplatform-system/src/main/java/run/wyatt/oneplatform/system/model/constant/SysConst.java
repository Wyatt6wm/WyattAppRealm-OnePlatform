package run.wyatt.oneplatform.system.model.constant;

/**
 * @author Wyatt
 * @date 2023/6/21 11:12
 */
public class SysConst {
    // ----- Redis缓存相关 -----
    public static final String CAPTCHA_REDIS_KEY_PREFIX = "captcha:";   // 验证码缓存KEY前缀
    public static final int CAPTCHA_REDIS_EXP_60_SECS = 60; // 验证码缓存过期时长：60秒
    public static final String REFRESH_ROLE_REDIS = "refreshRoleRedis"; // 用户需更新角色标识缓存的标志
    public static final String REFRESH_AUTH_REDIS = "refreshAuthRedis"; // 用户需更新权限标识缓存的标志
    public static final String ROLE_DB_CHANGE_TIME = "roleDbChangeTime"; // 角色最近更新数据库时间的缓存KEY
    public static final String AUTH_DB_CHANGE_TIME = "authDbChangeTime"; // 权限最近更新数据库时间的缓存KEY
    public static final String ROLE_REDIS_CHANGE_TIME="roleRedisChangeTime";// 角色最近更新缓存时间的缓存KEY
    public static final String AUTH_REDIS_CHANGE_TIME="authRedisChangeTime";// 权限最近更新缓存时间的缓存KEY
    // ----- 用户角色权限相关 -----
    public static final Long SUPER_ADMIN_ROLE_ID = 1L;  // 超级管理员角色ID
    public static final Long ADMIN_ROLE_ID = 2L;    // 管理员角色ID
    public static final Long DEFAULT_ROLE_ID = 3L;  // 默认角色ID
    public static final String SUPER_ADMIN_ROLE_IDENTIFIER = "super_admin";  // 超级管理员角色标识符
    public static final String ADMIN_ROLE_IDENTIFIER = "admin";  // 管理员角色标识符
    public static final String DEFAULT_ROLE_IDENTIFIER = "default";  // 默认角色标识符
}
