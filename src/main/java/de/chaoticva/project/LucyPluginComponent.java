package de.chaoticva.project;

import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import org.jetbrains.annotations.NotNull;

public class LucyPluginComponent implements ProjectManagerListener {
    @Override
    public void projectOpened(@NotNull Project project) {
        FileTemplateManager fileTemplateManager = FileTemplateManager.getInstance(project);

        fileTemplateManager.addTemplate("Lucy Class", "lc");
    }
}
