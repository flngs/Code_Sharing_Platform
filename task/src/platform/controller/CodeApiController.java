package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.entity.Code;
import platform.service.CodeService;

import java.util.List;
import java.util.Map;

@RestController
public class CodeApiController {

    private final CodeService codeService;

    @Autowired
    public CodeApiController(CodeService codeService) {
        this.codeService = codeService;
    }


    @GetMapping("/api/code/{id}")
    public ResponseEntity<Code> getCode(@PathVariable String id) {
        Code code = codeService.findCodeById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok()
                .header("Content-Type", "application/json")
                .body(code);
    }

    @GetMapping("/api/code/latest")
    public List<Code> getLatestCode() {
        return codeService.latestCode();
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Map<String, String>> setCode(@RequestBody Code newCode) {
        Code savedCode = codeService.saveCode(newCode);
        return ResponseEntity
                .ok()
                .body(Map.of("id", savedCode.getId()));
    }
}
