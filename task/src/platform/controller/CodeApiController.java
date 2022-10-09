package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.entity.Code;
import platform.service.CodeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CodeApiController {

    private final CodeService codeService;

    @Autowired
    public CodeApiController(CodeService codeService) {
        this.codeService = codeService;
    }


    @GetMapping("/api/code/{id}")
    public ResponseEntity<Code> getCode(@PathVariable Long id) {
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(codeService.findCodeById(id));
    }

    @GetMapping("/api/code/latest")
    public Collection<Code> getLatestCode() {
        return codeService.latestCode();
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Map<String, String>> setCode(@RequestBody Code newCode) {
        Code savedCode = codeService.saveCode(newCode);

        Map<String, String> resp = new HashMap<>();
        resp.put("id", Long.toString(savedCode.getId())); //TODO JSON formatter
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
