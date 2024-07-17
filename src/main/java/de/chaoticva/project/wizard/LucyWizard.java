package de.chaoticva.project.wizard;

import com.intellij.ide.actions.CreateFileFromTemplateAction;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.wizard.AbstractNewProjectWizardStep;
import com.intellij.ide.wizard.LanguageNewProjectWizard;
import com.intellij.ide.wizard.NewProjectWizardLanguageStep;
import com.intellij.ide.wizard.NewProjectWizardStep;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.module.WebModuleBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

import static com.intellij.ide.wizard.UIWizardUtil.setupProjectFromBuilder;

public class LucyWizard implements LanguageNewProjectWizard {
    @Override
    public int getOrdinal() {
        return 0;
    }

    @NotNull
    @Override
    public String getName() {
        return "Lucy";
    }

    @NotNull
    @Override
    public NewProjectWizardStep createStep(@NotNull NewProjectWizardLanguageStep parent) {
        return new Step(parent);
    }

    static class Step extends AbstractNewProjectWizardStep {
        private final NewProjectWizardLanguageStep parent;

        public Step(NewProjectWizardLanguageStep parent) {
            super(parent);
            this.parent = parent;
        }

        @Override
        public void setupProject(@NotNull Project project) {
            WebModuleBuilder<?> builder = new WebModuleBuilder<>();
            builder.setName(parent.getName());
            builder.setContentEntryPath(parent.getPath() + "/" + parent.getName());
            setupProjectFromBuilder(this, project, builder);

            StartupManager startupManager = StartupManager.getInstance(project);
            startupManager.runAfterOpened(() -> {
                String contentEntryPath = builder.getContentEntryPath();
                if (contentEntryPath == null) {
                    return;
                }
                VirtualFile root = LocalFileSystem.getInstance().findFileByPath(contentEntryPath);
                if (root == null) {
                    return;
                }

                WriteCommandAction.runWriteCommandAction(project, null, null, () -> {
                    FileTemplateManager fileTemplateManager = FileTemplateManager.getInstance(project);
                    FileTemplate fileTemplate = fileTemplateManager.getInternalTemplate("Lucy Class");
                    PsiDirectory directory = PsiManager.getInstance(project).findDirectory(root);
                    if (directory != null) {
                        CreateFileFromTemplateAction.createFileFromTemplate("main", fileTemplate, directory, null, true);
                    }
                });
            });
        }
    }
}
