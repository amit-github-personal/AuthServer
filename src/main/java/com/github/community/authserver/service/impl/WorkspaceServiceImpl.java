package com.github.community.authserver.service.impl;

import com.github.community.authserver.models.Workspace;
import com.github.community.authserver.service.WorkspaceService;

import javax.persistence.EntityManager;

public class WorkspaceServiceImpl implements WorkspaceService {

    private EntityManager entityManager;

    @Override
    public Workspace saveWorkSpace(String name) {
        return null;
    }

    @Override
    public Workspace updateWorkspace(String name, Long workspaceId) {
        return null;
    }

    @Override
    public void deleteWorkspace(Long workspaceId) {

    }

    @Override
    public Workspace getWorkspace(Long id) {
        return null;
    }
}
