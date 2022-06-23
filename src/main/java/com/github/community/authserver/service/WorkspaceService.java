package com.github.community.authserver.service;

import com.github.community.authserver.models.Workspace;
import org.hibernate.jdbc.Work;

public interface WorkspaceService {

    Workspace saveWorkSpace(String name);

    Workspace updateWorkspace(String name, Long workspaceId);

    void deleteWorkspace(Long workspaceId);

    Workspace getWorkspace(Long id);
}
