package io.github.talelin.merak.service;

import io.github.talelin.merak.model.PermissionDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author pedro
 * @since 2019-11-30
 */
public interface PermissionService extends IService<PermissionDO> {

    /**
     * 通过分组id得到分组的权限
     *
     * @param groupId 分组id
     * @return 权限
     */
    List<PermissionDO> getPermissionByGroupId(Long groupId);

    /**
     * 通过分组id得到分组的权限
     *
     * @param groupIds 分组id
     * @return 权限
     */
    List<PermissionDO> getPermissionByGroupIds(List<Long> groupIds);

    /**
     * 通过分组id得到分组的权限与分组id的映射
     *
     * @param groupIds 分组id
     * @return 权限map
     */
    Map<Long, List<PermissionDO>> getPermissionMapByGroupIds(List<Long> groupIds);

    /**
     * 将权限结构化
     *
     * @param permissions 权限
     * @return 结构化的权限
     */
    List<Map<String, List<Map<String, String>>>> structuringPermissions(List<PermissionDO> permissions);

    /**
     * 将权限简单地结构化
     *
     * @param permissions 权限
     * @return 结构化的权限
     */
    Map<String, List<String>> structuringPermissionsSimply(List<PermissionDO> permissions);
}
