package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.entity.Code;
import platform.service.CodeService;

@Controller
public class CodeViewController {

    private final CodeService codeService;

    @Autowired
    public CodeViewController(CodeService codeService) {
        this.codeService = codeService;
    }


    @GetMapping("/code/{id}")
    public String getCodeView(@PathVariable Long id, Model model) {
        Code codeById = codeService.findCodeById(id);
        model.addAttribute("code_snippet", codeById.getCode());
        model.addAttribute("load_date", codeById.getDate());
        return "codeSingle";
    }

    @GetMapping("/code/new")
    public String setCodeView() {
        return "addCodeSnippet";
    }

    @GetMapping("/code/latest")
    public String getLatestCode(Model model) {
        model.addAttribute("codes", codeService.latestCode());
        return "codeLatestList";
    }
}
