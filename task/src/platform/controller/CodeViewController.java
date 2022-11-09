package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
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
    public String getCodeView(@PathVariable String id, Model model) {
        Code codeById = codeService.findCodeById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("code", codeById);
        return "singleSnippet";
    }

    @GetMapping("/code/new")
    public String setCodeView() {
        return "createSnippet";
    }

    @GetMapping("/code/latest")
    public String getLatestCode(Model model) {
        model.addAttribute("codes", codeService.latestCode());
        return "latestSnippets";
    }
}
