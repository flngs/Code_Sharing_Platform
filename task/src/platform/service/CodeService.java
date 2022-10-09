package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.entity.Code;
import platform.repository.CodeRepo;

import java.util.Collection;
import java.util.Optional;

@Service
public class CodeService {

    private final CodeRepo codeRepo;

    @Autowired
    public CodeService(CodeRepo codeRepo) {
        this.codeRepo = codeRepo;
    }

    public Code saveCode(Code code) {
        return codeRepo.save(code);
    }

    public Collection<Code> latestCode() {
        return codeRepo.findTop10ByOrderByIdDesc();
    }

    public Code findCodeById(Long id) {
        Optional<Code> code = codeRepo.findById(id);
        return code.orElseThrow();
    }

}
