package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.entity.Code;
import platform.repository.CodeRepo;

import java.util.List;
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

    public List<Code> latestCode() {
        List<Code> latestCode = codeRepo.findLatest10();
        latestCode.forEach(Code::increaseViewCount);
        codeRepo.saveAll(latestCode);
        return latestCode;
    }

    public Optional<Code> findCodeById(String id) {
        Optional<Code> codeOptional = codeRepo.findById(id);
        Code code = codeOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); // TODO 404
        if (code.isAccessible()) {
            code.increaseViewCount();
            codeRepo.save(code);
        } else {
            codeRepo.delete(code);
            code = null;
        }
        return Optional.ofNullable(code);
    }
}
